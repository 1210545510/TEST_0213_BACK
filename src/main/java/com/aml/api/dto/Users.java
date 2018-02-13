package com.aml.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Users implements Serializable {
	private static final long serialVersionUID = -1603249908442759208L;
	/** 自增id，主要是用于关系绑定 */
	private Long userId;
	/** 用户编码 */
	private String userCode;
	/** 用户名 */
	private String userName;
	/** 用户姓名 */
	private String name;
	private String firstName;
	private String lastName;
	/** 性别 */
	private Integer gender;
	/** 小组编码 */
	private String teamCode;
	private Date createTime;
	/** 角色 */
	private List<Roles> roles;
	/** 工作天数 */
	private int days;
	/** 平均分 */
	private String score;

	public Long getUserId () {
		return userId;
	}

	public void setUserId ( Long userId ) {
		this.userId = userId;
	}

	public String getUserCode () {
		return userCode;
	}

	public void setUserCode ( String userCode ) {
		this.userCode = userCode;
	}

	public String getUserName () {
		return userName;
	}

	public void setUserName ( String userName ) {
		this.userName = userName;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public String getFirstName () {
		return firstName;
	}

	public void setFirstName ( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName () {
		return lastName;
	}

	public void setLastName ( String lastName ) {
		this.lastName = lastName;
	}

	public Integer getGender () {
		return gender;
	}

	public void setGender ( Integer gender ) {
		this.gender = gender;
	}

	public String getTeamCode () {
		return teamCode;
	}

	public void setTeamCode ( String teamCode ) {
		this.teamCode = teamCode;
	}

	public List<Roles> getRoles () {
		return roles;
	}

	public void setRoles ( List<Roles> roles ) {
		this.roles = roles;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime ( Date createTime ) {
		this.createTime = createTime;
	}

	public int getDays () {
		return days;
	}

	public void setDays ( int days ) {
		this.days = days;
	}

	public String getScore () {
		return score;
	}

	public void setScore ( String score ) {
		this.score = score;
	}

}