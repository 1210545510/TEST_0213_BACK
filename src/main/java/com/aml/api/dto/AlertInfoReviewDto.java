package com.aml.api.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlertInfoReviewDto {
	private List<Map<String, Object>> list;
	private String reviewDate;

	public List<Map<String, Object>> getList () {
		return list;
	}

	public void setList ( List<Map<String, Object>> list ) {
		this.list = list;
	}

	public String getReviewDate () {
		return reviewDate;
	}

	public void setReviewDate ( String reviewDate ) {
		this.reviewDate = reviewDate;
	}

}