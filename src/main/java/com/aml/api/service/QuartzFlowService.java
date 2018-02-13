package com.aml.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.AssignRuleMapper;
import com.aml.api.dao.CaseGroupMapper;
import com.aml.api.dao.WfBusStatusMapper;
import com.aml.api.dto.Users;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.AlertTeam;
import com.aml.api.pojo.AssignRule;
import com.aml.api.pojo.AssignRuleItem;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.pojo.WfBusStatus;
import com.aml.common.core.AlertConsts;
import com.aml.common.core.ConfigConsts;
import com.aml.common.core.FlowConsts;
import com.aml.common.util.DateUtils;
import com.aml.common.util.PropertiesUtil;
import com.aml.common.util.UMSUtils;

/**
 * 
 * @className: QuartzFlowService
 * @description: 定时任务
 * @author Bao
 * @date 2017年12月29日
 *
 */
@Service
public class QuartzFlowService {
	public static final Logger logger = LoggerFactory.getLogger(QuartzFlowService.class);
	@Autowired
	private SnakerEngineFacets facets;
	@Autowired
	private AlertGroupMapper alertGroupMapper;
	@Autowired
	private CaseGroupMapper caseGroupMapper;
	@Autowired
	private WfBusStatusMapper wfBusStatusMapper;
	@Autowired
	AssignRuleMapper assignRuleMapper;

	public void createAlertFlow ( String processId, String token, Long userId ) {
		// 查询自己创建的Alert，分配给自已
		List<Map<String, String>> privateAlertList = alertGroupMapper.queryPrivateAlert(AlertConsts.SYS_TYPE_AML,
				AlertConsts.CREATE_IS_NOT_ADMIN);
		if (!ObjectUtils.isEmpty(privateAlertList)) {
			for (Map<String, String> map : privateAlertList) {
				// 流程数据
				Map<String, Object> args = new HashMap<String, Object>();
				args.put("apply.operator", "auto");// 默认auto, TODO, 在查询审批列表时，需要过滤这条数据
				args.put("businessId", map.get("alertId"));
				args.put(FlowConsts.ALERT_ANALYST, map.get("userName"));
				args.put("expireTime", DateUtils.parse(DateUtils.getDateTime(30)));
				args.put("reminderTime", DateUtils.parse(DateUtils.getDateTime(23)));
				// 2、启动，创建流程实例，设置过期时间：创建时间 + 30 天
				Order order = facets.startAndExecute(processId, "", args);
				List<Task> tasks = facets.getEngine().query()
						.getActiveTasks(new QueryFilter().setOrderId(order.getId()));
				// 3、修改Alert Info数据表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("alertId", map.get("alertId"));
				params.put("status", FlowConsts.PENDING_REVIEW);
				params.put("orderId", order.getId());
				params.put("taskId", tasks.get(0).getId());
				params.put("roleId", PropertiesUtil.getValue("Alert.analyst"));
				params.put("roleNum", 1);
				alertGroupMapper.updateFlow(params);
			}
		}
		// Mantas和管理员创建的Alert
		List<AlertTeam> teams = alertGroupMapper.queryAlert(AlertConsts.SYS_TYPE_MANTAS, AlertConsts.CREATE_IS_ADMIN);
		if (!ObjectUtils.isEmpty(teams)) {
			this.assignAlert(processId, teams, token, userId);
		}
	}

	public void randomSamplingWaived ( String token, Long userId ) {
		// 查询被锁定的alert信息
		List<AlertTeam> teams = alertGroupMapper.queryIsLockAlert();
		if (!ObjectUtils.isEmpty(teams)) {
			this.assignWaivedAlert(teams, token, userId);
		}
	}

