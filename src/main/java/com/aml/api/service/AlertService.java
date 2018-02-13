package com.aml.api.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.AlertInfoMapper;
import com.aml.api.dao.AlertTransactionsMapper;
import com.aml.api.dao.CustMapper;
import com.aml.api.dto.AlertDTO;
import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.AlertInfoReviewDto;
import com.aml.api.dto.AlertTransactionDTO;
import com.aml.api.dto.transaction.TransactionDetailsDto;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.Cust;
import com.aml.common.core.AlertConsts;
import com.aml.common.util.DozerUtils;
import com.aml.common.util.StrUtils;
import com.aml.common.util.TimeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: AlertService
 * @description:
 * @author shun
 * @date 2017年11月22日
 *
 */
@Service
public class AlertService {
	public static final Logger logger = LoggerFactory.getLogger(AlertService.class);
	@Autowired
	private AlertGroupMapper alertGroupMapper;
	@Autowired
	private AlertInfoMapper alertInfoMapper;
	@Autowired
	private AlertTransactionsMapper alertTransactionsMapper;
	@Autowired
	private CustMapper custMapper;

	/**
	 * alert待办分页列表
	 * 
	 * @param dto
	 * @return
	 * @date 2017年11月22日
	 */
	public Page<Map<String, Object>> queryPageAlert ( AlertDTO dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.queryPageAlert(dto);
	}

	/**
	 * alert已处理分页列表
	 * 
	 * @param dto
	 * @return
	 * @date 2017年11月22日
	 */
	public Page<Map<String, Object>> queryPageAlertReviewed ( AlertDTO dto ) {
		// PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		int count = alertGroupMapper.queryPageAlertReviewed_count(dto);
		Page<Map<String, Object>> data = alertGroupMapper.queryPageAlertReviewed(dto);
		Page<Map<String, Object>> result = new Page<>(dto.getPageNum(), dto.getPageSize());
		result.setTotal(count);
		result.addAll(data);
		return result;
	}

	/**
	 * 
	 * @title: findAlertGroup
	 * @description: 查询一条Alert记录
	 *
	 * @param alertId
	 * @return AlertGroup
	 * @date 2017年12月5日 上午10:18:44
	 */
	public AlertGroup findAlertGroup ( Long alertId ) {
		return alertGroupMapper.findAlertGroup(alertId);
	}

	/**
	 * query
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getAlertInfoList ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertInfoList(dto);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public List<AlertGroup> getAlertGroup ( AlertInfoDto dto ) {
		if (dto.getCreateStartDate() != null) {
			dto.setCreateStartDate(TimeUtils.getStartTime(dto.getCreateStartDate()));
		}
		if (dto.getCreateEndDate() != null) {
			dto.setCreateEndDate(TimeUtils.getEndTime(dto.getCreateEndDate()));
		}
		return alertGroupMapper.getAlertGroup(dto);
	}

	/**
	 * 添加alert
	 * 
	 * @param dto
	 * @date 2017年11月22日
	 */
	public int addAlert ( AlertDTO dto ) {
		String subject = dto.getSubject();
		String customerId = dto.getCustomerId();
		String accountId = dto.getAccountId();
		AlertGroup alert = DozerUtils.map(dto, AlertGroup.class);
		alert.setSysType(AlertConsts.SYS_TYPE_AML);
		Cust cust = null;
		if (StringUtils.isNotBlank(subject)) {
			List<Cust> custList = custMapper.selectBySubject(subject);
			if (custList.size() != 0) {
				cust = custList.get(0);
			}
		} else if (StringUtils.isBlank(subject) && StringUtils.isNotBlank(customerId)) {
			alert.setFocusType(AlertConsts.FOCUS_TYPE_CU);
			cust = custMapper.selectByCustomerId(customerId);
		} else if (StringUtils.isBlank(subject) && StringUtils.isBlank(customerId)
				&& StringUtils.isNotBlank(accountId)) {
			alert.setFocusType(AlertConsts.FOCUS_TYPE_AC);
			cust = custMapper.selectByAccountId(accountId);
		}
		if (!ObjectUtils.isEmpty(cust)) {
			// 客户名称
			alert.setSubject(cust.getAliasNm());
			alert.setGeography(cust.getCustGeoRiskNb().toString());
			alert.setJurisdiction(cust.getJrsdcnCd());
			alert.setCustomerId(cust.getCustIntrlId());
		}
		alert.setCreateUser(dto.getUserName());

		String alertType = dto.getAlertType();
		Calendar cal = Calendar.getInstance();
		if (AlertConsts.ALERT_TYPE_CTR.equals(alertType)) {
			cal.add(Calendar.DATE, 15);
		} else {
			cal.add(Calendar.DATE, 30);
		}
		alert.setDueDate(cal.getTime());
		return this.insertSelective(alert);
	}

