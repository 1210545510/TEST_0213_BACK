package com.aml.api.dao;

import com.aml.api.dto.CtrFinancialInstitutionDto;
import com.aml.api.pojo.CtrFinancialInstitution;

public interface CtrFinancialInstitutionMapper {
	int deleteByPrimaryKey ( Long financialId );

	int insert ( CtrFinancialInstitution record );

	int insertSelective ( CtrFinancialInstitution record );

	CtrFinancialInstitution selectByPrimaryKey ( Long financialId );

	int updateByPrimaryKeySelective ( CtrFinancialInstitution record );

	int updateByPrimaryKey ( CtrFinancialInstitution record );

	int getCount ( CtrFinancialInstitutionDto dto );

	int insertSelectiveDto ( CtrFinancialInstitutionDto dto );

	int updateByPrimaryKeySelectiveDto ( CtrFinancialInstitutionDto dto );

	CtrFinancialInstitution selectByPrimaryKeyDto ( CtrFinancialInstitutionDto dto );
}