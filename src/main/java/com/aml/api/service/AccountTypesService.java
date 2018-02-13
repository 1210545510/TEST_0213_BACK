package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.AccountTypesMapper;
import com.aml.api.dto.AccountTypesDto;
import com.aml.api.pojo.AccountTypes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: AccountTypesService
 * @description: AccountTypesService  Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class AccountTypesService {
	public static final Logger logger = LoggerFactory.getLogger(AccountTypesService.class);
	
	@Autowired
	AccountTypesMapper accountTypesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return Page<AccountTypes>
	 */
	public Page<AccountTypes> getAccountTypesList(AccountTypesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		Page<AccountTypes> data = accountTypesMapper.getAccountTypesList(dto);
		return data;
	}

	/**
	 * Derived data
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downAccountTypesExcel(AccountTypesDto dto) {
		return accountTypesMapper.downAccountTypesExcel(dto);
	}

	/**
	 * Cycle adding AccountTypes
	 * @param accountTypesList
	 */
	public void insertAccountTypes(List<AccountTypes> accountTypesList) {
		for (AccountTypes accountTypes : accountTypesList) {
			accountTypesMapper.insertSelective(accountTypes);
		}
	}

	/**
	 * 校验code是否存在
	 * @param accountTypes
	 * @return
	 */
	public int getAccountTypesCountByCode(AccountTypes accountTypes) {
		return accountTypesMapper.getAccountTypesCountByCode(accountTypes);
	}

}
