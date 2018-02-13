package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AccountTypesDto extends BaseDto {
	private static final long serialVersionUID = -2109925727110961100L;
	private Long id;
	private String code;
	private String name;
	private String riskFactor;
	private String createdBy;
	private Date createDate;
	private Date lastModifyDate;
	private String lastModifyOper;
	private Integer isDeleted;
	private String param;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
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

	public String getRiskFactor () {
		return riskFactor;
	}

	public void setRiskFactor ( String riskFactor ) {
		this.riskFactor = riskFactor == null ? null : riskFactor.trim();
	}

	public String getCreatedBy () {
		return createdBy;
	}

	public void setCreatedBy ( String createdBy ) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public Date getCreateDate () {
		return createDate;
	}

	public void setCreateDate ( Date createDate ) {
		this.createDate = createDate;
	}

	public Date getLastModifyDate () {
		return lastModifyDate;
	}

	public void setLastModifyDate ( Date lastModifyDate ) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifyOper () {
		return lastModifyOper;
	}

	public void setLastModifyOper ( String lastModifyOper ) {
		this.lastModifyOper = lastModifyOper;
	}

	public String getParam () {
		return param;
	}

	public void setParam ( String param ) {
		this.param = param;
	}

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}

}