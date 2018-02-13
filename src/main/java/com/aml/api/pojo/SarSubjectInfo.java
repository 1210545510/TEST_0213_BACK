package com.aml.api.pojo;

import java.util.Date;
import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectInfo extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1037652340457382920L;

	private Long subId;

    private Long rid;

    private Integer check;

    private String lastName;

    private String firstName;

    private String middleName;

    private String suffix;

    private Byte gender;

    private Date birthdate;

    private String alternateName;

    private String businessType;

    private Byte naicsCode;

    private Integer tin;

    private Byte tinType;

    private String email;

    private String website;

    private Byte isFiler;

    private Byte subjectRole;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;
    
    private List<SarSubjectAddress> sarSubjectAddress;
    
    private List<SarSubjectAffect> sarSubjectAffect;
    
    private List<SarSubjectIdentification> sarSubjectIdentification;
    
    private List<SarSubjectInfoPhone> sarSubjectInfoPhone;
    
    private List<SarSubjectInstitution> sarSubjectInstitution;

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public Byte getIsFiler() {
        return isFiler;
    }

    public void setIsFiler(Byte isFiler) {
        this.isFiler = isFiler;
    }

    public Byte getSubjectRole() {
        return subjectRole;
    }

    public void setSubjectRole(Byte subjectRole) {
        this.subjectRole = subjectRole;
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

	public List<SarSubjectAddress> getSarSubjectAddress() {
		return sarSubjectAddress;
	}

	public void setSarSubjectAddress(List<SarSubjectAddress> sarSubjectAddress) {
		this.sarSubjectAddress = sarSubjectAddress;
	}

	public List<SarSubjectAffect> getSarSubjectAffect() {
		return sarSubjectAffect;
	}

	public void setSarSubjectAffect(List<SarSubjectAffect> sarSubjectAffect) {
		this.sarSubjectAffect = sarSubjectAffect;
	}

	public List<SarSubjectIdentification> getSarSubjectIdentification() {
		return sarSubjectIdentification;
	}

	public void setSarSubjectIdentification(List<SarSubjectIdentification> sarSubjectIdentification) {
		this.sarSubjectIdentification = sarSubjectIdentification;
	}

	public List<SarSubjectInfoPhone> getSarSubjectInfoPhone() {
		return sarSubjectInfoPhone;
	}

	public void setSarSubjectInfoPhone(List<SarSubjectInfoPhone> sarSubjectInfoPhone) {
		this.sarSubjectInfoPhone = sarSubjectInfoPhone;
	}

	public List<SarSubjectInstitution> getSarSubjectInstitution() {
		return sarSubjectInstitution;
	}

	public void setSarSubjectInstitution(List<SarSubjectInstitution> sarSubjectInstitution) {
		this.sarSubjectInstitution = sarSubjectInstitution;
	}
}