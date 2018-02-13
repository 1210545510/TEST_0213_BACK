package com.aml.api.dto;

import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GrandJurySubpoenaDto extends BaseDto {
	private static final long serialVersionUID = 7312096649927926659L;
	private Long id;
	private String extRequestId;
	private String requestingParty;
	private String name;
	private Integer idNum;
	private String city;
	private String state;
	private String address;
	private String country;
	private String bankName;
	private Integer accountNum;
	private String akaOrDba;
	private String internalBsa;
	private String custStatus;
	private String swiftCode;
	private String legalCounselCompany;
	private String legalCounselName;
	private String fiuSarReferral;
	private Long alertId;
	private Date birthdate;
	private String search;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getExtRequestId () {
		return extRequestId;
	}

	public void setExtRequestId ( String extRequestId ) {
		this.extRequestId = extRequestId == null ? null : extRequestId.trim();
	}

	public String getRequestingParty () {
		return requestingParty;
	}

	public void setRequestingParty ( String requestingParty ) {
		this.requestingParty = requestingParty == null ? null : requestingParty.trim();
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getIdNum () {
		return idNum;
	}

	public void setIdNum ( Integer idNum ) {
		this.idNum = idNum;
	}

	public String getCity () {
		return city;
	}

	public void setCity ( String city ) {
		this.city = city == null ? null : city.trim();
	}

	public String getState () {
		return state;
	}

	public void setState ( String state ) {
		this.state = state == null ? null : state.trim();
	}

	public String getAddress () {
		return address;
	}

	public void setAddress ( String address ) {
		this.address = address == null ? null : address.trim();
	}

	public String getCountry () {
		return country;
	}

	public void setCountry ( String country ) {
		this.country = country == null ? null : country.trim();
	}

	public String getBankName () {
		return bankName;
	}

	public void setBankName ( String bankName ) {
		this.bankName = bankName == null ? null : bankName.trim();
	}

	public Integer getAccountNum () {
		return accountNum;
	}

	public void setAccountNum ( Integer accountNum ) {
		this.accountNum = accountNum;
	}

	public String getAkaOrDba () {
		return akaOrDba;
	}

	public void setAkaOrDba ( String akaOrDba ) {
		this.akaOrDba = akaOrDba == null ? null : akaOrDba.trim();
	}

	public String getInternalBsa () {
		return internalBsa;
	}

	public void setInternalBsa ( String internalBsa ) {
		this.internalBsa = internalBsa == null ? null : internalBsa.trim();
	}

	public String getCustStatus () {
		return custStatus;
	}

	public void setCustStatus ( String custStatus ) {
		this.custStatus = custStatus == null ? null : custStatus.trim();
	}

	public String getSwiftCode () {
		return swiftCode;
	}

	public void setSwiftCode ( String swiftCode ) {
		this.swiftCode = swiftCode == null ? null : swiftCode.trim();
	}

	public String getLegalCounselCompany () {
		return legalCounselCompany;
	}

	public void setLegalCounselCompany ( String legalCounselCompany ) {
		this.legalCounselCompany = legalCounselCompany == null ? null : legalCounselCompany.trim();
	}

	public String getLegalCounselName () {
		return legalCounselName;
	}

	public void setLegalCounselName ( String legalCounselName ) {
		this.legalCounselName = legalCounselName == null ? null : legalCounselName.trim();
	}

	public String getFiuSarReferral () {
		return fiuSarReferral;
	}

	public void setFiuSarReferral ( String fiuSarReferral ) {
		this.fiuSarReferral = fiuSarReferral == null ? null : fiuSarReferral.trim();
	}

	public Long getAlertId () {
		return alertId;
	}

	public void setAlertId ( Long alertId ) {
		this.alertId = alertId;
	}

	public Date getBirthdate () {
		return birthdate;
	}

	public void setBirthdate ( Date birthdate ) {
		this.birthdate = birthdate;
	}

	public String getSearch () {
		return search;
	}

	public void setSearch ( String search ) {
		this.search = search;
	}

}