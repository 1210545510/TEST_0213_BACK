package com.aml.api.dto.transaction.wire;

import java.math.BigDecimal;
import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Transaction extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3444876344718375338L;
	
	private String transactionReferenceId;
	
	private String typeOrSource;
	
	private BigDecimal sendAmount;
	
	private String thirdPartyPassThrough;
	
	private String bankToBankInstructions;
	
	private String originatorToBeneficiaryInstructions;
	
	private Date date;
	
	private BigDecimal base;
	
	private Integer receivingAmount;
	
	private String trustedTransaction;
	
	private String ipAddress;

	public String getTransactionReferenceId() {
		return transactionReferenceId;
	}

	public void setTransactionReferenceId(String transactionReferenceId) {
		this.transactionReferenceId = transactionReferenceId;
	}

	public String getTypeOrSource() {
		return typeOrSource;
	}

	public void setTypeOrSource(String typeOrSource) {
		this.typeOrSource = typeOrSource;
	}

	public BigDecimal getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(BigDecimal sendAmount) {
		this.sendAmount = sendAmount;
	}

	public String getThirdPartyPassThrough() {
		return thirdPartyPassThrough;
	}

	public void setThirdPartyPassThrough(String thirdPartyPassThrough) {
		this.thirdPartyPassThrough = thirdPartyPassThrough;
	}

	public String getBankToBankInstructions() {
		return bankToBankInstructions;
	}

	public void setBankToBankInstructions(String bankToBankInstructions) {
		this.bankToBankInstructions = bankToBankInstructions;
	}

	public String getOriginatorToBeneficiaryInstructions() {
		return originatorToBeneficiaryInstructions;
	}

	public void setOriginatorToBeneficiaryInstructions(String originatorToBeneficiaryInstructions) {
		this.originatorToBeneficiaryInstructions = originatorToBeneficiaryInstructions;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public Integer getReceivingAmount() {
		return receivingAmount;
	}

	public void setReceivingAmount(Integer receivingAmount) {
		this.receivingAmount = receivingAmount;
	}

	public String getTrustedTransaction() {
		return trustedTransaction;
	}

	public void setTrustedTransaction(String trustedTransaction) {
		this.trustedTransaction = trustedTransaction;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}