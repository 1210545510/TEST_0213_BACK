package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.CustMapper;
import com.aml.api.dto.CustDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: CustService
 * @description: CustService  Class
 * @author Novan
 * @date 2017-11-27
 *
 */
@Service
public class CustService {
	public static final Logger logger = LoggerFactory.getLogger(CustService.class);

	@Autowired
	CustMapper custMapper;
	
	/**
	 * query
	 * @param dto
	 * @return
	 */
	public Page<CustDto> getCustList(CustDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return custMapper.getCustList(dto);
	}

	/**
	 * Export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downCustExcel(CustDto dto) {
		return custMapper.downCustExcel(dto);
	}
	
	

}
