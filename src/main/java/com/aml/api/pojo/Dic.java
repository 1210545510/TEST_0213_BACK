package com.aml.api.pojo;

import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Dic extends BasePoJo{
	private static final long serialVersionUID = 1L;

	private Long dicId;

	private String dicCode;

	private String dicName;

	private String dicValue;

	private Integer sortIndex;

	private String remark;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Integer isDeleted;

	/** 数据字典子项 */
	private List<DicSub> dicSubs;

	public Long getDicId () {
		return dicId;
	}

	public void setDicId ( Long dicId ) {
		this.dicId = dicId;
	}

	public String getDicCode () {
		return dicCode;
	}

	public void setDicCode ( String dicCode ) {
		this.dicCode = dicCode == null ? null : dicCode.trim();
	}

	public String getDicName () {
		return dicName;
	}

	public void setDicName ( String dicName ) {
		this.dicName = dicName == null ? null : dicName.trim();
	}

	public String getDicValue () {
		return dicValue;
	}

	public void setDicValue ( String dicValue ) {
		this.dicValue = dicValue == null ? null : dicValue.trim();
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

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}

	public List<DicSub> getDicSubs () {
		return dicSubs;
	}

	public void setDicSubs ( List<DicSub> dicSubs ) {
		this.dicSubs = dicSubs;
	}
	
}