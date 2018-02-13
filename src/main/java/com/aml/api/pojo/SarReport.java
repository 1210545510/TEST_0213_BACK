package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarReport extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1808031164580877009L;

	private Long rid;

    private String filingName;

    private String filingType;

    private String filingIndentifier;

    private String attachment;

    private Long caseId;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getFilingName() {
        return filingName;
    }

    public void setFilingName(String filingName) {
        this.filingName = filingName == null ? null : filingName.trim();
    }

    public String getFilingType() {
        return filingType;
    }

    public void setFilingType(String filingType) {
        this.filingType = filingType;
    }

    public String getFilingIndentifier() {
        return filingIndentifier;
    }

    public void setFilingIndentifier(String filingIndentifier) {
        this.filingIndentifier = filingIndentifier == null ? null : filingIndentifier.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
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