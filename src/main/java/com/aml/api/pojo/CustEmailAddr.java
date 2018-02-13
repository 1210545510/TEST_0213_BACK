package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustEmailAddr {
    private String custIntrlId;

    private String emailSeqNb;
    
    private Date dataDumpDt;

    private BigDecimal custEmailSeqId;

    private String emailAddrTx;

    private String srcSysCd;

    private String prcsngBatchNm;

    public String getCustIntrlId() {
		return custIntrlId;
	}

	public String getEmailSeqNb() {
		return emailSeqNb;
	}

	public void setCustIntrlId(String custIntrlId) {
		this.custIntrlId = custIntrlId;
	}

	public void setEmailSeqNb(String emailSeqNb) {
		this.emailSeqNb = emailSeqNb;
	}

	public Date getDataDumpDt() {
        return dataDumpDt;
    }

    public void setDataDumpDt(Date dataDumpDt) {
        this.dataDumpDt = dataDumpDt;
    }

    public BigDecimal getCustEmailSeqId() {
        return custEmailSeqId;
    }

    public void setCustEmailSeqId(BigDecimal custEmailSeqId) {
        this.custEmailSeqId = custEmailSeqId;
    }

    public String getEmailAddrTx() {
        return emailAddrTx;
    }

    public void setEmailAddrTx(String emailAddrTx) {
        this.emailAddrTx = emailAddrTx == null ? null : emailAddrTx.trim();
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
}