package com.aml.common.core;

import java.text.SimpleDateFormat;

/**
 * 
 * @className: DateConsts
 * @description: 日期格式 常量
 * @author huangliangbao
 * @date 2017年12月7日 下午3:27:24
 *
 */
public final class DateConsts {
	/** 年-月-日 */
	public static final String D_DEFAULT = "yyyy-MM-dd";
	/** 年-月-日 时:分:秒 */
	public static final String D_FULL = "yyyy-MM-dd HH:mm:ss";
	/** 年月日 */
	public static final String D_DEFAULT_STR = "yyyyMMdd";
	/** 时:分:秒 */
	public static final String T_HHMMSS = "HH:mm:ss";
	/** 时:分 */
	public static final String T_HHMM = "HH:mm";

	public static final SimpleDateFormat FM_D_DEFAULT = new SimpleDateFormat(D_DEFAULT);
	public static final SimpleDateFormat FM_D_FULL = new SimpleDateFormat(D_FULL);
}
