package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarFilingInstitution extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2684529480165759393L;

	private Long filingId;

    private Long rid;

    private Integer financialInstitutionType;

    private String financialInstitutionValue;

    private Integer primaryFederalRegulator;

    private String filerName;

    private Integer tin;

    private Integer tinType;

    private String securitiesType;

    private String securitiesValue;

    private Integer identificationType;

    private Integer identificationNumber;

    private String address;

    private String city;

    private Integer state;

    private Integer zipCode;

    private Integer country;

    private String alternateName;

    private String internalFileNumber;

    private String leAgency;

    private String leName;

    private String lePhone;

    private String lePhoneExt;

    private Date leDate;

    private String fiOffice;

    private String fiPhoneExt;
    
    private String fiPhone;

    private Date signedDate;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    public Long getFilingId() {
        return filingId;
    }

    public void setFilingId(Long filingId) {
        this.filingId = filingId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Integer getFinancialInstitutionType() {
        return financialInstitutionType;
    }

    public void setFinancialInstitutionType(Integer financialInstitutionType) {
        this.financialInstitutionType = financialInstitutionType;
    }

    public String getFinancialInstitutionValue() {
        return financialInstitutionValue;
    }

    public void setFinancialInstitutionValue(String financialInstitutionValue) {
        this.financialInstitutionValue = financialInstitutionValue == null ? null : financialInstitutionValue.trim();
    }

    public Integer getPrimaryFederalRegulator() {
        return primaryFederalRegulator;
    }

    public void setPrimaryFederalRegulator(Integer primaryFederalRegulator) {
        this.primaryFederalRegulator = primaryFederalRegulator;
    }

    public String getFilerName() {
        return filerName;
    }

    public void setFilerName(String filerName) {
        this.filerName = filerName == null ? null : filerName.trim();
    }

    public Integer getTin() {
        return tin;
    }

    public void setTin(Integer tin) {
        this.tin = tin;
    }

    public Integer getTinType() {
        return tinType;
    }

    public void setTinType(Integer tinType) {
        this.tinType = tinType;
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

    public Integer getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(Integer identificationType) {
        this.identificationType = identificationType;
    }

    public Integer getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Integer identificationNumber) {
        this.identificationNumber = identificationNumber;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName == null ? null : alternateName.trim();
    }

    public String getInternalFileNumber() {
        return internalFileNumber;
    }

    public void setInternalFileNumber(String internalFileNumber) {
        this.internalFileNumber = internalFileNumber == null ? null : internalFileNumber.trim();
    }

    public String getLeAgency() {
        return leAgency;
    }

    public void setLeAgency(String leAgency) {
        this.leAgency = leAgency == null ? null : leAgency.trim();
    }

    public String getLeName() {
        return leName;
    }

    public void setLeName(String leName) {
        this.leName = leName == null ? null : leName.trim();
    }

    public String getLePhone() {
        return lePhone;
    }

    public void setLePhone(String lePhone) {
        this.lePhone = lePhone == null ? null : lePhone.trim();
    }

    public String getLePhoneExt() {
        return lePhoneExt;
    }

    public void setLePhoneExt(String lePhoneExt) {
        this.lePhoneExt = lePhoneExt == null ? null : lePhoneExt.trim();
    }

    public Date getLeDate() {
        return leDate;
    }

    public void setLeDate(Date leDate) {
        this.leDate = leDate;
    }

    public String getFiOffice() {
        return fiOffice;
    }

    public void setFiOffice(String fiOffice) {
        this.fiOffice = fiOffice == null ? null : fiOffice.trim();
    }

    public String getFiPhoneExt() {
        return fiPhoneExt;
    }

    public void setFiPhoneExt(String fiPhoneExt) {
        this.fiPhoneExt = fiPhoneExt == null ? null : fiPhoneExt.trim();
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

	public String getFiPhone() {
		return fiPhone;
	}

	public void setFiPhone(String fiPhone) {
		this.fiPhone = fiPhone;
	}
}