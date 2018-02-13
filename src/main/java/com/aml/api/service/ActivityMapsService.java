package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.ActivityMapsMapper;
import com.aml.api.dto.ActivityMapsDto;
import com.aml.api.pojo.ActivityMaps;
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
public class ActivityMapsService {
	public static final Logger logger = LoggerFactory.getLogger(ActivityMapsService.class);
	
	@Autowired
	ActivityMapsMapper activityMapsMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return Page<AccountTypes>
	 */
	public Page<ActivityMaps> getActivityMapsList(ActivityMapsDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return activityMapsMapper.getActivityMapsList(dto);
	}

	/**
	 * Export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downActivityMapsExcel(ActivityMapsDto dto) {
		return activityMapsMapper.downActivityMapsExcel(dto);
	}

	/**
	 * import
	 * @param activityMapsList
	 */
	public void insertActivityMaps(List<ActivityMaps> activityMapsList) {
		for (ActivityMaps activityMaps : activityMapsList) {
			activityMapsMapper.insertSelective(activityMaps);
		}
	}

	
}
