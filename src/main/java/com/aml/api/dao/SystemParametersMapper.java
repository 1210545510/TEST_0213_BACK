package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.SystemParametersDto;
import com.aml.api.pojo.SystemParameters;
import com.github.pagehelper.Page;

public interface SystemParametersMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( SystemParameters record );

	int insertSelective ( SystemParameters record );

	SystemParameters selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( SystemParameters record );

	int updateByPrimaryKey ( SystemParameters record );

	Page<SystemParameters> getSystemParametersList ( SystemParametersDto dto );

	List<Map<String, Object>> downSystemParametersExcel ( SystemParametersDto dto );

	int getSystemParametersCountByCode ( SystemParameters systemParameters );
}