package com.aml.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.CaseGroupMapper;
import com.aml.api.dao.RfiMapper;
import com.aml.api.dao.SendRFIMapper;
import com.aml.api.dao.TaskApprovalMapper;
import com.aml.api.dao.WfTaskFileMapper;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.AlertInfo;
import com.aml.api.pojo.CaseGroup;
import com.aml.api.pojo.Rfi;
import com.aml.api.pojo.SendRFI;
import com.aml.api.pojo.WfBusStatus;
import com.aml.api.pojo.WfTaskFile;
import com.aml.api.service.AlertInfoService;
import com.aml.api.service.CaseInfoService;
import com.aml.api.service.ConfigService;
import com.aml.common.api.exception.ServiceException;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.FlowConsts;
import com.aml.common.util.PageUtils;
import com.aml.common.util.PropertiesUtil;
import com.aml.common.util.UMSUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: AlertReviewController
 * @description: Alert/Case Review详情
 * @author shun
 * @date 2017年11月24日
 *
 */
@RestController
public class AlertReviewController extends BaseController {
	@Autowired
	private AlertInfoService alertInfoService;
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private TaskApprovalMapper taskApprovalMapper;
	@Autowired
	private AlertGroupMapper alertGroupMapper;
	@Autowired
	private CaseGroupMapper caseGroupMapper;
	@Autowired
	private RfiMapper rfiMapper;
	@Autowired
	private SendRFIMapper sendRFIMapper;
	@Autowired
	private WfTaskFileMapper wfTaskFileMapper;

