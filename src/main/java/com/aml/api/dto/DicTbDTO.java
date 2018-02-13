package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DicTbDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long dicSid;
	private Integer sortIndex;

	public Long getDicSid () {
		return dicSid;
	}

	public void setDicSid ( Long dicSid ) {
		this.dicSid = dicSid;
	}

	public Integer getSortIndex () {
		return sortIndex;
	}

	public void setSortIndex ( Integer sortIndex ) {
		this.sortIndex = sortIndex;
	}

}