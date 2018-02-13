package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.dto.CaseDTO;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.pojo.CaseGroup;
import com.github.pagehelper.Page;

public interface CaseGroupMapper {
	int insertSelective ( CaseGroup record );
	
	int updateByPrimaryKeySelective(CaseGroup record);
	
	int insertCaseGroup(Map<String, Object> params);
	
	CaseGroup selectByPrimaryKey ( Long caseId );

	Page<CaseInfoDto> getCaseInfoList ( CaseInfoDto dto );

	List<Map<String, Object>> downCaseInfoExcel ( CaseInfoDto dto );

	CaseGroup findCaseGroup ( Long caseId );

	List<Map<String, Object>> getCaseInfo ( CaseInfoDto dto );

	List<CaseGroup> getCaseTotal ( CaseInfoDto dto );

	CaseGroup getCaseAmountVolume ( CaseInfoDto dto );

	CaseGroup getCaseAvgScore ( CaseInfoDto dto );

	List<Map<String, Object>> downCaseInfoTotalByMonth ( CaseInfoDto dto );

	Page<CaseGroup> getCaseInfoByMonth ( CaseInfoDto dto );

	Page<CaseGroup> getCaseAmountByMonth ( CaseInfoDto dto );

	Page<CaseGroup> getCaseVolumeByMonth ( CaseInfoDto dto );

	Page<Map<String, Object>> queryPageCase ( CaseDTO dto );
	
	Page<Map<String, Object>> queryPageCaseReviewed ( CaseDTO dto );
	
	List<Map<String, Object>> queryCaseReviewedExport ( CaseDTO dto );
	
	int queryPageCaseReviewed_count ( CaseDTO dto );

	Page<CaseGroup> getCaseReviewTime ( CaseInfoDto dto );

	Page<CaseGroup> getCaseReviewTimeQA ( CaseInfoDto dto );

	Integer getCasePending ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseAmountByMonthExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseVolumeByMonthExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseReviewTimeExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseReviewTimeQAExcel ( CaseInfoDto dto );

	Page<CaseGroup> getRateCase ( CaseInfoDto dto );

	Page<CaseGroup> getReversed ( AlertInfoDto dto );

	Page<CaseGroup> getEscalated ( AlertInfoDto dto );

	List<Map<String, Object>> getReversedExcel ( CaseInfoDto dto );

	List<Map<String, Object>> getEscalatedExcel ( CaseInfoDto dto );

	int updateFlow ( Map<String, Object> params );

	List<String> queryCase (Integer isAdminCreate);
	
	List<CaseGroup> queryPrivateCase (Integer isAdminCreate);

	List<Map<String, Object>> getCaseReviewTimeFoldLine ( CaseInfoDto dto );

	List<Map<String, Object>> getCaseReviewTimeQAFoldLine ( CaseInfoDto dto );

	List<Long> searchAlertId(AlertReviewDTO dto);
	
	Long findNewCaseId ();
	
	Integer getCaseReviewTimeTotalQA(CaseInfoDto dto);

	List<Map<String, Object>> getReversedCaseFoldLine(CaseInfoDto dto);

	List<Map<String, Object>> getEscalatedCaseFoldLine(CaseInfoDto dto);

	List<Map<String, Object>> getEscalationRateCaseFoldLine(CaseInfoDto dto);

	List<Map<String, Object>> getWaivedRateCaseFoldLine(CaseInfoDto dto);

	List<Map<String, Object>> getEscalationTeamFoldLine(CaseInfoDto dto);

	List<Map<String, Object>> getWaivedTeamFoldLine(CaseInfoDto dto);

	List<CaseGroup> getAverageCaseFoldLine(CaseInfoDto dto);

	int checkMergeState(@Param("caseIdList") List<Long> caseIdList);
	
	List<Map<String, Object>> getAverageTeamFoldLine(CaseInfoDto dto);

	CaseGroup queryFile(Long caseId);

	/**
	 * reporting ----------------------------------------
	 */
	
	Integer queryCaseCountByAlert();

	Integer querySarCountByCase();

	Integer queryCaseCountByRoleId(Integer roleId);

	Integer queryCaseCountCreate();

	Integer queryCaseToSarCount();

	Integer queryCaseReasonableCount();

	Integer queryPreCaseCount();

	Integer queryPreSarCount();
}