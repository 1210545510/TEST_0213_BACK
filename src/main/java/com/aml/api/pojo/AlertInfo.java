package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @className: AlertInfo
 * @description: Alert/Case 关联的KDD_REVIEW实体类
 * @author huangliangbao
 * @date 2018年1月9日 上午9:46:02
 *
 */
@JsonInclude(Include.NON_NULL)
public class AlertInfo extends BasePoJo {
	private static final long serialVersionUID = 3368921277986584968L;

	private Long id;

	private String sysType;

	private Long originalId;

	private Long alertId;

	private Long caseId;

	private String subjectType;

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

	private String caseReason;

	private Date bookDate;

	private String focusType;

	private String highlights;

	private String remark;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private Integer isDeleted;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getSysType () {
		return sysType;
	}

	public void setSysType ( String sysType ) {
		this.sysType = sysType == null ? null : sysType.trim();
	}

	public Long getOriginalId () {
		return originalId;
	}

	public void setOriginalId ( Long originalId ) {
		this.originalId = originalId;
	}

	public Long getAlertId () {
		return alertId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public Long getCaseId () {
		return caseId;
	}

	public void setCaseId ( Long caseId ) {
		this.caseId = caseId;
	}

	public String getSubjectType () {
		return subjectType;
	}

	public void setSubjectType ( String subjectType ) {
		this.subjectType = subjectType == null ? null : subjectType.trim();
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
}