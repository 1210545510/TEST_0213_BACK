package com.aml.api.dao;

import com.aml.api.pojo.AcctBalPosnSmry;

public interface AcctBalPosnSmryMapper {
	int deleteByPrimaryKey ( AcctBalPosnSmry key );

	int insert ( AcctBalPosnSmry record );

	int insertSelective ( AcctBalPosnSmry record );

	AcctBalPosnSmry selectByPrimaryKey ( AcctBalPosnSmry key );

	int updateByPrimaryKeySelective ( AcctBalPosnSmry record );

	int updateByPrimaryKey ( AcctBalPosnSmry record );
}