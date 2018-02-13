package com.aml.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.aml.api.dto.Roles;
import com.aml.api.dto.TaskApprovalDTO;
import com.aml.api.dto.TaskScoreDTO;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.pojo.TaskApproval;
import com.aml.api.pojo.TaskScore;
import com.aml.api.service.AlertService;
import com.aml.api.service.CaseService;
import com.aml.api.service.FlowService;
import com.aml.api.service.SnakerEngineFacets;
import com.aml.api.service.TaskApprovalService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.DicConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.FlowConsts;
import com.aml.common.core.Result;
import com.aml.common.util.PropertiesUtil;
import com.aml.common.util.StrUtils;
import com.aml.common.util.UMSUtils;

/**
 * 
 * @className: FlowController
 * @description: 流程处理
 * @author huangliangbao
 * @date 2017年11月29日 下午3:27:04
 *
 */
@RestController
@RequestMapping(value = "/snaker/flow")
public class FlowController extends BaseController {
	@Autowired
	private FlowService flowService;
	@Autowired
	private SnakerEngineFacets facets;
	@Autowired
	private TaskApprovalService taskApprovalService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private CaseService caseService;

	/**
	 * 
	 * @title: order
	 * @description: 查询流程实例
	 *
	 * @param request
	 * @param page
	 * @return String
	 * @date 2017年12月1日 下午3:41:06
	 */
	@RequestMapping(value = "order", method = RequestMethod.GET)
	public String order ( HttpServletRequest request, Page<HistoryOrder> page ) {
		logger.info("request url={}", request.getRequestURL());
		facets.getEngine().query().getHistoryOrders(page, new QueryFilter());
		return JSON.toJSONString(page);
	}

	/**
	 * 
	 * @title: assign
	 * @description: 单个或批量指派任务给其他人，并更新Alert/Case记录中的任务ID
	 *
	 * @param request
	 * @param params 参数<br>
	 *        alertId=>记录主键（可选）<br>
	 *        caseId=>记录主键（可选）<br>
	 *        taskId=>任务ID（必传）<br>
	 *        operator=>指派的任务处理人（必传）<br>
	 * @return ApiResult
	 * @date 2017年12月2日 下午1:54:11
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "assign", method = RequestMethod.PUT)
	public ApiResult<Void> assign ( HttpServletRequest request, @RequestBody HashMap<String, Object> params ) {
		logger.info("request url={}, params={}", request.getRequestURL(), JSON.toJSONString(params));
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Object userId = params.get("userId");
		Object operator = params.get("operator");
		Object list = params.get("list");

		if (ObjectUtils.isEmpty(userId)) {
			result.setErrorInfo("User Id cannot be empty");
			return result;
		}
		if (ObjectUtils.isEmpty(operator)) {
			result.setErrorInfo("operator cannot be empty");
			return result;
		}
		if (ObjectUtils.isEmpty(list)) {
			result.setErrorInfo("List cannot be empty");
			return result;
		}

		List<Map<String, Object>> mapList = (List<Map<String, Object>>) list;
		Result serviceResult = flowService.assign(operator.toString(), mapList);
		result.setResult(serviceResult);
		return result;
	}

	/**
	 * 
	 * @title: queryHistoryApprove
	 * @description: 查询历史审批记录列表
	 *
	 * @param orderId 实例ID
	 * @return ApiResult<List<Map<String, Object>>>
	 * @date 2017年12月11日 下午2:15:41
	 */
	@RequestMapping(value = "history/list", method = RequestMethod.GET)
	public ApiResult<List<Map<String, Object>>> queryHistoryApprove ( @RequestParam String orderId , String token, Long userId) {
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (StringUtils.isBlank(orderId)) {
			result.setErrorInfo("The Order Id cannot be empty");
			return result;
		}
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		HistoryOrder historyOrder = taskApprovalService.findHistoryOrder(orderId);
		String alertProcessId = PropertiesUtil.getValue("processId.alert");
		String sarProcessId = PropertiesUtil.getValue("processId.sar");
		Long dicId = null;
		if (alertProcessId.equals(historyOrder.getProcessId())) {
			dicId = DicConsts.D_ALERT_STATUS;
		} else if (sarProcessId.equals(historyOrder.getProcessId())) {
			dicId = DicConsts.D_SAR_STATUS;
			data.add(taskApprovalService.queryLastAlertApprove(orderId, dicId));
		}
		List<Map<String, Object>> historyTaskList = taskApprovalService.queryHistoryApprove(orderId, dicId);
		data.addAll(historyTaskList);
		for (Map<String, Object> map : data) {
			Roles role = UMSUtils.queryRole(Long.valueOf((String) map.get("roleId")), token, userId);
			map.put("roleName", role.getRoleName());
			map.put("isActive", false);
		}
		
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: approveSave
	 * @description: 审批流程时，点击【保存】
	 *
	 * @param dto 参数<br>
	 *        userName=>登录用户名（必传）<br>
	 *        roleId=>角色ID（必传）<br>
	 * 
	 *        alertId=>记录主键（可选）<br>
	 *        caseId=>记录主键（可选）<br>
	 *        approvalId=>主键（可选）<br>
	 *        result=>Processing result（必传）<br>
	 *        content=>审批意见（可选）<br>
	 *        score=>评分（可选）<br>
	 * @return ApiResult
	 * @date 2017年11月30日 下午3:06:26
	 */
	@RequestMapping(value = "approveSave/{flowType}", method = RequestMethod.POST)
	public ApiResult<Long> approveSave ( HttpServletRequest request, @PathVariable("flowType") String flowType,
			@RequestBody TaskApprovalDTO dto ) {
		logger.info("request url：{},  flowType：{}，dto：{}",
				new Object[] { request.getRequestURL(), flowType, dto.toJson() });
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getAlertId()) && ObjectUtils.isEmpty(dto.getCaseId())) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		String userName = super.getUserName(dto.getUserId());
		if (StringUtils.isBlank(userName)) {
			result.setErrorInfo("The UserName cannot be empty");
			return result;
		}
		if(StrUtils.strLength(dto.getContent())> 5000){
			result.setErrorInfo("The Content length is more than 1000 characters");
			return result;
		}
		switch (flowType) {
		case FlowConsts.TYPE_ALERT:
			// 查询Alert信息
			AlertGroup alertInfo = alertService.findAlertGroup(dto.getAlertId());
			// 设置参数
			dto.setOrderId(alertInfo.getOrderId());
			dto.setTaskId(alertInfo.getTaskId());
			dto.setStatus(alertInfo.getStatus());
			dto.setUserName(userName);
			break;
		case FlowConsts.TYPE_SAR:
			// 查询Case信息
			CaseGroup caseGroup = caseService.findCaseGroup(dto.getCaseId());
			// 设置参数
			dto.setOrderId(caseGroup.getOrderId());
			dto.setTaskId(caseGroup.getTaskId());
			dto.setStatus(caseGroup.getStatus());
			dto.setUserName(userName);
			break;
		default:
			break;
		}

