package com.aml.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aml.api.dto.AmlTeam;
import com.aml.api.dto.Roles;
import com.aml.api.dto.Users;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ConfigConsts;
import com.aml.common.filter.VerifyLoginFilter;

/**
 * 
 * @className: UMSUtils
 * @description: 调用UMS API接口
 * @author huangliangbao
 * @date 2017年11月8日 下午6:12:11
 *
 */
public class UMSUtils {
	public static final Logger logger = LoggerFactory.getLogger(VerifyLoginFilter.class);
	private static final String url = PropertiesUtil.getValue("ums.api.url");

	/**
	 * 
	 * @title: queryEnableUsers
	 * @description: 查询启用的用户列表
	 *
	 * @param roleId 角色ID
	 * @param roleName 角色名称
	 * @return List<Users>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static List<Users> queryEnableUsers ( Object param ,String token, Long userId) {
		StringBuffer path = new StringBuffer();
		path.append(url);
		path.append(PropertiesUtil.getValue("role.users"));
		path.append("?token=");
		path.append(token);
		path.append("&userId=");
		path.append(userId);
		path.append("&loginStatus=");
		path.append(ConfigConsts.LOGIN_STATUS_ENABLE);
		if (param instanceof Long) {
			path.append("&roleId=");
			path.append(param);
		} else if (param instanceof String) {
			path.append("&roleName=");
			path.append(param);
		}
		String response = HttpUtils.get(path.toString());
		@SuppressWarnings("unchecked")
		ApiResult<List<Users>> result = JSON.parseObject(response, ApiResult.class);

		List<Users> users = new ArrayList<Users>();
		if (null != result && result.isSuccess()) {
			users = JSON.parseArray(JSON.toJSONString(result.getData()), Users.class);
		}

		return users;
	}

	/**
	 * 
	 * @title: queryAllUsers
	 * @description: 查询所有用户列表
	 *
	 * @param roleName 角色名称
	 * @return List<Users>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static List<Users> queryAllUsers ( String roleName ,String token, Long userId) {
		StringBuffer path = new StringBuffer();
		path.append(url);
		path.append(PropertiesUtil.getValue("role.users"));
		path.append("?token=");
		path.append(token);
		path.append("&userId=");
		path.append(userId);
		path.append("&roleName=");
		path.append(roleName);
		String response = HttpUtils.get(path.toString());
		@SuppressWarnings("unchecked")
		ApiResult<List<Users>> result = JSON.parseObject(response, ApiResult.class);

		List<Users> users = new ArrayList<Users>();
		if (null != result && result.isSuccess()) {
			users = JSON.parseArray(JSON.toJSONString(result.getData()), Users.class);
		}

		return users;
	}

	/**
	 * 
	 * @title: queryUsersPermissions
	 * @description: 获取用户权限
	 *
	 * @param userId 用户ID
	 * @return Map<String, Object>
	 * @date 2017年12月2日 下午3:40:56
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> queryUsersPermissions ( Long userId ,String token) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("user.permissions"));
		JSONObject msg = new JSONObject();
		msg.put("apiKey", PropertiesUtil.getValue("apiKey"));
		msg.put("userId", userId);
		msg.put("token", token);
		msg.put("userId", userId);
		String response = HttpUtils.post(params.toString(), JSON.toJSONString(msg),
				MediaType.APPLICATION_JSON_UTF8_VALUE);
		ApiResult<Map<String, Object>> result = JSON.parseObject(response, ApiResult.class);
		Map<String, Object> permissions = new HashMap<String, Object>();
		if (result.isSuccess()) {
			permissions = JSON.parseObject(JSON.toJSONString(result.getData()), Map.class);
		}

		return permissions;
	}

	/**
	 * 
	 * @title: queryTeamUsers
	 * @description: 查询小组下用户列表
	 *
	 * @param teamCode 小组编码
	 * @return List<Users>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static List<Users> queryTeamUsers ( String teamCode ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("team.users"));
		params.append("?token=");
		params.append(token);
		params.append("&userId=");
		params.append(userId);
		params.append("&departmentCode=");
		params.append("'");
		params.append(PropertiesUtil.getValue("alert.departmentCode"));
		params.append("'");
		if (!ObjectUtils.isEmpty(teamCode)) {
			params.append("'");
			params.append("&teamCode=");
			params.append(teamCode);
			params.append("'");
		}
		String response = HttpUtils.get(params.toString());
		@SuppressWarnings("unchecked")
		ApiResult<List<Users>> result = JSON.parseObject(response, ApiResult.class);

		List<Users> users = new ArrayList<Users>();
		if (null != result && result.isSuccess()) {
			users = JSON.parseArray(JSON.toJSONString(result.getData()), Users.class);
		}

		return users;
	}

	/**
	 * 
	 * @title: queryRoleUsersByTeamCode
	 * @description: 查询小组中指定角色的用户列表
	 *
	 * @param teamCode 小组编码
	 * @param roleId 角色ID
	 * @return List<Users>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static List<Users> queryRoleUsersByTeamCode ( String teamCode, Long roleId ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("team.role.users"));
		params.append("?token=");
		params.append(token);
		params.append("&userId=");
		params.append(userId);
		params.append("&teamCode='");
		params.append(teamCode);
		params.append("'");
		if (!ObjectUtils.isEmpty(roleId)) {
			params.append("&roleId=");
			params.append(roleId);
		}
		String response = HttpUtils.get(params.toString());
		@SuppressWarnings("unchecked")
		ApiResult<List<Users>> result = JSON.parseObject(response, ApiResult.class);

		List<Users> users = new ArrayList<Users>();
		if (null != result && result.isSuccess()) {
			users = JSON.parseArray(JSON.toJSONString(result.getData()), Users.class);
		}

		return users;
	}

	/**
	 * 
	 * @title: queryRoles
	 * @description: 查询角色信息
	 *
	 * @return List<Roles>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static Roles queryRole ( Long roleId ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("aml.role"));
		params.append("?token=");
		params.append(token);
		params.append("&userId=");
		params.append(userId);
		params.append("&roleId=");
		params.append(roleId);
		String response = HttpUtils.get(params.toString());
		@SuppressWarnings("unchecked")
		ApiResult<Roles> result = JSON.parseObject(response, ApiResult.class);

		Roles roles = new Roles();
		if (null != result && result.isSuccess()) {
			roles = JSON.parseObject(JSON.toJSONString(result.getData()), Roles.class);
		}

		return roles;
	}

	/**
	 * 
	 * @title: queryUser
	 * @description: 查询用户信息
	 *
	 * @param userName
	 * @return Users
	 * @date 2017年12月21日 下午2:24:18
	 */
	public static Users queryUser ( String userName ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("aml.user"));
		params.append("?token=");
		params.append(token);
		params.append("&userId=");
		params.append(userId);
		params.append("&userName=");
		params.append(userName);
		String response = HttpUtils.get(params.toString());
		@SuppressWarnings("unchecked")
		ApiResult<Users> result = JSON.parseObject(response, ApiResult.class);

