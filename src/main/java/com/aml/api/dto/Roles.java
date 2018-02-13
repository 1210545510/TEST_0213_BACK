package com.aml.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Roles implements Serializable {
	private static final long serialVersionUID = 5668151131545869959L;
	/** 自增id,角色id */
	private Long roleId;
	/** 父级角色id, 如果是0就表示是父级 */
	private Long parentRoleId;
	/** 角色名称 */
	private String roleName;
	/** 小组编码 */
	private String teamcCode;
	private Integer userCount;
	/** 用户名称集合 */
	private String userNames;

	public Long getRoleId () {
		return roleId;
	}

	public void setRoleId ( Long roleId ) {
		this.roleId = roleId;
	}

	public Long getParentRoleId () {
		return parentRoleId;
	}

	public void setParentRoleId ( Long parentRoleId ) {
		this.parentRoleId = parentRoleId;
	}

	public String getRoleName () {
		return roleName;
	}

	public void setRoleName ( String roleName ) {
		this.roleName = roleName;
	}

	public String getTeamcCode () {
		return teamcCode;
	}

	public void setTeamcCode ( String teamcCode ) {
		this.teamcCode = teamcCode;
	}

	public Integer getUserCount () {
		return userCount;
	}

	public void setUserCount ( Integer userCount ) {
		this.userCount = userCount;
	}

	public String getUserNames () {
		return userNames;
	}

	public void setUserNames ( String userNames ) {
		this.userNames = userNames;
	}

}