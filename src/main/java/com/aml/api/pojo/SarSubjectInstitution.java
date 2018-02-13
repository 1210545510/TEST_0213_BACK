package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectInstitution extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1453168919323246672L;

	private Long relateId;

    private Long subId;

    private String relationshipList;

    private String relationshipStatus;

    private Date actionDate;

    private Byte isDeleted;

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getRelationshipList() {
        return relationshipList;
    }

    public void setRelationshipList(String relationshipList) {
        this.relationshipList = relationshipList == null ? null : relationshipList.trim();
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus == null ? null : relationshipStatus.trim();
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}