		Users user = new Users();
		if (null != result && result.isSuccess()) {
			user = JSON.parseObject(JSON.toJSONString(result.getData()), Users.class);
		}

		return user;
	}

	/**
	 * 
	 * @title: queryRoles
	 * @description: 查询角色列表
	 *
	 * @return List<Roles>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static List<Roles> queryRoles ( String roleName ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("aml.roles"));
		params.append("?token=");
		params.append(token);
		params.append("&userId=");
		params.append(userId);
		if (StringUtils.isNotBlank(roleName)) {
			params.append("&roleName=");
			params.append(roleName);
		}
		String response = HttpUtils.get(params.toString());
		@SuppressWarnings("unchecked")
		ApiResult<List<Roles>> result = JSON.parseObject(response, ApiResult.class);

		List<Roles> roles = new ArrayList<Roles>();
		if (null != result && result.isSuccess()) {
			roles = JSON.parseArray(JSON.toJSONString(result.getData()), Roles.class);
		}

		return roles;
	}

	/**
	 * 
	 * @title: queryAlertTeams
	 * @description: 查询Alert小组列表
	 *
	 * @param teamCode 小组编码
	 * @return List<AmlTeam>
	 * @date 2017年12月2日 下午3:39:49
	 */
	public static List<AmlTeam> queryAlertTeams (String teamCode ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("aml.alert.team"));
		params.append("?token=");
		params.append(token);
		params.append("&userId=");
		params.append(userId);
		params.append("&departmentCode=");
		params.append(PropertiesUtil.getValue("alert.departmentCode"));
		if (!ObjectUtils.isEmpty(teamCode)) {
			params.append("&teamCode=");
			params.append(teamCode);
		}
		String response = HttpUtils.get(params.toString());
		@SuppressWarnings("unchecked")
		ApiResult<List<AmlTeam>> result = JSON.parseObject(response, ApiResult.class);

		List<AmlTeam> teams = new ArrayList<AmlTeam>();
		if (null != result && result.isSuccess()) {
			teams = JSON.parseArray(JSON.toJSONString(result.getData()), AmlTeam.class);
		}

		return teams;
	}

	/**
	 * 保存日志
	 * 
	 * @param msg2
	 * @return
	 * @date 2017年12月12日
	 */
	@SuppressWarnings("unchecked")
	public static void logInterface ( JSONObject msg ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("aml.log"));
		msg.put("token", token);
		msg.put("userId", userId);
		logger.info("保存日志请求参数>>>>>{}", JSON.toJSONString(JSON.toJSONString(msg)));
		String response = HttpUtils.post(params.toString(), JSON.toJSONString(msg),
				MediaType.APPLICATION_JSON_UTF8_VALUE);
		ApiResult<Void> result = JSON.parseObject(response, ApiResult.class);
		logger.info("保存日志返回结果>>>>>{}", JSON.toJSONString(result));
	}

	/**
	 * 查询用户资料(电话邮箱部门)
	 * 
	 * @param analystNames
	 * @param qaNames
	 * @return
	 * @date 2017年12月14日
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> queryUsersDate ( List<String> analystNames, List<String> qaNames ,String token, Long userId) {
		StringBuffer params = new StringBuffer();
		params.append(url);
		params.append(PropertiesUtil.getValue("user.date"));
		JSONObject msg = new JSONObject();
		msg.put("analystNames", analystNames);
		msg.put("qaNames", qaNames);
		msg.put("token", token);
		msg.put("userId", userId);
		String response = HttpUtils.post(params.toString(), JSON.toJSONString(msg),
				MediaType.APPLICATION_JSON_UTF8_VALUE);
		ApiResult<Map<String, Object>> result = JSON.parseObject(response, ApiResult.class);

		Map<String, Object> map = new HashMap<>();
		if (null != result && result.isSuccess()) {
			map = JSON.parseObject(JSON.toJSONString(result.getData()), Map.class);
		}
		return map;
	}
}
