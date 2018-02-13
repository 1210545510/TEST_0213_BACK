package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskScoreDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long alertId;
	private Long caseId;
	private String orderId;
	private String taskId;
	private String organizedWritten;
	private String accuratelyAppropriately;
	private String grammarSpelling;
	private String otherIssues;
	private Integer grade;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getOrderId () {
		return orderId;
	}

	public void setOrderId ( String orderId ) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getTaskId () {
		return taskId;
	}

	public void setTaskId ( String taskId ) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getOrganizedWritten () {
		return organizedWritten;
	}

	public void setOrganizedWritten ( String organizedWritten ) {
		this.organizedWritten = organizedWritten;
	}

	public String getAccuratelyAppropriately () {
		return accuratelyAppropriately;
	}

	public void setAccuratelyAppropriately ( String accuratelyAppropriately ) {
		this.accuratelyAppropriately = accuratelyAppropriately;
	}

	public String getGrammarSpelling () {
		return grammarSpelling;
	}

	public void setGrammarSpelling ( String grammarSpelling ) {
		this.grammarSpelling = grammarSpelling;
	}

	public String getOtherIssues () {
		return otherIssues;
	}

	public void setOtherIssues ( String otherIssues ) {
		this.otherIssues = otherIssues;
	}

	public Integer getGrade () {
		return grade;
	}

	public void setGrade ( Integer grade ) {
		this.grade = grade;
	}

	public Long getAlertId () {
		return alertId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public Long getCaseId () {
		return caseId;
	}

	public void setCaseId ( Long caseId ) {
		this.caseId = caseId;
	}
}