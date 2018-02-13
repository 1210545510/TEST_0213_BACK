package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class WfBusStatus extends BasePoJo {
	private static final long serialVersionUID = 6141833573803552034L;

	private Integer id;

	private String didSid;

	private String flowId;

	private Integer statusType;

	private String paramValue;

	private String dicCode;

	private String sortIndex;

	private String owner;

	private String oranization;

	private String remark;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Byte idDeleted;

	private Long userId;

	private Integer dicId;

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

	public String getFlowId () {
		return flowId;
	}

	public void setFlowId ( String flowId ) {
		this.flowId = flowId == null ? null : flowId.trim();
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

	public String getSortIndex () {
		return sortIndex;
	}

	public void setSortIndex ( String sortIndex ) {
		this.sortIndex = sortIndex == null ? null : sortIndex.trim();
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

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime ( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public Byte getIdDeleted () {
		return idDeleted;
	}

	public void setIdDeleted ( Byte idDeleted ) {
		this.idDeleted = idDeleted;
	}

	public Long getUserId () {
		return userId;
	}

	public void setUserId ( Long userId ) {
		this.userId = userId;
	}

	public Integer getDicId () {
		return dicId;
	}

	public void setDicId ( Integer dicId ) {
		this.dicId = dicId;
	}

}