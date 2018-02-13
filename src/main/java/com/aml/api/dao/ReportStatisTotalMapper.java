package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ReportStatisTotalDto;
import com.aml.api.pojo.ReportStatisTotal;
import com.github.pagehelper.Page;

public interface ReportStatisTotalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportStatisTotal record);

    int insertSelective(ReportStatisTotal record);

    ReportStatisTotal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportStatisTotal record);

    int updateByPrimaryKey(ReportStatisTotal record);

	List<Map<String, Object>> getTrend(ReportStatisTotalDto dto);

	List<Map<String, Object>> getTrendTotal(ReportStatisTotalDto dto);
	
	List<Map<String, Object>> getTotalNumberTrend(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryAlertsGenerated(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryCaseCreated(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> querySarFiled(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryReferralsSar(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryAnalystsSar(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryQaSar(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> querySarFiledVersus(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryCasesReasonable(ReportStatisTotalDto dto);

	Page<List<Map<String, Object>>> queryWaivedByQa(ReportStatisTotalDto dto);
	
}