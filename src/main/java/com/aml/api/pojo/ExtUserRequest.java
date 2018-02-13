package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ExtUserRequest extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7560200848423852004L;

	private Integer extRequestId;

    private String requestingParty;

    private String name;

    private Date birthdate;

    private Integer idNum;

    private String address;

    private String city;

    private String state;

    private String country;

    private String bankName;

    private String akaOrDba;

    private String internalBsa;

    private String custStatus;

    private String swiftCode;

    private String fiuSarReferral;

    private Long alertId;

    private Integer accountNumber;

	public Integer getExtRequestId() {
		return extRequestId;
	}

	public void setExtRequestId(Integer extRequestId) {
		this.extRequestId = extRequestId;
	}

	public String getRequestingParty() {
		return requestingParty;
	}

	public void setRequestingParty(String requestingParty) {
		this.requestingParty = requestingParty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAkaOrDba() {
		return akaOrDba;
	}

	public void setAkaOrDba(String akaOrDba) {
		this.akaOrDba = akaOrDba;
	}

	public String getInternalBsa() {
		return internalBsa;
	}

	public void setInternalBsa(String internalBsa) {
		this.internalBsa = internalBsa;
	}

	public String getCustStatus() {
		return custStatus;
	}

	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getFiuSarReferral() {
		return fiuSarReferral;
	}

	public void setFiuSarReferral(String fiuSarReferral) {
		this.fiuSarReferral = fiuSarReferral;
	}

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
}