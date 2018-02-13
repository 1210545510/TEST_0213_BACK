package com.aml.api.dao;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.SarSuspiciousActivityDto;
import com.aml.api.pojo.SarSuspiciousActivity;

public interface SarSuspiciousActivityMapper {
	int deleteByPrimaryKey ( Long susId );

	int insert ( SarSuspiciousActivity record );

	int insertSelective ( SarSuspiciousActivity record );

	SarSuspiciousActivity selectByPrimaryKey ( Long susId );

	int updateByPrimaryKeySelective ( SarSuspiciousActivity record );

	int updateByPrimaryKey ( SarSuspiciousActivity record );

	SarSuspiciousActivity selectByPrimaryKeyDto ( @Param("rid") Long rid );

	int insertSelectiveDto ( SarSuspiciousActivityDto dto );
	
	int updateByPrimaryKeySelectiveDto ( SarSuspiciousActivityDto dto );

	int getCount ( Long rid );
}