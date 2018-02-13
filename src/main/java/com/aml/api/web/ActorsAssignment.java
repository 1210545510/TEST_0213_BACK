package com.aml.api.web;

import java.util.Map;

import org.snaker.engine.Assignment;
import org.snaker.engine.core.Execution;
import org.snaker.engine.model.TaskModel;


/**
 * 
 * @className: ActorsAssignment
 * @description: 自定义参与者 测试使用
 * @author huangliangbao
 * @date 2017年11月24日 下午2:50:15
 *
 */
public class ActorsAssignment extends Assignment {

	@Override
	public Object assign ( TaskModel model, Execution execution ) {
		Map<String, Object> args = execution.getArgs();
		String operator = (String)args.get("userName");
		return operator;
	}

}
