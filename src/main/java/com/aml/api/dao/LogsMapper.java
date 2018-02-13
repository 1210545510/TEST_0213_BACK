package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.LogsDto;
import com.aml.api.pojo.Logs;
import com.github.pagehelper.Page;

public interface LogsMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( Logs record );

	int insertSelective ( Logs record );

	Logs selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( Logs record );

	int updateByPrimaryKey ( Logs record );

	Page<Logs> getLogsList ( LogsDto dto );

	List<Map<String, Object>> downLogsExcel ( LogsDto dto );
}