package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserDefindRules extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9110012868149304311L;

	private Integer id;

    private String type;

    private String spName;

    private String supType;

    private String prePostDoo;

    private String lastModifyOper;

    private Date lastModifyDate;

    private Date createDate;

    private String createBy;

    private String lastStatus;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName == null ? null : spName.trim();
    }

    public String getSupType() {
        return supType;
    }

    public void setSupType(String supType) {
        this.supType = supType == null ? null : supType.trim();
    }

    public String getPrePostDoo() {
        return prePostDoo;
    }

    public void setPrePostDoo(String prePostDoo) {
        this.prePostDoo = prePostDoo == null ? null : prePostDoo.trim();
    }

    public String getLastModifyOper() {
        return lastModifyOper;
    }

    public void setLastModifyOper(String lastModifyOper) {
        this.lastModifyOper = lastModifyOper == null ? null : lastModifyOper.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus == null ? null : lastStatus.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}