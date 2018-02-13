package com.aml.api.dao;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.SarFilingInstitutionDto;
import com.aml.api.pojo.SarFilingInstitution;

public interface SarFilingInstitutionMapper {
	int deleteByPrimaryKey ( Long filingId );

	int insert ( SarFilingInstitution record );

	int insertSelective ( SarFilingInstitution record );

	SarFilingInstitution selectByPrimaryKey ( Long filingId );

	int updateByPrimaryKeySelective ( SarFilingInstitution record );

	int updateByPrimaryKey ( SarFilingInstitution record );

	int insertSelectiveDto ( SarFilingInstitutionDto dto );

	SarFilingInstitution selectByPrimaryKeyDto ( @Param("rid") Long rid );

	int getCount ( Long rid );

	int updateByPrimaryKeySelectiveDto ( SarFilingInstitutionDto dto );
}