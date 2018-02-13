package com.aml.common.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;

import com.aml.common.core.DateConsts;

/**
 * 
 * @className: StrUtils
 * @description: 字符常量工具类
 * @author huangliangbao
 * @date 2018年1月8日 上午11:45:31
 *
 */
public class StrUtils {
	public static final char UNDERLINE = '_';
	public static final char SPACE = ' ';

	/**
	 * 
	 * @title: getPrimaryKey
	 * @description: 生成Alert/Case 主键（命名规则：yyyyMMdd + 6位有序数）
	 *
	 * @param preKey 原主键ID
	 * @return Long 新主键ID
	 * @date 2018年1月8日 上午11:39:22
	 */
	public static Long getPrimaryKey ( Long preKey ) {
		String time = DateUtils.getDateTime(DateConsts.D_DEFAULT_STR);
		if (ObjectUtils.isEmpty(preKey)) {
			return Long.valueOf(time + "000001");
		} else {
			String newKey = String.valueOf(preKey + 1);
			String num = newKey.substring(8, newKey.length());
			return Long.valueOf(time + num);
		}
	}

	/**
	 * 
	 * @title: getControl
	 * @description: 生成发送RFI 主键
	 *
	 * @return String
	 * @date 2018年1月24日 下午3:36:09
	 */
	public static String getControl () {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 
	 * @title: getRandomNum
	 * @description: 产生指定长度的随机数.
	 *
	 * @param length 长度
	 * @return int
	 * @date 2018年1月8日 上午9:38:37
	 */
	public static int getRandomNum ( int length ) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) (random * num);
	}

	/**
	 * 
	 * @title: camelToSpace
	 * @description: 驼峰格式字符串转换为空格格式字符串
	 *
	 * @param param 驼峰字符串
	 * @return String 空格格式字符串
	 * @date 2018年1月13日 下午4:16:19
	 */
	public static String camelToSpace ( String param ) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @title: spaceToCamel
	 * @description: 空格格式字符串转换为驼峰格式字符串
	 *
	 * @param param 空格格式字符串
	 * @return String 驼峰格式字符串
	 * @date 2018年1月13日 下午4:17:11
	 */
	public static String spaceToCamel ( String param ) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = 0;
			if (i == 0) {
				c = Character.toLowerCase(param.charAt(i));
			} else {
				c = param.charAt(i);
			}
			if (c == SPACE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @title: underlineToCamel
	 * @description: 下划线格式字符串转换为驼峰格式字符串
	 *
	 * @param param 下划线格式字符串
	 * @return String 驼峰格式字符串
	 * @date 2018年1月13日 下午4:17:11
	 */
	public static String underlineToCamel ( String param ) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 
	 * @title: strLength
	 * @description: 计算字符串的长度，一个汉字长度为2,英文字符长度为1
	 *
	 * @param str 字符串
	 * @return int 长度
	 * @date 2018年1月17日 下午5:45:45
	 */
	public static int strLength ( String str ) {
		char[] c = str.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	public static boolean isLetter ( char c ) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	public static void main ( String[] args ) throws Exception {
		String aaa = "Create Date";
		String bbb = "create_date";
		System.out.println(spaceToCamel(aaa));
		System.out.println(underlineToCamel(bbb));
		aaa = "appVersionFld";
		System.out.println(camelToSpace(aaa));
		System.out.println(getPrimaryKey(20180108001254L));
	}
}
