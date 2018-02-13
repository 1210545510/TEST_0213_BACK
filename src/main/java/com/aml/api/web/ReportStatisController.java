package com.aml.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.ReportStatisCustomersDto;
import com.aml.api.dto.ReportStatisGroupSarDto;
import com.aml.api.dto.ReportStatisPercentDto;
import com.aml.api.dto.ReportStatisTotalDto;
import com.aml.api.service.ReportStatisService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.EchartsTitleConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.util.PageUtils;
import com.github.pagehelper.Page;

/**
 * 每日统计报表数据
 * @author Novan
 * @date 2018-01-22
 *
 */
@RestController
public class ReportStatisController extends BaseController {
	
	@Autowired
	ReportStatisService reportStatisService;
	
	/**
	 * Trend of alerts generated, cases created, and SARs filed 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getTrend", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getTrend(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		dto.setStr("sar");
		List<Map<String, Object>> alertTrend = reportStatisService.getTrend(dto); 
		dto.setStr("case");
		List<Map<String, Object>> caseTrend = reportStatisService.getTrend(dto); 
		dto.setStr("alert");
		List<Map<String, Object>> sarTrend = reportStatisService.getTrend(dto); 
		
		String[] title = new String[] { EchartsTitleConsts.SAR, EchartsTitleConsts.CASE, EchartsTitleConsts.ALERTS };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(alertTrend);
		data.add(caseTrend);
		data.add(sarTrend);
		map.put("list", data);
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * Ratios of productive alerts/ SAR yield rate 柱状图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getYield", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getYield(ReportStatisPercentDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		dto.setStr("ratios");
		List<Map<String, Object>> ratiosYield = reportStatisService.getYield(dto); 
		dto.setStr("sar");
		List<Map<String, Object>> sarYield = reportStatisService.getYield(dto);
		
		String[] title = new String[] { EchartsTitleConsts.RATIOS_OF_PRODUCTIVE_ALERTS, EchartsTitleConsts.SAR_YIELD_RATE };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(ratiosYield);
		data.add(sarYield);
		map.put("list", data);
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * Total number trend 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getTrendTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getTrendTotal(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		dto.setStr("qa");
		List<Map<String, Object>> qaTotal = reportStatisService.getTrendTotal(dto); 
		dto.setStr("analysts");
		List<Map<String, Object>> analystsTotal = reportStatisService.getTrendTotal(dto); 
		dto.setStr("team");
		List<Map<String, Object>> teamTotal = reportStatisService.getTrendTotal(dto); 
		
		String[] title = new String[] { EchartsTitleConsts.TOTAL_NUMBER_OF_DRAFTED_CASES_PENDING_SAR_QA_REVIEW_AND_TREND_ANALYSIS,
				EchartsTitleConsts.TOTAL_NUMBER_OF_ASSIGNED_CASES_PENDING_SAR_ANALYSTS_INVESTIGATION_AND_TREND_ANALYSIS, 
				EchartsTitleConsts.TOTAL_NUMBER_OF_REFERRALS_TO_THE_SAR_TEAM_ON_WEEKLY_MONTHLY_BASES_AND_TREND_ANALYSIS };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(qaTotal);
		data.add(analystsTotal);
		data.add(teamTotal);
		map.put("list", data);
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * Customers and non-customers on which multiple SARs have been filed 柱状图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getStatisCust", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getStatisCust(ReportStatisCustomersDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		dto.setStr("non");
		List<Map<String, Object>> nonCust = reportStatisService.getStatisCust(dto); 
		dto.setStr("cust");
		List<Map<String, Object>> cust = reportStatisService.getStatisCust(dto);
		
		String[] title = new String[] { EchartsTitleConsts.NON_CUSTOMERS_ON_WHICH_MULTIPLE_SARS_HAVE_BEEN_FILED, EchartsTitleConsts.CUSTOMERS_ON_WHICH_MULTIPLE_SARS_HAVE_BEEN_FILED };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(nonCust);
		data.add(cust);
		map.put("list", data);
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * The trend of total number of SARs filed 柱状图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getTrendTotalOfSar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getTrendTotalOfSar(ReportStatisGroupSarDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> cust = reportStatisService.getTrendTotalOfSar(dto); 
		
		String[] title = new String[] { EchartsTitleConsts.TOTAL_NUMBER_OF_SARS_FILED_FROM_EACH_SOURCE_OF_REFERRAL_AND_TREND_ANALYSIS };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(cust);
		map.put("list", data);
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * Total number trend 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getTotalNumberTrend", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getTotalNumberTrend(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		dto.setStr("waived");
		List<Map<String, Object>> waivedTrend = reportStatisService.getTotalNumberTrend(dto); 
		dto.setStr("filed");
		List<Map<String, Object>> filedTrend = reportStatisService.getTotalNumberTrend(dto);
		dto.setStr("reasonable");
		List<Map<String, Object>> reasonableTrend = reportStatisService.getTotalNumberTrend(dto);
		
		String[] title = new String[] { EchartsTitleConsts.NUMBER_OF_ALERTS_WAIVED_ALERT_BUT_REJECTED_BY_ALERT_QA,
				EchartsTitleConsts.NUMBER_OF_CASES_WITH_SAR_FILED_VERSUS, 
				EchartsTitleConsts.NUMBER_OF_CASES_REASONABLE_AND_TREND_ANALYSIS };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(waivedTrend);
		data.add(filedTrend);
		data.add(reasonableTrend);
		map.put("list", data);
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * data report ***********************************************************
	 */
	
	/**
	 * Number of Alerts generated
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryAlertsGenerated", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryAlertsGenerated(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryAlertsGenerated(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Number of Cases Created
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryCaseCreated", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryCaseCreated(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryCaseCreated(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Number of SARs filed
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/querySarFiled", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> querySarFiled(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.querySarFiled(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * SAR yield rate
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/querySarYield", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> querySarYield(ReportStatisPercentDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.querySarYield(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Total number of referrals to the SAR Team
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryReferralsSar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryReferralsSar(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryReferralsSar(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Total number of assigned cases pending SAR analysts’ investigation
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryAnalystsSar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryAnalystsSar(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryAnalystsSar(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Total number of drafted cases pending SAR QA review
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryQaSar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryQaSar(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryQaSar(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Number of cases with SAR filed versus
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/querySarFiledVersus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> querySarFiledVersus(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.querySarFiledVersus(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * number of cases waived
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryCasesReasonable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryCasesWaived(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryCasesReasonable(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	/**
	 * Number of alerts waived Alert but rejected by alert QA
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryWaivedByQa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryWaivedByQa(ReportStatisTotalDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryWaivedByQa(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}

	/**
	 * Total number of SARs filed from each source of referral
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryEachTotalOfSar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryEachTotalOfSar(ReportStatisGroupSarDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryEachTotalOfSar(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
	
	
	/**
	 * Customers and non-customers on which multiple SARs have been filed 
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dataReport/queryStatisCust", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<List<Map<String, Object>>> queryStatisCust(ReportStatisCustomersDto dto, HttpServletRequest request) {
		ApiPageResult<List<Map<String, Object>>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<List<Map<String, Object>>> report = reportStatisService.queryStatisCust(dto); 
		PageUtils.toApiPageResult(result, report);
		return result;
	}
}
