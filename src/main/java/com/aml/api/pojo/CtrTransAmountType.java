package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CtrTransAmountType extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2639667129741247081L;

	private Long transId;

    private Long cid;

    private Date transDate;

    private Byte transType;

    private String inDeposit;

    private String inPayment;

    private String inCurrencyReceived;

    private String inNegotiableInstrument;

    private String inCurrencyExchange;

    private String inPrepaidAccess;

    private String inOtherInstrument;

    private String inCurrencyWager;

    private String inBillsInserted;

    private String inOtherName;

    private String inOtherValue;

    private String inTotalCash;

    private String outWithdrawal;

    private String outAdvance;

    private String outCurrencyPaid;

    private String outNegotiableInstrument;

    private String outCurrencyExchange;

    private String outPrepaidAccess;

    private String outRedemptionInstrument;

    private String outPaymentWager;

    private String outTravelExpenses;

    private String outTournamentPayment;

    private String outOtherName;

    private String outOtherValue;

    private String outTotalCash;

    private String inForeignCash;

    private Byte inForeignCountry;

    private String outForeignCash;

    private Byte outForeignCountry;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Byte getTransType() {
        return transType;
    }

    public void setTransType(Byte transType) {
        this.transType = transType;
    }

    public String getInDeposit() {
        return inDeposit;
    }

    public void setInDeposit(String inDeposit) {
        this.inDeposit = inDeposit == null ? null : inDeposit.trim();
    }

    public String getInPayment() {
        return inPayment;
    }

    public void setInPayment(String inPayment) {
        this.inPayment = inPayment == null ? null : inPayment.trim();
    }

    public String getInCurrencyReceived() {
        return inCurrencyReceived;
    }

    public void setInCurrencyReceived(String inCurrencyReceived) {
        this.inCurrencyReceived = inCurrencyReceived == null ? null : inCurrencyReceived.trim();
    }

    public String getInNegotiableInstrument() {
        return inNegotiableInstrument;
    }

    public void setInNegotiableInstrument(String inNegotiableInstrument) {
        this.inNegotiableInstrument = inNegotiableInstrument == null ? null : inNegotiableInstrument.trim();
    }

    public String getInCurrencyExchange() {
        return inCurrencyExchange;
    }

    public void setInCurrencyExchange(String inCurrencyExchange) {
        this.inCurrencyExchange = inCurrencyExchange == null ? null : inCurrencyExchange.trim();
    }

    public String getInPrepaidAccess() {
        return inPrepaidAccess;
    }

    public void setInPrepaidAccess(String inPrepaidAccess) {
        this.inPrepaidAccess = inPrepaidAccess == null ? null : inPrepaidAccess.trim();
    }

    public String getInOtherInstrument() {
        return inOtherInstrument;
    }

    public void setInOtherInstrument(String inOtherInstrument) {
        this.inOtherInstrument = inOtherInstrument == null ? null : inOtherInstrument.trim();
    }

    public String getInCurrencyWager() {
        return inCurrencyWager;
    }

    public void setInCurrencyWager(String inCurrencyWager) {
        this.inCurrencyWager = inCurrencyWager == null ? null : inCurrencyWager.trim();
    }

    public String getInBillsInserted() {
        return inBillsInserted;
    }

    public void setInBillsInserted(String inBillsInserted) {
        this.inBillsInserted = inBillsInserted == null ? null : inBillsInserted.trim();
    }

    public String getInOtherName() {
        return inOtherName;
    }

    public void setInOtherName(String inOtherName) {
        this.inOtherName = inOtherName == null ? null : inOtherName.trim();
    }

    public String getInOtherValue() {
        return inOtherValue;
    }

    public void setInOtherValue(String inOtherValue) {
        this.inOtherValue = inOtherValue == null ? null : inOtherValue.trim();
    }

    public String getInTotalCash() {
        return inTotalCash;
    }

    public void setInTotalCash(String inTotalCash) {
        this.inTotalCash = inTotalCash == null ? null : inTotalCash.trim();
    }

    public String getOutWithdrawal() {
        return outWithdrawal;
    }

    public void setOutWithdrawal(String outWithdrawal) {
        this.outWithdrawal = outWithdrawal == null ? null : outWithdrawal.trim();
    }

    public String getOutAdvance() {
        return outAdvance;
    }

    public void setOutAdvance(String outAdvance) {
        this.outAdvance = outAdvance == null ? null : outAdvance.trim();
    }

    public String getOutCurrencyPaid() {
        return outCurrencyPaid;
    }

    public void setOutCurrencyPaid(String outCurrencyPaid) {
        this.outCurrencyPaid = outCurrencyPaid == null ? null : outCurrencyPaid.trim();
    }

    public String getOutNegotiableInstrument() {
        return outNegotiableInstrument;
    }

    public void setOutNegotiableInstrument(String outNegotiableInstrument) {
        this.outNegotiableInstrument = outNegotiableInstrument == null ? null : outNegotiableInstrument.trim();
    }

    public String getOutCurrencyExchange() {
        return outCurrencyExchange;
    }

    public void setOutCurrencyExchange(String outCurrencyExchange) {
        this.outCurrencyExchange = outCurrencyExchange == null ? null : outCurrencyExchange.trim();
    }

    public String getOutPrepaidAccess() {
        return outPrepaidAccess;
    }

    public void setOutPrepaidAccess(String outPrepaidAccess) {
        this.outPrepaidAccess = outPrepaidAccess == null ? null : outPrepaidAccess.trim();
    }

    public String getOutRedemptionInstrument() {
        return outRedemptionInstrument;
    }

    public void setOutRedemptionInstrument(String outRedemptionInstrument) {
        this.outRedemptionInstrument = outRedemptionInstrument == null ? null : outRedemptionInstrument.trim();
    }

    public String getOutPaymentWager() {
        return outPaymentWager;
    }

    public void setOutPaymentWager(String outPaymentWager) {
        this.outPaymentWager = outPaymentWager == null ? null : outPaymentWager.trim();
    }

    public String getOutTravelExpenses() {
        return outTravelExpenses;
    }

    public void setOutTravelExpenses(String outTravelExpenses) {
        this.outTravelExpenses = outTravelExpenses == null ? null : outTravelExpenses.trim();
    }

    public String getOutTournamentPayment() {
        return outTournamentPayment;
    }

    public void setOutTournamentPayment(String outTournamentPayment) {
        this.outTournamentPayment = outTournamentPayment == null ? null : outTournamentPayment.trim();
    }

    public String getOutOtherName() {
        return outOtherName;
    }

    public void setOutOtherName(String outOtherName) {
        this.outOtherName = outOtherName == null ? null : outOtherName.trim();
    }

    public String getOutOtherValue() {
        return outOtherValue;
    }

    public void setOutOtherValue(String outOtherValue) {
        this.outOtherValue = outOtherValue == null ? null : outOtherValue.trim();
    }

    public String getOutTotalCash() {
        return outTotalCash;
    }

    public void setOutTotalCash(String outTotalCash) {
        this.outTotalCash = outTotalCash == null ? null : outTotalCash.trim();
    }

    public String getInForeignCash() {
        return inForeignCash;
    }

    public void setInForeignCash(String inForeignCash) {
        this.inForeignCash = inForeignCash == null ? null : inForeignCash.trim();
    }

    public Byte getInForeignCountry() {
        return inForeignCountry;
    }

    public void setInForeignCountry(Byte inForeignCountry) {
        this.inForeignCountry = inForeignCountry;
    }

    public String getOutForeignCash() {
        return outForeignCash;
    }

    public void setOutForeignCash(String outForeignCash) {
        this.outForeignCash = outForeignCash == null ? null : outForeignCash.trim();
    }

    public Byte getOutForeignCountry() {
        return outForeignCountry;
    }

    public void setOutForeignCountry(Byte outForeignCountry) {
        this.outForeignCountry = outForeignCountry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}