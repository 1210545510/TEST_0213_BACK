package com.aml.api.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.CaseGroupMapper;
import com.aml.api.dao.CaseInfoMapper;
import com.aml.api.dao.CustMapper;
import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.CaseDTO;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.pojo.Cust;
import com.aml.common.core.AlertConsts;
import com.aml.common.util.DozerUtils;
import com.aml.common.util.StrUtils;
import com.aml.common.util.TimeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: CaseInfoService
 * @description: CaseInfoService Class
 * @author Novan
 * @date 2017-11-29
 *
 */
@Service
public class CaseService {
	public static final Logger logger = LoggerFactory.getLogger(CaseService.class);
	@Autowired
	CaseGroupMapper caseGroupMapper;
	@Autowired
	CaseInfoMapper caseInfoMapper;
	@Autowired
	AlertGroupMapper alertGroupMapper;
	@Autowired
	CustMapper custMapper;

	/**
	 * query
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfoDto> getCaseInfoList ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getCaseInfoList(dto);
	}

	/**
	 * export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downCaseInfoExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.downCaseInfoExcel(dto);
	}

	/**
	 * 
	 * @title: findCaseGroup
	 * @description: 查询一条Case记录
	 *
	 * @param caseId
	 * @return CaseGroup
	 * @date 2017年12月5日 上午10:18:44
	 */
	public CaseGroup findCaseGroup ( Long caseId ) {
		return caseGroupMapper.findCaseGroup(caseId);
	}

	/**
	 * 
	 * @title: getCaseInfo
	 * @description: TODO
	 *
	 * @param dto
	 * @return
	 * @date 2017年12月8日 下午4:00:09
	 */
	public List<Map<String, Object>> getCaseInfo ( CaseInfoDto dto ) {
		if (dto.getCreateStartDate() != null) {
			dto.setCreateStartDate(TimeUtils.getStartTime(dto.getCreateStartDate()));
		}
		if (dto.getCreateEndDate() != null) {
			dto.setCreateEndDate(TimeUtils.getEndTime(dto.getCreateEndDate()));
		}
		return caseGroupMapper.getCaseInfo(dto);
	}