	/**
	 * 查询Alert/Case关联列表
	 * 
	 * @param dto
	 * @param request
	 * @return ApiPageResult<AlertInfo>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<AlertInfo> queryAlertReviewList ( HttpServletRequest request, AlertReviewDTO dto ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiPageResult<AlertInfo> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		// if (ColumnConsts.C_UPDATE_TIME.equals(dto.getOrderby())) {
		// dto.setOrderby("a.alert_id");
		// }
		if (ObjectUtils.isEmpty(dto.getAlertId()) && ObjectUtils.isEmpty(dto.getCaseId())) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
//		Long roleId = super.getRoleId(dto.getUserId());
		Page<AlertInfo> data = null;
		if (!ObjectUtils.isEmpty(dto.getAlertId())) {
			AlertGroup alertGroup = alertGroupMapper.findAlertGroup(dto.getAlertId());
			if (this.isContain(FlowConsts.FLOW_ALERT_PENDING_STATUS, String.valueOf(alertGroup.getStatus()))) {
				// 查询配置的流程状态
				String flowId = PropertiesUtil.getValue("processId.alert");
				WfBusStatus busStatus = configService.findStatus(flowId, alertGroup.getStatus(), null, null);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("alertId", dto.getAlertId());
				map.put("status", busStatus == null ? "" : busStatus.getDicCode());
				map.put("preStatus", alertGroup.getStatus());
				map.put("reviewStartDate", FlowConsts.UNDER_REVIEW);
				alertGroupMapper.updateFlow(map);
				/*
				TaskApprovalDTO approval = new TaskApprovalDTO();
				approval.setTaskId(alertGroup.getTaskId());
				approval.setOrderId(alertGroup.getOrderId());
				approval.setRoleId(String.valueOf(roleId));
				approval.setStatus(alertGroup.getStatus());
				approval.setCreateUser(super.getUserName(dto.getUserId()));
				approval.setIsApprove(FlowConsts.IS_NOT_APPROVE);
				taskApprovalMapper.insert(approval);*/
			}
			data = alertInfoService.queryAlertReviewList(dto);
		} else if (!ObjectUtils.isEmpty(dto.getCaseId())) {
			CaseGroup caseGroup = caseGroupMapper.findCaseGroup(dto.getCaseId());
			if (this.isContain(FlowConsts.FLOW_SAR_PENDING_STATUS, String.valueOf(caseGroup.getStatus()))) {
				// 查询配置的流程状态
				String flowId = PropertiesUtil.getValue("processId.sar");
				WfBusStatus busStatus = configService.findStatus(flowId, caseGroup.getStatus(), null, null);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("caseId", dto.getCaseId());
				map.put("status", busStatus == null ? "" : busStatus.getDicCode());
				map.put("preStatus", caseGroup.getStatus());
				map.put("reviewStartDate", FlowConsts.UNDER_REVIEW);
				caseGroupMapper.updateFlow(map);
				/*
				TaskApprovalDTO approval = new TaskApprovalDTO();
				approval.setTaskId(caseGroup.getTaskId());
				approval.setOrderId(caseGroup.getOrderId());
				approval.setRoleId(String.valueOf(roleId));
				approval.setStatus(caseGroup.getStatus());
				approval.setCreateUser(super.getUserName(dto.getUserId()));
				approval.setIsApprove(FlowConsts.IS_NOT_APPROVE);
				taskApprovalMapper.insert(approval); */
			}
			data = caseInfoService.queryCaseReviewList(dto);
		}

		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 查询Alert/Case Review详情
	 * 
	 * @param dto
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryAlertReviewInfo ( HttpServletRequest request, AlertReviewDTO dto ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		if (ObjectUtils.isEmpty(alertId) && ObjectUtils.isEmpty(caseId)) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}

		Map<String, Object> data = new HashMap<String, Object>();
		String analystRoleId = null;
		String qaRoleId = null;
		if (!ObjectUtils.isEmpty(alertId)) {
			analystRoleId = PropertiesUtil.getValue("Alert.analyst");
			qaRoleId = PropertiesUtil.getValue("Alert.QA.Waived") + "," + PropertiesUtil.getValue("Alert.QA.Escalated");
			data = alertInfoService.queryAlertReviewInfo(dto);
		} else if (!ObjectUtils.isEmpty(caseId)) {
			analystRoleId = PropertiesUtil.getValue("Sar.analyst");
			qaRoleId = PropertiesUtil.getValue("Sar.QA");
			data = caseInfoService.queryCaseReviewInfo(dto);
		}
		// 查询已处理流程的分析员和QA
		String orderId = (String) data.get("orderId");
		List<String> analystNames = taskApprovalMapper.queryUserNameByRoleId(orderId, analystRoleId);
		List<String> qaNames = taskApprovalMapper.queryUserNameByRoleId(orderId, qaRoleId);
		data.put("operators", !ObjectUtils.isEmpty(analystNames) || !ObjectUtils.isEmpty(qaNames)
				? UMSUtils.queryUsersDate(analystNames, qaNames, dto.getToken(), dto.getUserId()) : new HashMap<>());

		result.setData(data);
		return result;
	}

	/**
	 * 查询每一条Review信息中关联的Account ID集合
	 * 
	 * @param dto 参数<br>
	 *        originalId=>原始ID
	 *        alertId=>Alert ID
	 *        caseId=>Case ID
	 * @param request
	 * @return ApiResult<Set<String>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/acctid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Set<String>> queryAlertAcctId ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Set<String>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer originalId = dto.getOriginalId();
		if (ObjectUtils.isEmpty(originalId)) {
			result.setErrorInfo("The Original ID cannot be empty");
			return result;
		}
		Set<String> data = alertInfoService.queryAlertAcctId(originalId);
		if (ObjectUtils.isEmpty(data)) {
			Long alertId = dto.getAlertId();
			Long caseId = dto.getCaseId();
			String accountId = null;
			if (alertId != null) {
				AlertGroup alertGroup = alertGroupMapper.selectByPrimaryKey(alertId);
				accountId = alertGroup.getAccountId();
			} else if (caseId != null) {
				CaseGroup caseGroup = caseGroupMapper.selectByPrimaryKey(caseId);
				accountId = caseGroup.getAccountId();
			}
			if (StringUtils.isNotBlank(accountId)) {
				data = new HashSet<>();
				data.add(accountId);
			}
		}
		result.setData(data);
		return result;
	}

	/**
	 * 查询最新Account Detail中Account信息(包括：Account基本信息，Account Contact Infomation，Account Risk information, Account Balance)
	 * 
	 * @param dto 参数<br>
	 *        accountId=>账号ID
	 * @param request
	 * @return ApiResult<List<Map<String, Object>>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/detail/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> getAcctInfo ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		String accountId = dto.getAccountId();
		if (ObjectUtils.isEmpty(accountId)) {
			result.setErrorInfo("Account ID cannot be empty");
			return result;
		}
		Map<String, Object> data = alertInfoService.getAcctInfo(accountId);
		result.setData(data);
		return result;
	}

	/**
	 * 查询最新Account Detail中Account Summary列表
	 * 
	 * @param dto 参数<br>
	 *        accountId=>账号ID
	 * @param request
	 * @return ApiPageResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/detail/summary/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Map<String, Object>> queryAcctSummaryInfo ( AlertReviewDTO dto,
			HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiPageResult<Map<String, Object>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		String accountId = dto.getAccountId();
		if (ObjectUtils.isEmpty(accountId)) {
			result.setErrorInfo("Account ID cannot be empty");
			return result;
		}
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		Page<Map<String, Object>> data = alertInfoService.queryAcctSummaryInfo(accountId);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 查询最新Account Detail中Alert帐户列表
	 * 
	 * @param dto 参数<br>
	 *        originalId=>原始ID
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/detail/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryAcctList ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer originalId = dto.getOriginalId();
		if (ObjectUtils.isEmpty(originalId)) {
			result.setErrorInfo("The Original ID cannot be empty");
			return result;
		}
		List<Map<String, Object>> data = alertInfoService.queryAlertAcctList(originalId);
		result.setData(data);
		return result;
	}

	/**
	 * 查询Review中 Account Profiles
	 * 
	 * @param dto 参数<br>
	 *        originalId=>原始ID(第一次查询时使用)
	 *        alertId=>Alert ID(点击列表记录时使用)
	 *        caseId=>Case ID(点击列表记录时使用)
	 * @param request
	 * @return ApiResult<List<Map<String, Object>>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/profile/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryAcctInfo ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer originalId = dto.getOriginalId();
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		List<Map<String, Object>> data = alertInfoService.queryAlertAcctInfo(originalId);
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
			data = alertInfoService.queryAlertAcctInfo(accountId, customerId);
		}
		result.setData(data);
		return result;
	}

	/**
	 * 查询Review中Subject Profile
	 * 
	 * @param dto 参数<br>
	 *        originalId=>原始ID(第一次查询时使用)
	 *        alertId=>Alert ID(点击列表记录时使用)
	 *        caseId=>Case ID(点击列表记录时使用)
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/profile/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryCustProfileInfo ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		result.setData(alertInfoService.queryAlertCustInfo(dto));
		return result;
	}

	/**
	 * 查询Subject Detail中Customer基本信息
	 * 
	 * @param dto 参数<br>
	 *        customerId=>客户ID
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/detail/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryCustInfo ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCustomerId())) {
			result.setErrorInfo("The Customer ID cannot be empty");
			return result;
		}
		Map<String, Object> data = alertInfoService.queryCustDetailInfo(dto.getCustomerId());
		result.setData(data);
		return result;
	}

	/**
	 * 查询Subject Detail中Accounts列表
	 * 
	 * @param dto 参数<br>
	 *        customerId=>客户ID
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/detail/accounts/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryAccountsList ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCustomerId())) {
			result.setErrorInfo("The Customer ID cannot be empty");
			return result;
		}
		List<Map<String, Object>> data = alertInfoService.queryAccountsList(dto.getCustomerId());
		result.setData(data);
		return result;
	}

	/**
	 * 查询Subject Detail中Contact information列表
	 * 
	 * @param dto 参数<br>
	 *        customerId=>客户ID
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/detail/contact/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryContactList ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCustomerId())) {
			result.setErrorInfo("The Customer ID cannot be empty");
			return result;
		}
		List<Map<String, Object>> data = alertInfoService.queryContactList(dto.getCustomerId());
		result.setData(data);
		return result;
	}

	/**
	 * 查询Subject Detail中Risk information列表
	 * 
	 * @param dto 参数<br>
	 *        customerId=>客户ID
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/detail/risk/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryRiskList ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCustomerId())) {
			result.setErrorInfo("The Customer ID cannot be empty");
			return result;
		}
		List<Map<String, Object>> data = alertInfoService.queryRiskList(dto.getCustomerId());
		result.setData(data);
		return result;
	}

	/**
	 * 查询Subject Detail中Summary列表 TODO
	 * 
	 * @param dto 参数<br>
	 *        customerId=>客户ID
	 * @param request
	 * @return ApiResult<Map<String, Object>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/detail/summary/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> querySummaryList ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCustomerId())) {
			result.setErrorInfo("The Customer ID cannot be empty");
			return result;
		}
		// 待确认数据表
		// List<Map<String, Object>> data = alertInfoService.querySummaryList(dto.getCustomerId());
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		result.setData(data);
		return result;
	}

	/**
	 * 查询Review中Account Snapshot快照信息(Account, Account Balance)
	 * 
	 * @param dto 参数<br>
	 *        originalId=>原始ID<br>
	 *        accountId=>账号ID<br>
	 *        month=>账户金额月份
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/snapshot/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryAcctSnapshot ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer originalId = dto.getOriginalId();
		String accountId = dto.getAccountId();
		if (ObjectUtils.isEmpty(originalId) && ObjectUtils.isEmpty(accountId)) {
			result.setErrorInfo("Parameter cannot be empty");
			return result;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Set<String> accountIdSet = new HashSet<>();
		if (ObjectUtils.isEmpty(accountId)) {
			accountIdSet = alertInfoService.queryAlertAcctId(originalId);
		} else {
			accountIdSet.add(accountId);
		}
		for (String aId : accountIdSet) {
			Map<String, Object> map = new HashMap<>();
			Map<String, Object> acct = alertInfoService.queryAcctSnapshotInfo(aId, dto.getMonth());
			map.put("accountId", aId);
			map.put("acct", acct == null ? new HashMap<>() : acct);
			list.add(map);
		}
		result.setData(list);
		return result;
	}

	/**
	 * 根据月份，查询Review中Account Balance快照信息
	 * 
	 * @param dto 参数<br>
	 *        accountId=>账号ID<br>
	 *        month=>账户金额月份<br>
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/snapshot/balance/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryAcctBalanceSnapshot ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		String accountId = dto.getAccountId();
		if (ObjectUtils.isEmpty(accountId)) {
			result.setErrorInfo("The Account ID cannot be empty");
			return result;
		}
		Map<String, Object> data = alertInfoService.queryAcctBalance(accountId, dto.getMonth());
		result.setData(data);
		return result;
	}

	/**
	 * 查询Account快照中Account Summary列表
	 * 
	 * @param dto 参数<br>
	 *        accountId=>账号ID
	 * @param request
	 * @return ApiResult<List<Map<String, Object>>>
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/acct/snapshot/summary/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Map<String, Object>> queryAcctSummaryInfoSnapshot ( AlertReviewDTO dto,
			HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiPageResult<Map<String, Object>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		String accountId = dto.getAccountId();
		if (ObjectUtils.isEmpty(accountId)) {
			result.setErrorInfo("Account ID cannot be empty");
			return result;
		}
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		Page<Map<String, Object>> data = alertInfoService.queryAcctSummaryInfoSnapshot(accountId);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 查询Review中Customer Snapshot
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/snapshot/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> queryCustInfoSnapshot ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Integer originalId = dto.getOriginalId();
		Map<String, Object> data = alertInfoService.queryCustInfoSnapshot(originalId);
		result.setData(data);
		return result;
	}

	/**
	 * 查询RCustomer Snapshot的Summary列表 TODO
	 * 
	 * @param dto 参数<br>
	 *        customerId=>客户ID
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/review/cust/snapshot/summary/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryCustSummarySnapshot ( AlertReviewDTO dto,
			HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getCustomerId())) {
			result.setErrorInfo("Customer ID cannot be empty");
			return result;
		}
		// 待确认数据表
		// List<Map<String, Object>> data = alertInfoService.queryCustSummarySnapshot(dto.getCustomerId());
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		result.setData(data);
		return result;
	}

	/**
	 * Alert解除绑定
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/unbind", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> updateAlertUnbind ( @RequestBody AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<Long> ids = dto.getIds();
		if (CollectionUtils.isEmpty(ids)) {
			result.setErrorInfo("The ID cannot be empty");
			return result;
		}
		try {
			dto.setUserName(super.getUserName(dto.getUserId()));
			alertInfoService.updateAlertUnbind(dto, super.isAdmin(dto.getUserId()));
		} catch (ServiceException e) {
			result.setErrorInfo(e.getMessage());
		}
		return result;
	}

	/**
	 * case解除绑定
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/case/review/unbind", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> updateCaseUnbind ( @RequestBody AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<Long> ids = dto.getIds();
		if (CollectionUtils.isEmpty(ids)) {
			result.setErrorInfo("The ID cannot be empty");
			return result;
		}
		try {
			dto.setUserName(super.getUserName(dto.getUserId()));
			alertInfoService.updateCaseUnbind(dto, super.isAdmin(dto.getUserId()));
		} catch (ServiceException e) {
			result.setErrorInfo(e.getMessage());

		}
		return result;
	}

	/**
	 * 查询所有originalId
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/originalids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Set<String>> queryOriginalIds ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Set<String>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		if (ObjectUtils.isEmpty(alertId) && ObjectUtils.isEmpty(caseId)) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		Set<String> data = null;
		if (!ObjectUtils.isEmpty(alertId)) {
			data = alertInfoService.queryAlertOriginalIds(alertId);
		}
		if (!ObjectUtils.isEmpty(caseId)) {
			data = alertInfoService.queryCaseOriginalIds(caseId);
		}
		result.setData(data);
		return result;
	}

	/**
	 * 搜索alertId/caseId
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Long>> searchAlertIdOrCaseId ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<List<Long>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		if (ObjectUtils.isEmpty(alertId) && ObjectUtils.isEmpty(caseId)) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		List<Long> data = new ArrayList<Long>();
		if (!ObjectUtils.isEmpty(alertId)) {
			dto.setUserName(super.getPermissionUsername(dto.getUserId(), null, FlowConsts.TYPE_ALERT, dto.getToken()));
			data = alertInfoService.searchAlertId(dto);
		}
		if (!ObjectUtils.isEmpty(caseId)) {
			dto.setUserName(super.getPermissionUsername(dto.getUserId(), null, FlowConsts.TYPE_SAR, dto.getToken()));
			data = caseInfoService.searchCaseId(dto);
		}

		result.setData(data);
		return result;
	}
	
	/**
	 * 查看文件
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/file", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Map<String, Object>> alertFile ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		Long relateId = null;
		Integer source = null;
		Map<String, Object> map = new HashMap<>();
		if(!ObjectUtils.isEmpty(alertId)) {
			relateId = alertId;
			source = 1;
			AlertGroup alertGroup = alertGroupMapper.queryFile(alertId);
			map.put("alertFile", alertGroup);
		}else if(!ObjectUtils.isEmpty(caseId)) {
			relateId = caseId;
			source = 2;
			CaseGroup caseGroup = caseGroupMapper.queryFile(caseId);
			map.put("alertFile", caseGroup);
		}else {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		//查询所有邮件文件
		List<SendRFI> sendRFIs = sendRFIMapper.queryAllFile(relateId,source);
		//查询流程文件
		List<WfTaskFile>  wfTaskFiles = wfTaskFileMapper.queryAllFile(alertId,caseId);
		map.put("wfTaskFiles", wfTaskFiles);
		map.put("sendRFIs", sendRFIs);
		result.setData(map);
		return result;
	}
	
	/**
	 * RFI(查询历史记录)
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/rfi/{control}/history", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Rfi> alertRfiHistory ( AlertReviewDTO dto,@PathVariable Long control, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Rfi> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(!ObjectUtils.isEmpty(control)) {
			result.setErrorInfo("The Control cannot be empty");
			return result;
		}
		Rfi rfi = rfiMapper.queryRfiHistory(control);
		result.setData(rfi);
		return result;
	}
	
	/**
	 * RFI(查询control)
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/rfi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> alertReviewRfiControl ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
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
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		Long control = rfiMapper.queryControlByRelateIdAndSource(relateId,source);
		result.setData(control);
		return result;
	}
	
	/**
	 * RFI(发送邮件)
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/review/rfi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> alertReviewRfi (@RequestBody AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Long alertId = dto.getAlertId();
		Long caseId = dto.getCaseId();
		if (ObjectUtils.isEmpty(alertId) && ObjectUtils.isEmpty(caseId)) {
			result.setErrorInfo("The Alert ID or Case ID cannot be empty");
			return result;
		}
		Integer controrNumber = dto.getControrNumber();
		if (ObjectUtils.isEmpty(controrNumber)) {
			result.setErrorInfo("The Contror Number cannot be empty");
			return result;
		}
		String from = dto.getFrom();
		if (ObjectUtils.isEmpty(from)) {
			result.setErrorInfo("The From cannot be empty");
			return result;
		}
		String to = dto.getTo();
		if (ObjectUtils.isEmpty(to)) {
			result.setErrorInfo("The To cannot be empty");
			return result;
		}
		String subject = dto.getSubject();
		if (ObjectUtils.isEmpty(subject)) {
			result.setErrorInfo("The Subject cannot be empty");
			return result;
		}
		String comment = dto.getComment();
		if (ObjectUtils.isEmpty(comment)) {
			result.setErrorInfo("The Comment cannot be empty");
			return result;
		}
		alertInfoService.alertReviewRfi(dto);
		return result;
	}

	private boolean isContain ( String pendingStatus, String status ) {
		boolean flag = false;
		String[] arr = pendingStatus.split(",");
		for (String str : arr) {
			if (str.equals(status)) {
				flag = true;
			}
		}
		return flag;
	}
	
	
}
