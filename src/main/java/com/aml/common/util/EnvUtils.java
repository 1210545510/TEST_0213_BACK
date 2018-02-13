package com.aml.common.util;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 环境工具类
 * 
 * <strong>env.properties</strong> is must put in th root CLASSPATH<br>
 * <code> 
 * isProEnv = false</code>
 * 
 * @author zouwei
 * @since 2017年5月3日
 */
public class EnvUtils {
	/** 获取env.properties */
	public static final Properties envPro;
	/** 获取core.properties */
	public static final Properties corePro;

	private static boolean proEnv;

	static {
		// 静态获取配置信息中的环境
		envPro = new Properties();
		corePro = new Properties();
		proEnv = false; // 是否正式环境
		try {
			envPro.load(new InputStreamReader(EnvUtils.class.getClassLoader().getResourceAsStream("env.properties"),
					"UTF-8"));
			corePro.load(new InputStreamReader(EnvUtils.class.getClassLoader().getResourceAsStream("core.properties"),
					"UTF-8"));
		} catch (Exception e) {
		}

		if (!envPro.isEmpty()) {
			String isProEnvStr = envPro.getProperty("isProEnv");
			try {
				proEnv = Boolean.parseBoolean(isProEnvStr);
			} catch (Exception e) {

			}
		}
	}

	/**
	 * 获取是否正式环境
	 * 
	 * @return
	 */
	public static boolean isProEnv() {
		return proEnv;
	}

	public static void setProEnv(boolean proEnv) {
		EnvUtils.proEnv = proEnv;
	}

}
