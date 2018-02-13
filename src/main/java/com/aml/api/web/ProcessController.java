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

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.AssertHelper;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.aml.api.service.SnakerEngineFacets;
import com.aml.common.base.BaseController;
import com.aml.common.core.ApiResult;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;
import com.aml.common.snaker.SnakerHelper;

/**
 * 
 * @className: ProcessController
 * @description: 流程定义管理
 * @author huangliangbao
 * @date 2017年11月29日 下午2:11:37
 *
 */
@RestController
@RequestMapping(value = "/snaker/process")
public class ProcessController extends BaseController {
	@Autowired
	private SnakerEngineFacets facets;

	/**
	 * 
	 * @title: processList
	 * @description: 查询流程定义列表
	 *
	 * @param model
	 * @param page
	 * @param displayName 流程名称
	 * @return
	 * @date 2017年11月13日 下午3:02:19
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String processList ( HttpServletRequest request, Page<Process> page, String displayName ) {
		logger.info("request url={}, displayName={}", request.getRequestURL(), displayName);
		QueryFilter filter = new QueryFilter();
		if (StringHelper.isNotEmpty(displayName)) {
			filter.setDisplayName(displayName);
		}
		facets.getEngine().process().getProcesss(page, filter);
		return JSON.toJSONString(page);
	}

	/**
	 * 
	 * @title: processDesigner
	 * @description: 编辑流程定义[web流程设计器]
	 *
	 * @param request
	 * @param processId 流程定义ID
	 * @return
	 * @date 2017年11月13日 下午3:28:04
	 */
	@RequestMapping(value = "designer", method = RequestMethod.GET)
	public ApiResult<Map<String, String>> processDesigner ( HttpServletRequest request, String processId ) {
		logger.info("request url={}, processId={}", request.getRequestURL(), processId);
		ApiResult<Map<String, String>> result = new ApiResult<>(ErrorTypeEnum.OK);
		if (StringUtils.isNotEmpty(processId)) {
			Map<String, String> data = new HashMap<String, String>();
			Process process = facets.getEngine().process().getProcessById(processId);
			AssertHelper.notNull(process);
			ProcessModel processModel = process.getModel();
			if (!ObjectUtils.isEmpty(processModel)) {
				String json = SnakerHelper.getModelJson(processModel);
				data.put("processId", processId);
				data.put("process", json);
			}
			result.setData(data);
		}
		return result;
	}

	/**
	 * 
	 * @title: processEdit
	 * @description: 编辑流程定义文件
	 *
	 * @param model
	 * @param id 流程定义ID
	 * @return
	 * @date 2017年11月13日 下午3:31:53
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
	public ApiResult<Map<String, Object>> processEdit ( HttpServletRequest request, @PathVariable("id") String id ) {
		logger.info("request url={}, id={}", request.getRequestURL(), id);
		ApiResult<Map<String, Object>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Map<String, Object> data = new HashMap<String, Object>();
		Process process = facets.getEngine().process().getProcessById(id);
		data.put("process", process);
		if (!ObjectUtils.isEmpty(process.getDBContent())) {
			try {
				data.put("content", StringHelper.textXML(new String(process.getDBContent(), "UTF-8")));
			} catch (UnsupportedEncodingException e) {
				logger.error("编辑流程定义文件, 出现异常, id={}##{}", new Object[] { id, e });
			}
		}
		result.setData(data);
		return result;
	}

	/**
	 * 
	 * @title: processDelete
	 * @description: 根据流程定义ID，删除流程定义
	 *
	 * @param id
	 * @return
	 * @date 2017年11月13日 下午3:41:34
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public ApiResult<Void> processDelete ( @PathVariable("id") String id ) {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		facets.getEngine().process().undeploy(id);
		return result;
	}

	/**
	 * 
	 * @title: processDeploy
	 * @description: 保存流程定义[web流程设计器]
	 *
	 * @param model 流程内容
	 * @param id 流程定义ID
	 * @return
	 * @date 2017年11月13日 下午3:41:57
	 */
	@RequestMapping(value = "deployXml", method = RequestMethod.POST)
	public ApiResult<Void> processDeploy ( String model, String id ) {
		ApiResult<Void> result = new ApiResult<>(ErrorTypeEnum.OK);
		Result data = new Result(ErrorTypeEnum.OK);
		InputStream input = null;
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
					+ SnakerHelper.convertXml(model);
			logger.debug("model xml={}", xml);
			input = StreamHelper.getStreamFromString(xml);
			if (StringUtils.isNotEmpty(id)) {
				facets.getEngine().process().redeploy(id, input);
			} else {
				facets.getEngine().process().deploy(input);
			}
		} catch (Exception e) {
			logger.error("保存流程定义, 出现异常, id={}， model={}##{}", new Object[] { id, model, e });
			data.setErrorType(ErrorTypeEnum.INSERT_ERROR);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IO流程关闭异常##{}", new Object[] { e });
				}
			}
		}
		result.setResult(data);
		return result;
	}

	/**
	 * 
	 * @title: json
	 * @description: 查看流程图（流程图的json格式） 暂时不用
	 * 
	 * @param processId 流程定义ID
	 * @param orderId 实例ID
	 * @return
	 * @date 2017年11月13日 下午3:22:26
	 */
	@RequestMapping(value = "json", method = RequestMethod.GET)
	public ApiResult<Map<String, String>> json ( String processId, String orderId ) {
		ApiResult<Map<String, String>> result = new ApiResult<>(ErrorTypeEnum.OK);
		Process process = facets.getEngine().process().getProcessById(processId);
		AssertHelper.notNull(process);
		ProcessModel model = process.getModel();
		Map<String, String> jsonMap = new HashMap<String, String>();
		if (model != null) {
			jsonMap.put("process", SnakerHelper.getModelJson(model));
		}

		if (StringUtils.isNotEmpty(orderId)) {
			List<Task> tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
			List<HistoryTask> historyTasks = facets.getEngine().query()
					.getHistoryTasks(new QueryFilter().setOrderId(orderId));
			jsonMap.put("state", SnakerHelper.getStateJson(model, tasks, historyTasks));
		}
		// {"historyRects":{"rects":[{"paths":["TO 任务1"],"name":"开始"},{"paths":["TO 分支"],"name":"任务1"},{"paths":["TO
		// 任务3","TO 任务4","TO 任务2"],"name":"分支"}]}}
		result.setData(jsonMap);
		return result;
	}

	/**
	 * 
	 * @title: display
	 * @description: 查看流程图具体执行过程列表
	 *
	 * @param model
	 * @param processId 流程定义ID
	 * @param orderId 实例ID
	 * @return
	 * @date 2017年11月13日 下午2:53:50
	 */
	@RequestMapping(value = "display", method = RequestMethod.GET)
	public ApiResult<Map<String, Object>> display ( String processId, String orderId ) {
		ApiResult<Map<String, Object>> result = new ApiResult<Map<String, Object>>(ErrorTypeEnum.OK);
		// 查询历史流程实例
		HistoryOrder order = facets.getEngine().query().getHistOrder(orderId);
		// 查询历史任务列表
		List<HistoryTask> tasks = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("processId", processId);
		dataMap.put("order", order);
		dataMap.put("tasks", tasks);
		result.setData(dataMap);
		return result;
	}
}
