/*
 * Copyright 2014-2015 snakerflow.com
 * *
 * * Licensed under the Apache License, Version 2.0 (the "License");
 * * you may not use this file except in compliance with the License.
 * * You may obtain a copy of the License at
 * *
 * * http://www.apache.org/licenses/LICENSE-2.0
 * *
 * * Unless required by applicable law or agreed to in writing, software
 * * distributed under the License is distributed on an "AS IS" BASIS,
 * * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * * See the License for the specific language governing permissions and
 * * limitations under the License.
 */
package com.aml.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.aml.common.core.DateConsts;

/**
 * 
 * @className: DateUtils
 * @description: 日期工具类
 * @author huangliangbao
 * @date 2017年12月7日 下午3:27:47
 *
 */
public class DateUtils {

	/**
	 * 
	 * @title: getCurrentTime
	 * @description: 获取当前时间（年-月-日 时:分:秒）
	 *
	 * @return String
	 * @date 2017年12月7日 下午3:29:29
	 */
	public static String getCurrentTime () {
		return new DateTime().toString(DateConsts.D_FULL);
	}

	/**
	 * 
	 * @title: getDateTime
	 * @description: 获取某天的时间, 返回日期格式：（年-月-日）
	 * 
	 * @param num 整数 (负数 表示前几天；零 表示当天；正数 表示后几天)，如：-1
	 * @return String
	 * @date 2017年12月7日 下午3:29:29
	 */
	public final static String getDateTime ( int num ) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, num); // 得到前/后几天的时间
		long datetime = cal.getTimeInMillis();
		synchronized (DateConsts.FM_D_DEFAULT) {
			return DateConsts.FM_D_DEFAULT.format(new Date(datetime));
		}
	}

	/**
	 * 
	 * @title: getDateTime
	 * @description: 按指定格式，获取当前时间
	 *
	 * @param pattern 日期格式
	 * @return String
	 * @date 2018年1月8日 上午9:35:31
	 */
	public static String getDateTime ( String pattern ) {
		if (StringUtils.isBlank(pattern)) {
			return getDateTime(0);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		synchronized (format) {
			return format.format(new Date());
		}
	}

	/**
	 * 
	 * @title: parse
	 * @description: 将日期字符串转换为Date对象
	 * 
	 * @param dateTime 日期字符串
	 * @return Date
	 * @throws ParseException
	 * @date 2017年12月7日 下午3:29:29
	 */
	public static Date parse ( String dateTime ) {
		try {
			return DateConsts.FM_D_DEFAULT.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 
	 * @title: daysBetween
	 * @description: 计算两个日期之间相差的天数
	 *
	 * @param smdate 开始时间
	 * @param bdate 结束时间
	 * @return int
	 * @throws ParseException
	 * @date 2017年12月25日 上午10:03:37
	 */
	public static int daysBetween ( Date smdate, Date bdate ) {
		try {
			SimpleDateFormat sdf = DateConsts.FM_D_DEFAULT;
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 
	 * @title: daysBetween
	 * @description: 计算两个日期字符串相差的天数
	 *
	 * @param smdate 开始时间
	 * @param bdate 结束时间
	 * @return int
	 * @throws ParseException
	 * @date 2017年12月25日 上午10:01:45
	 */
	public static int daysBetween ( String smdate, String bdate ) throws ParseException {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateConsts.FM_D_DEFAULT.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(DateConsts.FM_D_DEFAULT.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static void main ( String[] args ) throws ParseException {
		System.out.println(DateUtils.getCurrentTime());
		System.out.println(DateUtils.getDateTime(30));
		System.out.println(DateUtils.parse(DateUtils.getDateTime(23)));
		System.out.println(DateUtils.parse("2017-12-22"));
		System.out.println(DateUtils.daysBetween("2017-12-21 12:21:00", "2018-01-21 08:09:23"));
	}
}
