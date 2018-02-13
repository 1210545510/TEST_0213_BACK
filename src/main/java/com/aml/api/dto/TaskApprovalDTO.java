package com.aml.api.dto;

import java.util.List;
import java.util.Map;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskApprovalDTO extends BaseDto {
	private static final long serialVersionUID = 5112842848306317032L;
	private Long approvalId;
	private Long alertId;
	private Long caseId;
	private String taskId;
	private String orderId;
	private Integer status;
	private String remark;
	private String ownerOrg;
	private String roleId;
	private String result;
	private String content;
	private Integer score;
	private Integer isApprove;
	private Integer roleNum;
	private String createUser;
	private List<Map<String, String>> files;

	public Long getApprovalId () {
		return approvalId;
	}

	public void setApprovalId ( Long approvalId ) {
		this.approvalId = approvalId;
	}

	public String getTaskId () {
		return taskId;
	}

	public void setTaskId ( String taskId ) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getOrderId () {
		return orderId;
	}

	public void setOrderId ( String orderId ) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getRoleId () {
		return roleId;
	}

	public void setRoleId ( String roleId ) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getContent () {
		return content;
	}

	public void setContent ( String content ) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getScore () {
		return score;
	}

	public void setScore ( Integer score ) {
		this.score = score;
	}

	public String getCreateUser () {
		return createUser;
	}

	public void setCreateUser ( String createUser ) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getResult () {
		return result;
	}

	public void setResult ( String result ) {
		this.result = result;
	}

	public Integer getRoleNum () {
		return roleNum;
	}

	public void setRoleNum ( Integer roleNum ) {
		this.roleNum = roleNum;
	}

	public Long getAlertId () {
		return alertId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public Integer getStatus () {
		return status;
	}

	public void setStatus ( Integer status ) {
		this.status = status;
	}

	public String getRemark () {
		return remark;
	}

	public void setRemark ( String remark ) {
		this.remark = remark;
	}

	public Long getCaseId () {
		return caseId;
	}

	public void setCaseId ( Long caseId ) {
		this.caseId = caseId;
	}

	public List<Map<String, String>> getFiles () {
		return files;
	}

	public void setFiles ( List<Map<String, String>> files ) {
		this.files = files;
	}

	public Integer getIsApprove () {
		return isApprove;
	}

	public void setIsApprove ( Integer isApprove ) {
		this.isApprove = isApprove;
	}

	public String getOwnerOrg () {
		return ownerOrg;
	}

	public void setOwnerOrg ( String ownerOrg ) {
		this.ownerOrg = ownerOrg;
	}
	

}