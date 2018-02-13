package com.aml.api.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.aml.api.dto.DicDTO;
import com.aml.api.pojo.Dic;
import com.aml.api.service.DictService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiPageResult;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;
import com.aml.common.core.exception.ServerException;
import com.aml.common.util.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: DictController
 * @description: 数据字典
 * @author huangliangbao
 * @date 2017年11月15日 下午1:17:21
 *
 */
@RestController
public class DictController extends BaseController {
	@Autowired
	private DictService dictService;

	/**
	 * 
	 * @title: queryDicts
	 * @description: 查询所有数据字典
	 *
	 * @param request
	 * @return ApiResult
	 * @date 2017年11月15日 下午1:20:43
	 */
	@RequestMapping(value = "/dicts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiPageResult<Dic> queryDicts ( HttpServletRequest request, @RequestParam HashMap<String, String> params,
			@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize ) {
		logger.info("request url={}, params={}", request.getRequestURL(), JSON.toJSONString(params));
		ApiPageResult<Dic> result = new ApiPageResult<>(ErrorTypeEnum.OK);
		PageHelper.startPage(pageNum, pageSize);
		Page<Dic> data = dictService.queryDicts(params);
		PageUtils.toApiPageResult(result, data);
		return result;
	}

	/**
	 * 
	 * @title: findDicById
	 * @description: 查询单个资源
	 *
	 * @param request
	 * @param dicId
	 * @return ApiResult
	 * @date 2017年11月15日 下午6:31:28
	 */
	@RequestMapping(value = "/dict/{dicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Dic> findDicById ( HttpServletRequest request, @PathVariable("dicId") Long dicId ) {
		logger.info("request url：{}, dicId：{}", request.getRequestURL(), dicId);
		ApiResult<Dic> result = new ApiResult<>(ErrorTypeEnum.OK);
		Dic data = dictService.findDicById(dicId);
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: update
	 * @description: 修改单个资源
	 *
	 * @param request
	 * @param dicId
	 * @return ApiResult
	 * @date 2017年11月15日 下午6:31:28
	 */
	@RequestMapping(value = "/dict/{dicId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> update ( HttpServletRequest request, @PathVariable("dicId") Long dicId,
			@RequestBody DicDTO dto ) {
		logger.info("request url：{},  dto：{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result serviceResult = dictService.update(dto);
		result.setResult(serviceResult);
		return result;
	}

	/**
	 * 
	 * @title: delete
	 * @description: 删除单个资源
	 *
	 * @param request
	 * @param dicId
	 * @return ApiResult
	 * @date 2017年11月15日 下午6:31:28
	 */
	@RequestMapping(value = "/dict/{dicId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> delete ( HttpServletRequest request, @PathVariable("dicId") Long dicId ) {
		logger.info("request url：{},  dicId：{}", request.getRequestURL(), dicId);
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result serviceResult = dictService.delete(dicId);
		result.setResult(serviceResult);
		return result;
	}
	
	/**
	 * 
	 * @title: deleteDicSub
	 * @description: 删除字典下的某个字典项
	 *
	 * @param request
	 * @param dicSid
	 * @return ApiResult
	 * @date 2017年11月15日 下午6:31:28
	 */
	@RequestMapping(value = "/dict/dicSub/{dicSid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> deleteDicSub ( HttpServletRequest request, @PathVariable("dicSid") Long dicSid ) {
		logger.info("request url：{},  dicSid：{}", request.getRequestURL(), dicSid);
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result serviceResult = dictService.deleteDicSub(dicSid);
		result.setResult(serviceResult);
		return result;
	}

	/**
	 * 
	 * @title: add
	 * @description: 新增单个资源
	 *
	 * @param dto
	 * @param request
	 * @return ApiResult
	 * @throws ServerException
	 * @date 2017年11月15日 下午2:24:01
	 */
	@RequestMapping(value = "/dict", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResult<Void> add ( HttpServletRequest request, @RequestBody DicDTO dto ) throws ServerException {
		logger.debug("request url：{}, dto：{}", request.getRequestURL(), dto.toJson());
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (StringUtils.isBlank(dto.getDicCode()) || StringUtils.isBlank(dto.getDicName())) {
			result.setErrorType(ErrorTypeEnum.ERROR_VALIDATE);
			return result;
		}
		Result serviceResult = dictService.insert(dto);
		result.setResult(serviceResult);
		return result;
	}

}
