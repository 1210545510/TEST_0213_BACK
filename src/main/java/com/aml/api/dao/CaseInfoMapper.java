package com.aml.api.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.dto.CaseDTO;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.pojo.AlertInfo;
import com.aml.api.pojo.CaseInfo;
import com.github.pagehelper.Page;

public interface CaseInfoMapper {
	int deleteByPrimaryKey ( Long caseId );

	int insert ( CaseInfo record );

	int insertSelective ( CaseInfo record );
	
	int insertCaseInfo( Map<String, Object> params );

	CaseInfo selectByPrimaryKey ( Long caseId );

	int updateByPrimaryKeySelective ( CaseInfo record );

	int updateByPrimaryKey ( CaseInfo record );

	int updateByPrimaryKeySelective ( Map<String, Object> record );

	List<Map<String, Object>> downCaseInfoExcel ( CaseInfoDto dto );

	CaseInfo findCaseInfo ( Long caseId );
	
	Page<AlertInfo> queryCaseReviewList ( AlertReviewDTO dto );
	
	Map<String, Object> queryCaseReview ( AlertReviewDTO dto );

	List<Map<String, Object>> getCaseInfo ( CaseInfoDto dto );

	Integer getCaseTotal ( CaseInfoDto dto );

	CaseInfo getCaseAmountVolume ( CaseInfoDto dto );

	CaseInfo getCaseAvgScore ( CaseInfoDto dto );

	List<Map<String, Object>> downCaseInfoTotalByMonth ( CaseInfoDto dto );

	Page<CaseInfo> getCaseInfoByMonth ( CaseInfoDto dto );

	Page<CaseInfo> getCaseAmountByMonth ( CaseInfoDto dto );

	Page<CaseInfo> getCaseVolumeByMonth ( CaseInfoDto dto );

	Page<Map<String, Object>> queryPageCase ( CaseDTO dto );
	
	Page<Map<String, Object>> queryPageCaseReviewed ( CaseDTO dto );
	
	int queryPageCaseReviewed_count ( CaseDTO dto );

	Page<CaseInfo> getCaseReviewTime ( CaseInfoDto dto );

	Page<CaseInfo> getCaseReviewTimeQA ( CaseInfoDto dto );

	Integer getCasePending ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseAmountByMonthExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseVolumeByMonthExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseReviewTimeExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseReviewTimeQAExcel ( CaseInfoDto dto );

	Page<CaseInfo> getRateCase ( CaseInfoDto dto );

	Page<CaseInfo> getReversed ( AlertInfoDto dto );

	Page<CaseInfo> getEscalated ( AlertInfoDto dto );

	List<Map<String, Object>> getReversedExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getEscalatedExcel ( CaseInfoDto dto );

	List<String> queryCase ();

	List<CaseInfo> getCaseReviewTimeFoldLine ( CaseInfoDto dto );

	List<CaseInfo> getCaseReviewTimeQAFoldLine ( CaseInfoDto dto );

	List<Long> searchCaseId(AlertReviewDTO dto);

	Integer queryByCaseCount(Long caseId);

	List<Long> queryRemainingAlertId(@Param("ids") List<Long> ids,@Param("caseId") Long caseId);

	BigDecimal queryAllAmount(Long caseId);

	Integer queryAllVolume(Long caseId);

	void updateByAlertMerge(@Param("caseIdList") List<Long> caseIdList,@Param("cId") Long cId);
}