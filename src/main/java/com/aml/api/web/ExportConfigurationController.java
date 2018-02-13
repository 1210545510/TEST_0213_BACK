package com.aml.api.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.AccountTypesDto;
import com.aml.api.dto.ActivityMapsDto;
import com.aml.api.dto.ActivityTypesDto;
import com.aml.api.dto.CloseReasonDto;
import com.aml.api.dto.CountriesDto;
import com.aml.api.dto.CustomerTypesDto;
import com.aml.api.dto.ExemptionsDto;
import com.aml.api.dto.FormComfigurationDto;
import com.aml.api.dto.JobsDto;
import com.aml.api.dto.LogsDto;
import com.aml.api.dto.RelationshipTypesDto;
import com.aml.api.dto.SequencesDto;
import com.aml.api.dto.SystemParametersDto;
import com.aml.api.dto.TemplateRulesDto;
import com.aml.api.dto.UserDefindRulesDto;
import com.aml.api.service.AccountTypesService;
import com.aml.api.service.ActivityMapsService;
import com.aml.api.service.ActivityTypesService;
import com.aml.api.service.CloseReasonService;
import com.aml.api.service.CountriesService;
import com.aml.api.service.CustomerTypesService;
import com.aml.api.service.ExemptionsService;
import com.aml.api.service.FormComfigurationService;
import com.aml.api.service.JobsService;
import com.aml.api.service.LogsService;
import com.aml.api.service.RelationshipTypesService;
import com.aml.api.service.SequencesService;
import com.aml.api.service.SystemParametersService;
import com.aml.api.service.TemplateRulesService;
import com.aml.api.service.UserDefindRulesService;
import com.aml.common.base.BaseController;
import com.aml.common.file.FileOptHelper;
import com.aml.common.file.export.ReportExcelExporter;

/**
 * Export list data from all settings
 * @author Novan
 * @date 2017-11-22
 *
 */
@RestController
public class ExportConfigurationController extends BaseController {
	
	@Autowired
	private AccountTypesService accountTypesService;
	
	@Autowired
	private ActivityMapsService activityMapsService;
	
	@Autowired
	private ActivityTypesService activityTypesService;
	
	@Autowired
	private CountriesService countriesService;
	
	@Autowired
	private CustomerTypesService customerTypesService;
	
	@Autowired
	private CloseReasonService closeReasonService;
	
	@Autowired
	private ExemptionsService exemptionsService;
	
	@Autowired
	private FormComfigurationService formComfigurationService;
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private LogsService logsService;
	
	@Autowired
	private RelationshipTypesService relationshipTypesService;
	
	@Autowired
	private TemplateRulesService templateRulesService;
	
	@Autowired
	private UserDefindRulesService userDefindRulesService;
	
	@Autowired
	private SequencesService sequencesService;
	
