package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CtrFinancialInstitutionDto extends BaseDto {
	private static final long serialVersionUID = -4082195755312764302L;
	private Long financialId;
	private Long cid;
	private Byte financialInstitutionType;
	private String financialInstitutionOther;
	private Byte primaryFederalRegulator;
	private Byte casinoType;
	private String casinoOther;
	private String legalName;
	private String alternateName;
	private String tin;
	private Byte tinType;
	private String address;
	private String city;
	private Byte state;
	private Byte zipCode;
	private String financialIdType;
	private String financialIdNumber;
	private String contactOffice;
	private String phoneNumber;
	private String ext;
	private String dateFiled;
	private Date createTime;
	private Date updateTime;
	private Byte isDeleted;

	public Long getFinancialId () {
		return financialId;
	}

	public void setFinancialId ( Long financialId ) {
		this.financialId = financialId;
	}

	public Long getCid () {
		return cid;
	}

	public void setCid ( Long cid ) {
		this.cid = cid;
	}

	public Byte getFinancialInstitutionType () {
		return financialInstitutionType;
	}

	public void setFinancialInstitutionType ( Byte financialInstitutionType ) {
		this.financialInstitutionType = financialInstitutionType;
	}

	public String getFinancialInstitutionOther () {
		return financialInstitutionOther;
	}

	public void setFinancialInstitutionOther ( String financialInstitutionOther ) {
		this.financialInstitutionOther = financialInstitutionOther == null ? null : financialInstitutionOther.trim();
	}

	public Byte getPrimaryFederalRegulator () {
		return primaryFederalRegulator;
	}

	public void setPrimaryFederalRegulator ( Byte primaryFederalRegulator ) {
		this.primaryFederalRegulator = primaryFederalRegulator;
	}

	public Byte getCasinoType () {
		return casinoType;
	}

	public void setCasinoType ( Byte casinoType ) {
		this.casinoType = casinoType;
	}

	public String getCasinoOther () {
		return casinoOther;
	}

	public void setCasinoOther ( String casinoOther ) {
		this.casinoOther = casinoOther == null ? null : casinoOther.trim();
	}

	public String getLegalName () {
		return legalName;
	}

	public void setLegalName ( String legalName ) {
		this.legalName = legalName == null ? null : legalName.trim();
	}

	public String getAlternateName () {
		return alternateName;
	}

	public void setAlternateName ( String alternateName ) {
		this.alternateName = alternateName == null ? null : alternateName.trim();
	}

	public String getTin () {
		return tin;
	}

	public void setTin ( String tin ) {
		this.tin = tin == null ? null : tin.trim();
	}

	public Byte getTinType () {
		return tinType;
	}

	public void setTinType ( Byte tinType ) {
		this.tinType = tinType;
	}

	public String getAddress () {
		return address;
	}

	public void setAddress ( String address ) {
		this.address = address == null ? null : address.trim();
	}

	public String getCity () {
		return city;
	}

	public void setCity ( String city ) {
		this.city = city == null ? null : city.trim();
	}

	public Byte getState () {
		return state;
	}

	public void setState ( Byte state ) {
		this.state = state;
	}

	public Byte getZipCode () {
		return zipCode;
	}

	public void setZipCode ( Byte zipCode ) {
		this.zipCode = zipCode;
	}

	public String getFinancialIdType () {
		return financialIdType;
	}

	public void setFinancialIdType ( String financialIdType ) {
		this.financialIdType = financialIdType == null ? null : financialIdType.trim();
	}

	public String getFinancialIdNumber () {
		return financialIdNumber;
	}

	public void setFinancialIdNumber ( String financialIdNumber ) {
		this.financialIdNumber = financialIdNumber == null ? null : financialIdNumber.trim();
	}

	public String getContactOffice () {
		return contactOffice;
	}

	public void setContactOffice ( String contactOffice ) {
		this.contactOffice = contactOffice == null ? null : contactOffice.trim();
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber ( String phoneNumber ) {
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public String getExt () {
		return ext;
	}

	public void setExt ( String ext ) {
		this.ext = ext == null ? null : ext.trim();
	}

	public String getDateFiled () {
		return dateFiled;
	}

	public void setDateFiled ( String dateFiled ) {
		this.dateFiled = dateFiled == null ? null : dateFiled.trim();
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

	public Byte getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Byte isDeleted ) {
		this.isDeleted = isDeleted;
	}
}