package com.aml.api.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.AlertTransactionDTO;
import com.aml.api.dto.transaction.TransactionDetailsDto;
import com.github.pagehelper.Page;

/**
 * alert交易
 * 
 * @className: AlertTransactionsMapper
 * @description:
 * @author shun
 * @date 2017年12月2日
 *
 */
public interface AlertTransactionsMapper {

	List<Map<String, Object>> queryTransactionTop5 ( Integer originalId );

//	Page<Map<String, Object>> queryAlertTransaction ( Integer originalId );

	List<Map<String, Object>> queryTransactionNameTop5 ( @Param("originalId") Integer originalId,
			@Param("type") Integer type );

	List<Map<String, Object>> queryTransactionCountryTop5 ( @Param("originalId") Integer originalId,
			@Param("type") Integer type );

	List<Map<String, Object>> queryTransactionVolumeMap ( @Param("originalId") Integer originalId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate );

	List<Map<String, Object>> queryTransactionAmountMap ( Integer originalId );

//	Page<Map<String, Object>> queryTransaction ( AlertTransactionDTO dto );

	Page<Map<String, Object>> queryAlertTransactionByCashTrxnArc(Integer originalId);

	Page<Map<String, Object>> queryAlertTransactionByWireTrxnArc(Integer originalId);

	Page<Map<String, Object>> queryAlertTransactionByMiTrxnArc(Integer originalId);

	Page<Map<String, Object>> queryTransactionByCashTrxn(AlertTransactionDTO dto);

	Page<Map<String, Object>> queryTransactionByWireTrxn(AlertTransactionDTO dto);

	Page<Map<String, Object>> queryTransactionByMiTrxn(AlertTransactionDTO dto);

	TransactionDetailsDto queryTransactionDetailsByWireTrxn(AlertTransactionDTO dto);

	TransactionDetailsDto queryTransactionDetailsByMiTrxn(AlertTransactionDTO dto);

}