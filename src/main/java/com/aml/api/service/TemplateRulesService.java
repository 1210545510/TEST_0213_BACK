package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.TemplateRulesMapper;
import com.aml.api.dto.TemplateRulesDto;
import com.aml.api.pojo.TemplateRules;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: TemplateRulesService
 * @description: TemplateRulesService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class TemplateRulesService {
	public static final Logger logger = LoggerFactory.getLogger(TemplateRulesService.class);

	@Autowired
	TemplateRulesMapper templateRulesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<TemplateRules> getTemplateRulesList(TemplateRulesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return templateRulesMapper.getTemplateRulesList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downTemplateRulesExcel(TemplateRulesDto dto) {
		return templateRulesMapper.downTemplateRulesExcel(dto);
	}

	/**
	 * import
	 * @param templateRulesList
	 */
	public void insertTemplateRules(List<TemplateRules> templateRulesList) {
		for (TemplateRules templateRules : templateRulesList) {
			templateRulesMapper.insertSelective(templateRules);
		}
	}
	
}
