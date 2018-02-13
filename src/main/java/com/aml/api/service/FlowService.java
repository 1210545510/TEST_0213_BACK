package com.aml.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.DBAccess;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.CaseGroupMapper;
import com.aml.api.dao.CaseInfoMapper;
import com.aml.api.dao.TaskApprovalMapper;
import com.aml.api.dao.TaskScoreMapper;
import com.aml.api.dao.WfBusStatusMapper;
import com.aml.api.dto.TaskApprovalDTO;
import com.aml.api.dto.TaskScoreDTO;
import com.aml.api.dto.Users;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.pojo.TaskApproval;
import com.aml.api.pojo.TaskFile;
import com.aml.api.pojo.TaskScore;
import com.aml.api.pojo.WfBusStatus;
import com.aml.common.core.AlertConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.FlowConsts;
import com.aml.common.core.Result;
import com.aml.common.util.PropertiesUtil;
import com.aml.common.util.StrUtils;
import com.aml.common.util.UMSUtils;

/**
 * 
 * @className: FlowService
 * @description: 流程服务类
 * @author huangliangbao
 * @date 2017年11月15日 下午12:09:45
 *
 */
@Service
public class FlowService {
	public static final Logger logger = LoggerFactory.getLogger(FlowService.class);
	@Autowired
	private SnakerEngineFacets facets;
	@Autowired
	private TaskApprovalService taskApprovalService;
	@Autowired
	private TaskApprovalMapper taskApprovalMapper;
	@Autowired
	private WfBusStatusMapper wfBusStatusMapper;
	@Autowired
	private AlertGroupMapper alertGroupMapper;
	@Autowired
	private CaseGroupMapper caseGroupMapper;
	@Autowired
	private CaseInfoMapper caseInfoMapper;
	@Autowired
	private TaskScoreMapper taskScoreMapper;
	@Autowired
	private DBAccess dbAccess;

	/**
	 * 
	 * @title: assign
	 * @description: 转办，修改任务人
	 *
	 * @param operator 转办人
	 * @param mapList 任务数组
	 * @return Result
	 * @date 2017年12月5日 上午11:03:08
	 */
	public Result assign ( String operator, List<Map<String, Object>> mapList ) {
		Result result = new Result(ErrorTypeEnum.OK);
		for (Map<String, Object> map : mapList) {
			Long alertId = (Long) map.get("alertId");
			Long caseId = (Long) map.get("caseId");
			String taskId = (String) map.get("taskId");
			String[] actors = facets.getEngine().query().getTaskActorsByTaskId(taskId);
			// 获取当前任务
			Task task = facets.getEngine().query().getTask(taskId);
			Map<String, Object> variableMap = task.getVariableMap();
			// 获取assignee对应的值
			String assignee = this.getAssignee(variableMap);
			variableMap.put(assignee, operator);
			variableMap.put(Task.KEY_ACTOR, operator);

			// 1、修改任务处理人
			List<Task> taskList = facets.transferMajor(taskId, actors[0], operator.toString());
			// 新任务
			Task newTask = taskList.get(0);
			newTask.setVariable(JsonHelper.toJson(variableMap));
			// 修改新任务的处理人和附属变量json
			dbAccess.updateTask(newTask);
			// 删除当前任务的保存的历史记录
			taskApprovalMapper.deleteByTaskId(taskId);
			// 2、更新Alert/Case记录的taskId和流程状态值
			Map<String, Object> updateParams = new HashMap<String, Object>();
			updateParams.put("taskId", taskList.get(0).getId());
			if (!ObjectUtils.isEmpty(alertId)) {
				updateParams.put("alertId", alertId);
				AlertGroup alertGroup = alertGroupMapper.findAlertGroup(alertId);
				if (!ObjectUtils.isEmpty(alertGroup.getPreStatus()) && alertGroup.getPreStatus() != 0) {
					updateParams.put("status", alertGroup.getPreStatus());
				}
				alertGroupMapper.updateFlow(updateParams);
			} else if (!ObjectUtils.isEmpty(caseId)) {
				updateParams.put("caseId", caseId);
				CaseGroup caseGroup = caseGroupMapper.findCaseGroup(caseId);
				if (!ObjectUtils.isEmpty(caseGroup.getPreStatus()) && caseGroup.getPreStatus() != 0) {
					updateParams.put("status", caseGroup.getPreStatus());
				}
				caseGroupMapper.updateFlow(updateParams);
			}
		}
		return result;
	}

