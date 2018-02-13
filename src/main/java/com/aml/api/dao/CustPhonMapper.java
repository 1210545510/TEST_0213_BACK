package com.aml.api.dao;

import com.aml.api.pojo.CustPhon;

public interface CustPhonMapper {
	int deleteByPrimaryKey ( CustPhon key );

	int insert ( CustPhon record );

	int insertSelective ( CustPhon record );

	CustPhon selectByPrimaryKey ( CustPhon key );

	int updateByPrimaryKeySelective ( CustPhon record );

	int updateByPrimaryKey ( CustPhon record );
}