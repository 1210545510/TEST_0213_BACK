package com.aml.api.dao;

import com.aml.api.pojo.AcctTrxnSmryDaily;

public interface AcctTrxnSmryDailyMapper {
	int deleteByPrimaryKey ( AcctTrxnSmryDaily key );

	int insert ( AcctTrxnSmryDaily record );

	int insertSelective ( AcctTrxnSmryDaily record );

	AcctTrxnSmryDaily selectByPrimaryKey ( AcctTrxnSmryDaily key );

	int updateByPrimaryKeySelective ( AcctTrxnSmryDaily record );

	int updateByPrimaryKey ( AcctTrxnSmryDaily record );

}