package com.aml.api.pojo;

import java.util.Date;
import java.util.List;

import com.aml.api.dto.Roles;
import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Users extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1603249908442759208L;

	private Long userId;//自增id，主要是用于关系绑定

    private String userCode;//用户编码

    private String userName;//用户名

    private String name;//用户姓名
    
    private String firstName;
    
    private String lastName;
    
    private Integer gender; //性别

    private String companyCode;//公司编码

    private String departmentCode;//部门编码

    private String teamCode;//小组编码

    private String position;//职位

    private String phoneNumber;//手机号码

    private String telephone;//固定电话

    private Long reportToUser;//汇报对象

    private Integer isHeadReportTo;//是否直接上司汇报，0：否，1:是

    private String email;//邮箱

    private String profileImageUrl;//profile图片url

    private Date passwordValidity;//密码有效期，日期格式

    private Date startDateProfile;//开始日期

    private Date endDateProfile;//结束日期

    private Integer timeOutMinutes;//超时时长

    private Integer attempts;//尝试次数限制

    private String companyRestr;

    private String smsGroupId;//角色权限组

    private String signOnOffLog;//是否开启登陆日志 Y/N

    private Integer attemptsSince;//尝试次数限制，登陆成功清0

    private Date dateLastSignOn;//最后登陆时间

    private String password;//密码

    private Date passwChangeDate;//密码修改时间

    private Integer loginStatus;//登陆状态,0_有效，1_无效

    private String recordStatus;

    private String inputter;//输入信息人

    private Date dateTime;//用户创建时间

    private String authoriser;//授权人

    private String coCode;//公司编码

    private String deptCode;//部门编码

    private String teamcCode;//小组编码

    private Date createTime;//数据创建时间

    private Date updateTime;//更新时间
    
    private Long isActive;
    
    private Integer authStatus;//授权状态:0：授权中，1：通过，2：不通过
    
    private String authDesc;//授权备注

    private Integer isDeleted;//删除标识,0_可用，1_已删除不可用

    private Long lloydsId;

    private String lloydsPassword;

    private Long bridgerId;

    private String bridgerPassword;

    private Long dowJonesId;

    private String dowJonesPassword;

    private String token;
    
    private Integer dateCount;
    private Date confirmedAt;

    private List<Roles> roles; //角色
    private String cCompanyName;//公司
    private String companyName;//公司
    private String dName;//部门
    private String departmentName;//部门
    
    private String tName;//组织
    private String teamName;//组织
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getReportToUser() {
		return reportToUser;
	}

	public void setReportToUser(Long reportToUser) {
		this.reportToUser = reportToUser;
	}

	public Integer getIsHeadReportTo() {
		return isHeadReportTo;
	}

	public void setIsHeadReportTo(Integer isHeadReportTo) {
		this.isHeadReportTo = isHeadReportTo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public Date getPasswordValidity() {
		return passwordValidity;
	}

	public void setPasswordValidity(Date passwordValidity) {
		this.passwordValidity = passwordValidity;
	}

	public Date getStartDateProfile() {
		return startDateProfile;
	}

	public void setStartDateProfile(Date startDateProfile) {
		this.startDateProfile = startDateProfile;
	}

	public Date getEndDateProfile() {
		return endDateProfile;
	}

	public void setEndDateProfile(Date endDateProfile) {
		this.endDateProfile = endDateProfile;
	}

	public Integer getTimeOutMinutes() {
		return timeOutMinutes;
	}

	public void setTimeOutMinutes(Integer timeOutMinutes) {
		this.timeOutMinutes = timeOutMinutes;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public String getCompanyRestr() {
		return companyRestr;
	}

	public void setCompanyRestr(String companyRestr) {
		this.companyRestr = companyRestr;
	}

	public String getSmsGroupId() {
		return smsGroupId;
	}

	public void setSmsGroupId(String smsGroupId) {
		this.smsGroupId = smsGroupId;
	}

	public String getSignOnOffLog() {
		return signOnOffLog;
	}

	public void setSignOnOffLog(String signOnOffLog) {
		this.signOnOffLog = signOnOffLog;
	}

	public Integer getAttemptsSince() {
		return attemptsSince;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(Date confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public void setAttemptsSince(Integer attemptsSince) {
		this.attemptsSince = attemptsSince;
	}

	public Date getDateLastSignOn() {
		return dateLastSignOn;
	}

	public void setDateLastSignOn(Date dateLastSignOn) {
		this.dateLastSignOn = dateLastSignOn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswChangeDate() {
		return passwChangeDate;
	}

	public void setPasswChangeDate(Date passwChangeDate) {
		this.passwChangeDate = passwChangeDate;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getInputter() {
		return inputter;
	}

	public void setInputter(String inputter) {
		this.inputter = inputter;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getAuthoriser() {
		return authoriser;
	}

	public void setAuthoriser(String authoriser) {
		this.authoriser = authoriser;
	}

	public String getCoCode() {
		return coCode;
	}

	public void setCoCode(String coCode) {
		this.coCode = coCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTeamcCode() {
		return teamcCode;
	}

	public void setTeamcCode(String teamcCode) {
		this.teamcCode = teamcCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getcCompanyName() {
		return cCompanyName;
	}

	public void setcCompanyName(String cCompanyName) {
		this.cCompanyName = cCompanyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public Long getLloydsId() {
		return lloydsId;
	}

	public void setLloydsId(Long lloydsId) {
		this.lloydsId = lloydsId;
	}

	public String getLloydsPassword() {
		return lloydsPassword;
	}

	public void setLloydsPassword(String lloydsPassword) {
		this.lloydsPassword = lloydsPassword;
	}

	public Long getBridgerId() {
		return bridgerId;
	}

	public void setBridgerId(Long bridgerId) {
		this.bridgerId = bridgerId;
	}

	public String getBridgerPassword() {
		return bridgerPassword;
	}

	public void setBridgerPassword(String bridgerPassword) {
		this.bridgerPassword = bridgerPassword;
	}

	public Long getDowJonesId() {
		return dowJonesId;
	}

	public void setDowJonesId(Long dowJonesId) {
		this.dowJonesId = dowJonesId;
	}

	public String getDowJonesPassword() {
		return dowJonesPassword;
	}

	public void setDowJonesPassword(String dowJonesPassword) {
		this.dowJonesPassword = dowJonesPassword;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}