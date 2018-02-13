package com.aml.api.dao;

import com.aml.api.pojo.CustAddr;

public interface CustAddrMapper {
	int deleteByPrimaryKey ( CustAddr key );

	int insert ( CustAddr record );

	int insertSelective ( CustAddr record );

	CustAddr selectByPrimaryKey ( CustAddr key );

	int updateByPrimaryKeySelective ( CustAddr record );

	int updateByPrimaryKey ( CustAddr record );
}