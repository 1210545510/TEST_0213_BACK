package com.aml.common.api.exception;

/**
 * API:异常基类
 * 
 * @author zouwei
 * @since 2017年6月22日
 */
public class APIInvokerException extends Exception {
	private static final long serialVersionUID = 8746165788591418359L;
	protected int errorCode;
	protected String errorMsg;

	public APIInvokerException() {
		super();
		initDefaultCode();
	}

	public APIInvokerException(int code) {
		super();
		this.errorCode = code;
	}

	public APIInvokerException(String msg) {
		super(msg);
		this.errorMsg = msg;
		initDefaultCode();
	}

	public APIInvokerException(int code, String msg) {
		super(msg);
		this.errorCode = code;
		this.errorMsg = msg;
	}

	public APIInvokerException(Throwable cause) {
		super(cause);
		initDefaultCode();
	}

	public APIInvokerException(int code, Throwable cause) {
		super(cause);
		this.errorCode = code;
	}

	public APIInvokerException(String msg, Throwable cause) {
		super(msg, cause);
		this.errorMsg = msg;
		initDefaultCode();
	}

	public APIInvokerException(int code, String msg, Throwable cause) {
		super(msg, cause);
		this.errorCode = code;
		this.errorMsg = msg;
	}

	/**
	 * 初始化默认错误码
	 */
	protected void initDefaultCode () {
		this.errorCode = 4000;
	}

	public int getErrorCode () {
		return errorCode;
	}

	public String getErrorMsg () {
		return errorMsg;
	}

	@Override
	public String toString () {
		return "API异常，错误码：" + errorCode + "；错误信息：" + errorMsg;
	}

}
