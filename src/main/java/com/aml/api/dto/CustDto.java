package com.aml.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustDto extends BaseDto {
	private static final long serialVersionUID = -768065901510483336L;
	private String custIntrlId;
	private Date dataDumpDt;
	private BigDecimal custSeqId;
	private Date fnclPrflLastUpdtDt;
	private String custTypeCd;
	private Date custAddDt;
	private String taxId;
	private String taxIdFrmtCd;
	private BigDecimal annlIncmBaseAm;
	private BigDecimal netWrthBaseAm;
	private BigDecimal lqdNetWrthBaseAm;
	private String eqtyKnldgCd;
	private String bndKnldgCd;
	private String optnKnldgCd;
	private String ovrallKnldgCd;
	private String ovrallExpCd;
	private BigDecimal eqtyExpYrQt;
	private BigDecimal bndExpYrQt;
	private BigDecimal optnExpYrQt;
	private BigDecimal annlEqtyTrdQt;
	private BigDecimal annlBndTrdQt;
	private BigDecimal annlOptnTrdQt;
	private BigDecimal avgEqtyTrdAm;
	private BigDecimal avgBndTrdAm;
	private BigDecimal avgOptnTrdAm;
	private String firstNm;
	private String lastNm;
	private String midlNm;
	private String suffNm;
	private String titlNm;
	private String form407Fl;
	private Date birthDt;
	private String ctzshpCntry1Cd;
	private String ctzshpCntry2Cd;
	private String resCntryCd;
	private String mplyrNm;
	private String mplyrFinclInstFl;
	private String mplmtStatCd;
	private String mrtlStatCd;
	private BigDecimal dpndtQt;
	private String ocptnNm;
	private String orgNm;
	private BigDecimal ageYrCt;
	private String ctzshpStatCd;
	private String wlthSrcDscrTx;
	private String empFl;
	private String srcSysCd;
	private String orgLglStrucCd;
	private Date pwdLastChgDt;
	private String aliasNm;
	private String frgnAssetsFl;
	private String fullNm;
	private String mplyrIndusCd;
	private String jobTitlNm;
	private Date cstm1Dt;
	private Date cstm2Dt;
	private Date cstm3Dt;
	private BigDecimal cstm1Rl;
	private BigDecimal cstm2Rl;
	private BigDecimal cstm3Rl;
	private String cstm1Tx;
	private String cstm2Tx;
	private String cstm3Tx;
	private BigDecimal totAcctCt;
	private BigDecimal custEfctvRiskNb;
	private String cstm4Tx;
	private String cstm5Tx;
	private String fnclInstnIdTypeCd;
	private String fnclInstnId;
	private BigDecimal instnSeqId;
	private BigDecimal custListRiskNb;
	private String custListSrcCd;
	private String custMatchTx;
	private String custMatchTypeCd;
	private BigDecimal custBusRiskNb;
	private BigDecimal custGeoRiskNb;
	private BigDecimal cstmRisk1Nb;
	private BigDecimal cstmRisk2Nb;
	private String dmcldBrchOrgId;
	private String dayTrdKnldgCd;
	private String dayTrdExpCd;
	private BigDecimal annlIncmRptgAm;
	private BigDecimal netWrthRptgAm;
	private BigDecimal lqdNetWrthRptgAm;
	private String prcsngBatchNm;
	private String jrsdcnCd;
	private String busDmnListTx;
	private String rptgCrncyCd;
	private String mantasCustBusTypeCd;
	private String custEfctvRiskFactrTx;
	private String custCdtRtng;
	private String custCdtRtngSrc;
	private BigDecimal custCdtScore;
	private String custPeerGrpIntrlId;
	private String custStatCd;
	private String taxtnCntryCd;
	private String custIndusCd;
	private Date custIncDt;
	private String rgstnTypeCd;
	private String publcPrivtCd;
	private String altCustId;
	private String incmRngCd;
	private String custGndrCd;
	private String custNaicsCd;
	private String wlthCompFl;
	private String cmdtyKnldgCd;
	private BigDecimal cmdtyExpYrQt;
	private BigDecimal avgCmdtyTrdAm;
	private BigDecimal annlCmdtyTrdQt;
	private String frgnPublcOffclFl;
	private String empRlshpTypeCd;
	private BigDecimal retrmtYear;
	private BigDecimal maxDailyAtmWdrwlAm;
	private String recalcitrantFl;
	private String nonUsCtznResFl;
	private String nonUsDocHolderFl;
	private String certLossNationFl;
	private String cntryOfBirth;
	private String cntryOfInc;
	private String fatcaExemptCd;
	private String govtDocFl;
	private String thrdPartyCdtRptFl;
	private String frgnTaxRptngCertFl;
	private String exceptedNffeFl;
	private String fatcaOrgTypeCd;
	private String geoJrsdcnCd;
	private String search;

	public String getCustIntrlId () {
		return custIntrlId;
	}

	public void setCustIntrlId ( String custIntrlId ) {
		this.custIntrlId = custIntrlId == null ? null : custIntrlId.trim();
	}

	public Date getDataDumpDt () {
		return dataDumpDt;
	}

	public void setDataDumpDt ( Date dataDumpDt ) {
		this.dataDumpDt = dataDumpDt;
	}

	public BigDecimal getCustSeqId () {
		return custSeqId;
	}

	public void setCustSeqId ( BigDecimal custSeqId ) {
		this.custSeqId = custSeqId;
	}

	public Date getFnclPrflLastUpdtDt () {
		return fnclPrflLastUpdtDt;
	}

	public void setFnclPrflLastUpdtDt ( Date fnclPrflLastUpdtDt ) {
		this.fnclPrflLastUpdtDt = fnclPrflLastUpdtDt;
	}

	public String getCustTypeCd () {
		return custTypeCd;
	}

	public void setCustTypeCd ( String custTypeCd ) {
		this.custTypeCd = custTypeCd == null ? null : custTypeCd.trim();
	}

	public Date getCustAddDt () {
		return custAddDt;
	}

	public void setCustAddDt ( Date custAddDt ) {
		this.custAddDt = custAddDt;
	}

	public String getTaxId () {
		return taxId;
	}

	public void setTaxId ( String taxId ) {
		this.taxId = taxId == null ? null : taxId.trim();
	}

	public String getTaxIdFrmtCd () {
		return taxIdFrmtCd;
	}

	public void setTaxIdFrmtCd ( String taxIdFrmtCd ) {
		this.taxIdFrmtCd = taxIdFrmtCd == null ? null : taxIdFrmtCd.trim();
	}

	public BigDecimal getAnnlIncmBaseAm () {
		return annlIncmBaseAm;
	}

	public void setAnnlIncmBaseAm ( BigDecimal annlIncmBaseAm ) {
		this.annlIncmBaseAm = annlIncmBaseAm;
	}

	public BigDecimal getNetWrthBaseAm () {
		return netWrthBaseAm;
	}

	public void setNetWrthBaseAm ( BigDecimal netWrthBaseAm ) {
		this.netWrthBaseAm = netWrthBaseAm;
	}

	public BigDecimal getLqdNetWrthBaseAm () {
		return lqdNetWrthBaseAm;
	}

	public void setLqdNetWrthBaseAm ( BigDecimal lqdNetWrthBaseAm ) {
		this.lqdNetWrthBaseAm = lqdNetWrthBaseAm;
	}

	public String getEqtyKnldgCd () {
		return eqtyKnldgCd;
	}

	public void setEqtyKnldgCd ( String eqtyKnldgCd ) {
		this.eqtyKnldgCd = eqtyKnldgCd == null ? null : eqtyKnldgCd.trim();
	}

	public String getBndKnldgCd () {
		return bndKnldgCd;
	}

	public void setBndKnldgCd ( String bndKnldgCd ) {
		this.bndKnldgCd = bndKnldgCd == null ? null : bndKnldgCd.trim();
	}

	public String getOptnKnldgCd () {
		return optnKnldgCd;
	}

	public void setOptnKnldgCd ( String optnKnldgCd ) {
		this.optnKnldgCd = optnKnldgCd == null ? null : optnKnldgCd.trim();
	}

	public String getOvrallKnldgCd () {
		return ovrallKnldgCd;
	}

	public void setOvrallKnldgCd ( String ovrallKnldgCd ) {
		this.ovrallKnldgCd = ovrallKnldgCd == null ? null : ovrallKnldgCd.trim();
	}

	public String getOvrallExpCd () {
		return ovrallExpCd;
	}

	public void setOvrallExpCd ( String ovrallExpCd ) {
		this.ovrallExpCd = ovrallExpCd == null ? null : ovrallExpCd.trim();
	}

	public BigDecimal getEqtyExpYrQt () {
		return eqtyExpYrQt;
	}

	public void setEqtyExpYrQt ( BigDecimal eqtyExpYrQt ) {
		this.eqtyExpYrQt = eqtyExpYrQt;
	}

	public BigDecimal getBndExpYrQt () {
		return bndExpYrQt;
	}

	public void setBndExpYrQt ( BigDecimal bndExpYrQt ) {
		this.bndExpYrQt = bndExpYrQt;
	}

	public BigDecimal getOptnExpYrQt () {
		return optnExpYrQt;
	}

	public void setOptnExpYrQt ( BigDecimal optnExpYrQt ) {
		this.optnExpYrQt = optnExpYrQt;
	}

	public BigDecimal getAnnlEqtyTrdQt () {
		return annlEqtyTrdQt;
	}

	public void setAnnlEqtyTrdQt ( BigDecimal annlEqtyTrdQt ) {
		this.annlEqtyTrdQt = annlEqtyTrdQt;
	}

	public BigDecimal getAnnlBndTrdQt () {
		return annlBndTrdQt;
	}

	public void setAnnlBndTrdQt ( BigDecimal annlBndTrdQt ) {
		this.annlBndTrdQt = annlBndTrdQt;
	}

	public BigDecimal getAnnlOptnTrdQt () {
		return annlOptnTrdQt;
	}

	public void setAnnlOptnTrdQt ( BigDecimal annlOptnTrdQt ) {
		this.annlOptnTrdQt = annlOptnTrdQt;
	}

	public BigDecimal getAvgEqtyTrdAm () {
		return avgEqtyTrdAm;
	}

	public void setAvgEqtyTrdAm ( BigDecimal avgEqtyTrdAm ) {
		this.avgEqtyTrdAm = avgEqtyTrdAm;
	}

	public BigDecimal getAvgBndTrdAm () {
		return avgBndTrdAm;
	}

	public void setAvgBndTrdAm ( BigDecimal avgBndTrdAm ) {
		this.avgBndTrdAm = avgBndTrdAm;
	}

	public BigDecimal getAvgOptnTrdAm () {
		return avgOptnTrdAm;
	}

	public void setAvgOptnTrdAm ( BigDecimal avgOptnTrdAm ) {
		this.avgOptnTrdAm = avgOptnTrdAm;
	}

	public String getFirstNm () {
		return firstNm;
	}

	public void setFirstNm ( String firstNm ) {
		this.firstNm = firstNm == null ? null : firstNm.trim();
	}

	public String getLastNm () {
		return lastNm;
	}

	public void setLastNm ( String lastNm ) {
		this.lastNm = lastNm == null ? null : lastNm.trim();
	}

	public String getMidlNm () {
		return midlNm;
	}

	public void setMidlNm ( String midlNm ) {
		this.midlNm = midlNm == null ? null : midlNm.trim();
	}

	public String getSuffNm () {
		return suffNm;
	}

	public void setSuffNm ( String suffNm ) {
		this.suffNm = suffNm == null ? null : suffNm.trim();
	}

	public String getTitlNm () {
		return titlNm;
	}

	public void setTitlNm ( String titlNm ) {
		this.titlNm = titlNm == null ? null : titlNm.trim();
	}

	public String getForm407Fl () {
		return form407Fl;
	}

	public void setForm407Fl ( String form407Fl ) {
		this.form407Fl = form407Fl == null ? null : form407Fl.trim();
	}

	public Date getBirthDt () {
		return birthDt;
	}

	public void setBirthDt ( Date birthDt ) {
		this.birthDt = birthDt;
	}

	public String getCtzshpCntry1Cd () {
		return ctzshpCntry1Cd;
	}

	public void setCtzshpCntry1Cd ( String ctzshpCntry1Cd ) {
		this.ctzshpCntry1Cd = ctzshpCntry1Cd == null ? null : ctzshpCntry1Cd.trim();
	}

	public String getCtzshpCntry2Cd () {
		return ctzshpCntry2Cd;
	}

	public void setCtzshpCntry2Cd ( String ctzshpCntry2Cd ) {
		this.ctzshpCntry2Cd = ctzshpCntry2Cd == null ? null : ctzshpCntry2Cd.trim();
	}

	public String getResCntryCd () {
		return resCntryCd;
	}

	public void setResCntryCd ( String resCntryCd ) {
		this.resCntryCd = resCntryCd == null ? null : resCntryCd.trim();
	}

	public String getMplyrNm () {
		return mplyrNm;
	}

	public void setMplyrNm ( String mplyrNm ) {
		this.mplyrNm = mplyrNm == null ? null : mplyrNm.trim();
	}

	public String getMplyrFinclInstFl () {
		return mplyrFinclInstFl;
	}

	public void setMplyrFinclInstFl ( String mplyrFinclInstFl ) {
		this.mplyrFinclInstFl = mplyrFinclInstFl == null ? null : mplyrFinclInstFl.trim();
	}

	public String getMplmtStatCd () {
		return mplmtStatCd;
	}

	public void setMplmtStatCd ( String mplmtStatCd ) {
		this.mplmtStatCd = mplmtStatCd == null ? null : mplmtStatCd.trim();
	}

	public String getMrtlStatCd () {
		return mrtlStatCd;
	}

	public void setMrtlStatCd ( String mrtlStatCd ) {
		this.mrtlStatCd = mrtlStatCd == null ? null : mrtlStatCd.trim();
	}

	public BigDecimal getDpndtQt () {
		return dpndtQt;
	}

	public void setDpndtQt ( BigDecimal dpndtQt ) {
		this.dpndtQt = dpndtQt;
	}

	public String getOcptnNm () {
		return ocptnNm;
	}

	public void setOcptnNm ( String ocptnNm ) {
		this.ocptnNm = ocptnNm == null ? null : ocptnNm.trim();
	}

	public String getOrgNm () {
		return orgNm;
	}

	public void setOrgNm ( String orgNm ) {
		this.orgNm = orgNm == null ? null : orgNm.trim();
	}

	public BigDecimal getAgeYrCt () {
		return ageYrCt;
	}

	public void setAgeYrCt ( BigDecimal ageYrCt ) {
		this.ageYrCt = ageYrCt;
	}

	public String getCtzshpStatCd () {
		return ctzshpStatCd;
	}

	public void setCtzshpStatCd ( String ctzshpStatCd ) {
		this.ctzshpStatCd = ctzshpStatCd == null ? null : ctzshpStatCd.trim();
	}

	public String getWlthSrcDscrTx () {
		return wlthSrcDscrTx;
	}

	public void setWlthSrcDscrTx ( String wlthSrcDscrTx ) {
		this.wlthSrcDscrTx = wlthSrcDscrTx == null ? null : wlthSrcDscrTx.trim();
	}

	public String getEmpFl () {
		return empFl;
	}

	public void setEmpFl ( String empFl ) {
		this.empFl = empFl == null ? null : empFl.trim();
	}

	public String getSrcSysCd () {
		return srcSysCd;
	}

	public void setSrcSysCd ( String srcSysCd ) {
		this.srcSysCd = srcSysCd == null ? null : srcSysCd.trim();
	}

	public String getOrgLglStrucCd () {
		return orgLglStrucCd;
	}

	public void setOrgLglStrucCd ( String orgLglStrucCd ) {
		this.orgLglStrucCd = orgLglStrucCd == null ? null : orgLglStrucCd.trim();
	}

	public Date getPwdLastChgDt () {
		return pwdLastChgDt;
	}

	public void setPwdLastChgDt ( Date pwdLastChgDt ) {
		this.pwdLastChgDt = pwdLastChgDt;
	}

	public String getAliasNm () {
		return aliasNm;
	}

	public void setAliasNm ( String aliasNm ) {
		this.aliasNm = aliasNm == null ? null : aliasNm.trim();
	}

	public String getFrgnAssetsFl () {
		return frgnAssetsFl;
	}

	public void setFrgnAssetsFl ( String frgnAssetsFl ) {
		this.frgnAssetsFl = frgnAssetsFl == null ? null : frgnAssetsFl.trim();
	}

	public String getFullNm () {
		return fullNm;
	}

	public void setFullNm ( String fullNm ) {
		this.fullNm = fullNm == null ? null : fullNm.trim();
	}

	public String getMplyrIndusCd () {
		return mplyrIndusCd;
	}

	public void setMplyrIndusCd ( String mplyrIndusCd ) {
		this.mplyrIndusCd = mplyrIndusCd == null ? null : mplyrIndusCd.trim();
	}

	public String getJobTitlNm () {
		return jobTitlNm;
	}

	public void setJobTitlNm ( String jobTitlNm ) {
		this.jobTitlNm = jobTitlNm == null ? null : jobTitlNm.trim();
	}

	public Date getCstm1Dt () {
		return cstm1Dt;
	}

	public void setCstm1Dt ( Date cstm1Dt ) {
		this.cstm1Dt = cstm1Dt;
	}

	public Date getCstm2Dt () {
		return cstm2Dt;
	}

	public void setCstm2Dt ( Date cstm2Dt ) {
		this.cstm2Dt = cstm2Dt;
	}

	public Date getCstm3Dt () {
		return cstm3Dt;
	}

	public void setCstm3Dt ( Date cstm3Dt ) {
		this.cstm3Dt = cstm3Dt;
	}

	public BigDecimal getCstm1Rl () {
		return cstm1Rl;
	}

	public void setCstm1Rl ( BigDecimal cstm1Rl ) {
		this.cstm1Rl = cstm1Rl;
	}

	public BigDecimal getCstm2Rl () {
		return cstm2Rl;
	}

	public void setCstm2Rl ( BigDecimal cstm2Rl ) {
		this.cstm2Rl = cstm2Rl;
	}

	public BigDecimal getCstm3Rl () {
		return cstm3Rl;
	}

	public void setCstm3Rl ( BigDecimal cstm3Rl ) {
		this.cstm3Rl = cstm3Rl;
	}

	public String getCstm1Tx () {
		return cstm1Tx;
	}

	public void setCstm1Tx ( String cstm1Tx ) {
		this.cstm1Tx = cstm1Tx == null ? null : cstm1Tx.trim();
	}

	public String getCstm2Tx () {
		return cstm2Tx;
	}

	public void setCstm2Tx ( String cstm2Tx ) {
		this.cstm2Tx = cstm2Tx == null ? null : cstm2Tx.trim();
	}

	public String getCstm3Tx () {
		return cstm3Tx;
	}

	public void setCstm3Tx ( String cstm3Tx ) {
		this.cstm3Tx = cstm3Tx == null ? null : cstm3Tx.trim();
	}

	public BigDecimal getTotAcctCt () {
		return totAcctCt;
	}

	public void setTotAcctCt ( BigDecimal totAcctCt ) {
		this.totAcctCt = totAcctCt;
	}

	public BigDecimal getCustEfctvRiskNb () {
		return custEfctvRiskNb;
	}

	public void setCustEfctvRiskNb ( BigDecimal custEfctvRiskNb ) {
		this.custEfctvRiskNb = custEfctvRiskNb;
	}

	public String getCstm4Tx () {
		return cstm4Tx;
	}

	public void setCstm4Tx ( String cstm4Tx ) {
		this.cstm4Tx = cstm4Tx == null ? null : cstm4Tx.trim();
	}

	public String getCstm5Tx () {
		return cstm5Tx;
	}

	public void setCstm5Tx ( String cstm5Tx ) {
		this.cstm5Tx = cstm5Tx == null ? null : cstm5Tx.trim();
	}

	public String getFnclInstnIdTypeCd () {
		return fnclInstnIdTypeCd;
	}

	public void setFnclInstnIdTypeCd ( String fnclInstnIdTypeCd ) {
		this.fnclInstnIdTypeCd = fnclInstnIdTypeCd == null ? null : fnclInstnIdTypeCd.trim();
	}

	public String getFnclInstnId () {
		return fnclInstnId;
	}

	public void setFnclInstnId ( String fnclInstnId ) {
		this.fnclInstnId = fnclInstnId == null ? null : fnclInstnId.trim();
	}

	public BigDecimal getInstnSeqId () {
		return instnSeqId;
	}

	public void setInstnSeqId ( BigDecimal instnSeqId ) {
		this.instnSeqId = instnSeqId;
	}

	public BigDecimal getCustListRiskNb () {
		return custListRiskNb;
	}

	public void setCustListRiskNb ( BigDecimal custListRiskNb ) {
		this.custListRiskNb = custListRiskNb;
	}

	public String getCustListSrcCd () {
		return custListSrcCd;
	}

	public void setCustListSrcCd ( String custListSrcCd ) {
		this.custListSrcCd = custListSrcCd == null ? null : custListSrcCd.trim();
	}

	public String getCustMatchTx () {
		return custMatchTx;
	}

	public void setCustMatchTx ( String custMatchTx ) {
		this.custMatchTx = custMatchTx == null ? null : custMatchTx.trim();
	}

	public String getCustMatchTypeCd () {
		return custMatchTypeCd;
	}

	public void setCustMatchTypeCd ( String custMatchTypeCd ) {
		this.custMatchTypeCd = custMatchTypeCd == null ? null : custMatchTypeCd.trim();
	}

	public BigDecimal getCustBusRiskNb () {
		return custBusRiskNb;
	}

	public void setCustBusRiskNb ( BigDecimal custBusRiskNb ) {
		this.custBusRiskNb = custBusRiskNb;
	}

	public BigDecimal getCustGeoRiskNb () {
		return custGeoRiskNb;
	}

	public void setCustGeoRiskNb ( BigDecimal custGeoRiskNb ) {
		this.custGeoRiskNb = custGeoRiskNb;
	}

	public BigDecimal getCstmRisk1Nb () {
		return cstmRisk1Nb;
	}

	public void setCstmRisk1Nb ( BigDecimal cstmRisk1Nb ) {
		this.cstmRisk1Nb = cstmRisk1Nb;
	}

	public BigDecimal getCstmRisk2Nb () {
		return cstmRisk2Nb;
	}

	public void setCstmRisk2Nb ( BigDecimal cstmRisk2Nb ) {
		this.cstmRisk2Nb = cstmRisk2Nb;
	}

	public String getDmcldBrchOrgId () {
		return dmcldBrchOrgId;
	}

	public void setDmcldBrchOrgId ( String dmcldBrchOrgId ) {
		this.dmcldBrchOrgId = dmcldBrchOrgId == null ? null : dmcldBrchOrgId.trim();
	}

	public String getDayTrdKnldgCd () {
		return dayTrdKnldgCd;
	}

	public void setDayTrdKnldgCd ( String dayTrdKnldgCd ) {
		this.dayTrdKnldgCd = dayTrdKnldgCd == null ? null : dayTrdKnldgCd.trim();
	}

	public String getDayTrdExpCd () {
		return dayTrdExpCd;
	}

	public void setDayTrdExpCd ( String dayTrdExpCd ) {
		this.dayTrdExpCd = dayTrdExpCd == null ? null : dayTrdExpCd.trim();
	}

	public BigDecimal getAnnlIncmRptgAm () {
		return annlIncmRptgAm;
	}

	public void setAnnlIncmRptgAm ( BigDecimal annlIncmRptgAm ) {
		this.annlIncmRptgAm = annlIncmRptgAm;
	}

	public BigDecimal getNetWrthRptgAm () {
		return netWrthRptgAm;
	}

	public void setNetWrthRptgAm ( BigDecimal netWrthRptgAm ) {
		this.netWrthRptgAm = netWrthRptgAm;
	}

	public BigDecimal getLqdNetWrthRptgAm () {
		return lqdNetWrthRptgAm;
	}

	public void setLqdNetWrthRptgAm ( BigDecimal lqdNetWrthRptgAm ) {
		this.lqdNetWrthRptgAm = lqdNetWrthRptgAm;
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

	public String getRptgCrncyCd () {
		return rptgCrncyCd;
	}

	public void setRptgCrncyCd ( String rptgCrncyCd ) {
		this.rptgCrncyCd = rptgCrncyCd == null ? null : rptgCrncyCd.trim();
	}

	public String getMantasCustBusTypeCd () {
		return mantasCustBusTypeCd;
	}

	public void setMantasCustBusTypeCd ( String mantasCustBusTypeCd ) {
		this.mantasCustBusTypeCd = mantasCustBusTypeCd == null ? null : mantasCustBusTypeCd.trim();
	}

	public String getCustEfctvRiskFactrTx () {
		return custEfctvRiskFactrTx;
	}

	public void setCustEfctvRiskFactrTx ( String custEfctvRiskFactrTx ) {
		this.custEfctvRiskFactrTx = custEfctvRiskFactrTx == null ? null : custEfctvRiskFactrTx.trim();
	}

	public String getCustCdtRtng () {
		return custCdtRtng;
	}

	public void setCustCdtRtng ( String custCdtRtng ) {
		this.custCdtRtng = custCdtRtng == null ? null : custCdtRtng.trim();
	}

	public String getCustCdtRtngSrc () {
		return custCdtRtngSrc;
	}

	public void setCustCdtRtngSrc ( String custCdtRtngSrc ) {
		this.custCdtRtngSrc = custCdtRtngSrc == null ? null : custCdtRtngSrc.trim();
	}

	public BigDecimal getCustCdtScore () {
		return custCdtScore;
	}

	public void setCustCdtScore ( BigDecimal custCdtScore ) {
		this.custCdtScore = custCdtScore;
	}

	public String getCustPeerGrpIntrlId () {
		return custPeerGrpIntrlId;
	}

	public void setCustPeerGrpIntrlId ( String custPeerGrpIntrlId ) {
		this.custPeerGrpIntrlId = custPeerGrpIntrlId == null ? null : custPeerGrpIntrlId.trim();
	}

	public String getCustStatCd () {
		return custStatCd;
	}

	public void setCustStatCd ( String custStatCd ) {
		this.custStatCd = custStatCd == null ? null : custStatCd.trim();
	}

	public String getTaxtnCntryCd () {
		return taxtnCntryCd;
	}

	public void setTaxtnCntryCd ( String taxtnCntryCd ) {
		this.taxtnCntryCd = taxtnCntryCd == null ? null : taxtnCntryCd.trim();
	}

	public String getCustIndusCd () {
		return custIndusCd;
	}

	public void setCustIndusCd ( String custIndusCd ) {
		this.custIndusCd = custIndusCd == null ? null : custIndusCd.trim();
	}

	public Date getCustIncDt () {
		return custIncDt;
	}

	public void setCustIncDt ( Date custIncDt ) {
		this.custIncDt = custIncDt;
	}

	public String getRgstnTypeCd () {
		return rgstnTypeCd;
	}

	public void setRgstnTypeCd ( String rgstnTypeCd ) {
		this.rgstnTypeCd = rgstnTypeCd == null ? null : rgstnTypeCd.trim();
	}

	public String getPublcPrivtCd () {
		return publcPrivtCd;
	}

	public void setPublcPrivtCd ( String publcPrivtCd ) {
		this.publcPrivtCd = publcPrivtCd == null ? null : publcPrivtCd.trim();
	}

	public String getAltCustId () {
		return altCustId;
	}

	public void setAltCustId ( String altCustId ) {
		this.altCustId = altCustId == null ? null : altCustId.trim();
	}

	public String getIncmRngCd () {
		return incmRngCd;
	}

	public void setIncmRngCd ( String incmRngCd ) {
		this.incmRngCd = incmRngCd == null ? null : incmRngCd.trim();
	}

	public String getCustGndrCd () {
		return custGndrCd;
	}

	public void setCustGndrCd ( String custGndrCd ) {
		this.custGndrCd = custGndrCd == null ? null : custGndrCd.trim();
	}

	public String getCustNaicsCd () {
		return custNaicsCd;
	}

	public void setCustNaicsCd ( String custNaicsCd ) {
		this.custNaicsCd = custNaicsCd == null ? null : custNaicsCd.trim();
	}

	public String getWlthCompFl () {
		return wlthCompFl;
	}

	public void setWlthCompFl ( String wlthCompFl ) {
		this.wlthCompFl = wlthCompFl == null ? null : wlthCompFl.trim();
	}

	public String getCmdtyKnldgCd () {
		return cmdtyKnldgCd;
	}

	public void setCmdtyKnldgCd ( String cmdtyKnldgCd ) {
		this.cmdtyKnldgCd = cmdtyKnldgCd == null ? null : cmdtyKnldgCd.trim();
	}

	public BigDecimal getCmdtyExpYrQt () {
		return cmdtyExpYrQt;
	}

	public void setCmdtyExpYrQt ( BigDecimal cmdtyExpYrQt ) {
		this.cmdtyExpYrQt = cmdtyExpYrQt;
	}

	public BigDecimal getAvgCmdtyTrdAm () {
		return avgCmdtyTrdAm;
	}

	public void setAvgCmdtyTrdAm ( BigDecimal avgCmdtyTrdAm ) {
		this.avgCmdtyTrdAm = avgCmdtyTrdAm;
	}

	public BigDecimal getAnnlCmdtyTrdQt () {
		return annlCmdtyTrdQt;
	}

	public void setAnnlCmdtyTrdQt ( BigDecimal annlCmdtyTrdQt ) {
		this.annlCmdtyTrdQt = annlCmdtyTrdQt;
	}

	public String getFrgnPublcOffclFl () {
		return frgnPublcOffclFl;
	}

	public void setFrgnPublcOffclFl ( String frgnPublcOffclFl ) {
		this.frgnPublcOffclFl = frgnPublcOffclFl == null ? null : frgnPublcOffclFl.trim();
	}

	public String getEmpRlshpTypeCd () {
		return empRlshpTypeCd;
	}

	public void setEmpRlshpTypeCd ( String empRlshpTypeCd ) {
		this.empRlshpTypeCd = empRlshpTypeCd == null ? null : empRlshpTypeCd.trim();
	}

	public BigDecimal getRetrmtYear () {
		return retrmtYear;
	}

	public void setRetrmtYear ( BigDecimal retrmtYear ) {
		this.retrmtYear = retrmtYear;
	}

	public BigDecimal getMaxDailyAtmWdrwlAm () {
		return maxDailyAtmWdrwlAm;
	}

	public void setMaxDailyAtmWdrwlAm ( BigDecimal maxDailyAtmWdrwlAm ) {
		this.maxDailyAtmWdrwlAm = maxDailyAtmWdrwlAm;
	}

	public String getRecalcitrantFl () {
		return recalcitrantFl;
	}

	public void setRecalcitrantFl ( String recalcitrantFl ) {
		this.recalcitrantFl = recalcitrantFl == null ? null : recalcitrantFl.trim();
	}

	public String getNonUsCtznResFl () {
		return nonUsCtznResFl;
	}

	public void setNonUsCtznResFl ( String nonUsCtznResFl ) {
		this.nonUsCtznResFl = nonUsCtznResFl == null ? null : nonUsCtznResFl.trim();
	}

	public String getNonUsDocHolderFl () {
		return nonUsDocHolderFl;
	}

	public void setNonUsDocHolderFl ( String nonUsDocHolderFl ) {
		this.nonUsDocHolderFl = nonUsDocHolderFl == null ? null : nonUsDocHolderFl.trim();
	}

	public String getCertLossNationFl () {
		return certLossNationFl;
	}

	public void setCertLossNationFl ( String certLossNationFl ) {
		this.certLossNationFl = certLossNationFl == null ? null : certLossNationFl.trim();
	}

	public String getCntryOfBirth () {
		return cntryOfBirth;
	}

	public void setCntryOfBirth ( String cntryOfBirth ) {
		this.cntryOfBirth = cntryOfBirth == null ? null : cntryOfBirth.trim();
	}

	public String getCntryOfInc () {
		return cntryOfInc;
	}

	public void setCntryOfInc ( String cntryOfInc ) {
		this.cntryOfInc = cntryOfInc == null ? null : cntryOfInc.trim();
	}

	public String getFatcaExemptCd () {
		return fatcaExemptCd;
	}

	public void setFatcaExemptCd ( String fatcaExemptCd ) {
		this.fatcaExemptCd = fatcaExemptCd == null ? null : fatcaExemptCd.trim();
	}

	public String getGovtDocFl () {
		return govtDocFl;
	}

	public void setGovtDocFl ( String govtDocFl ) {
		this.govtDocFl = govtDocFl == null ? null : govtDocFl.trim();
	}

	public String getThrdPartyCdtRptFl () {
		return thrdPartyCdtRptFl;
	}

	public void setThrdPartyCdtRptFl ( String thrdPartyCdtRptFl ) {
		this.thrdPartyCdtRptFl = thrdPartyCdtRptFl == null ? null : thrdPartyCdtRptFl.trim();
	}

	public String getFrgnTaxRptngCertFl () {
		return frgnTaxRptngCertFl;
	}

	public void setFrgnTaxRptngCertFl ( String frgnTaxRptngCertFl ) {
		this.frgnTaxRptngCertFl = frgnTaxRptngCertFl == null ? null : frgnTaxRptngCertFl.trim();
	}

	public String getExceptedNffeFl () {
		return exceptedNffeFl;
	}

	public void setExceptedNffeFl ( String exceptedNffeFl ) {
		this.exceptedNffeFl = exceptedNffeFl == null ? null : exceptedNffeFl.trim();
	}

	public String getFatcaOrgTypeCd () {
		return fatcaOrgTypeCd;
	}

	public void setFatcaOrgTypeCd ( String fatcaOrgTypeCd ) {
		this.fatcaOrgTypeCd = fatcaOrgTypeCd == null ? null : fatcaOrgTypeCd.trim();
	}

	public String getGeoJrsdcnCd () {
		return geoJrsdcnCd;
	}

	public void setGeoJrsdcnCd ( String geoJrsdcnCd ) {
		this.geoJrsdcnCd = geoJrsdcnCd == null ? null : geoJrsdcnCd.trim();
	}

	public String getSearch () {
		return search;
	}

	public void setSearch ( String search ) {
		this.search = search;
	}

}