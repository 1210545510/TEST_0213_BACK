package com.aml.common.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alibaba.fastjson.JSON;
import com.aml.api.dto.Roles;
import com.aml.api.pojo.Users;
import com.aml.common.api.exception.APIInvokerException;
import com.aml.common.core.Constants;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;
import com.aml.common.core.exception.ServerException;
import com.aml.common.service.RedisService;
import com.aml.common.util.PropertiesUtil;
import com.aml.common.util.UMSUtils;

/**
 * Controller公共基类
 * 
 * @author zouwei
 * @since 2016年11月3
 */
public class BaseController extends BaseLogger {
	@Autowired(required = false)
	protected RedisService redisService;
	protected static final Long ALERTSUPERVISOR = Long.valueOf(PropertiesUtil.getValue("Alert.Supervisor"));
	protected static final Long SARSUPERVISOR = Long.valueOf(PropertiesUtil.getValue("Sar.Supervisor"));
	protected static final Long SAROFFICER = Long.valueOf(PropertiesUtil.getValue("Sar.Officer"));
	protected static final Long ADMIN = Long.valueOf(PropertiesUtil.getValue("AML.admin"));

	protected static final Long[] ADMINISTRATOR = { SARSUPERVISOR, SAROFFICER, ADMIN };

	/**
	 * 
	 * @title: getUserName
	 * @description: 从Redis中获取用户名称
	 *
	 * @param userId
	 * @return userName
	 * @date 2017年12月12日 下午5:50:23
	 */
	protected String getUserName ( Long userId ) {
		String userName = "";
		if (ObjectUtils.isEmpty(userId)) {
			return userName;
		}
		String userJson = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
		Users user = JSON.parseObject(userJson, Users.class);
		if (!ObjectUtils.isEmpty(user)) {
			userName = user.getUserName();
		}
		return userName;
	}

	/**
	 * 
	 * @title: getRoleId
	 * @description: 从Redis中获取用户角色ID
	 *
	 * @param userId
	 * @return roleId
	 * @date 2017年12月12日 下午5:50:23
	 */
	protected Long getRoleId ( Long userId ) {
		Long roleId = null;
		if (ObjectUtils.isEmpty(userId)) {
			return roleId;
		}
		String userJson = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
		Users user = JSON.parseObject(userJson, Users.class);
		if (!ObjectUtils.isEmpty(user)) {
			roleId = user.getRoles().get(0).getRoleId();
		}
		return roleId;
	}

	/**
	 * 
	 * @title: getTeamCode
	 * @description: 从Redis中获取用户小组编码
	 *
	 * @param userId
	 * @return teamCode
	 * @date 2017年12月12日 下午5:50:23
	 */
	protected String getTeamCode ( Long userId ) {
		String teamCode = "";
		if (ObjectUtils.isEmpty(userId)) {
			return teamCode;
		}
		String userJson = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
		Users user = JSON.parseObject(userJson, Users.class);
		if (!ObjectUtils.isEmpty(user)) {
			teamCode = user.getTeamCode();
		}
		return teamCode;
	}

	/**
	 * 
	 * @title: getSysType
	 * @description: 从Redis中获取系统来源
	 *
	 * @param token
	 * @return
	 * @date 2018年1月16日 下午4:07:07
	 */
	protected String getSysType ( String token, Long userId ) {
		return redisService.getValue(token + userId);
	}

