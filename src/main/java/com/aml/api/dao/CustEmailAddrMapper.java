package com.aml.api.dao;

import com.aml.api.pojo.CustEmailAddr;

public interface CustEmailAddrMapper {
	int deleteByPrimaryKey ( CustEmailAddr key );

	int insert ( CustEmailAddr record );

	int insertSelective ( CustEmailAddr record );

	CustEmailAddr selectByPrimaryKey ( CustEmailAddr key );

	int updateByPrimaryKeySelective ( CustEmailAddr record );

	int updateByPrimaryKey ( CustEmailAddr record );
}