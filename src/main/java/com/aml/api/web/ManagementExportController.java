package com.aml.api.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.service.AlertService;
import com.aml.api.service.CaseService;
import com.aml.common.base.BaseController;
import com.aml.common.file.FileOptHelper;
import com.aml.common.file.export.ReportExcelExporter;

/**
 * Management query
 * 
 * @author Novan
 * @date 2017-12-8
 *
 */
@RestController
public class ManagementExportController extends BaseController {
	@Autowired
	private AlertService alertService;
	@Autowired
	private CaseService caseService;
	
	/**
	 * AlertInfoTotal List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downAlertInfoTotalByMonthExcel", method = RequestMethod.POST)
	public void downAlertInfoTotalByMonthExcel(AlertInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.downAlertInfoTotalByMonth(dto);
		String fileName = "The total number of alerts reviewed Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * CaseInfoTotal List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downCaseInfoTotalByMonthExcel", method = RequestMethod.POST)
	public void downCaseInfoTotalByMonthExcel(CaseInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.downCaseInfoTotalByMonth(dto);
		String fileName = "The total number of cases reviewed Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "month", "month" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	
	/**
	 * The Amount total by month alert export
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 */
	@RequestMapping(value = "/getAlertAmountByMonthExcel", method = RequestMethod.POST)
	public void getAlertAmountByMonthExport(AlertInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.getAlertAmountByMonthExport(dto);
		String fileName = "The total Amount of alerts reviewed Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * The Amount total by month alert export
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 */
	@RequestMapping(value = "/getAlertVolumeByMonthExcel", method = RequestMethod.POST)
	public void getAlertVolumeByMonthExport(AlertInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.getAlertVolumeByMonthExport(dto);
		String fileName = "The total Volume of alerts reviewed Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	
	/**
	 * CaseInfoTotal List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getCaseAmountByMonthExcel", method = RequestMethod.POST)
	public void getCaseAmountByMonthExcel(CaseInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.getCaseAmountByMonthExcel(dto);
		String fileName = "The total Amount of cases reviewed Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "month", "month" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * CaseInfoTotal List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getCaseVolumeByMonthExcel", method = RequestMethod.POST)
	public void getCaseVolumeByMonthExcel(CaseInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.getCaseVolumeByMonthExcel(dto);
		String fileName = "The total Volume of cases reviewed Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "month", "month" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * The Amount total by month alert export
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 */
	@RequestMapping(value = "/getAlertReviewTimeExcel", method = RequestMethod.POST)
	public void getAlertReviewTimeExcel(AlertInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.getAlertReviewTimeExcel(dto);
		String fileName = "AlertReviewTime Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	
	/**
	 * CaseInfoTotal List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getCaseReviewTimeExcel", method = RequestMethod.POST)
	public void getCaseReviewTimeExcel(CaseInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.getCaseReviewTimeExcel(dto);
		String fileName = "CaseReviewTime Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "month", "month" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * The Amount total by month alert export
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 */
	@RequestMapping(value = "/getAlertReviewTimeQAExcel", method = RequestMethod.POST)
	public void getAlertReviewTimeQAExcel(AlertInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.getAlertReviewTimeQAExcel(dto);
		String fileName = "AlertReviewTimeQA Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * CaseInfoTotal List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getCaseReviewTimeQAExcel", method = RequestMethod.POST)
	public void getCaseReviewTimeQAExcel(CaseInfoDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.getCaseReviewTimeQAExcel(dto);
		String fileName = "CaseReviewTimeQA Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "month", "month" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	
	
	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getReversedAlertExcel", method = RequestMethod.POST)
	public void getReversedAlertExcel(AlertInfoDto dto, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.getReversedExcel(dto);
		String fileName = "Percentage of alert reviewed reversed by QA Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * The total number of productive alert escalated each month Excel
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getEscalatedAlertExcel", method = RequestMethod.POST)
	public void getEscalatedAlertExcel(AlertInfoDto dto, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = alertService.getEscalatedExcel(dto);
		String fileName = "The total number of productive alert escalated each month Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getReversedCaseExcel", method = RequestMethod.POST)
	public void getReversedCaseExcel(CaseInfoDto dto, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.getReversedExcel(dto);
		String fileName = "Percentage of alert reviewed reversed by QA Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * The total number of productive alert escalated each month Excel
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getEscalatedCaseExcel", method = RequestMethod.POST)
	public void getEscalatedCaseExcel(CaseInfoDto dto, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = caseService.getEscalatedExcel(dto);
		String fileName = "The total number of productive alert escalated each month Excel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "year", "Year" },
			{ "January", "January" },
			{ "February", "February" },
			{ "March", "March" },
			{ "April", "April" },
			{ "May", "May" },
			{ "June", "June" },
			{ "July", "July" },
			{ "August", "August" },
			{ "September", "September" },
			{ "October", "October" },
			{ "November", "November" },
			{ "December", "December" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
//	/**
//	 * The total number of  alert for XX review
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertReview", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertInfo> getAlertReview(AlertInfoDto dto, HttpServletRequest request) {
//		ApiPageResult<AlertInfo> result=new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertInfo> alertInfo = alertService.getAlertReview(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
	
}
