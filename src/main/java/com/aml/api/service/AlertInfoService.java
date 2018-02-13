package com.aml.api.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.aml.api.dao.AcctMapper;
import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.AlertInfoMapper;
import com.aml.api.dao.CaseGroupMapper;
import com.aml.api.dao.CaseInfoMapper;
import com.aml.api.dao.CustMapper;
import com.aml.api.dao.RfiMapper;
import com.aml.api.dao.SendRFIMapper;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.AlertInfo;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.pojo.CaseInfo;
import com.aml.api.pojo.Rfi;
import com.aml.api.pojo.SendRFI;
import com.aml.common.api.exception.ServiceException;
import com.aml.common.util.DozerUtils;
import com.aml.common.util.StrUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: AlertInfoService
 * @description: AlertInfoService Class
 * @author Novan
 * @date 2017-11-29
 *
 */
@Service
public class AlertInfoService {
	public static final Logger logger = LoggerFactory.getLogger(AlertInfoService.class);
	@Autowired
	AlertInfoMapper alertInfoMapper;
	@Autowired
	private CaseInfoMapper caseInfoMapper;
	@Autowired
	private CaseGroupMapper caseGroupMapper;
	@Autowired
	AlertGroupMapper alertGroupMapper;
	@Autowired
	private AcctMapper acctMapper;
	@Autowired
	private CustMapper custMapper;
	@Autowired
	private AlertService alertService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private SendRFIMapper sendRFIMapper;
	@Autowired
	private RfiMapper rfiMapper;

