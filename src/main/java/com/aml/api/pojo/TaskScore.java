package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskScore extends BasePoJo {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String orderId;

	private String taskId;

	private String qualityIssues;

	private String organizedWritten;

	private String accuratelyAppropriately;

	private String grammarSpelling;

	private String otherIssues;

	private Integer grade;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Integer isDeleted;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
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

	public String getQualityIssues () {
		return qualityIssues;
	}

	public void setQualityIssues ( String qualityIssues ) {
		this.qualityIssues = qualityIssues == null ? null : qualityIssues.trim();
	}

	public String getOrganizedWritten () {
		return organizedWritten;
	}

	public void setOrganizedWritten ( String organizedWritten ) {
		this.organizedWritten = organizedWritten == null ? null : organizedWritten.trim();
	}

	public String getAccuratelyAppropriately () {
		return accuratelyAppropriately;
	}

	public void setAccuratelyAppropriately ( String accuratelyAppropriately ) {
		this.accuratelyAppropriately = accuratelyAppropriately == null ? null : accuratelyAppropriately.trim();
	}

	public String getGrammarSpelling () {
		return grammarSpelling;
	}

	public void setGrammarSpelling ( String grammarSpelling ) {
		this.grammarSpelling = grammarSpelling == null ? null : grammarSpelling.trim();
	}

	public String getOtherIssues () {
		return otherIssues;
	}

	public void setOtherIssues ( String otherIssues ) {
		this.otherIssues = otherIssues == null ? null : otherIssues.trim();
	}

	public Integer getGrade () {
		return grade;
	}

	public void setGrade ( Integer grade ) {
		this.grade = grade;
	}

	public String getCreateUser () {
		return createUser;
	}

	public void setCreateUser ( String createUser ) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime ( Date createTime ) {
		this.createTime = createTime;
	}

	public String getUpdateUser () {
		return updateUser;
	}

	public void setUpdateUser ( String updateUser ) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime ( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}