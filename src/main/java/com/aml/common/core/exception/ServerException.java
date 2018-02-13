package com.aml.common.core.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * server要操作事务的统一抛异常
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public class ServerException extends Exception {
	private static final long serialVersionUID = -4705084777003122129L;

	public ServerException() {
		super();
	}

	private String info;

	public ServerException(String info) {
		super(info);
		this.info = info;
	}

	public ServerException(String info, Throwable cause) {
		super(info, cause);
		this.info = info;
	}

	public String getInfo () {
		if (StringUtils.isBlank(this.info)) {
			return this.getMessage();
		}
		return info;
	}

	public void setInfo ( String info ) {
		this.info = info;
	}

}
