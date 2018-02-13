package com.aml.api.dao;

import com.aml.api.dto.CtrReportDto;
import com.aml.api.pojo.CtrReport;

public interface CtrReportMapper {
	int deleteByPrimaryKey ( Long cid );

	int insert ( CtrReport record );

	int insertSelective ( CtrReport record );

	CtrReport selectByPrimaryKey ( Long cid );

	int updateByPrimaryKeySelective ( CtrReport record );

	int updateByPrimaryKey ( CtrReport record );

	void insertSelectiveDto ( CtrReportDto dto );

	CtrReport selectByPrimaryKeyDto ( CtrReportDto dto );

	int updateByPrimaryKeySelectiveDto ( CtrReportDto dto );

}