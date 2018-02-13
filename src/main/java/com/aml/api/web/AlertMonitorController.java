package com.aml.api.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.AlertInfoReviewDto;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.service.AlertService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.EchartsTitleConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.util.PageUtils;
import com.github.pagehelper.Page;

/**
 * Management query
 * 
 * @author Novan
 * @date 2017-12-7
 *
 */
@RestController
public class AlertMonitorController extends BaseController {
	@Autowired
	private AlertService alertService;

	/**
	 * get AlertInfo Data
	 * 
	 * @param dto 参数<br>
	 *        param=>用户集合字符串
	 * @param request
	 * @param session
	 * @return ApiResult<List<AlertGroup>>
	 */
	@RequestMapping(value = "/getAlertInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<List<AlertGroup>> getAlertInfo ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<List<AlertGroup>> result = new ApiResult<>(ErrorTypeEnum.OK);
		this.getDefaultSearch(dto);
		List<AlertGroup> data = alertService.getAlertGroup(dto);
		result.setData(data);
		return result;
	}

	/**
	 * The total number of alert reviewed pending
	 * 
	 * @param dto 参数<br>
	 *        param=>用户集合字符串
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getAlertPending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAlertPending ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		this.getDefaultSearch(dto);
		Map<String, Object> map = new HashMap<>();
		Integer total = alertService.getAlertPending(dto);
		map.put("total", total);
		result.setData(map);

		return result;
	}

	/**
	 * The total number of alert reviewed
	 * 
	 * @param dto 参数<br>
	 *        param=>用户集合字符串
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getAlertTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAlertTotal ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		this.getDefaultSearch(dto);
		Map<String, Object> map = new HashMap<>();
		List<AlertGroup> list = alertService.getAlertTotal(dto);
		map.put("total", list.size());
		result.setData(map);
		return result;
	}

	/**
	 * The total transaction amount and volume (alert)
	 * 
	 * @param dto 参数<br>
	 *        param=>用户集合字符串
	 * @param request
	 * @return ApiResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertAmountVolume", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<AlertGroup> getAlertAmountVolume ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<AlertGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
		this.getDefaultSearch(dto);
		AlertGroup alertGroup = alertService.getAlertAmountVolume(dto);
		result.setData(alertGroup);
		return result;
	}

	/**
	 * The total transaction Avg Score
	 * 
	 * @param dto 参数<br>
	 *        param=>用户集合字符串
	 * @param request
	 * @return ApiResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertAvgScore", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<AlertGroup> getAlertAvgScore ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<AlertGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
		this.getDefaultSearch(dto);
		AlertGroup alertGroup = alertService.getAlertAvgScore(dto);
		result.setData(alertGroup);
		return result;
	}

	/**
	 * The total by month
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertTotalByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getAlertTotalByMonth ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getAlertTotalByMonth(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * The Amount total by month alert
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertAmountByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getAlertAmountByMonth ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getAlertAmountByMonth(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * The Volume total by month alert
	 * 
	 * @param requestPojo
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertVolumeByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getAlertVolumeByMonth ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getAlertVolumeByMonth(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * Average review time per alert
	 * 
	 * @param requestPojo
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertReviewTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getAlertReviewTime ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getAlertReviewTime(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * Average review time per alert QA
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getAlertReviewTimeQA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getAlertReviewTimeQA ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getAlertReviewTimeQA(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * Average review time per alert Total / QA
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getAlertReviewTimeTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAlertReviewTimeTotalQA ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setParam(super.getUserName(dto.getUserId()));
		Map<String, Object> map = new HashMap<>();
		Integer total = alertService.getAlertReviewTimeTotalQA(dto);
		map.put("total", total);
		result.setData(map);
		return result;
	}

	/**
	 * The total number of alert for XX review
	 * 
	 * @param dto 参数<br>
	 *        org=>小组编码<br>
	 * @param request
	 * @return ApiPageResult<AlertInfoReviewDto>
	 */
	@RequestMapping(value = "/getAlertReview", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertInfoReviewDto> getAlertReview ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertInfoReviewDto> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertInfoReviewDto> data = alertService.getAlertReview(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * Escalation/waive rate
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getRateAlert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getRate ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getRate(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getReversedAlert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getReversedAlert ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getReversed(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * The total number of productive alert escalated each month
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertGroup>
	 */
	@RequestMapping(value = "/getEscalatedAlert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> getEscalatedAlert ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AlertGroup> data = alertService.getEscalated(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/** ***************************************************************************/

	/**
	 * Average review time per Alert / Average QA review time per alert 折线图
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getAverageAlertFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAverageAlertFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		// Average review time
		List<Map<String, Object>> alertInfo = alertService.getAlertReviewTimeFoldLine(dto);
		// Average QA review time
		List<Map<String, Object>> alertInfoByQA = alertService.getAlertReviewTimeQAFoldLine(dto);

		String[] title = new String[] { EchartsTitleConsts.ALERT_AVERAGE_REVIEW_TIME_PER_ALERT, EchartsTitleConsts.ALERT_AVERAGE_QA_REVIEW_TIME_PER_ALERT };
		map.put("list", this.mergeKey(alertInfo, alertInfoByQA));
		map.put("title", title);

		result.setData(map);
		return result;
	}

	/**
	 * Percentage of alert reviewed reversed by QA - 折线图 TODO
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getReversedAlertFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getReversedAlertFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		// getAlertUserName(dto);
		// Average review time
		List<Map<String, Object>> alertInfo = alertService.getReversedFoldLine(dto);
		String[] title = null;
		if (dto.getRoleId().equals("10041") || dto.getRoleId().equals("10043")) {
			// QA
			title = new String[] { EchartsTitleConsts.ALERT_PERCENTAGE_OF_ALERTS_REVERSED_BY_MY_QA_REVIEW };
		} else if (dto.getRoleId().equals("10042")) {
			// analyst
			title = new String[] { EchartsTitleConsts.ALERT_PERCENTAGE_OF_ALERTS_REVERSED_BY_QA };
		} else if (dto.getRoleId().equals("10044") || dto.getRoleId().equals("10047") || dto.getRoleId().equals("10052")
				|| dto.getRoleId().equals("10053")) {
			// sup
			title = new String[] { EchartsTitleConsts.ALERT_PERCENTAGE_OF_ALERTS_REVERSED_BY_QA };
		}
		if(alertInfo.size() == 0){
			map.put("list", new ArrayList<>());
		}else{
			List<List<Map<String, Object>>> data = new ArrayList<>();
			data.add(alertInfo);
			//map.put("list", this.mergeKey(caseGroup, caseGroupByQA));
			map.put("list", data);
		}
		map.put("title", title);
		result.setData(map);
		return result;
	}

	/**
	 * The total number of productive alert escalated each month 折线图
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */

	@RequestMapping(value = "/getEscalatedAlertFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getEscalatedFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		// Average review time
		List<Map<String, Object>> alertInfo = alertService.getEscalatedFoldLine(dto);

		String[] title = new String[] { EchartsTitleConsts.ALERT_TOTAL_NUMBER_OF_ALERTS_ESCALATED };
		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(alertInfo);

		map.put("list", data);
		map.put("title", title);
		result.setData(map);
		return result;
	}

	/**
	 * Escalation Rate / Waived Rate 折线图 TODO
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getRateFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getRateFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		// Average review time per alert
		List<Map<String, Object>> alertInfo = alertService.getEscalationRateFoldLine(dto);
		// Average QA review time per alert
		// List<Map<String, Object>> alertInfoByQA = alertService.getWaviedRateFoldLine(dto);

		// String[] title = new String[] { "Escalation Rate", "Wavied Rate" };
		String[] title = new String[] { EchartsTitleConsts.ALERT_ESCALATION_RATE };

		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(alertInfo);
		// map.put("list", this.mergeKey(alertInfo,alertInfoByQA));
		map.put("list", data);
		map.put("title", title);

		result.setData(map);
		return result;
	}

	/**
	 * Waived Rate / team 折线图
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getWaivedRateTeamFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getWaivedRateTeamFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setOrg(super.getTeamCode(dto.getUserId()));
		dto.setParam(super.getUserName(dto.getUserId()));
		// Waived I Rate
		List<Map<String, Object>> alertInfo = alertService.getWaivedRateIFoldLine(dto);
		// Waived Team Rate
		List<Map<String, Object>> alertInfoByQA = alertService.getWaivedRateTeamFoldLine(dto);

		String[] title = new String[] { EchartsTitleConsts.ALERT_WAIVED_RATE_FOR_I_REVIEWED, EchartsTitleConsts.ALERT_WAIVED_RATE_FOR_TEAM_REVIEWED };
		Map<String, Object> map = new HashMap<>();
		map.put("list", this.mergeKey(alertInfo, alertInfoByQA));
		map.put("title", title);

		result.setData(map);
		return result;
	}

	/**
	 * Escalation Rate / team 折线图
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getEscalationRateTeamFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getEscalationRateTeamFoldLine ( AlertInfoDto dto,
			HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setOrg(super.getTeamCode(dto.getUserId()));
		dto.setParam(super.getUserName(dto.getUserId()));
		// Escalation I Rate
		List<Map<String, Object>> alertInfo = alertService.getEscalationRateIFoldLine(dto);
		// Escalation Team Rate
		List<Map<String, Object>> alertInfoByQA = alertService.getEscalationRateTeamFoldLine(dto);

		String[] title = new String[] { EchartsTitleConsts.ALERT_ESCALATION_RATE_FOR_MY_ALERTS, EchartsTitleConsts.ALERT_ESCALATION_RATE_FOR_MY_TEAM_S_ALERTS };
		Map<String, Object> map = new HashMap<>();
		map.put("list", this.mergeKey(alertInfo, alertInfoByQA));
		map.put("title", title);

		result.setData(map);
		return result;
	}

	/**
	 * Average score for the alerts QA reviewed / team 折线图
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getAverageTeamFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAverageTeamFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setParam(super.getUserName(dto.getUserId()));
		// Escalation I Rate
		List<Map<String, Object>> alertInfo = alertService.getAverageIFoldLine(dto);
		// Escalation Team Rate
		dto.setParam(null);
		List<Map<String, Object>> alertInfoByQA = alertService.getAverageTeamFoldLine(dto);

		String[] title = new String[] { EchartsTitleConsts.ALERT_AVERAGE_SCORE_FOR_THE_ALERTS_I_CONDUCTED_QA_REVIEW,
				EchartsTitleConsts.ALERT_AVERAGE_SCORE_FOR_THE_ALERTS_MY_TEAM_CONDUCTED_QA_REVIEW };
		Map<String, Object> map = new HashMap<>();
		map.put("list", this.mergeKey(alertInfo, alertInfoByQA));
		map.put("title", title);

		result.setData(map);
		return result;
	}

	/**
	 * Average score for the alerts QA reviewed
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 */
	@RequestMapping(value = "/getAverageFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAverageFoldLine ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setParam(super.getUserName(dto.getUserId()));
		// Escalation I Rate
		List<Map<String, Object>> alertInfo = alertService.getAverageFoldLine(dto);
		// Escalation Team Rate

		String[] title = new String[] { EchartsTitleConsts.ALERT_AVERAGE_SCORE_FOR_THE_ALERTS_QA_REVIEWED };

		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(alertInfo);
		Map<String, Object> map = new HashMap<>();
		map.put("list", data);
		map.put("title", title);

		result.setData(map);
		return result;
	}

	/**
	 * 
	 * @title: getDefaultSearch
	 * @description: 获取默认搜索查询参数
	 *
	 * @param dto
	 * @date 2018年1月25日 上午9:35:46
	 */
	private void getDefaultSearch ( AlertInfoDto dto ) {
		Long userId = dto.getUserId();
		String param = dto.getParam();
		// 默认按角色权限查询数据
		if (ObjectUtils.isEmpty(param)) {
			if (!super.isAdmin(userId)) {
				if (super.isAlertSupervisor(userId)) {
					dto.setOrg(super.getTeamCode(userId));
				} else {
					dto.setParam(super.getUserName(userId));
				}
			}
		}
	}

	/**
	 * 合并key
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<List<Map<String, Object>>> mergeKey ( List<Map<String, Object>> list1,
			List<Map<String, Object>> list2 ) {
		Set<String> keySet1 = new HashSet<String>();
		for (Map<String, Object> map : list1) {
			keySet1.add(map.get("key").toString());
		}
		Set<String> keySet2 = new HashSet<String>();
		for (Map<String, Object> map : list2) {
			keySet2.add(map.get("key").toString());
		}

		for (String key : keySet2) {
			if (!keySet1.contains(key)) {
				Map<String, Object> newMap = new HashMap<String, Object>();
				newMap.put("key", key);
				newMap.put("value", 0);
				for (Map<String, Object> map : list2) {
					if (key.equals(map.get("key").toString())) {
						newMap.put("timeName", map.get("timeName"));
					}
				}
				list1.add(newMap);
			}
		}

		for (String key : keySet1) {
			if (!keySet2.contains(key)) {
				Map<String, Object> newMap = new HashMap<String, Object>();
				newMap.put("key", key);
				newMap.put("value", 0);
				for (Map<String, Object> map : list1) {
					if (key.equals(map.get("key").toString())) {
						newMap.put("timeName", map.get("timeName"));
					}
				}
				list2.add(newMap);
			}
		}
		// list1 排序
		Collections.sort(list1, new Comparator<Map<String, Object>>() {
			@Override
			public int compare ( Map<String, Object> o1, Map<String, Object> o2 ) {
				return Integer.parseInt(o1.get("key").toString()) - Integer.parseInt(o2.get("key").toString());
			}
		});
		// list2 排序
		Collections.sort(list2, new Comparator<Map<String, Object>>() {
			@Override
			public int compare ( Map<String, Object> o1, Map<String, Object> o2 ) {
				return Integer.parseInt(o1.get("key").toString()) - Integer.parseInt(o2.get("key").toString());
			}
		});

		List<List<Map<String, Object>>> result = new ArrayList<>();
		result.add(list1);
		result.add(list2);
		return result;
	}

}
