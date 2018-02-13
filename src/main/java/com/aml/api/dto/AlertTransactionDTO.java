package com.aml.api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlertTransactionDTO extends BaseDto {
	private static final long serialVersionUID = 4777098933220742269L;
	/** 原始id */
	private Long originalId;
	/** 告警id */
	private Long alertId;
	/** 开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	/** 结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	/** 搜索 */
	private String search;
	
	private Integer type;

	private Long id;
	
	private Integer isAlert;
	
	public Long getOriginalId () {
		return originalId;
	}

	public Long getAlertId () {
		return alertId;
	}

	public Date getStartDate () {
		return startDate;
	}

	public Date getEndDate () {
		return endDate;
	}

	public String getSearch () {
		return search;
	}

	public void setOriginalId ( Long originalId ) {
		this.originalId = originalId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public void setStartDate ( Date startDate ) {
		this.startDate = startDate;
	}

	public void setEndDate ( Date endDate ) {
		this.endDate = endDate;
	}

	public void setSearch ( String search ) {
		this.search = search;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(Integer isAlert) {
		this.isAlert = isAlert;
	}
}