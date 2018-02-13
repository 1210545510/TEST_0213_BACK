package com.aml.common.core;

/**
 * 
 * @className: ConfigConsts
 * @description: 系统设置常量
 * @author huangliangbao
 * @date 2017年11月23日 上午10:21:01
 *
 */
public class ConfigConsts {
	/** 规则类型(1-平均分配; 2-工作时间; 3-工作能力) */
	public static final int RULE_TYPE_AVG = 1;
	public static final int RULE_TYPE_WORKHOUR = 2;
	public static final int RULE_TYPE_WORKABILITY = 3;

	/** 登录状态（0-启用; 1-禁用 ） */
	public static final int LOGIN_STATUS_ENABLE = 0;
	public static final int LOGIN_STATUS_DISABLE = 1;

	/** 导出Excel格式 */
	public static final String EXCEL_CSV = "csv";
	
	/** 导出Excel的名称 */
	public static final String E_ALERT_LIST_NAME = "AlertListExcel";
	public static final String E_CASE_LIST_NAME = "CaseListExcel";
	public static final String E_CLIENT_BANK_NAME = "ClientBankExcel";
	public static final String E_EXT_USER_REQUEST_NAME = "ExtUserRequestExcel";
	public static final String E_CUSTOMER_PROFILE_NAME = "CustExcel";
	public static final String E_314A_NAME = "Data314AExcel";
	public static final String E_GRAND_JURY_SUB_NAME = "GrandJurySubpoenaExcel";
	public static final String E_ALERT_HISTORY_NAME = "AlertHistoryExcel";
	public static final String E_CTR_HISTORY_NAME = "CTRHistoryExcel";
	public static final String E_CASE_HISTORY_NAME = "CaseHistoryExcel";
	public static final String E_SAR_HISTORY_NAME = "SarHistoryExcel";
	

}
