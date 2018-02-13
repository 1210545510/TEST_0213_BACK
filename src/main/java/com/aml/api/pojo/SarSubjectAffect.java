package com.aml.api.pojo;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectAffect extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4779783699710434188L;

	private Long affectId;

    private Long subId;

    private String institutionTin;

    private String accountNumber;

    private Byte isDeleted;

    public Long getAffectId() {
        return affectId;
    }

    public void setAffectId(Long affectId) {
        this.affectId = affectId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getInstitutionTin() {
        return institutionTin;
    }

    public void setInstitutionTin(String institutionTin) {
        this.institutionTin = institutionTin == null ? null : institutionTin.trim();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}