	/**
	 * 
	 * @title: approveSave
	 * @description: 保存审批记录
	 *
	 * @param dto 审批对象
	 * @return Result
	 * @date 2017年12月5日 上午11:03:08
	 */
	public Result approveSave ( TaskApprovalDTO dto ) {
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		return taskApprovalService.save(dto, taskFiles);
	}

	/**
	 * 
	 * @title: scoreSave
	 * @description: 保存评分记录
	 *
	 * @param dto 评分对象
	 * @return Result
	 * @date 2017年12月5日 上午11:03:08
	 */
	public Result saveScore ( TaskScoreDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = taskScoreMapper.insert(dto);
		if (flag <= 0) {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
		}
		return result;
	}

	/**
	 * 
	 * @title: findScoreByTaskId
	 * @description: 查询一条评分记录
	 *
	 * @param taskId
	 * @return TaskScore
	 * @date 2017年11月16日 上午9:34:49
	 */
	public TaskScore findScoreByTaskId ( String taskId ) {
		return taskScoreMapper.findScoreByTaskId(taskId);
	}

	/**
	 * 
	 * @title: updateScore
	 * @description: 修改评分记录
	 *
	 * @param dto
	 * @return Result
	 * @date 2017年11月16日 上午9:32:07
	 */
	public Result updateScore ( TaskScoreDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = taskScoreMapper.update(dto);
		if (flag <= 0) {
			result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
		}
		return result;
	}

