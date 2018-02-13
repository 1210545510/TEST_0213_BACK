package com.aml.api.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.aml.api.pojo.Acct;
import com.github.pagehelper.Page;

public interface AcctMapper {
	int deleteByPrimaryKey ( String acctIntrlId );

	int insert ( Acct record );

	int insertSelective ( Acct record );

	Acct selectByPrimaryKey ( String acctIntrlId );

	int updateByPrimaryKeySelective ( Acct record );

	int updateByPrimaryKey ( Acct record );

	Set<String> queryAlertAcctId ( Integer originalId );

	List<Map<String, Object>> queryAlertAcctInfo ( Integer originalId );

	Map<String, Object> getAcctInfo ( @Param("accountId") String accountId );

	Map<String, Object> queryAcctSnapshotInfo ( @Param("accountId") String accountId,
			@Param("month") Integer month );
	
	Map<String, Object> queryAcctBalance ( @Param("accountId") String accountId,
			@Param("month") Integer month );

	Map<String, Object> queryAlertAcctInfo ( String acctId );

	List<Map<String, Object>> queryAlertAcctList ( Integer originalId );
	
	Page<Map<String, Object>> queryAcctSummaryInfo ( @Param("accountId") String accountId );
	
	Page<Map<String, Object>> queryAcctSummaryInfoSnapshot ( @Param("accountId") String accountId );

	Set<String> queryAlertOriginalIds ( Long alertId );

	Set<String> queryCaseOriginalIds ( Long caseId );

	List<Map<String, Object>> queryAlertAcctInfoByAidOrCid ( @Param("accountId") String accountId,
			@Param("customerId") String customerId );
}