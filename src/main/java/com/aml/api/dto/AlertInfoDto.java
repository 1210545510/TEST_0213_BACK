package com.aml.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlertInfoDto extends BaseDto {
	private static final long serialVersionUID = -9102894169837433346L;
	/** 创建开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createStartDate;
	/** 结束开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueStartDate;
	/** 创建结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createEndDate;
	/** 结束结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueEndDate;
	private String alertId;
	private String sysType;
	private String dataType;
	private Integer originalId;
	private String subjectType;
	private String alertType;
	private Byte status;
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
	private String customerId;
	private String domain;
	private String riskClass;
	private String linkCase;
	private String caseReason;
	private Date bookDate;
	private Byte isLock;
	private String orderId;
	private String taskId;
	private String remark;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	private Byte isDeleted;
	private String focusType;
	private String highlights;
	private List<Long> alertIds;
	private Date lastUpdatedDate;
	private Date businessDate;
	/** 最小金额 */
	private BigDecimal amountMin;
	/** 最大金额 */
	private BigDecimal amountMax;
	/** 最小交易综合 */
	private Integer volumeMin;
	/** 最大交易综合 */
	private Integer volumeMax;
	/** 最小得分 */
	private Integer scoreMin;
	/** 最大得分 */
	private Integer scoreMax;
	/** 搜索 */
	private String search;
	private String accountId;
	/** 原因 */
	private String alertReason;
	/** 文件路径 */
	private String fileUrl;
	private List<Long> alertIdList;
	/** 模块类型(type=alert或sar) */
	private String type;
	/** Dashboard查询条件 */
	/** 小组编码 */
	private String org;
	private List<Map<String, Object>> list;
	private String reviewDate;
	private String roleId;
	private String roleName;
	/** 用户集合字符串 */
	private String param;

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

	public String getDataType () {
		return dataType;
	}

	public void setDataType ( String dataType ) {
		this.dataType = dataType == null ? null : dataType.trim();
	}

	public Integer getOriginalId () {
		return originalId;
	}

	public void setOriginalId ( Integer originalId ) {
		this.originalId = originalId;
	}

	public String getSubjectType () {
		return subjectType;
	}

	public void setSubjectType ( String subjectType ) {
		this.subjectType = subjectType == null ? null : subjectType.trim();
	}

	public String getAlertType () {
		return alertType;
	}

	public void setAlertType ( String alertType ) {
		this.alertType = alertType == null ? null : alertType.trim();
	}

	public Byte getStatus () {
		return status;
	}

	public void setStatus ( Byte status ) {
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

	public String getCustomerId () {
		return customerId;
	}

	public void setCustomerId ( String customerId ) {
		this.customerId = customerId == null ? null : customerId.trim();
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

	public String getCaseReason () {
		return caseReason;
	}

	public void setCaseReason ( String caseReason ) {
		this.caseReason = caseReason == null ? null : caseReason.trim();
	}

	public Date getBookDate () {
		return bookDate;
	}

	public void setBookDate ( Date bookDate ) {
		this.bookDate = bookDate;
	}

	public Byte getIsLock () {
		return isLock;
	}

	public void setIsLock ( Byte isLock ) {
		this.isLock = isLock;
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

	public Byte getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Byte isDeleted ) {
		this.isDeleted = isDeleted;
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

	public List<Long> getAlertIds () {
		return alertIds;
	}

	public void setAlertIds ( List<Long> alertIds ) {
		this.alertIds = alertIds;
	}

	public Date getLastUpdatedDate () {
		return lastUpdatedDate;
	}

	public Date getBusinessDate () {
		return businessDate;
	}

	public void setLastUpdatedDate ( Date lastUpdatedDate ) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void setBusinessDate ( Date businessDate ) {
		this.businessDate = businessDate;
	}

	public Date getCreateStartDate () {
		return createStartDate;
	}

	public void setCreateStartDate ( Date createStartDate ) {
		this.createStartDate = createStartDate;
	}

	public Date getDueStartDate () {
		return dueStartDate;
	}

	public void setDueStartDate ( Date dueStartDate ) {
		this.dueStartDate = dueStartDate;
	}

	public Date getCreateEndDate () {
		return createEndDate;
	}

	public void setCreateEndDate ( Date createEndDate ) {
		this.createEndDate = createEndDate;
	}

	public Date getDueEndDate () {
		return dueEndDate;
	}

	public void setDueEndDate ( Date dueEndDate ) {
		this.dueEndDate = dueEndDate;
	}

	public BigDecimal getAmountMin () {
		return amountMin;
	}

	public void setAmountMin ( BigDecimal amountMin ) {
		this.amountMin = amountMin;
	}

	public BigDecimal getAmountMax () {
		return amountMax;
	}

	public void setAmountMax ( BigDecimal amountMax ) {
		this.amountMax = amountMax;
	}

	public Integer getVolumeMin () {
		return volumeMin;
	}

	public void setVolumeMin ( Integer volumeMin ) {
		this.volumeMin = volumeMin;
	}

	public Integer getVolumeMax () {
		return volumeMax;
	}

	public void setVolumeMax ( Integer volumeMax ) {
		this.volumeMax = volumeMax;
	}

	public Integer getScoreMin () {
		return scoreMin;
	}

	public void setScoreMin ( Integer scoreMin ) {
		this.scoreMin = scoreMin;
	}

	public Integer getScoreMax () {
		return scoreMax;
	}

	public void setScoreMax ( Integer scoreMax ) {
		this.scoreMax = scoreMax;
	}

	public String getSearch () {
		return search;
	}

	public void setSearch ( String search ) {
		this.search = search;
	}

	public String getAccountId () {
		return accountId;
	}

	public void setAccountId ( String accountId ) {
		this.accountId = accountId;
	}

	public String getAlertReason () {
		return alertReason;
	}

	public void setAlertReason ( String alertReason ) {
		this.alertReason = alertReason;
	}

	public String getFileUrl () {
		return fileUrl;
	}

	public void setFileUrl ( String fileUrl ) {
		this.fileUrl = fileUrl;
	}

	public List<Long> getAlertIdList () {
		return alertIdList;
	}

	public void setAlertIdList ( List<Long> alertIdList ) {
		this.alertIdList = alertIdList;
	}

	public String getOrg () {
		return org;
	}

	public void setOrg ( String org ) {
		this.org = org;
	}

	public List<Map<String, Object>> getList () {
		return list;
	}

	public void setList ( List<Map<String, Object>> list ) {
		this.list = list;
	}

	public String getReviewDate () {
		return reviewDate;
	}

	public void setReviewDate ( String reviewDate ) {
		this.reviewDate = reviewDate;
	}

	public String getParam () {
		return param;
	}

	public void setParam ( String param ) {
		this.param = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoleId () {
		return roleId;
	}

	public void setRoleId ( String roleId ) {
		this.roleId = roleId;
	}

	public String getRoleName () {
		return roleName;
	}

	public void setRoleName ( String roleName ) {
		this.roleName = roleName;
	}
	
}