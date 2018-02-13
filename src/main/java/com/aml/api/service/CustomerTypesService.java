package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.CustomerTypesMapper;
import com.aml.api.dto.CustomerTypesDto;
import com.aml.api.pojo.CustomerTypes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: CustomerTypesService
 * @description: CustomerTypesService Class
 * @author Novan
 * @date 2017-11-23
 *
 */
@Service
public class CustomerTypesService {
	public static final Logger logger = LoggerFactory.getLogger(CustomerTypesService.class);

	@Autowired
	CustomerTypesMapper customerTypesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<CustomerTypes> getCustomerTypesList(CustomerTypesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return customerTypesMapper.getCustomerTypesList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downCustomerTypesExcel(CustomerTypesDto dto) {
		return customerTypesMapper.downCustomerTypesExcel(dto);
	}

	/**
	 * import
	 * @param customerTypesList
	 */
	public void insertCustomerTypes(List<CustomerTypes> customerTypesList) {
		for (CustomerTypes customerTypes : customerTypesList) {
			customerTypesMapper.insertSelective(customerTypes);
		}
	}
	
}