	/**
	 * The total number of case reviewed
	 * 
	 * @param dto
	 * @return
	 */
	public List<CaseGroup> getCaseTotal ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseTotal(dto);
	}

	/**
	 * The total transaction amount and volume
	 * 
	 * @param dto
	 * @return
	 */
	public CaseGroup getCaseAmountVolume ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseAmountVolume(dto);
	}

	/**
	 * AVG score
	 * 
	 * @param dto
	 * @return
	 */
	public CaseGroup getCaseAvgScore ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseAvgScore(dto);
	}

	public List<Map<String, Object>> downCaseInfoTotalByMonth ( CaseInfoDto dto ) {
		return caseGroupMapper.downCaseInfoTotalByMonth(dto);
	}

	/**
	 * the total by month case
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getCaseInfoByMonth ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getCaseInfoByMonth(dto);
	}

	/**
	 * The Amount total by month case
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getCaseAmountByMonth ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getCaseAmountByMonth(dto);
	}

	/**
	 * The Volume total by month case
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getCaseVolumeByMonth ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getCaseVolumeByMonth(dto);
	}

	/**
	 * 查询Case待办列表
	 * 
	 * @param dto
	 * @return
	 * @date 2017年12月8日
	 */
	public Page<Map<String, Object>> queryPageCase ( CaseDTO dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.queryPageCase(dto);
	}

	/**
	 * 查询Case已处理列表
	 * 
	 * @param dto
	 * @return
	 * @date 2017年12月8日
	 */
	public Page<Map<String, Object>> queryPageCaseReviewed ( CaseDTO dto ) {
		int count = caseGroupMapper.queryPageCaseReviewed_count(dto);
		Page<Map<String, Object>> data = caseGroupMapper.queryPageCaseReviewed(dto);
		Page<Map<String, Object>> result = new Page<>(dto.getPageNum(), dto.getPageSize());
		result.setTotal(count);
		result.addAll(data);
		return result;
	}

	public List<Map<String, Object>> exportCSV ( CaseDTO dto ) {
		return caseGroupMapper.queryPageCase(dto);
	}
	
	public List<Map<String, Object>> exportReviewedCSV ( CaseDTO dto ) {
		return caseGroupMapper.queryCaseReviewedExport(dto);
	}

	/**
	 * 创建case
	 * 
	 * @param dto
	 * @date 2017年12月11日
	 */
	public int addCase ( CaseDTO dto ) {
		String subject = dto.getSubject();
		String customerId = dto.getCustomerId();
		String accountId = dto.getAccountId();
		CaseGroup caseGroup = DozerUtils.map(dto, CaseGroup.class);
		caseGroup.setSysType(AlertConsts.SYS_TYPE_AML);
		Cust cust = null;
		if (StringUtils.isNotBlank(subject)) {
			List<Cust> custList = custMapper.selectBySubject(subject);
			if (custList.size() != 0) {
				cust = custList.get(0);
			}
		} else if (StringUtils.isBlank(subject) && StringUtils.isNotBlank(customerId)) {
			caseGroup.setFocusType(AlertConsts.FOCUS_TYPE_CU);
			cust = custMapper.selectByCustomerId(customerId);
		} else if (StringUtils.isBlank(subject) && StringUtils.isBlank(customerId)
				&& StringUtils.isNotBlank(accountId)) {
			caseGroup.setFocusType(AlertConsts.FOCUS_TYPE_AC);
			cust = custMapper.selectByAccountId(accountId);
		}
		if (!ObjectUtils.isEmpty(cust)) {
			// 客户名称
			caseGroup.setSubject(cust.getAliasNm());
			caseGroup.setGeography(cust.getCustGeoRiskNb().toString());
			caseGroup.setJurisdiction(cust.getJrsdcnCd());
			caseGroup.setCustomerId(cust.getCustIntrlId());
		}
		caseGroup.setCreateUser(dto.getUserName());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		caseGroup.setDueDate(cal.getTime());

		return this.insertSelective(caseGroup);
	}

	/**
	 * Average review time per alert
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getCaseReviewTime ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getCaseReviewTime(dto);
	}

	/**
	 * QA
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getCaseReviewTimeQA ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getCaseReviewTimeQA(dto);
	}

	/**
	 * CasePending
	 * 
	 * @param dto
	 * @return
	 */
	public Integer getCasePending ( CaseInfoDto dto ) {
		return caseGroupMapper.getCasePending(dto);
	}

	/**
	 * Amount export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseAmountByMonthExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseAmountByMonthExcel(dto);
	}

	/**
	 * Volume export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseVolumeByMonthExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseVolumeByMonthExcel(dto);
	}

	/**
	 * Case Review Time Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseReviewTimeExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseReviewTimeExcel(dto);
	}

	/**
	 * Case Review Time QA Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseReviewTimeQAExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseReviewTimeQAExcel(dto);
	}

	/**
	 * query
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getRateCase ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getRateCase(dto);
	}

	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getReversed ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getReversed(dto);
	}

	/**
	 * The total number of productive alert escalated each month
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseGroup> getEscalated ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseGroupMapper.getEscalated(dto);
	}

	/**
	 * Percentage of alert reviewed reversed by QA Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getReversedExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.getReversedExcel(dto);
	}

	/**
	 * The total number of productive alert escalated each month Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalatedExcel ( CaseInfoDto dto ) {
		return caseGroupMapper.getEscalatedExcel(dto);
	}

	public List<Map<String, Object>> getCaseReviewTimeFoldLine ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseReviewTimeFoldLine(dto);
	}

	public List<Map<String, Object>> getCaseReviewTimeQAFoldLine ( CaseInfoDto dto ) {
		return caseGroupMapper.getCaseReviewTimeQAFoldLine(dto);
	}
	
	/**
	 * getCaseReviewTimeTotalQA
	 * @param dto
	 * @return
	 */
	public Integer getCaseReviewTimeTotalQA(CaseInfoDto dto) {
		return caseGroupMapper.getCaseReviewTimeTotalQA(dto);
	}
	

	/**
	 * alert合并
	 * @param dto
	 * @date 2018年1月11日
	 */
	public void alertMerge(CaseDTO dto) {
		List<Long> caseIdList = dto.getCaseIdList();
		Long cId = caseIdList.get(0);
		BigDecimal allAmount = BigDecimal.ZERO;
		Integer allVolume = 0;
		Set<String> alertIds = new HashSet<>();
		for (Long caseId : caseIdList) {
			CaseGroup caseGroup = caseGroupMapper.selectByPrimaryKey(caseId);
			String alertId = caseGroup.getAlertId();
			String[] split = alertId.split(",");
			for (String string : split) {
				if(StringUtils.isNotBlank(string)) {
					alertIds.add(string);
				}
			}
			allAmount.add(caseInfoMapper.queryAllAmount(caseId));
			allVolume += caseInfoMapper.queryAllVolume(caseId);
			//删除多余的alert
			if(!caseId.equals(cId)){
				caseGroup = new CaseGroup();
				caseGroup.setCaseId(caseId);
				caseGroup.setIsDeleted(1);
				caseGroupMapper.updateByPrimaryKeySelective(caseGroup);
			}
		}
		caseInfoMapper.updateByAlertMerge(caseIdList, cId);
		CaseGroup caseGroup = new CaseGroup();
		StringBuffer alertIdSt = new StringBuffer();
		for (String string : alertIds) {
			alertIdSt.append(string);
			alertIdSt.append(",");
		}
		if(alertIdSt.length() > 1) {
			caseGroup.setAlertId(alertIdSt.substring(0, alertIdSt.length() - 1));
		}
		caseGroup.setCaseId(cId);
		caseGroup.setAmount(allAmount);
		caseGroup.setVolume(allVolume);
		caseGroupMapper.updateByPrimaryKeySelective(caseGroup);
	}
	
	/**
	 * 插入CaseGroup数据
	 * @param record
	 * @return
	 * @date 2018年1月12日
	 */
	public synchronized int insertSelective ( CaseGroup record ){
		Long caseId = StrUtils.getPrimaryKey(caseGroupMapper.findNewCaseId());
		record.setCaseId(caseId);
		return caseGroupMapper.insertSelective(record);
	}

	/**
	 * Percentage of alert reviewed reversed by QA 折线图
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getReversedCaseFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getReversedCaseFoldLine(dto);
	}

	/**
	 * The total number of productive case escalated 折线图
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalatedCaseFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getEscalatedCaseFoldLine(dto);
	}

	/**
	 * Escalation Rate
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalationRateCaseFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getEscalationRateCaseFoldLine(dto);
	}

	/**
	 * Waived Rate
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWaivedRateCaseFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getWaivedRateCaseFoldLine(dto);
	}

	/**
	 * Escalation I Rate
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalationIFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getEscalationRateCaseFoldLine(dto);
	}

	/**
	 * Escalation Team Rate
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalationTeamFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getEscalationTeamFoldLine(dto);
	}

	/**
	 * Waived I Rate
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWaivedIFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getWaivedRateCaseFoldLine(dto);
	}
	/**
	 * Waived Team Rate
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWaivedTeamFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getWaivedTeamFoldLine(dto);
	}

	/**
	 * Average score for the case QA reviewed
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAverageScoreCaseFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getAverageTeamFoldLine(dto);
	}

	/**
	 * Average score for the case QA reviewed
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAverageIFoldLine(CaseInfoDto dto) {
		return caseGroupMapper.getAverageTeamFoldLine(dto);
	}

	/**
	 * Average score for the alerts Team reviewed
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAverageTeamFoldLine(CaseInfoDto dto) {
		dto.setActorId(null);
		return caseGroupMapper.getAverageTeamFoldLine(dto);
	}

	
}
