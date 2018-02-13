package com.aml.common.api.exception;

/**
 * 服务异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class ServiceException extends APIInvokerException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	protected void initDefaultCode () {
		this.errorCode = 4006;
	}

	@Override
	public String toString () {
		return "服务异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
