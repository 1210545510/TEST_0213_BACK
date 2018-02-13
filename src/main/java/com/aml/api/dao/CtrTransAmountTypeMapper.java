package com.aml.api.dao;

import com.aml.api.dto.CtrTransAmountTypeDto;
import com.aml.api.pojo.CtrTransAmountType;

public interface CtrTransAmountTypeMapper {
	int deleteByPrimaryKey ( Long transId );

	int insert ( CtrTransAmountType record );

	int insertSelective ( CtrTransAmountType record );

	CtrTransAmountType selectByPrimaryKey ( Long transId );

	int updateByPrimaryKeySelective ( CtrTransAmountType record );

	int updateByPrimaryKey ( CtrTransAmountType record );

	int getCount ( CtrTransAmountTypeDto dto );

	int insertSelectiveDto ( CtrTransAmountTypeDto dto );

	int updateByPrimaryKeySelectiveDto ( CtrTransAmountTypeDto dto );

	CtrTransAmountType selectByPrimaryKeyDto ( CtrTransAmountTypeDto dto );
}