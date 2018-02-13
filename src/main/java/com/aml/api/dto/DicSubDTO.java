package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DicSubDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long dicSid;
	private Long dicId;
	private String dicCode;
	private String dicKey;
	private String dicValue;
	private Integer sortIndex;
	private String remark;

	public Long getDicSid () {
		return dicSid;
	}

	public void setDicSid ( Long dicSid ) {
		this.dicSid = dicSid;
	}

	public Long getDicId () {
		return dicId;
	}

	public void setDicId ( Long dicId ) {
		this.dicId = dicId;
	}

	public String getDicCode () {
		return dicCode;
	}

	public void setDicCode ( String dicCode ) {
		this.dicCode = dicCode;
	}

	public String getDicKey () {
		return dicKey;
	}

	public void setDicKey ( String dicKey ) {
		this.dicKey = dicKey;
	}

	public String getDicValue () {
		return dicValue;
	}

	public void setDicValue ( String dicValue ) {
		this.dicValue = dicValue;
	}

	public Integer getSortIndex () {
		return sortIndex;
	}

	public void setSortIndex ( Integer sortIndex ) {
		this.sortIndex = sortIndex;
	}

	public String getRemark () {
		return remark;
	}

	public void setRemark ( String remark ) {
		this.remark = remark;
	}

}