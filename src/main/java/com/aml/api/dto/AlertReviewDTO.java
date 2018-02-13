package com.aml.api.dto;

import java.util.List;
import java.util.Set;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlertReviewDTO extends BaseDto {
	private static final long serialVersionUID = -1545080354964471953L;
	private Long id;
	private List<Long> ids;
	/** CaseId */
	private Long caseId;
	/** AlertId */
	private Long alertId;
	/** 原始id */
	private Integer originalId;
	/** 账号id */
	private String accountId;
	/** 客户id */
	private String customerId;
	private List<Long> alertIds;
	/** 账号id */
	private String acctIntrlId;
	/** 账号id集合 */
	private Set<String> accountIds;
	/** 月份 */
	private Integer month;
	//>>>>>>>---发送邮件
	private String subject;
	private String to;
	private String from;
	private Integer controrNumber;
	private String fileUrls;
	private String fileNames;
	private String comment;
	
	private Long control;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public List<Long> getIds () {
		return ids;
	}

	public void setIds ( List<Long> ids ) {
		this.ids = ids;
	}

	public Long getCaseId () {
		return caseId;
	}

	public void setCaseId ( Long caseId ) {
		this.caseId = caseId;
	}

	public Long getAlertId () {
		return alertId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public Integer getOriginalId () {
		return originalId;
	}

	public void setOriginalId ( Integer originalId ) {
		this.originalId = originalId;
	}

	public String getAccountId () {
		return accountId;
	}

	public void setAccountId ( String accountId ) {
		this.accountId = accountId;
	}

	public void setAcctIntrlId ( String acctIntrlId ) {
		this.acctIntrlId = acctIntrlId;
	}

	public List<Long> getAlertIds () {
		return alertIds;
	}

	public void setAlertIds ( List<Long> alertIds ) {
		this.alertIds = alertIds;
	}

	public String getAcctIntrlId () {
		return acctIntrlId;
	}

	public Set<String> getAccountIds () {
		return accountIds;
	}

	public void setAccountIds ( Set<String> accountIds ) {
		this.accountIds = accountIds;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getControrNumber() {
		return controrNumber;
	}

	public void setControrNumber(Integer controrNumber) {
		this.controrNumber = controrNumber;
	}

	public String getFileUrls() {
		return fileUrls;
	}

	public void setFileUrls(String fileUrls) {
		this.fileUrls = fileUrls;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getControl() {
		return control;
	}

	public void setControl(Long control) {
		this.control = control;
	}
}