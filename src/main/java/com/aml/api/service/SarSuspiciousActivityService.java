package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.SarSuspiciousActivityMapper;
import com.aml.api.dto.SarSuspiciousActivityDto;
import com.aml.api.pojo.SarSuspiciousActivity;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * 
 * @className: SarSuspiciousActivityService
 * @description: SarSuspiciousActivityService Class
 * @author Novan
 * @date 2017-12-6
 *
 */
@Service
public class SarSuspiciousActivityService {
	public static final Logger logger = LoggerFactory.getLogger(SarSuspiciousActivityService.class);
	@Autowired
	SarSuspiciousActivityMapper sarSuspiciousActivityMapper;

	/**
	 * saveOrUpdate
	 * 
	 * @param dto
	 * @return Result
	 */
	public Result saveOrUpdate ( SarSuspiciousActivityDto dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		// 保存
		if (ObjectUtils.isEmpty(dto.getSusId())) {
			int count = sarSuspiciousActivityMapper.getCount(dto.getRid());
			if (count > 0) {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
				return result;
			}
			int flag = sarSuspiciousActivityMapper.insertSelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			}
		} else {// 修改
			int flag = sarSuspiciousActivityMapper.updateByPrimaryKeySelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
			}
		}

		return result;
	}

	/**
	 * query
	 * 
	 * @param rid
	 * @return SarSuspiciousActivity
	 */
	public SarSuspiciousActivity query ( Long rid ) {
		return sarSuspiciousActivityMapper.selectByPrimaryKeyDto(rid);
	}

}
