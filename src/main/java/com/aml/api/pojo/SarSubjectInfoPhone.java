package com.aml.api.pojo;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectInfoPhone extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7906310539006561367L;

	private Long id;

    private Long subId;

    private String phone;

    private String phoneExt;

    private Byte phoneType;

    private Byte isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt == null ? null : phoneExt.trim();
    }

    public Byte getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Byte phoneType) {
        this.phoneType = phoneType;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}