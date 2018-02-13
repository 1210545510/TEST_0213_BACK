package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class WfBusStatusDTO extends BaseDto {
	private static final long serialVersionUID = -3193555621295887527L;
	private Integer id;
	private String didSid;
	private String flowId;
	private Integer statusType;
	private String paramValue;
	private String dicCode;
	private String owner;
	private String oranization;
	private String remark;
	private String createUser;
	private Date createTime;
	private String updateUser;
	/** 是否配置 */
	private Integer isConfigured;
	private Long dicId;
	private String dicKey;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getDidSid () {
		return didSid;
	}

	public void setDidSid ( String didSid ) {
		this.didSid = didSid == null ? null : didSid.trim();
	}

	public Integer getStatusType () {
		return statusType;
	}

	public void setStatusType ( Integer statusType ) {
		this.statusType = statusType;
	}

	public String getParamValue () {
		return paramValue;
	}

	public void setParamValue ( String paramValue ) {
		this.paramValue = paramValue == null ? null : paramValue.trim();
	}

	public String getDicCode () {
		return dicCode;
	}

	public void setDicCode ( String dicCode ) {
		this.dicCode = dicCode == null ? null : dicCode.trim();
	}

	public String getOwner () {
		return owner;
	}

	public void setOwner ( String owner ) {
		this.owner = owner == null ? null : owner.trim();
	}

	public String getOranization () {
		return oranization;
	}

	public void setOranization ( String oranization ) {
		this.oranization = oranization == null ? null : oranization.trim();
	}

	public String getRemark () {
		return remark;
	}

	public void setRemark ( String remark ) {
		this.remark = remark == null ? null : remark.trim();
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

	public Integer getIsConfigured () {
		return isConfigured;
	}

	public void setIsConfigured ( Integer isConfigured ) {
		this.isConfigured = isConfigured;
	}

	public Long getDicId () {
		return dicId;
	}

	public void setDicId ( Long dicId ) {
		this.dicId = dicId;
	}

	public String getFlowId () {
		return flowId;
	}

	public void setFlowId ( String flowId ) {
		this.flowId = flowId;
	}

	public String getDicKey () {
		return dicKey;
	}

	public void setDicKey ( String dicKey ) {
		this.dicKey = dicKey;
	}

}