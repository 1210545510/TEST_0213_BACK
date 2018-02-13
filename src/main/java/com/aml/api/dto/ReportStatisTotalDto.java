package com.aml.api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReportStatisTotalDto extends BaseDto {
    /**
	 * 
	 */
	private static final long serialVersionUID = -447777808053772084L;

	/** 创建开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createStartDate;
	
	/** 创建结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createEndDate;
	
	private Long id;

    private Date statisDate;

    private Integer alertCount;

    private Integer caseCount;

    private Integer sarCount;

    private Integer sarQaCaseCount;

    private Integer sarAnalystCaseCount;

    private Integer createCaseCount;

    private Integer oneSarCaseCount;

    private Integer twoSarCaseCount;

    private Integer threeSarCaseCount;

    private Integer reversedAlertWaivedCount;

    private Integer caseSarCount;

    private Integer caseReasonableCount;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    private String type;
    
    private String str;
    
    public Date getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(Date createStartDate) {
		this.createStartDate = createStartDate;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStatisDate() {
        return statisDate;
    }

    public void setStatisDate(Date statisDate) {
        this.statisDate = statisDate;
    }

    public Integer getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(Integer alertCount) {
        this.alertCount = alertCount;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public Integer getSarCount() {
        return sarCount;
    }

    public void setSarCount(Integer sarCount) {
        this.sarCount = sarCount;
    }

    public Integer getSarQaCaseCount() {
        return sarQaCaseCount;
    }

    public void setSarQaCaseCount(Integer sarQaCaseCount) {
        this.sarQaCaseCount = sarQaCaseCount;
    }

    public Integer getSarAnalystCaseCount() {
        return sarAnalystCaseCount;
    }

    public void setSarAnalystCaseCount(Integer sarAnalystCaseCount) {
        this.sarAnalystCaseCount = sarAnalystCaseCount;
    }

    public Integer getCreateCaseCount() {
        return createCaseCount;
    }

    public void setCreateCaseCount(Integer createCaseCount) {
        this.createCaseCount = createCaseCount;
    }

    public Integer getOneSarCaseCount() {
        return oneSarCaseCount;
    }

    public void setOneSarCaseCount(Integer oneSarCaseCount) {
        this.oneSarCaseCount = oneSarCaseCount;
    }

    public Integer getTwoSarCaseCount() {
        return twoSarCaseCount;
    }

    public void setTwoSarCaseCount(Integer twoSarCaseCount) {
        this.twoSarCaseCount = twoSarCaseCount;
    }

    public Integer getThreeSarCaseCount() {
        return threeSarCaseCount;
    }

    public void setThreeSarCaseCount(Integer threeSarCaseCount) {
        this.threeSarCaseCount = threeSarCaseCount;
    }

    public Integer getReversedAlertWaivedCount() {
        return reversedAlertWaivedCount;
    }

    public void setReversedAlertWaivedCount(Integer reversedAlertWaivedCount) {
        this.reversedAlertWaivedCount = reversedAlertWaivedCount;
    }

    public Integer getCaseSarCount() {
        return caseSarCount;
    }

    public void setCaseSarCount(Integer caseSarCount) {
        this.caseSarCount = caseSarCount;
    }

    public Integer getCaseReasonableCount() {
        return caseReasonableCount;
    }

    public void setCaseReasonableCount(Integer caseReasonableCount) {
        this.caseReasonableCount = caseReasonableCount;
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
   
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}