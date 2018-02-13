package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.RelationshipTypesDto;
import com.aml.api.pojo.RelationshipTypes;
import com.github.pagehelper.Page;

public interface RelationshipTypesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( RelationshipTypes record );

	int insertSelective ( RelationshipTypes record );

	RelationshipTypes selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( RelationshipTypes record );

	int updateByPrimaryKey ( RelationshipTypes record );

	Page<RelationshipTypes> getRelationshipTypesList ( RelationshipTypesDto dto );

	List<Map<String, Object>> downRelationshipTypesExcel ( RelationshipTypesDto dto );
}