package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AssignRuleItemDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long ruleId;
	private Integer startValue;
	private Integer endValue;
	private Integer threshold;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getRuleId () {
		return ruleId;
	}

	public void setRuleId ( Long ruleId ) {
		this.ruleId = ruleId;
	}

	public Integer getStartValue () {
		return startValue;
	}

	public void setStartValue ( Integer startValue ) {
		this.startValue = startValue;
	}

	public Integer getEndValue () {
		return endValue;
	}

	public void setEndValue ( Integer endValue ) {
		this.endValue = endValue;
	}

	public Integer getThreshold () {
		return threshold;
	}

	public void setThreshold ( Integer threshold ) {
		this.threshold = threshold;
	}
}