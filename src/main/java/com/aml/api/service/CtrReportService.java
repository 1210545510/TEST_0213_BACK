package com.aml.api.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.CtrFinancialInstitutionMapper;
import com.aml.api.dao.CtrPersonInfoMapper;
import com.aml.api.dao.CtrReportMapper;
import com.aml.api.dao.CtrTransAmountTypeMapper;
import com.aml.api.dto.CtrFinancialInstitutionDto;
import com.aml.api.dto.CtrPersonInfoDto;
import com.aml.api.dto.CtrReportDto;
import com.aml.api.dto.CtrTransAmountTypeDto;
import com.aml.api.pojo.CtrFinancialInstitution;
import com.aml.api.pojo.CtrPersonInfo;
import com.aml.api.pojo.CtrReport;
import com.aml.api.pojo.CtrTransAmountType;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;

/**
 * 
 * @className: CtrReportService
 * @description: CtrReportService Class
 * @author Novan
 * @date 2017-12-21
 *
 */
@Service
public class CtrReportService {
	public static final Logger logger = LoggerFactory.getLogger(CtrReportService.class);
	@Autowired
	private CtrReportMapper ctrReportMapper;
	
	@Autowired
	private CtrPersonInfoMapper personInfoMapper;
	
	@Autowired
	private CtrTransAmountTypeMapper ctrTransAmountTypeMapper;
	
	@Autowired
	private CtrFinancialInstitutionMapper ctrFinancialInstitutionMapper;

	/**
	 * Currency Transaction Report add
	 * @param dto
	 * @return
	 */
	public CtrReport addCtrReport(CtrReportDto dto) {
		CtrReport report = new CtrReport();
		dto.setCreateTime(new Date());
		ctrReportMapper.insertSelectiveDto(dto);
		report.setCid(dto.getCid());
		return report;
	}

	/**
	 * Currency Transaction Report query
	 * @param dto
	 * @return
	 */
	public CtrReport queryCtrReport(CtrReportDto dto) {
		return ctrReportMapper.selectByPrimaryKeyDto(dto);
	}

	/**
	 * Currency Transaction Report update
	 * @param dto
	 * @return
	 */
	public int updateCtrReport(CtrReportDto dto) {
		return ctrReportMapper.updateByPrimaryKeySelectiveDto(dto) ;
	}

	/**
	 * Person add
	 * @param dto
	 * @return
	 */
	public ApiResult<Void> addCtrPersonInfo(CtrPersonInfoDto dto) {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setCreateTime(new Date());
		int count = personInfoMapper.getCount(dto);
		if(count > 0){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("There are already data that can not be added more!");
			return result;
		}
		int flag = personInfoMapper.insertSelectiveDto(dto);
		if(flag == 0){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Save failure!");
			return result;
		}
		return result;
	}

	/**
	 * Person update
	 * @param dto
	 * @return
	 */
	public int updateCtrPersonInfo(CtrPersonInfoDto dto) {
		return personInfoMapper.updateByPrimaryKeySelectiveDto(dto);
	}
	
	/**
	 * Person query
	 * @param dto
	 * @return
	 */
	public CtrPersonInfo queryCtrPersonInfo(CtrPersonInfoDto dto) {
		return personInfoMapper.queryCtrPersonInfo(dto);
	}

	/**
	 * CtrTransAmountType add
	 * @param dto
	 * @return
	 */
	public ApiResult<Void> addCtrTransAmountType(CtrTransAmountTypeDto dto) {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setCreateTime(new Date());
		int count = ctrTransAmountTypeMapper.getCount(dto);
		if(count > 0){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("There are already data that can not be added more!");
			return result;
		}
		int flag = ctrTransAmountTypeMapper.insertSelectiveDto(dto);
		if(flag == 0){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Save failure!");
			return result;
		}
		return result;
	}

	/**
	 * update
	 * @param dto
	 * @return
	 */
	public int updateCtrTransAmountType(CtrTransAmountTypeDto dto) {
		return ctrTransAmountTypeMapper.updateByPrimaryKeySelectiveDto(dto);
	}

	/**
	 * query
	 * @param dto
	 * @return
	 */
	public CtrTransAmountType queryCtrTransAmountType(CtrTransAmountTypeDto dto) {
		return ctrTransAmountTypeMapper.selectByPrimaryKeyDto(dto);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public ApiResult<Void> addCtrFinancialInstitution(CtrFinancialInstitutionDto dto) {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		dto.setCreateTime(new Date());
		int count = ctrFinancialInstitutionMapper.getCount(dto);
		if(count > 0){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("There are already data that can not be added more!");
			return result;
		}
		int flag = ctrFinancialInstitutionMapper.insertSelectiveDto(dto);
		if(flag == 0){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Save failure!");
			return result;
		}
		return result;
	}

	/**
	 * update
	 * @param dto
	 * @return
	 */
	public int updateCtrFinancialInstitution(CtrFinancialInstitutionDto dto) {
		return ctrFinancialInstitutionMapper.updateByPrimaryKeySelectiveDto(dto);
	}

	/**
	 * query
	 * @param dto
	 * @return
	 */
	public CtrFinancialInstitution queryCtrFinancialInstitution(CtrFinancialInstitutionDto dto) {
		return ctrFinancialInstitutionMapper.selectByPrimaryKeyDto(dto);
	}
	
}
