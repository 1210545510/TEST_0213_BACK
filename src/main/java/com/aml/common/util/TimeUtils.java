package com.aml.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * 
 * @author zouwei
 * @since 2017年5月31日
 */
public class TimeUtils {

	/**
	 * 把时间添加结束时分秒 （23:59:59）
	 * 
	 * 如 2017-05-31转成 2017-05-31 23:59:59
	 * 
	 * @param time
	 *        (参数是String类型)
	 * @return String
	 */
	public static String endTime ( String time ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		Date endDate = new Date();
		try {
			endDate = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 23：59：59
		Calendar c2 = Calendar.getInstance();
		c2.setTime(endDate);
		c2.add(Calendar.DATE, -0);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = s.format(c2.getTime());
		return endTime;
	}

	/**
	 * 把时间添加开始时分秒（00:00:00）
	 * 
	 * 如 2017-05-31转成 2017-05-31 00:00:00
	 * 
	 * @param time
	 *        (参数是String类型)
	 * @return String
	 */
	public static String startTime ( String time ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		Date startDate = new Date();
		try {
			startDate = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		c1.add(Calendar.DATE, -0);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = s.format(c1.getTime());
		return startTime;
	}

	/**
	 * 把Date转成以23:59:59结尾的String
	 * 
	 * 
	 * 
	 * @param time
	 *        (参数是Date类型)
	 * @return String
	 */
	public static String endTime ( Date time ) {
		// 23：59：59
		Calendar c2 = Calendar.getInstance();
		c2.setTime(time);
		c2.add(Calendar.DATE, -0);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = s.format(c2.getTime());
		return endTime;
	}

	/**
	 * 把Date转成以00:00:00开头的String
	 * 
	 * @param time
	 *        (参数是Date类型)
	 * @return String
	 */
	public static String startTime ( Date time ) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(time);
		c1.add(Calendar.DATE, -0);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = s.format(c1.getTime());
		return startTime;
	}
	
	/**
	 * 把Date转成以23:59:59结尾的String
	 * 
	 * 
	 * 
	 * @param time
	 *        (参数是Date类型)
	 * @return Date
	 */
	public static Date getEndTime ( Date time ) {
		// 23：59：59
		Calendar c2 = Calendar.getInstance();
		c2.setTime(time);
		c2.add(Calendar.DATE, -0);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 0);
		return c2.getTime();
	}

	/**
	 * 把Date转成以00:00:00开头的String
	 * 
	 * @param time
	 *        (参数是Date类型)
	 * @return Date
	 */
	public static Date getStartTime ( Date time ) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(time);
		c1.add(Calendar.DATE, -0);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		return c1.getTime();
	}

	/**
	 * 获取当前日期yyyy-MM-dd
	 * 
	 * @param time
	 *        (参数是Date类型)
	 * @return String
	 */
	public static String getNowDate ( Date time ) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		return s.format(time);
	}

	/**
	 * 获取当前时间HH:mm:ss
	 * 
	 * @param time
	 *        (参数是Date类型)
	 * @return String
	 */
	public static String getNowTime ( Date time ) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		String nowTime = hour + ":" + minute + ":" + second;
		return nowTime;
	}

	public static void main ( String[] args ) throws Exception {
		Date d = new Date();
		String date = "2017-06-29 11:30:35";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		d = sdf.parse(date);

		System.out.println(getNowTime(d));
	}
}
