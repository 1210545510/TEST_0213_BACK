package com.aml.common.api.exception;

/**
 * 参数异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class ParamEncodingException extends APIInvokerException {
	private static final long serialVersionUID = 1L;

	public ParamEncodingException() {
		super();
	}

	public ParamEncodingException(String msg) {
		super(msg);
		this.errorMsg = msg;
	}

	public ParamEncodingException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	protected void initDefaultCode () {
		this.errorCode = 4004;
	}

	@Override
	public String toString () {
		return "API转码异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
