package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;

public class NoticeRuleDTO  extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer warnType;
    
    private String remindType;

    private Integer days;

    private Integer num;

    private Integer intervalDays;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(Integer intervalDays) {
        this.intervalDays = intervalDays;
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

	public String getRemindType () {
		return remindType;
	}

	public void setRemindType ( String remindType ) {
		this.remindType = remindType;
	}
    
}