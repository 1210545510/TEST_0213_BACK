package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.CustomerTypesDto;
import com.aml.api.pojo.CustomerTypes;
import com.github.pagehelper.Page;

public interface CustomerTypesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( CustomerTypes record );

	int insertSelective ( CustomerTypes record );

	CustomerTypes selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( CustomerTypes record );

	int updateByPrimaryKey ( CustomerTypes record );

	Page<CustomerTypes> getCustomerTypesList ( CustomerTypesDto dto );

	List<Map<String, Object>> downCustomerTypesExcel ( CustomerTypesDto dto );
}