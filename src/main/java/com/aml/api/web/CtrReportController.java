package com.aml.api.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.CtrFinancialInstitutionDto;
import com.aml.api.dto.CtrPersonInfoDto;
import com.aml.api.dto.CtrReportDto;
import com.aml.api.dto.CtrTransAmountTypeDto;
import com.aml.api.pojo.CtrFinancialInstitution;
import com.aml.api.pojo.CtrPersonInfo;
import com.aml.api.pojo.CtrReport;
import com.aml.api.pojo.CtrTransAmountType;
import com.aml.api.service.CtrReportService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;

/**
 * Currency Transaction Report
 * @author Novan
 * @date 2017-12-21
 *
 */
@RestController
public class CtrReportController extends BaseController {
	
	@Autowired
	private CtrReportService ctrReportService;
	
	
	/**
	 * Save Home  Currency Transaction Report
	 * @param request
	 * @return
	 * @date 2017-12-21
	 */
	@RequestMapping(value = "/ctrReport/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<CtrReport> addCtrReport (@RequestBody CtrReportDto dto, HttpServletRequest request ) {
		ApiResult<CtrReport> result = new ApiResult<>(ErrorTypeEnum.OK);
		CtrReport report = new CtrReport();
		if(ObjectUtils.isEmpty(dto.getCid())){
			//新增
			report = ctrReportService.addCtrReport(dto); 
		}else{
			//修改
			int flag = ctrReportService.updateCtrReport(dto);
			if(flag == 0){
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Save failure!");
				return result;
			}
		}
		result.setData(report);
		return result;
	}
	
	/**
	 * query Home Currency Transaction Report
	 * @param request
	 * @return
	 * @date 2017-12-21
	 */
	@RequestMapping(value = "/ctrReport/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<CtrReport> queryCtrReport (CtrReportDto dto, HttpServletRequest request ) {
		ApiResult<CtrReport> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCtrId())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("CtrId does not exist!");
			return result;
		}
		CtrReport report = ctrReportService.queryCtrReport(dto);
		result.setData(report);
		return result;
	}
	
	/**
	 * Save Person
	 * @param request
	 * @return
	 * @date 2017-12-21
	 */
	@RequestMapping(value = "/person/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> addPerson (@RequestBody CtrPersonInfoDto dto, HttpServletRequest request ) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCid())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Cid does not exist!");
			return result;
		}
		if(ObjectUtils.isEmpty(dto.getPersonId())){
			//新增
			result = ctrReportService.addCtrPersonInfo(dto);
		}else{
			//修改
			int flag = ctrReportService.updateCtrPersonInfo(dto);
			if(flag == 0){
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Save failure!");
				return result;
			}
		}
		return result;
	}
	
	/**
	 * query Person
	 * @param request
	 * @return
	 * @date 2017-12-21
	 */
	@RequestMapping(value = "/person/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<CtrPersonInfo> queryPerson (CtrPersonInfoDto dto, HttpServletRequest request ) {
		ApiResult<CtrPersonInfo> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCid())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Cid does not exist!");
			return result;
		}
		CtrPersonInfo ctrPersonInfo = ctrReportService.queryCtrPersonInfo(dto);
		result.setData(ctrPersonInfo);
		return result;
	}
	
	/**
	 * Save Trans Amount Type
	 * @param request
	 * @return
	 * @date 2017-12-21
	 */
	@RequestMapping(value = "/trans/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> addTrans (@RequestBody CtrTransAmountTypeDto dto, HttpServletRequest request ) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCid())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Cid does not exist!");
			return result;
		}
		if(ObjectUtils.isEmpty(dto.getTransId())){
			//新增
			result = ctrReportService.addCtrTransAmountType(dto);
		}else{
			//修改
			int flag = ctrReportService.updateCtrTransAmountType(dto);
			if(flag == 0){
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Save failure!");
				return result;
			}
		}
		return result;
	}
	
	/**
	 * query CtrTransAmountType
	 * @param request
	 * @return
	 * @date 2017-12-22
	 */
	@RequestMapping(value = "/trans/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<CtrTransAmountType> queryTrans (CtrTransAmountTypeDto dto, HttpServletRequest request ) {
		ApiResult<CtrTransAmountType> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCid())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Cid does not exist!");
			return result;
		}
		CtrTransAmountType ctrTransAmountType = ctrReportService.queryCtrTransAmountType(dto);
		result.setData(ctrTransAmountType);
		return result;
	}
	
	/**
	 * Save Ctr Financial Institution 
	 * @param request
	 * @return
	 * @date 2017-12-21
	 */
	@RequestMapping(value = "/ctrFinancial/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> addCtrFinancial (@RequestBody CtrFinancialInstitutionDto dto, HttpServletRequest request ) throws Exception {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCid())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Cid does not exist!");
			return result;
		}
		if(ObjectUtils.isEmpty(dto.getFinancialId())){
			//新增
			result = ctrReportService.addCtrFinancialInstitution(dto);
		}else{
			//修改
			int flag = ctrReportService.updateCtrFinancialInstitution(dto);
			if(flag == 0){
				result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
				result.setErrorInfo("Save failure!");
				return result;
			}
		}
		return result;
	}
	
	/**
	 * query CtrTransAmountType
	 * @param request
	 * @return
	 * @date 2017-12-22
	 */
	@RequestMapping(value = "/ctrFinancial/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiResult<CtrFinancialInstitution> queryCtrFinancial (CtrFinancialInstitutionDto dto, HttpServletRequest request ) {
		ApiResult<CtrFinancialInstitution> result = new ApiResult<>(ErrorTypeEnum.OK);
		if(ObjectUtils.isEmpty(dto.getCid())){
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			result.setErrorInfo("Cid does not exist!");
			return result;
		}
		CtrFinancialInstitution ctrFinancialInstitution = ctrReportService.queryCtrFinancialInstitution(dto);
		result.setData(ctrFinancialInstitution);
		return result;
	}
}
