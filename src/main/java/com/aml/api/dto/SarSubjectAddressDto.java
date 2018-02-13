package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectAddressDto extends BaseDto {
	private static final long serialVersionUID = -3258658355781431856L;
	private Long subAddrId;
	private Long subId;
	private String address;
	private String city;
	private Byte state;
	private Byte zipCode;
	private Byte country;
	private Date createTime;
	private Date updateTime;
	private Byte isDeleted;

	public Long getSubAddrId () {
		return subAddrId;
	}

	public void setSubAddrId ( Long subAddrId ) {
		this.subAddrId = subAddrId;
	}

	public Long getSubId () {
		return subId;
	}

	public void setSubId ( Long subId ) {
		this.subId = subId;
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

	public Byte getCountry () {
		return country;
	}

	public void setCountry ( Byte country ) {
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

	public Byte getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Byte isDeleted ) {
		this.isDeleted = isDeleted;
	}
}