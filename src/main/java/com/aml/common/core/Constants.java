package com.aml.common.core;

/**
 * 常用常量类
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class Constants {
	/** 用户 token redis的key */
	public static final String REDIS_KEY_TOKEN_USER = "user:";
	/** 定时器请求UMS */
	public static final String REDIS_KEY_TIMER = "timer:";

	/** AML用户权限 */
	public static final String REDIS_KEY_AML_USER_MENU = "userMenu:";
	
	/** AML所有权限*/
	public static final String REDIS_KEY_AML_ALL_MENU = "allMenu:";
	
	/** AML用户信息*/
	public static final String REDIS_KEY_AML_USER_INFO = "userInfo:";
}