		Result serivceResult = flowService.approveSave(dto);
		result.setResult(serivceResult);
		result.setData(dto.getApprovalId());
		return result;
	}

	/**
	 * 
	 * @title: saveScore
	 * @description: 审批审批时，QA对Analyst进行评分，点击【保存】
	 *
	 * @param dto 参数<br>
	 * 
	 *        alertId=>记录主键（可选）<br>
	 *        caseId=>记录主键（可选）<br>
	 *        id=>主键（可选）<br>
	 *        qualityIssues=>（可选）<br>
	 *        organizedWritten=>（可选）<br>
	 *        accuratelyAppropriately=>（可选）<br>
	 *        grammarSpelling=>（可选）<br>
	 *        otherIssues=>（可选）<br>
	 *        grade=>评分（可选）<br>
	 * @return ApiResult
	 * @date 2017年11月30日 下午3:06:26
	 */
	@RequestMapping(value = "saveScore/{flowType}", method = RequestMethod.POST)
	public ApiResult<Void> saveScore ( HttpServletRequest request, @PathVariable("flowType") String flowType,
			@RequestBody TaskScoreDTO dto ) {
		logger.info("request url：{},  flowType：{}，dto：{}",
				new Object[] { request.getRequestURL(), flowType, dto.toJson() });
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getAlertId()) && ObjectUtils.isEmpty(dto.getCaseId())) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		String userName = super.getUserName(dto.getUserId());
		if (StringUtils.isBlank(userName)) {
			result.setErrorInfo("The UserName cannot be empty");
			return result;
		}
		switch (flowType) {
		case FlowConsts.TYPE_ALERT:
			// 查询Alert信息
			AlertGroup alertInfo = alertService.findAlertGroup(dto.getAlertId());
			// 设置参数
			dto.setOrderId(alertInfo.getOrderId());
			dto.setTaskId(alertInfo.getTaskId());
			dto.setUserName(userName);
			break;
		case FlowConsts.TYPE_SAR:
			// 查询Case信息
			CaseGroup caseGroup = caseService.findCaseGroup(dto.getCaseId());
			// 设置参数
			dto.setOrderId(caseGroup.getOrderId());
			dto.setTaskId(caseGroup.getTaskId());
			dto.setUserName(userName);
			break;
		default:
			break;
		}

		Result serivceResult = flowService.saveScore(dto);
		result.setResult(serivceResult);

		return result;
	}

	/**
	 * 
	 * @title: findScoreByTaskId
	 * @description: 根据任务ID，查询评分记录
	 *
	 * @param request
	 * @param taskId 任务ID
	 * @return ApiResult<TaskApproval>
	 * @date 2017年11月30日 下午4:52:19
	 */
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ApiResult<TaskScore> findScoreByTaskId ( HttpServletRequest request, @RequestParam String taskId ) {
		logger.info("request url：{},  taskId：{}", request.getRequestURL(), taskId);
		ApiResult<TaskScore> result = new ApiResult<>(ErrorTypeEnum.OK);

		TaskScore data = flowService.findScoreByTaskId(taskId);
		result.setData(data);

		return result;
	}

	/**
	 * 
	 * @title: updateScore
	 * @description: 修改评分记录
	 *
	 * @param request
	 * @param id
	 * @return ApiResult
	 * @date 2017年11月15日 下午6:31:28
	 */
	@RequestMapping(value = "/score/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> updateScore ( HttpServletRequest request, @PathVariable("id") Integer id,
			@RequestBody TaskScoreDTO dto ) {
		logger.info("request url：{},  dto：{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result serviceResult = flowService.updateScore(dto);
		result.setResult(serviceResult);
		return result;
	}

	/**
	 * 
	 * @title: findByTaskId
	 * @description: 根据任务ID，查询审批记录（包括附件）
	 *
	 * @param request
	 * @param taskId 任务ID
	 * @return ApiResult<TaskApproval>
	 * @date 2017年11月30日 下午4:52:19
	 */
	@RequestMapping(value = "/approval", method = RequestMethod.GET)
	public ApiResult<TaskApproval> findByTaskId ( HttpServletRequest request, @RequestParam String taskId ) {
		logger.info("request url：{},  taskId：{}", request.getRequestURL(), taskId);
		ApiResult<TaskApproval> result = new ApiResult<>(ErrorTypeEnum.OK);

		TaskApproval data = taskApprovalService.findByTaskId(taskId);
		result.setData(data);

		return result;
	}

	/**
	 * 
	 * @title: approveAlert
	 * @description: 审批流程时，点击【提交】/【同意】/【不同意】,触发工作流 TODO
	 *
	 * @param roleId 角色ID
	 * @param dto 参数<br>
	 *        alertId=>记录主键（必传）<br>
	 *        approvalId=>审批主键（可选）<br>
	 *        result=>Processing result（必传）如： Escalated/Waived<br>
	 *        content=>审批意见（可选）<br>
	 *        score=>评分（可选）<br>
	 * @return ApiResult
	 * @date 2017年11月30日 下午3:06:26
	 */
	@RequestMapping(value = "approveAlert/{roleId}", method = RequestMethod.POST)
	public ApiResult<Void> approveAlert ( HttpServletRequest request, @PathVariable("roleId") String roleId,
			@RequestBody TaskApprovalDTO dto ) {
		logger.info("request url：{},  roleId：{}，dto：{}", roleId, request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getAlertId())) {
			result.setErrorInfo("The Alert ID cannot be empty");
			return result;
		}
		if (ObjectUtils.isEmpty(dto.getUserId())) {
			result.setErrorInfo("The User Id cannot be empty");
			return result;
		}
		if(StrUtils.strLength(dto.getContent())> 5000){
			result.setErrorInfo("The Content length is more than 1000 characters");
			return result;
		}
		// 查询Alert信息
		AlertGroup alertInfo = alertService.findAlertGroup(dto.getAlertId());
		// 设置参数
		dto.setRoleId(roleId);
		dto.setOwnerOrg(alertInfo.getOwnerOrg());
		dto.setOrderId(alertInfo.getOrderId());
		dto.setTaskId(alertInfo.getTaskId());
		dto.setRoleNum(alertInfo.getRoleNum());
		dto.setStatus(alertInfo.getStatus());
		dto.setRemark(alertInfo.getRemark());
		dto.setUserName(super.getUserName(dto.getUserId()));

		// 所有角色ID
		String alertAnalyst = PropertiesUtil.getValue("Alert.analyst");
		String alertQAWaived = PropertiesUtil.getValue("Alert.QA.Waived");
		String alertQAEscalated = PropertiesUtil.getValue("Alert.QA.Escalated");
		String alertSupervisor = PropertiesUtil.getValue("Alert.Supervisor");
		// QA审批时，判断是否对分析员已评分
		if (roleId.equals(alertQAWaived) || roleId.equals(alertQAEscalated)) {
			String msg = this.isScore(alertInfo.getTaskId());
			if (StringUtils.isNotEmpty(msg)) {
				result.setErrorInfo(msg);
				return result;
			}
		}
		String flowId = PropertiesUtil.getValue("processId.alert");
		if (roleId.equals(alertAnalyst)) {
			result.setResult(flowService.approveAlertAnalyst(flowId, dto, dto.getToken(), dto.getUserId()));
		} else if (roleId.equals(alertQAWaived) || roleId.equals(alertQAEscalated)) {
			result.setResult(flowService.approveAlertQA(flowId, dto));
		} else if (roleId.equals(alertSupervisor)) {
			result.setResult(flowService.approveAlertSupervisor(flowId, dto));
		}

		return result;
	}

	/**
	 * 
	 * @title: approveSar
	 * @description: 审批流程时，点击【提交】/【同意】/【不同意】,触发工作流 TODO
	 *
	 * @param roleId 角色ID
	 * @param dto 参数<br>
	 *        caseId=>记录主键（必传）<br>
	 *        approvalId=>审批主键（可选）<br>
	 *        result=>Processing result（必传）如： Reasonable/SAR, agree/disagree<br>
	 *        content=>审批意见（可选）<br>
	 *        score=>评分（可选）<br>
	 * @return ApiResult
	 * @date 2017年11月30日 下午3:06:26
	 */
	@RequestMapping(value = "approveSar/{roleId}", method = RequestMethod.POST)
	public ApiResult<Void> approveSar ( HttpServletRequest request, @PathVariable("roleId") String roleId,
			@RequestBody TaskApprovalDTO dto ) {
		logger.info("request url：{},  roleId：{}，dto：{}",
				new Object[] { request.getRequestURL(), roleId, dto.toJson() });
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCaseId())) {
			result.setErrorInfo("The Case ID cannot be empty");
			return result;
		}
		if (ObjectUtils.isEmpty(dto.getUserId())) {
			result.setErrorInfo("The User Id cannot be empty");
			return result;
		}
		if(StrUtils.strLength(dto.getContent())> 5000){
			result.setErrorInfo("The Content length is more than 1000 characters");
			return result;
		}
		// 查询Case信息
		CaseGroup caseGroup = caseService.findCaseGroup(dto.getCaseId());
		// 设置参数
		dto.setRoleId(roleId);
		dto.setOrderId(caseGroup.getOrderId());
		dto.setTaskId(caseGroup.getTaskId());
		dto.setStatus(caseGroup.getStatus());
		dto.setUserName(super.getUserName(dto.getUserId()));
		// 所有角色ID
		String sarAnalyst = PropertiesUtil.getValue("Sar.analyst");
		String sarQA = PropertiesUtil.getValue("Sar.QA");
		String sarOfficer = PropertiesUtil.getValue("Sar.Officer");
		String sarCoordinator = PropertiesUtil.getValue("Sar.Coordinator");
		String sarNarrative = PropertiesUtil.getValue("Sar.Narrative");
		// QA审批时，判断是否对分析员已评分
		if (roleId.equals(sarQA)) {
			String msg = this.isScore(caseGroup.getTaskId());
			if (StringUtils.isNotEmpty(msg)) {
				result.setErrorInfo(msg);
				return result;
			}
		}

		String flowId = PropertiesUtil.getValue("processId.sar");
		if (roleId.equals(sarAnalyst)) {
			result.setResult(flowService.approveSarAnalyst(flowId, dto, dto.getToken(), dto.getUserId()));
		} else if (roleId.equals(sarQA)) {
			result.setResult(flowService.approveSarQA(flowId, dto, dto.getToken(), dto.getUserId()));
		} else if (roleId.equals(sarOfficer)) {
			result.setResult(flowService.approveSarOfficer(flowId, dto));
		} else if (roleId.equals(sarCoordinator)) {
			result.setResult(flowService.approveSarCoordinator(flowId, dto, dto.getToken(), dto.getUserId()));
		} else if (roleId.equals(sarNarrative)) {
			result.setResult(flowService.approveSarNarrative(flowId, dto, dto.getToken(), dto.getUserId()));
		}

		return result;
	}

	/**
	 * 
	 * @title: isScore
	 * @description: 判断QA是否对分析员进行评分, 未评分，则返回错误信息
	 *
	 * @param taskId 任务ID
	 * @return String
	 * @date 2018年1月2日 下午4:05:44
	 */
	private String isScore ( String taskId ) {
		String msg = "";
		TaskScore taskScore = flowService.findScoreByTaskId(taskId);
		if (ObjectUtils.isEmpty(taskScore)) {
			msg = "You don't score the analyst and you can't submit it";
		}

		return msg;
	}
}
