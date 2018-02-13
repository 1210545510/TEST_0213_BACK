package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JobsDto extends BaseDto {
	private static final long serialVersionUID = -7885919092537484892L;
	private Integer id;
	private String code;
	private String name;
	private String precess;
	private Integer isDeleted;
	private String param;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getCode () {
		return code;
	}

	public void setCode ( String code ) {
		this.code = code == null ? null : code.trim();
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name == null ? null : name.trim();
	}

	public String getPrecess () {
		return precess;
	}

	public void setPrecess ( String precess ) {
		this.precess = precess == null ? null : precess.trim();
	}

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}

	public String getParam () {
		return param;
	}

	public void setParam ( String param ) {
		this.param = param;
	}

}