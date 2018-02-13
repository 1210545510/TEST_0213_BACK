package com.aml.api.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.aml.api.dto.AmlTeam;
import com.aml.api.dto.AssignRuleDTO;
import com.aml.api.dto.DicTbDTO;
import com.aml.api.dto.NoticeRuleDTO;
import com.aml.api.dto.Roles;
import com.aml.api.dto.Users;
import com.aml.api.dto.WfBusStatusDTO;
import com.aml.api.pojo.AssignRule;
import com.aml.api.pojo.DicSub;
import com.aml.api.pojo.DicTb;
import com.aml.api.pojo.NoticeRule;
import com.aml.api.pojo.WfBusStatus;
import com.aml.api.service.ConfigService;
import com.aml.api.service.DictService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ConfigConsts;
import com.aml.common.core.Constants;
import com.aml.common.core.DicConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.FlowConsts;
import com.aml.common.core.Result;
import com.aml.common.core.exception.ServerException;
import com.aml.common.util.PageUtils;
import com.aml.common.util.PropertiesUtil;
import com.aml.common.util.UMSUtils;
import com.github.pagehelper.Page;

/**
 * 
 * @className: ConfigController
 * @description: 系统设置
 * @author huangliangbao
 * @date 2017年11月15日 下午1:17:21
 *
 */
@RestController
public class ConfigController extends BaseController {
	@Autowired
	private DictService dictService;
	@Autowired
	private ConfigService configService;

	/**
	 * 
	 * @title: queryFields
	 * @description: 查询列表中所有列
	 *
	 * @param request
	 * @return ApiResult
	 * @date 2017年11月15日 下午1:20:43
	 */
	@RequestMapping(value = "/{dicId}/fields", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryFields ( HttpServletRequest request,
			@PathVariable("dicId") Long dicId ) {
		logger.info("request url={}, dicId={}", request.getRequestURL(), dicId);
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<DicSub> existFields = dictService.queryDicSubsByDicId(dicId);
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(dicId);
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		fieldsMap.put("existFields", existFields);
		fieldsMap.put("pendingFields", pendingFields);
		result.setData(fieldsMap);
		return result;
	}

	/**
	 * 
	 * @title: addFields
	 * @description: 针对具体列表，新增显示列
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/{dicId}/field", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> addFields ( @PathVariable("dicId") Long dicId, HttpServletRequest request,
			@RequestBody Map<String, Object> params ) throws ServerException {
		logger.debug("request url：{}, params：{}", request.getRequestURL(), JSON.toJSONString(params));
		List<DicTbDTO> dicTbs = JSON.parseArray(JSON.toJSONString(params.get("dicTbs")), DicTbDTO.class);
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result serviceResult = configService.addFields(dicTbs, dicId);
		result.setResult(serviceResult);
		return result;
	}

	/**
	 * 
	 * @title: querySurrogateUsers
	 * @description: (用于委托授权)查询部门或角色下所有用户
	 *
	 * @param roleName 模糊匹配(alert或sar)
	 * @param request
	 * @return ApiResult<List<Users>>
	 * @throws ServerException
	 * @date 2017年12月11日 下午6:23:36
	 */
	@RequestMapping(value = "/surrogate/role/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Users>> querySurrogateUsers ( @RequestParam(required = false) String roleName, String token,
			Long userId, HttpServletRequest request ) throws ServerException {
		logger.debug("request url：{},  roleName：{}", new Object[] { request.getRequestURL(), roleName });
		ApiResult<List<Users>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (StringUtils.isBlank(roleName)) {
			result.setErrorInfo("The Role Name is empty");
			return result;
		}
		List<Users> users = new ArrayList<Users>();
		if (FlowConsts.TYPE_ALERT.equals(roleName)) {
			users = UMSUtils.queryTeamUsers(null, token, userId);
		} else if (FlowConsts.TYPE_SAR.equals(roleName)) {
			users = UMSUtils.queryEnableUsers(roleName, token, userId);
		}
		result.setData(users);
		return result;
	}

	/**
	 * 
	 * @title: queryUsers
	 * @description: 根据角色ID， 查询角色下所有用户
	 *
	 * @param roleId 角色ID
	 * @param request
	 * @return ApiResult<List<Users>>
	 * @throws ServerException
	 * @date 2017年12月11日 下午6:23:36
	 */
	@RequestMapping(value = "/role/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Users>> queryUsers ( @RequestParam(required = false) Long roleId, HttpServletRequest request,
			String token, Long userId ) throws ServerException {
		logger.debug("request url：{}, roleId：{}", new Object[] { request.getRequestURL(), roleId });
		ApiResult<List<Users>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(roleId)) {
			result.setErrorInfo("The Role Id is empty");
			return result;
		}
		List<Users> users = UMSUtils.queryEnableUsers(roleId, token, userId);
		result.setData(users);
		return result;
	}

