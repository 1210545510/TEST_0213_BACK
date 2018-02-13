package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.LogsMapper;
import com.aml.api.dto.LogsDto;
import com.aml.api.pojo.Logs;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: LogsService
 * @description: LogsService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class LogsService {
	public static final Logger logger = LoggerFactory.getLogger(LogsService.class);

	@Autowired
	LogsMapper logsMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<Logs> getLogsList(LogsDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return logsMapper.getLogsList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downLogsExcel(LogsDto dto) {
		return logsMapper.downLogsExcel(dto);
	}

	/**
	 * import
	 * @param logsList
	 */
	public void insertLogs(List<Logs> logsList) {
		for (Logs logs : logsList) {
			logsMapper.insertSelective(logs);
		}
	}
	
}
