package com.aml.api.pojo;

import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AssignRule extends BasePoJo {
	private static final long serialVersionUID = 1L;
	private Long id;

	private Long roleId;

	private Integer ruleType;

	private Date createTime;

	private Date updateTime;

	private Integer isDeleted;

	/** 分配规则明细 */
	private List<AssignRuleItem> ruleItems;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getRoleId () {
		return roleId;
	}

	public void setRoleId ( Long roleId ) {
		this.roleId = roleId;
	}

	public Integer getRuleType () {
		return ruleType;
	}

	public void setRuleType ( Integer ruleType ) {
		this.ruleType = ruleType;
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

	public List<AssignRuleItem> getRuleItems () {
		return ruleItems;
	}

	public void setRuleItems ( List<AssignRuleItem> ruleItems ) {
		this.ruleItems = ruleItems;
	}

}