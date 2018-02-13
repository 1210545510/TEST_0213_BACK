package com.aml.api.dao;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.SarNarrativeDto;
import com.aml.api.pojo.SarNarrative;

public interface SarNarrativeMapper {
	int deleteByPrimaryKey ( Long narrativeId );

	int insert ( SarNarrative record );

	int insertSelective ( SarNarrative record );

	SarNarrative selectByPrimaryKey ( Long narrativeId );

	int updateByPrimaryKeySelective ( SarNarrative record );

	int updateByPrimaryKeyWithBLOBs ( SarNarrative record );

	int updateByPrimaryKey ( SarNarrative record );

	SarNarrative selectByPrimaryKeyDto ( @Param("rid") Long rid );

	int getCount ( Long rid );

	int insertSelectiveDto ( SarNarrativeDto dto );

	int updateByPrimaryKeySelectiveDto ( SarNarrativeDto dto );
}