	@Autowired
	private SystemParametersService systemParametersService;
	
	
	/**
	 * AccountTypes List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downAccountTypesExcel", method = RequestMethod.POST)
	public void downAccountTypesExcel(AccountTypesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = accountTypesService.downAccountTypesExcel(dto);
		String fileName = "AccountTypesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code", "Code" },
			{ "name", "Name" },
			{"risk_factor", "Risk factor" },
			{ "created_by", "Created by" },
			{ "create_date", "Create Date" } ,
			{ "last_modify_date", "Last modify Date" }, 
			{ "last_modify_oper", "Last modify oper" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * ActivityMaps List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downActivityMapsExcel", method = RequestMethod.POST)
	public void downActivityMapsExcel(ActivityMapsDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = activityMapsService.downActivityMapsExcel(dto);
		String fileName = "ActivityMapsExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "original_activity", "Original Activity" },
			{ "job_id", "Job ID" },
			{"prime_code", "Prime code" },
			{ "created_by", "Created by" },
			{ "last_modify_date", "Last modify Date" },
			{ "last_modify_oper", "Last modify oper" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * ActivityTypes List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downActivityTypesExcel", method = RequestMethod.POST)
	public void downActivityTypesExcel(ActivityTypesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = activityTypesService.downActivityTypesExcel(dto);
		String fileName = "ActivityTypesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code_name", "Code Name" },
			{ "risk_factor", "Risk factor" },
			{ "created_by", "Created by" },
			{ "last_modify_date", "Last modify Date" },
			{ "last_modify_oper", "Last modify oper" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * CloseReason List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downCloseReasonExcel", method = RequestMethod.POST)
	public void downCloseReasonExcel(CloseReasonDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = closeReasonService.downCloseReasonExcel(dto);
		String fileName = "CloseReasonExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code", "Code" },
			{ "object_type", "Object Type" },
			{ "name", "Name" },
			{ "create_operation", "Create Operation" },
			{ "date_of_careation", "Date Of Careation" },
			{ "modified_operation", "Modified Operation" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * Countries List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downCountriesExcel", method = RequestMethod.POST)
	public void downCountriesExcel(CountriesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = countriesService.downCountriesExcel(dto);
		String fileName = "CountriesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code_name", "Code Name" },
			{ "region", "Region" },
			{ "monitor", "Monitor" },
			{ "rist", "Rist" },
			{ "case_score", "Case Score" },
			{ "creat_by", "Creat By" },
			{ "creat_date", "Creat Date" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * CustomerTypes List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downCustomerTypesExcel", method = RequestMethod.POST)
	public void downCustomerTypesExcel(CustomerTypesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = customerTypesService.downCustomerTypesExcel(dto);
		String fileName = "CustomerTypesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code_name", "Code Name" },
			{ "descripition", "Descripition" },
			{ "risk_model", "Risk Model" },
			{ "accepetion_model", "Accepetion Model" },
			{ "case_score", "Case Score" },
			{ "creat_by", "Creat By" },
			{ "creat_date", "Creat Date" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * Exemptions List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downExemptionsExcel", method = RequestMethod.POST)
	public void downExemptionsExcel(ExemptionsDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = exemptionsService.downExemptionsExcel(dto);
		String fileName = "ExemptionsExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code_name", "Code Name" },
			{ "creat_by", "Creat By" },
			{ "creat_date", "Creat Date" },
			{ "last_modify_date", "Last modify Date" },
			{ "last_modify_oper", "Last modify oper" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * FormComfiguration List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downFormComfigurationExcel", method = RequestMethod.POST)
	public void downFormComfigurationExcel(FormComfigurationDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = formComfigurationService.downFormComfigurationExcel(dto);
		String fileName = "FormComfigurationExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "display_name", "Display Name" },
			{ "eorm_name", "Eorm Name" },
			{ "editable", "Editable" },
			{ "table_name", "Table Name" },
			{ "colunm_name", "Colunm Name" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * Jobs List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downJobsExcel", method = RequestMethod.POST)
	public void downJobsExcel(JobsDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = jobsService.downJobsExcel(dto);
		String fileName = "JobsExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code", "Code" },
			{ "name", "Name" },
			{ "precess", "Precess" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * Logs List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downLogsExcel", method = RequestMethod.POST)
	public void downLogsExcel(LogsDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = logsService.downLogsExcel(dto);
		String fileName = "LogsExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code_name", "Code Name" },
			{ "name", "Name" },
			{ "precess", "Precess" },
			{ "enbled", "Enbled" },
			{ "modiffied", "Modiffied" },
			{ "created_by", "Created by" },
			{ "created", "Created" },
			{ "midiffied_by", "Midiffied By" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * RelationshipTypes List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downRelationshipTypesExcel", method = RequestMethod.POST)
	public void downRelationshipTypesExcel(RelationshipTypesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = relationshipTypesService.downRelationshipTypesExcel(dto);
		String fileName = "RelationshipTypesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code_name", "Code Name" },
			{ "created_by", "Created by" },
			{ "created_date", "Created Date" },
			{ "last_modify_date", "Last modify Date" },
			{ "last_modify_oper", "Last modify oper" } 
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * Sequences List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downSequencesExcel", method = RequestMethod.POST)
	public void downSequencesExcel(SequencesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = sequencesService.downSequencesExcel(dto);
		String fileName = "SequencesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "object_type", "Object Type" },
			{ "width", "Width" },
			{ "conla_chart", "Conla Chart" },
			{ "create_pression", "Create Pression" },
			{ "sequernce", "Sequernce" },
			{ "value", "Value" },
			{ "create_peer", "Create Peer" },
			{ "create_date", "Create Date" },
			{ "last_modify", "Last Modify" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * SystemParameters List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downSystemParametersExcel", method = RequestMethod.POST)
	public void downSystemParametersExcel(SystemParametersDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = systemParametersService.downSystemParametersExcel(dto);
		String fileName = "SystemParametersExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "code", "Code" },
			{ "name", "Name" },
			{ "account_bussiness_date", "Account Bussiness Date" },
			{ "last_end_of_day", "Last End of Day" },
			{ "date", "Date" },
			{ "profile_review_frequary", "Profile Review Frequary" },
			{ "server", "Server" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * TemplateRules List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downTemplateRulesExcel", method = RequestMethod.POST)
	public void downTemplateRulesExcel(TemplateRulesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = templateRulesService.downTemplateRulesExcel(dto);
		String fileName = "TemplateRulesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "tride", "Tride" },
			{ "type", "Type" },
			{ "sp_name", "SP Name" },
			{ "susp_type_schedule", "Susp Type-Schedule" },
			{ "pre_post_edd", "Pre/Post EDD" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
	
	/**
	 * UserDefindRules List Download
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/downUserDefindRulesExcel", method = RequestMethod.POST)
	public void downUserDefindRulesExcel(UserDefindRulesDto dto,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		logger.debug("request url: {}", request.getRequestURL());
		List<Map<String, Object>> exportData = userDefindRulesService.downUserDefindRulesExcel(dto);
		String fileName = "UserDefindRulesExcel";
		List<Map<String, String>> titleList = FileOptHelper.createDisplayColumn(new String[][] {
			{ "type", "Type" },
			{ "sp_name", "SP Name" },
			{ "sup_type", "Sup type" },
			{ "pre_post_doo", "Pre/Post doo" },
			{ "last_modify_date", "Last Modify Date" },
			{ "last_modify_oper", "Last Modify Oper" },
			{ "create_date", "Create Date" },
			{ "create_by", "Create By" },
			{ "last_status", "last status" }
			});
		new ReportExcelExporter(response, fileName, exportData, titleList,"CSV");
	}
}
