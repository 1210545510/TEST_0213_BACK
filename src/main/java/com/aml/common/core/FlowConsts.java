package com.aml.common.core;

/**
 * 
 * @className: FlowConsts
 * @description: 工作流常量
 * @author huangliangbao
 * @date 2017年11月23日 上午10:21:01
 *
 */
public class FlowConsts {
	/** 模块类型 */
	public static final String TYPE_ALERT = "alert";
	public static final String TYPE_SAR = "sar";
	public static final String TYPE_CTR = "ctr";
	
	/** 是否锁定(0-未锁定, 1-锁定) */
	public static final int UNLOCK = 0;
	public static final int LOCK = 1;
	
	/** 是否为业务操作(0-否; 1-是) */
	public static final int IS_NOT_APPROVE = 0;
	public static final int IS_APPROVE = 1;

	/** Alert Processing result（Waive，Escalate） */
	public static final String ESCALATE = "Escalate";
	public static final String WAIVE = "Waive";
	public static final String ESCALATE_ZH = "1";
	public static final String WAIVE_ZH = "0";

	/** Sar Processing result（Reasonable，SAR） */
	public static final String REASONABLE = "Reasonable";
	public static final String SAR = "SAR";

	/** 同意/不同意 */
	public static final String AGREE = "Agree";
	public static final String DISAGREE = "Disagree";
	public static final String AGREE_ZH = "1";
	public static final String DISAGREE_ZH = "0";
	public static final String AGREE_EN = "Y";
	public static final String DISAGREE_EN = "N";

	/** Alert 流程状态ID (待查看，已查看, Waived,QA驳回已查看, 已完成) */
	public static final int PENDING_REVIEW = 2;
	public static final int UNDER_REVIEW = 1;
	public static final int WAIVED = 3;
	public static final int ESCALATED_UNDER_RE_WORK = 13;
	public static final int FINISH_DATE = 1;

	/** Alert analyst 小组 */
	public static final String TEAM_FIU = "FIU";
	public static final String TEAM_FCB = "FCB";
	public static final String TEAM_DCT = "DCT";
	public static final String TEAM_CTR = "CTR";

	/** 决策状态 */
	public static final int STATUS_AGREE = 1;
	public static final int STATUS_DISAGREE = 2;
	public static final int STATUS_REVIEW = 3;

	/** Alert 节点处理人参数 */
	public static final String ALERT_ANALYST = "Analyst.operator";
	public static final String ALERT_QA_WAIVED = "QA.Waived.operator";
	public static final String ALERT_QA_ESCALATED = "QA.Escalated.operator";
	public static final String ALERT_SUPERVISOR = "Supervisor.operator";

	/** Sar 节点处理人参数 */
	public static final String SAR_ANALYST = "SarAnalyst.operator";
	public static final String SAR_QA = "SarQA.operator";
	public static final String SAR_OFFICER = "Officer.operator";
	public static final String SAR_COORDINATOR = "Coordinator.operator";
	public static final String SAR_NARRATIVE = "Narrative.operator";

	/** Sar 流程状态ID */
	public static final int PENDING_INVESTIGATION = 1;
	public static final int UNDER_INVESTIGATION = 2;
	public static final int SAR_UNDER_BSA_OFFICER = 9;
	public static final int REASONABLE_UNDER_BSA_OFFICER = 17;

	/** Alert/Sar 任务名称 */
	public static final String TASK_ALERT_QA_WAIVED = "approveQA_Waived";
	public static final String TASK_ALERT_ANALYST = "approveAnalyst";
	public static final String TASK_SAR_ANALYST = "approveAnalyst";
	public static final String TASK_SAR_COORDINATOR = "approveCoordinator";

	/** Alert/Sar 待查看状态 */
	public static final String FLOW_ALERT_PENDING_STATUS = "2,4,7,10,12,14";
	public static final String FLOW_SAR_PENDING_STATUS = "1,3,4,7,8,12,13,16,18,20,21";

}
