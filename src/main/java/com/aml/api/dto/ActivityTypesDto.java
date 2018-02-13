package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ActivityTypesDto extends BaseDto {
	private static final long serialVersionUID = 2683445146627739245L;
	private Integer id;
	private String codeName;
	private String riskFactor;
	private String createdBy;
	private Date lastModifyDate;
	private String lastModifyOper;
	private Integer isDeleted;
	private String param;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getCodeName () {
		return codeName;
	}

	public void setCodeName ( String codeName ) {
		this.codeName = codeName == null ? null : codeName.trim();
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
		this.lastModifyOper = lastModifyOper == null ? null : lastModifyOper.trim();
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