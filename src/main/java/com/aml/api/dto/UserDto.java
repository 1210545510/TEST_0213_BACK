package com.aml.api.dto;

import java.util.Date;
import java.util.List;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @className: UserDto
 * @description:
 * @author shun
 * @date 2017年12月29日
 *
 */
@JsonInclude(Include.NON_NULL)
public class UserDto extends BaseDto {
	private static final long serialVersionUID = -1603249908442759208L;
	/** 更新时间 */
	private Date updateTime;
	/** 删除标识,0_可用，1_已删除不可用 */
	private Byte isDeleted;
	private String apiKey;
	private Integer dateCount;
	/** 公司 */
	private String cCompanyName;
	/** 部门 */
	private String dName;
	/** 组织 */
	private String tName;
	/** 许可 */
	private List<String> uaCompanyName;
	/** 用户编码 */
	private String userCode;
	/** 公司编码 */
	private String companyCode;
	/** 部门编码 */
	private String departmentCode;
	/** 小组编码 */
	private String teamCode;
	/** 邮箱 */
	private String email;
	/** 密码有效期 */
	private Date passwordValidity;
	/** 开始日期20161024 */
	private Integer startDateProfile;
	/** 结束日期20161024 */
	private Integer endDateProfile;
	/** 超时时长 */
	private Integer timeOutMinutes;
	/** 尝试次数 */
	private Integer attempts;
	private String companyRestr;
	/** sms组id */
	private String smsGroupId;
	/** 是否开启登陆日志 Y/N */
	private String signOnOffLog;
	private Integer attemptsSince;
	/** 最后登陆日期 */
	private Date dateLastSignOn;
	/** 密码 */
	private String password;
	/** （修改）首次密码 */
	private String newPassword;
	/** （修改）再次密码 */
	private String againPassword;
	/** 密码修改日期 */
	private Date passwChangeDate;
	/** 登陆状态，active, */
	private Integer loginStatus;
	private String recordStatus;
	/** 输入信息人 */
	private String inputter;
	/** 创建时间 */
	private Date dateTime;
	/** 授权人 */
	private String authoriser;
	/** 公司编码 */
	private String coCode;
	/** 部门编码 */
	private String deptCode;
	/** 小组编码 */
	private String teamcCode;
	/** 创建时间 */
	private Date createTime;
	/** 授权状态:0：授权中，1：通过，2：不通过 */
	private Integer authStatus;
	/** 授权备注 */
	private String authDesc;
	private Long isActive;
	private Long lloydsId;
	private String lloydsPassword;
	private Long bridgerId;
	private String bridgerPassword;
	private Long dowJonesId;
	private String dowJonesPassword;
	private Date confirmedAt;
	/** 用户姓名 */
	private String name;
	/** 性别 */
	private Integer gender;
	/** 职位 */
	private String position;
	/** 手机号码 */
	private String phoneNumber;
	/** 固定电话 */
	private String telephone;
	/** 汇报对象 */
	private Long reportToUser;
	/** 是否直接上司汇报，0：否，1:是 */
	private Byte isHeadReportTo;
	/** profile图片url */
	private String profileImageUrl;
	/** 搜索 */
	private String search;
	private Integer roleId;
	/** 角色 */
	private List<Integer> roles;
	private List<String> authCompanyCode;
	private String firstName;
	private String lastName;
	private String url;

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

	public String getUserCode () {
		return userCode;
	}

	public void setUserCode ( String userCode ) {
		this.userCode = userCode;
	}

	public String getCompanyCode () {
		return companyCode;
	}

	public void setCompanyCode ( String companyCode ) {
		this.companyCode = companyCode;
	}

	public String getDepartmentCode () {
		return departmentCode;
	}

	public void setDepartmentCode ( String departmentCode ) {
		this.departmentCode = departmentCode;
	}

	public String getTeamCode () {
		return teamCode;
	}

