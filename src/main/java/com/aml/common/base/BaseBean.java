package com.aml.common.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.aml.common.util.XmlUtils;
import com.thoughtworks.xstream.XStream;

/**
 * BaseBean
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public abstract class BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 转成json字符串
	 * 
	 * @return
	 */
	public String toJson () {
		return JSON.toJSONString(this);
	}

	/**
	 * 转成XML字符串
	 * 
	 * @return
	 */
	public String toCdataXml () {
		XStream xstream = XmlUtils.getXstream();
		xstream.alias("xml", this.getClass());
		return xstream.toXML(this);
	}

	public String toXml () {
		XStream xstream = new XStream();
		xstream.alias("xml", this.getClass());
		return xstream.toXML(this);
	}
}
