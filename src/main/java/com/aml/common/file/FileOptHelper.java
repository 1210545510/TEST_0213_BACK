package com.aml.common.file;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 文件操作帮助类
 * 
 * @author zouwei
 * @since 2017年5月4日
 */
public class FileOptHelper {

	/** 标题行名称，对应数据库字段名 */
	public static final String COLUMN_NAME = "name";
	/** 标题名称 */
	public static final String COLUMN_COMMENT = "comment";

	/**
	 * 设置Excel标题样式
	 * 
	 * @return
	 */
	public static CellStyle getExcelTitleCellStyle(Workbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		// font.setFontHeightInPoints((short)10); //字体大小
		// font.setFontName("黑体");
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
		cellStyle.setFont(font);
		return cellStyle;
	}

	/**
	 * 通过传入的数组构建excel title集合 {"itemname","商品名称"},{"propertyname","辅助属性"}
	 * 
	 * @param addColumns
	 * @return
	 */
	public static List<Map<String, String>> createDisplayColumn(String[]... addColumns) {
		List<Map<String, String>> titleList = new LinkedList<>();
		for (String[] tempColumn : addColumns) {
			// 判断是否传入了key 和 value
			if (tempColumn.length != 2) {
				continue;
			}
			Map<String, String> title = new HashMap<>();
			// COLUMN_NAME COLUMN_COMMENT
			title.put(COLUMN_NAME, tempColumn[0]);
			title.put(COLUMN_COMMENT, tempColumn[1]);
			titleList.add(title);
		}
		return titleList;
	}

	/**
	 * 获取最终导出的文件名（原来文件名称加上时间戳后缀 + 转码）
	 * 
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static String getExportFileName(String fileName, String suffix) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(now).toString();
		String s = fileName + "_" + date + "." + suffix;
		try {
			s = new String(s.getBytes("utf-8"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
		}
		return s;
	}
}
