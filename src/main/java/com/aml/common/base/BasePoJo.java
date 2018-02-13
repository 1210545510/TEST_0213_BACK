package com.aml.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * base pojo
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
@JsonInclude(Include.NON_NULL)
public abstract class BasePoJo extends BaseBean {
	private static final long serialVersionUID = 1L;
}
