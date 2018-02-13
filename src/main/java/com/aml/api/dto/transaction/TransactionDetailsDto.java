package com.aml.api.dto.transaction;

import com.aml.api.dto.transaction.wire.Transaction;
import com.aml.api.dto.transaction.wire.TransactionDetails;
import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TransactionDetailsDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3444876344718375338L;
	
	private Transaction transaction;
	
	private TransactionDetails sendFi;
	
	private TransactionDetails receivingFi;
	
	private TransactionDetails originator;
	
	private TransactionDetails beneficiary;
	
	private TransactionDetails thirdPartyOriginator;
	
	private TransactionDetails thirdPartyBeneficiary;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionDetails getSendFi() {
		return sendFi;
	}

	public void setSendFi(TransactionDetails sendFi) {
		this.sendFi = sendFi;
	}

	public TransactionDetails getReceivingFi() {
		return receivingFi;
	}

	public void setReceivingFi(TransactionDetails receivingFi) {
		this.receivingFi = receivingFi;
	}

	public TransactionDetails getOriginator() {
		return originator;
	}

	public void setOriginator(TransactionDetails originator) {
		this.originator = originator;
	}

	public TransactionDetails getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(TransactionDetails beneficiary) {
		this.beneficiary = beneficiary;
	}

	public TransactionDetails getThirdPartyOriginator() {
		return thirdPartyOriginator;
	}

	public void setThirdPartyOriginator(TransactionDetails thirdPartyOriginator) {
		this.thirdPartyOriginator = thirdPartyOriginator;
	}

	public TransactionDetails getThirdPartyBeneficiary() {
		return thirdPartyBeneficiary;
	}

	public void setThirdPartyBeneficiary(TransactionDetails thirdPartyBeneficiary) {
		this.thirdPartyBeneficiary = thirdPartyBeneficiary;
	}
}