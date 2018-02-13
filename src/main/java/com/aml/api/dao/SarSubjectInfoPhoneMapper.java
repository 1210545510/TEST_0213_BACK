package com.aml.api.dao;

import com.aml.api.dto.SarSubjectInfoPhoneDto;
import com.aml.api.pojo.SarSubjectInfoPhone;

public interface SarSubjectInfoPhoneMapper {
	int deleteByPrimaryKey ( Long id );

	int insert ( SarSubjectInfoPhone record );

	int insertSelective ( SarSubjectInfoPhone record );

	SarSubjectInfoPhone selectByPrimaryKey ( Long id );

	int updateByPrimaryKeySelective ( SarSubjectInfoPhone record );

	int updateByPrimaryKey ( SarSubjectInfoPhone record );

	int insertSelectiveDto ( SarSubjectInfoPhoneDto phoneDto );

	int updateByPrimaryKeySelectiveDto ( SarSubjectInfoPhoneDto phoneDto );
}