	/**
	 * 查询Alert关联列表
	 * 
	 * @param dto
	 * @return Page<AlertInfo>
	 * @date 2017年11月24日
	 */
	public Page<AlertInfo> queryAlertReviewList ( AlertReviewDTO dto ) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return alertInfoMapper.queryAlertReviewList(dto);
	}

	/**
	 * 查询Alert详情
	 * 
	 * @param dto
	 * @return
	 * @date 2017年11月24日
	 */
	public Map<String, Object> queryAlertReviewInfo ( AlertReviewDTO dto ) {
		return alertInfoMapper.queryAlertReview(dto);
	}

	/**
	 * 查询所有alert账号id
	 * 
	 * @param originalId
	 * @return
	 * @date 2017年12月2日
	 */
	public Set<String> queryAlertAcctId ( Integer originalId ) {
		return acctMapper.queryAlertAcctId(originalId);
	}

	/**
	 * 查询最新Account Detail中Account信息
	 * 
	 * @param accountId
	 * @return  Map<String, Object>
	 * @date 2017年12月2日
	 */
	public Map<String, Object> getAcctInfo ( String accountId ) {
		return acctMapper.getAcctInfo(accountId);
	}
	
	/**
	 * 查询最新Account Detail中Account Summary列表
	 * 
	 * @param accountId
	 * @return  Map<String, Object>
	 * @date 2017年12月2日
	 */
	public Page<Map<String, Object>> queryAcctSummaryInfo ( String accountId ) {
		return acctMapper.queryAcctSummaryInfo(accountId);
	}
	
	/**
	 * 查询快照Account Detail中Account Summary列表
	 * 
	 * @param accountId
	 * @return  List<Map<String, Object>>
	 * @date 2017年12月2日
	 */
	public Page<Map<String, Object>> queryAcctSummaryInfoSnapshot ( String accountId ) {
		return acctMapper.queryAcctSummaryInfoSnapshot(accountId);
	}
	

	/**
	 * 查询账号快照信息
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 * @date 2017年12月2日
	 */
	public Map<String, Object> queryAcctSnapshotInfo ( String accountId, Integer month ) {
		return acctMapper.queryAcctSnapshotInfo(accountId, month);
	}

	/**
	 * 查询账号余额快照信息
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 * @date 2017年12月2日
	 */
	public Map<String, Object> queryAcctBalance ( String accountId, Integer month ) {
		return acctMapper.queryAcctBalance(accountId, month);
	}
	
	/**
	 * 查询客户快照信息
	 * 
	 * @param originalId
	 * @return
	 * @date 2018年1月12日
	 */
	public Map<String, Object> queryCustInfoSnapshot ( Integer originalId ) {
		return custMapper.queryCustInfoSnapshot(originalId);
	}

	/**
	 * 查询客户信息
	 * 
	 * @param originalId
	 * @return
	 * @date 2017年12月2日
	 */
	public Map<String, Object> queryAlertCustInfo ( Integer originalId ) {
		return custMapper.queryAlertCustInfo(originalId);
	}

	/**
	 * 查询客户信息
	 * 
	 * @param accountId
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public Map<String, Object> queryAlertCustInfo ( AlertReviewDTO dto ) {
		Integer originalId = dto.getOriginalId();
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		Map<String, Object> data = this.queryAlertCustInfo(originalId);
		if (ObjectUtils.isEmpty(data)) {
			String accountId = null;
			String customerId = null;
			if (!ObjectUtils.isEmpty(alertId)) {
				AlertGroup alertGroup = alertGroupMapper.selectByPrimaryKey(alertId);
				if (!ObjectUtils.isEmpty(alertGroup)) {
					accountId = alertGroup.getAccountId();
					customerId = alertGroup.getCustomerId();
				}
			} else if (!ObjectUtils.isEmpty(caseId)) {
				CaseGroup caseGroup = caseGroupMapper.selectByPrimaryKey(alertId);
				if (!ObjectUtils.isEmpty(caseGroup)) {
					accountId = caseGroup.getAccountId();
					customerId = caseGroup.getCustomerId();
				}
			}
			if (accountId != null && customerId != null) {
				return custMapper.queryAlertCustInfoBycIdOrAid(accountId, customerId);
			}
		}
		return data;
	}
	
	/**
	 * 查询客户详细信息
	 * 
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public Map<String, Object> queryCustDetailInfo ( String customerId ) {
		return custMapper.queryCustDetailInfo(customerId);
	}
	
	/**
	 * 查询客户关联的Accounts列表
	 * 
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public List<Map<String, Object>> queryAccountsList ( String customerId ) {
		return custMapper.queryAccountsList(customerId);
	}
	
	/**
	 * 查询客户关联的Contact Information列表
	 * 
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public List<Map<String, Object>> queryContactList ( String customerId ) {
		return custMapper.queryContactList(customerId);
	}
	
	/**
	 * 查询客户关联的Risk Information列表
	 * 
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public List<Map<String, Object>> queryRiskList ( String customerId ) {
		return custMapper.queryRiskList(customerId);
	}
	
	/**
	 * 查询客户关联的Summary列表
	 * 
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public List<Map<String, Object>> querySummaryList ( String customerId ) {
		return custMapper.querySummaryList(customerId);
	}
	
	/**
	 * 查询客户快照关联的Summary列表
	 * 
	 * @param customerId
	 * @return
	 * @date 2017年12月2日
	 */
	public List<Map<String, Object>> queryCustSummarySnapshot ( String customerId ) {
		return custMapper.queryCustSummarySnapshot(customerId);
	}
	
	
	/**
	 * 查询Alert帐户资料
	 * 
	 * @param originalId
	 * @return
	 * @date 2017年12月4日
	 */
	public List<Map<String, Object>> queryAlertAcctInfo ( Integer originalId ) {
		return acctMapper.queryAlertAcctInfo(originalId);
	}

	public List<Map<String, Object>> queryAlertAcctInfo ( String accountId, String customerId ) {
		return acctMapper.queryAlertAcctInfoByAidOrCid(accountId, customerId);
	}

	/**
	 * 查询Alert帐户列表
	 * 
	 * @param originalId
	 * @return
	 * @date 2017年12月4日
	 */
	public List<Map<String, Object>> queryAlertAcctList ( Integer originalId ) {
		return acctMapper.queryAlertAcctList(originalId);
	}

	/**
	 * Alert解除绑定
	 * 
	 * @param dto
	 * @param isAdminCreate
	 * @return
	 * @throws Exception
	 * @date 2017年11月27日
	 */
	public void updateAlertUnbind ( AlertReviewDTO dto, boolean isAdminCreate ) throws ServiceException {
		Integer alertCount = null;
		for (Long id : dto.getIds()) {
			AlertInfo alertInfo = alertInfoMapper.selectByPrimaryKey(id);
			if (ObjectUtils.isEmpty(alertInfo)) {
				throw new ServiceException("Data does not exist");
			}
			// 修改原alert统计
			Long alertId = alertInfo.getAlertId();
			if (alertCount == null) {
				alertCount = alertInfoMapper.queryByAlertCount(alertId);
				if (alertCount <= dto.getIds().size()) {
					throw new ServiceException("Leave at least one data");
				}
			}

			BigDecimal amount = alertInfo.getAmount();
			Integer volume = alertInfo.getVolume();
			amount = ObjectUtils.isEmpty(amount) ? BigDecimal.ZERO : amount;
			volume = ObjectUtils.isEmpty(volume) ? 0 : volume;
			AlertGroup oldAlertGroup = alertGroupMapper.selectByPrimaryKey(alertId);
			BigDecimal allAmount = oldAlertGroup.getAmount();
			Integer allVolume = oldAlertGroup.getVolume();
			allAmount = ObjectUtils.isEmpty(allAmount) ? BigDecimal.ZERO : allAmount;
			allVolume = ObjectUtils.isEmpty(allVolume) ? 0 : allVolume;
			amount = ObjectUtils.isEmpty(amount) ? BigDecimal.ZERO.add(allAmount) : amount;
			oldAlertGroup.setAmount(allAmount.subtract(amount));
			oldAlertGroup.setVolume(allVolume - volume);
			alertGroupMapper.updateByPrimaryKeySelective(oldAlertGroup);
			// 创建新的alert主信息
			AlertGroup alertGroup = DozerUtils.map(alertInfo, AlertGroup.class);
			alertGroup.setAlertId(null);
			alertGroup.setCreateTime(new Date());
			alertGroup.setCreationDate(new Date());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			alertGroup.setDueDate(cal.getTime());
			alertGroup.setOwnerOrg(oldAlertGroup.getOwnerOrg());
			// alertGroup.setSubject(oldAlertGroup.getSubject());
			// alertGroup.setSubjectType(oldAlertGroup.getSubjectType());
			alertGroup.setAlertType(oldAlertGroup.getAlertType());
			// alertGroup.setCaseType(oldAlertGroup.getCaseType());
			alertGroup.setDataType(oldAlertGroup.getDataType());
			alertGroup.setIsAdminCreate(isAdminCreate ? 1 : 0);
			alertGroup.setCreateUser(dto.getUserName());
			alertService.insertSelective(alertGroup);
			// 更新alertId到解绑alert上
			alertInfo.setAlertId(alertGroup.getAlertId());
			alertInfoMapper.updateByPrimaryKeySelective(alertInfo);
		}
	}

	/**
	 * Case解除绑定
	 * 
	 * @param dto
	 * @param isAdminCreate
	 * @return
	 * @throws Exception
	 * @date 2017年11月27日
	 */
	public void updateCaseUnbind ( AlertReviewDTO dto, boolean isAdminCreate ) throws ServiceException {
		Integer alertCount = null;
		StringBuffer alertIdsSt = new StringBuffer();
		for (Long id : dto.getIds()) {
			CaseInfo caseInfo = caseInfoMapper.selectByPrimaryKey(id);
			if (ObjectUtils.isEmpty(caseInfo)) {
				throw new ServiceException("Data does not exist");
			}
			// 修改原alert统计
			Long caseId = caseInfo.getCaseId();
			if (alertCount == null) {
				alertCount = caseInfoMapper.queryByCaseCount(caseId);
				if (alertCount <= dto.getIds().size()) {
					throw new ServiceException("Leave at least one data");
				}
				List<Long> alertIds = caseInfoMapper.queryRemainingAlertId(dto.getIds(), caseId);
				for (Long alertId : alertIds) {
					alertIdsSt.append(alertId);
					alertIdsSt.append(",");
				}
			}
			BigDecimal amount = caseInfo.getAmount();
			Integer volume = caseInfo.getVolume();
			amount = ObjectUtils.isEmpty(amount) ? BigDecimal.ZERO : amount;
			volume = ObjectUtils.isEmpty(volume) ? 0 : volume;
			CaseGroup oldCaseGroup = caseGroupMapper.selectByPrimaryKey(caseId);
			oldCaseGroup.setAlertId(alertIdsSt.substring(0, alertIdsSt.length() - 1));
			BigDecimal allAmount = oldCaseGroup.getAmount();
			Integer allVolume = oldCaseGroup.getVolume();
			allAmount = ObjectUtils.isEmpty(allAmount) ? BigDecimal.ZERO : allAmount;
			allVolume = ObjectUtils.isEmpty(allVolume) ? 0 : allVolume;
			amount = ObjectUtils.isEmpty(amount) ? BigDecimal.ZERO.add(allAmount) : amount;
			oldCaseGroup.setAmount(allAmount.subtract(amount));
			oldCaseGroup.setVolume(allVolume - volume);
			caseGroupMapper.updateByPrimaryKeySelective(oldCaseGroup);
			// 创建新的alert主信息
			CaseGroup caseGroup = DozerUtils.map(caseInfo, CaseGroup.class);
			caseGroup.setCaseId(null);
			caseGroup.setAlertId(String.valueOf(caseInfo.getAlertId()));
			caseGroup.setCreateTime(new Date());
			caseGroup.setCreationDate(new Date());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			caseGroup.setDueDate(cal.getTime());
			caseGroup.setOwnerOrg(oldCaseGroup.getOwnerOrg());
			// alertGroup.setSubject(oldAlertGroup.getSubject());
			// alertGroup.setSubjectType(oldAlertGroup.getSubjectType());
			caseGroup.setCaseType(oldCaseGroup.getCaseType());
			// alertGroup.setCaseType(oldAlertGroup.getCaseType());
			caseGroup.setDataType(oldCaseGroup.getDataType());
			caseGroup.setIsAdminCreate(isAdminCreate ? 1 : 0);
			caseGroup.setCreateUser(dto.getUserName());
			caseService.insertSelective(caseGroup);
			// 更新caseId到解绑case上
			caseInfo.setCaseId(caseGroup.getCaseId());
			caseInfoMapper.updateByPrimaryKeySelective(caseInfo);
		}
	}

	/**
	 * 查询Alert原始id
	 * 
	 * @param alertId
	 * @return
	 * @date 2017年12月7日
	 */
	public Set<String> queryAlertOriginalIds ( Long alertId ) {
		return acctMapper.queryAlertOriginalIds(alertId);
	}

	/**
	 * 查询Case原始id
	 * 
	 * @param caseId
	 * @return
	 * @date 2017年12月7日
	 */
	public Set<String> queryCaseOriginalIds ( Long caseId ) {
		return acctMapper.queryCaseOriginalIds(caseId);
	}

	/**
	 * 搜索alertId
	 * 
	 * @param dto
	 * @return
	 * @date 2017年12月8日
	 */
	public List<Long> searchAlertId ( AlertReviewDTO dto ) {
		return alertInfoMapper.searchAlertId(dto);
	}

	/**
	 * 保存发送的邮件
	 * @param dto
	 */
	public void alertReviewRfi(AlertReviewDTO dto) {
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		Long relateId = null;
		Integer source = null;
		if(!ObjectUtils.isEmpty(alertId)) {
			relateId = alertId;
			source = 1;
		}else if(!ObjectUtils.isEmpty(caseId)) {
			relateId = caseId;
			source = 2;
		}else {
			return ;
		}
		Long control = rfiMapper.queryControlByRelateIdAndSource(relateId,source);
		if(control == null) {
			Map<String, Object> cust = this.queryAlertCustInfo(dto);
			Rfi rfi = new Rfi();
			rfi.setRecipient(dto.getTo());
			rfi.setCreateUser(dto.getUserName());
			if(!ObjectUtils.isEmpty(cust)) {
				rfi.setSubjectName(cust.get("subjectName").toString());
				rfi.setSubjectCountry(cust.get("subjectCountry").toString());
				rfi.setSubjectType(cust.get("subjectType").toString());
				rfi.setCustomerId(cust.get("customerId").toString());
				rfi.setRequestingParty(cust.get("requestingParty").toString());
			}
			this.insertSelectiveRfi(rfi);
			control = rfi.getControl();
		}else {
			Rfi rfi = new Rfi();
			rfi.setControl(control);
			rfi.setUpdateTime(new Date());
			rfi.setUpdateUser(dto.getUserName());
			rfiMapper.updateByPrimaryKeySelective(rfi);
		}
		
		SendRFI sendRFI = new SendRFI();
		sendRFI.setControlId(control);
		sendRFI.setFrom(dto.getFrom());
		sendRFI.setTo(dto.getTo());
		sendRFI.setSubject(dto.getSubject());
		sendRFI.setComment(dto.getComment());
		sendRFI.setFileUrls(dto.getFileUrls());
		sendRFI.setFileNames(dto.getFileNames());
		sendRFI.setCreateUser(dto.getUserName());
		sendRFIMapper.insertSelective(sendRFI);
	}

	/**
	 * 插入RFI数据
	 * @param record
	 * @return
	 * @date 2018年1月12日
	 */
	public synchronized int insertSelectiveRfi ( Rfi record ){
		// 使用同步锁，防止Id重复添加
		Long control = StrUtils.getPrimaryKey(rfiMapper.findNewControlId());
		record.setControl(control);;
		return rfiMapper.insertSelective(record);
	}

}
