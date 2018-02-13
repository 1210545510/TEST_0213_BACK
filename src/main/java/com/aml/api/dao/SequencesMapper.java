package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.SequencesDto;
import com.aml.api.pojo.Sequences;
import com.github.pagehelper.Page;

public interface SequencesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( Sequences record );

	int insertSelective ( Sequences record );

	Sequences selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( Sequences record );

	int updateByPrimaryKey ( Sequences record );

	Page<Sequences> getSequencesList ( SequencesDto dto );

	List<Map<String, Object>> downSequencesExcel ( SequencesDto dto );
}