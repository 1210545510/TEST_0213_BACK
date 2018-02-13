package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.SarFilingInstitutionMapper;
import com.aml.api.dto.SarFilingInstitutionDto;
import com.aml.api.pojo.SarFilingInstitution;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * 
 * @className: SarFilingInstitutionService
 * @description: SarFilingInstitutionService Class
 * @author Novan
 * @date 2017-12-5
 *
 */
@Service
public class SarFilingInstitutionService {
	public static final Logger logger = LoggerFactory.getLogger(SarFilingInstitutionService.class);
	@Autowired 
	SarFilingInstitutionMapper sarFilingInstitutionMapper;
	
	/**
	 * saveOrUpdate
	 * 
	 * @param dto
	 * @return Result
	 */
	public Result saveOrUpdate ( SarFilingInstitutionDto dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		// 保存
		if (ObjectUtils.isEmpty(dto.getFilingId())) {
			int count = sarFilingInstitutionMapper.getCount(dto.getRid());
			if(count > 0){
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
				return result;
			}
			int flag = sarFilingInstitutionMapper.insertSelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			}
		} else {// 修改
			int flag = sarFilingInstitutionMapper.updateByPrimaryKeySelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
			}
		}

		return result;
	}

	/**
	 * query
	 * @param rid
	 * @return SarFilingInstitution
	 */
	public SarFilingInstitution query(Long rid) {
		return  sarFilingInstitutionMapper.selectByPrimaryKeyDto(rid);
	}

}
