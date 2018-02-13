package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.SarReportMapper;
import com.aml.api.dto.SarReportDto;
import com.aml.api.pojo.SarReport;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * 
 * @className: SarReportService
 * @description: SarReportService Class
 * @author Novan
 * @date 2017-12-5
 *
 */
@Service
public class SarReportService {
	public static final Logger logger = LoggerFactory.getLogger(SarReportService.class);

	@Autowired
	SarReportMapper sarReportMapper;

	/**
	 * saveOrUpdate
	 * 
	 * @param dto
	 * @return Result
	 */
	public Result saveOrUpdate ( SarReportDto dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		// 保存
		if (ObjectUtils.isEmpty(dto.getRid())) {
			int flag = sarReportMapper.insertSelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			}
		} else {// 修改
			int flag = sarReportMapper.updateByPrimaryKeySelectiveDto(dto);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
			}
		}

		return result;
	}

	/**
	 * query
	 * 
	 * @param caseId
	 * @return SarReport
	 */
	public SarReport query ( Long caseId ) {
		return sarReportMapper.selectByPrimaryKeyDto(caseId);
	}

}
