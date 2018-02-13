package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.SarNarrativeMapper;
import com.aml.api.dto.SarNarrativeDto;
import com.aml.api.pojo.SarNarrative;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * 
 * @className: SarNarrativeService
 * @description: SarNarrativeService Class
 * @author Novan
 * @date 2017-12-6
 *
 */
@Service
public class SarNarrativeService {
	public static final Logger logger = LoggerFactory.getLogger(SarNarrativeService.class);
	@Autowired 
	SarNarrativeMapper sarNarrativeMapper;
	
	/**
	 * saveOrUpdate
	 * 
	 * @param dto
	 * @return Result
	 */
	public Result saveOrUpdate ( SarNarrativeDto dto ) {	
		Result result = new Result(ErrorTypeEnum.OK);
		// 保存
		if (ObjectUtils.isEmpty(dto.getNarrativeId())) {
			int count = sarNarrativeMapper.getCount(dto.getRid());
			if(count > 0){
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
				return result;
			}
			int flag = sarNarrativeMapper.insertSelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			}
		} else {// 修改
			int flag = sarNarrativeMapper.updateByPrimaryKeySelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
			}
		}

		return result;
	}

	/**
	 * query
	 * @param rid
	 * @return
	 */
	public SarNarrative query(Long rid) {
		return sarNarrativeMapper.selectByPrimaryKeyDto(rid);
	}
	
}