	/**
	 * Alert List(To do List)导出csv
	 * 
	 * @param dto
	 * @return
	 * @date 2017年11月23日
	 */
	public List<Map<String, Object>> exportCSV ( AlertDTO dto ) {
		return alertGroupMapper.queryPageAlert(dto);
	}

	/**
	 * Alert List(Reviewed List) 导出csv
	 * 
	 * @param dto
	 * @return
	 * @date 2017年11月23日
	 */
	public List<Map<String, Object>> exportReviewedCSV ( AlertDTO dto ) {
		return alertGroupMapper.queryAlertReviewedExport(dto);
	}

	/**
	 * 查询前5条交易记录
	 * 
	 * @param alertId
	 * @return
	 * @date 2017年12月2日
	 */
	public List<Map<String, Object>> queryTransactionTop5 ( Integer originalId ) {
		return alertTransactionsMapper.queryTransactionTop5(originalId);
	}

	/**
	 * 查询alert交易记录
	 * @param type 
	 * 
	 * @param alertId
	 * @return
	 * @date 2017年12月4日
	 */
	public Page<Map<String, Object>> queryAlertTransaction ( Integer originalId, Integer type ) {
		if(type == null || type == 1) {
			//Cash Transaction
			return alertTransactionsMapper.queryAlertTransactionByCashTrxnArc(originalId);
		}else if(type == 2){
			//Electronic Fund Transaction
			return alertTransactionsMapper.queryAlertTransactionByWireTrxnArc(originalId);
		}else if(type == 3){
			//Monetary Instrument
			return alertTransactionsMapper.queryAlertTransactionByMiTrxnArc(originalId);
		}
		return null;
	}

	/**
	 * 查询前5条的交易人
	 * 
	 * @param type
	 * @param alertId
	 * @return
	 * @date 2017年12月4日
	 */
	public List<Map<String, Object>> queryTransactionNameTop5 ( Integer originalId, Integer type ) {
		return alertTransactionsMapper.queryTransactionNameTop5(originalId, type);
	}

	/**
	 * 查询前5条的地区
	 * 
	 * @param alertId
	 * @return
	 * @date 2017年12月4日
	 */
	public List<Map<String, Object>> queryTransactionCountryTop5 ( Integer originalId, Integer type ) {
		return alertTransactionsMapper.queryTransactionCountryTop5(originalId, type);
	}

	/**
	 * 所有交易量
	 * 
	 * @param endDate
	 * @param startDate
	 * @param alertId
	 * @return
	 * @date 2017年12月4日
	 */
	public List<Map<String, Object>> queryTransactionVolumeMap ( Integer originalId, Date startDate, Date endDate ) {
		return alertTransactionsMapper.queryTransactionVolumeMap(originalId, startDate, endDate);
	}

	/**
	 * 所有交易额
	 * 
	 * @param alertId
	 * @return
	 * @date 2017年12月4日
	 */
	public List<Map<String, Object>> queryTransactionAmountMap ( Integer originalId ) {
		return alertTransactionsMapper.queryTransactionAmountMap(originalId);
	}

