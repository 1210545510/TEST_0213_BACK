package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.dto.CustDto;
import com.aml.api.pojo.Cust;
import com.github.pagehelper.Page;

public interface CustMapper {
	int deleteByPrimaryKey ( String custIntrlId );

	int insert ( Cust record );

	int insertSelective ( Cust record );

	Cust selectByPrimaryKey ( String custIntrlId );

	int updateByPrimaryKeySelective ( Cust record );

	int updateByPrimaryKey ( Cust record );

	Cust selectByAccountId ( String accountId );

	List<Cust> selectBySubject ( String subject );

	Cust selectByCustomerId ( String customerId );

	Cust queryAlertReviewAcct ( AlertReviewDTO dto );

	Page<CustDto> getCustList ( CustDto dto );

	List<Map<String, Object>> downCustExcel ( CustDto dto );

	Map<String, Object> queryAlertCustInfo ( Integer originalId );

	Map<String, Object> queryAlertCustInfoBycIdOrAid(@Param("accountId") String accountId,@Param("customerId") String customerId);
	
	Map<String, Object> queryCustDetailInfo(String customerId);
	
	List<Map<String, Object>> queryAccountsList(String customerId);
	
	List<Map<String, Object>> queryContactList(String customerId);
	
	List<Map<String, Object>> queryRiskList(String customerId);
	
	List<Map<String, Object>> queryCustSummarySnapshot(String customerId);
	
	List<Map<String, Object>> querySummaryList(String customerId);

	Map<String, Object> queryCustInfoSnapshot(Integer originalId);
}