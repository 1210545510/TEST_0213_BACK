package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ActivityMapsDto extends BaseDto {
	private static final long serialVersionUID = 6785310351692561012L;
	private Integer id;
	private String originalActivity;
	private String jobId;
	private String primeCode;
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

	public String getOriginalActivity () {
		return originalActivity;
	}

	public void setOriginalActivity ( String originalActivity ) {
		this.originalActivity = originalActivity == null ? null : originalActivity.trim();
	}

	public String getJobId () {
		return jobId;
	}

	public void setJobId ( String jobId ) {
		this.jobId = jobId == null ? null : jobId.trim();
	}

	public String getPrimeCode () {
		return primeCode;
	}

	public void setPrimeCode ( String primeCode ) {
		this.primeCode = primeCode == null ? null : primeCode.trim();
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