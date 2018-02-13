package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.SystemParametersMapper;
import com.aml.api.dto.SystemParametersDto;
import com.aml.api.pojo.SystemParameters;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: SystemParametersService
 * @description: SystemParametersService Class
 * @author Novan
 * @date 2017-11-23
 *
 */
@Service
public class SystemParametersService {
	public static final Logger logger = LoggerFactory.getLogger(SystemParametersService.class);

	@Autowired
	SystemParametersMapper systemParametersMapper;

	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<SystemParameters> getSystemParametersList(SystemParametersDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return systemParametersMapper.getSystemParametersList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downSystemParametersExcel(SystemParametersDto dto) {
		return systemParametersMapper.downSystemParametersExcel(dto);
	}

	/**
	 * import
	 * @param systemParametersList
	 */
	public void insertSystemParameters(List<SystemParameters> systemParametersList) {
		for (SystemParameters systemParameters : systemParametersList) {
			systemParametersMapper.insertSelective(systemParameters);
		}
	}

	/**
	 * 校验code是否存在
	 * @param systemParameters
	 * @return
	 */
	public int getSystemParametersCountByCode(SystemParameters systemParameters) {
		return systemParametersMapper.getSystemParametersCountByCode(systemParameters);
	}
	
}
