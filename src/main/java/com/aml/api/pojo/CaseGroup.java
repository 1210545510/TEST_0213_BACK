package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CaseGroup extends BasePoJo {
	private static final long serialVersionUID = -7362703514433790097L;

	private Long caseId;

    private String alertId;

    private String sysType;

    private Integer dataType;

    private String subjectType;

    private String caseType;

    private Integer status;
    
    private Integer preStatus;

    private Integer score;

    private String subject;

    private BigDecimal amount;

    private Integer volume;

    private String geography;

    private String scenario;

    private Date dueDate;

    private Date creationDate;

    private String focus;

    private String jurisdiction;

    private String domain;

    private String riskClass;

    private String linkCase;

    private Date bookDate;

    private String focusType;

    private String highlights;

    private String caseReason;

    private String customerId;

    private String accountId;

    private String fileUrl;
    
    private String fileName;

    private String ownerOrg;

    private String roleId;

    private String orderId;

    private String taskId;

    private Date businessDate;

    private Date reviewStartDate;

    private Date closedDate;

    private Integer isAdminCreate;
    
	private Long sarId;
	
	private String reportNumber;
	
	private Integer sarNum;

    private String remark;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer isDeleted;

	private Integer roleNum;

	private String userName;

	private List<Long> alertIds;

	private Date lastUpdatedDate;

	private Integer value;

	private String name;

	private String actorId;

	private String variable;

	private String num;

	private Integer month;

	private Integer year;
	private Integer day;

	private String january;

	private String february;

	private String march;

	private String april;

	private String may;

	private String june;

	private String july;

	private String august;

	private String september;

	private String october;

	private String november;

	private String december;

	private String org;

	public Long getCaseId () {
		return caseId;
	}

	public void setCaseId ( Long caseId ) {
		this.caseId = caseId;
	}

	public String getAlertId () {
		return alertId;
	}

	public void setAlertId ( String alertId ) {
		this.alertId = alertId;
	}

	public String getSysType () {
		return sysType;
	}

	public void setSysType ( String sysType ) {
		this.sysType = sysType == null ? null : sysType.trim();
	}

	public Integer getDataType () {
		return dataType;
	}

	public void setDataType ( Integer dataType ) {
		this.dataType = dataType;
	}

	public String getSubjectType () {
		return subjectType;
	}

	public void setSubjectType ( String subjectType ) {
		this.subjectType = subjectType == null ? null : subjectType.trim();
	}

	public String getCaseType () {
		return caseType;
	}

	public void setCaseType ( String caseType ) {
		this.caseType = caseType;
	}

	public Integer getStatus () {
		return status;
	}

	public void setStatus ( Integer status ) {
		this.status = status;
	}

	public Integer getScore () {
		return score;
	}

	public void setScore ( Integer score ) {
		this.score = score;
	}

	public String getSubject () {
		return subject;
	}

	public void setSubject ( String subject ) {
		this.subject = subject == null ? null : subject.trim();
	}

	public Integer getPreStatus () {
		return preStatus;
	}

	public void setPreStatus ( Integer preStatus ) {
		this.preStatus = preStatus;
	}

	public BigDecimal getAmount () {
		return amount;
	}

	public void setAmount ( BigDecimal amount ) {
		this.amount = amount;
	}

	public Integer getVolume () {
		return volume;
	}

	public void setVolume ( Integer volume ) {
		this.volume = volume;
	}

	public String getGeography () {
		return geography;
	}

	public void setGeography ( String geography ) {
		this.geography = geography == null ? null : geography.trim();
	}

	public String getScenario () {
		return scenario;
	}

	public void setScenario ( String scenario ) {
		this.scenario = scenario == null ? null : scenario.trim();
	}

	public Date getDueDate () {
		return dueDate;
	}

	public void setDueDate ( Date dueDate ) {
		this.dueDate = dueDate;
	}

	public Date getCreationDate () {
		return creationDate;
	}

	public void setCreationDate ( Date creationDate ) {
		this.creationDate = creationDate;
	}

	public String getFocus () {
		return focus;
	}

	public void setFocus ( String focus ) {
		this.focus = focus == null ? null : focus.trim();
	}

	public String getJurisdiction () {
		return jurisdiction;
	}

	public void setJurisdiction ( String jurisdiction ) {
		this.jurisdiction = jurisdiction == null ? null : jurisdiction.trim();
	}

	public String getDomain () {
		return domain;
	}

	public void setDomain ( String domain ) {
		this.domain = domain == null ? null : domain.trim();
	}

	public String getRiskClass () {
		return riskClass;
	}

	public void setRiskClass ( String riskClass ) {
		this.riskClass = riskClass == null ? null : riskClass.trim();
	}

	public String getLinkCase () {
		return linkCase;
	}

	public void setLinkCase ( String linkCase ) {
		this.linkCase = linkCase == null ? null : linkCase.trim();
	}

	public Date getBookDate () {
		return bookDate;
	}

	public void setBookDate ( Date bookDate ) {
		this.bookDate = bookDate;
	}

	public String getFocusType () {
		return focusType;
	}

	public void setFocusType ( String focusType ) {
		this.focusType = focusType == null ? null : focusType.trim();
	}

	public String getHighlights () {
		return highlights;
	}

	public void setHighlights ( String highlights ) {
		this.highlights = highlights == null ? null : highlights.trim();
	}

	public String getCaseReason () {
		return caseReason;
	}

	public void setCaseReason ( String caseReason ) {
		this.caseReason = caseReason == null ? null : caseReason.trim();
	}

	public String getCustomerId () {
		return customerId;
	}

	public void setCustomerId ( String customerId ) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getAccountId () {
		return accountId;
	}

	public void setAccountId ( String accountId ) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	public String getFileUrl () {
		return fileUrl;
	}

	public void setFileUrl ( String fileUrl ) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getOwnerOrg () {
		return ownerOrg;
	}

	public void setOwnerOrg ( String ownerOrg ) {
		this.ownerOrg = ownerOrg == null ? null : ownerOrg.trim();
	}

	public String getRoleId () {
		return roleId;
	}

	public void setRoleId ( String roleId ) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public Integer getRoleNum () {
		return roleNum;
	}

	public void setRoleNum ( Integer roleNum ) {
		this.roleNum = roleNum;
	}

	public Date getBusinessDate () {
		return businessDate;
	}

	public Date getReviewStartDate () {
		return reviewStartDate;
	}

	public Date getClosedDate () {
		return closedDate;
	}

	public void setBusinessDate ( Date businessDate ) {
		this.businessDate = businessDate;
	}

	public void setReviewStartDate ( Date reviewStartDate ) {
		this.reviewStartDate = reviewStartDate;
	}

	public void setClosedDate ( Date closedDate ) {
		this.closedDate = closedDate;
	}

	public String getOrderId () {
		return orderId;
	}

	public void setOrderId ( String orderId ) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getTaskId () {
		return taskId;
	}

	public void setTaskId ( String taskId ) {
		this.taskId = taskId == null ? null : taskId.trim();
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

	public String getUserName () {
		return userName;
	}

	public void setUserName ( String userName ) {
		this.userName = userName;
	}

	public List<Long> getAlertIds () {
		return alertIds;
	}

	public void setAlertIds ( List<Long> alertIds ) {
		this.alertIds = alertIds;
	}

	public Date getLastUpdatedDate () {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate ( Date lastUpdatedDate ) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Integer getValue () {
		return value;
	}

	public void setValue ( Integer value ) {
		this.value = value;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public String getActorId () {
		return actorId;
	}

	public void setActorId ( String actorId ) {
		this.actorId = actorId;
	}

	public String getVariable () {
		return variable;
	}

	public void setVariable ( String variable ) {
		this.variable = variable;
	}

	public String getNum () {
		return num;
	}

	public void setNum ( String num ) {
		this.num = num;
	}

	public Integer getMonth () {
		return month;
	}

	public void setMonth ( Integer month ) {
		this.month = month;
	}

	public Integer getYear () {
		return year;
	}

	public void setYear ( Integer year ) {
		this.year = year;
	}

	public Integer getDay () {
		return day;
	}

	public void setDay ( Integer day ) {
		this.day = day;
	}

	public String getJanuary() {
		return january;
	}

	public void setJanuary(String january) {
		this.january = january;
	}

	public String getFebruary() {
		return february;
	}

	public void setFebruary(String february) {
		this.february = february;
	}

	public String getMarch() {
		return march;
	}

	public void setMarch(String march) {
		this.march = march;
	}

	public String getApril() {
		return april;
	}

	public void setApril(String april) {
		this.april = april;
	}

	public String getMay() {
		return may;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public String getJune() {
		return june;
	}

	public void setJune(String june) {
		this.june = june;
	}

	public String getJuly() {
		return july;
	}

	public void setJuly(String july) {
		this.july = july;
	}

	public String getAugust() {
		return august;
	}

	public void setAugust(String august) {
		this.august = august;
	}

	public String getSeptember() {
		return september;
	}

	public void setSeptember(String september) {
		this.september = september;
	}

	public String getOctober() {
		return october;
	}

	public void setOctober(String october) {
		this.october = october;
	}

	public String getNovember() {
		return november;
	}

	public void setNovember(String november) {
		this.november = november;
	}

	public String getDecember() {
		return december;
	}

	public void setDecember(String december) {
		this.december = december;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Integer getIsAdminCreate () {
		return isAdminCreate;
	}

	public void setIsAdminCreate ( Integer isAdminCreate ) {
		this.isAdminCreate = isAdminCreate;
	}

	public Long getSarId () {
		return sarId;
	}

	public void setSarId ( Long sarId ) {
		this.sarId = sarId;
	}

	public String getReportNumber () {
		return reportNumber;
	}

	public void setReportNumber ( String reportNumber ) {
		this.reportNumber = reportNumber;
	}

	public Integer getSarNum () {
		return sarNum;
	}

	public void setSarNum ( Integer sarNum ) {
		this.sarNum = sarNum;
	}
	
}