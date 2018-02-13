package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ActivityTypesDto;
import com.aml.api.pojo.ActivityTypes;
import com.github.pagehelper.Page;

public interface ActivityTypesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( ActivityTypes record );

	int insertSelective ( ActivityTypes record );

	ActivityTypes selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( ActivityTypes record );

	int updateByPrimaryKey ( ActivityTypes record );

	Page<ActivityTypes> getActivityTypesList ( ActivityTypesDto dto );

	List<Map<String, Object>> downActivityTypesExcel ( ActivityTypesDto dto );
}