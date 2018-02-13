package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.WfHistOrderMapper;
import com.aml.api.dto.WfHistOrderDto;
import com.aml.common.util.PropertiesUtil;

/**
 * 
 * @className: WfHistOrderService
 * @description: WfHistOrderService Class
 * @author Novan
 * @date 2018-01-22
 *
 */
@Service
public class WfHistOrderService {
	public static final Logger logger = LoggerFactory.getLogger(WfHistOrderService.class);
	
	protected static final String PROCESSIDALERT = PropertiesUtil.getValue("processId.alert");
	protected static final String PROCESSIDSAR = PropertiesUtil.getValue("processId.sar");
	
	@Autowired
	WfHistOrderMapper wfHistOrderMapper;
	
	/**
	 * 流程数据
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWorkflow(WfHistOrderDto dto) {
		return wfHistOrderMapper.getWorkflowData(dto);
	}

	/**
	 * alert 流程数据
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWorkflowAlert(WfHistOrderDto dto) {
		dto.setProcessId(PROCESSIDALERT);
		return wfHistOrderMapper.getWorkflowData(dto);
	}

	/**
	 * Case 流程数据
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> getWorkflowCase(WfHistOrderDto dto) {
		dto.setProcessId(PROCESSIDSAR);
		return wfHistOrderMapper.getWorkflowData(dto);
	}
	
}
