package com.aml.common.filter;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aml.api.dto.RequestDto;
import com.aml.api.pojo.Menu;
import com.aml.api.pojo.Users;
import com.aml.common.core.ApiResult;
import com.aml.common.core.Constants;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.service.RedisService;
import com.aml.common.thread.LogThread;
import com.aml.common.util.IpUtils;
import com.aml.common.util.RepeatReadHttpServletRequestWrapper;
import com.aml.common.util.UMSUtils;

/**
 * 判断用户是否登陆过滤器
 * <p>
 * 用户登陆 , token userId, token
 * </p>
 * 
 * @author zouwei
 * @since 2017年1月6日
 * @version 0.0.2
 */
public class VerifyLoginFilter extends OncePerRequestFilter {

	public static final Logger logger = LoggerFactory.getLogger(VerifyLoginFilter.class);

	/** 需要手动注入redis */
	private RedisService redisService;
	
	/** 线程池 */
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

    private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

	/** 忽略的文件后缀 */
	private final String[] ignores = { "txt", "html", "htm", "css", "js", "png", "gif", "jpeg", "jpg" };

	/** 不需要登陆校验的get请求Url集合(包括子Url) */
	private Set<String> getMethodUrlSet = new HashSet<>();

	/** 不需要登陆校验的所有请求Url集合(包括子Url) */
	private Set<String> allMethodUrlSet = new HashSet<String>();
	
	/** 不需要权限校验的所有请求Url集合(包括子Url) */
	private Set<String> hasAccessUrlSet = new HashSet<String>();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURI();
		// 静态资源放行
		if (isStaticResources(url)) {
			filterChain.doFilter(request, response); // 直接放行
			return;
		}

		String method = request.getMethod();
		HttpServletRequest requestWrapper = null;

		// 是否需要校验
		boolean verify = isVerify(url, method);

