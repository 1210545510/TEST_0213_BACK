package com.aml.api.pojo;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TemplateRules extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5112842848306317032L;

	private Integer id;

    private String tride;

    private String type;

    private String spName;

    private String suspTypeSchedule;

    private String prePostEdd;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTride() {
        return tride;
    }

    public void setTride(String tride) {
        this.tride = tride == null ? null : tride.trim();
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

    public String getSuspTypeSchedule() {
        return suspTypeSchedule;
    }

    public void setSuspTypeSchedule(String suspTypeSchedule) {
        this.suspTypeSchedule = suspTypeSchedule == null ? null : suspTypeSchedule.trim();
    }

    public String getPrePostEdd() {
        return prePostEdd;
    }

    public void setPrePostEdd(String prePostEdd) {
        this.prePostEdd = prePostEdd == null ? null : prePostEdd.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}