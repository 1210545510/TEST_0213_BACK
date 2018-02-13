package com.aml.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aml.api.service.QuartzFlowService;
import com.aml.common.core.Constants;
import com.aml.common.util.CodeUtils;
import com.aml.common.util.DateUtils;
import com.aml.common.util.PropertiesUtil;

/**
 * 
 * @className: FlowTaskService
 * @description: (Spring自主开发的定时任务工具) 任务调度：启动Alert/Case任务
 * @author huangliangbao
 * @date 2017年11月29日 上午10:11:47
 *
 */
public class FlowTaskService {
	private static final Logger LOG = LoggerFactory.getLogger(FlowTaskService.class);
	@Autowired
	private QuartzFlowService quartzFlowService;
	@Autowired
	private RedisService redisService;

	int time = 60*60;//1小时
	
	Long amlId = -100L;
	/**
	 * 
	 * @title: alertFlowTask
	 * @description: （每天早上8:00）定时创建Alert工作流
	 *
	 * @date 2017年11月29日 上午10:20:25
	 */
	public void alertFlowTask () {
		LOG.info("AlertFlow task start, time={}", DateUtils.getCurrentTime());
		try {
			String token = CodeUtils.generateToken("AML", Constants.REDIS_KEY_TIMER);// 获取token
			redisService.setValueAndExpire(token, amlId.toString(), time);
			quartzFlowService.createAlertFlow(PropertiesUtil.getValue("processId.alert"), token, amlId);
		} catch (Exception e) {
			LOG.error("AlertFlow task start error，exception={}", e);
		}
		LOG.info("AlertFlow task end, time={}", DateUtils.getCurrentTime());
	}

	/**
	 * 
	 * @title: randomSamplingWaived
	 * @description: 每天晚上(8:00) 定时抽样Waived的Alert
	 *
	 * @date 2017年12月5日 下午3:42:20
	 */
	public void randomSamplingWaived () {
		LOG.info("Random Sampling task start, time={}", DateUtils.getCurrentTime());
		try {
			String token = CodeUtils.generateToken("AML", Constants.REDIS_KEY_TIMER);// 获取token
			redisService.setValueAndExpire(token, amlId.toString(), time);
			quartzFlowService.randomSamplingWaived(token, amlId);
		} catch (Exception e) {
			LOG.error("Random Sampling start error，exception={}", e);
		}
		LOG.info("Random Sampling end, time={}", DateUtils.getCurrentTime());
	}

	/**
	 * 
	 * @title: sarFlowTask
	 * @description: （每天早上8:00）定时创建Sar工作流
	 *
	 * @date 2017年11月29日 上午10:20:25
	 */
	public void sarFlowTask () {
		LOG.info("SarFlow task start, time={}", DateUtils.getCurrentTime());
		try {
			String token = CodeUtils.generateToken("AML", Constants.REDIS_KEY_TIMER);// 获取token
			redisService.setValueAndExpire(token, amlId.toString(), time);
			 quartzFlowService.createSarFlow(PropertiesUtil.getValue("processId.sar"), token, amlId);
		} catch (Exception e) {
			LOG.error("SarFlow task start error，exception={}", e);
		}
		LOG.info("SarFlow task end, time={}", DateUtils.getCurrentTime());
	}

}
