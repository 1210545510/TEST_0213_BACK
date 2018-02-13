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
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.dto.WfHistOrderDto;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.service.CaseService;
import com.aml.api.service.WfHistOrderService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.EchartsTitleConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.FlowConsts;
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
public class CaseMonitorController extends BaseController {
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private WfHistOrderService wfHistOrderService;

	/**
	 * get CaseInfo Data
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<List<Map<String, Object>>> getCaseInfo ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (dto.getRoleId() == null) {
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("RoleId does not exist!");
			return result;
		}
		getCaseUserName(dto);
		if(dto.getParam() != null){
			dto.setRoleName("NotNull");
		}
		List<Map<String, Object>> caseInfo = caseService.getCaseInfo(dto);
		result.setData(caseInfo);
		return result;
	}

	/**
	 * The total number of alert reviewed
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCasePending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getCasePending ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		getCaseUserName(dto);
		Map<String, Object> map = new HashMap<>();
		if(dto.getParam() != null){
			dto.setRoleName("NotNull");
		}
		Integer total = caseService.getCasePending(dto);
		map.put("total", total);
		result.setData(map);
		return result;
	}

	/**
	 * The total number of case reviewed
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getCaseTotal ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		getCaseUserName(dto);
		List<CaseGroup> caseGroupList= caseService.getCaseTotal(dto);
		map.put("total", caseGroupList.size());
		result.setData(map);
		return result;
	}

	/**
	 * The total transaction amount and volume (Case)
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseAmountVolume", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<CaseGroup> getCaseAmountVolume ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<CaseGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
		getCaseUserName(dto);
		CaseGroup caseGroup = caseService.getCaseAmountVolume(dto);
		result.setData(caseGroup);
		return result;
	}

	/**
	 * The total transaction amount and volume (Case)
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseAvgScore", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<CaseGroup> getCaseAvgScore ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<CaseGroup> result = new ApiResult<>(ErrorTypeEnum.OK);
		getCaseUserName(dto);
		CaseGroup caseGroup = caseService.getCaseAvgScore(dto);
		result.setData(caseGroup);
		return result;
	}
	
	/**
	 * Average review time per Case Total / QA
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseReviewTimeTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getCaseReviewTimeTotal ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		if(dto.getActorId() != null){
			Long userId = Long.parseLong(dto.getActorId());
			dto.setActorId(super.getUserName(userId));
		}
		Integer total = caseService.getCaseReviewTimeTotalQA(dto);
		map.put("total", total);
		result.setData(map);
		return result;
	}
	
	
	/***
	 * data report -----------------------
	 */

	/**
	 * The total by month
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseTotalByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getCaseTotalByMonth ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getCaseInfoByMonth(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * The Amount total by month case
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseAmountByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getCaseAmountByMonth ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getCaseAmountByMonth(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * The Volume total by month case
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseVolumeByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getCaseVolumeByMonth ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getCaseVolumeByMonth(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * Average review time per case
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseReviewTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getCaseReviewTime ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getCaseReviewTime(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * Average review time per case QA
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCaseReviewTimeQA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getCaseReviewTimeQA ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getCaseReviewTimeQA(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * Escalation/waive rate
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getRateCase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getRateCase ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getRateCase(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getReversedCase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getReversedCase ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getReversed(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/**
	 * The total number of productive alert escalated each month
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getEscalatedCase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseGroup> getEscalatedCase ( AlertInfoDto dto, HttpServletRequest request ) {
		ApiPageResult<CaseGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CaseGroup> caseGroup = caseService.getEscalated(dto);
		PageUtils.toApiPageResult(result, caseGroup);
		return result;
	}

	/***
	 * 折线图 -----------------------
	 */
	
