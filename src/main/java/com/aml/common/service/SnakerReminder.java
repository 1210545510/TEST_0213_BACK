package com.aml.common.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.entity.Process;
import org.snaker.engine.model.NodeModel;
import org.snaker.engine.scheduling.IReminder;

/**
 * 
 * @className: SnakerReminderService
 * @description: 流程提醒服务
 * @author huangliangbao
 * @date 2017年12月7日 下午3:40:30
 *
 */
public class SnakerReminder implements IReminder {
	private static final Logger LOG = LoggerFactory.getLogger(SnakerReminder.class);
	@Override
	public void remind ( Process process, String orderId, String taskId, NodeModel nodeModel,
			Map<String, Object> data ) {
		LOG.info("orderId:"+orderId);
		LOG.info("taskId:"+taskId);
		LOG.info("process:"+process.getDisplayName());
		LOG.info("nodeModel:"+nodeModel.getDisplayName());
        for(String key:data.keySet()){
        	LOG.info(key+":"+data.get(key));
        }

	}

}
