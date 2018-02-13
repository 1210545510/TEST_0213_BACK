package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ActivityMapsDto;
import com.aml.api.pojo.ActivityMaps;
import com.github.pagehelper.Page;

public interface ActivityMapsMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( ActivityMaps record );

	int insertSelective ( ActivityMaps record );

	ActivityMaps selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( ActivityMaps record );

	int updateByPrimaryKey ( ActivityMaps record );
	
	Page<ActivityMaps> getActivityMapsList ( ActivityMapsDto dto );

	List<Map<String, Object>> downActivityMapsExcel ( ActivityMapsDto dto );
}