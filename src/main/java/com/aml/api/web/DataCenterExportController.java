package com.aml.api.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dao.RfiMapper;
import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.dto.ClientBankDto;
import com.aml.api.dto.CustDto;
import com.aml.api.dto.Data314ADto;
import com.aml.api.dto.ExtUserRequestDto;
import com.aml.api.dto.GrandJurySubpoenaDto;
import com.aml.api.pojo.DicTb;
import com.aml.api.service.AlertService;
import com.aml.api.service.CaseService;
import com.aml.api.service.ClientBankService;
import com.aml.api.service.ConfigService;
import com.aml.api.service.CustService;
import com.aml.api.service.Data314AService;
import com.aml.api.service.ExtUserRequestService;
import com.aml.api.service.GrandJurySubpoenaService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ColumnConsts;
import com.aml.common.core.ConfigConsts;
import com.aml.common.core.DicConsts;
import com.aml.common.file.FileOptHelper;
import com.aml.common.file.export.ReportExcelExporter;
import com.aml.common.util.StrUtils;

/**
 * Data center query
 * 
 * @author Novan
 * @date 2017-11-27
 *
 */
@RestController
public class DataCenterExportController extends BaseController {
	@Autowired
	private ExtUserRequestService extUserRequestService;
	@Autowired
	private ClientBankService clientBankService;
	@Autowired
	private CustService custService;
	@Autowired
	private Data314AService data314AService;
	@Autowired
	private GrandJurySubpoenaService grandJurySubpoenaService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private RfiMapper rfiMapper;

