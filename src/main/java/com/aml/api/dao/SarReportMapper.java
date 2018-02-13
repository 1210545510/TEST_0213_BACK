package com.aml.api.dao;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.SarReportDto;
import com.aml.api.pojo.SarReport;

public interface SarReportMapper {
	int deleteByPrimaryKey ( Long rid );

	int insert ( SarReport record );

	int insertSelective ( SarReport record );

	SarReport selectByPrimaryKey ( Long rid );

	int updateByPrimaryKeySelective ( SarReport record );

	int updateByPrimaryKey ( SarReport record );

	int insertSelectiveDto ( SarReportDto dto );

	SarReport selectByPrimaryKeyDto (@Param("caseId") Long caseId );

	int updateByPrimaryKeySelectiveDto ( SarReportDto dto );
}