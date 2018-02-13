//package com.aml.api.web;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.aml.api.dto.AlertInfoDto;
//import com.aml.api.dto.AlertInfoReviewDto;
//import com.aml.api.dto.CaseInfoDto;
//import com.aml.api.dto.FoldLineDto;
//import com.aml.api.pojo.AlertGroup;
//import com.aml.api.pojo.CaseGroup;
//import com.aml.api.service.AlertService;
//import com.aml.api.service.CaseService;
//import com.aml.common.base.BaseController;
//import com.aml.common.core.ApiPageResult;
//import com.aml.common.core.ApiResult;
//import com.aml.common.core.ErrorTypeEnum;
//import com.aml.common.core.FlowConsts;
//import com.aml.common.util.PageUtils;
//import com.github.pagehelper.Page;
//
///**
// * Management query
// * 
// * @author Novan
// * @date 2017-12-7
// *
// */
//@RestController
//public class ManagementController extends BaseController {
//	@Autowired
//	private AlertService alertService;
//	@Autowired
//	private CaseService caseService;
//
//	/**
//	 * get AlertInfo Data
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<List<AlertGroup>> getAlertInfo ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<List<AlertGroup>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		if (dto.getRoleId() == null) {
//			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
//			result.setErrorInfo("RoleId does not exist!");
//			return result;
//		}
//		getAlertUserName(dto);
//		List<AlertGroup> alertInfo = alertService.getAlertGroup(dto);
//		result.setData(alertInfo);
//		return result;
//	}
//
//	/**
//	 * get CaseInfo Data
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<List<Map<String, Object>>> getCaseInfo ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		if (dto.getRoleId() == null) {
//			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
//			result.setErrorInfo("RoleId does not exist!");
//			return result;
//		}
//		getCaseUserName(dto);
//		List<Map<String, Object>> caseInfo = caseService.getCaseInfo(dto);
//		result.setData(caseInfo);
//		return result;
//	}
//
//	/**
//	 * The total number of alert reviewed
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertPending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getAlertPending ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getAlertUserName(dto);
//		Map<String, Object> map = new HashMap<>();
//		Integer total = alertService.getAlertPending(dto);
//		map.put("total", total);
//		result.setData(map);
//		return result;
//	}
//
//	/**
//	 * The total number of alert reviewed
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCasePending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getCasePending ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getCaseUserName(dto);
//		Map<String, Object> map = new HashMap<>();
//		Integer total = caseService.getCasePending(dto);
//		map.put("total", total);
//		result.setData(map);
//		return result;
//	}
//
//	/**
//	 * The total number of alert reviewed
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getAlertTotal ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getAlertUserName(dto);
//		Map<String, Object> map = new HashMap<>();
//		List<AlertGroup> alertGroupList = alertService.getAlertTotal(dto);
//		map.put("total", alertGroupList.size());
//		result.setData(map);
//		return result;
//	}
//
//	/**
//	 * The total transaction amount and volume (alert)
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertAmountVolume", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<AlertGroup> getAlertAmountVolume ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<AlertGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getAlertUserName(dto);
//		AlertGroup alertInfo = alertService.getAlertAmountVolume(dto);
//		result.setData(alertInfo);
//		return result;
//	}
//
//	/**
//	 * The total transaction Avg Score (Case)
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertAvgScore", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<AlertGroup> getAlertAvgScore ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<AlertGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getAlertUserName(dto);
//		AlertGroup alertInfo = alertService.getAlertAvgScore(dto);
//		result.setData(alertInfo);
//		return result;
//	}
//
//	/**
//	 * The total number of case reviewed
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getCaseTotal ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		Map<String, Object> map = new HashMap<>();
//		getCaseUserName(dto);
//		List<CaseGroup> caseGroupList= caseService.getCaseTotal(dto);
//		map.put("total", caseGroupList.size());
//		result.setData(map);
//		return result;
//	}
//
//	/**
//	 * The total transaction amount and volume (Case)
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseAmountVolume", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<CaseGroup> getCaseAmountVolume ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<CaseGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getCaseUserName(dto);
//		CaseGroup caseGroup = caseService.getCaseAmountVolume(dto);
//		result.setData(caseGroup);
//		return result;
//	}
//
//	/**
//	 * The total transaction amount and volume (Case)
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseAvgScore", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<CaseGroup> getCaseAvgScore ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<CaseGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
//		getCaseUserName(dto);
//		CaseGroup caseGroup = caseService.getCaseAvgScore(dto);
//		result.setData(caseGroup);
//		return result;
//	}
//
//	/**
//	 * The total by month
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertTotalByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getAlertTotalByMonth ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getAlertTotalByMonth(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * The total by month
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseTotalByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getCaseTotalByMonth ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getCaseInfoByMonth(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * The Amount total by month alert
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertAmountByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getAlertAmountByMonth ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getAlertAmountByMonth(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * The Volume total by month alert
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertVolumeByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getAlertVolumeByMonth ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getAlertVolumeByMonth(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * The Amount total by month case
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseAmountByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getCaseAmountByMonth ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getCaseAmountByMonth(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * The Volume total by month case
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseVolumeByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getCaseVolumeByMonth ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getCaseVolumeByMonth(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * Average review time per alert
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertReviewTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getAlertReviewTime ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getAlertReviewTime(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * Average review time per case
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseReviewTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getCaseReviewTime ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getCaseReviewTime(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * Average review time per alert QA
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertReviewTimeQA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getAlertReviewTimeQA ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getAlertReviewTimeQA(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * Average review time per case QA
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseReviewTimeQA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getCaseReviewTimeQA ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getCaseReviewTimeQA(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * The total number of alert for XX review
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertReview", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertInfoReviewDto> getAlertReview ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertInfoReviewDto> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertInfoReviewDto> alertInfo = alertService.getAlertReview(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * Escalation/waive rate
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getRateAlert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getRate ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getRate(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * Percentage of alert reviewed reversed by QA
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getReversedAlert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getReversedAlert ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getReversed(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * The total number of productive alert escalated each month
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getEscalatedAlert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<AlertGroup> getEscalatedAlert ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<AlertGroup> alertInfo = alertService.getEscalated(dto);
//		PageUtils.toApiPageResult(result, alertInfo);
//		return result;
//	}
//
//	/**
//	 * Escalation/waive rate
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getRateCase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getRateCase ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getRateCase(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * Percentage of alert reviewed reversed by QA
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getReversedCase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getReversedCase ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getReversed(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * The total number of productive alert escalated each month
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getEscalatedCase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiPageResult<CaseGroup> getEscalatedCase ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
//		Page<CaseGroup> caseGroup = caseService.getEscalated(dto);
//		PageUtils.toApiPageResult(result, caseGroup);
//		return result;
//	}
//
//	/**
//	 * Average review time per Alert / Average QA review time per alert 折线图
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAverageAlertFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getAverageAlertFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		Map<String, Object> map = new HashMap<>();
//		List<AlertGroup> alertInfo = alertService.getAlertReviewTimeFoldLine(dto); // Average review time per alert
//		List<AlertGroup> alertInfoByQA = alertService.getAlertReviewTimeQAFoldLine(dto); // Average QA review time
//																								// per alert
//		String[] reviewTime = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
//				"September", "October", "November", "December" };
//		String[] reviewTimeValues = null;
//		// String[] reviewTimeQA = null;
//		String[] reviewTimeQAValues = null;
//		if (alertInfo.size() > 0) {
//			reviewTimeValues = new String[12];
//			reviewTimeValues[0] = alertInfo.get(0).getJanuary().toString();
//			reviewTimeValues[1] = alertInfo.get(0).getFebruary().toString();
//			reviewTimeValues[2] = alertInfo.get(0).getMarch().toString();
//			reviewTimeValues[3] = alertInfo.get(0).getApril().toString();
//			reviewTimeValues[4] = alertInfo.get(0).getMay().toString();
//			reviewTimeValues[5] = alertInfo.get(0).getJune().toString();
//			reviewTimeValues[6] = alertInfo.get(0).getJuly().toString();
//			reviewTimeValues[7] = alertInfo.get(0).getAugust().toString();
//			reviewTimeValues[8] = alertInfo.get(0).getSeptember().toString();
//			reviewTimeValues[9] = alertInfo.get(0).getOctober().toString();
//			reviewTimeValues[10] = alertInfo.get(0).getNovember().toString();
//			reviewTimeValues[11] = alertInfo.get(0).getDecember().toString();
//		} else {
//			reviewTimeValues = new String[] { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
//		}
//		if (alertInfoByQA.size() > 0) {
//			// reviewTimeQA = new
//			// String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
//			reviewTimeQAValues = new String[12];
//			reviewTimeQAValues[0] = alertInfoByQA.get(0).getJanuary().toString();
//			reviewTimeQAValues[1] = alertInfoByQA.get(0).getFebruary().toString();
//			reviewTimeQAValues[2] = alertInfoByQA.get(0).getMarch().toString();
//			reviewTimeQAValues[3] = alertInfoByQA.get(0).getApril().toString();
//			reviewTimeQAValues[4] = alertInfoByQA.get(0).getMay().toString();
//			reviewTimeQAValues[5] = alertInfoByQA.get(0).getJune().toString();
//			reviewTimeQAValues[6] = alertInfoByQA.get(0).getJuly().toString();
//			reviewTimeQAValues[7] = alertInfoByQA.get(0).getAugust().toString();
//			reviewTimeQAValues[8] = alertInfoByQA.get(0).getSeptember().toString();
//			reviewTimeQAValues[9] = alertInfoByQA.get(0).getOctober().toString();
//			reviewTimeQAValues[10] = alertInfoByQA.get(0).getNovember().toString();
//			reviewTimeQAValues[11] = alertInfoByQA.get(0).getDecember().toString();
//		} else {
//			reviewTimeQAValues = new String[] { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
//		}
//		String[] title = new String[] { "Average review time per alert", "Average QA review time per alert" };
//		List<FoldLineDto> list = new ArrayList<>();
//		FoldLineDto dto1 = new FoldLineDto();
//		FoldLineDto dto2 = new FoldLineDto();
//		dto1.setName("Average review time per alert");
//		dto1.setData(reviewTimeValues);
//		dto2.setName("Average QA review time per alert");
//		dto2.setData(reviewTimeQAValues);
//		list.add(0, dto1);
//		list.add(1, dto2);
//		map.put("data", reviewTime);
//		map.put("list", list);
//		map.put("title", title);
//		System.out.println(map);
//		result.setData(map);
//		return result;
//	}
//
//	/**
//	 * Average review time per case / Average QA review time per case 折线图
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAverageCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getAverageCaseFoldLine ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		Map<String, Object> map = new HashMap<>();
//		List<CaseGroup> caseGroup = caseService.getCaseReviewTimeFoldLine(dto); // Average review time per case
//		List<CaseGroup> caseGroupByQA = caseService.getCaseReviewTimeQAFoldLine(dto); // Average QA review time per
//																							// case
//		String[] reviewTime = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
//				"September", "October", "November", "December" };
//		String[] reviewTimeValues = null;
//		// String[] reviewTimeQA = null;
//		String[] reviewTimeQAValues = null;
//		if (caseGroup.size() > 0) {
//			reviewTimeValues = new String[12];
//			reviewTimeValues[0] = caseGroup.get(0).getJanuary().toString();
//			reviewTimeValues[1] = caseGroup.get(0).getFebruary().toString();
//			reviewTimeValues[2] = caseGroup.get(0).getMarch().toString();
//			reviewTimeValues[3] = caseGroup.get(0).getApril().toString();
//			reviewTimeValues[4] = caseGroup.get(0).getMay().toString();
//			reviewTimeValues[5] = caseGroup.get(0).getJune().toString();
//			reviewTimeValues[6] = caseGroup.get(0).getJuly().toString();
//			reviewTimeValues[7] = caseGroup.get(0).getAugust().toString();
//			reviewTimeValues[8] = caseGroup.get(0).getSeptember().toString();
//			reviewTimeValues[9] = caseGroup.get(0).getOctober().toString();
//			reviewTimeValues[10] = caseGroup.get(0).getNovember().toString();
//			reviewTimeValues[11] = caseGroup.get(0).getDecember().toString();
//		} else {
//			reviewTimeValues = new String[] { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
//		}
//		if (caseGroupByQA.size() > 0) {
//			// reviewTimeQA = new
//			// String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
//			reviewTimeQAValues = new String[12];
//			reviewTimeQAValues[0] = caseGroupByQA.get(0).getJanuary().toString();
//			reviewTimeQAValues[1] = caseGroupByQA.get(0).getFebruary().toString();
//			reviewTimeQAValues[2] = caseGroupByQA.get(0).getMarch().toString();
//			reviewTimeQAValues[3] = caseGroupByQA.get(0).getApril().toString();
//			reviewTimeQAValues[4] = caseGroupByQA.get(0).getMay().toString();
//			reviewTimeQAValues[5] = caseGroupByQA.get(0).getJune().toString();
//			reviewTimeQAValues[6] = caseGroupByQA.get(0).getJuly().toString();
//			reviewTimeQAValues[7] = caseGroupByQA.get(0).getAugust().toString();
//			reviewTimeQAValues[8] = caseGroupByQA.get(0).getSeptember().toString();
//			reviewTimeQAValues[9] = caseGroupByQA.get(0).getOctober().toString();
//			reviewTimeQAValues[10] = caseGroupByQA.get(0).getNovember().toString();
//			reviewTimeQAValues[11] = caseGroupByQA.get(0).getDecember().toString();
//		} else {
//			reviewTimeQAValues = new String[] { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
//		}
//		String[] title = new String[] { "Average review time per alert", "Average QA review time per alert" };
//		List<FoldLineDto> list = new ArrayList<>();
//		FoldLineDto dto1 = new FoldLineDto();
//		FoldLineDto dto2 = new FoldLineDto();
//		dto1.setName("Average review time per alert");
//		dto1.setData(reviewTimeValues);
//		dto2.setName("Average QA review time per alert");
//		dto2.setData(reviewTimeQAValues);
//		list.add(0, dto1);
//		list.add(1, dto2);
//		map.put("data", reviewTime);
//		map.put("list", list);
//		map.put("title", title);
//		System.out.println(map);
//		result.setData(map);
//		return result;
//	}
//	
//	
//	/**
//	 * Average review time per alert QA
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getAlertReviewTimeTotalQA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getAlertReviewTimeTotalQA ( AlertInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		Map<String, Object> map = new HashMap<>();
//		if(dto.getActorId() != null){
//			Long userId = Long.parseLong(dto.getActorId());
//			dto.setActorId(super.getUserName(userId));
//		}
//		Integer total = alertService.getAlertReviewTimeTotalQA(dto);
//		map.put("total", total);
//		result.setData(map);
//		return result;
//	}
//
//	/**
//	 * Average review time per case QA
//	 * 
//	 * @param requestPojo
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/getCaseReviewTimeTotalQA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ApiResult<Map<String, Object>> getCaseReviewTimeTotalQA ( CaseInfoDto dto, HttpServletRequest request ) {
//		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
//		Map<String, Object> map = new HashMap<>();
//		if(dto.getActorId() != null){
//			Long userId = Long.parseLong(dto.getActorId());
//			dto.setActorId(super.getUserName(userId));
//		}
//		Integer total = caseService.getCaseReviewTimeTotalQA(dto);
//		map.put("total", total);
//		result.setData(map);
//		return result;
//	}
//
//	public void getAlertUserName ( AlertInfoDto dto ) {
//		Long userId = Long.parseLong(dto.getActorId());
//		dto.setActorId(super.getUserName(userId));
//		Long roleId = dto.getRoleId();
//		String roleName = null;
//		if (!ObjectUtils.isEmpty(roleId)) {
//			for (Long id : ADMINISTRATOR) {
//				if (id.equals(roleId)) {
//					roleName = FlowConsts.TYPE_ALERT;
//				}
//			}
//		}
//		dto.setRoleName(roleName);
//	}
//
//	public void getCaseUserName ( CaseInfoDto dto ) {
//		Long userId = Long.parseLong(dto.getActorId());
//		dto.setActorId(super.getUserName(userId));
//		Long roleId = dto.getRoleId();
//		String roleName = null;
//		if (!ObjectUtils.isEmpty(roleId)) {
//			for (Long id : ADMINISTRATOR) {
//				if (id.equals(roleId)) {
//					roleName = FlowConsts.TYPE_ALERT;
//				}
//			}
//		}
//		dto.setRoleName(roleName);
//	}
//}
