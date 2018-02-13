package com.aml.api.dto;

import com.aml.common.base.BaseDto;

public class RequestDto extends BaseDto {
	private static final long serialVersionUID = 4285346175054306579L;
	/** 同用状态 */
	private Integer type;

	public Integer getType () {
		return type;
	}

	public void setType ( Integer type ) {
		this.type = type;
	}
}