	/**
	 * ClientBank List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downClientBankExcel", method = RequestMethod.POST)
	public void downClientBankExcel ( ClientBankDto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_CLIENT_BANK_LIST);
		String[][] columns = new String[pendingFields.size() + 1][2];
		// 固定列字段
		columns[0][0] = ColumnConsts.C_INSTN_SEQ_ID;
		columns[0][1] = ColumnConsts.T_INSTN_SEQ_ID;
		int length = 1;
		for (DicTb dicTb : pendingFields) {
			this.addClientBankColumns(columns, length, dicTb.getDicValue());
			length++;
		}
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = clientBankService.downClientBankExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_CLIENT_BANK_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * ExtUserRequest List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downExtUserRequestExcel", method = RequestMethod.POST)
	public void downExtUserRequestExcel ( ExtUserRequestDto dto, HttpServletRequest request,
			HttpServletResponse response ) throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_EXT_USER_REQUEST_LIST);
		String[][] columns = new String[pendingFields.size() + 1][2];
		// 固定列字段
		columns[0][0] = ColumnConsts.C_EXTERNAL_REQUEST_ID;
		columns[0][1] = ColumnConsts.T_EXTERNAL_REQUEST_ID;
		int length = 1;
		for (DicTb dicTb : pendingFields) {
			this.addExtUserRequestColumns(columns, length, dicTb.getDicValue());
			length++;
		}
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = extUserRequestService.downExtUserRequestExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_EXT_USER_REQUEST_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * Cust List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downCustExcel", method = RequestMethod.POST)
	public void downCustExcel ( CustDto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_CUSTOMER_PROFILE_LIST);
		String[][] columns = new String[pendingFields.size() + 1][2];
		// 固定列字段
		columns[0][0] = ColumnConsts.C_CUSTOMER_ID;
		columns[0][1] = ColumnConsts.T_CUSTOMER_ID;
		int length = 1;
		for (DicTb dicTb : pendingFields) {
			this.addCustomerProfileColumns(columns, length, dicTb.getDicValue());
			length++;
		}
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = custService.downCustExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_CUSTOMER_PROFILE_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);

	}

	/**
	 * Data314A List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downData314AExcel", method = RequestMethod.POST)
	public void downData314AExcel ( Data314ADto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_314A_LIST);
		String[][] columns = new String[pendingFields.size() + 1][2];
		// 固定列字段
		columns[0][0] = ColumnConsts.C_TRACKING_NUM_ID;
		columns[0][1] = ColumnConsts.T_TRACKING_NUM_ID;
		int length = 1;
		for (DicTb dicTb : pendingFields) {
			this.addData314AColumns(columns, length, dicTb.getDicValue());
			length++;
		}
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = data314AService.downData314AExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_314A_NAME, exportData, titleList, ConfigConsts.EXCEL_CSV);
	}

	/**
	 * GrandJurySubpoena List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downGrandJurySubpoenaExcel", method = RequestMethod.POST)
	public void downGrandJurySubpoenaExcel ( GrandJurySubpoenaDto dto, HttpServletRequest request,
			HttpServletResponse response ) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_GRAND_JURY_SUBPOENA_LIST);
		String[][] columns = new String[pendingFields.size() + 1][2];
		// 固定列字段
		columns[0][0] = ColumnConsts.C_EXTERNAL_REQUEST_ID;
		columns[0][1] = ColumnConsts.T_EXTERNAL_REQUEST_ID;
		int length = 1;
		for (DicTb dicTb : pendingFields) {
			this.addGrandJurySubColumns(columns, length, dicTb.getDicValue());
			length++;
		}
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = grandJurySubpoenaService.downGrandJurySubpoenaExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_GRAND_JURY_SUB_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * Alert History List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downAlertInfoExcel", method = RequestMethod.POST)
	public void downAlertInfoExcel ( AlertInfoDto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_ALERT_HISTORY_LIST);
		String[][] columns = new String[pendingFields.size() + 2][2];
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
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = alertService.downAlertInfoExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_ALERT_HISTORY_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * CTR History List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downCTRInfoExcel", method = RequestMethod.POST)
	public void downCTRInfoExcel ( AlertInfoDto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		// 查询 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_CTR_HISTORY_LIST);
		String[][] columns = new String[pendingFields.size() + 2][2];
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
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = alertService.downAlertInfoExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_CTR_HISTORY_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * Case History List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downCaseInfoExcel", method = RequestMethod.POST)
	public void downCaseInfoExcel ( CaseInfoDto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		dto.setStatus(19);
		// 查询case 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_CASE_HISTORY_LIST);
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
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = caseService.downCaseInfoExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_CASE_HISTORY_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * SAR History List Download
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downSarInfoExcel", method = RequestMethod.POST)
	public void downSarInfoExcel ( CaseInfoDto dto, HttpServletRequest request, HttpServletResponse response )
			throws ParseException {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		dto.setStatus(11);
		// 查询case 需要显示的字段
		List<DicTb> pendingFields = configService.queryDicTbsByDicId(DicConsts.D_SAR_HISTORY_LIST);
		String[][] columns = new String[pendingFields.size() + 3][2];
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
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(columns);
		List<Map<String, Object>> exportData = caseService.downCaseInfoExcel(dto);
		new ReportExcelExporter(response, ConfigConsts.E_SAR_HISTORY_NAME, exportData, titleList,
				ConfigConsts.EXCEL_CSV);
	}

	/**
	 * @title: RFI导出
	 * @description: Query Data314A
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/rfi/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void rfiExport(Data314ADto dto, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("request url: {}, dto: {}", request.getRequestURL(), dto.toJson());
		List<Map<String, Object>> exportData = rfiMapper.queryRfiExport(dto);
		String fileName = "RFI list";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] { { "control", "Control" },
			{ "subject_name", "Subject Name" }, { "subject_country", "Subject Country" }, { "subject_type", "Type" },
			{ "customer_id", "Customer ID" }, { "requesting_party", "Requesting Party" }, { "recipient", "Recipient" }, 
			{"create_time","Initial Request Date"},	{"update_time", "Last Update Date"}, {"transaction_id", "Transaction ID"}, 
//			{"lastModified", "Attachment"} 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,ConfigConsts.EXCEL_CSV);
	}

	/**
	 * 拼接Client Bank List导出表头
	 * 
	 * @param addColumns : 表头数据
	 * @param length : 当前表头下标
	 * @param dicValue : 列名
	 * @date 2017年11月23日
	 */
	private void addClientBankColumns ( String[][] addColumns, int length, String dicValue ) {
		String field = null;
		switch (dicValue) {
		case "INSTN_SEQ_ID":
			field = "INSTN_SEQ_ID";
			break;
		case "CLIENT_BANK Name":
			field = "INSTN_NM";
			break;
		case "Effective Risk":
			field = "CB_EFCTV_RISK_NB";
			break;
		case "Account Manager Name":
			field = "ACCT_MGR_NM";
			break;
		case "Business Unit":
			field = "BUS_UNIT_CD";
			break;
		case "Activity Items":
			field = "activityItems";
			break;
		case "Alert Activity Items":
			field = "alertActivityItems";
			break;
		case "Alert Activity Amount":
			field = "alertActivityAmount";
			break;
		case "Activity Amount":
			field = "activityAmount";
			break;
		case "Action":
			field = "EDD Review";
			break;
		default:
			break;
		}
		if (StringUtils.isNotBlank(field)) {
			addColumns[length][0] = field;
			addColumns[length][1] = dicValue;
		}
	}

