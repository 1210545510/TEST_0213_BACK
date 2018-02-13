package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.ExtUserRequestMapper;
import com.aml.api.dto.ExtUserRequestDto;
import com.aml.api.pojo.ExtUserRequest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: ExtUserRequestService
 * @description: ExtUserRequestService  Class
 * @author Novan
 * @date 2017-11-27
 *
 */
@Service
public class ExtUserRequestService {
	public static final Logger logger = LoggerFactory.getLogger(ExtUserRequestService.class);

	@Autowired
	ExtUserRequestMapper extUserRequestMapper;
	
	/**
	 * query
	 * @param dto
	 * @return
	 */
	public Page<ExtUserRequest> getExtUserRequestList(ExtUserRequestDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return extUserRequestMapper.getExtUserRequestList(dto);
	}

	/**
	 * Export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downExtUserRequestExcel(ExtUserRequestDto dto) {
		return extUserRequestMapper.downExtUserRequestExcel(dto);
	}

	/**
	 * import
	 * @param extUserRequestList
	 */
	public void insertExtUserRequest(List<ExtUserRequest> extUserRequestList) {
		for (ExtUserRequest extUserRequest : extUserRequestList) {
			extUserRequestMapper.insertSelective(extUserRequest);
		}
	}
	
	

}
