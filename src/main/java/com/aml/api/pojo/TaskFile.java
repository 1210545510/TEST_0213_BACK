package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaskFile extends BasePoJo {
	private static final long serialVersionUID = 5112842848306317032L;
	private Long fileId;

	private String taskId;

	private String fileName;

	private String fileType;

	private String filePath;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Integer isDeleted;

	public Long getFileId () {
		return fileId;
	}

	public void setFileId ( Long fileId ) {
		this.fileId = fileId;
	}

	public String getTaskId () {
		return taskId;
	}

	public void setTaskId ( String taskId ) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getFileName () {
		return fileName;
	}

	public void setFileName ( String fileName ) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getFileType () {
		return fileType;
	}

	public void setFileType ( String fileType ) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	public String getFilePath () {
		return filePath;
	}

	public void setFilePath ( String filePath ) {
		this.filePath = filePath == null ? null : filePath.trim();
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
}