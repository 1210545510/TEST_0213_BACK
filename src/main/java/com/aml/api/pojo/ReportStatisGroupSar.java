package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReportStatisGroupSar extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7329317021263778515L;

	private Long id;

    private Date statisDate;

    private Integer fiuSarCount;

    private Integer fcbSarCount;

    private Integer dctSarCount;

    private Integer ctrSarCount;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

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

    public Integer getFiuSarCount() {
        return fiuSarCount;
    }

    public void setFiuSarCount(Integer fiuSarCount) {
        this.fiuSarCount = fiuSarCount;
    }

    public Integer getFcbSarCount() {
        return fcbSarCount;
    }

    public void setFcbSarCount(Integer fcbSarCount) {
        this.fcbSarCount = fcbSarCount;
    }

    public Integer getDctSarCount() {
        return dctSarCount;
    }

    public void setDctSarCount(Integer dctSarCount) {
        this.dctSarCount = dctSarCount;
    }

    public Integer getCtrSarCount() {
        return ctrSarCount;
    }

    public void setCtrSarCount(Integer ctrSarCount) {
        this.ctrSarCount = ctrSarCount;
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
}