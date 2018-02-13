package com.aml.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.SarSubjectAddressMapper;
import com.aml.api.dao.SarSubjectAffectMapper;
import com.aml.api.dao.SarSubjectIdentificationMapper;
import com.aml.api.dao.SarSubjectInfoMapper;
import com.aml.api.dao.SarSubjectInfoPhoneMapper;
import com.aml.api.dao.SarSubjectInstitutionMapper;
import com.aml.api.dto.SarSubjectAddressDto;
import com.aml.api.dto.SarSubjectAffectDto;
import com.aml.api.dto.SarSubjectIdentificationDto;
import com.aml.api.dto.SarSubjectInfoDto;
import com.aml.api.dto.SarSubjectInfoPhoneDto;
import com.aml.api.dto.SarSubjectInstitutionDto;
import com.aml.api.pojo.SarSubjectInfo;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;

/**
 * 
 * @className: SarSubjectInfoService
 * @description: SarSubjectInfoService Class
 * @author Novan
 * @date 2017-12-5
 *
 */
@Service
public class SarSubjectInfoService {
	public static final Logger logger = LoggerFactory.getLogger(SarSubjectInfoService.class);
	@Autowired
	SarSubjectInfoMapper sarSubjectInfoMapper;
	@Autowired
	SarSubjectAddressMapper addressMapper;
	@Autowired
	SarSubjectAffectMapper affectMapper;
	@Autowired
	SarSubjectIdentificationMapper identificationMapper;
	@Autowired
	SarSubjectInfoPhoneMapper phoneMapper;
	@Autowired
	SarSubjectInstitutionMapper institutionMapper;

	/**
	 * save
	 * 
	 * @param dto
	 * @return ApiResult<Long>
	 * @throws Exception
	 */
	public ApiResult<Long> save ( SarSubjectInfoDto dto ) throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		int count = sarSubjectInfoMapper.getCount(dto.getRid());
		if (count > 0) {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			return result;
		}
		int flag = sarSubjectInfoMapper.insertSelectiveDto(dto);
		if (flag > 0) {
			for (SarSubjectAddressDto addressDto : dto.getSarSubjectAddress()) {
				addressDto.setSubId(dto.getSubId());
				addressMapper.insertSelectiveDto(addressDto);
			}
			for (SarSubjectAffectDto affectDto : dto.getSarSubjectAffect()) {
				affectDto.setSubId(dto.getSubId());
				affectMapper.insertSelectiveDto(affectDto);
			}
			for (SarSubjectIdentificationDto identificationDto : dto.getSarSubjectIdentification()) {
				identificationDto.setSubId(dto.getSubId());
				identificationMapper.insertSelectiveDto(identificationDto);
			}
			for (SarSubjectInfoPhoneDto phoneDto : dto.getSarSubjectInfoPhone()) {
				phoneDto.setSubId(dto.getSubId());
				phoneMapper.insertSelectiveDto(phoneDto);
			}
			for (SarSubjectInstitutionDto institutionDto : dto.getSarSubjectInstitution()) {
				institutionDto.setSubId(dto.getSubId());
				institutionMapper.insertSelectiveDto(institutionDto);
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
	 * @throws Exception
	 */
	public ApiResult<Long> update ( SarSubjectInfoDto dto ) throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		int flag = sarSubjectInfoMapper.updateByPrimaryKeySelectiveDto(dto);
		if (flag > 0) {
			for (SarSubjectAddressDto addressDto : dto.getSarSubjectAddress()) {
				addressDto.setSubId(dto.getSubId());
				addressMapper.updateByPrimaryKeySelectiveDto(addressDto);
			}
			for (SarSubjectAffectDto affectDto : dto.getSarSubjectAffect()) {
				affectDto.setSubId(dto.getSubId());
				affectMapper.updateByPrimaryKeySelectiveDto(affectDto);
			}
			for (SarSubjectIdentificationDto identificationDto : dto.getSarSubjectIdentification()) {
				identificationDto.setSubId(dto.getSubId());
				identificationMapper.updateByPrimaryKeySelectiveDto(identificationDto);
			}
			for (SarSubjectInfoPhoneDto phoneDto : dto.getSarSubjectInfoPhone()) {
				phoneDto.setSubId(dto.getSubId());
				phoneMapper.updateByPrimaryKeySelectiveDto(phoneDto);
			}
			for (SarSubjectInstitutionDto institutionDto : dto.getSarSubjectInstitution()) {
				institutionDto.setSubId(dto.getSubId());
				institutionMapper.updateByPrimaryKeySelectiveDto(institutionDto);
			}
		} else {
			result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
		}
		return result;
	}

	/**
	 * query
	 * 
	 * @param rid
	 * @return SarSubjectInfo
	 */
	public SarSubjectInfo query ( Long rid ) {
		SarSubjectInfo sarSubjectInfo = sarSubjectInfoMapper.selectByPrimaryKeyDto(rid);
		return sarSubjectInfo;
	}
}
