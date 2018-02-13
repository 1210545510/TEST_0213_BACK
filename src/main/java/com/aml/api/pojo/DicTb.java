package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DicTb extends BasePoJo {
	private static final long serialVersionUID = 1L;
	private Long id;

	private Long dicSid;

	private Integer sortIndex;

	private String remark;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Integer idDeleted;
	/** 以下为额外参数 */
	private String dicValue;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getDicSid () {
		return dicSid;
	}

	public void setDicSid ( Long dicSid ) {
		this.dicSid = dicSid;
	}

	public Integer getSortIndex () {
		return sortIndex;
	}

	public void setSortIndex ( Integer sortIndex ) {
		this.sortIndex = sortIndex;
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

	public Integer getIdDeleted () {
		return idDeleted;
	}

	public void setIdDeleted ( Integer idDeleted ) {
		this.idDeleted = idDeleted;
	}

	public String getDicValue () {
		return dicValue;
	}

	public void setDicValue ( String dicValue ) {
		this.dicValue = dicValue;
	}

}