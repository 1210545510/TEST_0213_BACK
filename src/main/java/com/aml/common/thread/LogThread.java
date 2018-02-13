package com.aml.common.thread;

import com.alibaba.fastjson.JSONObject;
import com.aml.common.util.UMSUtils;

/**
 * 
 * @className: LogThread
 * @description:
 * @author shun
 * @date 2017年12月29日
 *
 */
public class LogThread  extends Thread{

	JSONObject msg;
	String token;
	Long userId;
	
	public LogThread(JSONObject msg, String token, Long userId){
		this.msg = msg;
		this.token = token;
		this.userId = userId;
	}
	
	@Override
	public void run() {
		UMSUtils.logInterface(msg, token, userId);
	}
	
}
