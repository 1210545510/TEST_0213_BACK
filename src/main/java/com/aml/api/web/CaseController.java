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

import com.aml.api.dao.CaseGroupMapper;
import com.aml.api.dto.AlertReviewDTO;
import com.aml.api.dto.CaseDTO;
import com.aml.api.pojo.DicTb;
import com.aml.api.service.CaseService;
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
 * @className: CaseController
 * @description: Case List
 * @author shun
 * @date 2017年12月8日
 *
 */
@RestController
public class CaseController extends BaseController {
	@Autowired
	private CaseService caseService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private CaseGroupMapper caseGroupMapper;

	/**
	 * 查询Case列表
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月22日
	 */
	@RequestMapping(value = "/case/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Map<String, Object>> queryCaseList ( CaseDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiPageResult<Map<String, Object>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		this.initOrderBy(dto);
		Page<Map<String, Object>> data = null;
		if (AlertConsts.STATE_TODO == dto.getState()) {
			dto.setUserName(super.getPermissionUsername(dto.getUserId(), dto.getUserName(), FlowConsts.TYPE_SAR, dto.getToken()));
			data = caseService.queryPageCase(dto);
		} else if (AlertConsts.STATE_REVIEWED == dto.getState()) {
			dto.setAssigner(dto.getUserName());
			dto.setUserName(super.getUserName(dto.getUserId()));
			data = caseService.queryPageCaseReviewed(dto);
		}
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 获取第一条caseId
	 * 
	 * @param dto
	 * @param request
	 * @return
	 * @date 2017年12月5日
	 */
	@RequestMapping(value = "/case/review/article1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Object> queryCaseReviewarticle ( AlertReviewDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Object> result = new ApiResult<>(ErrorTypeEnum.OK);
		CaseDTO alertDTO = new CaseDTO();
		alertDTO.setUserName(dto.getUserName());
		alertDTO.setOrderby("c.case_id desc");
		alertDTO.setUserName(super.getPermissionUsername(dto.getUserId(), dto.getUserName(), FlowConsts.TYPE_SAR, dto.getToken()));
		Page<Map<String, Object>> queryPageAlert = caseService.queryPageCase(alertDTO);
		if (queryPageAlert.size() == 0) {
			result.setErrorInfo("No record at the moment");
			return result;
		}
		result.setData(queryPageAlert.get(0).get("caseId"));
		return result;
	}

	/**
	 * 创建Case
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月22日
	 */
	@RequestMapping(value = "/case/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> addCase ( @RequestBody CaseDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		String caseType = dto.getCaseType();
		String subject = dto.getSubject();
		String customerId = dto.getCustomerId();
		String accountId = dto.getAccountId();
		String caseReason = dto.getCaseReason();
		if (StringUtils.isBlank(caseType)) {
			result.setErrorInfo("The Case type cannot be empty");
			return result;
		}
		if (StringUtils.isBlank(subject) && StringUtils.isBlank(customerId) && StringUtils.isBlank(accountId)) {
			result.setErrorInfo("Subject and Customer ID and Account ID are at least one not empty");
			return result;
		}
		if (StringUtils.isBlank(caseReason)) {
			result.setErrorInfo("The Case Reason cannot be empty");
			return result;
		}
		if(StrUtils.strLength(caseReason)> 200){
			result.setErrorInfo("The Case Reason length is more than 200 characters");
			return result;
		}
		dto.setUserName(super.getUserName(dto.getUserId()));
		dto.setIsAdminCreate(super.isAdmin(dto.getUserId()) ? 1 : 0);
		caseService.addCase(dto);
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
	@RequestMapping(value = "/case/excel", method = RequestMethod.POST)
	public void exportCSV ( CaseDTO dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		this.initOrderBy(dto);
		// 查询case 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_CASE_LIST);
		String[][] columns = new String[pendingFields.size() + 4][2];
		// 固定列字段
		columns[0][0] = ColumnConsts.C_CASE_ID;
		columns[0][1] = ColumnConsts.T_CASE_ID;
		columns[1][0] = ColumnConsts.C_ALERT_ID;
		columns[1][1] = ColumnConsts.T_ALERT_ID;
		columns[2][0] = ColumnConsts.C_STATUS;
		columns[2][1] = ColumnConsts.T_STATUS;
		int length = 3;
		for (DicTb dicTb : pendingFields) {
			if (ColumnConsts.T_CASE_TYPE.equals(dicTb.getDicValue())) {
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
		List<Map<String, Object>> exportData = caseService.exportCSV(dto);
		if (AlertConsts.STATE_TODO == dto.getState()) {
			dto.setUserName(super.getPermissionUsername(dto.getUserId(), dto.getUserName(), FlowConsts.TYPE_SAR, dto.getToken()));
			exportData = caseService.exportCSV(dto);
		} else if (AlertConsts.STATE_REVIEWED == dto.getState()) {
			dto.setAssigner(dto.getUserName());
			dto.setUserName(super.getUserName(dto.getUserId()));
			exportData = caseService.exportReviewedCSV(dto);
		}

		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		new ReportExcelExporter(response, ConfigConsts.E_CASE_LIST_NAME, exportData, titleList, ConfigConsts.EXCEL_CSV);
	}

	/**
	 * 合并Case
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月22日
	 */
	@RequestMapping(value = "/case/merge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> alertMerge ( @RequestBody CaseDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} dto:{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<Long> caseIdList = dto.getCaseIdList();
		if (ObjectUtils.isEmpty(caseIdList)) {
			result.setErrorInfo("The Case ID cannot be empty");
			return result;
		}
		if(caseGroupMapper.checkMergeState(caseIdList) > 0) {
			result.setErrorInfo("Workflows that have been processed cannot be merged.");
			return result;
		}
		dto.setUserName(super.getUserName(dto.getUserId()));
		caseService.alertMerge(dto);
		return result;
	}

	private void initOrderBy ( CaseDTO dto ) {
		String orderby = dto.getOrderby();
		switch (orderby) {
		case "1":
			dto.setOrderby("c.score");
			break;
		case "2":
			dto.setOrderby("c.score desc");
			break;
		case "3":
			dto.setOrderby("c.amount");
			break;
		case "4":
			dto.setOrderby("c.amount desc");
			break;
		case "5":
			dto.setOrderby("c.volume");
			break;
		case "6":
			dto.setOrderby("c.volume desc");
			break;
		case "7":
			dto.setOrderby("c.due_date");
			break;
		case "8":
			dto.setOrderby("c.due_date desc");
			break;
		case "9":
			dto.setOrderby("c.create_time");
			break;
		case "10":
			dto.setOrderby("c.create_time desc");
			break;
		default:
			if (AlertConsts.STATE_TODO == dto.getState()) {
				dto.setOrderby("c.due_date");
			} else if (AlertConsts.STATE_REVIEWED == dto.getState()) {
				dto.setOrderby("c.update_time desc");
			}
			break;
		}
	}
}