	/**
	 * 查询历史交易记录
	 * 
	 * @param alertId
	 * @return
	 * @date 2017年12月4日
	 */
	public Page<Map<String, Object>> queryTransaction ( AlertTransactionDTO dto ) {
		Integer type = dto.getType();
		if(type == null || type == 1) {
			//Cash Transaction
			return alertTransactionsMapper.queryTransactionByCashTrxn(dto);
		}else if(type == 2){
			//Electronic Fund Transaction
			return alertTransactionsMapper.queryTransactionByWireTrxn(dto);
		}else if(type == 3){
			//Monetary Instrument
			return alertTransactionsMapper.queryTransactionByMiTrxn(dto);
		}
		return null;
	}
	
	/**
	 * 查询历史交易记录详情
	 * @param dto
	 * @return
	 */
	public TransactionDetailsDto queryTransactionDetails(AlertTransactionDTO dto) {
		Integer type = dto.getType();
		if(type == null || type == 2){
			//Electronic Fund Transaction
			return alertTransactionsMapper.queryTransactionDetailsByWireTrxn(dto);
		}else if(type == 3){
			//Monetary Instrument
			return alertTransactionsMapper.queryTransactionDetailsByMiTrxn(dto);
		}
		return null;
	}

	/**
	 * export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downAlertInfoExcel ( AlertInfoDto dto ) {
		return alertGroupMapper.downAlertInfoExcel(dto);
	}

	/**
	 * The total number of alert reviewed
	 * 
	 * @param dto
	 * @return
	 */
	public List<AlertGroup> getAlertTotal ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertTotal(dto);
	}

	/**
	 * The total transaction amount and volume
	 * 
	 * @param dto
	 * @return
	 */
	public AlertGroup getAlertAmountVolume ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertAmountVolume(dto);
	}

	/**
	 * AVG score
	 * 
	 * @param dto
	 * @return
	 */
	public AlertGroup getAlertAvgScore ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertAvgScore(dto);
	}

	/**
	 * The total by month
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getAlertTotalByMonth ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertTotalByMonth(dto);
	}

	/**
	 * export total by month
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downAlertInfoTotalByMonth ( AlertInfoDto dto ) {
		return alertGroupMapper.downAlertInfoTotalByMonth(dto);
	}

	/**
	 * Amount
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getAlertAmountByMonth ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertAmountByMonth(dto);
	}

	/**
	 * Volume
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getAlertVolumeByMonth ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertVolumeByMonth(dto);
	}

	/**
	 * Pending
	 * 
	 * @param dto
	 * @return
	 */
	public Integer getAlertPending ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertPending(dto);
	}

	/**
	 * Average review time per alert
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getAlertReviewTime ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertReviewTime(dto);
	}

	/**
	 * QA
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getAlertReviewTimeQA ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertReviewTimeQA(dto);
	}

	/**
	 * The total number of alert for XX review
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertInfoReviewDto> getAlertReview ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getAlertReview(dto);
	}

	/**
	 * amount export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAlertAmountByMonthExport ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertAmountByMonthExport(dto);
	}

	/**
	 * volume export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAlertVolumeByMonthExport ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertVolumeByMonthExport(dto);
	}

	/**
	 * Alert Review Time export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAlertReviewTimeExcel ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertReviewTimeExcel(dto);
	}

	/**
	 * Alert Review Time export QA
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAlertReviewTimeQAExcel ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertReviewTimeQAExcel(dto);
	}

	@SuppressWarnings("unused")
	private Map<Integer, Integer> count ( Integer key, Integer count, Map<Integer, Integer> data ) {
		Integer novemberCount = data.get(key);
		novemberCount = ObjectUtils.isEmpty(novemberCount) ? 0 : novemberCount;
		data.put(key, novemberCount + count);
		return data;
	}

	/**
	 * Escalation/waive rate
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getRate ( AlertInfoDto dto ) {
		return alertGroupMapper.getRate(dto);
	}

	/**
	 * Percentage of alert reviewed reversed by QA
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getReversed ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getReversed(dto);
	}

	/**
	 * The total number of productive alert escalated each month
	 * 
	 * @param dto
	 * @return
	 */
	public Page<AlertGroup> getEscalated ( AlertInfoDto dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertGroupMapper.getEscalated(dto);
	}

	/**
	 * export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getReversedExcel ( AlertInfoDto dto ) {
		return alertGroupMapper.getReversedExcel(dto);
	}

	/**
	 * export
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalatedExcel ( AlertInfoDto dto ) {
		return alertGroupMapper.getEscalatedExcel(dto);
	}

	/**
	 * getAlertReviewTimeTotalQA
	 * 
	 * @param dto
	 * @return
	 */
	public Integer getAlertReviewTimeTotalQA ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertReviewTimeTotalQA(dto);
	}

	/**
	 * 折线图 --
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAlertReviewTimeFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertReviewTimeFoldLine(dto);
	}

	/**
	 * 折线图 --
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAlertReviewTimeQAFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getAlertReviewTimeQAFoldLine(dto);
	}

	/**
	 * 折线图 -- Percentage of alert reviewed reversed by QA
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getReversedFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getReversedFoldLine(dto);
	}

	/**
	 * 折线图 --The total number of productive alert escalated
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalatedFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getEscalatedFoldLine(dto);
	}

	/**
	 * 折线图-- EscalationRate
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalationRateFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getEscalationRateFoldLine(dto);
	}

	/**
	 * 折线图-- WaviedRate
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWaviedRateFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getWaviedRateFoldLine(dto);
	}

	/**
	 * 折线图- Escalation I Rate
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalationRateIFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getEscalationRateIFoldLine(dto);
	}

	/**
	 * 折线图- Escalation Team Rate
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getEscalationRateTeamFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getEscalationRateTeamFoldLine(dto);
	}

	/**
	 * 折线图- Waived I Rate
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWaivedRateIFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getWaivedRateIFoldLine(dto);
	}

	/**
	 * 折线图- Waived Team Rate
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWaivedRateTeamFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getWaivedRateTeamFoldLine(dto);
	}

	/**
	 * 折线图- Average I
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAverageIFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getAverageTeamFoldLine(dto);
	}

	/**
	 * 折线图- Average Team
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAverageTeamFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getAverageTeamFoldLine(dto);
	}

	/**
	 * 折线图- Average score
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getAverageFoldLine ( AlertInfoDto dto ) {
		return alertGroupMapper.getAverageTeamFoldLine(dto);
	}

	/**
	 * alert合并
	 * 
	 * @param dto
	 * @date 2018年1月11日
	 */
	public void alertMerge ( AlertDTO dto ) {
		List<Long> alertIdList = dto.getAlertIdList();
		Long aId = alertIdList.get(0);
		BigDecimal allAmount = BigDecimal.ZERO;
		Integer allVolume = 0;
		for (Long alertId : alertIdList) {
			allAmount.add(alertInfoMapper.queryAllAmount(alertId));
			allVolume += alertInfoMapper.queryAllVolume(alertId);
			// 删除多余的alert
			if (!alertId.equals(aId)) {
				AlertGroup alertGroup = new AlertGroup();
				alertGroup.setAlertId(alertId);
				alertGroup.setIsDeleted(1);
				alertGroupMapper.updateByPrimaryKeySelective(alertGroup);
			}
		}
		alertInfoMapper.updateByAlertMerge(alertIdList, aId);
		AlertGroup alertGroup = new AlertGroup();
		alertGroup.setAlertId(aId);
		alertGroup.setAmount(allAmount);
		alertGroup.setVolume(allVolume);
		alertGroupMapper.updateByPrimaryKeySelective(alertGroup);
	}


	/**
	 * 插入AlertGroup数据
	 * @param record
	 * @return
	 * @date 2018年1月12日
	 */
	public synchronized int insertSelective ( AlertGroup record ){
		// 使用同步锁，防止alertId重复添加
		Long alertId = StrUtils.getPrimaryKey(alertGroupMapper.findNewAlertId());
		record.setAlertId(alertId);
		return alertGroupMapper.insertSelective(record);
	}
}
