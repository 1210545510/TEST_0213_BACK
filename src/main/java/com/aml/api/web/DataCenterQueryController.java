package com.aml.api.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.AlertInfoDto;
import com.aml.api.dto.CaseInfoDto;
import com.aml.api.dto.ClientBankDto;
import com.aml.api.dto.CustDto;
import com.aml.api.dto.Data314ADto;
import com.aml.api.dto.ExtUserRequestDto;
import com.aml.api.dto.GrandJurySubpoenaDto;
import com.aml.api.pojo.AlertGroup;
import com.aml.api.pojo.ClientBank;
import com.aml.api.pojo.Data314A;
import com.aml.api.pojo.ExtUserRequest;
import com.aml.api.pojo.GrandJurySubpoena;
import com.aml.api.pojo.Rfi;
import com.aml.api.service.AlertService;
import com.aml.api.service.CaseService;
import com.aml.api.service.ClientBankService;
import com.aml.api.service.CustService;
import com.aml.api.service.Data314AService;
import com.aml.api.service.DataCenterService;
import com.aml.api.service.ExtUserRequestService;
import com.aml.api.service.GrandJurySubpoenaService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.util.PageUtils;
import com.github.pagehelper.Page;

/**
 * Data center query
 * 
 * @author Novan
 * @date 2017-11-27
 *
 */
@RestController
public class DataCenterQueryController extends BaseController {
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
	private DataCenterService dataCenterService;

	/**
	 * @title: queryClientBank
	 * @description: Query ClientBank
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/clientBankList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<ClientBank> clientBankList(ClientBankDto dto, HttpServletRequest request) {
		ApiPageResult<ClientBank> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<ClientBank> data = clientBankService.getClientBankList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * @title: queryExtUserRequest
	 * @description: Query ExtUserRequest
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/extUserRequestList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<ExtUserRequest> extUserRequestList(ExtUserRequestDto dto, HttpServletRequest request) {
		ApiPageResult<ExtUserRequest> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<ExtUserRequest> data = extUserRequestService.getExtUserRequestList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * @title: queryCust
	 * @description: Query Cust
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/custList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CustDto> custList(CustDto dto, HttpServletRequest request) {
		ApiPageResult<CustDto> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<CustDto> data = custService.getCustList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * @title: queryGrandJurySubpoena
	 * @description: Query GrandJurySubpoena
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/grandJurySubpoenaList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<GrandJurySubpoena> grandJurySubpoenaList(GrandJurySubpoenaDto dto,
			HttpServletRequest request) {
		ApiPageResult<GrandJurySubpoena> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<GrandJurySubpoena> data = grandJurySubpoenaService.getGrandJurySubpoenaList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * @title: queryData314A
	 * @description: Query Data314A
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/data314AList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Data314A> data314AList(Data314ADto dto, HttpServletRequest request) {
		ApiPageResult<Data314A> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Data314A> data = data314AService.getData314AList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: RFI
	 * @description: Query Data314A
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/rfi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<Rfi> rfi(Data314ADto dto, HttpServletRequest request) {
		ApiPageResult<Rfi> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		Page<Rfi> data = dataCenterService.queryRfiList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: queryAlertInfo
	 * @description: Query AlertInfo
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/alertInfoList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<AlertGroup> alertInfoList(AlertInfoDto dto, HttpServletRequest request) {
		ApiPageResult<AlertGroup> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		String orderby = dto.getOrderby();
		switch (orderby) {
			case "1":	dto.setOrderby("a.score");break;
			case "2":	dto.setOrderby("a.score desc");break;
			case "3":	dto.setOrderby("a.amount");break;
			case "4":	dto.setOrderby("a.amount desc");break;
			case "5":	dto.setOrderby("a.volume");break;
			case "6":	dto.setOrderby("a.volume desc");break;
			default: 	dto.setOrderby("a.alert_id desc");break;
		}
		Page<AlertGroup> data = alertService.getAlertInfoList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * @title: queryCaseInfo
	 * @description: Query CaseInfo
	 * @param request
	 * @return ApiPageResult
	 * @date 2017-11-22
	 */
	@RequestMapping(value = "/caseInfoList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ApiPageResult<CaseInfoDto> caseInfoList(CaseInfoDto dto, HttpServletRequest request) {
		ApiPageResult<CaseInfoDto> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		String orderby = dto.getOrderby();
		switch (orderby) {
			case "1":	dto.setOrderby("a.score");break;
			case "2":	dto.setOrderby("a.score desc");break;
			case "3":	dto.setOrderby("a.amount");break;
			case "4":	dto.setOrderby("a.amount desc");break;
			case "5":	dto.setOrderby("a.volume");break;
			case "6":	dto.setOrderby("a.volume desc");break;
			default: 	dto.setOrderby("a.alert_id desc");break;
		}
		Page<CaseInfoDto> data = caseService.getCaseInfoList(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
}
