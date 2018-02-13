package com.aml.common.core;

/**
 * 
 * @className: ErrorTypeEnum
 * @description: 错误信息类型枚举，注意：不要避免定义重复的错误类型
 * @author huangliangbao
 * @date 2017年11月16日 上午9:48:32
 *
 */
public enum ErrorTypeEnum {
	/** OK */
	OK(0, "OK"), 
	NO_LOGIN_61(-110, "Insufficient permissions"), 
	/** 系统模块错误类型，-200到-299 */
	SERVER_EXCEPTION(-200, "There was an error during service run"), 
	DB_ERROR(-201, "Database error"), 
	DB_EXCEPTION(-202, "Database exception"), 
	REDIS_EXCEPTION(-203, "Cached connection failed"), 
	IO_EXCEPTION(-204, "IO error"), 
	MYSQL_INTEGRITYCONSTRAINTVIOLATIO_NEXCEPTION(-205, "SQL database exception"), 
	ERROR_UPFILE_ERROR(-206, "Upload file error"), 
	API_EXCEPTION(-210, "API error"), 
	NOT_FOUND(-211, "The record does not exist"), 
	CODE_FOUND(-212, "The encoding already exists"), 
	NAME_FOUND(-213, "The name already exists"), 
	INSERT_ERROR(-214, "Insertion failure"),
	UPDATE_ERROR(-215, "Modification failed"),
	DELETE_ERROR(-216, "Delete failed"),
	IMG_ERROE(-217, "Upload image images can only be JPG/PNG/GIF format!"),
	ERROR_VALIDATE(-218, "Parameter check failed"), 
	NOT_NEXT_USER_ERROR(-219, "No next node processor was found"), 

	/** 用户模块错误类型，-300到-399*/
	NO_LOGIN(-300, "Please re-login"), 
	OTHER_EQUIPMENT_LOGIN(-301, "You have logged into this account on other devices."), //
	;

	/** 错误状态码 */
	private int errorCode;
	/** 错误信息 */
	private String errorMsg;

	ErrorTypeEnum(int code, String msg) {
		this.errorCode = code;
		this.errorMsg = msg;
	}

	public int getErrorCode () {
		return errorCode;
	}

	public void setErrorCode ( int errorCode ) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg () {
		return errorMsg;
	}

	public void setErrorMsg ( String errorMsg ) {
		this.errorMsg = errorMsg;
	}
}
