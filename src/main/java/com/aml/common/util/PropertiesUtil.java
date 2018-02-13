package com.aml.common.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @className: PropertiesUtil
 * @description: 启动时, 加载属性文件，在工具类中使用
 * @author huangliangbao
 * @date 2017年11月14日 上午11:27:06
 *
 */
public class PropertiesUtil {
	private static Properties prop = new Properties();
	private static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

	private PropertiesUtil() {
	}

	static {
		InputStream html = null;
		try {
			html = PropertiesUtil.class.getResourceAsStream("/aml.properties");
			prop.load(html);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (html != null) {
				try {
					html.close();
				} catch (Exception e1) {
					LOG.error(e1.getMessage(), e1);
				}
			}
		}
	}

	public static String getValue ( String key ) {
		return prop.getProperty(key);
	}

}