	/**
	 * Percentage of alert reviewed reversed by QA 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getReversedCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getReversedCaseFoldLine ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		getCaseUserName(dto);
		List<Map<String, Object>> caseGroup = caseService.getReversedCaseFoldLine(dto); // Average review time per case
		
		String[] title = new String[] { EchartsTitleConsts.CASE_PERCENTAGE_OF_CASES_REVERSED_BY_QA };

		if(caseGroup.size() == 0){
			map.put("list", new ArrayList<>());
		}else{
			List<List<Map<String, Object>>> data = new ArrayList<>();
			data.add(caseGroup);
			
			map.put("list", data);
		}
		
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 * Average review time per case / Average QA review time per case 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAverageCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAverageCaseFoldLine ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> caseGroup = caseService.getCaseReviewTimeFoldLine(dto); // Average review time per case
		List<Map<String, Object>> caseGroupByQA = caseService.getCaseReviewTimeQAFoldLine(dto); // Average QA review time per
			
		// case
		String[] title = new String[] { EchartsTitleConsts.CASE_AVERAGE_INVESTIGATION_TIME_PER_CASE, EchartsTitleConsts.CASE_AVERAGE_QA_REVIEW_TIME_PER_CASE };
		map.put("list", this.mergeKey(caseGroup, caseGroupByQA,1));
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 * The total number of productive case escalated 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getEscalatedCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getEscalatedCaseFoldLine ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> caseGroup = caseService.getEscalatedCaseFoldLine(dto); // Average review time per case
		
		String[] title = new String[] { EchartsTitleConsts.CASE_THE_TOTAL_NUMBER_OF_PRODUCTIVE_CASE_ESCALATED };
		map.put("list", caseGroup);
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 * Escalation Rate / Waived Rate 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getRateCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getRateFoldLine(CaseInfoDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		getCaseUserName(dto);
		List<Map<String, Object>> caseGroup = caseService.getEscalationRateCaseFoldLine(dto); // Escalation Rate
		//List<Map<String, Object>> caseGroupByQA = caseService.getWaivedRateCaseFoldLine(dto); // Waived Rate
		
		
		//String[] title = new String[] { "Escalation Rate", "Waived Rate" };
		
		String[] title = new String[] { EchartsTitleConsts.CASE_SAR_YIELD_RATE };
		if(caseGroup.size() == 0){
			map.put("list", new ArrayList<>());
		}else{
			List<List<Map<String, Object>>> data = new ArrayList<>();
			data.add(caseGroup);
			//map.put("list", this.mergeKey(caseGroup, caseGroupByQA));
			map.put("list", data);
		}
		
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 * Escalation I Rate / Escalation Team Rate 折线图 (SAR Rate for I reviewed)
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getEscalationTeamFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getEscalationTeamFoldLine(CaseInfoDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		getCaseUserName(dto);
		List<Map<String, Object>> caseGroup = caseService.getEscalationIFoldLine(dto); // Escalation I Rate
		List<Map<String, Object>> caseGroupByQA = caseService.getEscalationTeamFoldLine(dto); // Escalation Team Rate
		
		String[] title = new String[] { EchartsTitleConsts.CASE_SAR_RATE_FOR_I_REVIEWED, EchartsTitleConsts.CASE_SAR_RATE_FOR_TEAM_REVIEWED };
		
		if(caseGroup.size() == 0 && caseGroupByQA.size() == 0){
			
			map.put("list", new ArrayList<>());
		}else{
			map.put("list", this.mergeKey(caseGroup, caseGroupByQA,1));
		}
		
		
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 * Waived I Rate / Waived Team Rate 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWaivedTeamFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getWaivedTeamFoldLine(CaseInfoDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		getCaseUserName(dto);
		List<Map<String, Object>> caseGroup = caseService.getWaivedIFoldLine(dto); // Waived I Rate
		List<Map<String, Object>> caseGroupByQA = caseService.getWaivedTeamFoldLine(dto); // Waived Team Rate
		
		
		String[] title = new String[] { EchartsTitleConsts.CASE_REASONABLE_RATE_FOR_I_REVIEWED, EchartsTitleConsts.CASE_REASONABLE_RATE_FOR_TEAM_REVIEWED };
		
		map.put("list", this.mergeKey(caseGroup, caseGroupByQA,1));
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 * Average score for the case QA reviewed 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAverageScoreCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAverageScoreCaseFoldLine ( CaseInfoDto dto, HttpServletRequest request ) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> caseGroup = caseService.getAverageScoreCaseFoldLine(dto); // Average score for the case QA reviewed

		String[] title = new String[] { EchartsTitleConsts.CASE_AVERAGE_SCORE_FOR_THE_CASES_QA_REVIEWED };
		
		if(caseGroup.size() == 0){
			map.put("list", new ArrayList<>());
		}else{
			List<List<Map<String, Object>>> data = new ArrayList<>();
			data.add(caseGroup);
			
			map.put("list", data);
		}
		map.put("title", title);
		
		result.setData(map);
		return result;
	}
	
	/**
	 *	Average score for the alerts QA reviewed / team 折线图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAverageTeamCaseFoldLine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getAverageTeamCaseFoldLine(CaseInfoDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		getCaseUserName(dto);
		List<Map<String, Object>> caseGroup = caseService.getAverageIFoldLine(dto); // Average score for the alerts QA reviewed
		List<Map<String, Object>> caseGroupByQA = caseService.getAverageTeamFoldLine(dto); // Average score for the alerts team reviewed

		String[] title = new String[] { EchartsTitleConsts.CASE_AVERAGE_SCORE_FOR_THE_CASES_I_CONDUCTED_QA_REVIEW, EchartsTitleConsts.CASE_AVERAGE_SCORE_FOR_THE_CASES_MY_TEAM_CONDUCTED_QA_REVIEW };

		map.put("list", this.mergeKey(caseGroup, caseGroupByQA,1));
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 *	Workflow time statistics 柱状图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWorkflow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getWorkflow(WfHistOrderDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> workflow = wfHistOrderService.getWorkflow(dto); // Workflow time statistics

		String[] title = new String[] { EchartsTitleConsts.CASE_WORKFLOW_TIME_STATISTICS };

		List<List<Map<String, Object>>> data = new ArrayList<>();
		data.add(workflow);
		
		map.put("list", data);
		map.put("title", title);
		result.setData(map);
		return result;
	}
	
	/**
	 *	Alert/Case review time statistics 柱状图
	 * 
	 * @param requestPojo
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWorkflowByProcessId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<Map<String, Object>> getWorkflowByProcessId(WfHistOrderDto dto, HttpServletRequest request) {
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> map = new HashMap<>();
		// Alert review time statistics
		List<Map<String, Object>> workflowAlert = wfHistOrderService.getWorkflowAlert(dto); 
		// Case review time statistics
		List<Map<String, Object>> workflowCase = wfHistOrderService.getWorkflowCase(dto); 

		String[] title = new String[] { EchartsTitleConsts.CASE_ALERT_REVIEW_TIME_STATISTICS,EchartsTitleConsts.CASE_REVIEW_TIME_STATISTICS };
		
		map.put("list", this.mergeKey(workflowAlert, workflowCase,2));
		map.put("title", title);
		result.setData(map);
		return result;
	}
	

	public void getCaseUserName ( CaseInfoDto dto ) {
		Long userId = Long.parseLong(dto.getActorId());
		dto.setActorId(super.getUserName(userId));
		Long roleId = dto.getRoleId();
		String roleName = null;
		if (!ObjectUtils.isEmpty(roleId)) {
			for (Long id : ADMINISTRATOR) {
				if (id.equals(roleId)) {
					roleName = FlowConsts.TYPE_ALERT;
				}
			}
		}
		dto.setRoleName(roleName);
	}
	
	/**
	 * 合并key
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<List<Map<String, Object>>> mergeKey(List<Map<String, Object>> list1, List<Map<String, Object>> list2,int flag){
		Set<String> keySet1 = new HashSet<String>();
		for (Map<String, Object> map : list1) {
			keySet1.add(map.get("key").toString());
		}
		Set<String> keySet2 = new HashSet<String>();
		for (Map<String, Object> map : list2) {
			keySet2.add(map.get("key").toString());
		}
		
		for (String key : keySet2) {
			if(!keySet1.contains(key)){
				Map<String, Object> newMap = new HashMap<String, Object>();
				newMap.put("key", key);
				newMap.put("value", 0);
				if(flag == 1){
					for(Map<String, Object> map : list2){
						if(key.equals(map.get("key").toString())){
							newMap.put("timeName", map.get("timeName"));
						}
					}
				}
				list1.add(newMap);
			}
		}
		
		for (String key : keySet1) {
			if(!keySet2.contains(key)){
				Map<String, Object> newMap = new HashMap<String, Object>();
				newMap.put("key", key);
				newMap.put("value", 0);
				if(flag == 1){
					for(Map<String, Object> map : list1){
						if(key.equals(map.get("key").toString())){
							newMap.put("timeName", map.get("timeName"));
						}
					}
				}
				list2.add(newMap);
			}
		}
		//list1 排序
		Collections.sort(list1, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return Integer.parseInt(o1.get("key").toString()) - Integer.parseInt(o2.get("key").toString());
			}
		});
		//list2 排序
		Collections.sort(list2, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return Integer.parseInt(o1.get("key").toString()) - Integer.parseInt(o2.get("key").toString());
			}
		});
		
		List<List<Map<String, Object>>> result = new ArrayList<>();
		result.add(list1);
		result.add(list2);
		return result;
	}
}
