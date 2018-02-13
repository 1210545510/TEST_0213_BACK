package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.AccountTypesDto;
import com.aml.api.pojo.AccountTypes;
import com.github.pagehelper.Page;

public interface AccountTypesMapper {
	int deleteByPrimaryKey ( Long id );

	int insert ( AccountTypes record );

	int insertSelective ( AccountTypes record );

	AccountTypes selectByPrimaryKey ( Long id );

	int updateByPrimaryKeySelective ( AccountTypes record );

	int updateByPrimaryKey ( AccountTypes record );

	Page<AccountTypes> getAccountTypesList ( AccountTypesDto dto );

	List<Map<String, Object>> downAccountTypesExcel ( AccountTypesDto dto );

	int getAccountTypesCountByCode ( AccountTypes accountTypes );
}