	/**
	 * 判断当前用户是否为管理员
	 * 
	 * @param userId
	 * @return
	 * @date 2017年12月14日
	 */
	protected boolean isAdmin ( Long userId ) {
		if (ObjectUtils.isEmpty(userId)) {
			return false;
		}
		String userJson = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
		Users user = JSON.parseObject(userJson, Users.class);
		if (ObjectUtils.isEmpty(user)) {
			return false;
		}
		List<Roles> roles = user.getRoles();
		if (ObjectUtils.isEmpty(roles)) {
			return false;
		}
		for (Roles role : roles) {
			Long roleId = role.getRoleId();
			for (Long id : ADMINISTRATOR) {
				if (id.equals(roleId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否为Alert Supervisor
	 * 
	 * @param userId
	 * @return boolean
	 * @date 2017年12月14日
	 */
	protected boolean isAlertSupervisor ( Long userId ) {
		if (ObjectUtils.isEmpty(userId)) {
			return false;
		}
		String userJson = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
		Users user = JSON.parseObject(userJson, Users.class);
		if (ObjectUtils.isEmpty(user)) {
			return false;
		}
		List<Roles> roles = user.getRoles();
		if (ObjectUtils.isEmpty(roles)) {
			return false;
		}
		for (Roles role : roles) {
			Long roleId = role.getRoleId();
			if (ALERTSUPERVISOR.equals(roleId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 统一异常处理
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public Result exceptionHandler ( HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex ) {
		Result result = new Result();
		ex.printStackTrace();
		// 服务端异常
		if (ex instanceof ServerException) {
			result.setStatus(ErrorTypeEnum.SERVER_EXCEPTION.getErrorCode());

			ServerException e = (ServerException) ex;

			String info = e.getInfo();
			String errorInfo;

			if (StringUtils.isNotBlank(info)) {
				result.setErrorInfo(info);
				errorInfo = info;
			} else {
				result.setErrorInfo(ErrorTypeEnum.SERVER_EXCEPTION.getErrorMsg());
				errorInfo = e.getMessage();
			}
			logger.error("服务端发生错误，错误信息： {}, {}", errorInfo, ex);

		} else if (ex instanceof APIInvokerException) {
			// 第三方api异常
			APIInvokerException e = (APIInvokerException) ex;
			logger.error("调用第三方api发生错误，错误信息： {}, {}", e.getErrorMsg(), ex);
			result.setErrorType(ErrorTypeEnum.API_EXCEPTION);
			result.setStatus(e.getErrorCode());
			result.setErrorInfo(e.getErrorMsg());

		} else if (ex instanceof HttpMessageNotReadableException || ex instanceof MethodArgumentTypeMismatchException) {
			// 参数错误
			logger.error("参数错误，错误信息： {}, {}", ex.getMessage(), ex);
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);

		} else if (ex instanceof RedisConnectionFailureException) {
			// redis
			logger.error("redis错误，错误信息： {}, {}", ex.getMessage(), ex);
			result.setErrorType(ErrorTypeEnum.REDIS_EXCEPTION);

		} else if (ex instanceof IOException) {
			// IO exception
			logger.error("IO错误，错误信息： {}, {}", ex.getMessage(), ex);
			result.setErrorType(ErrorTypeEnum.IO_EXCEPTION);

		} else if (ex instanceof MyBatisSystemException) {
			// 数据库异�?
			logger.error("数据库链接错误，错误信息：{}, {}", ex.getMessage(), ex);
			result.setErrorType(ErrorTypeEnum.DB_ERROR);

		} else {
			// 其他异常
			logger.error(ex.getMessage(), ex);
			result.setErrorType(ErrorTypeEnum.SERVER_EXCEPTION);
			// result.setErrorInfo(ex.getMessage());
		}
		return result;
	}

	/**
	 * 判断token权限
	 * 
	 * @param result
	 * @param token
	 * @param userId
	 */
	protected boolean hashToken ( Result result, String token, Long userId ) {
		if (userId == null || userId == 0L || StringUtils.isBlank(token) || redisService == null
				|| !redisService.verifyLanding(token, userId)) {
			result.setErrorType(ErrorTypeEnum.NO_LOGIN);
			return false;
		}
		return true;
	}

	/**
	 * 获取请求所在地区语言类型
	 * 
	 * @param request
	 * @return
	 */
	protected String getLocale ( HttpServletRequest request ) {
		Locale locale = request.getLocale();
		if (locale == null) {
			return Locale.SIMPLIFIED_CHINESE.toString();
		}
		return locale.toString();
	}

	/**
	 * response写出
	 * 
	 * @param response
	 * @param str
	 */
	protected void write ( HttpServletResponse response, String str ) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (Exception e) {
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	/**
	 * 关闭资源
	 * 
	 * @param is
	 * @deprecated IOUtils.closeQuietly();
	 */
	@Deprecated
	protected void close ( InputStream is ) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
			}
			is = null;
		}
	}

	/**
	 * 设置编码为UTF-8
	 * 
	 * @param request
	 * @param response
	 */
	protected void setCharacterEncoding ( HttpServletRequest request, HttpServletResponse response ) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * 查询权限的用户名称
	 * 
	 * @param userId
	 * @param userName
	 * @param type
	 * @return
	 * @date 2017年12月27日
	 */
	public String getPermissionUsername ( Long userId, String userName, String type, String token) {
		if (StringUtils.isNotBlank(userName)) {
			return userName;
		}
		if (this.isAdmin(userId) || this.isAlertSupervisor(userId)) {
			List<com.aml.api.dto.Users> users = null;
			// Alert Supervisor 只能查看同小组下的所有用户待办列表
			if (this.isAlertSupervisor(userId)) {
				String teamCode = this.getTeamCode(userId);
				users = UMSUtils.queryRoleUsersByTeamCode(teamCode, null, token, userId);
			} else {
				users = UMSUtils.queryAllUsers(type, token, userId);
			}
			StringBuffer userNames = new StringBuffer();
			int size = users.size();
			for (int i = 0; i < size; i++) {
				com.aml.api.dto.Users user = users.get(i);
				userName = user.getUserName();
				userNames.append(userName);
				if (i != size - 1) {
					userNames.append(",");
				} else {
					return userNames.toString();
				}
			}
		}

		return this.getUserName(userId);
	}
}
