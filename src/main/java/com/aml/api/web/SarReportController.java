package com.aml.api.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.SarFilingInstitutionDto;
import com.aml.api.dto.SarFinancialInstitutionDto;
import com.aml.api.dto.SarNarrativeDto;
import com.aml.api.dto.SarReportDto;
import com.aml.api.dto.SarSubjectInfoDto;
import com.aml.api.dto.SarSuspiciousActivityDto;
import com.aml.api.pojo.SarFilingInstitution;
import com.aml.api.pojo.SarFinancialInstitution;
import com.aml.api.pojo.SarNarrative;
import com.aml.api.pojo.SarReport;
import com.aml.api.pojo.SarSubjectInfo;
import com.aml.api.pojo.SarSuspiciousActivity;
import com.aml.api.service.SarFilingInstitutionService;
import com.aml.api.service.SarFinancialInstitutionService;
import com.aml.api.service.SarNarrativeService;
import com.aml.api.service.SarReportService;
import com.aml.api.service.SarSubjectInfoService;
import com.aml.api.service.SarSuspiciousActivityService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * Save the SAR report
 * 
 * @author Novan
 * @date 2017-12-5
 *
 */
@RestController
public class SarReportController extends BaseController {
	@Autowired
	private SarReportService sarReportService;
	@Autowired
	private SarFilingInstitutionService sarFilingInstitutionService;
	@Autowired
	private SarFinancialInstitutionService sarFinancialInstitutionService;
	@Autowired
	private SarSubjectInfoService sarSubjectInfoService;
	@Autowired
	private SarSuspiciousActivityService sarSuspiciousActivityService;
	@Autowired
	private SarNarrativeService sarNarrativeService;

	/**
	 * 
	 * @title: addReport
	 * @description: Save/Update Home
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult<Long>
	 * @date 2018年1月5日 下午2:58:25
	 */
	@RequestMapping(value = "/sarReport/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> addReport ( @RequestBody SarReportDto dto, HttpServletRequest request ) {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result serviceResult = sarReportService.saveOrUpdate(dto);

		result.setResult(serviceResult);
		result.setData(dto.getRid());
		return result;
	}

