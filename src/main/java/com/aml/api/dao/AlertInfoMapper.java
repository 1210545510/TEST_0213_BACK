package com.aml.api.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.pojo.AlertInfo;
import com.github.pagehelper.Page;

public interface AlertInfoMapper {
	int deleteByPrimaryKey ( Long id );

	int insert ( AlertInfo record );

	int insertSelective ( AlertInfo record );

	AlertInfo selectByPrimaryKey ( Long id );

	int updateByPrimaryKeySelective ( AlertInfo record );

	int updateByPrimaryKey ( AlertInfo record );

	List<Long> searchAlertId (AlertReviewDTO dto );
	
	Page<AlertInfo> queryAlertReviewList ( AlertReviewDTO dto );
	
	Map<String, Object> queryAlertReview ( AlertReviewDTO dto );

	int queryByAlertCount ( Long alertId );

	BigDecimal queryAllAmount(Long alertId);

	Integer queryAllVolume(Long alertId);

	void updateByAlertMerge(@Param("alertIdList") List<Long> alertIdList,@Param("aId") Long aId);
}