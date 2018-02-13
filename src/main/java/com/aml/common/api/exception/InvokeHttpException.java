package com.aml.common.api.exception;

/**
 * API:执行http请求异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class InvokeHttpException extends APIInvokerException {
	private static final long serialVersionUID = -3337956670822128206L;

	public InvokeHttpException() {
		super();
	}

	public InvokeHttpException(String msg) {
		super(msg);
	}

	public InvokeHttpException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	protected void initDefaultCode () {
		this.errorCode = 4002;
	}

	@Override
	public String toString () {
		return "API执行http请求异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
