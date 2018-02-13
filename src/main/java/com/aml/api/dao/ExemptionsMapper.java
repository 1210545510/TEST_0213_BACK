package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ExemptionsDto;
import com.aml.api.pojo.Exemptions;
import com.github.pagehelper.Page;

public interface ExemptionsMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( Exemptions record );

	int insertSelective ( Exemptions record );

	Exemptions selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( Exemptions record );

	int updateByPrimaryKey ( Exemptions record );

	Page<Exemptions> getExemptionsList ( ExemptionsDto dto );

	List<Map<String, Object>> downExemptionsExcel ( ExemptionsDto dto );
}