package com.aml.api.dao;

import com.aml.api.dto.SarSubjectAffectDto;
import com.aml.api.pojo.SarSubjectAffect;

public interface SarSubjectAffectMapper {
	int deleteByPrimaryKey ( Long affectId );

	int insert ( SarSubjectAffect record );

	int insertSelective ( SarSubjectAffect record );

	SarSubjectAffect selectByPrimaryKey ( Long affectId );

	int updateByPrimaryKeySelective ( SarSubjectAffect record );

	int updateByPrimaryKey ( SarSubjectAffect record );

	int insertSelectiveDto ( SarSubjectAffectDto affectDto );

	int updateByPrimaryKeySelectiveDto ( SarSubjectAffectDto affectDto );
}