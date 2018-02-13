package com.aml.api.dao;

import com.aml.api.dto.SarSubjectIdentificationDto;
import com.aml.api.pojo.SarSubjectIdentification;

public interface SarSubjectIdentificationMapper {
	int deleteByPrimaryKey ( Long identifyId );

	int insert ( SarSubjectIdentification record );

	int insertSelective ( SarSubjectIdentification record );

	SarSubjectIdentification selectByPrimaryKey ( Long identifyId );

	int updateByPrimaryKeySelective ( SarSubjectIdentification record );

	int updateByPrimaryKey ( SarSubjectIdentification record );

	int insertSelectiveDto ( SarSubjectIdentificationDto identificationDto );

	int updateByPrimaryKeySelectiveDto ( SarSubjectIdentificationDto identificationDto );
}