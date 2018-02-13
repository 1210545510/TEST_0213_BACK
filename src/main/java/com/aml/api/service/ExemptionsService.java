package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.ExemptionsMapper;
import com.aml.api.dto.ExemptionsDto;
import com.aml.api.pojo.Exemptions;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: ExemptionsService
 * @description: ExemptionsService Class
 * @author Novan
 * @date 2017-11-23
 *
 */
@Service
public class ExemptionsService {
	public static final Logger logger = LoggerFactory.getLogger(ExemptionsService.class);

	@Autowired 
	ExemptionsMapper exemptionsMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<Exemptions> getExemptionsList(ExemptionsDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return exemptionsMapper.getExemptionsList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downExemptionsExcel(ExemptionsDto dto) {
		return exemptionsMapper.downExemptionsExcel(dto);
	}

	/**
	 * import
	 * @param exemptionsList
	 */
	public void insertExemptions(List<Exemptions> exemptionsList) {
		for (Exemptions exemptions : exemptionsList) {
			exemptionsMapper.insertSelective(exemptions);
		}
	}
	
	
}
