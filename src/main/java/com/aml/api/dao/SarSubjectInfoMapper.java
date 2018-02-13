package com.aml.api.dao;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.SarSubjectInfoDto;
import com.aml.api.pojo.SarSubjectInfo;

public interface SarSubjectInfoMapper {
	int deleteByPrimaryKey ( Long subId );

	int insert ( SarSubjectInfo record );

	int insertSelective ( SarSubjectInfo record );

	SarSubjectInfo selectByPrimaryKey ( Long subId );

	int updateByPrimaryKeySelective ( SarSubjectInfo record );

	int updateByPrimaryKey ( SarSubjectInfo record );

	SarSubjectInfo selectByPrimaryKeyDto ( @Param("rid") Long rid );

	int insertSelectiveDto ( SarSubjectInfoDto dto );

	int getCount ( Long rid);

	int updateByPrimaryKeySelectiveDto ( SarSubjectInfoDto dto );
}