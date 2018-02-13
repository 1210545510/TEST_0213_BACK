package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ActivityTypesDto;
import com.aml.api.dto.CountriesDto;
import com.aml.api.pojo.Countries;
import com.github.pagehelper.Page;

public interface CountriesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( Countries record );

	int insertSelective ( Countries record );

	Countries selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( Countries record );

	int updateByPrimaryKey ( Countries record );

	Page<Countries> getCountriesList ( ActivityTypesDto dto );

	List<Map<String, Object>> downCountriesExcel ( CountriesDto dto );
}