	/**
	 * 
	 * @title: approveAlertAnalyst
	 * @description: AlertAnalyst审批
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return Result
	 * @date 2017年12月9日 下午1:43:55
	 */
	public Result approveAlertAnalyst ( String flowId, TaskApprovalDTO dto, String token, Long userId) {
		// 流程分支跳转变量值
		String decision = dto.getResult();
		if (dto.getStatus() == FlowConsts.ESCALATED_UNDER_RE_WORK) {
			if (FlowConsts.WAIVE.equals(dto.getResult())) {
				decision = FlowConsts.WAIVE_ZH;
			} else if (FlowConsts.ESCALATE.equals(dto.getResult())) {
				decision = FlowConsts.ESCALATE_ZH;
			}
		}
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 动态设置参与者
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", decision);
			params.put("content", dto.getContent());
			// 下一节点角色ID
			String roleId = "";
			// 下一节历史处理人ID
			String historyRoleId = "";
			// 下一节点处理人参数
			String operatorKey = "";
			// 是否为锁定状态
			boolean isLock = false;
			switch (dto.getRoleNum()) {
			case 1:
				// 下一节点：Alert QA Review(Escalated)
				if (FlowConsts.ESCALATE.equals(dto.getResult())) {
					roleId = PropertiesUtil.getValue("Alert.analyst.Escalated_one");
					operatorKey = FlowConsts.ALERT_QA_ESCALATED;
				} else if (FlowConsts.WAIVE.equals(dto.getResult())) {
					if (dto.getStatus().equals(FlowConsts.UNDER_REVIEW)) {
						isLock = true;
					} else {
						operatorKey = FlowConsts.ALERT_QA_WAIVED;
						// 获取下一节点历史处理人(默认按时间降序查询)
						HistoryTask historyTask = this.findHistoryTask(dto.getOrderId(),
								FlowConsts.TASK_ALERT_QA_WAIVED);
						params.put(operatorKey, historyTask.getOperator());
						TaskApproval approval = taskApprovalService.findByTaskId(historyTask.getId());
						historyRoleId = approval.getRoleId();
					}
				}
				break;
			case 2:
				// 下一节点：Alert supervisor review
				if (FlowConsts.ESCALATE.equals(dto.getResult())) {
					roleId = PropertiesUtil.getValue("Alert.analyst.Escalated_two");
					operatorKey = FlowConsts.ALERT_SUPERVISOR;
				}
				break;
			default:
				break;
			}

			if (StringUtils.isNotBlank(roleId)) {
				// 获取小组中下一节点处理人列表
				List<Users> users = UMSUtils.queryRoleUsersByTeamCode(dto.getOwnerOrg(), Long.valueOf(roleId), token, userId);
				if(ObjectUtils.isEmpty(users)){
					result.setErrorType(ErrorTypeEnum.NOT_NEXT_USER_ERROR);
					return result;
				}
				// 随机获取一个用户
				int index = (int) (Math.random() * users.size());
				Users user = users.get(index);
				params.put(operatorKey, user.getUserName());
			}
			// 当审批结果为Waived，则当前任务需要锁定，等待抽样处理
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("alertId", dto.getAlertId());
			map.put("roleId", roleId.equals("") ? historyRoleId : roleId);
			if (isLock) {
				map.put("isLock", FlowConsts.LOCK);
			}

			// 执行工作流
			List<Task> newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			map.put("taskId", newTask.size() == 0 ? "" : newTask.get(0).getId());

			if (!isLock) {
				// 查询下一节点的流程状态ID
				WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), null, dto.getResult());
				map.put("status", busStatus == null ? "" : busStatus.getDicCode());
				map.put("preStatus", "");
			}

			// 修改Alert Info数据表
			alertGroupMapper.updateFlow(map);
		}
		return result;
	}

	/**
	 * 
	 * @title: approveAlertQA
	 * @description: Alert QA审批
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return Result
	 * @date 2017年12月4日 下午5:28:10
	 */
	public Result approveAlertQA ( String flowId, TaskApprovalDTO dto ) {
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 设置参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", dto.getResult());
			params.put("content", dto.getContent());
			// 执行工作流
			List<Task> newTask = new ArrayList<Task>();
			Integer statusType = null;
			String waivedRoleId = PropertiesUtil.getValue("Alert.QA.Waived");
			String escalatedRoleId = PropertiesUtil.getValue("Alert.QA.Escalated");
			if (FlowConsts.DISAGREE.equals(dto.getResult())) {
				// 下一节处理人为第一次Alert Analyst处理人
				HistoryTask historyTask = this.findHistoryTask(dto.getOrderId(), FlowConsts.TASK_ALERT_ANALYST);
				String userName = historyTask.getOperator();
				params.put(FlowConsts.ALERT_ANALYST, userName);
				statusType = FlowConsts.STATUS_DISAGREE;
				// Alert QA(Waived) 不同意，表示驳回；Alert QA(Escalated) 不同意，流程正常执行
				if (waivedRoleId.equals(dto.getRoleId())) {
					// 若存在任务转办，则不能直接驳回至第一步，需要指定具体驳回节点
					newTask = facets.executeAndJump(dto.getTaskId(), dto.getUserName(), params,
							FlowConsts.TASK_ALERT_ANALYST);
				} else if (escalatedRoleId.equals(dto.getRoleId())) {
					dto.setRoleNum(2);
					newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
				}
			} else if (FlowConsts.AGREE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_AGREE;
				newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			}
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), statusType, null);
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("alertId", dto.getAlertId());
			map.put("taskId", newTask.size() == 0 ? null : newTask.get(0).getId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleNum", dto.getRoleNum());
			if (FlowConsts.AGREE.equals(dto.getResult())) {
				map.put("closedDate", FlowConsts.FINISH_DATE);
			} else {
				map.put("roleId", PropertiesUtil.getValue("Alert.analyst"));
			}
			alertGroupMapper.updateFlow(map);

			// 生成Case记录
			if (escalatedRoleId.equals(dto.getRoleId()) && FlowConsts.AGREE.equals(dto.getResult())) {
				this.insertCase(dto.getUserName(), dto.getAlertId());
			}
		}

		return result;
	}

	/**
	 * 
	 * @title: approveAlertSupervisor
	 * @description: Alert Supervisor审批 TODO
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return Result
	 * @date 2017年12月4日 下午5:28:10
	 */
	public Result approveAlertSupervisor ( String flowId, TaskApprovalDTO dto ) {
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 设置参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", dto.getResult());
			params.put("content", dto.getContent());
			// 执行工作流
			facets.execute(dto.getTaskId(), dto.getUserName(), params);
			// 流程类型
			Integer statusType = null;
			if (FlowConsts.WAIVE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_DISAGREE;
			} else {
				statusType = FlowConsts.STATUS_AGREE;
			}
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), statusType, null);
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("alertId", dto.getAlertId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleId", dto.getRoleId());
			map.put("closedDate", FlowConsts.FINISH_DATE);
			alertGroupMapper.updateFlow(map);
			// 生成Case记录
			if (FlowConsts.ESCALATE.equals(dto.getResult())) {
				this.insertCase(dto.getUserName(), dto.getAlertId());
			}
		}
		return result;
	}

	/**
	 * 
	 * @title: approveSarAnalyst
	 * @description: Sar Analyst审批
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return
	 * @date 2017年12月5日 上午11:25:12
	 */
	public Result approveSarAnalyst ( String flowId, TaskApprovalDTO dto, String token, Long userId) {
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 动态设置参与者
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", dto.getResult());
			params.put("content", dto.getContent());
			// 下一节点处理人参数
			String operatorKey = FlowConsts.SAR_QA;
			// 下一节点角色ID
			String roleId = PropertiesUtil.getValue("Sar.analyst.nextOperator");

			if (StringUtils.isNotBlank(roleId)) {
				// 获取下一节点处理人列表
				List<Users> users = UMSUtils.queryEnableUsers(Long.valueOf(roleId), token, userId);
				if(ObjectUtils.isEmpty(users)){
					result.setErrorType(ErrorTypeEnum.NOT_NEXT_USER_ERROR);
					return result;
				}
				// 随机获取一个用户
				int index = (int) (Math.random() * users.size());
				Users user = users.get(index);
				params.put(operatorKey, user.getUserName());
			}

			// 执行工作流
			List<Task> newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), null, dto.getResult());
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseId", dto.getCaseId());
			map.put("taskId", newTask.size() == 0 ? "" : newTask.get(0).getId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleId", roleId);
			caseGroupMapper.updateFlow(map);
		}
		return result;
	}

	/**
	 * 
	 * @title: approveSarQA
	 * @description: Sar QA审批 TODO
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return
	 * @date 2017年12月5日 上午11:25:12
	 */
	public Result approveSarQA ( String flowId, TaskApprovalDTO dto, String token, Long userId) {
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 查询Sar analyst的处理结果
			HistoryTask historyTask = this.findHistoryTask(dto.getOrderId(), FlowConsts.TASK_SAR_ANALYST);

			// 驳回处理人
			String preOperator = "";
			// 下一节点处理人参数
			String operatorKey = "";
			// 下一节点角色ID
			String roleId = "";
			// 下一节点历史处理人角色ID
			String historyRoleId = "";
			// 处理结果
			String processingResult = "";
			if (FlowConsts.AGREE.equals(dto.getResult())) {
				Map<String, Object> variableMap = historyTask.getVariableMap();
				processingResult = variableMap.get("result").toString();
				if (FlowConsts.REASONABLE.equals(processingResult)) {
					operatorKey = FlowConsts.SAR_COORDINATOR;
					roleId = PropertiesUtil.getValue("Sar.QA.agree.Reasonable");
				} else if (FlowConsts.SAR.equals(processingResult)) {
					operatorKey = FlowConsts.SAR_OFFICER;
					roleId = PropertiesUtil.getValue("Sar.QA.agree.SAR");
				}
			} else if (FlowConsts.DISAGREE.equals(dto.getResult())) {
				operatorKey = FlowConsts.SAR_ANALYST;
				preOperator = historyTask.getOperator();
				TaskApproval approval = taskApprovalService.findByTaskId(historyTask.getId());
				historyRoleId = approval.getRoleId();
			}
			// 动态设置参与者
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", processingResult);
			params.put("content", dto.getContent());
			if (StringUtils.isNotBlank(roleId)) {
				// 获取下一节点处理人列表
				List<Users> users = UMSUtils.queryEnableUsers(Long.valueOf(roleId), token, userId);
				if(ObjectUtils.isEmpty(users)){
					result.setErrorType(ErrorTypeEnum.NOT_NEXT_USER_ERROR);
					return result;
				}
				// 随机获取一个用户
				int index = (int) (Math.random() * users.size());
				Users user = users.get(index);
				params.put(operatorKey, user.getUserName());
			} else {
				params.put(operatorKey, preOperator);
			}

			// 执行工作流
			Integer statusType = null;
			List<Task> newTask = new ArrayList<Task>();
			if (FlowConsts.AGREE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_AGREE;
				newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			} else if (FlowConsts.DISAGREE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_DISAGREE;
				newTask = facets.executeAndJump(dto.getTaskId(), dto.getUserName(), params,
						FlowConsts.TASK_SAR_ANALYST);
			}
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), statusType, null);
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseId", dto.getCaseId());
			map.put("taskId", newTask.size() == 0 ? "" : newTask.get(0).getId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleId", roleId.equals("") ? historyRoleId : roleId);
			caseGroupMapper.updateFlow(map);
		}
		return result;
	}

	/**
	 * 
	 * @title: approveSarOfficer
	 * @description: Sar BSA-officer审批 TODO
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return
	 * @date 2017年12月5日 上午11:25:12
	 */
	public Result approveSarOfficer ( String flowId, TaskApprovalDTO dto ) {
		// 决策表达式的值
		String decision = this.convertResult(dto.getResult(), dto.getStatus());
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 动态设置参与者
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", decision);
			params.put("content", dto.getContent());
			// 执行工作流
			List<Task> newTask = new ArrayList<Task>();
			String roleId = "";
			Integer statusType = null;
			if (FlowConsts.AGREE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_AGREE;
				newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			} else if (FlowConsts.DISAGREE_ZH.equals(decision)) {
				statusType = FlowConsts.STATUS_DISAGREE;
				// 查询Sar analyst的处理结果
				HistoryTask hisTaskAnalyst = this.findHistoryTask(dto.getOrderId(), FlowConsts.TASK_SAR_ANALYST);
				params.put(FlowConsts.SAR_ANALYST, hisTaskAnalyst.getOperator());
				TaskApproval approval = taskApprovalService.findByTaskId(hisTaskAnalyst.getId());
				roleId = approval.getRoleId();
				newTask = facets.executeAndJump(dto.getTaskId(), dto.getUserName(), params,
						FlowConsts.TASK_SAR_ANALYST);
			} else if (FlowConsts.DISAGREE_EN.equals(decision)) {
				statusType = FlowConsts.STATUS_DISAGREE;
				// 查询Coordinator的处理结果
				HistoryTask hisTaskCoordinator = this.findHistoryTask(dto.getOrderId(),
						FlowConsts.TASK_SAR_COORDINATOR);
				params.put(FlowConsts.SAR_COORDINATOR, hisTaskCoordinator.getOperator());
				TaskApproval approval = taskApprovalService.findByTaskId(hisTaskCoordinator.getId());
				roleId = approval.getRoleId();
				newTask = facets.executeAndJump(dto.getTaskId(), dto.getUserName(), params,
						FlowConsts.TASK_SAR_COORDINATOR);
			}
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), statusType, null);
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseId", dto.getCaseId());
			map.put("taskId", newTask.size() == 0 ? "" : newTask.get(0).getId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleId", roleId);
			map.put("closedDate", FlowConsts.FINISH_DATE);
			caseGroupMapper.updateFlow(map);
		}
		return result;
	}

	/**
	 * 
	 * @title: approveSarCoordinator
	 * @description: Coordinator审批 TODO
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return
	 * @date 2017年12月5日 上午11:25:12
	 */
	public Result approveSarCoordinator ( String flowId, TaskApprovalDTO dto, String token, Long userId) {
		// 决策表达式的值
		String decision = this.convertResult(dto.getResult(), dto.getStatus());
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 驳回处理人
			String preOperator = "";
			// 下一节点处理人参数
			String operatorKey = "";
			// 下一节点角色ID
			String roleId = "";
			// 下一节点历史处理人角色ID
			String historyRoleId = "";
			Integer statusType = null;
			if (FlowConsts.AGREE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_AGREE;
				operatorKey = FlowConsts.SAR_NARRATIVE;
				roleId = PropertiesUtil.getValue("Sar.Coordinator.nextOperator");
			} else if (FlowConsts.DISAGREE.equals(dto.getResult())) {
				statusType = FlowConsts.STATUS_DISAGREE;
				operatorKey = FlowConsts.SAR_ANALYST;
				// 查询Sar analyst的处理结果
				HistoryTask historyTask = this.findHistoryTask(dto.getOrderId(), FlowConsts.TASK_SAR_ANALYST);
				preOperator = historyTask.getOperator();
				TaskApproval approval = taskApprovalService.findByTaskId(historyTask.getId());
				historyRoleId = approval.getRoleId();
			}
			// 动态设置参与者
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", decision);
			params.put("content", dto.getContent());
			if (StringUtils.isNotBlank(roleId)) {
				// 获取下一节点处理人列表
				List<Users> users = UMSUtils.queryEnableUsers(Long.valueOf(roleId), token, userId);
				if(ObjectUtils.isEmpty(users)){
					result.setErrorType(ErrorTypeEnum.NOT_NEXT_USER_ERROR);
					return result;
				}
				// 随机获取一个用户
				int index = (int) (Math.random() * users.size());
				Users user = users.get(index);
				params.put(operatorKey, user.getUserName());
			} else {
				params.put(operatorKey, preOperator);
			}

			// 执行工作流
			List<Task> newTask = new ArrayList<Task>();
			if (FlowConsts.AGREE.equals(dto.getResult())) {
				newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			} else if (FlowConsts.DISAGREE.equals(dto.getResult())) {
				// 驳回到指定节点
				newTask = facets.executeAndJump(dto.getTaskId(), dto.getUserName(), params,
						FlowConsts.TASK_SAR_ANALYST);
			}
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), statusType, null);
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseId", dto.getCaseId());
			map.put("taskId", newTask.size() == 0 ? "" : newTask.get(0).getId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleId", roleId.equals("") ? historyRoleId : roleId);
			caseGroupMapper.updateFlow(map);
		}
		return result;
	}

	/**
	 * 
	 * @title: approveSarNarrative
	 * @description: Narrative审批 TODO
	 *
	 * @param flowId 流程ID
	 * @param dto 审批对象
	 * @return
	 * @date 2017年12月5日 上午11:25:12
	 */
	public Result approveSarNarrative ( String flowId, TaskApprovalDTO dto, String token, Long userId) {
		// 获取上传文件
		List<TaskFile> taskFiles = this.getTaskFiles(dto);
		// 保存
		Result result = taskApprovalService.save(dto, taskFiles);
		if (result.isSuccess()) {
			// 下一节点处理人参数
			String operatorKey = FlowConsts.SAR_OFFICER;
			// 下一节点角色ID
			String roleId = PropertiesUtil.getValue("Sar.Narrative.nextOperator");

			// 动态设置参与者
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("result", "Submit");
			params.put("content", dto.getContent());
			if (StringUtils.isNotBlank(roleId)) {
				// 获取下一节点处理人列表
				List<Users> users = UMSUtils.queryEnableUsers(Long.valueOf(roleId), token, userId);
				if(ObjectUtils.isEmpty(users)){
					result.setErrorType(ErrorTypeEnum.NOT_NEXT_USER_ERROR);
					return result;
				}
				// 随机获取一个用户
				int index = (int) (Math.random() * users.size());
				Users user = users.get(index);
				params.put(operatorKey, user.getUserName());
			}

			// 执行工作流
			List<Task> newTask = facets.execute(dto.getTaskId(), dto.getUserName(), params);
			// 查询下一节点的流程状态ID
			WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, dto.getStatus(), null, null);
			// 修改Alert Info数据表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseId", dto.getCaseId());
			map.put("taskId", newTask.size() == 0 ? "" : newTask.get(0).getId());
			map.put("status", busStatus == null ? "" : busStatus.getDicCode());
			map.put("preStatus", "");
			map.put("roleId", roleId);
			caseGroupMapper.updateFlow(map);
		}

		return result;
	}

	/**
	 * 
	 * @title: getTaskFiles
	 * @description: 得到附件列表
	 *
	 * @param dto
	 * @return List<TaskFile>
	 * @date 2017年12月9日 下午1:40:21
	 */
	private List<TaskFile> getTaskFiles ( TaskApprovalDTO dto ) {
		List<TaskFile> fileList = new ArrayList<TaskFile>();
		if (!CollectionUtils.isEmpty(dto.getFiles())) {
			for (Map<String, String> map : dto.getFiles()) {
				TaskFile file = new TaskFile();
				file.setTaskId(dto.getTaskId());
				file.setFileName(map.get("fileName"));
				file.setFilePath(map.get("filePath"));
				file.setCreateUser(dto.getUserName());
				fileList.add(file);
			}
		}

		return fileList;
	}

	/**
	 * 
	 * @title: findHistoryTask
	 * @description: 获取指定节点的历史处理记录
	 *
	 * @param orderId 实例ID
	 * @param taskName 任务名称
	 * @return HistoryTask
	 * @date 2017年12月5日 下午6:45:02
	 */
	private HistoryTask findHistoryTask ( String orderId, String taskName ) {
		List<HistoryTask> histTasks = facets.getEngine().query()
				.getHistoryTasks(new QueryFilter().setOrderId(orderId).setName(taskName));

		return histTasks.get(0);
	}

	/**
	 * 
	 * @title: convertResult
	 * @description: 根据审批结果，获取决策表达式的值
	 *
	 * @param result
	 * @param status
	 * @return String
	 * @date 2017年12月7日 上午9:36:31
	 */
	private String convertResult ( String result, Integer status ) {
		// 决策表达式的值
		String decision = result;
		if (FlowConsts.AGREE.equals(result)) {
			if (status == FlowConsts.SAR_UNDER_BSA_OFFICER) {
				decision = FlowConsts.AGREE_ZH;
			} else if (status == FlowConsts.REASONABLE_UNDER_BSA_OFFICER) {
				decision = FlowConsts.AGREE_EN;
			}
		} else if (FlowConsts.DISAGREE.equals(result)) {
			if (status == FlowConsts.SAR_UNDER_BSA_OFFICER) {
				decision = FlowConsts.DISAGREE_ZH;
			} else if (status == FlowConsts.REASONABLE_UNDER_BSA_OFFICER) {
				decision = FlowConsts.DISAGREE_EN;
			}
		}

		return decision;
	}

	private String getAssignee ( Map<String, Object> variableMap ) {
		// 获取assignee对应的值
		String assignee = null;
		if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.ALERT_ANALYST))) {
			assignee = FlowConsts.ALERT_ANALYST;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.ALERT_QA_WAIVED))) {
			assignee = FlowConsts.ALERT_QA_WAIVED;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.ALERT_QA_ESCALATED))) {
			assignee = FlowConsts.ALERT_QA_ESCALATED;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.ALERT_SUPERVISOR))) {
			assignee = FlowConsts.ALERT_SUPERVISOR;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.SAR_ANALYST))) {
			assignee = FlowConsts.SAR_ANALYST;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.SAR_QA))) {
			assignee = FlowConsts.SAR_QA;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.SAR_OFFICER))) {
			assignee = FlowConsts.SAR_OFFICER;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.SAR_COORDINATOR))) {
			assignee = FlowConsts.SAR_COORDINATOR;
		} else if (!ObjectUtils.isEmpty(variableMap.get(FlowConsts.SAR_NARRATIVE))) {
			assignee = FlowConsts.SAR_NARRATIVE;
		}

		return assignee;
	}

	private synchronized void insertCase ( String userName, Long alertId ) {
		Long caseId = StrUtils.getPrimaryKey(caseGroupMapper.findNewCaseId());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("alertId", alertId);
		params.put("caseId", caseId);
		// Alert升级为Case ，默认为管理员创建，任务分配时，使用随机分配
		params.put("isAdminCreate", AlertConsts.CREATE_IS_ADMIN);
		params.put("userName", userName);
		caseGroupMapper.insertCaseGroup(params);
		caseInfoMapper.insertCaseInfo(params);
	}

}
