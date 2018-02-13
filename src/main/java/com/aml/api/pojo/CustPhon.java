package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustPhon {
    private String custIntrlId;

    private String phonSeqNb;
    
    private Date dataDumpDt;

    private BigDecimal custPhonSeqId;

    private String phonUsageCd;

    private String phonNb;

    private String phonExtNb;

    private String srcSysCd;

    private String prcsngBatchNm;

    private String cntryOfPhon;

    public String getCustIntrlId() {
		return custIntrlId;
	}

	public String getPhonSeqNb() {
		return phonSeqNb;
	}

	public void setCustIntrlId(String custIntrlId) {
		this.custIntrlId = custIntrlId;
	}

	public void setPhonSeqNb(String phonSeqNb) {
		this.phonSeqNb = phonSeqNb;
	}

	public Date getDataDumpDt() {
        return dataDumpDt;
    }

    public void setDataDumpDt(Date dataDumpDt) {
        this.dataDumpDt = dataDumpDt;
    }

    public BigDecimal getCustPhonSeqId() {
        return custPhonSeqId;
    }

    public void setCustPhonSeqId(BigDecimal custPhonSeqId) {
        this.custPhonSeqId = custPhonSeqId;
    }

    public String getPhonUsageCd() {
        return phonUsageCd;
    }

    public void setPhonUsageCd(String phonUsageCd) {
        this.phonUsageCd = phonUsageCd == null ? null : phonUsageCd.trim();
    }

    public String getPhonNb() {
        return phonNb;
    }

    public void setPhonNb(String phonNb) {
        this.phonNb = phonNb == null ? null : phonNb.trim();
    }

    public String getPhonExtNb() {
        return phonExtNb;
    }

    public void setPhonExtNb(String phonExtNb) {
        this.phonExtNb = phonExtNb == null ? null : phonExtNb.trim();
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd == null ? null : srcSysCd.trim();
    }

    public String getPrcsngBatchNm() {
        return prcsngBatchNm;
    }

    public void setPrcsngBatchNm(String prcsngBatchNm) {
        this.prcsngBatchNm = prcsngBatchNm == null ? null : prcsngBatchNm.trim();
    }

    public String getCntryOfPhon() {
        return cntryOfPhon;
    }

    public void setCntryOfPhon(String cntryOfPhon) {
        this.cntryOfPhon = cntryOfPhon == null ? null : cntryOfPhon.trim();
    }
}