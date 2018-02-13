package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.TemplateRulesDto;
import com.aml.api.pojo.TemplateRules;
import com.github.pagehelper.Page;

public interface TemplateRulesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( TemplateRules record );

	int insertSelective ( TemplateRules record );

	TemplateRules selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( TemplateRules record );

	int updateByPrimaryKey ( TemplateRules record );

	Page<TemplateRules> getTemplateRulesList ( TemplateRulesDto dto );

	List<Map<String, Object>> downTemplateRulesExcel ( TemplateRulesDto dto );
}