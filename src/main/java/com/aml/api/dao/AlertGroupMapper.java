package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.AlertDTO;
import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.AlertInfoReviewDto;
import com.aml.api.dto.Users;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.AlertTeam;
import com.github.pagehelper.Page;

public interface AlertGroupMapper {
	int deleteByPrimaryKey ( Long alertId );

	int insert ( AlertGroup record );

	int insertSelective ( AlertGroup record );

	AlertGroup selectByPrimaryKey ( Long alertId );

	int updateByPrimaryKeySelective ( AlertGroup record );

	int updateByPrimaryKey ( AlertGroup record );

	int updateByPrimaryKeySelective ( Map<String, Object> record );

	Page<Map<String, Object>> queryPageAlert ( AlertDTO dto );

	int queryPageAlertReviewed_count ( AlertDTO dto );

	Page<Map<String, Object>> queryPageAlertReviewed ( AlertDTO dto );
	
	List<Map<String, Object>> queryAlertReviewedExport ( AlertDTO dto );

	Page<AlertGroup> getAlertInfoList ( AlertInfoDto dto );

	AlertGroup findAlertGroup ( Long alertId );

	Long findNewAlertId ();

	List<Map<String, Object>> downAlertInfoExcel ( AlertInfoDto dto );

	List<AlertGroup> getAlertGroup ( AlertInfoDto dto );

	List<AlertGroup> getAlertTotal ( AlertInfoDto dto );

	AlertGroup getAlertAmountVolume ( AlertInfoDto dto );

	AlertGroup getAlertAvgScore ( AlertInfoDto dto );

	Page<AlertGroup> getAlertTotalByMonth ( AlertInfoDto dto );

	List<Map<String, Object>> downAlertInfoTotalByMonth ( AlertInfoDto dto );

	Page<AlertGroup> getAlertAmountByMonth ( AlertInfoDto dto );

	Page<AlertGroup> getAlertVolumeByMonth ( AlertInfoDto dto );

	Page<AlertGroup> getAlertReviewTime ( AlertInfoDto dto );

	Page<AlertGroup> getAlertReviewTimeQA ( AlertInfoDto dto );

	Integer getAlertPending ( AlertInfoDto dto );

	Page<AlertInfoReviewDto> getAlertReview ( AlertInfoDto dto );

	List<Map<String, Object>> getAlertAmountByMonthExport ( AlertInfoDto dto );

	List<Map<String, Object>> getAlertVolumeByMonthExport ( AlertInfoDto dto );

	List<Map<String, Object>> getAlertReviewTimeExcel ( AlertInfoDto dto );

	List<Map<String, Object>> getAlertReviewTimeQAExcel ( AlertInfoDto dto );

	Page<AlertGroup> getRate ( AlertInfoDto dto );

	Page<AlertGroup> getReversed ( AlertInfoDto dto );

	Page<AlertGroup> getEscalated ( AlertInfoDto dto );

	List<Map<String, Object>> getReversedExcel ( AlertInfoDto dto );

	List<Map<String, Object>> getEscalatedExcel ( AlertInfoDto dto );

	int updateFlow ( Map<String, Object> params );

	List<AlertTeam> queryAlert ( @Param("sysType") String sysType, @Param("isAdminCreate") Integer isAdminCreate );

	List<Map<String, String>> queryPrivateAlert ( @Param("sysType") String sysType,
			@Param("isAdminCreate") Integer isAdminCreate );

	List<Users> queryAnalystScore ( String roleIds );

	List<AlertTeam> queryIsLockAlert ();

	List<Map<String, Object>> getAlertReviewTimeFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getAlertReviewTimeQAFoldLine ( AlertInfoDto dto );

	Integer getAlertReviewTimeTotalQA ( AlertInfoDto dto );

	List<Map<String, Object>> getReversedFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getEscalatedFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getEscalationRateFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getWaviedRateFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getEscalationRateIFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getEscalationRateTeamFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getWaivedRateIFoldLine ( AlertInfoDto dto );

	List<Map<String, Object>> getWaivedRateTeamFoldLine ( AlertInfoDto dto );

	int isSameGroup(@Param("alertIdList") List<Long> alertIdList);

	int checkMergeState(@Param("alertIdList") List<Long> alertIdList);
	
	List<Map<String, Object>> getAverageTeamFoldLine ( AlertInfoDto dto );

	AlertGroup queryFile(Long alertId);

	
	/**
	 * reporting -------------------------------------------
	 */
	Integer queryAlertCountMantas();

	Integer queryAlertWaivedByQa();

	Integer queryPreAlertCount();
}
