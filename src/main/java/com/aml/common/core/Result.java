package com.aml.common.core;

import com.aml.common.base.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * server端返回结果集
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
@JsonInclude(Include.NON_NULL)
public class Result extends BaseBean {
	private static final long serialVersionUID = -3247721732941599513L;
	/** 状态码 */
	private int status;
	/** 错误信息 */
	private String errorInfo;

	/** 是否成功 */
	private boolean success = false;

	public Result() {

	}

	/**
	 * 设置service返回result
	 * 
	 * @param r
	 */
	public void setResult ( Result r ) {
		this.status = r.status;
		this.errorInfo = r.errorInfo;
		this.success = r.success;
	}

	public Result(boolean success) {
		this.success = success;
	}

	public Result(ErrorTypeEnum errorType) {
		this.status = errorType.getErrorCode();
		this.errorInfo = errorType.getErrorMsg();
		if (ErrorTypeEnum.OK.equals(errorType)) {
			this.success = true;
		}
	}

	/**
	 * 设置错误类型
	 */
	public void setErrorType ( ErrorTypeEnum errorType ) {
		this.setStatus(errorType.getErrorCode());
		this.setErrorInfo(errorType.getErrorMsg());
		if (ErrorTypeEnum.OK.equals(errorType)) {
			this.success = true;
		} else {
			this.success = false;
		}
	}

	public int getStatus () {
		return status;
	}

	public void setStatus ( int status ) {
		if (status != 0) {
			this.setSuccess(false);
		}
		this.status = status;
	}

	public String getErrorInfo () {
		return errorInfo;
	}

	public void setErrorInfo ( String errorInfo ) {
		this.errorInfo = errorInfo;
		this.setStatus(-100);
		this.success = false;
	}

	public boolean isSuccess () {
		return success;
	}

	public void setSuccess ( boolean success ) {
		this.success = success;
	}

	@Override
	public String toString () {
		return "Result [status=" + status + ", errorInfo=" + errorInfo + ", success=" + success + "]";
	}
}