	public void setTeamCode ( String teamCode ) {
		this.teamCode = teamCode;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail ( String email ) {
		this.email = email;
	}

	public Date getPasswordValidity () {
		return passwordValidity;
	}

	public void setPasswordValidity ( Date passwordValidity ) {
		this.passwordValidity = passwordValidity;
	}

	public Integer getStartDateProfile () {
		return startDateProfile;
	}

	public void setStartDateProfile ( Integer startDateProfile ) {
		this.startDateProfile = startDateProfile;
	}

	public Integer getEndDateProfile () {
		return endDateProfile;
	}

	public void setEndDateProfile ( Integer endDateProfile ) {
		this.endDateProfile = endDateProfile;
	}

	public Integer getTimeOutMinutes () {
		return timeOutMinutes;
	}

	public void setTimeOutMinutes ( Integer timeOutMinutes ) {
		this.timeOutMinutes = timeOutMinutes;
	}

	public Integer getAttempts () {
		return attempts;
	}

	public void setAttempts ( Integer attempts ) {
		this.attempts = attempts;
	}

	public String getCompanyRestr () {
		return companyRestr;
	}

	public void setCompanyRestr ( String companyRestr ) {
		this.companyRestr = companyRestr;
	}

	public String getSmsGroupId () {
		return smsGroupId;
	}

	public void setSmsGroupId ( String smsGroupId ) {
		this.smsGroupId = smsGroupId;
	}

	public String getSignOnOffLog () {
		return signOnOffLog;
	}

	public void setSignOnOffLog ( String signOnOffLog ) {
		this.signOnOffLog = signOnOffLog;
	}

	public void setAttemptsSince ( Integer attemptsSince ) {
		this.attemptsSince = attemptsSince;
	}

	public Integer getAttemptsSince () {
		return attemptsSince;
	}

	public Date getDateLastSignOn () {
		return dateLastSignOn;
	}

	public void setDateLastSignOn ( Date dateLastSignOn ) {
		this.dateLastSignOn = dateLastSignOn;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword ( String password ) {
		this.password = password;
	}

	public String getNewPassword () {
		return newPassword;
	}

	public void setNewPassword ( String newPassword ) {
		this.newPassword = newPassword;
	}

	public String getAgainPassword () {
		return againPassword;
	}

	public void setAgainPassword ( String againPassword ) {
		this.againPassword = againPassword;
	}

	public Date getPasswChangeDate () {
		return passwChangeDate;
	}

	public void setPasswChangeDate ( Date passwChangeDate ) {
		this.passwChangeDate = passwChangeDate;
	}

	public Integer getLoginStatus () {
		return loginStatus;
	}

	public void setLoginStatus ( Integer loginStatus ) {
		this.loginStatus = loginStatus;
	}

	public String getRecordStatus () {
		return recordStatus;
	}

	public void setRecordStatus ( String recordStatus ) {
		this.recordStatus = recordStatus;
	}

	public String getInputter () {
		return inputter;
	}

	public void setInputter ( String inputter ) {
		this.inputter = inputter;
	}

	public Date getDateTime () {
		return dateTime;
	}

	public void setDateTime ( Date dateTime ) {
		this.dateTime = dateTime;
	}

	public String getAuthoriser () {
		return authoriser;
	}

	public void setAuthoriser ( String authoriser ) {
		this.authoriser = authoriser;
	}

	public String getCoCode () {
		return coCode;
	}

	public void setCoCode ( String coCode ) {
		this.coCode = coCode;
	}

	public String getDeptCode () {
		return deptCode;
	}

	public void setDeptCode ( String deptCode ) {
		this.deptCode = deptCode;
	}

	public String getTeamcCode () {
		return teamcCode;
	}

	public void setTeamcCode ( String teamcCode ) {
		this.teamcCode = teamcCode;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime ( Date createTime ) {
		this.createTime = createTime;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public Integer getGender () {
		return gender;
	}

	public void setGender ( Integer gender ) {
		this.gender = gender;
	}

	public String getPosition () {
		return position;
	}

	public void setPosition ( String position ) {
		this.position = position;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber ( String phoneNumber ) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelephone () {
		return telephone;
	}

	public void setTelephone ( String telephone ) {
		this.telephone = telephone;
	}

	public Long getReportToUser () {
		return reportToUser;
	}

	public void setReportToUser ( Long reportToUser ) {
		this.reportToUser = reportToUser;
	}

	public Byte getIsHeadReportTo () {
		return isHeadReportTo;
	}

	public void setIsHeadReportTo ( Byte isHeadReportTo ) {
		this.isHeadReportTo = isHeadReportTo;
	}

	public String getProfileImageUrl () {
		return profileImageUrl;
	}

	public void setProfileImageUrl ( String profileImageUrl ) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getSearch () {
		return search;
	}

	public void setSearch ( String search ) {
		this.search = search;
	}

	public Integer getRoleId () {
		return roleId;
	}

	public void setRoleId ( Integer roleId ) {
		this.roleId = roleId;
	}

	public List<Integer> getRoles () {
		return roles;
	}

	public void setRoles ( List<Integer> roles ) {
		this.roles = roles;
	}

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime ( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public Byte getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Byte isDeleted ) {
		this.isDeleted = isDeleted;
	}

	public String getApiKey () {
		return apiKey;
	}

	public void setApiKey ( String apiKey ) {
		this.apiKey = apiKey;
	}

	public Integer getDateCount () {
		return dateCount;
	}

	public void setDateCount ( Integer dateCount ) {
		this.dateCount = dateCount;
	}

	public String getcCompanyName () {
		return cCompanyName;
	}

	public void setcCompanyName ( String cCompanyName ) {
		this.cCompanyName = cCompanyName;
	}

	public String getdName () {
		return dName;
	}

	public void setdName ( String dName ) {
		this.dName = dName;
	}

	public Long getIsActive () {
		return isActive;
	}

	public void setIsActive ( Long isActive ) {
		this.isActive = isActive;
	}

	public String gettName () {
		return tName;
	}

	public void settName ( String tName ) {
		this.tName = tName;
	}

	public List<String> getUaCompanyName () {
		return uaCompanyName;
	}

	public void setUaCompanyName ( List<String> uaCompanyName ) {
		this.uaCompanyName = uaCompanyName;
	}

	public List<String> getAuthCompanyCode () {
		return authCompanyCode;
	}

	public void setAuthCompanyCode ( List<String> authCompanyCode ) {
		this.authCompanyCode = authCompanyCode;
	}

	public Integer getAuthStatus () {
		return authStatus;
	}

	public void setAuthStatus ( Integer authStatus ) {
		this.authStatus = authStatus;
	}

	public String getAuthDesc () {
		return authDesc;
	}

	public Long getLloydsId () {
		return lloydsId;
	}

	public void setLloydsId ( Long lloydsId ) {
		this.lloydsId = lloydsId;
	}

	public String getLloydsPassword () {
		return lloydsPassword;
	}

	public void setLloydsPassword ( String lloydsPassword ) {
		this.lloydsPassword = lloydsPassword;
	}

	public Long getBridgerId () {
		return bridgerId;
	}

	public void setBridgerId ( Long bridgerId ) {
		this.bridgerId = bridgerId;
	}

	public String getBridgerPassword () {
		return bridgerPassword;
	}

	public void setBridgerPassword ( String bridgerPassword ) {
		this.bridgerPassword = bridgerPassword;
	}

	public Long getDowJonesId () {
		return dowJonesId;
	}

	public void setDowJonesId ( Long dowJonesId ) {
		this.dowJonesId = dowJonesId;
	}

	public String getDowJonesPassword () {
		return dowJonesPassword;
	}

	public void setDowJonesPassword ( String dowJonesPassword ) {
		this.dowJonesPassword = dowJonesPassword;
	}

	public Date getConfirmedAt () {
		return confirmedAt;
	}

	public void setConfirmedAt ( Date confirmedAt ) {
		this.confirmedAt = confirmedAt;
	}

	public void setAuthDesc ( String authDesc ) {
		this.authDesc = authDesc;
	}

	public String getUrl () {
		return url;
	}

	public void setUrl ( String url ) {
		this.url = url;
	}

}