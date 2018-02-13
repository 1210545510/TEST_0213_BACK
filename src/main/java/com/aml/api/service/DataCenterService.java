package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.RfiMapper;
import com.aml.api.dto.Data314ADto;
import com.aml.api.pojo.Rfi;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author GLS
 *
 */
@Service
public class DataCenterService {
	public static final Logger logger = LoggerFactory.getLogger(DataCenterService.class);
	
	@Autowired
	private RfiMapper rfiMapper;

	/**
	 * RFI
	 * @param dto
	 * @return
	 */
	public Page<Rfi> queryRfiList(Data314ADto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return rfiMapper.queryRfiList(dto);
	}
	

}
