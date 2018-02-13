package com.aml.common.api.exception;

/**
 * API:结果转换异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class ResultParseException extends APIInvokerException {
	private static final long serialVersionUID = 1L;

	public ResultParseException() {
		super();
	}

	public ResultParseException(String message) {
		super(message);
	}

	public ResultParseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	protected void initDefaultCode () {
		this.errorCode = 4005;
	}

	@Override
	public String toString () {
		return "API结果转换异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
