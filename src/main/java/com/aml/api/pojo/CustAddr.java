package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustAddr {
    private String custIntrlId;

    private String addrSeqNb;
    
    private Date dataDumpDt;

    private BigDecimal custAddrSeqId;

    private String addrUsageCd;

    private String addrCityNm;

    private String addrStateCd;

    private String addrPostlCd;

    private String addrCntryCd;

    private String addrStrtLine1Tx;

    private String addrStrtLine2Tx;

    private String addrStrtLine3Tx;

    private String addrStrtLine4Tx;

    private String addrStrtLine5Tx;

    private String addrStrtLine6Tx;

    private String addrRgnNm;

    private String srcSysCd;

    private String prcsngBatchNm;

    private String mailHndlngInstr;

    public String getCustIntrlId() {
		return custIntrlId;
	}

	public String getAddrSeqNb() {
		return addrSeqNb;
	}

	public void setCustIntrlId(String custIntrlId) {
		this.custIntrlId = custIntrlId;
	}

	public void setAddrSeqNb(String addrSeqNb) {
		this.addrSeqNb = addrSeqNb;
	}

	public Date getDataDumpDt() {
        return dataDumpDt;
    }

    public void setDataDumpDt(Date dataDumpDt) {
        this.dataDumpDt = dataDumpDt;
    }

    public BigDecimal getCustAddrSeqId() {
        return custAddrSeqId;
    }

    public void setCustAddrSeqId(BigDecimal custAddrSeqId) {
        this.custAddrSeqId = custAddrSeqId;
    }

    public String getAddrUsageCd() {
        return addrUsageCd;
    }

    public void setAddrUsageCd(String addrUsageCd) {
        this.addrUsageCd = addrUsageCd == null ? null : addrUsageCd.trim();
    }

    public String getAddrCityNm() {
        return addrCityNm;
    }

    public void setAddrCityNm(String addrCityNm) {
        this.addrCityNm = addrCityNm == null ? null : addrCityNm.trim();
    }

    public String getAddrStateCd() {
        return addrStateCd;
    }

    public void setAddrStateCd(String addrStateCd) {
        this.addrStateCd = addrStateCd == null ? null : addrStateCd.trim();
    }

    public String getAddrPostlCd() {
        return addrPostlCd;
    }

    public void setAddrPostlCd(String addrPostlCd) {
        this.addrPostlCd = addrPostlCd == null ? null : addrPostlCd.trim();
    }

    public String getAddrCntryCd() {
        return addrCntryCd;
    }

    public void setAddrCntryCd(String addrCntryCd) {
        this.addrCntryCd = addrCntryCd == null ? null : addrCntryCd.trim();
    }

    public String getAddrStrtLine1Tx() {
        return addrStrtLine1Tx;
    }

    public void setAddrStrtLine1Tx(String addrStrtLine1Tx) {
        this.addrStrtLine1Tx = addrStrtLine1Tx == null ? null : addrStrtLine1Tx.trim();
    }

    public String getAddrStrtLine2Tx() {
        return addrStrtLine2Tx;
    }

    public void setAddrStrtLine2Tx(String addrStrtLine2Tx) {
        this.addrStrtLine2Tx = addrStrtLine2Tx == null ? null : addrStrtLine2Tx.trim();
    }

    public String getAddrStrtLine3Tx() {
        return addrStrtLine3Tx;
    }

    public void setAddrStrtLine3Tx(String addrStrtLine3Tx) {
        this.addrStrtLine3Tx = addrStrtLine3Tx == null ? null : addrStrtLine3Tx.trim();
    }

    public String getAddrStrtLine4Tx() {
        return addrStrtLine4Tx;
    }

    public void setAddrStrtLine4Tx(String addrStrtLine4Tx) {
        this.addrStrtLine4Tx = addrStrtLine4Tx == null ? null : addrStrtLine4Tx.trim();
    }

    public String getAddrStrtLine5Tx() {
        return addrStrtLine5Tx;
    }

    public void setAddrStrtLine5Tx(String addrStrtLine5Tx) {
        this.addrStrtLine5Tx = addrStrtLine5Tx == null ? null : addrStrtLine5Tx.trim();
    }

    public String getAddrStrtLine6Tx() {
        return addrStrtLine6Tx;
    }

    public void setAddrStrtLine6Tx(String addrStrtLine6Tx) {
        this.addrStrtLine6Tx = addrStrtLine6Tx == null ? null : addrStrtLine6Tx.trim();
    }

    public String getAddrRgnNm() {
        return addrRgnNm;
    }

    public void setAddrRgnNm(String addrRgnNm) {
        this.addrRgnNm = addrRgnNm == null ? null : addrRgnNm.trim();
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

    public String getMailHndlngInstr() {
        return mailHndlngInstr;
    }

    public void setMailHndlngInstr(String mailHndlngInstr) {
        this.mailHndlngInstr = mailHndlngInstr == null ? null : mailHndlngInstr.trim();
    }
}