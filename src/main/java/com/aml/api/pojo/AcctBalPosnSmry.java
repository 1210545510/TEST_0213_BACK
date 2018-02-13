package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AcctBalPosnSmry {
    private String acctIntrlId;

    private Date dataDumpDt;
    
    private BigDecimal acctBalSeqId;

    private BigDecimal netWrthAm;

    private BigDecimal availPayAm;

    private BigDecimal eqtyMktvalLngAm;

    private BigDecimal eqtyMktvalShrtAm;

    private BigDecimal mrgnEqtyPt;

    private BigDecimal lowPricdScrtyAm;

    private BigDecimal optnMktvalLngAm;

    private BigDecimal optnMktvalShrtAm;

    private BigDecimal optnMrgnRqmtAm;

    private BigDecimal eqtyMktvalShrtNoboxAm;

    private BigDecimal nkdCallAsgmtRiskAm;

    private BigDecimal nkdPutAsgmtRiskAm;

    private BigDecimal nkdPutAsgmtValAm;

    private BigDecimal totOptnAsgmtRiskAm;

    private BigDecimal lngOptn2moExprnCt;

    private BigDecimal lngOptn6moExprnCt;

    private BigDecimal lngOptn3mo6moExprnCt;

    private BigDecimal nkdOptn2moExprnCt;

    private BigDecimal nkdOptn6moExprnCt;

    private BigDecimal nkdOptn3mo6moExprnCt;

    private String hiCncScrtyIntrlId;

    private BigDecimal hiCncScrtyValAm;

    private BigDecimal hiCncScrtyUnitQt;

    private BigDecimal hiCncScrtyEqtyPt;

    private String hiCncShrtScrtyIntrlId;

    private BigDecimal hiCncShrtScrtyValAm;

    private BigDecimal hiCncShrtScrtyUnitQt;

    private String hiCncSctrNm;

    private BigDecimal hiCncSctrValAm;

    private String hiCncNkdOptnUndScrtyId;

    private String hiCncLngOptnUndScrtyId;

    private BigDecimal hiCncNkdOptnCntrQt;

    private BigDecimal hiCncLngOptnCntrQt;

    private BigDecimal totNkdOptnCntrQt;

    private BigDecimal totLngOptnCntrQt;

    private BigDecimal hiCncOptnMktvalLngAm;

    private String hhAcctGrpId;

    private BigDecimal mrgnBalAm;

    private BigDecimal cashBalAm;

    private BigDecimal lngMrgnMktvalAm;

    private BigDecimal shrtMrgnMktvalAm;

    private BigDecimal totCashScrtyValAm;

    private BigDecimal mnyMktFundBalAm;

    private BigDecimal mrgnEqtyAm;

    private BigDecimal mfundBalAm;

    private BigDecimal bondBalAm;

    private BigDecimal totDebtAm;

    private BigDecimal uncolBalAm;

    private BigDecimal ovrDrftBalAm;

    private BigDecimal lastOvrDrftNegBalAm;

    private BigDecimal lastOvrDrftDurtnDyCt;

    private Date lastOvrDrftNegBalDt;

    private String srcSysCd;

    private Date cstm1Dt;

    private Date cstm2Dt;

    private Date cstm3Dt;

    private BigDecimal cstm1Rl;

    private BigDecimal cstm2Rl;

    private BigDecimal cstm3Rl;

    private String cstm1Tx;

    private String cstm2Tx;

    private String cstm3Tx;

    private String cstm4Tx;

    private String cstm5Tx;

    private BigDecimal cltrlValAm;

    private BigDecimal cltrlRqmtAm;

    private String prcsngBatchNm;

    private BigDecimal adjMrgnDbtAm;

    private BigDecimal netWrthRptngAm;

    private BigDecimal availPayRptngAm;

    private BigDecimal mrgnBalRptngAm;

    private BigDecimal mrgnEqtyRptngAm;

    private BigDecimal cashBalRptngAm;

    private BigDecimal lastOvrDrftNegBalRptngAm;

    private BigDecimal totCashScrtyValRptngAm;

    private BigDecimal uncolBalRptngAm;

    private BigDecimal eqtyMktvalShrtRptngAm;

    private BigDecimal eqtyMktvalLngRptngAm;

    private BigDecimal lngMrgnMktvalRptngAm;

    private BigDecimal shrtMrgnMktvalRptngAm;

    private BigDecimal mnyMktFundBalRptngAm;

    private BigDecimal optnMrgnRqmtRptngAm;

    private BigDecimal optnMktvalShrtRptngAm;

    private BigDecimal optnMktvalLngRptngAm;

    private BigDecimal ovrDrftBalRptngAm;

    private BigDecimal cltrlValRptngAm;

    private BigDecimal cltrlRqmtRptngAm;

    private BigDecimal adjMrgnDbtRptngAm;

    private BigDecimal totDebtRptngAm;

    private BigDecimal feeAm;

    private BigDecimal suprCmsnAm;

    private BigDecimal cashValRptngAm;

    private BigDecimal cashValBaseAm;

    private BigDecimal acctGrossAssetsRptngAm;

    private BigDecimal acctGrossAssetsBaseAm;

    private BigDecimal ldgrAm;

    private BigDecimal ldgrRptngAm;

    private String netWrthAmRngCd;

    private String availPayAmRngCd;

    private BigDecimal lowPricdScrtyBasePr;

    public String getAcctIntrlId() {
		return acctIntrlId;
	}

	public Date getDataDumpDt() {
		return dataDumpDt;
	}

	public void setAcctIntrlId(String acctIntrlId) {
		this.acctIntrlId = acctIntrlId;
	}

	public void setDataDumpDt(Date dataDumpDt) {
		this.dataDumpDt = dataDumpDt;
	}

	public BigDecimal getAcctBalSeqId() {
        return acctBalSeqId;
    }

    public void setAcctBalSeqId(BigDecimal acctBalSeqId) {
        this.acctBalSeqId = acctBalSeqId;
    }

    public BigDecimal getNetWrthAm() {
        return netWrthAm;
    }

    public void setNetWrthAm(BigDecimal netWrthAm) {
        this.netWrthAm = netWrthAm;
    }

    public BigDecimal getAvailPayAm() {
        return availPayAm;
    }

    public void setAvailPayAm(BigDecimal availPayAm) {
        this.availPayAm = availPayAm;
    }

    public BigDecimal getEqtyMktvalLngAm() {
        return eqtyMktvalLngAm;
    }

    public void setEqtyMktvalLngAm(BigDecimal eqtyMktvalLngAm) {
        this.eqtyMktvalLngAm = eqtyMktvalLngAm;
    }

    public BigDecimal getEqtyMktvalShrtAm() {
        return eqtyMktvalShrtAm;
    }

    public void setEqtyMktvalShrtAm(BigDecimal eqtyMktvalShrtAm) {
        this.eqtyMktvalShrtAm = eqtyMktvalShrtAm;
    }

    public BigDecimal getMrgnEqtyPt() {
        return mrgnEqtyPt;
    }

    public void setMrgnEqtyPt(BigDecimal mrgnEqtyPt) {
        this.mrgnEqtyPt = mrgnEqtyPt;
    }

    public BigDecimal getLowPricdScrtyAm() {
        return lowPricdScrtyAm;
    }

    public void setLowPricdScrtyAm(BigDecimal lowPricdScrtyAm) {
        this.lowPricdScrtyAm = lowPricdScrtyAm;
    }

    public BigDecimal getOptnMktvalLngAm() {
        return optnMktvalLngAm;
    }

    public void setOptnMktvalLngAm(BigDecimal optnMktvalLngAm) {
        this.optnMktvalLngAm = optnMktvalLngAm;
    }

    public BigDecimal getOptnMktvalShrtAm() {
        return optnMktvalShrtAm;
    }

    public void setOptnMktvalShrtAm(BigDecimal optnMktvalShrtAm) {
        this.optnMktvalShrtAm = optnMktvalShrtAm;
    }

    public BigDecimal getOptnMrgnRqmtAm() {
        return optnMrgnRqmtAm;
    }

    public void setOptnMrgnRqmtAm(BigDecimal optnMrgnRqmtAm) {
        this.optnMrgnRqmtAm = optnMrgnRqmtAm;
    }

    public BigDecimal getEqtyMktvalShrtNoboxAm() {
        return eqtyMktvalShrtNoboxAm;
    }

    public void setEqtyMktvalShrtNoboxAm(BigDecimal eqtyMktvalShrtNoboxAm) {
        this.eqtyMktvalShrtNoboxAm = eqtyMktvalShrtNoboxAm;
    }

    public BigDecimal getNkdCallAsgmtRiskAm() {
        return nkdCallAsgmtRiskAm;
    }

    public void setNkdCallAsgmtRiskAm(BigDecimal nkdCallAsgmtRiskAm) {
        this.nkdCallAsgmtRiskAm = nkdCallAsgmtRiskAm;
    }

    public BigDecimal getNkdPutAsgmtRiskAm() {
        return nkdPutAsgmtRiskAm;
    }

    public void setNkdPutAsgmtRiskAm(BigDecimal nkdPutAsgmtRiskAm) {
        this.nkdPutAsgmtRiskAm = nkdPutAsgmtRiskAm;
    }

    public BigDecimal getNkdPutAsgmtValAm() {
        return nkdPutAsgmtValAm;
    }

    public void setNkdPutAsgmtValAm(BigDecimal nkdPutAsgmtValAm) {
        this.nkdPutAsgmtValAm = nkdPutAsgmtValAm;
    }

    public BigDecimal getTotOptnAsgmtRiskAm() {
        return totOptnAsgmtRiskAm;
    }

    public void setTotOptnAsgmtRiskAm(BigDecimal totOptnAsgmtRiskAm) {
        this.totOptnAsgmtRiskAm = totOptnAsgmtRiskAm;
    }

    public BigDecimal getLngOptn2moExprnCt() {
        return lngOptn2moExprnCt;
    }

    public void setLngOptn2moExprnCt(BigDecimal lngOptn2moExprnCt) {
        this.lngOptn2moExprnCt = lngOptn2moExprnCt;
    }

    public BigDecimal getLngOptn6moExprnCt() {
        return lngOptn6moExprnCt;
    }

    public void setLngOptn6moExprnCt(BigDecimal lngOptn6moExprnCt) {
        this.lngOptn6moExprnCt = lngOptn6moExprnCt;
    }

    public BigDecimal getLngOptn3mo6moExprnCt() {
        return lngOptn3mo6moExprnCt;
    }

    public void setLngOptn3mo6moExprnCt(BigDecimal lngOptn3mo6moExprnCt) {
        this.lngOptn3mo6moExprnCt = lngOptn3mo6moExprnCt;
    }

    public BigDecimal getNkdOptn2moExprnCt() {
        return nkdOptn2moExprnCt;
    }

    public void setNkdOptn2moExprnCt(BigDecimal nkdOptn2moExprnCt) {
        this.nkdOptn2moExprnCt = nkdOptn2moExprnCt;
    }

    public BigDecimal getNkdOptn6moExprnCt() {
        return nkdOptn6moExprnCt;
    }

    public void setNkdOptn6moExprnCt(BigDecimal nkdOptn6moExprnCt) {
        this.nkdOptn6moExprnCt = nkdOptn6moExprnCt;
    }

    public BigDecimal getNkdOptn3mo6moExprnCt() {
        return nkdOptn3mo6moExprnCt;
    }

    public void setNkdOptn3mo6moExprnCt(BigDecimal nkdOptn3mo6moExprnCt) {
        this.nkdOptn3mo6moExprnCt = nkdOptn3mo6moExprnCt;
    }

    public String getHiCncScrtyIntrlId() {
        return hiCncScrtyIntrlId;
    }

    public void setHiCncScrtyIntrlId(String hiCncScrtyIntrlId) {
        this.hiCncScrtyIntrlId = hiCncScrtyIntrlId == null ? null : hiCncScrtyIntrlId.trim();
    }

    public BigDecimal getHiCncScrtyValAm() {
        return hiCncScrtyValAm;
    }

    public void setHiCncScrtyValAm(BigDecimal hiCncScrtyValAm) {
        this.hiCncScrtyValAm = hiCncScrtyValAm;
    }

    public BigDecimal getHiCncScrtyUnitQt() {
        return hiCncScrtyUnitQt;
    }

    public void setHiCncScrtyUnitQt(BigDecimal hiCncScrtyUnitQt) {
        this.hiCncScrtyUnitQt = hiCncScrtyUnitQt;
    }

    public BigDecimal getHiCncScrtyEqtyPt() {
        return hiCncScrtyEqtyPt;
    }

    public void setHiCncScrtyEqtyPt(BigDecimal hiCncScrtyEqtyPt) {
        this.hiCncScrtyEqtyPt = hiCncScrtyEqtyPt;
    }

    public String getHiCncShrtScrtyIntrlId() {
        return hiCncShrtScrtyIntrlId;
    }

    public void setHiCncShrtScrtyIntrlId(String hiCncShrtScrtyIntrlId) {
        this.hiCncShrtScrtyIntrlId = hiCncShrtScrtyIntrlId == null ? null : hiCncShrtScrtyIntrlId.trim();
    }

    public BigDecimal getHiCncShrtScrtyValAm() {
        return hiCncShrtScrtyValAm;
    }

    public void setHiCncShrtScrtyValAm(BigDecimal hiCncShrtScrtyValAm) {
        this.hiCncShrtScrtyValAm = hiCncShrtScrtyValAm;
    }

    public BigDecimal getHiCncShrtScrtyUnitQt() {
        return hiCncShrtScrtyUnitQt;
    }

    public void setHiCncShrtScrtyUnitQt(BigDecimal hiCncShrtScrtyUnitQt) {
        this.hiCncShrtScrtyUnitQt = hiCncShrtScrtyUnitQt;
    }

    public String getHiCncSctrNm() {
        return hiCncSctrNm;
    }

    public void setHiCncSctrNm(String hiCncSctrNm) {
        this.hiCncSctrNm = hiCncSctrNm == null ? null : hiCncSctrNm.trim();
    }

    public BigDecimal getHiCncSctrValAm() {
        return hiCncSctrValAm;
    }

    public void setHiCncSctrValAm(BigDecimal hiCncSctrValAm) {
        this.hiCncSctrValAm = hiCncSctrValAm;
    }

    public String getHiCncNkdOptnUndScrtyId() {
        return hiCncNkdOptnUndScrtyId;
    }

    public void setHiCncNkdOptnUndScrtyId(String hiCncNkdOptnUndScrtyId) {
        this.hiCncNkdOptnUndScrtyId = hiCncNkdOptnUndScrtyId == null ? null : hiCncNkdOptnUndScrtyId.trim();
    }

    public String getHiCncLngOptnUndScrtyId() {
        return hiCncLngOptnUndScrtyId;
    }

    public void setHiCncLngOptnUndScrtyId(String hiCncLngOptnUndScrtyId) {
        this.hiCncLngOptnUndScrtyId = hiCncLngOptnUndScrtyId == null ? null : hiCncLngOptnUndScrtyId.trim();
    }

    public BigDecimal getHiCncNkdOptnCntrQt() {
        return hiCncNkdOptnCntrQt;
    }

    public void setHiCncNkdOptnCntrQt(BigDecimal hiCncNkdOptnCntrQt) {
        this.hiCncNkdOptnCntrQt = hiCncNkdOptnCntrQt;
    }

    public BigDecimal getHiCncLngOptnCntrQt() {
        return hiCncLngOptnCntrQt;
    }

    public void setHiCncLngOptnCntrQt(BigDecimal hiCncLngOptnCntrQt) {
        this.hiCncLngOptnCntrQt = hiCncLngOptnCntrQt;
    }

    public BigDecimal getTotNkdOptnCntrQt() {
        return totNkdOptnCntrQt;
    }

    public void setTotNkdOptnCntrQt(BigDecimal totNkdOptnCntrQt) {
        this.totNkdOptnCntrQt = totNkdOptnCntrQt;
    }

    public BigDecimal getTotLngOptnCntrQt() {
        return totLngOptnCntrQt;
    }

    public void setTotLngOptnCntrQt(BigDecimal totLngOptnCntrQt) {
        this.totLngOptnCntrQt = totLngOptnCntrQt;
    }

    public BigDecimal getHiCncOptnMktvalLngAm() {
        return hiCncOptnMktvalLngAm;
    }

    public void setHiCncOptnMktvalLngAm(BigDecimal hiCncOptnMktvalLngAm) {
        this.hiCncOptnMktvalLngAm = hiCncOptnMktvalLngAm;
    }

    public String getHhAcctGrpId() {
        return hhAcctGrpId;
    }

    public void setHhAcctGrpId(String hhAcctGrpId) {
        this.hhAcctGrpId = hhAcctGrpId == null ? null : hhAcctGrpId.trim();
    }

    public BigDecimal getMrgnBalAm() {
        return mrgnBalAm;
    }

    public void setMrgnBalAm(BigDecimal mrgnBalAm) {
        this.mrgnBalAm = mrgnBalAm;
    }

    public BigDecimal getCashBalAm() {
        return cashBalAm;
    }

    public void setCashBalAm(BigDecimal cashBalAm) {
        this.cashBalAm = cashBalAm;
    }

    public BigDecimal getLngMrgnMktvalAm() {
        return lngMrgnMktvalAm;
    }

    public void setLngMrgnMktvalAm(BigDecimal lngMrgnMktvalAm) {
        this.lngMrgnMktvalAm = lngMrgnMktvalAm;
    }

    public BigDecimal getShrtMrgnMktvalAm() {
        return shrtMrgnMktvalAm;
    }

    public void setShrtMrgnMktvalAm(BigDecimal shrtMrgnMktvalAm) {
        this.shrtMrgnMktvalAm = shrtMrgnMktvalAm;
    }

    public BigDecimal getTotCashScrtyValAm() {
        return totCashScrtyValAm;
    }

    public void setTotCashScrtyValAm(BigDecimal totCashScrtyValAm) {
        this.totCashScrtyValAm = totCashScrtyValAm;
    }

    public BigDecimal getMnyMktFundBalAm() {
        return mnyMktFundBalAm;
    }

    public void setMnyMktFundBalAm(BigDecimal mnyMktFundBalAm) {
        this.mnyMktFundBalAm = mnyMktFundBalAm;
    }

    public BigDecimal getMrgnEqtyAm() {
        return mrgnEqtyAm;
    }

    public void setMrgnEqtyAm(BigDecimal mrgnEqtyAm) {
        this.mrgnEqtyAm = mrgnEqtyAm;
    }

    public BigDecimal getMfundBalAm() {
        return mfundBalAm;
    }

    public void setMfundBalAm(BigDecimal mfundBalAm) {
        this.mfundBalAm = mfundBalAm;
    }

    public BigDecimal getBondBalAm() {
        return bondBalAm;
    }

    public void setBondBalAm(BigDecimal bondBalAm) {
        this.bondBalAm = bondBalAm;
    }

    public BigDecimal getTotDebtAm() {
        return totDebtAm;
    }

    public void setTotDebtAm(BigDecimal totDebtAm) {
        this.totDebtAm = totDebtAm;
    }

    public BigDecimal getUncolBalAm() {
        return uncolBalAm;
    }

    public void setUncolBalAm(BigDecimal uncolBalAm) {
        this.uncolBalAm = uncolBalAm;
    }

    public BigDecimal getOvrDrftBalAm() {
        return ovrDrftBalAm;
    }

    public void setOvrDrftBalAm(BigDecimal ovrDrftBalAm) {
        this.ovrDrftBalAm = ovrDrftBalAm;
    }

    public BigDecimal getLastOvrDrftNegBalAm() {
        return lastOvrDrftNegBalAm;
    }

    public void setLastOvrDrftNegBalAm(BigDecimal lastOvrDrftNegBalAm) {
        this.lastOvrDrftNegBalAm = lastOvrDrftNegBalAm;
    }

    public BigDecimal getLastOvrDrftDurtnDyCt() {
        return lastOvrDrftDurtnDyCt;
    }

    public void setLastOvrDrftDurtnDyCt(BigDecimal lastOvrDrftDurtnDyCt) {
        this.lastOvrDrftDurtnDyCt = lastOvrDrftDurtnDyCt;
    }

    public Date getLastOvrDrftNegBalDt() {
        return lastOvrDrftNegBalDt;
    }

    public void setLastOvrDrftNegBalDt(Date lastOvrDrftNegBalDt) {
        this.lastOvrDrftNegBalDt = lastOvrDrftNegBalDt;
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd == null ? null : srcSysCd.trim();
    }

    public Date getCstm1Dt() {
        return cstm1Dt;
    }

    public void setCstm1Dt(Date cstm1Dt) {
        this.cstm1Dt = cstm1Dt;
    }

    public Date getCstm2Dt() {
        return cstm2Dt;
    }

    public void setCstm2Dt(Date cstm2Dt) {
        this.cstm2Dt = cstm2Dt;
    }

    public Date getCstm3Dt() {
        return cstm3Dt;
    }

    public void setCstm3Dt(Date cstm3Dt) {
        this.cstm3Dt = cstm3Dt;
    }

    public BigDecimal getCstm1Rl() {
        return cstm1Rl;
    }

    public void setCstm1Rl(BigDecimal cstm1Rl) {
        this.cstm1Rl = cstm1Rl;
    }

    public BigDecimal getCstm2Rl() {
        return cstm2Rl;
    }

    public void setCstm2Rl(BigDecimal cstm2Rl) {
        this.cstm2Rl = cstm2Rl;
    }

    public BigDecimal getCstm3Rl() {
        return cstm3Rl;
    }

    public void setCstm3Rl(BigDecimal cstm3Rl) {
        this.cstm3Rl = cstm3Rl;
    }

    public String getCstm1Tx() {
        return cstm1Tx;
    }

    public void setCstm1Tx(String cstm1Tx) {
        this.cstm1Tx = cstm1Tx == null ? null : cstm1Tx.trim();
    }

    public String getCstm2Tx() {
        return cstm2Tx;
    }

    public void setCstm2Tx(String cstm2Tx) {
        this.cstm2Tx = cstm2Tx == null ? null : cstm2Tx.trim();
    }

    public String getCstm3Tx() {
        return cstm3Tx;
    }

    public void setCstm3Tx(String cstm3Tx) {
        this.cstm3Tx = cstm3Tx == null ? null : cstm3Tx.trim();
    }

    public String getCstm4Tx() {
        return cstm4Tx;
    }

    public void setCstm4Tx(String cstm4Tx) {
        this.cstm4Tx = cstm4Tx == null ? null : cstm4Tx.trim();
    }

    public String getCstm5Tx() {
        return cstm5Tx;
    }

    public void setCstm5Tx(String cstm5Tx) {
        this.cstm5Tx = cstm5Tx == null ? null : cstm5Tx.trim();
    }

    public BigDecimal getCltrlValAm() {
        return cltrlValAm;
    }

    public void setCltrlValAm(BigDecimal cltrlValAm) {
        this.cltrlValAm = cltrlValAm;
    }

    public BigDecimal getCltrlRqmtAm() {
        return cltrlRqmtAm;
    }

    public void setCltrlRqmtAm(BigDecimal cltrlRqmtAm) {
        this.cltrlRqmtAm = cltrlRqmtAm;
    }

    public String getPrcsngBatchNm() {
        return prcsngBatchNm;
    }

    public void setPrcsngBatchNm(String prcsngBatchNm) {
        this.prcsngBatchNm = prcsngBatchNm == null ? null : prcsngBatchNm.trim();
    }

    public BigDecimal getAdjMrgnDbtAm() {
        return adjMrgnDbtAm;
    }

    public void setAdjMrgnDbtAm(BigDecimal adjMrgnDbtAm) {
        this.adjMrgnDbtAm = adjMrgnDbtAm;
    }

    public BigDecimal getNetWrthRptngAm() {
        return netWrthRptngAm;
    }

    public void setNetWrthRptngAm(BigDecimal netWrthRptngAm) {
        this.netWrthRptngAm = netWrthRptngAm;
    }

    public BigDecimal getAvailPayRptngAm() {
        return availPayRptngAm;
    }

    public void setAvailPayRptngAm(BigDecimal availPayRptngAm) {
        this.availPayRptngAm = availPayRptngAm;
    }

    public BigDecimal getMrgnBalRptngAm() {
        return mrgnBalRptngAm;
    }

    public void setMrgnBalRptngAm(BigDecimal mrgnBalRptngAm) {
        this.mrgnBalRptngAm = mrgnBalRptngAm;
    }

    public BigDecimal getMrgnEqtyRptngAm() {
        return mrgnEqtyRptngAm;
    }

    public void setMrgnEqtyRptngAm(BigDecimal mrgnEqtyRptngAm) {
        this.mrgnEqtyRptngAm = mrgnEqtyRptngAm;
    }

    public BigDecimal getCashBalRptngAm() {
        return cashBalRptngAm;
    }

    public void setCashBalRptngAm(BigDecimal cashBalRptngAm) {
        this.cashBalRptngAm = cashBalRptngAm;
    }

    public BigDecimal getLastOvrDrftNegBalRptngAm() {
        return lastOvrDrftNegBalRptngAm;
    }

    public void setLastOvrDrftNegBalRptngAm(BigDecimal lastOvrDrftNegBalRptngAm) {
        this.lastOvrDrftNegBalRptngAm = lastOvrDrftNegBalRptngAm;
    }

    public BigDecimal getTotCashScrtyValRptngAm() {
        return totCashScrtyValRptngAm;
    }

    public void setTotCashScrtyValRptngAm(BigDecimal totCashScrtyValRptngAm) {
        this.totCashScrtyValRptngAm = totCashScrtyValRptngAm;
    }

    public BigDecimal getUncolBalRptngAm() {
        return uncolBalRptngAm;
    }

    public void setUncolBalRptngAm(BigDecimal uncolBalRptngAm) {
        this.uncolBalRptngAm = uncolBalRptngAm;
    }

    public BigDecimal getEqtyMktvalShrtRptngAm() {
        return eqtyMktvalShrtRptngAm;
    }

    public void setEqtyMktvalShrtRptngAm(BigDecimal eqtyMktvalShrtRptngAm) {
        this.eqtyMktvalShrtRptngAm = eqtyMktvalShrtRptngAm;
    }

    public BigDecimal getEqtyMktvalLngRptngAm() {
        return eqtyMktvalLngRptngAm;
    }

    public void setEqtyMktvalLngRptngAm(BigDecimal eqtyMktvalLngRptngAm) {
        this.eqtyMktvalLngRptngAm = eqtyMktvalLngRptngAm;
    }

    public BigDecimal getLngMrgnMktvalRptngAm() {
        return lngMrgnMktvalRptngAm;
    }

    public void setLngMrgnMktvalRptngAm(BigDecimal lngMrgnMktvalRptngAm) {
        this.lngMrgnMktvalRptngAm = lngMrgnMktvalRptngAm;
    }

    public BigDecimal getShrtMrgnMktvalRptngAm() {
        return shrtMrgnMktvalRptngAm;
    }

    public void setShrtMrgnMktvalRptngAm(BigDecimal shrtMrgnMktvalRptngAm) {
        this.shrtMrgnMktvalRptngAm = shrtMrgnMktvalRptngAm;
    }

    public BigDecimal getMnyMktFundBalRptngAm() {
        return mnyMktFundBalRptngAm;
    }

    public void setMnyMktFundBalRptngAm(BigDecimal mnyMktFundBalRptngAm) {
        this.mnyMktFundBalRptngAm = mnyMktFundBalRptngAm;
    }

    public BigDecimal getOptnMrgnRqmtRptngAm() {
        return optnMrgnRqmtRptngAm;
    }

    public void setOptnMrgnRqmtRptngAm(BigDecimal optnMrgnRqmtRptngAm) {
        this.optnMrgnRqmtRptngAm = optnMrgnRqmtRptngAm;
    }

    public BigDecimal getOptnMktvalShrtRptngAm() {
        return optnMktvalShrtRptngAm;
    }

    public void setOptnMktvalShrtRptngAm(BigDecimal optnMktvalShrtRptngAm) {
        this.optnMktvalShrtRptngAm = optnMktvalShrtRptngAm;
    }

    public BigDecimal getOptnMktvalLngRptngAm() {
        return optnMktvalLngRptngAm;
    }

    public void setOptnMktvalLngRptngAm(BigDecimal optnMktvalLngRptngAm) {
        this.optnMktvalLngRptngAm = optnMktvalLngRptngAm;
    }

    public BigDecimal getOvrDrftBalRptngAm() {
        return ovrDrftBalRptngAm;
    }

    public void setOvrDrftBalRptngAm(BigDecimal ovrDrftBalRptngAm) {
        this.ovrDrftBalRptngAm = ovrDrftBalRptngAm;
    }

    public BigDecimal getCltrlValRptngAm() {
        return cltrlValRptngAm;
    }

    public void setCltrlValRptngAm(BigDecimal cltrlValRptngAm) {
        this.cltrlValRptngAm = cltrlValRptngAm;
    }

    public BigDecimal getCltrlRqmtRptngAm() {
        return cltrlRqmtRptngAm;
    }

    public void setCltrlRqmtRptngAm(BigDecimal cltrlRqmtRptngAm) {
        this.cltrlRqmtRptngAm = cltrlRqmtRptngAm;
    }

    public BigDecimal getAdjMrgnDbtRptngAm() {
        return adjMrgnDbtRptngAm;
    }

    public void setAdjMrgnDbtRptngAm(BigDecimal adjMrgnDbtRptngAm) {
        this.adjMrgnDbtRptngAm = adjMrgnDbtRptngAm;
    }

    public BigDecimal getTotDebtRptngAm() {
        return totDebtRptngAm;
    }

    public void setTotDebtRptngAm(BigDecimal totDebtRptngAm) {
        this.totDebtRptngAm = totDebtRptngAm;
    }

    public BigDecimal getFeeAm() {
        return feeAm;
    }

    public void setFeeAm(BigDecimal feeAm) {
        this.feeAm = feeAm;
    }

    public BigDecimal getSuprCmsnAm() {
        return suprCmsnAm;
    }

    public void setSuprCmsnAm(BigDecimal suprCmsnAm) {
        this.suprCmsnAm = suprCmsnAm;
    }

    public BigDecimal getCashValRptngAm() {
        return cashValRptngAm;
    }

    public void setCashValRptngAm(BigDecimal cashValRptngAm) {
        this.cashValRptngAm = cashValRptngAm;
    }

    public BigDecimal getCashValBaseAm() {
        return cashValBaseAm;
    }

    public void setCashValBaseAm(BigDecimal cashValBaseAm) {
        this.cashValBaseAm = cashValBaseAm;
    }

    public BigDecimal getAcctGrossAssetsRptngAm() {
        return acctGrossAssetsRptngAm;
    }

    public void setAcctGrossAssetsRptngAm(BigDecimal acctGrossAssetsRptngAm) {
        this.acctGrossAssetsRptngAm = acctGrossAssetsRptngAm;
    }

    public BigDecimal getAcctGrossAssetsBaseAm() {
        return acctGrossAssetsBaseAm;
    }

    public void setAcctGrossAssetsBaseAm(BigDecimal acctGrossAssetsBaseAm) {
        this.acctGrossAssetsBaseAm = acctGrossAssetsBaseAm;
    }

    public BigDecimal getLdgrAm() {
        return ldgrAm;
    }

    public void setLdgrAm(BigDecimal ldgrAm) {
        this.ldgrAm = ldgrAm;
    }

    public BigDecimal getLdgrRptngAm() {
        return ldgrRptngAm;
    }

    public void setLdgrRptngAm(BigDecimal ldgrRptngAm) {
        this.ldgrRptngAm = ldgrRptngAm;
    }

    public String getNetWrthAmRngCd() {
        return netWrthAmRngCd;
    }

    public void setNetWrthAmRngCd(String netWrthAmRngCd) {
        this.netWrthAmRngCd = netWrthAmRngCd == null ? null : netWrthAmRngCd.trim();
    }

    public String getAvailPayAmRngCd() {
        return availPayAmRngCd;
    }

    public void setAvailPayAmRngCd(String availPayAmRngCd) {
        this.availPayAmRngCd = availPayAmRngCd == null ? null : availPayAmRngCd.trim();
    }

    public BigDecimal getLowPricdScrtyBasePr() {
        return lowPricdScrtyBasePr;
    }

    public void setLowPricdScrtyBasePr(BigDecimal lowPricdScrtyBasePr) {
        this.lowPricdScrtyBasePr = lowPricdScrtyBasePr;
    }
}