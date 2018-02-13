package com.aml.api.dto;

import java.util.List;

public class User {
	private String userId;
	private String userName;
	private List<Roles> roles;

	public String getUserId () {
		return userId;
	}

	public void setUserId ( String userId ) {
		this.userId = userId;
	}

	public String getUserName () {
		return userName;
	}

	public void setUserName ( String userName ) {
		this.userName = userName;
	}

	public List<Roles> getRoles () {
		return roles;
	}

	public void setRoles ( List<Roles> roles ) {
		this.roles = roles;
	}

}
