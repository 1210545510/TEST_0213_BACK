package com.aml.api.pojo;

import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskApproval extends BasePoJo {
	private static final long serialVersionUID = 5112842848306317032L;
	private Long approvalId;

	private String taskId;

	private String orderId;

	private String roleId;

	private String result;

	private String content;

	private Integer score;
	
	private Integer isApprove;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Integer isDeleted;

	/** 附件列表 */
	private List<TaskFile> files;

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

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}

	public String getResult () {
		return result;
	}

	public void setResult ( String result ) {
		this.result = result;
	}

	public List<TaskFile> getFiles () {
		return files;
	}

	public void setFiles ( List<TaskFile> files ) {
		this.files = files;
	}

	public Integer getIsApprove () {
		return isApprove;
	}

	public void setIsApprove ( Integer isApprove ) {
		this.isApprove = isApprove;
	}

}