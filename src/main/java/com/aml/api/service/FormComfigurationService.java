package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.FormComfigurationMapper;
import com.aml.api.dto.FormComfigurationDto;
import com.aml.api.pojo.FormComfiguration;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: FormComfigurationService
 * @description: FormComfigurationService Class
 * @author Novan
 * @date 2017-11-23
 *
 */
@Service
public class FormComfigurationService {
	public static final Logger logger = LoggerFactory.getLogger(FormComfigurationService.class);

	@Autowired
	FormComfigurationMapper formComfigurationMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<FormComfiguration> getFormComfigurationList(FormComfigurationDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return formComfigurationMapper.getFormComfigurationList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downFormComfigurationExcel(FormComfigurationDto dto) {
		return formComfigurationMapper.downFormComfigurationExcel(dto);
	}

	/**
	 * import
	 * @param formComfigurationList
	 */
	public void insertFormComfiguration(List<FormComfiguration> formComfigurationList) {
		for (FormComfiguration formComfiguration : formComfigurationList) {
			formComfigurationMapper.insertSelective(formComfiguration);
		}
	}
	
}
