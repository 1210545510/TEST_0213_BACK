package com.aml.api.dao;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.SarFinancialInstitutionDto;
import com.aml.api.pojo.SarFinancialInstitution;

public interface SarFinancialInstitutionMapper {
	int deleteByPrimaryKey ( Long financialId );

	int insert ( SarFinancialInstitution record );

	int insertSelective ( SarFinancialInstitution record );

	SarFinancialInstitution selectByPrimaryKey ( Long financialId );

	int updateByPrimaryKeySelective ( SarFinancialInstitution record );

	int updateByPrimaryKey ( SarFinancialInstitution record );

	SarFinancialInstitution selectByPrimaryKeyDto ( @Param("rid") Long rid );

	int insertSelectiveDto ( SarFinancialInstitutionDto dto );

	int getCount ( Long rid );

	int updateByPrimaryKeySelectiveDto ( SarFinancialInstitutionDto dto );
}