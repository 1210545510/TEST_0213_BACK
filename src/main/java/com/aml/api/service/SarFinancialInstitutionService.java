package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.SarFinancialInstitutionBranchMapper;
import com.aml.api.dao.SarFinancialInstitutionMapper;
import com.aml.api.dto.SarFinancialInstitutionBranchDto;
import com.aml.api.dto.SarFinancialInstitutionDto;
import com.aml.api.pojo.SarFinancialInstitution;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;

/**
 * 
 * @className: SarFinancialInstitutionService
 * @description: SarFinancialInstitutionService Class
 * @author Novan
 * @date 2017-12-5
 *
 */
@Service
public class SarFinancialInstitutionService {
	public static final Logger logger = LoggerFactory.getLogger(SarFinancialInstitutionService.class);
	@Autowired
	SarFinancialInstitutionMapper financialInstitutionMapper;
	@Autowired
	SarFinancialInstitutionBranchMapper branchMapper;

	/**
	 * save
	 * 
	 * @param dto
	 * @return ApiResult<Long>
	 * @throws Exception
	 */
	public ApiResult<Long> save ( SarFinancialInstitutionDto dto ) throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		int count = financialInstitutionMapper.getCount(dto.getRid());
		if (count > 0) {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			return result;
		}
		int flag = financialInstitutionMapper.insertSelectiveDto(dto);
		if (flag > 0) {
			if (!ObjectUtils.isEmpty(dto.getBranch())) {
				for (SarFinancialInstitutionBranchDto branchDto : dto.getBranch()) {
					branchDto.setFinancialId(dto.getFinancialId());
					branchMapper.insertSelectiveDto(branchDto);
				}
			}
		} else {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
		}
		return result;
	}

	/**
	 * update
	 * 
	 * @param dto
	 * @return ApiResult<Long>
	 */
	public ApiResult<Long> update ( SarFinancialInstitutionDto dto ) throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		int flag = financialInstitutionMapper.updateByPrimaryKeySelectiveDto(dto);
		if (flag > 0) {
			for (SarFinancialInstitutionBranchDto branchDto : dto.getBranch()) {
				branchDto.setFinancialId(dto.getFinancialId());
				branchMapper.updateByPrimaryKeySelectiveDto(branchDto);
			}
		}
		return result;
	}
	
	/**
	 * query
	 * 
	 * @param rid
	 * @return SarFinancialInstitution
	 */
	public SarFinancialInstitution query ( Long rid ) {
		return financialInstitutionMapper.selectByPrimaryKeyDto(rid);
	}
}
