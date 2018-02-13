/*
 * Copyright 2012-2013 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aml.api.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Surrogate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.aml.api.service.SnakerEngineFacets;
import com.aml.api.service.TaskApprovalService;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * 
 * @className: SurrogateController
 * @description: 委托授权
 * @author huangliangbao
 * @date 2017年12月7日 下午12:26:56
 *
 */
@RestController
@RequestMapping(value = "/snaker/surrogate")
public class SurrogateController extends BaseController {
	@Autowired
	private SnakerEngineFacets facets;
	@Autowired
	TaskApprovalService taskApprovalService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list ( HttpServletRequest request, Page<Surrogate> page, String processName ) {
		logger.info("request url={}, page={}", request.getRequestURL(), JSON.toJSONString(page));
		QueryFilter filter = new QueryFilter();
		if (StringUtils.isNotBlank(processName)) {
			filter.setNames(new String[] { processName });
		}
		facets.searchSurrogate(page, filter);

		return JSON.toJSONString(page);
	}

	@RequestMapping(value = "getAllProcessNames", method = RequestMethod.GET)
	public ApiResult<List<String>> create ( HttpServletRequest request ) {
		logger.info("request url={}", request.getRequestURL());
		ApiResult<List<String>> result = new ApiResult<>(ErrorTypeEnum.OK);
		List<String> data = facets.getAllProcessNames();
		result.setData(data);

		return result;
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ApiResult<Map<String, Object>> edit ( HttpServletRequest request, @PathVariable("id") String id ) {
		logger.info("request url={}, id={}", request.getRequestURL(), id);
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> data = new HashMap<String, Object>();
		Surrogate surrogate = facets.getSurrogate(id);
		List<String> processNames = facets.getAllProcessNames();
		data.put("surrogate", surrogate);
		data.put("processNames", processNames);
		result.setData(data);

		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ApiResult<Void> update ( HttpServletRequest request, @RequestBody Surrogate entity ) {
		logger.info("request url={}, surrogate={}", request.getRequestURL(), JSON.toJSONString(entity));
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		entity.setSdate(entity.getSdate() + " 00:00:00");
		entity.setEdate(entity.getEdate() + " 23:59:59");

		if (entity.getState() == null) {
			entity.setState(1);
		}
		Result serviceResult = taskApprovalService.saveOrUpdate(entity);
		result.setResult(serviceResult);

		return result;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.PUT)
	public ApiResult<Void> delete ( HttpServletRequest request, @PathVariable("id") String id ) {
		logger.info("request url={}, id={}", request.getRequestURL(), id);
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		facets.deleteSurrogate(id);

		return result;
	}
}
