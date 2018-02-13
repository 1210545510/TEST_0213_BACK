package com.aml.common.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * API返回结果类
 * 
 * @author zouwei
 * @since 2017年6月23日
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class ApiResult<T> extends Result {
	private static final long serialVersionUID = 33123178473941890L;

	/** 返回结果 */
	private T data;

	public ApiResult() {

	}

	public ApiResult(boolean success) {
		super(success);
	}

	public ApiResult(ErrorTypeEnum errorType) {
		super(errorType);
	}

	public void setData ( T data ) {
		this.data = data;
	}

	public T getData () {
		return data;
	}

	@Override
	public String toString () {
		return "ApiResult [data=" + data + ", errorCode=" + getStatus() + ", errorMsg=" + getErrorInfo() + ", success="
				+ isSuccess() + "]";
	}

}
