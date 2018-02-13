package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.ReportStatisCustomersMapper;
import com.aml.api.dao.ReportStatisGroupSarMapper;
import com.aml.api.dao.ReportStatisPercentMapper;
import com.aml.api.dao.ReportStatisTotalMapper;
import com.aml.api.dto.ReportStatisCustomersDto;
import com.aml.api.dto.ReportStatisGroupSarDto;
import com.aml.api.dto.ReportStatisPercentDto;
import com.aml.api.dto.ReportStatisTotalDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 每日统计报表数据Service
 * @author Novan
 *
 */
@Service
public class ReportStatisService {

	@Autowired
	ReportStatisTotalMapper reportStatisTotalMapper;
	
	@Autowired
	ReportStatisPercentMapper reportStatisPercentMapper;
	
	@Autowired
	ReportStatisCustomersMapper reportStatisCustomersMapper;
	
	@Autowired
	ReportStatisGroupSarMapper reportStatisGroupSarMapper;
	
	/**
	 * Trend of alerts generated, cases created, and SARs filed
	 * @param str
	 * @param dto 
	 * @return
	 */
	public List<Map<String, Object>> getTrend(ReportStatisTotalDto dto) {
		return reportStatisTotalMapper.getTrend(dto);
	}

	/**
	 * Ratios of productive alerts/ SAR yield rate 柱状图
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getYield(ReportStatisPercentDto dto) {
		return reportStatisPercentMapper.getYield(dto);
	}

	/**
	 * Total number trend 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getTrendTotal(ReportStatisTotalDto dto) {
		return reportStatisTotalMapper.getTrendTotal(dto);
	}

	/**
	 * Customers and non-customers on which multiple SARs have been filed
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getStatisCust(ReportStatisCustomersDto dto) {
		return reportStatisCustomersMapper.getStatisCust(dto);
	}

	/**
	 * The trend of total number of SARs filed
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getTrendTotalOfSar(ReportStatisGroupSarDto dto) {
		return reportStatisGroupSarMapper.getTrendTotalOfSar(dto);
	}

	/**
	 * Total number trend 折线图
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getTotalNumberTrend(ReportStatisTotalDto dto) {
		return reportStatisTotalMapper.getTotalNumberTrend(dto);
	}

	
	/**
	 * data report **************************************************************
	 */
	
	/**
	 * Number of Alerts generated
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryAlertsGenerated(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryAlertsGenerated(dto);
	}

	/**
	 * Number of Cases Created
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryCaseCreated(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryCaseCreated(dto);
	}

	/**
	 * Number of SARs filed
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> querySarFiled(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.querySarFiled(dto);
	}

	/**
	 * SAR yield rate
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> querySarYield(ReportStatisPercentDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisPercentMapper.querySarYield(dto);
	}

	/**
	 * Total number of referrals to the SAR Team
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryReferralsSar(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryReferralsSar(dto);
	}

	/**
	 * Total number of assigned cases pending SAR analysts’ investigation
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryAnalystsSar(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryAnalystsSar(dto);
	}

	/**
	 * Total number of drafted cases pending SAR QA review
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryQaSar(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryQaSar(dto);
	}

	/**
	 * Number of cases with SAR filed versus
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> querySarFiledVersus(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.querySarFiledVersus(dto);
	}

	/**
	 * number of cases waived(Reasonable)
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryCasesReasonable(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryCasesReasonable(dto);
	}

	/**
	 * Number of alerts waived Alert but rejected by alert QA
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryWaivedByQa(ReportStatisTotalDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisTotalMapper.queryWaivedByQa(dto);
	}

	/**
	 * Total number of SARs filed from each source of referral
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryEachTotalOfSar(ReportStatisGroupSarDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisGroupSarMapper.queryEachTotalOfSar(dto);
	}

	/**
	 * Customers and non-customers on which multiple SARs have been filed
	 * @param dto
	 * @return
	 */
	public Page<List<Map<String, Object>>> queryStatisCust(ReportStatisCustomersDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return reportStatisCustomersMapper.queryEachTotalOfSar(dto);
	}

}
