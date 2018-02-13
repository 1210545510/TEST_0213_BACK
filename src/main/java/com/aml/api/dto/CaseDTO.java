package com.aml.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @className: CaseDTO
 * @description:
 * @author shun
 * @date 2017年12月29日
 *
 */
@JsonInclude(Include.NON_NULL)
public class CaseDTO extends BaseDto {
	private static final long serialVersionUID = -1545080354964471953L;
	private String caseId;
	private Long alertId;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	/** 结束时间 */
	private Date dueDate;
	/** Alert状态，流程状态码，对应数据字典编码 */
	private Integer status;
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
	/** 数据来源，0：自动生成;1：External User Request;2：Grand Jury Subpoena;3：314A; 4: 手动创建Alert; 5:手动创建的case */
	private Integer dataType;
	/** Case类型, 对应数据字典编码 */
	private String caseType;
	/** 主体类型 */
	private String subjectType;
	/** 主体 */
	private String subject;
	private String customerId;
	private String accountId;
	/** 文件路径 */
	private String fileUrl;
	private String fileName;
	/** 状态(0-待办；1-已处理) */
	private Integer state;
	/** 是否为管理员创建（0-不是；1-是） */
	private Integer isAdminCreate;
	private String caseReason;
	private String geography;
	private String scenario;
	/** 指派任务人 */
	private String assigner;
	/** SAR ID */
	private Long sarId;
	/** Report Number */
	private String reportNumber;
	/** Case报SAR次数 */
	private Integer sarNum;
	private List<Long> alertIdList;
	private List<Long> caseIdList;

	public String getCaseId () {
		return caseId;
	}

	public void setCaseId ( String caseId ) {
		this.caseId = caseId;
	}


	public Long getAlertId () {
		return alertId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public Date getCreateStartDate () {
		return createStartDate;
	}

	public Date getDueStartDate () {
		return dueStartDate;
	}

	public Date getCreateEndDate () {
		return createEndDate;
	}

	public Date getDueEndDate () {
		return dueEndDate;
	}

	public void setCreateStartDate ( Date createStartDate ) {
		this.createStartDate = createStartDate;
	}

	public void setDueStartDate ( Date dueStartDate ) {
		this.dueStartDate = dueStartDate;
	}

	public void setCreateEndDate ( Date createEndDate ) {
		this.createEndDate = createEndDate;
	}

	public void setDueEndDate ( Date dueEndDate ) {
		this.dueEndDate = dueEndDate;
	}

	public Integer getStatus () {
		return status;
	}

	public BigDecimal getAmountMin () {
		return amountMin;
	}

	public BigDecimal getAmountMax () {
		return amountMax;
	}

	public Integer getScoreMin () {
		return scoreMin;
	}

	public Integer getScoreMax () {
		return scoreMax;
	}

	public void setStatus ( Integer status ) {
		this.status = status;
	}

	public void setAmountMin ( BigDecimal amountMin ) {
		this.amountMin = amountMin;
	}

	public void setAmountMax ( BigDecimal amountMax ) {
		this.amountMax = amountMax;
	}

	public Integer getVolumeMin () {
		return volumeMin;
	}

	public Integer getVolumeMax () {
		return volumeMax;
	}

	public void setVolumeMin ( Integer volumeMin ) {
		this.volumeMin = volumeMin;
	}

	public void setVolumeMax ( Integer volumeMax ) {
		this.volumeMax = volumeMax;
	}

	public void setScoreMin ( Integer scoreMin ) {
		this.scoreMin = scoreMin;
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

	public Date getDueDate () {
		return dueDate;
	}

	public String getSubjectType () {
		return subjectType;
	}

	public String getSubject () {
		return subject;
	}

	public String getCustomerId () {
		return customerId;
	}

	public String getAccountId () {
		return accountId;
	}

	public String getFileUrl () {
		return fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setDueDate ( Date dueDate ) {
		this.dueDate = dueDate;
	}

	public String getCaseType () {
		return caseType;
	}

	public void setCaseType ( String caseType ) {
		this.caseType = caseType;
	}

	public void setSubjectType ( String subjectType ) {
		this.subjectType = subjectType;
	}

	public void setSubject ( String subject ) {
		this.subject = subject;
	}

	public void setCustomerId ( String customerId ) {
		this.customerId = customerId;
	}

	public void setAccountId ( String accountId ) {
		this.accountId = accountId;
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

	public String getCaseReason () {
		return caseReason;
	}

	public void setCaseReason ( String caseReason ) {
		this.caseReason = caseReason;
	}

	public String getGeography () {
		return geography;
	}

	public String getScenario () {
		return scenario;
	}

	public void setGeography ( String geography ) {
		this.geography = geography;
	}

	public void setScenario ( String scenario ) {
		this.scenario = scenario;
	}

	/**
	 * 查询alert列表用到
	 * 
	 * @return
	 * @date 2017年12月14日
	 */
	public String[] getUserNames () {
		if (StringUtils.isBlank(super.getUserName())) {
			return null;
		}
		return super.getUserName().split(",");
	}

	public Integer getState () {
		return state;
	}

	public void setState ( Integer state ) {
		this.state = state;
	}

	public String getAssigner () {
		return assigner;
	}

	public void setAssigner ( String assigner ) {
		this.assigner = assigner;
	}

	public Integer getDataType () {
		return dataType;
	}

	public void setDataType ( Integer dataType ) {
		this.dataType = dataType;
	}

	public List<Long> getCaseIdList () {
		return caseIdList;
	}

	public void setCaseIdList ( List<Long> caseIdList ) {
		this.caseIdList = caseIdList;
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