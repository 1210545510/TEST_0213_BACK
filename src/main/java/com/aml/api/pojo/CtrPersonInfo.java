package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CtrPersonInfo extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1483035686891335548L;

	private Long personId;

    private Long cid;

    private Byte involvedTransaction;

    private Byte multTransaction;

    private Byte isEntity;

    private String lastName;

    private String firstName;

    private String middleName;

    private String suffix;

    private Byte gender;

    private String alternateName;

    private String businessType;

    private Byte naicsCode;

    private String address;

    private String city;

    private Byte state;

    private String postalCode;

    private Byte country;

    private String tin;

    private Byte tinType;

    private String birthdate;

    private String contactPhone;

    private String ext;

    private String email;

    private Byte verifyIdentityType;

    private String other;

    private String otherNumber;

    private Byte otherCountry;

    private Byte otherIssuingState;

    private String cashInAmount;

    private String cashInAccount;

    private String cashOutAmount;

    private String cashOutAccount;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getInvolvedTransaction() {
        return involvedTransaction;
    }

    public void setInvolvedTransaction(Byte involvedTransaction) {
        this.involvedTransaction = involvedTransaction;
    }

    public Byte getMultTransaction() {
        return multTransaction;
    }

    public void setMultTransaction(Byte multTransaction) {
        this.multTransaction = multTransaction;
    }

    public Byte getIsEntity() {
        return isEntity;
    }

    public void setIsEntity(Byte isEntity) {
        this.isEntity = isEntity;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName == null ? null : middleName.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName == null ? null : alternateName.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public Byte getNaicsCode() {
        return naicsCode;
    }

    public void setNaicsCode(Byte naicsCode) {
        this.naicsCode = naicsCode;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public Byte getCountry() {
        return country;
    }

    public void setCountry(Byte country) {
        this.country = country;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin == null ? null : tin.trim();
    }

    public Byte getTinType() {
        return tinType;
    }

    public void setTinType(Byte tinType) {
        this.tinType = tinType;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate == null ? null : birthdate.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getVerifyIdentityType() {
        return verifyIdentityType;
    }

    public void setVerifyIdentityType(Byte verifyIdentityType) {
        this.verifyIdentityType = verifyIdentityType;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber == null ? null : otherNumber.trim();
    }

    public Byte getOtherCountry() {
        return otherCountry;
    }

    public void setOtherCountry(Byte otherCountry) {
        this.otherCountry = otherCountry;
    }

    public Byte getOtherIssuingState() {
        return otherIssuingState;
    }

    public void setOtherIssuingState(Byte otherIssuingState) {
        this.otherIssuingState = otherIssuingState;
    }

    public String getCashInAmount() {
        return cashInAmount;
    }

    public void setCashInAmount(String cashInAmount) {
        this.cashInAmount = cashInAmount == null ? null : cashInAmount.trim();
    }

    public String getCashInAccount() {
        return cashInAccount;
    }

    public void setCashInAccount(String cashInAccount) {
        this.cashInAccount = cashInAccount == null ? null : cashInAccount.trim();
    }

    public String getCashOutAmount() {
        return cashOutAmount;
    }

    public void setCashOutAmount(String cashOutAmount) {
        this.cashOutAmount = cashOutAmount == null ? null : cashOutAmount.trim();
    }

    public String getCashOutAccount() {
        return cashOutAccount;
    }

    public void setCashOutAccount(String cashOutAccount) {
        this.cashOutAccount = cashOutAccount == null ? null : cashOutAccount.trim();
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
}