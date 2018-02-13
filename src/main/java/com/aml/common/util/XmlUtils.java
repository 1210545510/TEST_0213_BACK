package com.aml.common.util;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * XmlUtils
 * 
 * @author zouwei
 * @since 2017年5月24日
 */
@SuppressWarnings("unchecked")
public class XmlUtils {
	public static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);

	/**
	 * XML转MAP
	 * 
	 * @param msg
	 * @return
	 */
	public static Map<String, String> parseXml2Map ( Document document ) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();
			// 遍历所有子节点
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			logger.error("XML转MAP发生错误，错误信息为: {}", e.getMessage(), e);
		}
		return map;
	}

	public static XStream getXstream () {
		return xstream;
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 */
	private static final XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter ( Writer out ) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@Override
				protected void writeText ( QuickWriter writer, String text ) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}

				@Override
				@SuppressWarnings("rawtypes")
				public void startNode ( String name, Class clazz ) {
					super.startNode(name, clazz);
				}

				@Override
				public String encodeNode ( String name ) {
					return name;
				}
			};
		}
	});

}
