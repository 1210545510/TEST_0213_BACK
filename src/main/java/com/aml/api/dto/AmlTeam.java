package com.aml.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmlTeam  implements Serializable {
	private static final long serialVersionUID = 1809217313758962215L;

	private String teamCode;// 小组编码

	private String name;// 小组名称

	private List<Roles> roleList;// 对应角色集合

	public String getTeamCode () {
		return teamCode;
	}

	public void setTeamCode ( String teamCode ) {
		this.teamCode = teamCode;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public List<Roles> getRoleList () {
		return roleList;
	}

	public void setRoleList ( List<Roles> roleList ) {
		this.roleList = roleList;
	}

}