	/**
	 * 
	 * @title: queryTeamUsers
	 * @description: 查询小组中指定角色的用户列表
	 *
	 * @param teamCode 小组编码
	 * @param roleId 角色ID
	 * @param request
	 * @return ApiResult<List<Users>>
	 * @throws ServerException
	 * @date 2017年12月11日 下午6:23:36
	 */
	@RequestMapping(value = "/team/role/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Users>> queryTeamUsers ( @RequestParam String teamCode, @RequestParam Long roleId,
			String token, Long userId, HttpServletRequest request ) throws ServerException {
		logger.debug("request url：{}, teamCode：{}, roleId：{}",
				new Object[] { request.getRequestURL(), teamCode, roleId });
		ApiResult<List<Users>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(teamCode)) {
			result.setErrorInfo("The Team Code is empty");
			return result;
		}
		if (ObjectUtils.isEmpty(roleId)) {
			result.setErrorInfo("The Role ID is empty");
			return result;
		}

		List<Users> users = UMSUtils.queryRoleUsersByTeamCode(teamCode, roleId, token, userId);
		result.setData(users);
		return result;
	}

	/**
	 * 
	 * @title: queryRoles
	 * @description: 根据角色名称，查询角色及用户集合
	 *
	 * @param request
	 * @param type 模块类型(alert 表示Alert Management; sar 表示Case Management)
	 * @param roleId 角色ID
	 * @return ApiResult<List<Users>>
	 * @throws ServerException
	 * @date 2017年12月1日 下午5:31:49
	 */
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Roles>> queryRoles ( @RequestParam(required = false) String type, String token, Long userId,
			@RequestParam(required = false) Long roleId, @RequestParam(required = false) String roleName,
			HttpServletRequest request ) throws ServerException {
		logger.debug("request url：{}", request.getRequestURL());
		ApiResult<List<Roles>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(roleName)) {
			// 系统管理员
			if (!ObjectUtils.isEmpty(type)) {
				if (FlowConsts.TYPE_ALERT.equals(type)) {
					roleName = FlowConsts.TYPE_ALERT;
				} else if (FlowConsts.TYPE_SAR.equals(type)) {
					roleName = FlowConsts.TYPE_SAR;
				}
			} else {// Alert Supervisor或Sar Supervisor
				if (roleId.equals(ALERTSUPERVISOR)) {
					roleName = FlowConsts.TYPE_ALERT;
				} else if (roleId.equals(SAROFFICER)) {
					roleName = FlowConsts.TYPE_SAR;
				}
			}
		}

		List<Roles> roles = UMSUtils.queryRoles(roleName, token, userId);
		result.setData(roles);
		return result;
	}

	/**
	 * 
	 * @title: queryAlertTeams
	 * @description: 查询小组中角色及用户集合
	 *
	 * @param request
	 * @param userId
	 * @return ApiResult<List<Team>>
	 * @throws ServerException
	 * @date 2017年12月9日 下午3:32:23
	 */
	@RequestMapping(value = "/alert/teams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<AmlTeam>> queryAlertTeams ( Long userId, HttpServletRequest request, String token )
			throws ServerException {
		logger.debug("request url：{}", request.getRequestURL());
		ApiResult<List<AmlTeam>> result = new ApiResult<>(ErrorTypeEnum.OK);
		String teamCode = null;
		if (super.isAlertSupervisor(userId)) {
			teamCode = super.getTeamCode(userId);
		}
		List<AmlTeam> teams = UMSUtils.queryAlertTeams(teamCode, token, userId);
		System.out.println(JSON.toJSONString(teams));
		result.setData(teams);
		return result;
	}

	/**
	 * 
	 * @title: queryAssignRuleByRoleId
	 * @description: （只有管理员才有权限操作）根据角色ID，查询QA/Analyst分配规则
	 *
	 * @param roleId
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/{roleId}/assignRule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<AssignRule> queryAssignRuleByRoleId ( HttpServletRequest request,
			@PathVariable("roleId") Long roleId ) throws ServerException {
		logger.debug("request url：{}, roleId：{}", request.getRequestURL(), roleId);
		ApiResult<AssignRule> result = new ApiResult<>(ErrorTypeEnum.OK);
		AssignRule data = configService.queryAssignRuleByRoleId(roleId);
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: assignRule
	 * @description: 针对角色ID，新增/修改分配规则
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/{roleId}/assignRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> assignRule ( HttpServletRequest request, @RequestBody AssignRuleDTO dto )
			throws ServerException {
		logger.debug("request url：{}, dto：{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer ruleType = dto.getRuleType();
		if (ConfigConsts.RULE_TYPE_AVG != ruleType) {
			if (CollectionUtils.isEmpty(dto.getRuleItems())) {
				result.setErrorInfo("The rule item is empty");
				return result;
			}
		}
		// 1、新增分配规则
		if (null == dto.getId()) {
			result.setResult(configService.addAssignRule(dto));
		} else {
			// 2、修改分配规则
			result.setResult(configService.updateAssignRule(dto));
		}

		return result;
	}
	

	/**
	 * 
	 * @title: querySamplingRule
	 * @description: 查询Alert Sampling settings
	 *
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/query/samplingRule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<AssignRule> querySamplingRule ( HttpServletRequest request) throws ServerException {
		logger.debug("request url：{}", request.getRequestURL());
		ApiResult<AssignRule> result = new ApiResult<>(ErrorTypeEnum.OK);
		AssignRule data = configService.querySamplingRule();
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: samplingRule
	 * @description: 新增/修改Alert Sampling settings
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/save/samplingRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> samplingRule ( HttpServletRequest request, @RequestBody AssignRuleDTO dto )
			throws ServerException {
		logger.debug("request url：{}, dto：{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer ruleType = dto.getRuleType();
		if (ConfigConsts.RULE_TYPE_AVG != ruleType) {
			if (CollectionUtils.isEmpty(dto.getRuleItems())) {
				result.setErrorInfo("The rule item is empty");
				return result;
			}
		}
		// 1、新增分配规则
		if (null == dto.getId()) {
			result.setResult(configService.addAssignRule(dto));
		} else {
			// 2、修改分配规则
			result.setResult(configService.updateAssignRule(dto));
		}

		return result;
	}

	/**
	 * 
	 * @title: queryNoticeRule
	 * @description: （只有管理员才有权限操作）查询Days Settings
	 *
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/query/noticeRule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<NoticeRule>> queryNoticeRule ( HttpServletRequest request )
			throws ServerException {
		logger.debug("request url：{}", request.getRequestURL());
		ApiResult<List<NoticeRule>> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<NoticeRule> data = configService.queryNoticeRule();
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: noticeRule
	 * @description: 新增/修改Days Settings
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/save/noticeRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> noticeRule ( HttpServletRequest request, @RequestBody NoticeRuleDTO dto )
			throws ServerException {
		logger.debug("request url：{}, dto：{}", request.getRequestURL(), dto.toJson());
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getDays())) {
			result.setErrorInfo("Parameters can not be empty");
			return result;
		}
		if (ObjectUtils.isEmpty(dto.getNum())) {
			result.setErrorInfo("Parameters can not be empty");
			return result;
		}
		if (ObjectUtils.isEmpty(dto.getIntervalDays())) {
			result.setErrorInfo("Parameters can not be empty");
			return result;
		}
		result.setResult(configService.saveNoticeRule(dto));
		result.setData(dto.getId());
		return result;
	}

	/**
	 * 配置流程状态
	 * 
	 * @param request
	 * @param dto
	 * @return
	 * @throws ServerException
	 * @date 2017年11月28日
	 */
	@RequestMapping(value = "/{didSid}/configuration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> configurationStatus ( @PathVariable("didSid") String didSid,
			@RequestBody Map<String, Object> params, HttpServletRequest request ) throws ServerException {
		logger.debug("request url：{}, dto：{} dicKey:{}", request.getRequestURL(), JSON.toJSON(params), didSid);
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Object object = params.get("wfBusStatuss");
		if (ObjectUtils.isEmpty(object)) {
			result.setErrorInfo("Parameter error");
			return result;
		}
		List<WfBusStatusDTO> parseArray = JSON.parseArray(JSON.toJSONString(object), WfBusStatusDTO.class);
		if (ObjectUtils.isEmpty(params)) {
			result.setErrorInfo("Data is empty");
			return result;
		}
		for (WfBusStatusDTO dto : parseArray) {
			dto.setFlowId(this.getFlowId(dto.getDicId()));
			Integer statusType = dto.getStatusType();
			String paramValue = dto.getParamValue();
			int i = 0;
			for (WfBusStatusDTO dto1 : parseArray) {
				Integer statusType1 = dto1.getStatusType();
				String paramValue1 = dto1.getParamValue();
				if (statusType1.equals(statusType) && paramValue1.equals(paramValue)) {
					i++;
					if (i > 2) {
						result.setErrorInfo("There is duplicate data");
						return result;
					}
				}
			}
		}
		configService.saveConfigurationStatus(parseArray, didSid);
		return result;
	}

	/**
	 * 查询配置流程状态列表
	 * 
	 * @param request
	 * @param dto
	 * @return
	 * @throws ServerException
	 * @date 2017年11月28日
	 */
	@RequestMapping(value = "/configuration/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<DicSub> configurationStatus ( WfBusStatusDTO dto, HttpServletRequest request )
			throws ServerException {
		logger.debug("request url：{}, dto：{} ", request.getRequestURL(), JSON.toJSON(dto));
		ApiPageResult<DicSub> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		dto.setFlowId(this.getFlowId(dto.getDicId()));
		Page<DicSub> data = dictService.pageProcessState(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 查询流程状态的配置
	 * 
	 * @param request
	 * @param dto
	 * @return
	 * @throws ServerException
	 * @date 2017年11月28日
	 */
	@RequestMapping(value = "/{didSid}/configuration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<WfBusStatus>> getconfigurationStatus ( @PathVariable("didSid") String didSid,
			@RequestParam Long dicId, HttpServletRequest request ) throws ServerException {
		logger.debug("request url：{}, didSid：{} ", request.getRequestURL(), didSid);
		ApiResult<List<WfBusStatus>> result = new ApiResult<>(ErrorTypeEnum.OK);
		String flowId = this.getFlowId(dicId);
		List<WfBusStatus> data = dictService.getconfigurationStatus(flowId, didSid);
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: queryUsersPermissions
	 * @description: 根据用户ID， 获取用户菜单权限
	 *
	 * @param request
	 * @param userId 用户ID
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年12月2日 下午3:11:42
	 */
	@RequestMapping(value = "/{userId}/permissions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryUsersPermissions ( HttpServletRequest request,
			@PathVariable("userId") Long userId ) throws ServerException {
		logger.debug("request url：{}, userId：{} ", request.getRequestURL(), userId);
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> permissions = new HashMap<String, Object>();
		String allMenuStr = redisService.getValue(Constants.REDIS_KEY_AML_ALL_MENU);
		String userMenuStr = redisService.getValue(Constants.REDIS_KEY_AML_USER_MENU + userId);
		String userInfoStr = redisService.getValue(Constants.REDIS_KEY_AML_USER_INFO + userId);
		permissions.put("user", JSON.parseObject(userInfoStr));
		permissions.put("listMenu", JSON.parseArray(userMenuStr));
		permissions.put("allMenu", JSON.parseArray(allMenuStr));
		result.setData(permissions);
		return result;
	}

	/**
	 * 
	 * @title: queryAssignedTo
	 * @description: 获取当前处理人列表
	 *
	 * @param userId
	 * @param token
	 * @param type 模块类型(type=alert或sar或ctr)
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年12月2日 下午3:11:42
	 */
	@RequestMapping(value = "/get/actor/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<String>> queryAssignedTo ( HttpServletRequest request, @RequestParam Long userId,
			@RequestParam String token, @RequestParam String type ) throws ServerException {
		logger.debug("request url：{}, userId: {}, type：{} ", new Object[] { request.getRequestURL(), userId, type });
		ApiResult<List<String>> result = new ApiResult<>(ErrorTypeEnum.OK);
		String userNames = super.getPermissionUsername(userId, null, type, token);
		List<String> userNameList = Arrays.asList(userNames.split(","));
		result.setData(userNameList);
		return result;
	}

	private String getFlowId ( Long dicId ) {
		String flowId = "";
		if (DicConsts.D_ALERT_STATUS == dicId) {
			flowId = PropertiesUtil.getValue("processId.alert");
		} else if (DicConsts.D_SAR_STATUS == dicId) {
			flowId = PropertiesUtil.getValue("processId.sar");
		}
		return flowId;
	}

}
