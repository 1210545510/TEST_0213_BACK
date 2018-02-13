package com.aml.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSuspiciousActivityDto extends BaseDto {
	private static final long serialVersionUID = 4589669153294737395L;
	private Long susId;
	private Long rid;
	private BigDecimal involveAmount;
	private Date startDate;
	private Date endDate;
	private BigDecimal cumulativeAmount;
	private String structuringType;
	private String structuringValue;
	private String terroristFinancingType;
	private String terroristFinancingValue;
	private String franudType;
	private String franudValue;
	private String casinosType;
	private String casinosValue;
	private String moneyLaunderingType;
	private String moneyLaunderingValue;
	private String identificationType;
	private String identificationValue;
	private String otherSuspiciousType;
	private String otherSuspiciousValue;
	private String insuranceType;
	private String insuranceValue;
	private String securitiesOptionType;
	private String securitiesOptionValue;
	private String mortgageFrandType;
	private String mortgageFrandValue;
	private String productType;
	private String productValue;
	private String instrumentType;
	private String instrumentValue;
	private String commodityType;
	private String productDescription;
	private String tradeMarket;
	private String ipAddress;
	private String cusipNumber;
	private Date createTime;
	private Date updateTime;
	private Byte isDeleted;

	public Long getSusId () {
		return susId;
	}

	public void setSusId ( Long susId ) {
		this.susId = susId;
	}

	public Long getRid () {
		return rid;
	}

	public void setRid ( Long rid ) {
		this.rid = rid;
	}

	public BigDecimal getInvolveAmount () {
		return involveAmount;
	}

	public void setInvolveAmount ( BigDecimal involveAmount ) {
		this.involveAmount = involveAmount;
	}

	public Date getStartDate () {
		return startDate;
	}

	public void setStartDate ( Date startDate ) {
		this.startDate = startDate;
	}

	public Date getEndDate () {
		return endDate;
	}

	public void setEndDate ( Date endDate ) {
		this.endDate = endDate;
	}

	public BigDecimal getCumulativeAmount () {
		return cumulativeAmount;
	}

	public void setCumulativeAmount ( BigDecimal cumulativeAmount ) {
		this.cumulativeAmount = cumulativeAmount;
	}

	public String getStructuringType () {
		return structuringType;
	}

	public void setStructuringType ( String structuringType ) {
		this.structuringType = structuringType == null ? null : structuringType.trim();
	}

	public String getStructuringValue () {
		return structuringValue;
	}

	public void setStructuringValue ( String structuringValue ) {
		this.structuringValue = structuringValue == null ? null : structuringValue.trim();
	}

	public String getTerroristFinancingType () {
		return terroristFinancingType;
	}

	public void setTerroristFinancingType ( String terroristFinancingType ) {
		this.terroristFinancingType = terroristFinancingType == null ? null : terroristFinancingType.trim();
	}

	public String getTerroristFinancingValue () {
		return terroristFinancingValue;
	}

	public void setTerroristFinancingValue ( String terroristFinancingValue ) {
		this.terroristFinancingValue = terroristFinancingValue == null ? null : terroristFinancingValue.trim();
	}

	public String getFranudType () {
		return franudType;
	}

	public void setFranudType ( String franudType ) {
		this.franudType = franudType == null ? null : franudType.trim();
	}

	public String getFranudValue () {
		return franudValue;
	}

	public void setFranudValue ( String franudValue ) {
		this.franudValue = franudValue == null ? null : franudValue.trim();
	}

	public String getCasinosType () {
		return casinosType;
	}

	public void setCasinosType ( String casinosType ) {
		this.casinosType = casinosType == null ? null : casinosType.trim();
	}

	public String getCasinosValue () {
		return casinosValue;
	}

	public void setCasinosValue ( String casinosValue ) {
		this.casinosValue = casinosValue == null ? null : casinosValue.trim();
	}

	public String getMoneyLaunderingType () {
		return moneyLaunderingType;
	}

	public void setMoneyLaunderingType ( String moneyLaunderingType ) {
		this.moneyLaunderingType = moneyLaunderingType == null ? null : moneyLaunderingType.trim();
	}

	public String getMoneyLaunderingValue () {
		return moneyLaunderingValue;
	}

	public void setMoneyLaunderingValue ( String moneyLaunderingValue ) {
		this.moneyLaunderingValue = moneyLaunderingValue == null ? null : moneyLaunderingValue.trim();
	}

	public String getIdentificationType () {
		return identificationType;
	}

	public void setIdentificationType ( String identificationType ) {
		this.identificationType = identificationType == null ? null : identificationType.trim();
	}

	public String getIdentificationValue () {
		return identificationValue;
	}

	public void setIdentificationValue ( String identificationValue ) {
		this.identificationValue = identificationValue == null ? null : identificationValue.trim();
	}

	public String getOtherSuspiciousType () {
		return otherSuspiciousType;
	}

	public void setOtherSuspiciousType ( String otherSuspiciousType ) {
		this.otherSuspiciousType = otherSuspiciousType == null ? null : otherSuspiciousType.trim();
	}

	public String getOtherSuspiciousValue () {
		return otherSuspiciousValue;
	}

	public void setOtherSuspiciousValue ( String otherSuspiciousValue ) {
		this.otherSuspiciousValue = otherSuspiciousValue == null ? null : otherSuspiciousValue.trim();
	}

	public String getInsuranceType () {
		return insuranceType;
	}

	public void setInsuranceType ( String insuranceType ) {
		this.insuranceType = insuranceType == null ? null : insuranceType.trim();
	}

	public String getInsuranceValue () {
		return insuranceValue;
	}

	public void setInsuranceValue ( String insuranceValue ) {
		this.insuranceValue = insuranceValue == null ? null : insuranceValue.trim();
	}

	public String getSecuritiesOptionType () {
		return securitiesOptionType;
	}

	public void setSecuritiesOptionType ( String securitiesOptionType ) {
		this.securitiesOptionType = securitiesOptionType == null ? null : securitiesOptionType.trim();
	}

	public String getSecuritiesOptionValue () {
		return securitiesOptionValue;
	}

	public void setSecuritiesOptionValue ( String securitiesOptionValue ) {
		this.securitiesOptionValue = securitiesOptionValue == null ? null : securitiesOptionValue.trim();
	}

	public String getMortgageFrandType () {
		return mortgageFrandType;
	}

	public void setMortgageFrandType ( String mortgageFrandType ) {
		this.mortgageFrandType = mortgageFrandType == null ? null : mortgageFrandType.trim();
	}

	public String getMortgageFrandValue () {
		return mortgageFrandValue;
	}

	public void setMortgageFrandValue ( String mortgageFrandValue ) {
		this.mortgageFrandValue = mortgageFrandValue == null ? null : mortgageFrandValue.trim();
	}

	public String getProductType () {
		return productType;
	}

	public void setProductType ( String productType ) {
		this.productType = productType == null ? null : productType.trim();
	}

	public String getProductValue () {
		return productValue;
	}

	public void setProductValue ( String productValue ) {
		this.productValue = productValue == null ? null : productValue.trim();
	}

	public String getInstrumentType () {
		return instrumentType;
	}

	public void setInstrumentType ( String instrumentType ) {
		this.instrumentType = instrumentType == null ? null : instrumentType.trim();
	}

	public String getInstrumentValue () {
		return instrumentValue;
	}

	public void setInstrumentValue ( String instrumentValue ) {
		this.instrumentValue = instrumentValue == null ? null : instrumentValue.trim();
	}

	public String getCommodityType () {
		return commodityType;
	}

	public void setCommodityType ( String commodityType ) {
		this.commodityType = commodityType == null ? null : commodityType.trim();
	}

	public String getProductDescription () {
		return productDescription;
	}

	public void setProductDescription ( String productDescription ) {
		this.productDescription = productDescription == null ? null : productDescription.trim();
	}

	public String getTradeMarket () {
		return tradeMarket;
	}

	public void setTradeMarket ( String tradeMarket ) {
		this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
	}

	public String getIpAddress () {
		return ipAddress;
	}

	public void setIpAddress ( String ipAddress ) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	public String getCusipNumber () {
		return cusipNumber;
	}

	public void setCusipNumber ( String cusipNumber ) {
		this.cusipNumber = cusipNumber == null ? null : cusipNumber.trim();
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime ( Date createTime ) {
		this.createTime = createTime;
	}

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime ( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public Byte getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Byte isDeleted ) {
		this.isDeleted = isDeleted;
	}
}