package com.aml.api.dto;

import java.util.List;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AssignRuleDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long roleId;
	private Integer ruleType;
	/** 分配规则明细 */
	private List<AssignRuleItemDTO> ruleItems;

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

	public List<AssignRuleItemDTO> getRuleItems () {
		return ruleItems;
	}

	public void setRuleItems ( List<AssignRuleItemDTO> ruleItems ) {
		this.ruleItems = ruleItems;
	}

}