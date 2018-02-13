package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Acct {
    private String acctIntrlId;

    private Date dataDumpDt;

    private BigDecimal acctSeqId;

    private String sgmntId;

    private String optnAprvlLvlCd;

    private Date optnAppvlDt;

    private String rgstnTypeCd;

    private String acctType1Cd;

    private String acctType2Cd;

    private Date acctOpenDt;

    private BigDecimal maintRqmtPt;

    private String acctTaxId;

    private String taxIdFrmtCd;

    private String mrgnAppvlFl;

    private String taxWthldCd;

    private String titlLine1Tx;

    private String titlLine2Tx;

    private String titlLine3Tx;

    private String dscrnFl;

    private String hhAcctGrpId;

    private String mktgCampCd;

    private String cmsnSchedTblCd;

    private String empAcctFl;

    private String testAcctFl;

    private String acctStatCd;

    private Date acctStatDt;

    private String altAcctId;

    private String nvsmtMgrRetlRefrlFl;

    private Date lastActvyDt;

    private String srcSysCd;

    private String acctDsplyNm;

    private BigDecimal acctBusRiskNb;

    private BigDecimal acctCustRiskNb;

    private BigDecimal acctGeoRiskNb;

    private BigDecimal stmtCustQt;

    private String prmryPrdctTypeCd;

    private Date lastStmtDt;

    private String stmtSuprFl;

    private String notfyLtrSuprFl;

    private String legalNtityId;

    private String acctPrmBrkrCd;

    private String instnCntryCd;

    private String globlRlshpFl;

    private String acctListSrcCd;

    private String prmryCustIntrlId;

    private BigDecimal acctEfctvRiskNb;

    private BigDecimal acctListRiskNb;

    private String acctMatchTx;

    private String acctMatchTypeCd;

    private BigDecimal cstmRisk1Nb;

    private BigDecimal cstmRisk2Nb;

    private String taxPayrCustIntrlId;

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

    private String pttrnDayTrdFl;

    private String dayTrdAprvlStatCd;

    private Date dayTrdAprvlUpdtDt;

    private String dayTrdAprvlUpdtUIntrlId;

    private Date dayTrdAprvlReqDt;

    private BigDecimal stbltySurvlLevelNb;

    private String acctRptngCrncyCd;

    private String dmcldBrchCd;

    private String prcsngBatchNm;

    private String ultmtInstlCustIntrlId;

    private String dvpRvpFl;

    private String mantasAcctHoldrTypeCd;

    private String mantasAcctBusTypeCd;

    private String mantasAcctOwnrshpTypeCd;

    private String mantasAcctPurpCd;

    private String retrmtAcctFl;

    private String jrsdcnCd;

    private String busDmnListTx;

    private String highActvyAcctFl;

    private String firmAcctOrgIntrlId;

    private String payBasisTypeCd;

    private String cashRptExmptFl;

    private String acctEfctvRiskFactrTx;

    private String acctCustListSrcCd;

    private String srvcTeamIntrlId;

    private String feeSchedCd;

    private Date payBasisStartDt;

    private String riskTlrncCd;

    private String prmryCustPinNb;

    private String acctPeerGrpIntrlId;

    private String prtfloMgrIntrlId;

    private String highPrflFl;

    private String prodIntrlId;

    private String srcInitFunds;

    private String mthdInitFunds;

    private String mthdAcctOpng;

    private Date acctCloseDt;

    private String acctRptCatCd;

    private String adminCd;

    private String bkupAdminCd;

    private String bkngCtrCd;

    private String cpctyCd;

    private String cntrlGrpCd;

    private String cntrlId;

    private String hardHoldFl;

    private String localAcctOffcr;

    private String pledgeFl;

    private String realEstOffcrCd;

    private String rlshpId;

    private String stateCd;

    private String stopPaymtFl;

    private Date trstReviewDt;

    private String titlLine4Tx;

    private String nvsmtObjCd;

    private String nvsmtOffcrCd;

    private Date avpDt;

    private String bllngPrdctCd;

    private String dscrnCd;

    private String holdAllMailCd;

    private String incmRngCd;

    private String mgrNm;

    private String mangdAcctFl;

    private String grwthNvsmtObjRnkngCd;

    private String incmNvsmtObjRnkngCd;

    private String lqdtyNvsmtObjRnkngCd;

    private String taxDffrdNvsmtObjRnkngCd;

    private String ownrshpCatCd;

    private String planNbTx;

    private String stockOptnPlanFl;

    private String spcltnFl;

    private String styleCd;

    private String srvcLevelTx;

    private String permAcctIntrlId;

    private String ofacReasnCd;

    private String spcltnLvl;

    private String optnAprvlCd;

    private String cnsldtnNbrCd;

    private String dscMangdCd;

    private String leadAcctFl;

    private String leadTrackCd;

    private String qlfidPlanEmpNm;

    private String rllvrFl;

    private String prmryClntFl;

    private String insdrAcctFl;

    private String smallHhFl;

    private String cpiFl;

    private String stndgInstrUsAcctFl;

    private String usPoaSignFl;

    private String recalcitrantFl;

    private BigDecimal priorPrdEndNetWrthAm;

    private Date netWrthAmPrdEndDt;

    private String extrlNvsmtAcctFl;

    private String geoJrsdcnCd;

    private String fatcaStatusCd;

    private String fatcaPooledRptTypeCd;

    private String fatcaIndiciaFl;

    private String fsdfStgSrc;

    private BigDecimal intAm;

    private BigDecimal dividendAm;

    private BigDecimal grossProceedsRdmptAm;

    private BigDecimal otherIncmAm;
    
    
    
    private AcctBalPosnSmry acctBal;//金额表
    
    private List<String> acctIds;//
    
    private String addrUsageCd;
    
    private String addrCityNm;
    
    private String addrCntryCd;
    
    private String addrRgnNm;
    
    private String addrStateCd;
    
    public String getAcctIntrlId() {
        return acctIntrlId;
    }

    public void setAcctIntrlId(String acctIntrlId) {
        this.acctIntrlId = acctIntrlId == null ? null : acctIntrlId.trim();
    }

    public Date getDataDumpDt() {
        return dataDumpDt;
    }

    public void setDataDumpDt(Date dataDumpDt) {
        this.dataDumpDt = dataDumpDt;
    }

    public BigDecimal getAcctSeqId() {
        return acctSeqId;
    }

    public void setAcctSeqId(BigDecimal acctSeqId) {
        this.acctSeqId = acctSeqId;
    }

    public String getSgmntId() {
        return sgmntId;
    }

    public void setSgmntId(String sgmntId) {
        this.sgmntId = sgmntId == null ? null : sgmntId.trim();
    }

    public String getOptnAprvlLvlCd() {
        return optnAprvlLvlCd;
    }

    public void setOptnAprvlLvlCd(String optnAprvlLvlCd) {
        this.optnAprvlLvlCd = optnAprvlLvlCd == null ? null : optnAprvlLvlCd.trim();
    }

    public Date getOptnAppvlDt() {
        return optnAppvlDt;
    }

    public void setOptnAppvlDt(Date optnAppvlDt) {
        this.optnAppvlDt = optnAppvlDt;
    }

    public String getRgstnTypeCd() {
        return rgstnTypeCd;
    }

    public void setRgstnTypeCd(String rgstnTypeCd) {
        this.rgstnTypeCd = rgstnTypeCd == null ? null : rgstnTypeCd.trim();
    }

    public String getAcctType1Cd() {
        return acctType1Cd;
    }

    public void setAcctType1Cd(String acctType1Cd) {
        this.acctType1Cd = acctType1Cd == null ? null : acctType1Cd.trim();
    }

    public String getAcctType2Cd() {
        return acctType2Cd;
    }

    public void setAcctType2Cd(String acctType2Cd) {
        this.acctType2Cd = acctType2Cd == null ? null : acctType2Cd.trim();
    }

    public Date getAcctOpenDt() {
        return acctOpenDt;
    }

    public void setAcctOpenDt(Date acctOpenDt) {
        this.acctOpenDt = acctOpenDt;
    }

    public BigDecimal getMaintRqmtPt() {
        return maintRqmtPt;
    }

    public void setMaintRqmtPt(BigDecimal maintRqmtPt) {
        this.maintRqmtPt = maintRqmtPt;
    }

    public String getAcctTaxId() {
        return acctTaxId;
    }

    public void setAcctTaxId(String acctTaxId) {
        this.acctTaxId = acctTaxId == null ? null : acctTaxId.trim();
    }

    public String getTaxIdFrmtCd() {
        return taxIdFrmtCd;
    }

    public void setTaxIdFrmtCd(String taxIdFrmtCd) {
        this.taxIdFrmtCd = taxIdFrmtCd == null ? null : taxIdFrmtCd.trim();
    }

    public String getMrgnAppvlFl() {
        return mrgnAppvlFl;
    }

    public void setMrgnAppvlFl(String mrgnAppvlFl) {
        this.mrgnAppvlFl = mrgnAppvlFl == null ? null : mrgnAppvlFl.trim();
    }

    public String getTaxWthldCd() {
        return taxWthldCd;
    }

    public void setTaxWthldCd(String taxWthldCd) {
        this.taxWthldCd = taxWthldCd == null ? null : taxWthldCd.trim();
    }

    public String getTitlLine1Tx() {
        return titlLine1Tx;
    }

    public void setTitlLine1Tx(String titlLine1Tx) {
        this.titlLine1Tx = titlLine1Tx == null ? null : titlLine1Tx.trim();
    }

    public String getTitlLine2Tx() {
        return titlLine2Tx;
    }

    public void setTitlLine2Tx(String titlLine2Tx) {
        this.titlLine2Tx = titlLine2Tx == null ? null : titlLine2Tx.trim();
    }

    public String getTitlLine3Tx() {
        return titlLine3Tx;
    }

    public void setTitlLine3Tx(String titlLine3Tx) {
        this.titlLine3Tx = titlLine3Tx == null ? null : titlLine3Tx.trim();
    }

    public String getDscrnFl() {
        return dscrnFl;
    }

    public void setDscrnFl(String dscrnFl) {
        this.dscrnFl = dscrnFl == null ? null : dscrnFl.trim();
    }

    public String getHhAcctGrpId() {
        return hhAcctGrpId;
    }

    public void setHhAcctGrpId(String hhAcctGrpId) {
        this.hhAcctGrpId = hhAcctGrpId == null ? null : hhAcctGrpId.trim();
    }

    public String getMktgCampCd() {
        return mktgCampCd;
    }

    public void setMktgCampCd(String mktgCampCd) {
        this.mktgCampCd = mktgCampCd == null ? null : mktgCampCd.trim();
    }

    public String getCmsnSchedTblCd() {
        return cmsnSchedTblCd;
    }

    public void setCmsnSchedTblCd(String cmsnSchedTblCd) {
        this.cmsnSchedTblCd = cmsnSchedTblCd == null ? null : cmsnSchedTblCd.trim();
    }

    public String getEmpAcctFl() {
        return empAcctFl;
    }

    public void setEmpAcctFl(String empAcctFl) {
        this.empAcctFl = empAcctFl == null ? null : empAcctFl.trim();
    }

    public String getTestAcctFl() {
        return testAcctFl;
    }

    public void setTestAcctFl(String testAcctFl) {
        this.testAcctFl = testAcctFl == null ? null : testAcctFl.trim();
    }

    public String getAcctStatCd() {
        return acctStatCd;
    }

    public void setAcctStatCd(String acctStatCd) {
        this.acctStatCd = acctStatCd == null ? null : acctStatCd.trim();
    }

    public Date getAcctStatDt() {
        return acctStatDt;
    }

    public void setAcctStatDt(Date acctStatDt) {
        this.acctStatDt = acctStatDt;
    }

    public String getAltAcctId() {
        return altAcctId;
    }

    public void setAltAcctId(String altAcctId) {
        this.altAcctId = altAcctId == null ? null : altAcctId.trim();
    }

    public String getNvsmtMgrRetlRefrlFl() {
        return nvsmtMgrRetlRefrlFl;
    }

    public void setNvsmtMgrRetlRefrlFl(String nvsmtMgrRetlRefrlFl) {
        this.nvsmtMgrRetlRefrlFl = nvsmtMgrRetlRefrlFl == null ? null : nvsmtMgrRetlRefrlFl.trim();
    }

    public Date getLastActvyDt() {
        return lastActvyDt;
    }

    public void setLastActvyDt(Date lastActvyDt) {
        this.lastActvyDt = lastActvyDt;
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd == null ? null : srcSysCd.trim();
    }

    public String getAcctDsplyNm() {
        return acctDsplyNm;
    }

    public void setAcctDsplyNm(String acctDsplyNm) {
        this.acctDsplyNm = acctDsplyNm == null ? null : acctDsplyNm.trim();
    }

    public BigDecimal getAcctBusRiskNb() {
        return acctBusRiskNb;
    }

    public void setAcctBusRiskNb(BigDecimal acctBusRiskNb) {
        this.acctBusRiskNb = acctBusRiskNb;
    }

    public BigDecimal getAcctCustRiskNb() {
        return acctCustRiskNb;
    }

    public void setAcctCustRiskNb(BigDecimal acctCustRiskNb) {
        this.acctCustRiskNb = acctCustRiskNb;
    }

    public BigDecimal getAcctGeoRiskNb() {
        return acctGeoRiskNb;
    }

    public void setAcctGeoRiskNb(BigDecimal acctGeoRiskNb) {
        this.acctGeoRiskNb = acctGeoRiskNb;
    }

    public BigDecimal getStmtCustQt() {
        return stmtCustQt;
    }

    public void setStmtCustQt(BigDecimal stmtCustQt) {
        this.stmtCustQt = stmtCustQt;
    }

    public String getPrmryPrdctTypeCd() {
        return prmryPrdctTypeCd;
    }

    public void setPrmryPrdctTypeCd(String prmryPrdctTypeCd) {
        this.prmryPrdctTypeCd = prmryPrdctTypeCd == null ? null : prmryPrdctTypeCd.trim();
    }

    public Date getLastStmtDt() {
        return lastStmtDt;
    }

    public void setLastStmtDt(Date lastStmtDt) {
        this.lastStmtDt = lastStmtDt;
    }

    public String getStmtSuprFl() {
        return stmtSuprFl;
    }

    public void setStmtSuprFl(String stmtSuprFl) {
        this.stmtSuprFl = stmtSuprFl == null ? null : stmtSuprFl.trim();
    }

    public String getNotfyLtrSuprFl() {
        return notfyLtrSuprFl;
    }

    public void setNotfyLtrSuprFl(String notfyLtrSuprFl) {
        this.notfyLtrSuprFl = notfyLtrSuprFl == null ? null : notfyLtrSuprFl.trim();
    }

    public String getLegalNtityId() {
        return legalNtityId;
    }

    public void setLegalNtityId(String legalNtityId) {
        this.legalNtityId = legalNtityId == null ? null : legalNtityId.trim();
    }

    public String getAcctPrmBrkrCd() {
        return acctPrmBrkrCd;
    }

    public void setAcctPrmBrkrCd(String acctPrmBrkrCd) {
        this.acctPrmBrkrCd = acctPrmBrkrCd == null ? null : acctPrmBrkrCd.trim();
    }

    public String getInstnCntryCd() {
        return instnCntryCd;
    }

    public void setInstnCntryCd(String instnCntryCd) {
        this.instnCntryCd = instnCntryCd == null ? null : instnCntryCd.trim();
    }

    public String getGloblRlshpFl() {
        return globlRlshpFl;
    }

    public void setGloblRlshpFl(String globlRlshpFl) {
        this.globlRlshpFl = globlRlshpFl == null ? null : globlRlshpFl.trim();
    }

    public String getAcctListSrcCd() {
        return acctListSrcCd;
    }

    public void setAcctListSrcCd(String acctListSrcCd) {
        this.acctListSrcCd = acctListSrcCd == null ? null : acctListSrcCd.trim();
    }

    public String getPrmryCustIntrlId() {
        return prmryCustIntrlId;
    }

    public void setPrmryCustIntrlId(String prmryCustIntrlId) {
        this.prmryCustIntrlId = prmryCustIntrlId == null ? null : prmryCustIntrlId.trim();
    }

    public BigDecimal getAcctEfctvRiskNb() {
        return acctEfctvRiskNb;
    }

    public void setAcctEfctvRiskNb(BigDecimal acctEfctvRiskNb) {
        this.acctEfctvRiskNb = acctEfctvRiskNb;
    }

    public BigDecimal getAcctListRiskNb() {
        return acctListRiskNb;
    }

    public void setAcctListRiskNb(BigDecimal acctListRiskNb) {
        this.acctListRiskNb = acctListRiskNb;
    }

    public String getAcctMatchTx() {
        return acctMatchTx;
    }

    public void setAcctMatchTx(String acctMatchTx) {
        this.acctMatchTx = acctMatchTx == null ? null : acctMatchTx.trim();
    }

    public String getAcctMatchTypeCd() {
        return acctMatchTypeCd;
    }

    public void setAcctMatchTypeCd(String acctMatchTypeCd) {
        this.acctMatchTypeCd = acctMatchTypeCd == null ? null : acctMatchTypeCd.trim();
    }

    public BigDecimal getCstmRisk1Nb() {
        return cstmRisk1Nb;
    }

    public void setCstmRisk1Nb(BigDecimal cstmRisk1Nb) {
        this.cstmRisk1Nb = cstmRisk1Nb;
    }

    public BigDecimal getCstmRisk2Nb() {
        return cstmRisk2Nb;
    }

    public void setCstmRisk2Nb(BigDecimal cstmRisk2Nb) {
        this.cstmRisk2Nb = cstmRisk2Nb;
    }

    public String getTaxPayrCustIntrlId() {
        return taxPayrCustIntrlId;
    }

    public void setTaxPayrCustIntrlId(String taxPayrCustIntrlId) {
        this.taxPayrCustIntrlId = taxPayrCustIntrlId == null ? null : taxPayrCustIntrlId.trim();
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

    public String getPttrnDayTrdFl() {
        return pttrnDayTrdFl;
    }

    public void setPttrnDayTrdFl(String pttrnDayTrdFl) {
        this.pttrnDayTrdFl = pttrnDayTrdFl == null ? null : pttrnDayTrdFl.trim();
    }

    public String getDayTrdAprvlStatCd() {
        return dayTrdAprvlStatCd;
    }

    public void setDayTrdAprvlStatCd(String dayTrdAprvlStatCd) {
        this.dayTrdAprvlStatCd = dayTrdAprvlStatCd == null ? null : dayTrdAprvlStatCd.trim();
    }

    public Date getDayTrdAprvlUpdtDt() {
        return dayTrdAprvlUpdtDt;
    }

    public void setDayTrdAprvlUpdtDt(Date dayTrdAprvlUpdtDt) {
        this.dayTrdAprvlUpdtDt = dayTrdAprvlUpdtDt;
    }

    public String getDayTrdAprvlUpdtUIntrlId() {
        return dayTrdAprvlUpdtUIntrlId;
    }

    public void setDayTrdAprvlUpdtUIntrlId(String dayTrdAprvlUpdtUIntrlId) {
        this.dayTrdAprvlUpdtUIntrlId = dayTrdAprvlUpdtUIntrlId == null ? null : dayTrdAprvlUpdtUIntrlId.trim();
    }

    public Date getDayTrdAprvlReqDt() {
        return dayTrdAprvlReqDt;
    }

    public void setDayTrdAprvlReqDt(Date dayTrdAprvlReqDt) {
        this.dayTrdAprvlReqDt = dayTrdAprvlReqDt;
    }

    public BigDecimal getStbltySurvlLevelNb() {
        return stbltySurvlLevelNb;
    }

    public void setStbltySurvlLevelNb(BigDecimal stbltySurvlLevelNb) {
        this.stbltySurvlLevelNb = stbltySurvlLevelNb;
    }

    public String getAcctRptngCrncyCd() {
        return acctRptngCrncyCd;
    }

    public void setAcctRptngCrncyCd(String acctRptngCrncyCd) {
        this.acctRptngCrncyCd = acctRptngCrncyCd == null ? null : acctRptngCrncyCd.trim();
    }

    public String getDmcldBrchCd() {
        return dmcldBrchCd;
    }

    public void setDmcldBrchCd(String dmcldBrchCd) {
        this.dmcldBrchCd = dmcldBrchCd == null ? null : dmcldBrchCd.trim();
    }

    public String getPrcsngBatchNm() {
        return prcsngBatchNm;
    }

    public void setPrcsngBatchNm(String prcsngBatchNm) {
        this.prcsngBatchNm = prcsngBatchNm == null ? null : prcsngBatchNm.trim();
    }

    public String getUltmtInstlCustIntrlId() {
        return ultmtInstlCustIntrlId;
    }

    public void setUltmtInstlCustIntrlId(String ultmtInstlCustIntrlId) {
        this.ultmtInstlCustIntrlId = ultmtInstlCustIntrlId == null ? null : ultmtInstlCustIntrlId.trim();
    }

    public String getDvpRvpFl() {
        return dvpRvpFl;
    }

    public void setDvpRvpFl(String dvpRvpFl) {
        this.dvpRvpFl = dvpRvpFl == null ? null : dvpRvpFl.trim();
    }

    public String getMantasAcctHoldrTypeCd() {
        return mantasAcctHoldrTypeCd;
    }

    public void setMantasAcctHoldrTypeCd(String mantasAcctHoldrTypeCd) {
        this.mantasAcctHoldrTypeCd = mantasAcctHoldrTypeCd == null ? null : mantasAcctHoldrTypeCd.trim();
    }

    public String getMantasAcctBusTypeCd() {
        return mantasAcctBusTypeCd;
    }

    public void setMantasAcctBusTypeCd(String mantasAcctBusTypeCd) {
        this.mantasAcctBusTypeCd = mantasAcctBusTypeCd == null ? null : mantasAcctBusTypeCd.trim();
    }

    public String getMantasAcctOwnrshpTypeCd() {
        return mantasAcctOwnrshpTypeCd;
    }

    public void setMantasAcctOwnrshpTypeCd(String mantasAcctOwnrshpTypeCd) {
        this.mantasAcctOwnrshpTypeCd = mantasAcctOwnrshpTypeCd == null ? null : mantasAcctOwnrshpTypeCd.trim();
    }

    public String getMantasAcctPurpCd() {
        return mantasAcctPurpCd;
    }

    public void setMantasAcctPurpCd(String mantasAcctPurpCd) {
        this.mantasAcctPurpCd = mantasAcctPurpCd == null ? null : mantasAcctPurpCd.trim();
    }

    public String getRetrmtAcctFl() {
        return retrmtAcctFl;
    }

    public void setRetrmtAcctFl(String retrmtAcctFl) {
        this.retrmtAcctFl = retrmtAcctFl == null ? null : retrmtAcctFl.trim();
    }

    public String getJrsdcnCd() {
        return jrsdcnCd;
    }

    public void setJrsdcnCd(String jrsdcnCd) {
        this.jrsdcnCd = jrsdcnCd == null ? null : jrsdcnCd.trim();
    }

    public String getBusDmnListTx() {
        return busDmnListTx;
    }

    public void setBusDmnListTx(String busDmnListTx) {
        this.busDmnListTx = busDmnListTx == null ? null : busDmnListTx.trim();
    }

    public String getHighActvyAcctFl() {
        return highActvyAcctFl;
    }

    public void setHighActvyAcctFl(String highActvyAcctFl) {
        this.highActvyAcctFl = highActvyAcctFl == null ? null : highActvyAcctFl.trim();
    }

    public String getFirmAcctOrgIntrlId() {
        return firmAcctOrgIntrlId;
    }

    public void setFirmAcctOrgIntrlId(String firmAcctOrgIntrlId) {
        this.firmAcctOrgIntrlId = firmAcctOrgIntrlId == null ? null : firmAcctOrgIntrlId.trim();
    }

    public String getPayBasisTypeCd() {
        return payBasisTypeCd;
    }

    public void setPayBasisTypeCd(String payBasisTypeCd) {
        this.payBasisTypeCd = payBasisTypeCd == null ? null : payBasisTypeCd.trim();
    }

    public String getCashRptExmptFl() {
        return cashRptExmptFl;
    }

    public void setCashRptExmptFl(String cashRptExmptFl) {
        this.cashRptExmptFl = cashRptExmptFl == null ? null : cashRptExmptFl.trim();
    }

    public String getAcctEfctvRiskFactrTx() {
        return acctEfctvRiskFactrTx;
    }

    public void setAcctEfctvRiskFactrTx(String acctEfctvRiskFactrTx) {
        this.acctEfctvRiskFactrTx = acctEfctvRiskFactrTx == null ? null : acctEfctvRiskFactrTx.trim();
    }

    public String getAcctCustListSrcCd() {
        return acctCustListSrcCd;
    }

    public void setAcctCustListSrcCd(String acctCustListSrcCd) {
        this.acctCustListSrcCd = acctCustListSrcCd == null ? null : acctCustListSrcCd.trim();
    }

    public String getSrvcTeamIntrlId() {
        return srvcTeamIntrlId;
    }

    public void setSrvcTeamIntrlId(String srvcTeamIntrlId) {
        this.srvcTeamIntrlId = srvcTeamIntrlId == null ? null : srvcTeamIntrlId.trim();
    }

    public String getFeeSchedCd() {
        return feeSchedCd;
    }

    public void setFeeSchedCd(String feeSchedCd) {
        this.feeSchedCd = feeSchedCd == null ? null : feeSchedCd.trim();
    }

    public Date getPayBasisStartDt() {
        return payBasisStartDt;
    }

    public void setPayBasisStartDt(Date payBasisStartDt) {
        this.payBasisStartDt = payBasisStartDt;
    }

    public String getRiskTlrncCd() {
        return riskTlrncCd;
    }

    public void setRiskTlrncCd(String riskTlrncCd) {
        this.riskTlrncCd = riskTlrncCd == null ? null : riskTlrncCd.trim();
    }

    public String getPrmryCustPinNb() {
        return prmryCustPinNb;
    }

    public void setPrmryCustPinNb(String prmryCustPinNb) {
        this.prmryCustPinNb = prmryCustPinNb == null ? null : prmryCustPinNb.trim();
    }

    public String getAcctPeerGrpIntrlId() {
        return acctPeerGrpIntrlId;
    }

    public void setAcctPeerGrpIntrlId(String acctPeerGrpIntrlId) {
        this.acctPeerGrpIntrlId = acctPeerGrpIntrlId == null ? null : acctPeerGrpIntrlId.trim();
    }

    public String getPrtfloMgrIntrlId() {
        return prtfloMgrIntrlId;
    }

    public void setPrtfloMgrIntrlId(String prtfloMgrIntrlId) {
        this.prtfloMgrIntrlId = prtfloMgrIntrlId == null ? null : prtfloMgrIntrlId.trim();
    }

    public String getHighPrflFl() {
        return highPrflFl;
    }

    public void setHighPrflFl(String highPrflFl) {
        this.highPrflFl = highPrflFl == null ? null : highPrflFl.trim();
    }

    public String getProdIntrlId() {
        return prodIntrlId;
    }

    public void setProdIntrlId(String prodIntrlId) {
        this.prodIntrlId = prodIntrlId == null ? null : prodIntrlId.trim();
    }

    public String getSrcInitFunds() {
        return srcInitFunds;
    }

    public void setSrcInitFunds(String srcInitFunds) {
        this.srcInitFunds = srcInitFunds == null ? null : srcInitFunds.trim();
    }

    public String getMthdInitFunds() {
        return mthdInitFunds;
    }

    public void setMthdInitFunds(String mthdInitFunds) {
        this.mthdInitFunds = mthdInitFunds == null ? null : mthdInitFunds.trim();
    }

    public String getMthdAcctOpng() {
        return mthdAcctOpng;
    }

    public void setMthdAcctOpng(String mthdAcctOpng) {
        this.mthdAcctOpng = mthdAcctOpng == null ? null : mthdAcctOpng.trim();
    }

    public Date getAcctCloseDt() {
        return acctCloseDt;
    }

    public void setAcctCloseDt(Date acctCloseDt) {
        this.acctCloseDt = acctCloseDt;
    }

    public String getAcctRptCatCd() {
        return acctRptCatCd;
    }

    public void setAcctRptCatCd(String acctRptCatCd) {
        this.acctRptCatCd = acctRptCatCd == null ? null : acctRptCatCd.trim();
    }

    public String getAdminCd() {
        return adminCd;
    }

    public void setAdminCd(String adminCd) {
        this.adminCd = adminCd == null ? null : adminCd.trim();
    }

    public String getBkupAdminCd() {
        return bkupAdminCd;
    }

    public void setBkupAdminCd(String bkupAdminCd) {
        this.bkupAdminCd = bkupAdminCd == null ? null : bkupAdminCd.trim();
    }

    public String getBkngCtrCd() {
        return bkngCtrCd;
    }

    public void setBkngCtrCd(String bkngCtrCd) {
        this.bkngCtrCd = bkngCtrCd == null ? null : bkngCtrCd.trim();
    }

    public String getCpctyCd() {
        return cpctyCd;
    }

    public void setCpctyCd(String cpctyCd) {
        this.cpctyCd = cpctyCd == null ? null : cpctyCd.trim();
    }

    public String getCntrlGrpCd() {
        return cntrlGrpCd;
    }

    public void setCntrlGrpCd(String cntrlGrpCd) {
        this.cntrlGrpCd = cntrlGrpCd == null ? null : cntrlGrpCd.trim();
    }

    public String getCntrlId() {
        return cntrlId;
    }

    public void setCntrlId(String cntrlId) {
        this.cntrlId = cntrlId == null ? null : cntrlId.trim();
    }

    public String getHardHoldFl() {
        return hardHoldFl;
    }

    public void setHardHoldFl(String hardHoldFl) {
        this.hardHoldFl = hardHoldFl == null ? null : hardHoldFl.trim();
    }

    public String getLocalAcctOffcr() {
        return localAcctOffcr;
    }

    public void setLocalAcctOffcr(String localAcctOffcr) {
        this.localAcctOffcr = localAcctOffcr == null ? null : localAcctOffcr.trim();
    }

    public String getPledgeFl() {
        return pledgeFl;
    }

    public void setPledgeFl(String pledgeFl) {
        this.pledgeFl = pledgeFl == null ? null : pledgeFl.trim();
    }

    public String getRealEstOffcrCd() {
        return realEstOffcrCd;
    }

    public void setRealEstOffcrCd(String realEstOffcrCd) {
        this.realEstOffcrCd = realEstOffcrCd == null ? null : realEstOffcrCd.trim();
    }

    public String getRlshpId() {
        return rlshpId;
    }

    public void setRlshpId(String rlshpId) {
        this.rlshpId = rlshpId == null ? null : rlshpId.trim();
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd == null ? null : stateCd.trim();
    }

    public String getStopPaymtFl() {
        return stopPaymtFl;
    }

    public void setStopPaymtFl(String stopPaymtFl) {
        this.stopPaymtFl = stopPaymtFl == null ? null : stopPaymtFl.trim();
    }

    public Date getTrstReviewDt() {
        return trstReviewDt;
    }

    public void setTrstReviewDt(Date trstReviewDt) {
        this.trstReviewDt = trstReviewDt;
    }

    public String getTitlLine4Tx() {
        return titlLine4Tx;
    }

    public void setTitlLine4Tx(String titlLine4Tx) {
        this.titlLine4Tx = titlLine4Tx == null ? null : titlLine4Tx.trim();
    }

    public String getNvsmtObjCd() {
        return nvsmtObjCd;
    }

    public void setNvsmtObjCd(String nvsmtObjCd) {
        this.nvsmtObjCd = nvsmtObjCd == null ? null : nvsmtObjCd.trim();
    }

    public String getNvsmtOffcrCd() {
        return nvsmtOffcrCd;
    }

    public void setNvsmtOffcrCd(String nvsmtOffcrCd) {
        this.nvsmtOffcrCd = nvsmtOffcrCd == null ? null : nvsmtOffcrCd.trim();
    }

    public Date getAvpDt() {
        return avpDt;
    }

    public void setAvpDt(Date avpDt) {
        this.avpDt = avpDt;
    }

    public String getBllngPrdctCd() {
        return bllngPrdctCd;
    }

    public void setBllngPrdctCd(String bllngPrdctCd) {
        this.bllngPrdctCd = bllngPrdctCd == null ? null : bllngPrdctCd.trim();
    }

    public String getDscrnCd() {
        return dscrnCd;
    }

    public void setDscrnCd(String dscrnCd) {
        this.dscrnCd = dscrnCd == null ? null : dscrnCd.trim();
    }

    public String getHoldAllMailCd() {
        return holdAllMailCd;
    }

    public void setHoldAllMailCd(String holdAllMailCd) {
        this.holdAllMailCd = holdAllMailCd == null ? null : holdAllMailCd.trim();
    }

    public String getIncmRngCd() {
        return incmRngCd;
    }

    public void setIncmRngCd(String incmRngCd) {
        this.incmRngCd = incmRngCd == null ? null : incmRngCd.trim();
    }

    public String getMgrNm() {
        return mgrNm;
    }

    public void setMgrNm(String mgrNm) {
        this.mgrNm = mgrNm == null ? null : mgrNm.trim();
    }

    public String getMangdAcctFl() {
        return mangdAcctFl;
    }

    public void setMangdAcctFl(String mangdAcctFl) {
        this.mangdAcctFl = mangdAcctFl == null ? null : mangdAcctFl.trim();
    }

    public String getGrwthNvsmtObjRnkngCd() {
        return grwthNvsmtObjRnkngCd;
    }

    public void setGrwthNvsmtObjRnkngCd(String grwthNvsmtObjRnkngCd) {
        this.grwthNvsmtObjRnkngCd = grwthNvsmtObjRnkngCd == null ? null : grwthNvsmtObjRnkngCd.trim();
    }

    public String getIncmNvsmtObjRnkngCd() {
        return incmNvsmtObjRnkngCd;
    }

    public void setIncmNvsmtObjRnkngCd(String incmNvsmtObjRnkngCd) {
        this.incmNvsmtObjRnkngCd = incmNvsmtObjRnkngCd == null ? null : incmNvsmtObjRnkngCd.trim();
    }

    public String getLqdtyNvsmtObjRnkngCd() {
        return lqdtyNvsmtObjRnkngCd;
    }

    public void setLqdtyNvsmtObjRnkngCd(String lqdtyNvsmtObjRnkngCd) {
        this.lqdtyNvsmtObjRnkngCd = lqdtyNvsmtObjRnkngCd == null ? null : lqdtyNvsmtObjRnkngCd.trim();
    }

    public String getTaxDffrdNvsmtObjRnkngCd() {
        return taxDffrdNvsmtObjRnkngCd;
    }

    public void setTaxDffrdNvsmtObjRnkngCd(String taxDffrdNvsmtObjRnkngCd) {
        this.taxDffrdNvsmtObjRnkngCd = taxDffrdNvsmtObjRnkngCd == null ? null : taxDffrdNvsmtObjRnkngCd.trim();
    }

    public String getOwnrshpCatCd() {
        return ownrshpCatCd;
    }

    public void setOwnrshpCatCd(String ownrshpCatCd) {
        this.ownrshpCatCd = ownrshpCatCd == null ? null : ownrshpCatCd.trim();
    }

    public String getPlanNbTx() {
        return planNbTx;
    }

    public void setPlanNbTx(String planNbTx) {
        this.planNbTx = planNbTx == null ? null : planNbTx.trim();
    }

    public String getStockOptnPlanFl() {
        return stockOptnPlanFl;
    }

    public void setStockOptnPlanFl(String stockOptnPlanFl) {
        this.stockOptnPlanFl = stockOptnPlanFl == null ? null : stockOptnPlanFl.trim();
    }

    public String getSpcltnFl() {
        return spcltnFl;
    }

    public void setSpcltnFl(String spcltnFl) {
        this.spcltnFl = spcltnFl == null ? null : spcltnFl.trim();
    }

    public String getStyleCd() {
        return styleCd;
    }

    public void setStyleCd(String styleCd) {
        this.styleCd = styleCd == null ? null : styleCd.trim();
    }

    public String getSrvcLevelTx() {
        return srvcLevelTx;
    }

    public void setSrvcLevelTx(String srvcLevelTx) {
        this.srvcLevelTx = srvcLevelTx == null ? null : srvcLevelTx.trim();
    }

    public String getPermAcctIntrlId() {
        return permAcctIntrlId;
    }

    public void setPermAcctIntrlId(String permAcctIntrlId) {
        this.permAcctIntrlId = permAcctIntrlId == null ? null : permAcctIntrlId.trim();
    }

    public String getOfacReasnCd() {
        return ofacReasnCd;
    }

    public void setOfacReasnCd(String ofacReasnCd) {
        this.ofacReasnCd = ofacReasnCd == null ? null : ofacReasnCd.trim();
    }

    public String getSpcltnLvl() {
        return spcltnLvl;
    }

    public void setSpcltnLvl(String spcltnLvl) {
        this.spcltnLvl = spcltnLvl == null ? null : spcltnLvl.trim();
    }

    public String getOptnAprvlCd() {
        return optnAprvlCd;
    }

    public void setOptnAprvlCd(String optnAprvlCd) {
        this.optnAprvlCd = optnAprvlCd == null ? null : optnAprvlCd.trim();
    }

    public String getCnsldtnNbrCd() {
        return cnsldtnNbrCd;
    }

    public void setCnsldtnNbrCd(String cnsldtnNbrCd) {
        this.cnsldtnNbrCd = cnsldtnNbrCd == null ? null : cnsldtnNbrCd.trim();
    }

    public String getDscMangdCd() {
        return dscMangdCd;
    }

    public void setDscMangdCd(String dscMangdCd) {
        this.dscMangdCd = dscMangdCd == null ? null : dscMangdCd.trim();
    }

    public String getLeadAcctFl() {
        return leadAcctFl;
    }

    public void setLeadAcctFl(String leadAcctFl) {
        this.leadAcctFl = leadAcctFl == null ? null : leadAcctFl.trim();
    }

    public String getLeadTrackCd() {
        return leadTrackCd;
    }

    public void setLeadTrackCd(String leadTrackCd) {
        this.leadTrackCd = leadTrackCd == null ? null : leadTrackCd.trim();
    }

    public String getQlfidPlanEmpNm() {
        return qlfidPlanEmpNm;
    }

    public void setQlfidPlanEmpNm(String qlfidPlanEmpNm) {
        this.qlfidPlanEmpNm = qlfidPlanEmpNm == null ? null : qlfidPlanEmpNm.trim();
    }

    public String getRllvrFl() {
        return rllvrFl;
    }

    public void setRllvrFl(String rllvrFl) {
        this.rllvrFl = rllvrFl == null ? null : rllvrFl.trim();
    }

    public String getPrmryClntFl() {
        return prmryClntFl;
    }

    public void setPrmryClntFl(String prmryClntFl) {
        this.prmryClntFl = prmryClntFl == null ? null : prmryClntFl.trim();
    }

    public String getInsdrAcctFl() {
        return insdrAcctFl;
    }

    public void setInsdrAcctFl(String insdrAcctFl) {
        this.insdrAcctFl = insdrAcctFl == null ? null : insdrAcctFl.trim();
    }

    public String getSmallHhFl() {
        return smallHhFl;
    }

    public void setSmallHhFl(String smallHhFl) {
        this.smallHhFl = smallHhFl == null ? null : smallHhFl.trim();
    }

    public String getCpiFl() {
        return cpiFl;
    }

    public void setCpiFl(String cpiFl) {
        this.cpiFl = cpiFl == null ? null : cpiFl.trim();
    }

    public String getStndgInstrUsAcctFl() {
        return stndgInstrUsAcctFl;
    }

    public void setStndgInstrUsAcctFl(String stndgInstrUsAcctFl) {
        this.stndgInstrUsAcctFl = stndgInstrUsAcctFl == null ? null : stndgInstrUsAcctFl.trim();
    }

    public String getUsPoaSignFl() {
        return usPoaSignFl;
    }

    public void setUsPoaSignFl(String usPoaSignFl) {
        this.usPoaSignFl = usPoaSignFl == null ? null : usPoaSignFl.trim();
    }

    public String getRecalcitrantFl() {
        return recalcitrantFl;
    }

    public void setRecalcitrantFl(String recalcitrantFl) {
        this.recalcitrantFl = recalcitrantFl == null ? null : recalcitrantFl.trim();
    }

    public BigDecimal getPriorPrdEndNetWrthAm() {
        return priorPrdEndNetWrthAm;
    }

    public void setPriorPrdEndNetWrthAm(BigDecimal priorPrdEndNetWrthAm) {
        this.priorPrdEndNetWrthAm = priorPrdEndNetWrthAm;
    }

    public Date getNetWrthAmPrdEndDt() {
        return netWrthAmPrdEndDt;
    }

    public void setNetWrthAmPrdEndDt(Date netWrthAmPrdEndDt) {
        this.netWrthAmPrdEndDt = netWrthAmPrdEndDt;
    }

    public String getExtrlNvsmtAcctFl() {
        return extrlNvsmtAcctFl;
    }

    public void setExtrlNvsmtAcctFl(String extrlNvsmtAcctFl) {
        this.extrlNvsmtAcctFl = extrlNvsmtAcctFl == null ? null : extrlNvsmtAcctFl.trim();
    }

    public String getGeoJrsdcnCd() {
        return geoJrsdcnCd;
    }

    public void setGeoJrsdcnCd(String geoJrsdcnCd) {
        this.geoJrsdcnCd = geoJrsdcnCd == null ? null : geoJrsdcnCd.trim();
    }

    public String getFatcaStatusCd() {
        return fatcaStatusCd;
    }

    public void setFatcaStatusCd(String fatcaStatusCd) {
        this.fatcaStatusCd = fatcaStatusCd == null ? null : fatcaStatusCd.trim();
    }

    public String getFatcaPooledRptTypeCd() {
        return fatcaPooledRptTypeCd;
    }

    public void setFatcaPooledRptTypeCd(String fatcaPooledRptTypeCd) {
        this.fatcaPooledRptTypeCd = fatcaPooledRptTypeCd == null ? null : fatcaPooledRptTypeCd.trim();
    }

    public String getFatcaIndiciaFl() {
        return fatcaIndiciaFl;
    }

    public void setFatcaIndiciaFl(String fatcaIndiciaFl) {
        this.fatcaIndiciaFl = fatcaIndiciaFl == null ? null : fatcaIndiciaFl.trim();
    }

    public String getFsdfStgSrc() {
        return fsdfStgSrc;
    }

    public void setFsdfStgSrc(String fsdfStgSrc) {
        this.fsdfStgSrc = fsdfStgSrc == null ? null : fsdfStgSrc.trim();
    }

    public BigDecimal getIntAm() {
        return intAm;
    }

    public void setIntAm(BigDecimal intAm) {
        this.intAm = intAm;
    }

    public BigDecimal getDividendAm() {
        return dividendAm;
    }

    public void setDividendAm(BigDecimal dividendAm) {
        this.dividendAm = dividendAm;
    }

    public BigDecimal getGrossProceedsRdmptAm() {
        return grossProceedsRdmptAm;
    }

    public void setGrossProceedsRdmptAm(BigDecimal grossProceedsRdmptAm) {
        this.grossProceedsRdmptAm = grossProceedsRdmptAm;
    }

    public BigDecimal getOtherIncmAm() {
        return otherIncmAm;
    }

    public void setOtherIncmAm(BigDecimal otherIncmAm) {
        this.otherIncmAm = otherIncmAm;
    }

	public AcctBalPosnSmry getAcctBal() {
		return acctBal;
	}

	public void setAcctBal(AcctBalPosnSmry acctBal) {
		this.acctBal = acctBal;
	}

	public List<String> getAcctIds() {
		return acctIds;
	}

	public void setAcctIds(List<String> acctIds) {
		this.acctIds = acctIds;
	}

	public String getAddrUsageCd() {
		return addrUsageCd;
	}

	public String getAddrCityNm() {
		return addrCityNm;
	}

	public String getAddrCntryCd() {
		return addrCntryCd;
	}

	public String getAddrRgnNm() {
		return addrRgnNm;
	}

	public String getAddrStateCd() {
		return addrStateCd;
	}

	public void setAddrUsageCd(String addrUsageCd) {
		this.addrUsageCd = addrUsageCd;
	}

	public void setAddrCityNm(String addrCityNm) {
		this.addrCityNm = addrCityNm;
	}

	public void setAddrCntryCd(String addrCntryCd) {
		this.addrCntryCd = addrCntryCd;
	}

	public void setAddrRgnNm(String addrRgnNm) {
		this.addrRgnNm = addrRgnNm;
	}

	public void setAddrStateCd(String addrStateCd) {
		this.addrStateCd = addrStateCd;
	}
}