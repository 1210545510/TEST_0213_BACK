package com.aml.api.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dto.AlertDTO;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.pojo.DicTb;
import com.aml.api.service.AlertService;
import com.aml.api.service.ConfigService;
import com.aml.common.base.BaseController;
import com.aml.common.core.AlertConsts;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ColumnConsts;
import com.aml.common.core.ConfigConsts;
import com.aml.common.core.DicConsts;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.FlowConsts;
import com.aml.common.file.FileOptHelper;
import com.aml.common.file.export.ReportExcelExporter;
import com.aml.common.util.PageUtils;
import com.aml.common.util.StrUtils;
import com.github.pagehelper.Page;

/**
 * 
 * @className: AlertController
 * @description:
 * @author shun
 * @date 2017年11月22日
 *
 */
@RestController
public class AlertController extends BaseController {

	@Autowired
	private AlertService alertService;
	@Autowired
	private AlertGroupMapper alertGroupMapper;
	@Autowired
	private ConfigService configService;

	/**
	 * 查询Alert列表
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月22日
	 */
	@RequestMapping(value = "/alert/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Map<String, Object>> queryAlertList ( AlertDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiPageResult<Map<String, Object>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		// 获取登录时选择的系统来源
		String sysType = super.getSysType(dto.getToken(), dto.getUserId());
		if(StringUtils.isBlank(sysType)){
			result.setErrorInfo("System source is empty");
			return result;
		}
		dto.setSysType(sysType);
		this.initOrderBy(dto);
		Page<Map<String, Object>> data = null;
		if (AlertConsts.STATE_TODO == dto.getState()) {
			dto.setUserName(super.getPermissionUsername(dto.getUserId(), dto.getUserName(), FlowConsts.TYPE_ALERT, dto.getToken()));
			data = alertService.queryPageAlert(dto);
		} else if (AlertConsts.STATE_REVIEWED == dto.getState()) {
			dto.setAssigner(dto.getUserName());
			dto.setUserName(super.getUserName(dto.getUserId()));
			data = alertService.queryPageAlertReviewed(dto);
		}
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 创建Alert
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月22日
	 */
	@RequestMapping(value = "/alert/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> addAlert ( @RequestBody AlertDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		String alertType = dto.getAlertType();
		String subjectType = dto.getSubjectType();
		String ownerOrg = dto.getOwnerOrg();
		String subject = dto.getSubject();
		String customerId = dto.getCustomerId();
		String accountId = dto.getAccountId();
		String alertReason = dto.getAlertReason();
		if (StringUtils.isBlank(alertType)) {
			result.setErrorInfo("The Alert type cannot be empty");
			return result;
		}
		if (StringUtils.isBlank(subjectType)) {
			result.setErrorInfo("The Subject type cannot be empty");
			return result;
		}
		if (StringUtils.isBlank(ownerOrg)) {
			result.setErrorInfo("The Owner cannot be empty");
			return result;
		}
		if (StringUtils.isBlank(subject) && StringUtils.isBlank(customerId) && StringUtils.isBlank(accountId)) {
			result.setErrorInfo("Subject and Customer ID and Account ID are at least one not empty");
			return result;
		}
		if (StringUtils.isBlank(alertReason)) {
			result.setErrorInfo("The Alert Reason cannot be empty");
			return result;
		}
		if(StrUtils.strLength(alertReason)> 200){
			result.setErrorInfo("The Alert Reason length is more than 200 characters");
			return result;
		}
		dto.setUserName(super.getUserName(dto.getUserId()));
		dto.setIsAdminCreate(super.isAdmin(dto.getUserId()) ? 1 : 0);
		alertService.addAlert(dto);
		return result;
	}

	/**
	 * 获取第一条alertId
	 * 
	 * @param dto
	 * @param request
	 * @return
	 * @date 2017年12月5日
	 */
	@RequestMapping(value = "/alert/review/article1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Object> queryAlertReviewarticle1 ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Object> result = new ApiResult<>(ErrorTypeEnum.OK);
		AlertDTO alertDTO = new AlertDTO();
		alertDTO.setUserName(dto.getUserName());
		alertDTO.setOrderby("a.alert_id desc");
		Long aId = dto.getAlertId();
		if(!ObjectUtils.isEmpty(aId)){
			alertDTO.setAlertId(String.valueOf(aId));
		}
		alertDTO.setUserName(super.getPermissionUsername(dto.getUserId(), dto.getUserName(), FlowConsts.TYPE_ALERT, dto.getToken()));
		Page<Map<String, Object>> queryPageAlert = alertService.queryPageAlert(alertDTO);
		if (ObjectUtils.isEmpty(queryPageAlert)) {
			result.setErrorInfo("No record at the moment");
			return result;
		}
		Object alertId = queryPageAlert.get(0).get("alertId");
		result.setData(alertId);
		return result;
	}

	/**
	 * 导出csv
	 * 
	 * @param dto
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @date 2017年11月23日
	 */
	@RequestMapping(value = "/alert/excel", method = RequestMethod.POST)
	public void exportCSV ( AlertDTO dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		this.initOrderBy(dto);
		// 查询alert 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_ALERT_LIST);
		String[][] columns = new String[pendingFields.size() + 3][2];
		// 固定字段列
		columns[0][0] = ColumnConsts.C_ALERT_ID;
		columns[0][1] = ColumnConsts.T_ALERT_ID;
		columns[1][0] = ColumnConsts.C_STATUS;
		columns[1][1] = ColumnConsts.T_STATUS;
		int length = 2;
		for (DicTb dicTb : pendingFields) {
			if (ColumnConsts.T_TYPE.equals(dicTb.getDicValue())) {
				columns[length][0] = StrUtils.spaceToCamel(ColumnConsts.T_TYPE_VALUE);
				columns[length][1] = dicTb.getDicValue();
			} else {
				columns[length][0] = StrUtils.spaceToCamel(dicTb.getDicValue());
				columns[length][1] = dicTb.getDicValue();
			}
			length++;
		}
		columns[length][0] = ColumnConsts.C_USERNAME;
		columns[length][1] = ColumnConsts.T_ASSIGNED_TO;

		List<Map<String, Object>> exportData = null;
		if (AlertConsts.STATE_TODO == dto.getState()) {
			dto.setUserName(super.getPermissionUsername(dto.getUserId(), dto.getUserName(), FlowConsts.TYPE_ALERT, dto.getToken()));
			exportData = alertService.exportCSV(dto);
		} else if (AlertConsts.STATE_REVIEWED == dto.getState()) {
			dto.setAssigner(dto.getUserName());
			dto.setUserName(super.getUserName(dto.getUserId()));
			exportData = alertService.exportReviewedCSV(dto);
		}
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		new ReportExcelExporter(response, ConfigConsts.E_ALERT_LIST_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * 合并Alert
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月22日
	 */
	@RequestMapping(value = "/alert/merge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> alertMerge ( @RequestBody AlertDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<Long> alertIdList = dto.getAlertIdList();
		if (ObjectUtils.isEmpty(alertIdList)) {
			result.setErrorInfo("The Alert ID cannot be empty");
			return result;
		}
		if(alertGroupMapper.isSameGroup(alertIdList) != 1) {
			result.setErrorInfo("Sorry, this alert does not belong to the same group and you have no right to merge.");
			return result;
		}
		if(alertGroupMapper.checkMergeState(alertIdList) > 0) {
			result.setErrorInfo("Workflows that have been processed cannot be merged.");
			return result;
		}
		dto.setUserName(super.getUserName(dto.getUserId()));
		alertService.alertMerge(dto);
		return result;
	}

	private void initOrderBy ( AlertDTO dto ) {
		String orderby = dto.getOrderby();
		switch (orderby) {
		case "1":
			dto.setOrderby("a.score");
			break;
		case "2":
			dto.setOrderby("a.score desc");
			break;
		case "3":
			dto.setOrderby("a.amount");
			break;
		case "4":
			dto.setOrderby("a.amount desc");
			break;
		case "5":
			dto.setOrderby("a.volume");
			break;
		case "6":
			dto.setOrderby("a.volume desc");
			break;
		case "7":
			dto.setOrderby("a.due_date");
			break;
		case "8":
			dto.setOrderby("a.due_date desc");
			break;
		case "9":
			dto.setOrderby("a.create_time");
			break;
		case "10":
			dto.setOrderby("a.create_time desc");
			break;
		default:
			if (AlertConsts.STATE_TODO == dto.getState()) {
				dto.setOrderby("a.due_date");
			} else if (AlertConsts.STATE_REVIEWED == dto.getState()) {
				dto.setOrderby("a.update_time desc");
			}
			break;
		}
	}
}
