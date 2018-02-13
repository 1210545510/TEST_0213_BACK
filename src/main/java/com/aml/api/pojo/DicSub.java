package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DicSub extends BasePoJo{
	private static final long serialVersionUID = 1L;
    private Long dicSid;

    private Long dicId;

    private String dicCode;

    private String dicKey;

    private String dicValue;

    private Integer sortIndex;

    private String remark;

    private Integer idDeleted;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
    
    
    
    private String flowId;// 流程ID
    private Integer isConfigured;//是否已配置

    public Long getDicSid() {
        return dicSid;
    }

    public void setDicSid(Long dicSid) {
        this.dicSid = dicSid;
    }

    public Long getDicId() {
        return dicId;
    }

    public void setDicId(Long dicId) {
        this.dicId = dicId;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey == null ? null : dicKey.trim();
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(Integer idDeleted) {
        this.idDeleted = idDeleted;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Integer getIsConfigured() {
		return isConfigured;
	}

	public void setIsConfigured(Integer isConfigured) {
		this.isConfigured = isConfigured;
	}

	public String getFlowId () {
		return flowId;
	}

	public void setFlowId ( String flowId ) {
		this.flowId = flowId;
	}

}