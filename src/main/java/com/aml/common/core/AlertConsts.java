package com.aml.common.core;

/**
 * 
 * @className: ConfigConsts
 * @description: Alert 常量
 * @author huangliangbao
 * @date 2017年11月23日 上午10:21:01
 *
 */
public class AlertConsts {
	/** 系统来源(mantas/prime/aml) */
	public static final String SYS_TYPE_MANTAS = "mantas";
	public static final String SYS_TYPE_PRIME = "prime";
	public static final String SYS_TYPE_AML = "aml";
	/** 数据来源，0-自动生成;1-External User Request;2-Grand Jury Subpoena;3-314A; 4:-手动创建Alert; 5-手动创建的case */
	public static final int DATA_TYPE_ZERO = 0;
	public static final int DATA_TYPE_ONE = 1;
	public static final int DATA_TYPE_TWO = 2;
	public static final int DATA_TYPE_THREE = 3;
	public static final int DATA_TYPE_FOUR = 4;
	public static final int DATA_TYPE_FIVE = 5;
	/** 是否为管理员创建（0-不是；1-是） */
	public static final int CREATE_IS_ADMIN = 1;
	public static final int CREATE_IS_NOT_ADMIN = 0;
	/** 关注类型 */
	public static final String FOCUS_TYPE_CU = "CU";
	public static final String FOCUS_TYPE_AC = "AC";
	/** Alert type */
	public static final String ALERT_TYPE_AML = "1";
	public static final String ALERT_TYPE_CTR = "2";
	/** 状态(0-待办；1-已处理) */
	public static final int STATE_TODO = 0;
	public static final int STATE_REVIEWED = 1;

}
