package com.aml.api.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.AccountTypesDto;
import com.aml.api.dto.ActivityMapsDto;
import com.aml.api.dto.ActivityTypesDto;
import com.aml.api.dto.CloseReasonDto;
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
import com.aml.api.pojo.AccountTypes;
import com.aml.api.pojo.ActivityMaps;
import com.aml.api.pojo.ActivityTypes;
import com.aml.api.pojo.CloseReason;
import com.aml.api.pojo.Countries;
import com.aml.api.pojo.CustomerTypes;
import com.aml.api.pojo.Exemptions;
import com.aml.api.pojo.FormComfiguration;
import com.aml.api.pojo.Jobs;
import com.aml.api.pojo.Logs;
import com.aml.api.pojo.RelationshipTypes;
import com.aml.api.pojo.Sequences;
import com.aml.api.pojo.SystemParameters;
import com.aml.api.pojo.TemplateRules;
import com.aml.api.pojo.UserDefindRules;
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
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.util.PageUtils;
import com.github.pagehelper.Page;


/**
 * Query list data in all settings
 * @author Novan
 * @date 2017-11-22
 */
@RestController
public class QueryConfigurationController extends BaseController {
	
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
	 * @title: queryAccountTypes
	 * @description: Query AccountTypes
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/accountTypesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AccountTypes> accountTypesList(AccountTypesDto dto, HttpServletRequest request) {
		ApiPageResult<AccountTypes> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<AccountTypes> data = accountTypesService.getAccountTypesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: ActivityMaps
	 * @description: Query ActivityMaps
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/activityMapsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<ActivityMaps> activityMapsList(ActivityMapsDto dto, HttpServletRequest request) {
		ApiPageResult<ActivityMaps> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<ActivityMaps> data = activityMapsService.getActivityMapsList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: ActivityTypes
	 * @description: Query ActivityTypes
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/activityTypesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<ActivityTypes> activityTypesList(ActivityTypesDto dto, HttpServletRequest request) {
		ApiPageResult<ActivityTypes> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<ActivityTypes> data = activityTypesService.getActivityTypesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: Countries
	 * @description: Query Countries
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/countriesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Countries> countriesList(ActivityTypesDto dto, HttpServletRequest request) {
		ApiPageResult<Countries> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Countries> data = countriesService.getCountriesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: CloseReason
	 * @description: Query CloseReason
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/closeReasonList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CloseReason> closeReasonList(CloseReasonDto dto, HttpServletRequest request) {
		ApiPageResult<CloseReason> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CloseReason> data = closeReasonService.getCloseReasonList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: CustomerTypes
	 * @description: Query CustomerTypes
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/customerTypesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CustomerTypes> customerTypesList(CustomerTypesDto dto, HttpServletRequest request) {
		ApiPageResult<CustomerTypes> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CustomerTypes> data = customerTypesService.getCustomerTypesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: Exemptions
	 * @description: Query Exemptions
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/exemptionsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Exemptions> exemptionsList(ExemptionsDto dto, HttpServletRequest request) {
		ApiPageResult<Exemptions> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Exemptions> data = exemptionsService.getExemptionsList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: FormComfiguration
	 * @description: Query FormComfiguration
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/formComfigurationList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<FormComfiguration> formComfigurationList(FormComfigurationDto dto, HttpServletRequest request) {
		ApiPageResult<FormComfiguration> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<FormComfiguration> data = formComfigurationService.getFormComfigurationList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: Jobs
	 * @description: Query Jobs
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/jobsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Jobs> formComfigurationList(JobsDto dto, HttpServletRequest request) {
		ApiPageResult<Jobs> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Jobs> data = jobsService.getJobsList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: Logs
	 * @description: Query Logs
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/logsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Logs> logsList(LogsDto dto, HttpServletRequest request) {
		ApiPageResult<Logs> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Logs> data = logsService.getLogsList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: RelationshipTypes
	 * @description: Query RelationshipTypes
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/relationshipTypesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<RelationshipTypes> relationshipTypesList(RelationshipTypesDto dto, HttpServletRequest request) {
		ApiPageResult<RelationshipTypes> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<RelationshipTypes> data = relationshipTypesService.getRelationshipTypesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: TemplateRules
	 * @description: Query TemplateRules
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/templateRulesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<TemplateRules> templateRulesList(TemplateRulesDto dto, HttpServletRequest request) {
		ApiPageResult<TemplateRules> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<TemplateRules> data = templateRulesService.getTemplateRulesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: UserDefindRules
	 * @description: Query UserDefindRules
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/userDefindRulesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<UserDefindRules> userDefindRulesList(UserDefindRulesDto dto, HttpServletRequest request) {
		ApiPageResult<UserDefindRules> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<UserDefindRules> data = userDefindRulesService.getUserDefindRulesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: Sequences
	 * @description: Query Sequences
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/sequencesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Sequences> sequencesList(SequencesDto dto, HttpServletRequest request) {
		ApiPageResult<Sequences> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Sequences> data = sequencesService.getSequencesList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: SystemParameters
	 * @description: Query SystemParameters
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/systemParametersList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<SystemParameters> systemParametersList(SystemParametersDto dto, HttpServletRequest request) {
		ApiPageResult<SystemParameters> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<SystemParameters> data = systemParametersService.getSystemParametersList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
}
