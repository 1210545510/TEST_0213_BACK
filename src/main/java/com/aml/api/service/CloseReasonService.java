package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.CloseReasonMapper;
import com.aml.api.dto.CloseReasonDto;
import com.aml.api.pojo.CloseReason;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: CloseReasonService
 * @description: CloseReasonService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class CloseReasonService {
	public static final Logger logger = LoggerFactory.getLogger(CloseReasonService.class);

	@Autowired
	CloseReasonMapper closeReasonMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<CloseReason> getCloseReasonList(CloseReasonDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return closeReasonMapper.getCloseReasonList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downCloseReasonExcel(CloseReasonDto dto) {
		return closeReasonMapper.downCloseReasonExcel(dto);
	}

	/**
	 * import
	 * @param closeReasonList
	 */
	public void insertCloseReason(List<CloseReason> closeReasonList) {
		for (CloseReason closeReason : closeReasonList) {
			closeReasonMapper.insertSelective(closeReason);
		}
	}

	/**
	 * 校验code是否存在
	 * @param closeReason
	 * @return
	 */
	public int getCloseReasonCountByCode(CloseReason closeReason) {
		return closeReasonMapper.getCloseReasonCountByCode(closeReason);
	}
	
}
