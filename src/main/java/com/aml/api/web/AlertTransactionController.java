package com.aml.api.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aml.api.dto.AlertTransactionDTO;
import com.aml.api.dto.transaction.TransactionDetailsDto;
import com.aml.api.service.AlertService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.util.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: AlertTransactionController
 * @description:
 * @author shun
 * @date 2017年12月2日
 *
 */
@RestController
public class AlertTransactionController extends BaseController {

	@Autowired
	private AlertService alertService;

	/**
	 * 查询前5条交易记录
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/transaction/{originalId}/top5", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryTransactionTop5 ( @PathVariable Integer originalId,
			HttpServletRequest request ) {
		logger.info("request url：{} originalId:{}", request.getRequestURL(), originalId);
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<Map<String, Object>> data = alertService.queryTransactionTop5(originalId);
		result.setData(data);
		return result;
	}

	/**
	 * 查询alert交易记录
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/transaction/{originalId}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Map<String, Object>> queryAlertTransaction ( @PathVariable Integer originalId, @PathVariable Integer type,
			AlertTransactionDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} originalId：{}, dto：{}",
				new Object[] { request.getRequestURL(), originalId, dto.toJson() });
		ApiPageResult<Map<String, Object>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		Page<Map<String, Object>> data = alertService.queryAlertTransaction(originalId,type);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 查询前5条的交易人
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/transaction/{originalId}/name/top5", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryTransactionNameTop5 ( @PathVariable Integer originalId,
			Integer type, HttpServletRequest request ) {
		logger.info("request url：{} originalId：{}, type：{}",
				new Object[] { request.getRequestURL(), originalId, type });
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(type)) {
			type = 0;
		}
		List<Map<String, Object>> data = alertService.queryTransactionNameTop5(originalId, type);
		result.setData(data);
		return result;
	}

	/**
	 * 查询前5条的地区
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/transaction/{originalId}/country/top5", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryTransactionCountryTop5 ( @PathVariable Integer originalId,
			Integer type, HttpServletRequest request ) {
		logger.info("request url：{} originalId：{}, type：{}",
				new Object[] { request.getRequestURL(), originalId, type });
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(type)) {
			type = 0;
		}
		List<Map<String, Object>> data = alertService.queryTransactionCountryTop5(originalId, type);
		result.setData(data);
		return result;
	}

	/**
	 * 图表
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/alert/transaction/{originalId}/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<List<Map<String, Object>>> queryTransactionMap ( @PathVariable Integer originalId, 
			@DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			HttpServletRequest request ) {
		logger.info("request url：{} originalId：{}, type：{} ,startDate:{} ,endDate:{}",request.getRequestURL(),originalId,startDate,endDate);
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<Map<String, Object>> volume = alertService.queryTransactionVolumeMap(originalId,startDate,endDate);
//		List<Map<String, Object>> amounts = alertService.queryTransactionAmountMap(originalId);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("volume", volume);
//		map.put("amounts", amounts);
		result.setData(volume);
		return result;
	}

	/**
	 * 查询历史交易记录
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/transaction/{originalId}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Map<String, Object>> queryTransaction ( @PathVariable Long originalId, @PathVariable Integer type,
			AlertTransactionDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} originalId：{}, dto：{}",
				new Object[] { request.getRequestURL(), originalId, dto.toJson() });
		dto.setOriginalId(originalId);
		dto.setType(type);
		ApiPageResult<Map<String, Object>> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		Page<Map<String, Object>> data = alertService.queryTransaction(dto);
		PageUtils.toApiPageResult(result, data);
		return result;
	}
	
	/**
	 * 查询历史交易记录详情
	 * 
	 * @param request
	 * @return
	 * @date 2017年11月24日
	 */
	@RequestMapping(value = "/transaction/{id}/{type}/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<TransactionDetailsDto> queryTransactionDetails ( @PathVariable Long id, @PathVariable Integer type,
			AlertTransactionDTO dto, HttpServletRequest request ) {
		logger.info("request url：{} originalId：{}, dto：{}",
				new Object[] { request.getRequestURL(), id, dto.toJson() });
		dto.setId(id);
		dto.setType(type);
		ApiResult<TransactionDetailsDto> result = new ApiResult<>(ErrorTypeEnum.OK);
		TransactionDetailsDto data = alertService.queryTransactionDetails(dto);
		result.setData(data);
		return result;
	}
}
