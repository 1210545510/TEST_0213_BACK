package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class KddReview extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8690204351850428030L;

	private BigDecimal reviewId;

    private BigDecimal creatId;

    private Date creatTs;

    private BigDecimal lockId;

    private Date lockTs;

    private String statusCd;

    private Date statusDt;

    private BigDecimal cntryId;

    private BigDecimal clsId;

    private String cntryKeyId;

    private BigDecimal scnroId;

    private String scnroClassCd;

    private BigDecimal pttrnId;

    private String dmnCd;

    private BigDecimal alertCt;

    private String reopnFl;

    private String reasnFl;

    private BigDecimal ownerSeqId;

    private BigDecimal scnroCt;

    private BigDecimal scoreCt;

    private String scoreFl;

    private Date dueDt;

    private BigDecimal autoClsCt;

    private String cstm1Tx;

    private String cstm2Tx;

    private String cstm3Tx;

    private String cstm4Tx;

    private String cstm5Tx;

    private Date cstm1Dt;

    private Date cstm2Dt;

    private Date cstm3Dt;

    private BigDecimal cstm1Rl;

    private BigDecimal cstm2Rl;

    private BigDecimal cstm3Rl;

    private String autoReasnFl;

    private String lastActvyTypeCd;

    private BigDecimal prevMatchCtSmScnro;

    private BigDecimal prevMatchCtAll;

    private String ownerOrg;

    private BigDecimal age;

    private String focalNtityDsplyNm;

    private String focalNtityDsplyId;

    private BigDecimal prevMatchCtSmScnroClass;

    private String sendNtfctnCd;

    private String busDmnSt;

    private String busDmnDsplyNmSt;

    private String jrsdcnCd;

    private String prcsngBatchNm;

    private String prcsngBatchCmpltFl;

    private String hilgtTx;

    private BigDecimal origOwnerSeqId;

    private String clsActvyTypeCd;

    private String clsClassCd;

    private String scnroDisplNm;

    private String extrlRefId;

    private String extrlRefLink;

    private String extrlRefSrcId;

    private BigDecimal rqstAuditSeqId;

    private Date prcsngDt;

    private String reviewTypeCd;

    private BigDecimal alertCaseCt;

    private String lastLinkFl;

    private Date linkUpdtTs;

    private BigDecimal linkUpdtId;

    private String regStatusList;

    private String purgeRuleOverrideFl;

    private String cntryTypeCd;

    private String extrlAlertStatus;

    private String extrlAlertDtls;

    private String extrlAlertDescTx;

    private String reportedBy;

    private String alertPriority;

    private String alertRisk;

    private Date eventEndDt;

    private String eventOccurenceType;

    private Date eventStartDt;

    public BigDecimal getReviewId() {
        return reviewId;
    }

    public void setReviewId(BigDecimal reviewId) {
        this.reviewId = reviewId;
    }

    public BigDecimal getCreatId() {
        return creatId;
    }

    public void setCreatId(BigDecimal creatId) {
        this.creatId = creatId;
    }

    public Date getCreatTs() {
        return creatTs;
    }

    public void setCreatTs(Date creatTs) {
        this.creatTs = creatTs;
    }

    public BigDecimal getLockId() {
        return lockId;
    }

    public void setLockId(BigDecimal lockId) {
        this.lockId = lockId;
    }

    public Date getLockTs() {
        return lockTs;
    }

    public void setLockTs(Date lockTs) {
        this.lockTs = lockTs;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Date getStatusDt() {
        return statusDt;
    }

    public void setStatusDt(Date statusDt) {
        this.statusDt = statusDt;
    }

    public BigDecimal getCntryId() {
        return cntryId;
    }

    public void setCntryId(BigDecimal cntryId) {
        this.cntryId = cntryId;
    }

    public BigDecimal getClsId() {
        return clsId;
    }

    public void setClsId(BigDecimal clsId) {
        this.clsId = clsId;
    }

    public String getCntryKeyId() {
        return cntryKeyId;
    }

    public void setCntryKeyId(String cntryKeyId) {
        this.cntryKeyId = cntryKeyId == null ? null : cntryKeyId.trim();
    }

    public BigDecimal getScnroId() {
        return scnroId;
    }

    public void setScnroId(BigDecimal scnroId) {
        this.scnroId = scnroId;
    }

    public String getScnroClassCd() {
        return scnroClassCd;
    }

    public void setScnroClassCd(String scnroClassCd) {
        this.scnroClassCd = scnroClassCd == null ? null : scnroClassCd.trim();
    }

    public BigDecimal getPttrnId() {
        return pttrnId;
    }

    public void setPttrnId(BigDecimal pttrnId) {
        this.pttrnId = pttrnId;
    }

    public String getDmnCd() {
        return dmnCd;
    }

    public void setDmnCd(String dmnCd) {
        this.dmnCd = dmnCd == null ? null : dmnCd.trim();
    }

    public BigDecimal getAlertCt() {
        return alertCt;
    }

    public void setAlertCt(BigDecimal alertCt) {
        this.alertCt = alertCt;
    }

    public String getReopnFl() {
        return reopnFl;
    }

    public void setReopnFl(String reopnFl) {
        this.reopnFl = reopnFl == null ? null : reopnFl.trim();
    }

    public String getReasnFl() {
        return reasnFl;
    }

    public void setReasnFl(String reasnFl) {
        this.reasnFl = reasnFl == null ? null : reasnFl.trim();
    }

    public BigDecimal getOwnerSeqId() {
        return ownerSeqId;
    }

    public void setOwnerSeqId(BigDecimal ownerSeqId) {
        this.ownerSeqId = ownerSeqId;
    }

    public BigDecimal getScnroCt() {
        return scnroCt;
    }

    public void setScnroCt(BigDecimal scnroCt) {
        this.scnroCt = scnroCt;
    }

    public BigDecimal getScoreCt() {
        return scoreCt;
    }

    public void setScoreCt(BigDecimal scoreCt) {
        this.scoreCt = scoreCt;
    }

    public String getScoreFl() {
        return scoreFl;
    }

    public void setScoreFl(String scoreFl) {
        this.scoreFl = scoreFl == null ? null : scoreFl.trim();
    }

    public Date getDueDt() {
        return dueDt;
    }

    public void setDueDt(Date dueDt) {
        this.dueDt = dueDt;
    }

    public BigDecimal getAutoClsCt() {
        return autoClsCt;
    }

    public void setAutoClsCt(BigDecimal autoClsCt) {
        this.autoClsCt = autoClsCt;
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

    public String getAutoReasnFl() {
        return autoReasnFl;
    }

    public void setAutoReasnFl(String autoReasnFl) {
        this.autoReasnFl = autoReasnFl == null ? null : autoReasnFl.trim();
    }

    public String getLastActvyTypeCd() {
        return lastActvyTypeCd;
    }

    public void setLastActvyTypeCd(String lastActvyTypeCd) {
        this.lastActvyTypeCd = lastActvyTypeCd == null ? null : lastActvyTypeCd.trim();
    }

    public BigDecimal getPrevMatchCtSmScnro() {
        return prevMatchCtSmScnro;
    }

    public void setPrevMatchCtSmScnro(BigDecimal prevMatchCtSmScnro) {
        this.prevMatchCtSmScnro = prevMatchCtSmScnro;
    }

    public BigDecimal getPrevMatchCtAll() {
        return prevMatchCtAll;
    }

    public void setPrevMatchCtAll(BigDecimal prevMatchCtAll) {
        this.prevMatchCtAll = prevMatchCtAll;
    }

    public String getOwnerOrg() {
        return ownerOrg;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg == null ? null : ownerOrg.trim();
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public String getFocalNtityDsplyNm() {
        return focalNtityDsplyNm;
    }

    public void setFocalNtityDsplyNm(String focalNtityDsplyNm) {
        this.focalNtityDsplyNm = focalNtityDsplyNm == null ? null : focalNtityDsplyNm.trim();
    }

    public String getFocalNtityDsplyId() {
        return focalNtityDsplyId;
    }

    public void setFocalNtityDsplyId(String focalNtityDsplyId) {
        this.focalNtityDsplyId = focalNtityDsplyId == null ? null : focalNtityDsplyId.trim();
    }

    public BigDecimal getPrevMatchCtSmScnroClass() {
        return prevMatchCtSmScnroClass;
    }

    public void setPrevMatchCtSmScnroClass(BigDecimal prevMatchCtSmScnroClass) {
        this.prevMatchCtSmScnroClass = prevMatchCtSmScnroClass;
    }

    public String getSendNtfctnCd() {
        return sendNtfctnCd;
    }

    public void setSendNtfctnCd(String sendNtfctnCd) {
        this.sendNtfctnCd = sendNtfctnCd == null ? null : sendNtfctnCd.trim();
    }

    public String getBusDmnSt() {
        return busDmnSt;
    }

    public void setBusDmnSt(String busDmnSt) {
        this.busDmnSt = busDmnSt == null ? null : busDmnSt.trim();
    }

    public String getBusDmnDsplyNmSt() {
        return busDmnDsplyNmSt;
    }

    public void setBusDmnDsplyNmSt(String busDmnDsplyNmSt) {
        this.busDmnDsplyNmSt = busDmnDsplyNmSt == null ? null : busDmnDsplyNmSt.trim();
    }

    public String getJrsdcnCd() {
        return jrsdcnCd;
    }

    public void setJrsdcnCd(String jrsdcnCd) {
        this.jrsdcnCd = jrsdcnCd == null ? null : jrsdcnCd.trim();
    }

    public String getPrcsngBatchNm() {
        return prcsngBatchNm;
    }

    public void setPrcsngBatchNm(String prcsngBatchNm) {
        this.prcsngBatchNm = prcsngBatchNm == null ? null : prcsngBatchNm.trim();
    }

    public String getPrcsngBatchCmpltFl() {
        return prcsngBatchCmpltFl;
    }

    public void setPrcsngBatchCmpltFl(String prcsngBatchCmpltFl) {
        this.prcsngBatchCmpltFl = prcsngBatchCmpltFl == null ? null : prcsngBatchCmpltFl.trim();
    }

    public String getHilgtTx() {
        return hilgtTx;
    }

    public void setHilgtTx(String hilgtTx) {
        this.hilgtTx = hilgtTx == null ? null : hilgtTx.trim();
    }

    public BigDecimal getOrigOwnerSeqId() {
        return origOwnerSeqId;
    }

    public void setOrigOwnerSeqId(BigDecimal origOwnerSeqId) {
        this.origOwnerSeqId = origOwnerSeqId;
    }

    public String getClsActvyTypeCd() {
        return clsActvyTypeCd;
    }

    public void setClsActvyTypeCd(String clsActvyTypeCd) {
        this.clsActvyTypeCd = clsActvyTypeCd == null ? null : clsActvyTypeCd.trim();
    }

    public String getClsClassCd() {
        return clsClassCd;
    }

    public void setClsClassCd(String clsClassCd) {
        this.clsClassCd = clsClassCd == null ? null : clsClassCd.trim();
    }

    public String getScnroDisplNm() {
        return scnroDisplNm;
    }

    public void setScnroDisplNm(String scnroDisplNm) {
        this.scnroDisplNm = scnroDisplNm == null ? null : scnroDisplNm.trim();
    }

    public String getExtrlRefId() {
        return extrlRefId;
    }

    public void setExtrlRefId(String extrlRefId) {
        this.extrlRefId = extrlRefId == null ? null : extrlRefId.trim();
    }

    public String getExtrlRefLink() {
        return extrlRefLink;
    }

    public void setExtrlRefLink(String extrlRefLink) {
        this.extrlRefLink = extrlRefLink == null ? null : extrlRefLink.trim();
    }

    public String getExtrlRefSrcId() {
        return extrlRefSrcId;
    }

    public void setExtrlRefSrcId(String extrlRefSrcId) {
        this.extrlRefSrcId = extrlRefSrcId == null ? null : extrlRefSrcId.trim();
    }

    public BigDecimal getRqstAuditSeqId() {
        return rqstAuditSeqId;
    }

    public void setRqstAuditSeqId(BigDecimal rqstAuditSeqId) {
        this.rqstAuditSeqId = rqstAuditSeqId;
    }

    public Date getPrcsngDt() {
        return prcsngDt;
    }

    public void setPrcsngDt(Date prcsngDt) {
        this.prcsngDt = prcsngDt;
    }

    public String getReviewTypeCd() {
        return reviewTypeCd;
    }

    public void setReviewTypeCd(String reviewTypeCd) {
        this.reviewTypeCd = reviewTypeCd == null ? null : reviewTypeCd.trim();
    }

    public BigDecimal getAlertCaseCt() {
        return alertCaseCt;
    }

    public void setAlertCaseCt(BigDecimal alertCaseCt) {
        this.alertCaseCt = alertCaseCt;
    }

    public String getLastLinkFl() {
        return lastLinkFl;
    }

    public void setLastLinkFl(String lastLinkFl) {
        this.lastLinkFl = lastLinkFl == null ? null : lastLinkFl.trim();
    }

    public Date getLinkUpdtTs() {
        return linkUpdtTs;
    }

    public void setLinkUpdtTs(Date linkUpdtTs) {
        this.linkUpdtTs = linkUpdtTs;
    }

    public BigDecimal getLinkUpdtId() {
        return linkUpdtId;
    }

    public void setLinkUpdtId(BigDecimal linkUpdtId) {
        this.linkUpdtId = linkUpdtId;
    }

    public String getRegStatusList() {
        return regStatusList;
    }

    public void setRegStatusList(String regStatusList) {
        this.regStatusList = regStatusList == null ? null : regStatusList.trim();
    }

    public String getPurgeRuleOverrideFl() {
        return purgeRuleOverrideFl;
    }

    public void setPurgeRuleOverrideFl(String purgeRuleOverrideFl) {
        this.purgeRuleOverrideFl = purgeRuleOverrideFl == null ? null : purgeRuleOverrideFl.trim();
    }

    public String getCntryTypeCd() {
        return cntryTypeCd;
    }

    public void setCntryTypeCd(String cntryTypeCd) {
        this.cntryTypeCd = cntryTypeCd == null ? null : cntryTypeCd.trim();
    }

    public String getExtrlAlertStatus() {
        return extrlAlertStatus;
    }

    public void setExtrlAlertStatus(String extrlAlertStatus) {
        this.extrlAlertStatus = extrlAlertStatus == null ? null : extrlAlertStatus.trim();
    }

    public String getExtrlAlertDtls() {
        return extrlAlertDtls;
    }

    public void setExtrlAlertDtls(String extrlAlertDtls) {
        this.extrlAlertDtls = extrlAlertDtls == null ? null : extrlAlertDtls.trim();
    }

    public String getExtrlAlertDescTx() {
        return extrlAlertDescTx;
    }

    public void setExtrlAlertDescTx(String extrlAlertDescTx) {
        this.extrlAlertDescTx = extrlAlertDescTx == null ? null : extrlAlertDescTx.trim();
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy == null ? null : reportedBy.trim();
    }

    public String getAlertPriority() {
        return alertPriority;
    }

    public void setAlertPriority(String alertPriority) {
        this.alertPriority = alertPriority == null ? null : alertPriority.trim();
    }

    public String getAlertRisk() {
        return alertRisk;
    }

    public void setAlertRisk(String alertRisk) {
        this.alertRisk = alertRisk == null ? null : alertRisk.trim();
    }

    public Date getEventEndDt() {
        return eventEndDt;
    }

    public void setEventEndDt(Date eventEndDt) {
        this.eventEndDt = eventEndDt;
    }

    public String getEventOccurenceType() {
        return eventOccurenceType;
    }

    public void setEventOccurenceType(String eventOccurenceType) {
        this.eventOccurenceType = eventOccurenceType == null ? null : eventOccurenceType.trim();
    }

    public Date getEventStartDt() {
        return eventStartDt;
    }

    public void setEventStartDt(Date eventStartDt) {
        this.eventStartDt = eventStartDt;
    }
}