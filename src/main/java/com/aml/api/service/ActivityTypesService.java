package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.ActivityTypesMapper;
import com.aml.api.dto.ActivityTypesDto;
import com.aml.api.pojo.ActivityTypes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: ActivityMapsService
 * @description: ActivityMapsService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class ActivityTypesService {
	public static final Logger logger = LoggerFactory.getLogger(ActivityTypesService.class);
	
	@Autowired
	ActivityTypesMapper activityTypesMapper;
	

	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<ActivityTypes> getActivityTypesList(ActivityTypesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return activityTypesMapper.getActivityTypesList(dto);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downActivityTypesExcel(ActivityTypesDto dto) {
		return activityTypesMapper.downActivityTypesExcel(dto);
	}

	/**
	 * import
	 * @param activityTypesList
	 */
	public void insertActivityTypes(List<ActivityTypes> activityTypesList) {
		for (ActivityTypes activityTypes : activityTypesList) {
			activityTypesMapper.insertSelective(activityTypes);
		}
	}

	
}
