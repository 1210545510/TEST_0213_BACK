package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.Data314AMapper;
import com.aml.api.dto.Data314ADto;
import com.aml.api.pojo.Data314A;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: Data314AService
 * @description: Data314AService  Class
 * @author Novan
 * @date 2017-11-27
 *
 */
@Service
public class Data314AService {
	public static final Logger logger = LoggerFactory.getLogger(Data314AService.class);

	@Autowired
	Data314AMapper data314aMapper; 
	
	/**
	 * query
	 * @param dto
	 * @return
	 */
	public Page<Data314A> getData314AList(Data314ADto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return data314aMapper.getData314AList(dto);
	}

	/**
	 * Export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downData314AExcel(Data314ADto dto) {
		return data314aMapper.downData314AExcel(dto);
	}

	/**
	 * import
	 * @param data314aList
	 */
	public void insertData314A(List<Data314A> data314aList) {
		for (Data314A data314a : data314aList) {
			data314aMapper.insertSelective(data314a);
		}
	}
	

}
