package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CountriesDto extends BaseDto {
	private static final long serialVersionUID = 5139191784912714645L;
	private Integer id;
	private String codeName;
	private String region;
	private String monitor;
	private String rist;
	private String caseScore;
	private String creatBy;
	private Date creatDate;
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

	public String getRegion () {
		return region;
	}

	public void setRegion ( String region ) {
		this.region = region == null ? null : region.trim();
	}

	public String getMonitor () {
		return monitor;
	}

	public void setMonitor ( String monitor ) {
		this.monitor = monitor == null ? null : monitor.trim();
	}

	public String getRist () {
		return rist;
	}

	public void setRist ( String rist ) {
		this.rist = rist == null ? null : rist.trim();
	}

	public String getCaseScore () {
		return caseScore;
	}

	public void setCaseScore ( String caseScore ) {
		this.caseScore = caseScore == null ? null : caseScore.trim();
	}

	public String getCreatBy () {
		return creatBy;
	}

	public void setCreatBy ( String creatBy ) {
		this.creatBy = creatBy == null ? null : creatBy.trim();
	}

	public Date getCreatDate () {
		return creatDate;
	}

	public void setCreatDate ( Date creatDate ) {
		this.creatDate = creatDate;
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