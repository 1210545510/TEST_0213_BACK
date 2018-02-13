package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectInstitutionDto extends BaseDto {
	private static final long serialVersionUID = 2346543512486697288L;
	private Long relateId;
	private Long subId;
	private String relationshipList;
	private String relationshipStatus;
	private Date actionDate;
	private Byte isDeleted;

	public Long getRelateId () {
		return relateId;
	}

	public void setRelateId ( Long relateId ) {
		this.relateId = relateId;
	}

	public Long getSubId () {
		return subId;
	}

	public void setSubId ( Long subId ) {
		this.subId = subId;
	}

	public String getRelationshipList () {
		return relationshipList;
	}

	public void setRelationshipList ( String relationshipList ) {
		this.relationshipList = relationshipList == null ? null : relationshipList.trim();
	}

	public String getRelationshipStatus () {
		return relationshipStatus;
	}

	public void setRelationshipStatus ( String relationshipStatus ) {
		this.relationshipStatus = relationshipStatus == null ? null : relationshipStatus.trim();
	}

	public Date getActionDate () {
		return actionDate;
	}

	public void setActionDate ( Date actionDate ) {
		this.actionDate = actionDate;
	}

	public Byte getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Byte isDeleted ) {
		this.isDeleted = isDeleted;
	}
}