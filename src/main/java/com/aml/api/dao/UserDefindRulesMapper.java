package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.UserDefindRulesDto;
import com.aml.api.pojo.UserDefindRules;
import com.github.pagehelper.Page;

public interface UserDefindRulesMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( UserDefindRules record );

	int insertSelective ( UserDefindRules record );

	UserDefindRules selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( UserDefindRules record );

	int updateByPrimaryKey ( UserDefindRules record );

	Page<UserDefindRules> getUserDefindRulesList ( UserDefindRulesDto dto );

	List<Map<String, Object>> downUserDefindRulesExcel ( UserDefindRulesDto dto );
}