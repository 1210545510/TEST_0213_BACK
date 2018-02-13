package com.aml.api.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AssignRuleItem extends BasePoJo {
	private static final long serialVersionUID = 1L;
	private Long id;

	private Long ruleId;

	private Integer startValue;

	private Integer endValue;

	private Integer threshold;

	private Date createTime;

	private Date updateTime;

	private Integer isDeleted;

	private boolean isExistUser;

	private List<com.aml.api.dto.Users> containUsers = new ArrayList<>();

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getRuleId () {
		return ruleId;
	}

	public void setRuleId ( Long ruleId ) {
		this.ruleId = ruleId;
	}

	public Integer getStartValue () {
		return startValue;
	}

	public void setStartValue ( Integer startValue ) {
		this.startValue = startValue;
	}

	public Integer getEndValue () {
		return endValue;
	}

	public void setEndValue ( Integer endValue ) {
		this.endValue = endValue;
	}

	public Integer getThreshold () {
		return threshold;
	}

	public void setThreshold ( Integer threshold ) {
		this.threshold = threshold;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime ( Date createTime ) {
		this.createTime = createTime;
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

	public boolean isExistUser () {
		return isExistUser;
	}

	public void setExistUser ( boolean isExistUser ) {
		this.isExistUser = isExistUser;
	}

	public List<com.aml.api.dto.Users> getContainUsers () {
		return containUsers;
	}

	public void setContainUsers ( List<com.aml.api.dto.Users> containUsers ) {
		this.containUsers = containUsers;
	}



}