	/**
	 * 拼接External User Request List导出表头
	 * 
	 * @param addColumns : 表头数据
	 * @param length : 当前表头下标
	 * @param dicValue : 列名
	 * @date 2017年11月23日
	 */
	private void addExtUserRequestColumns ( String[][] addColumns, int length, String dicValue ) {
		String field = null;
		switch (dicValue) {
		case "Requesting Party":
			field = "requesting_party";
			break;
		case "Name":
			field = "name";
			break;
		case "Birth Date":
			field = "birthdate";
			break;
		case "ID Number":
			field = "id_num";
			break;
		case "Address":
			field = "address";
			break;
		case "Bank Name":
			field = "bank_name";
			break;
		case "Account Number":
			field = "account_number";
			break;
		case "AKA or DBA":
			field = "aka_or_dba";
			break;
		case "Internal BSA":
			field = "internal_bsa";
			break;
		case "Swift Code":
			field = "swift_code";
			break;
		case "Customer Status":
			field = "cust_status";
			break;
		default:
			break;
		}
		if (StringUtils.isNotBlank(field)) {
			addColumns[length][0] = field;
			addColumns[length][1] = dicValue;
		}
	}

	/**
	 * 拼接Customer Profile List导出表头
	 * 
	 * @param addColumns : 表头数据
	 * @param length : 当前表头下标
	 * @param dicValue : 列名
	 * @date 2017年11月23日
	 */
	private void addCustomerProfileColumns ( String[][] addColumns, int length, String dicValue ) {
		String field = null;
		switch (dicValue) {
		case "First Name":
			field = "FIRST_NM";
			break;
		case "Middle Name":
			field = "MIDL_NM";
			break;
		case "Last Name":
			field = "LAST_NM";
			break;
		case "Type":
			field = "CUST_TYPE_CD";
			break;
		case "Address":
			field = "ADDR_RGN_NM";
			break;
		case "City":
			field = "ADDR_CITY_NM";
			break;
		case "Country":
			field = "ADDR_CNTRY_CD";
			break;
		case "State":
			field = "ADDR_STATE_CD";
			break;
		case "Tax ID/SSN":
			field = "TAX_ID";
			break;
		default:
			break;
		}
		if (StringUtils.isNotBlank(field)) {
			addColumns[length][0] = field;
			addColumns[length][1] = dicValue;
		}
	}

	/**
	 * 拼接Data 314A List导出表头
	 * 
	 * @param addColumns : 表头数据
	 * @param length : 当前表头下标
	 * @param dicValue : 列名
	 * @date 2017年11月23日
	 */
	private void addData314AColumns ( String[][] addColumns, int length, String dicValue ) {
		String field = null;
		switch (dicValue) {
		case "Tracking Number":
			field = "tracking_num";
			break;
		case "Subject Type":
			field = "subject_type";
			break;
		case "Name":
			field = "name";
			break;
		case "Number Type":
			field = "number_type";
			break;
		case "Number":
			field = "number";
			break;
		case "Street":
			field = "street";
			break;
		case "City":
			field = "city";
			break;
		case "Zip":
			field = "zip";
			break;
		case "Country":
			field = "country";
			break;
		case "State":
			field = "state";
			break;
		case "Internal BSA":
			field = "internal_bsa";
			break;
		default:
			break;
		}
		if (StringUtils.isNotBlank(field)) {
			addColumns[length][0] = field;
			addColumns[length][1] = dicValue;
		}
	}

	/**
	 * 拼接Grand Jury Subpoena List导出表头
	 * 
	 * @param addColumns : 表头数据
	 * @param length : 当前表头下标
	 * @param dicValue : 列名
	 * @date 2017年11月23日
	 */
	private void addGrandJurySubColumns ( String[][] addColumns, int length, String dicValue ) {
		String field = null;
		switch (dicValue) {
		case "Requesting Party":
			field = "requesting_party";
			break;
		case "Name":
			field = "name";
			break;
		case "Birth Date":
			field = "birthdate";
			break;
		case "ID Number":
			field = "id_num";
			break;
		case "Address":
			field = "address";
			break;
		case "Bank Name":
			field = "bank_name";
			break;
		case "Account Number":
			field = "account_num";
			break;
		case "AKA or DBA":
			field = "aka_or_dba";
			break;
		case "Swift Code":
			field = "swift_code";
			break;
		case "Internal BSA":
			field = "internal_bsa";
			break;
		case "Legal Counsel Company":
			field = "legal_counsel_company";
			break;
		case "Legal Counsel Name":
			field = "legal_counsel_name";
			break;
		case "Customer Status":
			field = "cust_status";
			break;
		default:
			break;
		}
		if (StringUtils.isNotBlank(field)) {
			addColumns[length][0] = field;
			addColumns[length][1] = dicValue;
		}
	}

}
