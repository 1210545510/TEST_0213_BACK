package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.CountriesMapper;
import com.aml.api.dto.ActivityTypesDto;
import com.aml.api.dto.CountriesDto;
import com.aml.api.pojo.Countries;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: CountriesService
 * @description: CountriesService Class
 * @author Novan
 * @date 2017-11-23
 *
 */
@Service
public class CountriesService {
	public static final Logger logger = LoggerFactory.getLogger(CountriesService.class);
	
	@Autowired
	CountriesMapper countriesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<Countries> getCountriesList(ActivityTypesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return countriesMapper.getCountriesList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downCountriesExcel(CountriesDto dto) {
		return countriesMapper.downCountriesExcel(dto);
	}

	/**
	 * import
	 * @param countriesList
	 */
	public void insertCountries(List<Countries> countriesList) {
		for (Countries countries : countriesList) {
			countriesMapper.insertSelective(countries);
		}
	}
	
}
