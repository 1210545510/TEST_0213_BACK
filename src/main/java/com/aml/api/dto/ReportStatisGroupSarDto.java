package com.aml.api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReportStatisGroupSarDto extends BaseDto {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7329317021263778515L;

	/** 创建开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createStartDate;
	
	/** 创建结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createEndDate;
	
	private Long id;

    private Date statisDate;

    private Integer fiuSarCount;

    private Integer fcbSarCount;

    private Integer dctSarCount;

    private Integer ctrSarCount;

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