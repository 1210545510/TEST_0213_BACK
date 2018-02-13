package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarFinancialInstitutionBranchDto extends BaseDto {
	private static final long serialVersionUID = 7863283396921471203L;
	private Long financialBranchId;
	private Long financialId;
	private Byte transationRole;
	private String branchAddress;
	private String city;
	private Integer rssdNumber;
	private Integer state;
	private Integer zipCode;
	private Integer country;
	private Date createTime;
	private Date updateTime;
	private Integer isDeleted;

	public Long getFinancialBranchId () {
		return financialBranchId;
	}

	public void setFinancialBranchId ( Long financialBranchId ) {
		this.financialBranchId = financialBranchId;
	}

	public Long getFinancialId () {
		return financialId;
	}

	public void setFinancialId ( Long financialId ) {
		this.financialId = financialId;
	}

	public Byte getTransationRole () {
		return transationRole;
	}

	public void setTransationRole ( Byte transationRole ) {
		this.transationRole = transationRole;
	}

	public String getBranchAddress () {
		return branchAddress;
	}

	public void setBranchAddress ( String branchAddress ) {
		this.branchAddress = branchAddress == null ? null : branchAddress.trim();
	}

	public String getCity () {
		return city;
	}

	public void setCity ( String city ) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getRssdNumber () {
		return rssdNumber;
	}

	public void setRssdNumber ( Integer rssdNumber ) {
		this.rssdNumber = rssdNumber;
	}

	public Integer getState () {
		return state;
	}

	public void setState ( Integer state ) {
		this.state = state;
	}

	public Integer getZipCode () {
		return zipCode;
	}

	public void setZipCode ( Integer zipCode ) {
		this.zipCode = zipCode;
	}

	public Integer getCountry () {
		return country;
	}

	public void setCountry ( Integer country ) {
		this.country = country;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime ( Date createTime ) {
		this.createTime = createTime;
	}

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime ( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}
}