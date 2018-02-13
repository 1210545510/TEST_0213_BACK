package com.aml.common.api.exception;

/**
 * API:HTTP返回：200调用异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class InvalidHttpStatusException extends APIInvokerException {
	private static final long serialVersionUID = 1L;

	public InvalidHttpStatusException() {
		super();
	}

	public InvalidHttpStatusException(String msg) {
		super(msg);
		this.errorMsg = msg;
	}

	public InvalidHttpStatusException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	protected void initDefaultCode() {
		this.errorCode = 4001;
	}

	@Override
	public String toString() {
		return "API HTTP返回200异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