	/**
	 * 
	 * @title: queryReport
	 * @description: query Home
	 *
	 * @param caseId
	 * @param request
	 * @return ApiResult<SarReport>
	 * @date 2018年1月5日 下午3:03:31
	 */
	@RequestMapping(value = "/sarReport/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<SarReport> queryReport ( @RequestParam Long caseId, HttpServletRequest request ) {
		ApiResult<SarReport> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(caseId)) {
			result.setErrorInfo("Case Id cannot be empty");
			return result;
		}
		result.setData(sarReportService.query(caseId));
		return result;
	}

	/**
	 * 
	 * @title: addFiling
	 * @description: Save Step 1.Filing Institution Contact Information
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult<Long>
	 * @throws Exception
	 * @date 2018年1月5日 下午3:44:19
	 */
	@RequestMapping(value = "/filing/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> addFiling ( @RequestBody SarFilingInstitutionDto dto, HttpServletRequest request )
			throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getRid())) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		Result serviceResult = sarFilingInstitutionService.saveOrUpdate(dto);

		result.setResult(serviceResult);
		result.setData(dto.getFilingId());
		return result;
	}

	/**
	 * 
	 * @title: queryFiling
	 * @description: query Step 1.Filing Institution Contact Information
	 *
	 * @param rid
	 * @param request
	 * @return ApiResult<SarFilingInstitution>
	 * @date 2018年1月5日 下午3:45:10
	 */
	@RequestMapping(value = "/filing/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<SarFilingInstitution> queryFiling ( @RequestParam Long rid, HttpServletRequest request ) {
		ApiResult<SarFilingInstitution> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(rid)) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		result.setData(sarFilingInstitutionService.query(rid));
		return result;
	}

	/**
	 * 
	 * @title: addFinancial
	 * @description: Save Step 2.Financial Institution Where Activity Occurred
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult<Long>
	 * @throws Exception
	 * @date 2018年1月5日 下午3:54:42
	 */
	@RequestMapping(value = "/financial/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> addFinancial ( @RequestBody SarFinancialInstitutionDto dto, HttpServletRequest request )
			throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getRid())) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		// 新增
		if (ObjectUtils.isEmpty(dto.getFinancialId())) {
			result = sarFinancialInstitutionService.save(dto);
		} else {// 修改
			result = sarFinancialInstitutionService.update(dto);
		}
		result.setData(dto.getFinancialId());
		return result;
	}

	/**
	 * 
	 * @title: queryFinancial
	 * @description: query Step 2.Financial Institution Where Activity Occurred
	 *
	 * @param rid
	 * @param request
	 * @return ApiResult<SarFinancialInstitution>
	 * @throws Exception
	 * @date 2018年1月5日 下午4:32:29
	 */
	@RequestMapping(value = "/financial/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<SarFinancialInstitution> queryFinancial ( @RequestParam Long rid, HttpServletRequest request )
			throws Exception {
		ApiResult<SarFinancialInstitution> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(rid)) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		result.setData(sarFinancialInstitutionService.query(rid));
		return result;
	}

	/**
	 * 
	 * @title: addSubject
	 * @description: Save Step 3.Subject Information
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult<Long>
	 * @throws Exception
	 * @date 2018年1月5日 下午4:51:50
	 */
	@RequestMapping(value = "/subject/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> addSubject ( @RequestBody SarSubjectInfoDto dto, HttpServletRequest request )
			throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getRid())) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		// 新增
		if (ObjectUtils.isEmpty(dto.getSubId())) {
			result = sarSubjectInfoService.save(dto);
		} else {// 修改
			result = sarSubjectInfoService.update(dto);
		}
		result.setData(dto.getSubId());
		return result;
	}

	/**
	 * 
	 * @title: querySubject
	 * @description: query Step 3.Subject Information
	 *
	 * @param rid
	 * @param request
	 * @return ApiResult<SarSubjectInfo>
	 * @throws Exception
	 * @date 2018年1月5日 下午4:51:36
	 */
	@RequestMapping(value = "/subject/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<SarSubjectInfo> querySubject ( @RequestParam Long rid, HttpServletRequest request )
			throws Exception {
		ApiResult<SarSubjectInfo> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(rid)) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		result.setData(sarSubjectInfoService.query(rid));
		return result;
	}

	/**
	 * 
	 * @title: addSuspicious
	 * @description: Save Step 4.Suspicious Activity Information
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult<Long>
	 * @throws Exception
	 * @date 2018年1月5日 下午5:07:19
	 */
	@RequestMapping(value = "/suspicious/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> addSuspicious ( @RequestBody SarSuspiciousActivityDto dto, HttpServletRequest request )
			throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getRid())) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		Result serviceResult = sarSuspiciousActivityService.saveOrUpdate(dto);

		result.setResult(serviceResult);
		result.setData(dto.getRid());
		return result;
	}

	/**
	 * 
	 * @title: querySuspicious
	 * @description: query Step 4.Suspicious Activity Information
	 *
	 * @param rid
	 * @param request
	 * @return ApiResult<SarSuspiciousActivity>
	 * @throws Exception
	 * @date 2018年1月5日 下午5:24:26
	 */
	@RequestMapping(value = "/suspicious/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<SarSuspiciousActivity> querySuspicious ( Long rid, HttpServletRequest request ) throws Exception {
		ApiResult<SarSuspiciousActivity> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(rid)) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		result.setData(sarSuspiciousActivityService.query(rid));
		return result;
	}

	/**
	 * 
	 * @title: addNarrative
	 * @description: Save Step 5.Narrative
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult<Long>
	 * @throws Exception
	 * @date 2018年1月5日 下午5:25:25
	 */
	@RequestMapping(value = "/narrative/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Long> addNarrative ( @RequestBody SarNarrativeDto dto, HttpServletRequest request )
			throws Exception {
		ApiResult<Long> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getRid())) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		Result serviceResult = sarNarrativeService.saveOrUpdate(dto);

		result.setResult(serviceResult);
		result.setData(dto.getRid());
		return result;
	}

	/**
	 * 
	 * @title: queryNarrative
	 * @description: query Step 5.Narrative
	 *
	 * @param rid
	 * @param request
	 * @return ApiResult<SarNarrative>
	 * @throws Exception
	 * @date 2018年1月5日 下午5:26:30
	 */
	@RequestMapping(value = "/narrative/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<SarNarrative> queryNarrative ( Long rid, HttpServletRequest request ) throws Exception {
		ApiResult<SarNarrative> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(rid)) {
			result.setErrorInfo("Rid cannot be empty");
			return result;
		}
		result.setData(sarNarrativeService.query(rid));
		return result;
	}
}
