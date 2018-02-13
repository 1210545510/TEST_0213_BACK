package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.UserDefindRulesMapper;
import com.aml.api.dto.UserDefindRulesDto;
import com.aml.api.pojo.UserDefindRules;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: UserDefindRulesService
 * @description: UserDefindRulesService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class UserDefindRulesService {
	public static final Logger logger = LoggerFactory.getLogger(UserDefindRulesService.class);

	@Autowired
	UserDefindRulesMapper userDefindRulesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<UserDefindRules> getUserDefindRulesList(UserDefindRulesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return userDefindRulesMapper.getUserDefindRulesList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downUserDefindRulesExcel(UserDefindRulesDto dto) {
		return userDefindRulesMapper.downUserDefindRulesExcel(dto);
	}

	/**
	 * import
	 * @param userDefindRulesList
	 */
	public void insertUserDefindRules(List<UserDefindRules> userDefindRulesList) {
		for (UserDefindRules userDefindRules : userDefindRulesList) {
			userDefindRulesMapper.insertSelective(userDefindRules);
		}
	}
	
}
