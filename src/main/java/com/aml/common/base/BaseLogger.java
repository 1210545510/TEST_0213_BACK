package com.aml.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录基类
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public abstract class BaseLogger {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
