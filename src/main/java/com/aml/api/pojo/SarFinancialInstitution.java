package com.aml.api.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarFinancialInstitution extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3814985732953394036L;

	private Long financialId;

    private Long rid;

    private Byte financialInstitutionType;

    private String financialInstitutionValue;

    private Byte primaryFederalRegulator;

    private String gamingInstitutionType;

    private String gamingInstitutionValue;

    private String securitiesType;

    private String securitiesValue;

    private Byte identificationType;

    private Integer identificationNumber;

    private Byte transationRole;

    private String legalName;

    private Integer tin;

    private Byte tinType;

    private String address;

    private String city;

    private Byte state;

    private Byte zipCode;

    private Byte country;

    private String internalFileNumber;

    private BigDecimal loss;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;
    
    private List<SarFinancialInstitutionBranch> branch;

    public Long getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Long financialId) {
        this.financialId = financialId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Byte getFinancialInstitutionType() {
        return financialInstitutionType;
    }

    public void setFinancialInstitutionType(Byte financialInstitutionType) {
        this.financialInstitutionType = financialInstitutionType;
    }

    public String getFinancialInstitutionValue() {
        return financialInstitutionValue;
    }

    public void setFinancialInstitutionValue(String financialInstitutionValue) {
        this.financialInstitutionValue = financialInstitutionValue == null ? null : financialInstitutionValue.trim();
    }

    public Byte getPrimaryFederalRegulator() {
        return primaryFederalRegulator;
    }

    public void setPrimaryFederalRegulator(Byte primaryFederalRegulator) {
        this.primaryFederalRegulator = primaryFederalRegulator;
    }

    public String getGamingInstitutionType() {
        return gamingInstitutionType;
    }

    public void setGamingInstitutionType(String gamingInstitutionType) {
        this.gamingInstitutionType = gamingInstitutionType == null ? null : gamingInstitutionType.trim();
    }

    public String getGamingInstitutionValue() {
        return gamingInstitutionValue;
    }

    public void setGamingInstitutionValue(String gamingInstitutionValue) {
        this.gamingInstitutionValue = gamingInstitutionValue == null ? null : gamingInstitutionValue.trim();
    }

    public String getSecuritiesType() {
        return securitiesType;
    }

    public void setSecuritiesType(String securitiesType) {
        this.securitiesType = securitiesType == null ? null : securitiesType.trim();
    }

    public String getSecuritiesValue() {
        return securitiesValue;
    }

    public void setSecuritiesValue(String securitiesValue) {
        this.securitiesValue = securitiesValue == null ? null : securitiesValue.trim();
    }

    public Byte getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(Byte identificationType) {
        this.identificationType = identificationType;
    }

    public Integer getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Integer identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Byte getTransationRole() {
        return transationRole;
    }

    public void setTransationRole(Byte transationRole) {
        this.transationRole = transationRole;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public Integer getTin() {
        return tin;
    }

    public void setTin(Integer tin) {
        this.tin = tin;
    }

    public Byte getTinType() {
        return tinType;
    }

    public void setTinType(Byte tinType) {
        this.tinType = tinType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getZipCode() {
        return zipCode;
    }

    public void setZipCode(Byte zipCode) {
        this.zipCode = zipCode;
    }

    public Byte getCountry() {
        return country;
    }

    public void setCountry(Byte country) {
        this.country = country;
    }

    public String getInternalFileNumber() {
        return internalFileNumber;
    }

    public void setInternalFileNumber(String internalFileNumber) {
        this.internalFileNumber = internalFileNumber == null ? null : internalFileNumber.trim();
    }

    public BigDecimal getLoss() {
        return loss;
    }

    public void setLoss(BigDecimal loss) {
        this.loss = loss;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

	public List<SarFinancialInstitutionBranch> getBranch() {
		return branch;
	}

	public void setBranch(List<SarFinancialInstitutionBranch> branch) {
		this.branch = branch;
	}

}