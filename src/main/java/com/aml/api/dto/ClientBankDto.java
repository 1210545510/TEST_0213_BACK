package com.aml.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientBankDto extends BaseDto {
	private static final long serialVersionUID = -2375673549643099162L;
	private BigDecimal instnSeqId;
	private Date dataDumpDt;
	private BigDecimal cbEfctvRiskNb;
	private String cbListSrcCd;
	private String cbMatchTx;
	private String busUnitCd;
	private String acctMgrNm;
	private Date lastActvyDt;
	private BigDecimal cbListRiskNb;
	private String cbMatchTypeCd;
	private String prcsngBatchNm;
	private String jrsdcnCd;
	private String busDmnListTx;
	private String search;

	public BigDecimal getInstnSeqId () {
		return instnSeqId;
	}

	public void setInstnSeqId ( BigDecimal instnSeqId ) {
		this.instnSeqId = instnSeqId;
	}

	public Date getDataDumpDt () {
		return dataDumpDt;
	}

	public void setDataDumpDt ( Date dataDumpDt ) {
		this.dataDumpDt = dataDumpDt;
	}

	public BigDecimal getCbEfctvRiskNb () {
		return cbEfctvRiskNb;
	}

	public void setCbEfctvRiskNb ( BigDecimal cbEfctvRiskNb ) {
		this.cbEfctvRiskNb = cbEfctvRiskNb;
	}

	public String getCbListSrcCd () {
		return cbListSrcCd;
	}

	public void setCbListSrcCd ( String cbListSrcCd ) {
		this.cbListSrcCd = cbListSrcCd == null ? null : cbListSrcCd.trim();
	}

	public String getCbMatchTx () {
		return cbMatchTx;
	}

	public void setCbMatchTx ( String cbMatchTx ) {
		this.cbMatchTx = cbMatchTx == null ? null : cbMatchTx.trim();
	}

	public String getBusUnitCd () {
		return busUnitCd;
	}

	public void setBusUnitCd ( String busUnitCd ) {
		this.busUnitCd = busUnitCd == null ? null : busUnitCd.trim();
	}

	public String getAcctMgrNm () {
		return acctMgrNm;
	}

	public void setAcctMgrNm ( String acctMgrNm ) {
		this.acctMgrNm = acctMgrNm == null ? null : acctMgrNm.trim();
	}

	public Date getLastActvyDt () {
		return lastActvyDt;
	}

	public void setLastActvyDt ( Date lastActvyDt ) {
		this.lastActvyDt = lastActvyDt;
	}

	public BigDecimal getCbListRiskNb () {
		return cbListRiskNb;
	}

	public void setCbListRiskNb ( BigDecimal cbListRiskNb ) {
		this.cbListRiskNb = cbListRiskNb;
	}

	public String getCbMatchTypeCd () {
		return cbMatchTypeCd;
	}

	public void setCbMatchTypeCd ( String cbMatchTypeCd ) {
		this.cbMatchTypeCd = cbMatchTypeCd == null ? null : cbMatchTypeCd.trim();
	}

	public String getPrcsngBatchNm () {
		return prcsngBatchNm;
	}

	public void setPrcsngBatchNm ( String prcsngBatchNm ) {
		this.prcsngBatchNm = prcsngBatchNm == null ? null : prcsngBatchNm.trim();
	}

	public String getJrsdcnCd () {
		return jrsdcnCd;
	}

	public void setJrsdcnCd ( String jrsdcnCd ) {
		this.jrsdcnCd = jrsdcnCd == null ? null : jrsdcnCd.trim();
	}

	public String getBusDmnListTx () {
		return busDmnListTx;
	}

	public void setBusDmnListTx ( String busDmnListTx ) {
		this.busDmnListTx = busDmnListTx == null ? null : busDmnListTx.trim();
	}

	public String getSearch () {
		return search;
	}

	public void setSearch ( String search ) {
		this.search = search;
	}
}