	/**
	 * 
	 * @title: assignAlert
	 * @description: 按规则分配任务
	 *
	 * @param processId 流程ID
	 * @param roleId 角色ID
	 * @param teams Alert集合
	 * @date 2018年1月23日 下午12:03:08
	 */
	private void assignAlert ( String processId, List<AlertTeam> teams, String token, Long userId ) {
		Long roleId = Long.valueOf(PropertiesUtil.getValue("Alert.analyst"));
		/** 获取Alert信息和用户列表 */
		// FIU小组对应的Alert信息和用户列表
		List<String> alertFIU = null;
		List<Users> userFIU = null;
		// CTR小组对应的Alert信息和用户列表
		List<String> alertCTR = null;
		List<Users> userCTR = null;
		// DCT小组对应的Alert信息和用户列表
		List<String> alertDCT = null;
		List<Users> userDCT = null;
		// FCB小组对应的Alert信息和用户列表
		List<String> alertFCB = null;
		List<Users> userFCB = null;
		for (AlertTeam team : teams) {
			String ownerOrg = team.getOwnerOrg();
			if (FlowConsts.TEAM_FIU.equals(ownerOrg)) {
				alertFIU = team.getList();
				userFIU = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			} else if (FlowConsts.TEAM_CTR.equals(ownerOrg)) {
				alertCTR = team.getList();
				userCTR = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			} else if (FlowConsts.TEAM_DCT.equals(ownerOrg)) {
				alertDCT = team.getList();
				userDCT = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			} else if (FlowConsts.TEAM_FCB.equals(ownerOrg)) {
				alertFCB = team.getList();
				userFCB = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			}
		}

		/** 查询Alert analyst 的分配规则 */
		AssignRule assingRule = assignRuleMapper.queryAssignRuleByRoleId(roleId);
		// 默认分配规则为1（即：平均分配）
		Integer ruleType = ConfigConsts.RULE_TYPE_AVG;
		if (!ObjectUtils.isEmpty(assingRule)) {
			ruleType = assingRule.getRuleType();
		}
		if (ruleType == ConfigConsts.RULE_TYPE_AVG) {
			if (!ObjectUtils.isEmpty(alertFIU) && !ObjectUtils.isEmpty(userFIU)) {
				System.out.println("===========analyst平均分配");
				this.runAlertFlow(processId, this.avgAssign(alertFIU, userFIU));
			}
			if (!ObjectUtils.isEmpty(alertCTR) && !ObjectUtils.isEmpty(userCTR)) {
				this.runAlertFlow(processId, this.avgAssign(alertCTR, userCTR));
			}
			if (!ObjectUtils.isEmpty(alertDCT) && !ObjectUtils.isEmpty(userDCT)) {
				this.runAlertFlow(processId, this.avgAssign(alertDCT, userDCT));
			}
			if (!ObjectUtils.isEmpty(alertFCB) && !ObjectUtils.isEmpty(userFCB)) {
				this.runAlertFlow(processId, this.avgAssign(alertFCB, userFCB));
			}
		} else if (ruleType == ConfigConsts.RULE_TYPE_WORKHOUR) {
			List<Map<String, String>> totalAlertList = new ArrayList<Map<String, String>>();
			// 计算用户的工作天数
			Date sysDate = new Date();
			if (!ObjectUtils.isEmpty(alertFIU) && !ObjectUtils.isEmpty(userFIU)) {
				for (Users user : userFIU) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, alertFIU, userFIU, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(alertCTR) && !ObjectUtils.isEmpty(userCTR)) {
				for (Users user : userCTR) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, alertCTR, userCTR, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(alertDCT) && !ObjectUtils.isEmpty(userDCT)) {
				for (Users user : userDCT) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, alertDCT, userDCT, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(alertFCB) && !ObjectUtils.isEmpty(userFCB)) {
				for (Users user : userFCB) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, alertFCB, userFCB, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
		} else if (ruleType == ConfigConsts.RULE_TYPE_WORKABILITY) {
			// 查询分析员评分记录
			StringBuffer roleIds = new StringBuffer();
			roleIds.append(PropertiesUtil.getValue("Alert.QA.Waived"));
			roleIds.append(",");
			roleIds.append(PropertiesUtil.getValue("Alert.QA.Escalated"));
			List<Users> scoreList = alertGroupMapper.queryAnalystScore(roleIds.toString());
			List<Map<String, String>> totalAlertList = new ArrayList<Map<String, String>>();
			if (!ObjectUtils.isEmpty(alertFIU) && !ObjectUtils.isEmpty(userFIU)) {
				this.workAblityAssign(assingRule, alertFIU, scoreList, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(alertCTR) && !ObjectUtils.isEmpty(userCTR)) {
				this.workAblityAssign(assingRule, alertCTR, scoreList, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(alertDCT) && !ObjectUtils.isEmpty(userDCT)) {
				this.workAblityAssign(assingRule, alertDCT, scoreList, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(alertFCB) && !ObjectUtils.isEmpty(userFCB)) {
				this.workAblityAssign(assingRule, alertFCB, scoreList, totalAlertList);
				this.runAlertFlow(processId, totalAlertList);
			}
		}
	}

	/**
	 * 
	 * @title: assignWaivedAlert
	 * @description: Alert Waived抽样,按规则分配任务
	 *
	 * @param teams Alert集合
	 * @date 2018年1月23日 下午12:03:08
	 */
	private void assignWaivedAlert ( List<AlertTeam> teams, String token, Long userId ) {
		String processId = PropertiesUtil.getValue("processId.alert");
		Long roleId = Long.valueOf(PropertiesUtil.getValue("Alert.QA.Waived"));
		/** 获取Alert信息和用户列表 */
		// FIU小组对应的Alert信息和用户列表
		List<String> alertFIU = null;
		List<String> samplingAlertFIU = null;
		List<String> finishAlertFIU = null;
		List<Users> userFIU = null;
		// CTR小组对应的Alert信息和用户列表
		List<String> alertCTR = null;
		List<String> samplingAlertCTR = null;
		List<String> finishAlertCTR = null;
		List<Users> userCTR = null;
		// DCT小组对应的Alert信息和用户列表
		List<String> alertDCT = null;
		List<String> samplingAlertDCT = null;
		List<String> finishAlertDCT = null;
		List<Users> userDCT = null;
		// FCB小组对应的Alert信息和用户列表
		List<String> alertFCB = null;
		List<String> samplingAlertFCB = null;
		List<String> finishAlertFCB = null;
		List<Users> userFCB = null;
		for (AlertTeam team : teams) {
			String ownerOrg = team.getOwnerOrg();
			if (FlowConsts.TEAM_FIU.equals(ownerOrg)) {
				alertFIU = team.getList();
				userFIU = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			} else if (FlowConsts.TEAM_CTR.equals(ownerOrg)) {
				alertCTR = team.getList();
				userCTR = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			} else if (FlowConsts.TEAM_DCT.equals(ownerOrg)) {
				alertDCT = team.getList();
				userDCT = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			} else if (FlowConsts.TEAM_FCB.equals(ownerOrg)) {
				alertFCB = team.getList();
				userFCB = UMSUtils.queryRoleUsersByTeamCode(ownerOrg, roleId, token, userId);
			}
		}

		/** 假设：每个小组按80%进行抽样, 其它的Alert自动Waived */
		double rate = 0.8;
		// 所有未被抽样的Alert集合
		List<String> finishAlerts = new ArrayList<String>();
		if (!ObjectUtils.isEmpty(alertFIU)) {
			// TODO
			int FIUNum = (int) Math.round(alertFIU.size() * rate);
			samplingAlertFIU = alertFIU.subList(0, FIUNum);
			finishAlertFIU = alertFIU.subList(FIUNum, alertFIU.size());
			finishAlerts.addAll(finishAlertFIU);
			System.out.println("FIU抽取的个数num=============" + FIUNum);
		}
		if (!ObjectUtils.isEmpty(alertCTR)) {
			int CTRNum = (int) Math.round(alertCTR.size() * rate);
			samplingAlertCTR = alertCTR.subList(0, CTRNum);
			finishAlertCTR = alertCTR.subList(CTRNum, alertCTR.size());
			finishAlerts.addAll(finishAlertCTR);
			System.out.println("CTR抽取的个数num=============" + CTRNum);
		}
		if (!ObjectUtils.isEmpty(alertDCT)) {
			int DCTNum = (int) Math.round(alertDCT.size() * rate);
			samplingAlertDCT = alertDCT.subList(0, DCTNum);
			finishAlertDCT = alertDCT.subList(DCTNum, alertDCT.size());
			finishAlerts.addAll(finishAlertDCT);
			System.out.println("DCT抽取的个数num=============" + DCTNum);
		}
		if (!ObjectUtils.isEmpty(alertFCB)) {
			int FCBNum = (int) Math.round(alertFCB.size() * rate);
			samplingAlertFCB = alertFCB.subList(0, FCBNum);
			finishAlertFCB = alertFCB.subList(FCBNum, alertFCB.size());
			finishAlerts.addAll(finishAlertFCB);
			System.out.println("FCB抽取的个数num=============" + FCBNum);
		}

		/** 查询Alert QA(Waived) 的分配规则 */
		AssignRule assingRule = assignRuleMapper.queryAssignRuleByRoleId(roleId);
		// 默认分配规则为1（即：平均分配）
		Integer ruleType = ConfigConsts.RULE_TYPE_AVG;
		if (!ObjectUtils.isEmpty(assingRule)) {
			ruleType = assingRule.getRuleType();
		}
		if (ruleType == ConfigConsts.RULE_TYPE_AVG) {
			if (!ObjectUtils.isEmpty(samplingAlertFIU) && !ObjectUtils.isEmpty(userFIU)) {
				System.out.println("===========QA平均分配");
				this.runWaivedAlertFlow(processId, this.avgAssign(samplingAlertFIU, userFIU));
			}
			if (!ObjectUtils.isEmpty(samplingAlertCTR) && !ObjectUtils.isEmpty(userCTR)) {
				this.runWaivedAlertFlow(processId, this.avgAssign(samplingAlertCTR, userCTR));
			}
			if (!ObjectUtils.isEmpty(samplingAlertDCT) && !ObjectUtils.isEmpty(userDCT)) {
				this.runWaivedAlertFlow(processId, this.avgAssign(samplingAlertDCT, userDCT));
			}
			if (!ObjectUtils.isEmpty(samplingAlertFCB) && !ObjectUtils.isEmpty(userFCB)) {
				this.runWaivedAlertFlow(processId, this.avgAssign(samplingAlertFCB, userFCB));
			}
		} else if (ruleType == ConfigConsts.RULE_TYPE_WORKHOUR) {
			List<Map<String, String>> totalAlertList = new ArrayList<Map<String, String>>();
			// 计算用户的工作天数
			Date sysDate = new Date();
			if (!ObjectUtils.isEmpty(samplingAlertFIU) && !ObjectUtils.isEmpty(userFIU)) {
				for (Users user : userFIU) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, samplingAlertFIU, userFIU, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(samplingAlertCTR) && !ObjectUtils.isEmpty(userCTR)) {
				for (Users user : userCTR) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, samplingAlertCTR, userCTR, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(samplingAlertDCT) && !ObjectUtils.isEmpty(userDCT)) {
				for (Users user : userDCT) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, samplingAlertDCT, userDCT, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(samplingAlertFCB) && !ObjectUtils.isEmpty(userFCB)) {
				for (Users user : userFCB) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, samplingAlertFCB, userFCB, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
		} else if (ruleType == ConfigConsts.RULE_TYPE_WORKABILITY) {
			// 查询分析员评分记录
			StringBuffer roleIds = new StringBuffer();
			roleIds.append(PropertiesUtil.getValue("Alert.QA.Waived"));
			roleIds.append(",");
			roleIds.append(PropertiesUtil.getValue("Alert.QA.Escalated"));
			List<Users> scoreList = alertGroupMapper.queryAnalystScore(roleIds.toString());
			List<Map<String, String>> totalAlertList = new ArrayList<Map<String, String>>();
			if (!ObjectUtils.isEmpty(samplingAlertFIU) && !ObjectUtils.isEmpty(userFIU)) {
				this.workAblityAssign(assingRule, samplingAlertFIU, scoreList, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(samplingAlertCTR) && !ObjectUtils.isEmpty(userCTR)) {
				this.workAblityAssign(assingRule, samplingAlertCTR, scoreList, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(samplingAlertDCT) && !ObjectUtils.isEmpty(userDCT)) {
				this.workAblityAssign(assingRule, samplingAlertDCT, scoreList, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
			if (!ObjectUtils.isEmpty(samplingAlertFCB) && !ObjectUtils.isEmpty(userFCB)) {
				this.workAblityAssign(assingRule, samplingAlertFCB, scoreList, totalAlertList);
				this.runWaivedAlertFlow(processId, totalAlertList);
			}
		}
		/** 未被抽样的Alert，自动结束 */
		this.sysAutoWaived(finishAlerts);
	}

	private void sysAutoWaived ( List<String> finishAlerts ) {
		if (!ObjectUtils.isEmpty(finishAlerts)) {
			// 数据参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("remark", "没被抽样的Waived任务，自动结束");
			AlertGroup alertGroup = null;
			for (String alertId : finishAlerts) {
				alertGroup = alertGroupMapper.findAlertGroup(Long.valueOf(alertId));
				String orderId = alertGroup.getOrderId();
				String taskId = alertGroup.getTaskId();
				List<HistoryTask> histTasks = facets.getEngine().query()
						.getHistoryTasks(new QueryFilter().setOrderId(orderId).setName(FlowConsts.ALERT_ANALYST));
				// 历史处理的分析员
				String userName = histTasks.get(0).getOperator();
				// 1、根据任务ID， 添加处理人，再删除原任务记录
				facets.getEngine().task().addTaskActor(taskId, userName);
				facets.getEngine().task().removeTaskActor(taskId, "QA.Waived.operator");

				// 2、系统执行任务进行结束
				facets.execute(taskId, userName, map);
				// 3、修改Alert Group数据表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("alertId", alertId);
				params.put("status", FlowConsts.WAIVED);
				params.put("preStatus", "");
				params.put("roleId", PropertiesUtil.getValue("Alert.analyst"));
				alertGroupMapper.updateFlow(params);
			}
		}
	}

	public void createSarFlow ( String processId, String token, Long userId ) {
		// 查询自己创建的Case，分配给自已
		List<CaseGroup> privateCaseList = caseGroupMapper.queryPrivateCase(AlertConsts.CREATE_IS_NOT_ADMIN);
		if (!ObjectUtils.isEmpty(privateCaseList)) {
			for (CaseGroup caseGroup : privateCaseList) {
				// 流程数据
				Map<String, Object> args = new HashMap<String, Object>();
				// 默认auto, TODO, 在查询审批列表时，需要过滤这条数据
				args.put("apply.operator", "auto");
				// 当前审批人
				args.put(FlowConsts.SAR_ANALYST, caseGroup.getCreateUser());
				// 实例ID（保存业务Case ID）
				args.put(SnakerEngine.ID, String.valueOf(caseGroup.getCaseId()));
				// 期望完成时间
				args.put("expireTime", DateUtils.parse(DateUtils.getDateTime(30)));
				// 提醒时间
				args.put("reminderTime", DateUtils.parse(DateUtils.getDateTime(23)));
				// 2、启动，创建流程实例
				Order order = facets.startAndExecute(processId, "", args);
				List<Task> tasks = facets.getEngine().query()
						.getActiveTasks(new QueryFilter().setOrderId(order.getId()));
				// 3、修改Case Info数据表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("caseId", caseGroup.getCaseId());
				params.put("status", FlowConsts.PENDING_INVESTIGATION);
				params.put("orderId", order.getId());
				params.put("taskId", tasks.get(0).getId());
				params.put("roleId", PropertiesUtil.getValue("Sar.analyst"));
				caseGroupMapper.updateFlow(params);
			}
		}

		// 查询90天未开始的Case信息，管理员创建的Alert，按规则进行分配
		List<String> caseList = caseGroupMapper.queryCase(AlertConsts.CREATE_IS_ADMIN);
		if (!ObjectUtils.isEmpty(caseList)) {
			Long roleId = Long.valueOf(PropertiesUtil.getValue("Sar.analyst"));
			// 查询Sar analyst角色对应的用户列表
			List<Users> userList = UMSUtils.queryEnableUsers(roleId, token, userId);
			// 查询Sar analyst 的分配规则
			AssignRule assingRule = assignRuleMapper.queryAssignRuleByRoleId(roleId);
			// 默认分配规则为1（即：平均分配）
			Integer ruleType = ConfigConsts.RULE_TYPE_AVG;
			if (!ObjectUtils.isEmpty(assingRule)) {
				ruleType = assingRule.getRuleType();
			}
			List<Map<String, String>> totalCaseList = new ArrayList<Map<String, String>>();
			if (ruleType == ConfigConsts.RULE_TYPE_AVG) {
				this.runSarFlow(processId, this.avgAssign(caseList, userList));
			} else if (ruleType == ConfigConsts.RULE_TYPE_WORKHOUR) {
				// 计算用户的工作天数
				Date sysDate = new Date();
				for (Users user : userList) {
					int days = DateUtils.daysBetween(user.getCreateTime(), sysDate);
					user.setDays(days);
				}
				this.workDaysAssign(assingRule, caseList, userList, totalCaseList);
				this.runSarFlow(processId, totalCaseList);
			} else if (ruleType == ConfigConsts.RULE_TYPE_WORKABILITY) {
				// 查询分析员评分记录
				List<Users> scoreList = alertGroupMapper.queryAnalystScore(PropertiesUtil.getValue("Sar.QA"));
				this.workAblityAssign(assingRule, caseList, scoreList, totalCaseList);
				this.runSarFlow(processId, totalCaseList);
			}
		}

	}

	/**
	 * 
	 * @title: avgAssign
	 * @description: 规则：平均分配 TODO
	 *
	 * @param infoList
	 * @param userList
	 * @return List<Map<String, String>>
	 * @date 2017年12月22日 下午4:18:42
	 */
	private List<Map<String, String>> avgAssign ( List<String> infoList, List<Users> userList ) {
		int size = infoList.size();
		int userSize = userList.size();
		List<Map<String, String>> totalUserList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < size;) {
			for (int j = 0; j < userSize; j++) {
				if (i >= size) {
					break;
				}
				String id = infoList.get(i);
				Users user = userList.get(j);
				Map<String, String> map = new HashMap<String, String>();
				map.put(user.getUserName(), id);
				totalUserList.add(map);
				i++;
			}
		}
		return totalUserList;
	}

	/**
	 * 
	 * @title: workDaysAssign
	 * @description: 规则：按工作时间分配
	 *
	 * @param assingRule 规则列表
	 * @param infoList Alert/Case列表
	 * @param userList 用户列表
	 * @param totalInfoList 汇总Alert/Case列表
	 * @date 2017年12月22日 下午5:20:05
	 */
	private void workDaysAssign ( AssignRule assingRule, List<String> infoList, List<Users> userList,
			List<Map<String, String>> totalInfoList ) {
		int totalNum = infoList.size();
		int totalThreshold = 0;
		List<AssignRuleItem> ruleItems = assingRule.getRuleItems();
		for (AssignRuleItem ruleItem : ruleItems) {
			for (int i = 0; i < userList.size(); i++) {
				int days = Integer.valueOf(userList.get(i).getDays());
				if (days >= ruleItem.getStartValue() && days < ruleItem.getEndValue()) {
					ruleItem.setExistUser(true);
					ruleItem.getContainUsers().add(userList.get(i));
				}
			}
			totalThreshold += ruleItem.getThreshold();
		}

		for (AssignRuleItem ruleItem : ruleItems) {
			if (ruleItem.isExistUser()) {
				// alert总记录数为1，随机分配
				if (totalNum == 1) {
					totalInfoList.addAll(this.avgAssign(infoList, ruleItem.getContainUsers()));
					break;
				}
				// 计算每个权重下分配的alert数量(四舍五入)
				long num = Math.round(((ruleItem.getThreshold() * totalNum * 1.0) / totalThreshold));
				if (num > infoList.size()) {
					num = infoList.size();
				}
				List<String> thresholdAlert = infoList.subList(0, Integer.valueOf(String.valueOf(num)));
				List<Users> containUsers = ruleItem.getContainUsers();
				totalInfoList.addAll(this.avgAssign(thresholdAlert, containUsers));
				infoList.removeAll(thresholdAlert);
			}
		}
	}

	/**
	 * 
	 * @title: workAblityAssign
	 * @description: 规则：按工作能力分配(即：按评分分配)
	 *
	 * @param assingRule 规则列表
	 * @param infoList Alert/Case列表
	 * @param scoreList 用户列表
	 * @param totalInfoList 汇总Alert/Case列表
	 * @date 2017年12月22日 下午5:20:05
	 */
	private void workAblityAssign ( AssignRule assingRule, List<String> infoList, List<Users> scoreList,
			List<Map<String, String>> totalInfoList ) {
		int totalNum = infoList.size();
		int totalThreshold = 0;
		List<AssignRuleItem> ruleItems = assingRule.getRuleItems();

		for (AssignRuleItem ruleItem : ruleItems) {
			for (int i = 0; i < scoreList.size(); i++) {
				int score = Integer.valueOf(scoreList.get(i).getScore());
				if (score >= ruleItem.getStartValue() && score < ruleItem.getEndValue()) {
					ruleItem.setExistUser(true);
					ruleItem.getContainUsers().add(scoreList.get(i));
				}
			}
			totalThreshold += ruleItem.getThreshold();
		}

		for (AssignRuleItem ruleItem : ruleItems) {
			if (ruleItem.isExistUser()) {
				// alert/case总记录数为1，随机分配
				if (totalNum == 1) {
					totalInfoList.addAll(this.avgAssign(infoList, ruleItem.getContainUsers()));
					break;
				}
				// 计算每个权重下分配的alert数量(四舍五入)
				long num = Math.round(((ruleItem.getThreshold() * totalNum * 1.0) / totalThreshold));
				List<String> thresholdAlert = infoList.subList(0, Integer.valueOf(String.valueOf(num)));
				List<Users> containUsers = ruleItem.getContainUsers();
				totalInfoList.addAll(this.avgAssign(thresholdAlert, containUsers));
				infoList.removeAll(thresholdAlert);
			}
		}
	}

	/**
	 * 
	 * @title: runAlertFlow
	 * @description: 启动工作流
	 *
	 * @param processId
	 * @param userAlertList
	 * @date 2017年12月22日 下午5:17:49
	 */
	private void runAlertFlow ( String processId, List<Map<String, String>> userAlertList ) {
		/** 启动工作流 */
		for (Map<String, String> map : userAlertList) {
			for (String userName : map.keySet()) {
				String alertId = map.get(userName);
				// 流程数据
				Map<String, Object> args = new HashMap<String, Object>();
				// 默认auto, TODO, 在查询审批列表时，需要过滤这条数据
				args.put("apply.operator", "auto");
				// 当前审批人
				args.put(FlowConsts.ALERT_ANALYST, userName);
				// 实例ID（保存业务Alert ID）
				args.put(SnakerEngine.ID, alertId);
				// 期望完成时间
				args.put("expireTime", DateUtils.parse(DateUtils.getDateTime(30)));
				// 提醒时间
				args.put("reminderTime", DateUtils.parse(DateUtils.getDateTime(23)));
				// 2、启动，创建流程实例，设置过期时间：创建时间 + 30 天
				Order order = facets.startAndExecute(processId, "", args);
				List<Task> tasks = facets.getEngine().query()
						.getActiveTasks(new QueryFilter().setOrderId(order.getId()));
				// 3、修改Alert Group数据表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("alertId", alertId);
				params.put("status", FlowConsts.PENDING_REVIEW);
				params.put("orderId", order.getId());
				params.put("taskId", tasks.get(0).getId());
				params.put("roleId", PropertiesUtil.getValue("Alert.analyst"));
				params.put("roleNum", 1);
				alertGroupMapper.updateFlow(params);
			}
		}
	}

	/**
	 * 
	 * @title: runWaivedAlertFlow
	 * @description: 启动工作流
	 *
	 * @param processId
	 * @param userAlertList
	 * @date 2017年12月22日 下午5:17:49
	 */
	private void runWaivedAlertFlow ( String processId, List<Map<String, String>> userAlertList ) {
		/** 启动工作流 */
		AlertGroup alertGroup = null;
		for (Map<String, String> map : userAlertList) {
			for (String userName : map.keySet()) {
				String alertId = map.get(userName);
				alertGroup = alertGroupMapper.findAlertGroup(Long.valueOf(alertId));
				// 1、根据任务ID， 添加处理人，再删除原任务记录
				facets.getEngine().task().addTaskActor(alertGroup.getTaskId(), userName);
				facets.getEngine().task().removeTaskActor(alertGroup.getTaskId(), "QA.Waived.operator");
				// 2、查询下一节点的流程状态ID
				String flowId = PropertiesUtil.getValue("processId.alert");
				WfBusStatus busStatus = wfBusStatusMapper.findStatus(flowId, alertGroup.getStatus(), null,
						FlowConsts.WAIVE);

				// 3、修改Alert Group数据表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("alertId", alertId);
				params.put("status", busStatus == null ? "" : busStatus.getDicCode());
				params.put("preStatus", "");
				params.put("roleId", PropertiesUtil.getValue("Alert.QA.Waived"));
				alertGroupMapper.updateFlow(params);
			}
		}
	}

	/**
	 * 
	 * @title: runSarFlow
	 * @description: 启动工作流
	 *
	 * @param processId
	 * @param userSarList
	 * @date 2017年12月22日 下午5:17:49
	 */
	private void runSarFlow ( String processId, List<Map<String, String>> userSarList ) {
		/** 启动工作流 */
		for (Map<String, String> map : userSarList) {
			for (String userName : map.keySet()) {
				String caseId = map.get(userName);
				// 流程数据
				Map<String, Object> args = new HashMap<String, Object>();
				// 默认auto, TODO, 在查询审批列表时，需要过滤这条数据
				args.put("apply.operator", "auto");
				// 当前审批人
				args.put(FlowConsts.SAR_ANALYST, userName);
				// 实例ID（保存业务Case ID）
				args.put(SnakerEngine.ID, caseId);
				// 期望完成时间
				args.put("expireTime", DateUtils.parse(DateUtils.getDateTime(30)));
				// 提醒时间
				args.put("reminderTime", DateUtils.parse(DateUtils.getDateTime(23)));
				// 2、启动，创建流程实例
				Order order = facets.startAndExecute(processId, "", args);
				List<Task> tasks = facets.getEngine().query()
						.getActiveTasks(new QueryFilter().setOrderId(order.getId()));
				// 3、修改Case Group数据表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("caseId", caseId);
				params.put("status", FlowConsts.PENDING_INVESTIGATION);
				params.put("orderId", order.getId());
				params.put("taskId", tasks.get(0).getId());
				params.put("roleId", PropertiesUtil.getValue("Sar.analyst"));
				caseGroupMapper.updateFlow(params);
			}
		}
	}

}
