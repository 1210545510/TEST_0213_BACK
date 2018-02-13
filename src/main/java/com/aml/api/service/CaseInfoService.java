package com.aml.api.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.CaseInfoMapper;
import com.aml.api.dao.CustMapper;
import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.dto.CaseDTO;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.AlertInfo;
import com.aml.api.pojo.CaseInfo;
import com.aml.api.pojo.Cust;
import com.aml.common.core.AlertConsts;
import com.aml.common.util.DozerUtils;
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
public class CaseInfoService {
	public static final Logger logger = LoggerFactory.getLogger(CaseInfoService.class);
	@Autowired
	CaseInfoMapper caseInfoMapper;
	@Autowired
	AlertGroupMapper alertGroupMapper;
	@Autowired
	CustMapper custMapper;

	/**
	 * export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downCaseInfoExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.downCaseInfoExcel(dto);
	}

	/**
	 * 查询Case关联列表
	 * 
	 * @param dto
	 * @return Page<AlertInfo>
	 * @date 2017年11月24日
	 */
	public Page<AlertInfo> queryCaseReviewList ( AlertReviewDTO dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.queryCaseReviewList(dto);
	}

	/**
	 * 查询Case详情
	 * 
	 * @param dto
	 * @return Map<String, Object>
	 * @date 2017年11月24日
	 */
	public Map<String, Object> queryCaseReviewInfo ( AlertReviewDTO dto ) {
		return caseInfoMapper.queryCaseReview(dto);
	}
	
	/**
	 * 搜索caseId
	 * 
	 * @param dto
	 * @return
	 * @date 2017年12月8日
	 */
	public List<Long> searchCaseId ( AlertReviewDTO dto ) {
		return caseInfoMapper.searchCaseId(dto);
	}

	/**
	 * The total number of case reviewed
	 * 
	 * @param dto
	 * @return
	 */
	public Integer getCaseTotal ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseTotal(dto);
	}

	/**
	 * The total transaction amount and volume
	 * 
	 * @param dto
	 * @return
	 */
	public CaseInfo getCaseAmountVolume ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseAmountVolume(dto);
	}

	/**
	 * AVG score
	 * 
	 * @param dto
	 * @return
	 */
	public CaseInfo getCaseAvgScore ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseAvgScore(dto);
	}

	public List<Map<String, Object>> downCaseInfoTotalByMonth ( CaseInfoDto dto ) {
		return caseInfoMapper.downCaseInfoTotalByMonth(dto);
	}

	/**
	 * the total by month case
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getCaseInfoByMonth ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getCaseInfoByMonth(dto);
	}

	/**
	 * The Amount total by month case
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getCaseAmountByMonth ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getCaseAmountByMonth(dto);
	}

	/**
	 * The Volume total by month case
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getCaseVolumeByMonth ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getCaseVolumeByMonth(dto);
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
		return caseInfoMapper.queryPageCase(dto);
	}

	/**
	 * 查询Case已处理列表
	 * 
	 * @param dto
	 * @return
	 * @date 2017年12月8日
	 */
	public Page<Map<String, Object>> queryPageCaseReviewed ( CaseDTO dto ) {
		int count = caseInfoMapper.queryPageCaseReviewed_count(dto);
		Page<Map<String, Object>> data = caseInfoMapper.queryPageCaseReviewed(dto);
		Page<Map<String, Object>> result = new Page<>(dto.getPageNum(), dto.getPageSize());
		result.setTotal(count);
		result.addAll(data);
		return result;
	}

	public List<Map<String, Object>> exportCSV ( CaseDTO dto ) {
		return caseInfoMapper.queryPageCase(dto);
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
		AlertGroup alertGroup = DozerUtils.map(dto, AlertGroup.class);
		alertGroup.setDataType(AlertConsts.DATA_TYPE_FIVE);
		alertGroup.setSysType(AlertConsts.SYS_TYPE_AML);
		Cust cust = null;
		if (StringUtils.isNotBlank(subject)) {
			List<Cust> custList = custMapper.selectBySubject(subject);
			if (custList.size() != 0) {
				cust = custList.get(0);
			}
		} else if (StringUtils.isBlank(subject) && StringUtils.isNotBlank(customerId)) {
			alertGroup.setFocusType(AlertConsts.FOCUS_TYPE_CU);
			cust = custMapper.selectByCustomerId(customerId);
		} else if (StringUtils.isBlank(subject) && StringUtils.isBlank(customerId)
				&& StringUtils.isNotBlank(accountId)) {
			alertGroup.setFocusType(AlertConsts.FOCUS_TYPE_AC);
			cust = custMapper.selectByAccountId(accountId);
		}
		if (!ObjectUtils.isEmpty(cust)) {
			// 客户名称
			alertGroup.setSubject(cust.getAliasNm());
			alertGroup.setGeography(cust.getCustGeoRiskNb().toString());
			alertGroup.setJurisdiction(cust.getJrsdcnCd());
			alertGroup.setCustomerId(cust.getCustIntrlId());
		}
		alertGroup.setCreateUser(dto.getUserName());
		alertGroupMapper.insertSelective(alertGroup);
		Long alertId = alertGroup.getAlertId();
		CaseInfo caseInfo = new CaseInfo();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		caseInfo.setAlertId(alertId);
		caseInfo.setDueDate(cal.getTime());
		return caseInfoMapper.insertSelective(caseInfo);
	}

	/**
	 * Average review time per alert
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getCaseReviewTime ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getCaseReviewTime(dto);
	}

	/**
	 * QA
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getCaseReviewTimeQA ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getCaseReviewTimeQA(dto);
	}

	/**
	 * CasePending
	 * 
	 * @param dto
	 * @return
	 */
	public Integer getCasePending ( CaseInfoDto dto ) {
		return caseInfoMapper.getCasePending(dto);
	}

	/**
	 * Amount export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseAmountByMonthExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseAmountByMonthExcel(dto);
	}

	/**
	 * Volume export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseVolumeByMonthExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseVolumeByMonthExcel(dto);
	}

	/**
	 * Case Review Time Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseReviewTimeExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseReviewTimeExcel(dto);
	}

	/**
	 * Case Review Time QA Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getCaseReviewTimeQAExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseReviewTimeQAExcel(dto);
	}

	/**
	 * query
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getRateCase ( CaseInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getRateCase(dto);
	}

	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getReversed ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getReversed(dto);
	}

	/**
	 * The total number of productive alert escalated each month
	 * 
	 * @param dto
	 * @return
	 */
	public Page<CaseInfo> getEscalated ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return caseInfoMapper.getEscalated(dto);
	}

	/**
	 * Percentage of alert reviewed reversed by QA Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getReversedExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.getReversedExcel(dto);
	}

	/**
	 * The total number of productive alert escalated each month Excel
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalatedExcel ( CaseInfoDto dto ) {
		return caseInfoMapper.getEscalatedExcel(dto);
	}

	public List<CaseInfo> getCaseReviewTimeFoldLine ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseReviewTimeFoldLine(dto);
	}

	public List<CaseInfo> getCaseReviewTimeQAFoldLine ( CaseInfoDto dto ) {
		return caseInfoMapper.getCaseReviewTimeQAFoldLine(dto);
	}
}