		if (verify) {
			String loginUser = null, token = null;
			Long userId = null;
			
			if ("GET".equals(method)) { // get请求 直接获取参数
				token = request.getParameter("token");
				if (StringUtils.isNotBlank(token)) {
					loginUser = request.getParameter("userId");
					try {
						userId = Long.parseLong(loginUser);
					} catch (Exception e) {
						writeNoLoginMsg(response,ErrorTypeEnum.NO_LOGIN);
						return;
					}
				}
			} else { // 其他请求，从request Body里面读取
					String contentType = request.getContentType();
					if(StringUtils.isNotEmpty(contentType)){
						contentType = contentType.split(";")[0];
					}
					if(MediaType.APPLICATION_JSON_UTF8_VALUE.contains(contentType)){
						//json数据
						Reader reader = null;
						try {
							requestWrapper = new RepeatReadHttpServletRequestWrapper(request);
							reader = requestWrapper.getReader();
							RequestDto dto = JSON.parseObject(IOUtils.toString(reader), RequestDto.class);
							if (dto == null) {
								writeNoLoginMsg(response,ErrorTypeEnum.NO_LOGIN);
								return;
							}
							token = dto.getToken();
							if (StringUtils.isNotBlank(token)) {
								userId = dto.getUserId();
							}
						} finally {
							IOUtils.closeQuietly(reader);
						}
					}else if(MediaType.APPLICATION_FORM_URLENCODED_VALUE.contains(contentType)){
						//表单提交
						token = request.getParameter("token");
						String userIdStr = request.getParameter("userId");
						if(StringUtils.isNotBlank(userIdStr)){
							userId = Long.parseLong(userIdStr);
						}
					}else if(MediaType.MULTIPART_FORM_DATA_VALUE.contains(contentType)){
						//文件上传
                        MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
                        token=multiReq.getParameter("token");//获取参数中的token
						String userIdStr = multiReq.getParameter("userId");
						if(StringUtils.isNotBlank(userIdStr)){
							userId = Long.parseLong(userIdStr);
						}
						request = multiReq;//将转化后的reuqest赋值到过滤链中的参数 *重要
					}
			}
			try {
				
				//检查接口请求是否登陆成功
				if (!hashToken(token, userId)) {
					if(redisService.verifyLoginElsewhere(token, String.valueOf(userId))){
						writeNoLoginMsg(response,ErrorTypeEnum.OTHER_EQUIPMENT_LOGIN);
						return;
					}
					writeNoLoginMsg(response,ErrorTypeEnum.NO_LOGIN);
					return;
				}else{
					int saveTime = 60 * 60; // 1个小时
					
					redisService.setValueAndExpire(token.split(":")[0] + ":" + userId, token, saveTime);
					redisService.setValueAndExpire(token+userId, redisService.getValue(token+userId), saveTime);
					
					// 需要放到缓存，登陆成功，保存权限列表
					String allMenuStr = redisService.getValue(Constants.REDIS_KEY_AML_ALL_MENU);
					String userMenuStr = redisService.getValue(Constants.REDIS_KEY_AML_USER_MENU + userId);
					String userInfoStr = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
					if(allMenuStr == null || userMenuStr == null || userInfoStr == null){
						//重新获取权限和用户数据
						Map<String, Object> map = UMSUtils.queryUsersPermissions(userId, token);
						userMenuStr = JSON.toJSONString(map.get("listMenu"));
						userInfoStr = JSON.toJSONString(map.get("user"));
						int  time = 60 * 60; 
						if(StringUtils.isEmpty(allMenuStr)){
							allMenuStr = JSON.toJSONString(map.get("allMenu"));
							redisService.setValueAndExpire(Constants.REDIS_KEY_AML_ALL_MENU, allMenuStr, time);
						}
						redisService.setValueAndExpire(Constants.REDIS_KEY_AML_USER_MENU + userId, userMenuStr, time);
						redisService.setValueAndExpire(Constants.REDIS_KEY_AML_USER_INFO + userId, userInfoStr, time);
					}
	
					List<Menu> userMenu = JSON.parseArray(userMenuStr,Menu.class);
					List<Menu> allMenu = JSON.parseArray(allMenuStr,Menu.class);
					Users userInfo = JSON.parseObject(userInfoStr,Users.class);
					//登陆状态,0_有效，1_无效
					Integer loginStatus = userInfo.getLoginStatus();
					//授权状态:0：授权中，1：通过，2：不通过
					Integer authStatus = userInfo.getAuthStatus();
					if(ObjectUtils.isEmpty(loginStatus) || ObjectUtils.isEmpty(authStatus) 
							|| loginStatus != 0 || authStatus != 1){
						redisService.delete(token);
						writeNoAuthMsg(response);
						return;
					}
					
					//验证接口请求的访问权限 update ,用户已登陆状态，该用户是否有接口访问权限
					if(userId != -1 && isAccess(url, method) &&!isAuthorized(request,allMenu,userMenu)){ 
						writeNoAuthMsg(response);
						return;
					}
	
	//				Users user = JSON.parseObject(userInfoStr,Users.class);
					for(Menu menu: allMenu){
						String[] menuUrls = menu.getMenuUrl().split(",");
						String menuUrl = menuUrls[menuUrls.length-1];
						if("".equals(menuUrl)){
							continue;
						}
						if(url.contains(menuUrl) || "/login".equals(url) || "/logout".equals(url)){
							//记录操作日志
							JSONObject msg = new JSONObject();
							msg.put("token", token);
							msg.put("userId", userId);
							msg.put("function", url);
							msg.put("clientIpAddress", IpUtils.clientIp(request));
							msg.put("id", "AML");
	//						userLog.setCompanyCode(user.getCompanyCode());
	//						userLog.setDepartmentCode(user.getDepartmentCode());
	//						userLog.setTeamCode(user.getTeamCode());
							taskExecutor.execute(new LogThread(msg, token, userId));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 验证通过，放行
		if (requestWrapper != null) {
			filterChain.doFilter(requestWrapper, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
	/**
	 * 重写该方法 执行初始化程序
	 * <p>
	 * Note: 此方法将从标准过滤器初始化以及Spring应用程序上下文中的过滤器bean初始化调用
	 * <p>
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		FilterConfig config = this.getFilterConfig();
		if (config != null) {
			// tomcat初始化filter
			setInitParam(config);
		} else {
			// spring容器初始化该bean
		}
	}

	/**
	 * 是否静态访问静态资源
	 * 
	 * @param url
	 * @return
	 */
	private boolean isStaticResources(String url) {
		// 拦截静态资源
		if (url.lastIndexOf(".") >= 0) {
			String[] split = url.split("\\.");
			String suffix = split[split.length - 1];
			suffix = suffix.toLowerCase();
			for (String ignore : ignores) {
				if (ignore.equals(suffix)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否需要校验
	 * 
	 * @param url
	 * @param method
	 * @return
	 */
	private boolean isVerify(String url, String method) {
		// options嗅探方法全部放行
		if ("OPTIONS".equals(method)) {
			return false;
		}

		// 不需要登陆校验的所有请求Url
		for (String allMethodUrl : allMethodUrlSet) {
			if (url.indexOf(allMethodUrl) >= 0) {
				return false;
			}
		}

		// 不需要校验的url且是GET请求，直接放行
		for (String getMethodUrl : getMethodUrlSet) {
			if (url.indexOf(getMethodUrl) >= 0 && "GET".equals(method)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 是否需要校验
	 * 
	 * @param url
	 * @param method
	 * @return
	 */
	private boolean isAccess(String url, String method) {
		// options嗅探方法全部放行
		if ("OPTIONS".equals(method)) {
			return false;
		}
		// 不需要登陆校验的所有请求Url
		for (String allMethodUrl : hasAccessUrlSet) {
			if (url.indexOf(allMethodUrl) >= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断token权限
	 * 
	 * @param token
	 * @param userId
	 */
	private boolean hashToken(String token, Long userId) {
		
		if (userId == null || userId == 0L || StringUtils.isBlank(token) || redisService == null
				|| !redisService.verifyLanding(token, userId)) {
			return false;
		}
		return true;
	}

	/**
	 * 写出返回信息
	 * 
	 * @param response
	 * @throws IOException
	 */
	private void writeNoLoginMsg(HttpServletResponse response, ErrorTypeEnum type) throws IOException {
		response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		Writer writer = null;
		ApiResult<Void> result = new ApiResult<>(type);
		try {
			writer = response.getWriter();
			writer.write(JSON.toJSONString(result));
			writer.flush();
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
	/**
	 * 无权限访问
	 * 
	 * @param response
	 * @throws IOException
	 */
	private void writeNoAuthMsg(HttpServletResponse response) throws IOException {
		response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		Writer writer = null;
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.NO_LOGIN_61);
		try {
			writer = response.getWriter();
			writer.write(JSON.toJSONString(result));
			writer.flush();
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	/**
	 * 设置初始化参数
	 */
	private void setInitParam(FilterConfig config) {
		// 设置不需要登陆校验的get请求Url集合
		String getMethodUrls = config.getInitParameter("getMethodUrls");
		if (StringUtils.isNotBlank(getMethodUrls)) {
			String[] getMethodUrlArr = getMethodUrls.split(",");
			for (String getMethodUrl : getMethodUrlArr) {
				getMethodUrlSet.add(getMethodUrl.trim());
			}
		}

		// 设置不需要登陆校验的所有请求Url集合
		String allMethodUrls = config.getInitParameter("allMethodUrls");
		if (StringUtils.isNotBlank(allMethodUrls)) {
			String[] allMethodUrlArr = allMethodUrls.split(",");
			for (String allMethodUrl : allMethodUrlArr) {
				allMethodUrlSet.add(allMethodUrl.trim());
			}
		}
		
		// 设置不需要权限校验的所有请求Url集合
		String hasAccessUrls = config.getInitParameter("hasAccessUrls");
		if (StringUtils.isNotBlank(hasAccessUrls)) {
			String[] hasAccessUrlArr = hasAccessUrls.split(",");
			for (String hasAccessUrl : hasAccessUrlArr) {
				hasAccessUrlSet.add(hasAccessUrl.trim());
			}
		}
	}

	public RedisService getRedisService() {
		return redisService;
	}

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
	
	/***
	 * 判断用户是否有权限访问
	 * 第一步：检查当前访问请求，是否包含在所有权限列表中
	 * 第二步：如果包含，检查当前用户是否有访问接口权限
	 * 未做权限控制的请求，默认返回true
	 * @param userMenuStr 
	 * @param allMenuStr 
	 * @return
	 */
	public boolean isAuthorized(HttpServletRequest request, List<Menu> allMenu, List<Menu> userMenu){
		String requestUrl = request.getRequestURI();
//		//循环遍历用户权限菜单，如果有菜单功能权限，返回
		for (Menu menu : allMenu) {
			if( StringUtils.isNotBlank(menu.getMenuUrl()) && verifyMenu(requestUrl, menu.getMenuUrl())){
				for (Menu menu2 : userMenu) {
					if(StringUtils.isNotBlank(menu2.getMenuUrl()) && verifyMenu(requestUrl, menu2.getMenuUrl())){
						return true;
					}
				}
				return false;
			}
		}
		return true;
	} 
	
	private boolean verifyMenu(String requestUrl,String menuUrls) {
		if(menuUrls == null || requestUrl == null){
			return false;
		}
		String[] split = menuUrls.split(",");
		for(String menuUrl : split){
			if(requestUrl.contains(menuUrl)){
				return true;
			}
		}
		return false;	
	}
	
}
