package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AcctTrxnSmryDaily {
    private String acctIntrlId;

    private Date dataDumpDt;
    
    private String prcsngBatchNm;

    private BigDecimal acctDlySmrySeqId;

    private BigDecimal interHhJrnlInCt;

    private BigDecimal interHhJrnlInAm;

    private BigDecimal interHhJrnlOutCt;

    private BigDecimal interHhJrnlOutAm;

    private BigDecimal interHhScrtyJrnlInCt;

    private BigDecimal interHhScrtyJrnlOutCt;

    private BigDecimal totDepstCt;

    private BigDecimal totDepstAm;

    private BigDecimal totWdrwlCt;

    private BigDecimal totWdrwlAm;

    private BigDecimal wireTrxnInCt;

    private BigDecimal wireTrxnInAm;

    private BigDecimal wireTrxnOutCt;

    private BigDecimal wireTrxnOutAm;

    private BigDecimal hrgWireTrxnInCt;

    private BigDecimal hrgWireTrxnInAm;

    private BigDecimal hrgWireTrxnOutCt;

    private BigDecimal hrgWireTrxnOutAm;

    private BigDecimal frgnWireTrxnInCt;

    private BigDecimal frgnWireTrxnInAm;

    private BigDecimal frgnWireTrxnOutCt;

    private BigDecimal frgnWireTrxnOutAm;

    private BigDecimal thrdPartyWireInCt;

    private BigDecimal thrdPartyWireInAm;

    private BigDecimal thrdPartyWiresOutCt;

    private BigDecimal thrdPartyWiresOutAm;

    private BigDecimal hrgChkTrxnInCt;

    private BigDecimal hrgChkTrxnInAm;

    private BigDecimal hrgChkTrxnOutCt;

    private BigDecimal hrgChkTrxnOutAm;

    private BigDecimal hrgClTrxnInCt;

    private BigDecimal hrgClTrxnInAm;

    private BigDecimal hrgClTrxnOutCt;

    private BigDecimal hrgClTrxnOutAm;

    private BigDecimal frgnChkTrxnInCt;

    private BigDecimal frgnChkTrxnInAm;

    private BigDecimal frgnChkTrxnOutCt;

    private BigDecimal frgnChkTrxnOutAm;

    private BigDecimal cashEquivTrxnInCt;

    private BigDecimal cashEquivTrxnInAm;

    private BigDecimal cashEquivTrxnOutCt;

    private BigDecimal cashEquivTrxnOutAm;

    private BigDecimal scrtyTrxnInCt;

    private BigDecimal scrtyTrxnInAm;

    private BigDecimal scrtyTrxnOutCt;

    private BigDecimal scrtyTrxnOutAm;

    private BigDecimal totChkInCt;

    private BigDecimal totChkInAm;

    private BigDecimal totChkOutCt;

    private BigDecimal totChkOutAm;

    private BigDecimal jrnlTrxnInCt;

    private BigDecimal jrnlTrxnInAm;

    private BigDecimal jrnlTrxnOutCt;

    private BigDecimal jrnlTrxnOutAm;

    private BigDecimal thrdPartyWdrwlCt;

    private BigDecimal thrdPartyWdrwlAm;

    private BigDecimal thrdPartyChkDsbmtCt;

    private BigDecimal thrdPartyChkDsbmtAm;

    private BigDecimal cashTrxnInCt;

    private BigDecimal cashTrxnInAm;

    private BigDecimal cashTrxnOutCt;

    private BigDecimal cashTrxnOutAm;

    private BigDecimal eiTrxnInCt;

    private BigDecimal eiTrxnInAm;

    private BigDecimal eiTrxnOutCt;

    private BigDecimal eiTrxnOutAm;

    private BigDecimal atmTrxnInCt;

    private BigDecimal atmTrxnInAm;

    private BigDecimal atmTrxnOutCt;

    private BigDecimal atmTrxnOutAm;

    private BigDecimal cmdtyPrecsMtlsInCt;

    private BigDecimal cmdtyPrecsMtlsInAm;

    private BigDecimal cmdtyPrecsMtlsOutCt;

    private BigDecimal cmdtyPrecsMtlsOutAm;

    private BigDecimal dbtCardTrxnCt;

    private BigDecimal dbtCardTrxnAm;

    private BigDecimal dbtCardTrxnDayCt;

    private BigDecimal totDbtMemoAm;

    private BigDecimal totDbtMemoCt;

    private BigDecimal totCdtMemoAm;

    private BigDecimal totCdtMemoCt;

    private BigDecimal disHonrdChkOutCt;

    private BigDecimal disHonrdChkInCt;

    private BigDecimal totNvsmtMgrFeeAm;

    private BigDecimal maxIhhJrnlActvyRiskInNb;

    private BigDecimal maxIhhJrnlActvyRiskOutNb;

    private BigDecimal maxJrnlActvyRiskInNb;

    private BigDecimal maxJrnlActvyRiskOutNb;

    private BigDecimal domWireTrxnInCt;

    private BigDecimal domWireTrxnInAm;

    private BigDecimal domWireTrxnOutCt;

    private BigDecimal domWireTrxnOutAm;

    private BigDecimal maxWireActvyRiskInNb;

    private BigDecimal maxWireActvyRiskOutNb;

    private BigDecimal maxMiActvyRiskInNb;

    private BigDecimal maxMiActvyRiskOutNb;

    private BigDecimal maxCashActvyRiskInNb;

    private BigDecimal maxCashActvyRiskOutNb;

    private BigDecimal maxCheckActvyRiskInNb;

    private BigDecimal maxCheckActvyRiskOutNb;

    private BigDecimal maxCmdtyActvyRiskInNb;

    private BigDecimal maxCmdtyActvyRiskOutNb;

    private BigDecimal rdcAllInCt;

    private BigDecimal rdcAllInAm;

    private BigDecimal rdcMobileInCt;

    private BigDecimal rdcMobileInAm;

    private BigDecimal rdcPcInCt;

    private BigDecimal rdcPcInAm;

    private BigDecimal rdcTerminalInCt;

    private BigDecimal rdcTerminalInAm;

    private BigDecimal rdcMerchantInCt;

    private BigDecimal rdcMerchantInAm;

    private BigDecimal rdcLockBoxInCt;

    private BigDecimal rdcLockBoxInAm;

    private BigDecimal rdcRedepositInCt;

    private BigDecimal rdcRedepositInAm;

    private BigDecimal rdcKlikInCt;

    private BigDecimal rdcKlikInAm;

    private BigDecimal rdcClientInCt;

    private BigDecimal rdcClientInAm;

    private BigDecimal rdcVendorInCt;

    private BigDecimal rdcVendorInAm;

    private BigDecimal rdcOtherInCt;

    private BigDecimal rdcOtherInAm;

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

	public String getPrcsngBatchNm() {
        return prcsngBatchNm;
    }

    public void setPrcsngBatchNm(String prcsngBatchNm) {
        this.prcsngBatchNm = prcsngBatchNm == null ? null : prcsngBatchNm.trim();
    }

    public BigDecimal getAcctDlySmrySeqId() {
        return acctDlySmrySeqId;
    }

    public void setAcctDlySmrySeqId(BigDecimal acctDlySmrySeqId) {
        this.acctDlySmrySeqId = acctDlySmrySeqId;
    }

    public BigDecimal getInterHhJrnlInCt() {
        return interHhJrnlInCt;
    }

    public void setInterHhJrnlInCt(BigDecimal interHhJrnlInCt) {
        this.interHhJrnlInCt = interHhJrnlInCt;
    }

    public BigDecimal getInterHhJrnlInAm() {
        return interHhJrnlInAm;
    }

    public void setInterHhJrnlInAm(BigDecimal interHhJrnlInAm) {
        this.interHhJrnlInAm = interHhJrnlInAm;
    }

    public BigDecimal getInterHhJrnlOutCt() {
        return interHhJrnlOutCt;
    }

    public void setInterHhJrnlOutCt(BigDecimal interHhJrnlOutCt) {
        this.interHhJrnlOutCt = interHhJrnlOutCt;
    }

    public BigDecimal getInterHhJrnlOutAm() {
        return interHhJrnlOutAm;
    }

    public void setInterHhJrnlOutAm(BigDecimal interHhJrnlOutAm) {
        this.interHhJrnlOutAm = interHhJrnlOutAm;
    }

    public BigDecimal getInterHhScrtyJrnlInCt() {
        return interHhScrtyJrnlInCt;
    }

    public void setInterHhScrtyJrnlInCt(BigDecimal interHhScrtyJrnlInCt) {
        this.interHhScrtyJrnlInCt = interHhScrtyJrnlInCt;
    }

    public BigDecimal getInterHhScrtyJrnlOutCt() {
        return interHhScrtyJrnlOutCt;
    }

    public void setInterHhScrtyJrnlOutCt(BigDecimal interHhScrtyJrnlOutCt) {
        this.interHhScrtyJrnlOutCt = interHhScrtyJrnlOutCt;
    }

    public BigDecimal getTotDepstCt() {
        return totDepstCt;
    }

    public void setTotDepstCt(BigDecimal totDepstCt) {
        this.totDepstCt = totDepstCt;
    }

    public BigDecimal getTotDepstAm() {
        return totDepstAm;
    }

    public void setTotDepstAm(BigDecimal totDepstAm) {
        this.totDepstAm = totDepstAm;
    }

    public BigDecimal getTotWdrwlCt() {
        return totWdrwlCt;
    }

    public void setTotWdrwlCt(BigDecimal totWdrwlCt) {
        this.totWdrwlCt = totWdrwlCt;
    }

    public BigDecimal getTotWdrwlAm() {
        return totWdrwlAm;
    }

    public void setTotWdrwlAm(BigDecimal totWdrwlAm) {
        this.totWdrwlAm = totWdrwlAm;
    }

    public BigDecimal getWireTrxnInCt() {
        return wireTrxnInCt;
    }

    public void setWireTrxnInCt(BigDecimal wireTrxnInCt) {
        this.wireTrxnInCt = wireTrxnInCt;
    }

    public BigDecimal getWireTrxnInAm() {
        return wireTrxnInAm;
    }

    public void setWireTrxnInAm(BigDecimal wireTrxnInAm) {
        this.wireTrxnInAm = wireTrxnInAm;
    }

    public BigDecimal getWireTrxnOutCt() {
        return wireTrxnOutCt;
    }

    public void setWireTrxnOutCt(BigDecimal wireTrxnOutCt) {
        this.wireTrxnOutCt = wireTrxnOutCt;
    }

    public BigDecimal getWireTrxnOutAm() {
        return wireTrxnOutAm;
    }

    public void setWireTrxnOutAm(BigDecimal wireTrxnOutAm) {
        this.wireTrxnOutAm = wireTrxnOutAm;
    }

    public BigDecimal getHrgWireTrxnInCt() {
        return hrgWireTrxnInCt;
    }

    public void setHrgWireTrxnInCt(BigDecimal hrgWireTrxnInCt) {
        this.hrgWireTrxnInCt = hrgWireTrxnInCt;
    }

    public BigDecimal getHrgWireTrxnInAm() {
        return hrgWireTrxnInAm;
    }

    public void setHrgWireTrxnInAm(BigDecimal hrgWireTrxnInAm) {
        this.hrgWireTrxnInAm = hrgWireTrxnInAm;
    }

    public BigDecimal getHrgWireTrxnOutCt() {
        return hrgWireTrxnOutCt;
    }

    public void setHrgWireTrxnOutCt(BigDecimal hrgWireTrxnOutCt) {
        this.hrgWireTrxnOutCt = hrgWireTrxnOutCt;
    }

    public BigDecimal getHrgWireTrxnOutAm() {
        return hrgWireTrxnOutAm;
    }

    public void setHrgWireTrxnOutAm(BigDecimal hrgWireTrxnOutAm) {
        this.hrgWireTrxnOutAm = hrgWireTrxnOutAm;
    }

    public BigDecimal getFrgnWireTrxnInCt() {
        return frgnWireTrxnInCt;
    }

    public void setFrgnWireTrxnInCt(BigDecimal frgnWireTrxnInCt) {
        this.frgnWireTrxnInCt = frgnWireTrxnInCt;
    }

    public BigDecimal getFrgnWireTrxnInAm() {
        return frgnWireTrxnInAm;
    }

    public void setFrgnWireTrxnInAm(BigDecimal frgnWireTrxnInAm) {
        this.frgnWireTrxnInAm = frgnWireTrxnInAm;
    }

    public BigDecimal getFrgnWireTrxnOutCt() {
        return frgnWireTrxnOutCt;
    }

    public void setFrgnWireTrxnOutCt(BigDecimal frgnWireTrxnOutCt) {
        this.frgnWireTrxnOutCt = frgnWireTrxnOutCt;
    }

    public BigDecimal getFrgnWireTrxnOutAm() {
        return frgnWireTrxnOutAm;
    }

    public void setFrgnWireTrxnOutAm(BigDecimal frgnWireTrxnOutAm) {
        this.frgnWireTrxnOutAm = frgnWireTrxnOutAm;
    }

    public BigDecimal getThrdPartyWireInCt() {
        return thrdPartyWireInCt;
    }

    public void setThrdPartyWireInCt(BigDecimal thrdPartyWireInCt) {
        this.thrdPartyWireInCt = thrdPartyWireInCt;
    }

    public BigDecimal getThrdPartyWireInAm() {
        return thrdPartyWireInAm;
    }

    public void setThrdPartyWireInAm(BigDecimal thrdPartyWireInAm) {
        this.thrdPartyWireInAm = thrdPartyWireInAm;
    }

    public BigDecimal getThrdPartyWiresOutCt() {
        return thrdPartyWiresOutCt;
    }

    public void setThrdPartyWiresOutCt(BigDecimal thrdPartyWiresOutCt) {
        this.thrdPartyWiresOutCt = thrdPartyWiresOutCt;
    }

    public BigDecimal getThrdPartyWiresOutAm() {
        return thrdPartyWiresOutAm;
    }

    public void setThrdPartyWiresOutAm(BigDecimal thrdPartyWiresOutAm) {
        this.thrdPartyWiresOutAm = thrdPartyWiresOutAm;
    }

    public BigDecimal getHrgChkTrxnInCt() {
        return hrgChkTrxnInCt;
    }

    public void setHrgChkTrxnInCt(BigDecimal hrgChkTrxnInCt) {
        this.hrgChkTrxnInCt = hrgChkTrxnInCt;
    }

    public BigDecimal getHrgChkTrxnInAm() {
        return hrgChkTrxnInAm;
    }

    public void setHrgChkTrxnInAm(BigDecimal hrgChkTrxnInAm) {
        this.hrgChkTrxnInAm = hrgChkTrxnInAm;
    }

    public BigDecimal getHrgChkTrxnOutCt() {
        return hrgChkTrxnOutCt;
    }

    public void setHrgChkTrxnOutCt(BigDecimal hrgChkTrxnOutCt) {
        this.hrgChkTrxnOutCt = hrgChkTrxnOutCt;
    }

    public BigDecimal getHrgChkTrxnOutAm() {
        return hrgChkTrxnOutAm;
    }

    public void setHrgChkTrxnOutAm(BigDecimal hrgChkTrxnOutAm) {
        this.hrgChkTrxnOutAm = hrgChkTrxnOutAm;
    }

    public BigDecimal getHrgClTrxnInCt() {
        return hrgClTrxnInCt;
    }

    public void setHrgClTrxnInCt(BigDecimal hrgClTrxnInCt) {
        this.hrgClTrxnInCt = hrgClTrxnInCt;
    }

    public BigDecimal getHrgClTrxnInAm() {
        return hrgClTrxnInAm;
    }

    public void setHrgClTrxnInAm(BigDecimal hrgClTrxnInAm) {
        this.hrgClTrxnInAm = hrgClTrxnInAm;
    }

    public BigDecimal getHrgClTrxnOutCt() {
        return hrgClTrxnOutCt;
    }

    public void setHrgClTrxnOutCt(BigDecimal hrgClTrxnOutCt) {
        this.hrgClTrxnOutCt = hrgClTrxnOutCt;
    }

    public BigDecimal getHrgClTrxnOutAm() {
        return hrgClTrxnOutAm;
    }

    public void setHrgClTrxnOutAm(BigDecimal hrgClTrxnOutAm) {
        this.hrgClTrxnOutAm = hrgClTrxnOutAm;
    }

    public BigDecimal getFrgnChkTrxnInCt() {
        return frgnChkTrxnInCt;
    }

    public void setFrgnChkTrxnInCt(BigDecimal frgnChkTrxnInCt) {
        this.frgnChkTrxnInCt = frgnChkTrxnInCt;
    }

    public BigDecimal getFrgnChkTrxnInAm() {
        return frgnChkTrxnInAm;
    }

    public void setFrgnChkTrxnInAm(BigDecimal frgnChkTrxnInAm) {
        this.frgnChkTrxnInAm = frgnChkTrxnInAm;
    }

    public BigDecimal getFrgnChkTrxnOutCt() {
        return frgnChkTrxnOutCt;
    }

    public void setFrgnChkTrxnOutCt(BigDecimal frgnChkTrxnOutCt) {
        this.frgnChkTrxnOutCt = frgnChkTrxnOutCt;
    }

    public BigDecimal getFrgnChkTrxnOutAm() {
        return frgnChkTrxnOutAm;
    }

    public void setFrgnChkTrxnOutAm(BigDecimal frgnChkTrxnOutAm) {
        this.frgnChkTrxnOutAm = frgnChkTrxnOutAm;
    }

    public BigDecimal getCashEquivTrxnInCt() {
        return cashEquivTrxnInCt;
    }

    public void setCashEquivTrxnInCt(BigDecimal cashEquivTrxnInCt) {
        this.cashEquivTrxnInCt = cashEquivTrxnInCt;
    }

    public BigDecimal getCashEquivTrxnInAm() {
        return cashEquivTrxnInAm;
    }

    public void setCashEquivTrxnInAm(BigDecimal cashEquivTrxnInAm) {
        this.cashEquivTrxnInAm = cashEquivTrxnInAm;
    }

    public BigDecimal getCashEquivTrxnOutCt() {
        return cashEquivTrxnOutCt;
    }

    public void setCashEquivTrxnOutCt(BigDecimal cashEquivTrxnOutCt) {
        this.cashEquivTrxnOutCt = cashEquivTrxnOutCt;
    }

    public BigDecimal getCashEquivTrxnOutAm() {
        return cashEquivTrxnOutAm;
    }

    public void setCashEquivTrxnOutAm(BigDecimal cashEquivTrxnOutAm) {
        this.cashEquivTrxnOutAm = cashEquivTrxnOutAm;
    }

    public BigDecimal getScrtyTrxnInCt() {
        return scrtyTrxnInCt;
    }

    public void setScrtyTrxnInCt(BigDecimal scrtyTrxnInCt) {
        this.scrtyTrxnInCt = scrtyTrxnInCt;
    }

    public BigDecimal getScrtyTrxnInAm() {
        return scrtyTrxnInAm;
    }

    public void setScrtyTrxnInAm(BigDecimal scrtyTrxnInAm) {
        this.scrtyTrxnInAm = scrtyTrxnInAm;
    }

    public BigDecimal getScrtyTrxnOutCt() {
        return scrtyTrxnOutCt;
    }

    public void setScrtyTrxnOutCt(BigDecimal scrtyTrxnOutCt) {
        this.scrtyTrxnOutCt = scrtyTrxnOutCt;
    }

    public BigDecimal getScrtyTrxnOutAm() {
        return scrtyTrxnOutAm;
    }

    public void setScrtyTrxnOutAm(BigDecimal scrtyTrxnOutAm) {
        this.scrtyTrxnOutAm = scrtyTrxnOutAm;
    }

    public BigDecimal getTotChkInCt() {
        return totChkInCt;
    }

    public void setTotChkInCt(BigDecimal totChkInCt) {
        this.totChkInCt = totChkInCt;
    }

    public BigDecimal getTotChkInAm() {
        return totChkInAm;
    }

    public void setTotChkInAm(BigDecimal totChkInAm) {
        this.totChkInAm = totChkInAm;
    }

    public BigDecimal getTotChkOutCt() {
        return totChkOutCt;
    }

    public void setTotChkOutCt(BigDecimal totChkOutCt) {
        this.totChkOutCt = totChkOutCt;
    }

    public BigDecimal getTotChkOutAm() {
        return totChkOutAm;
    }

    public void setTotChkOutAm(BigDecimal totChkOutAm) {
        this.totChkOutAm = totChkOutAm;
    }

    public BigDecimal getJrnlTrxnInCt() {
        return jrnlTrxnInCt;
    }

    public void setJrnlTrxnInCt(BigDecimal jrnlTrxnInCt) {
        this.jrnlTrxnInCt = jrnlTrxnInCt;
    }

    public BigDecimal getJrnlTrxnInAm() {
        return jrnlTrxnInAm;
    }

    public void setJrnlTrxnInAm(BigDecimal jrnlTrxnInAm) {
        this.jrnlTrxnInAm = jrnlTrxnInAm;
    }

    public BigDecimal getJrnlTrxnOutCt() {
        return jrnlTrxnOutCt;
    }

    public void setJrnlTrxnOutCt(BigDecimal jrnlTrxnOutCt) {
        this.jrnlTrxnOutCt = jrnlTrxnOutCt;
    }

    public BigDecimal getJrnlTrxnOutAm() {
        return jrnlTrxnOutAm;
    }

    public void setJrnlTrxnOutAm(BigDecimal jrnlTrxnOutAm) {
        this.jrnlTrxnOutAm = jrnlTrxnOutAm;
    }

    public BigDecimal getThrdPartyWdrwlCt() {
        return thrdPartyWdrwlCt;
    }

    public void setThrdPartyWdrwlCt(BigDecimal thrdPartyWdrwlCt) {
        this.thrdPartyWdrwlCt = thrdPartyWdrwlCt;
    }

    public BigDecimal getThrdPartyWdrwlAm() {
        return thrdPartyWdrwlAm;
    }

    public void setThrdPartyWdrwlAm(BigDecimal thrdPartyWdrwlAm) {
        this.thrdPartyWdrwlAm = thrdPartyWdrwlAm;
    }

    public BigDecimal getThrdPartyChkDsbmtCt() {
        return thrdPartyChkDsbmtCt;
    }

    public void setThrdPartyChkDsbmtCt(BigDecimal thrdPartyChkDsbmtCt) {
        this.thrdPartyChkDsbmtCt = thrdPartyChkDsbmtCt;
    }

    public BigDecimal getThrdPartyChkDsbmtAm() {
        return thrdPartyChkDsbmtAm;
    }

    public void setThrdPartyChkDsbmtAm(BigDecimal thrdPartyChkDsbmtAm) {
        this.thrdPartyChkDsbmtAm = thrdPartyChkDsbmtAm;
    }

    public BigDecimal getCashTrxnInCt() {
        return cashTrxnInCt;
    }

    public void setCashTrxnInCt(BigDecimal cashTrxnInCt) {
        this.cashTrxnInCt = cashTrxnInCt;
    }

    public BigDecimal getCashTrxnInAm() {
        return cashTrxnInAm;
    }

    public void setCashTrxnInAm(BigDecimal cashTrxnInAm) {
        this.cashTrxnInAm = cashTrxnInAm;
    }

    public BigDecimal getCashTrxnOutCt() {
        return cashTrxnOutCt;
    }

    public void setCashTrxnOutCt(BigDecimal cashTrxnOutCt) {
        this.cashTrxnOutCt = cashTrxnOutCt;
    }

    public BigDecimal getCashTrxnOutAm() {
        return cashTrxnOutAm;
    }

    public void setCashTrxnOutAm(BigDecimal cashTrxnOutAm) {
        this.cashTrxnOutAm = cashTrxnOutAm;
    }

    public BigDecimal getEiTrxnInCt() {
        return eiTrxnInCt;
    }

    public void setEiTrxnInCt(BigDecimal eiTrxnInCt) {
        this.eiTrxnInCt = eiTrxnInCt;
    }

    public BigDecimal getEiTrxnInAm() {
        return eiTrxnInAm;
    }

    public void setEiTrxnInAm(BigDecimal eiTrxnInAm) {
        this.eiTrxnInAm = eiTrxnInAm;
    }

    public BigDecimal getEiTrxnOutCt() {
        return eiTrxnOutCt;
    }

    public void setEiTrxnOutCt(BigDecimal eiTrxnOutCt) {
        this.eiTrxnOutCt = eiTrxnOutCt;
    }

    public BigDecimal getEiTrxnOutAm() {
        return eiTrxnOutAm;
    }

    public void setEiTrxnOutAm(BigDecimal eiTrxnOutAm) {
        this.eiTrxnOutAm = eiTrxnOutAm;
    }

    public BigDecimal getAtmTrxnInCt() {
        return atmTrxnInCt;
    }

    public void setAtmTrxnInCt(BigDecimal atmTrxnInCt) {
        this.atmTrxnInCt = atmTrxnInCt;
    }

    public BigDecimal getAtmTrxnInAm() {
        return atmTrxnInAm;
    }

    public void setAtmTrxnInAm(BigDecimal atmTrxnInAm) {
        this.atmTrxnInAm = atmTrxnInAm;
    }

    public BigDecimal getAtmTrxnOutCt() {
        return atmTrxnOutCt;
    }

    public void setAtmTrxnOutCt(BigDecimal atmTrxnOutCt) {
        this.atmTrxnOutCt = atmTrxnOutCt;
    }

    public BigDecimal getAtmTrxnOutAm() {
        return atmTrxnOutAm;
    }

    public void setAtmTrxnOutAm(BigDecimal atmTrxnOutAm) {
        this.atmTrxnOutAm = atmTrxnOutAm;
    }

    public BigDecimal getCmdtyPrecsMtlsInCt() {
        return cmdtyPrecsMtlsInCt;
    }

    public void setCmdtyPrecsMtlsInCt(BigDecimal cmdtyPrecsMtlsInCt) {
        this.cmdtyPrecsMtlsInCt = cmdtyPrecsMtlsInCt;
    }

    public BigDecimal getCmdtyPrecsMtlsInAm() {
        return cmdtyPrecsMtlsInAm;
    }

    public void setCmdtyPrecsMtlsInAm(BigDecimal cmdtyPrecsMtlsInAm) {
        this.cmdtyPrecsMtlsInAm = cmdtyPrecsMtlsInAm;
    }

    public BigDecimal getCmdtyPrecsMtlsOutCt() {
        return cmdtyPrecsMtlsOutCt;
    }

    public void setCmdtyPrecsMtlsOutCt(BigDecimal cmdtyPrecsMtlsOutCt) {
        this.cmdtyPrecsMtlsOutCt = cmdtyPrecsMtlsOutCt;
    }

    public BigDecimal getCmdtyPrecsMtlsOutAm() {
        return cmdtyPrecsMtlsOutAm;
    }

    public void setCmdtyPrecsMtlsOutAm(BigDecimal cmdtyPrecsMtlsOutAm) {
        this.cmdtyPrecsMtlsOutAm = cmdtyPrecsMtlsOutAm;
    }

    public BigDecimal getDbtCardTrxnCt() {
        return dbtCardTrxnCt;
    }

    public void setDbtCardTrxnCt(BigDecimal dbtCardTrxnCt) {
        this.dbtCardTrxnCt = dbtCardTrxnCt;
    }

    public BigDecimal getDbtCardTrxnAm() {
        return dbtCardTrxnAm;
    }

    public void setDbtCardTrxnAm(BigDecimal dbtCardTrxnAm) {
        this.dbtCardTrxnAm = dbtCardTrxnAm;
    }

    public BigDecimal getDbtCardTrxnDayCt() {
        return dbtCardTrxnDayCt;
    }

    public void setDbtCardTrxnDayCt(BigDecimal dbtCardTrxnDayCt) {
        this.dbtCardTrxnDayCt = dbtCardTrxnDayCt;
    }

    public BigDecimal getTotDbtMemoAm() {
        return totDbtMemoAm;
    }

    public void setTotDbtMemoAm(BigDecimal totDbtMemoAm) {
        this.totDbtMemoAm = totDbtMemoAm;
    }

    public BigDecimal getTotDbtMemoCt() {
        return totDbtMemoCt;
    }

    public void setTotDbtMemoCt(BigDecimal totDbtMemoCt) {
        this.totDbtMemoCt = totDbtMemoCt;
    }

    public BigDecimal getTotCdtMemoAm() {
        return totCdtMemoAm;
    }

    public void setTotCdtMemoAm(BigDecimal totCdtMemoAm) {
        this.totCdtMemoAm = totCdtMemoAm;
    }

    public BigDecimal getTotCdtMemoCt() {
        return totCdtMemoCt;
    }

    public void setTotCdtMemoCt(BigDecimal totCdtMemoCt) {
        this.totCdtMemoCt = totCdtMemoCt;
    }

    public BigDecimal getDisHonrdChkOutCt() {
        return disHonrdChkOutCt;
    }

    public void setDisHonrdChkOutCt(BigDecimal disHonrdChkOutCt) {
        this.disHonrdChkOutCt = disHonrdChkOutCt;
    }

    public BigDecimal getDisHonrdChkInCt() {
        return disHonrdChkInCt;
    }

    public void setDisHonrdChkInCt(BigDecimal disHonrdChkInCt) {
        this.disHonrdChkInCt = disHonrdChkInCt;
    }

    public BigDecimal getTotNvsmtMgrFeeAm() {
        return totNvsmtMgrFeeAm;
    }

    public void setTotNvsmtMgrFeeAm(BigDecimal totNvsmtMgrFeeAm) {
        this.totNvsmtMgrFeeAm = totNvsmtMgrFeeAm;
    }

    public BigDecimal getMaxIhhJrnlActvyRiskInNb() {
        return maxIhhJrnlActvyRiskInNb;
    }

    public void setMaxIhhJrnlActvyRiskInNb(BigDecimal maxIhhJrnlActvyRiskInNb) {
        this.maxIhhJrnlActvyRiskInNb = maxIhhJrnlActvyRiskInNb;
    }

    public BigDecimal getMaxIhhJrnlActvyRiskOutNb() {
        return maxIhhJrnlActvyRiskOutNb;
    }

    public void setMaxIhhJrnlActvyRiskOutNb(BigDecimal maxIhhJrnlActvyRiskOutNb) {
        this.maxIhhJrnlActvyRiskOutNb = maxIhhJrnlActvyRiskOutNb;
    }

    public BigDecimal getMaxJrnlActvyRiskInNb() {
        return maxJrnlActvyRiskInNb;
    }

    public void setMaxJrnlActvyRiskInNb(BigDecimal maxJrnlActvyRiskInNb) {
        this.maxJrnlActvyRiskInNb = maxJrnlActvyRiskInNb;
    }

    public BigDecimal getMaxJrnlActvyRiskOutNb() {
        return maxJrnlActvyRiskOutNb;
    }

    public void setMaxJrnlActvyRiskOutNb(BigDecimal maxJrnlActvyRiskOutNb) {
        this.maxJrnlActvyRiskOutNb = maxJrnlActvyRiskOutNb;
    }

    public BigDecimal getDomWireTrxnInCt() {
        return domWireTrxnInCt;
    }

    public void setDomWireTrxnInCt(BigDecimal domWireTrxnInCt) {
        this.domWireTrxnInCt = domWireTrxnInCt;
    }

    public BigDecimal getDomWireTrxnInAm() {
        return domWireTrxnInAm;
    }

    public void setDomWireTrxnInAm(BigDecimal domWireTrxnInAm) {
        this.domWireTrxnInAm = domWireTrxnInAm;
    }

    public BigDecimal getDomWireTrxnOutCt() {
        return domWireTrxnOutCt;
    }

    public void setDomWireTrxnOutCt(BigDecimal domWireTrxnOutCt) {
        this.domWireTrxnOutCt = domWireTrxnOutCt;
    }

    public BigDecimal getDomWireTrxnOutAm() {
        return domWireTrxnOutAm;
    }

    public void setDomWireTrxnOutAm(BigDecimal domWireTrxnOutAm) {
        this.domWireTrxnOutAm = domWireTrxnOutAm;
    }

    public BigDecimal getMaxWireActvyRiskInNb() {
        return maxWireActvyRiskInNb;
    }

    public void setMaxWireActvyRiskInNb(BigDecimal maxWireActvyRiskInNb) {
        this.maxWireActvyRiskInNb = maxWireActvyRiskInNb;
    }

    public BigDecimal getMaxWireActvyRiskOutNb() {
        return maxWireActvyRiskOutNb;
    }

    public void setMaxWireActvyRiskOutNb(BigDecimal maxWireActvyRiskOutNb) {
        this.maxWireActvyRiskOutNb = maxWireActvyRiskOutNb;
    }

    public BigDecimal getMaxMiActvyRiskInNb() {
        return maxMiActvyRiskInNb;
    }

    public void setMaxMiActvyRiskInNb(BigDecimal maxMiActvyRiskInNb) {
        this.maxMiActvyRiskInNb = maxMiActvyRiskInNb;
    }

    public BigDecimal getMaxMiActvyRiskOutNb() {
        return maxMiActvyRiskOutNb;
    }

    public void setMaxMiActvyRiskOutNb(BigDecimal maxMiActvyRiskOutNb) {
        this.maxMiActvyRiskOutNb = maxMiActvyRiskOutNb;
    }

    public BigDecimal getMaxCashActvyRiskInNb() {
        return maxCashActvyRiskInNb;
    }

    public void setMaxCashActvyRiskInNb(BigDecimal maxCashActvyRiskInNb) {
        this.maxCashActvyRiskInNb = maxCashActvyRiskInNb;
    }

    public BigDecimal getMaxCashActvyRiskOutNb() {
        return maxCashActvyRiskOutNb;
    }

    public void setMaxCashActvyRiskOutNb(BigDecimal maxCashActvyRiskOutNb) {
        this.maxCashActvyRiskOutNb = maxCashActvyRiskOutNb;
    }

    public BigDecimal getMaxCheckActvyRiskInNb() {
        return maxCheckActvyRiskInNb;
    }

    public void setMaxCheckActvyRiskInNb(BigDecimal maxCheckActvyRiskInNb) {
        this.maxCheckActvyRiskInNb = maxCheckActvyRiskInNb;
    }

    public BigDecimal getMaxCheckActvyRiskOutNb() {
        return maxCheckActvyRiskOutNb;
    }

    public void setMaxCheckActvyRiskOutNb(BigDecimal maxCheckActvyRiskOutNb) {
        this.maxCheckActvyRiskOutNb = maxCheckActvyRiskOutNb;
    }

    public BigDecimal getMaxCmdtyActvyRiskInNb() {
        return maxCmdtyActvyRiskInNb;
    }

    public void setMaxCmdtyActvyRiskInNb(BigDecimal maxCmdtyActvyRiskInNb) {
        this.maxCmdtyActvyRiskInNb = maxCmdtyActvyRiskInNb;
    }

    public BigDecimal getMaxCmdtyActvyRiskOutNb() {
        return maxCmdtyActvyRiskOutNb;
    }

    public void setMaxCmdtyActvyRiskOutNb(BigDecimal maxCmdtyActvyRiskOutNb) {
        this.maxCmdtyActvyRiskOutNb = maxCmdtyActvyRiskOutNb;
    }

    public BigDecimal getRdcAllInCt() {
        return rdcAllInCt;
    }

    public void setRdcAllInCt(BigDecimal rdcAllInCt) {
        this.rdcAllInCt = rdcAllInCt;
    }

    public BigDecimal getRdcAllInAm() {
        return rdcAllInAm;
    }

    public void setRdcAllInAm(BigDecimal rdcAllInAm) {
        this.rdcAllInAm = rdcAllInAm;
    }

    public BigDecimal getRdcMobileInCt() {
        return rdcMobileInCt;
    }

    public void setRdcMobileInCt(BigDecimal rdcMobileInCt) {
        this.rdcMobileInCt = rdcMobileInCt;
    }

    public BigDecimal getRdcMobileInAm() {
        return rdcMobileInAm;
    }

    public void setRdcMobileInAm(BigDecimal rdcMobileInAm) {
        this.rdcMobileInAm = rdcMobileInAm;
    }

    public BigDecimal getRdcPcInCt() {
        return rdcPcInCt;
    }

    public void setRdcPcInCt(BigDecimal rdcPcInCt) {
        this.rdcPcInCt = rdcPcInCt;
    }

    public BigDecimal getRdcPcInAm() {
        return rdcPcInAm;
    }

    public void setRdcPcInAm(BigDecimal rdcPcInAm) {
        this.rdcPcInAm = rdcPcInAm;
    }

    public BigDecimal getRdcTerminalInCt() {
        return rdcTerminalInCt;
    }

    public void setRdcTerminalInCt(BigDecimal rdcTerminalInCt) {
        this.rdcTerminalInCt = rdcTerminalInCt;
    }

    public BigDecimal getRdcTerminalInAm() {
        return rdcTerminalInAm;
    }

    public void setRdcTerminalInAm(BigDecimal rdcTerminalInAm) {
        this.rdcTerminalInAm = rdcTerminalInAm;
    }

    public BigDecimal getRdcMerchantInCt() {
        return rdcMerchantInCt;
    }

    public void setRdcMerchantInCt(BigDecimal rdcMerchantInCt) {
        this.rdcMerchantInCt = rdcMerchantInCt;
    }

    public BigDecimal getRdcMerchantInAm() {
        return rdcMerchantInAm;
    }

    public void setRdcMerchantInAm(BigDecimal rdcMerchantInAm) {
        this.rdcMerchantInAm = rdcMerchantInAm;
    }

    public BigDecimal getRdcLockBoxInCt() {
        return rdcLockBoxInCt;
    }

    public void setRdcLockBoxInCt(BigDecimal rdcLockBoxInCt) {
        this.rdcLockBoxInCt = rdcLockBoxInCt;
    }

    public BigDecimal getRdcLockBoxInAm() {
        return rdcLockBoxInAm;
    }

    public void setRdcLockBoxInAm(BigDecimal rdcLockBoxInAm) {
        this.rdcLockBoxInAm = rdcLockBoxInAm;
    }

    public BigDecimal getRdcRedepositInCt() {
        return rdcRedepositInCt;
    }

    public void setRdcRedepositInCt(BigDecimal rdcRedepositInCt) {
        this.rdcRedepositInCt = rdcRedepositInCt;
    }

    public BigDecimal getRdcRedepositInAm() {
        return rdcRedepositInAm;
    }

    public void setRdcRedepositInAm(BigDecimal rdcRedepositInAm) {
        this.rdcRedepositInAm = rdcRedepositInAm;
    }

    public BigDecimal getRdcKlikInCt() {
        return rdcKlikInCt;
    }

    public void setRdcKlikInCt(BigDecimal rdcKlikInCt) {
        this.rdcKlikInCt = rdcKlikInCt;
    }

    public BigDecimal getRdcKlikInAm() {
        return rdcKlikInAm;
    }

    public void setRdcKlikInAm(BigDecimal rdcKlikInAm) {
        this.rdcKlikInAm = rdcKlikInAm;
    }

    public BigDecimal getRdcClientInCt() {
        return rdcClientInCt;
    }

    public void setRdcClientInCt(BigDecimal rdcClientInCt) {
        this.rdcClientInCt = rdcClientInCt;
    }

    public BigDecimal getRdcClientInAm() {
        return rdcClientInAm;
    }

    public void setRdcClientInAm(BigDecimal rdcClientInAm) {
        this.rdcClientInAm = rdcClientInAm;
    }

    public BigDecimal getRdcVendorInCt() {
        return rdcVendorInCt;
    }

    public void setRdcVendorInCt(BigDecimal rdcVendorInCt) {
        this.rdcVendorInCt = rdcVendorInCt;
    }

    public BigDecimal getRdcVendorInAm() {
        return rdcVendorInAm;
    }

    public void setRdcVendorInAm(BigDecimal rdcVendorInAm) {
        this.rdcVendorInAm = rdcVendorInAm;
    }

    public BigDecimal getRdcOtherInCt() {
        return rdcOtherInCt;
    }

    public void setRdcOtherInCt(BigDecimal rdcOtherInCt) {
        this.rdcOtherInCt = rdcOtherInCt;
    }

    public BigDecimal getRdcOtherInAm() {
        return rdcOtherInAm;
    }

    public void setRdcOtherInAm(BigDecimal rdcOtherInAm) {
        this.rdcOtherInAm = rdcOtherInAm;
    }
}