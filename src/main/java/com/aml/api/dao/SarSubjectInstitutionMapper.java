package com.aml.api.dao;

import com.aml.api.dto.SarSubjectInstitutionDto;
import com.aml.api.pojo.SarSubjectInstitution;

public interface SarSubjectInstitutionMapper {
	int deleteByPrimaryKey ( Long relateId );

	int insert ( SarSubjectInstitution record );

	int insertSelective ( SarSubjectInstitution record );

	SarSubjectInstitution selectByPrimaryKey ( Long relateId );

	int updateByPrimaryKeySelective ( SarSubjectInstitution record );

	int updateByPrimaryKey ( SarSubjectInstitution record );

	int insertSelectiveDto ( SarSubjectInstitutionDto institutionDto );

	int updateByPrimaryKeySelectiveDto ( SarSubjectInstitutionDto institutionDto );
}