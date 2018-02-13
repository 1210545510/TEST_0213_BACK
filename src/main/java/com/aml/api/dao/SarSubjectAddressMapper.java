package com.aml.api.dao;

import com.aml.api.dto.SarSubjectAddressDto;
import com.aml.api.pojo.SarSubjectAddress;

public interface SarSubjectAddressMapper {
	int deleteByPrimaryKey ( Long subAddrId );

	int insert ( SarSubjectAddress record );

	int insertSelective ( SarSubjectAddress record );

	SarSubjectAddress selectByPrimaryKey ( Long subAddrId );

	int updateByPrimaryKeySelective ( SarSubjectAddress record );

	int updateByPrimaryKey ( SarSubjectAddress record );

	int insertSelectiveDto ( SarSubjectAddressDto addressDto );

	int updateByPrimaryKeySelectiveDto ( SarSubjectAddressDto addressDto );
}