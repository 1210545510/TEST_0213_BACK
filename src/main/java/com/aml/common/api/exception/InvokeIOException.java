package com.aml.common.api.exception;

/**
 * IO异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class InvokeIOException extends APIInvokerException {
	private static final long serialVersionUID = -2959525371736988483L;

	public InvokeIOException() {
		super();
	}

	public InvokeIOException(String msg) {
		super(msg);
		this.errorMsg = msg;
	}

	public InvokeIOException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	protected void initDefaultCode () {
		this.errorCode = 4003;
	}

	@Override
	public String toString () {
		return "API IO异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
