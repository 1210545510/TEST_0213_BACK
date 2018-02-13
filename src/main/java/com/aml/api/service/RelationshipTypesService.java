package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.RelationshipTypesMapper;
import com.aml.api.dto.RelationshipTypesDto;
import com.aml.api.pojo.RelationshipTypes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: RelationshipTypesService
 * @description: RelationshipTypesService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class RelationshipTypesService {
	public static final Logger logger = LoggerFactory.getLogger(RelationshipTypesService.class);

	@Autowired
	RelationshipTypesMapper relationshipTypesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<RelationshipTypes> getRelationshipTypesList(RelationshipTypesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return relationshipTypesMapper.getRelationshipTypesList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downRelationshipTypesExcel(RelationshipTypesDto dto) {
		return relationshipTypesMapper.downRelationshipTypesExcel(dto);
	}

	/**
	 * import
	 * @param relationshipTypesList
	 */
	public void insertRelationshipTypes(List<RelationshipTypes> relationshipTypesList) {
		for (RelationshipTypes relationshipTypes : relationshipTypesList) {
			relationshipTypesMapper.insertSelective(relationshipTypes);
		}
	}
	
}
