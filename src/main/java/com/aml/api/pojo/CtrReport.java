package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CtrReport extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3544797573026668076L;

	private Long cid;

    private String filingName;

    private Byte filingType;

    private String filingIndentifier;

    private Long ctrId;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getFilingName() {
        return filingName;
    }

    public void setFilingName(String filingName) {
        this.filingName = filingName == null ? null : filingName.trim();
    }

    public Byte getFilingType() {
        return filingType;
    }

    public void setFilingType(Byte filingType) {
        this.filingType = filingType;
    }

    public String getFilingIndentifier() {
        return filingIndentifier;
    }

    public void setFilingIndentifier(String filingIndentifier) {
        this.filingIndentifier = filingIndentifier == null ? null : filingIndentifier.trim();
    }

    public Long getCtrId() {
        return ctrId;
    }

    public void setCtrId(Long ctrId) {
        this.ctrId = ctrId;
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