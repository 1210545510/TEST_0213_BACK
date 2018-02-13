package com.aml.api.dto.transaction.wire;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TransactionDetails extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3444876344718375338L;
	
	private String name;
	
	private String risk;
	
	private String address;
	
	private String fiId;
	
	private String match;
	
	private String originatorToBeneficiaryTrusted;
	
	private String originatorToThirdPartyBeneficiaryTrusted;
	
	private String beneficiaryToThirdPartyOriginatorTrusted;
	
	private String thirdPartyOriginatorToThirdPartyBeneficiary;
	
	private String accountId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFiId() {
		return fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getOriginatorToBeneficiaryTrusted() {
		return originatorToBeneficiaryTrusted;
	}

	public void setOriginatorToBeneficiaryTrusted(String originatorToBeneficiaryTrusted) {
		this.originatorToBeneficiaryTrusted = originatorToBeneficiaryTrusted;
	}

	public String getOriginatorToThirdPartyBeneficiaryTrusted() {
		return originatorToThirdPartyBeneficiaryTrusted;
	}

	public void setOriginatorToThirdPartyBeneficiaryTrusted(String originatorToThirdPartyBeneficiaryTrusted) {
		this.originatorToThirdPartyBeneficiaryTrusted = originatorToThirdPartyBeneficiaryTrusted;
	}

	public String getBeneficiaryToThirdPartyOriginatorTrusted() {
		return beneficiaryToThirdPartyOriginatorTrusted;
	}

	public void setBeneficiaryToThirdPartyOriginatorTrusted(String beneficiaryToThirdPartyOriginatorTrusted) {
		this.beneficiaryToThirdPartyOriginatorTrusted = beneficiaryToThirdPartyOriginatorTrusted;
	}

	public String getThirdPartyOriginatorToThirdPartyBeneficiary() {
		return thirdPartyOriginatorToThirdPartyBeneficiary;
	}

	public void setThirdPartyOriginatorToThirdPartyBeneficiary(String thirdPartyOriginatorToThirdPartyBeneficiary) {
		this.thirdPartyOriginatorToThirdPartyBeneficiary = thirdPartyOriginatorToThirdPartyBeneficiary;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}