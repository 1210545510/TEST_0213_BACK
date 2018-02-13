package com.aml.api.dao;

import com.aml.api.dto.SarFinancialInstitutionBranchDto;
import com.aml.api.pojo.SarFinancialInstitutionBranch;

public interface SarFinancialInstitutionBranchMapper {
	int deleteByPrimaryKey ( Long financialBranchId );

	int insert ( SarFinancialInstitutionBranch record );

	int insertSelective ( SarFinancialInstitutionBranch record );

	SarFinancialInstitutionBranch selectByPrimaryKey ( Long financialBranchId );

	int updateByPrimaryKeySelective ( SarFinancialInstitutionBranch record );

	int updateByPrimaryKey ( SarFinancialInstitutionBranch record );

	int insertSelectiveDto ( SarFinancialInstitutionBranchDto sarFinancialInstitutionBranchDto );

	int updateByPrimaryKeySelectiveDto ( SarFinancialInstitutionBranchDto dto );
}