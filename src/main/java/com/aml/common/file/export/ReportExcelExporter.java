package com.aml.common.file.export;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aml.common.file.FileOptHelper;
import com.aml.common.util.TypeCaseHelper;

/**
 * excel平台统一导出入口 后续如果需要分批导出，可以实现该类
 ******这个类在导出数据运算用到List<Map<String, Object>>   /Novan 订单报表
 * @author 
 * @since 
 */
public class ReportExcelExporter {

	protected static final Logger logger = LoggerFactory.getLogger(ReportExcelExporter.class);

	protected XSSFWorkbook workbook;
	protected String fileName;
	// 标题行
	protected List<Map<String, String>> titleList;

	// 导出的数据（数据库一次性查询完），将来实现分批导出，可以分批查出
	private List<Map<String, Object>> exportData;

	/**
	 * 数据导出唯一入口（构造方法形式）
	 * 
	 * @param response
	 * @param fileName
	 * @param exportData
	 * @param titleList
	 */
	public ReportExcelExporter(HttpServletResponse response, String fileName, List<Map<String, Object>> exportData,
			List<Map<String, String>> titleList,String suffix) {
		logger.info("********************************************");
		logger.info("开始进行excel导出, {}...", fileName);
		if (exportData != null && titleList != null) {
			this.workbook = new XSSFWorkbook();
			this.fileName = fileName;
			this.exportData = exportData;
			this.titleList = titleList;
			Sheet sheet = workbook.createSheet(fileName);
			productSheet(sheet);
			executeExport(response, suffix);
		}
		logger.info("excel导出结束...");
		logger.info("********************************************");
	}

	/**
	 * 生成excel sheet
	 * 
	 * @param sheet
	 */
	private void productSheet(Sheet sheet) {
		setTitleRow(sheet);
		for (int i = 0; i < exportData.size(); i++) {
			Map<String, ?> data = exportData.get(i);
			Row row = sheet.createRow(i + 1);
			fillingRowData(data, row);
		}
	}

	/**
	 * 填充一行数据
	 * 
	 * @param rowData
	 * @param titleList
	 */
	protected void fillingRowData(Map<String, ?> data, Row row) {
		for (int i = 0; i < titleList.size(); i++) {
			Map<String, String> title = titleList.get(i);
			String titleName = title.get(FileOptHelper.COLUMN_NAME); // 数据库字段名称
			String value = TypeCaseHelper.getAsString(data.get(titleName));
			Cell cell = row.createCell(i);
			cell.setCellValue(value);
		}
	}

	/**
	 * 设置标题行信息
	 * 
	 * @param sheet
	 * @param titleList
	 * @param entrytitleList
	 */
	protected void setTitleRow(Sheet sheet) {
		Row row = sheet.createRow(0);
		// 遍历所有标题，并且将其放入到Excel内
		for (int i = 0; i < titleList.size(); i++) {
			Cell tempCell = row.createCell(i);
			Map<String, String> title = titleList.get(i);
			String columnComment = title.get(FileOptHelper.COLUMN_COMMENT); // 字段名称对应的标题名称
			String columnName = title.get(FileOptHelper.COLUMN_NAME); // 数据库字段名称
			if (StringUtils.isBlank(columnComment)) {
				tempCell.setCellValue(columnName);
			} else {
				tempCell.setCellValue(columnComment);
			}
			tempCell.setCellStyle(FileOptHelper.getExcelTitleCellStyle(workbook));
			sheet.setColumnWidth(i, 6000);
		}
	}

	/**
	 * 执行导出下载
	 */
	private void executeExport(HttpServletResponse response,String suffix) {
		if(suffix == null){
			suffix = "xlsx";
		}
		ByteArrayOutputStream buffer = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			buffer = new ByteArrayOutputStream();
			workbook.write(buffer);
			buffer.flush();

			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(buffer.size());
			response.setHeader("Content-Disposition",
					"attachment; filename=" + FileOptHelper.getExportFileName(fileName, suffix));
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");

			out.write(buffer.toByteArray());
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(buffer);
			IOUtils.closeQuietly(out);
		}
	}
}
