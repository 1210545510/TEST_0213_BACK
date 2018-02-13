package com.aml.api.pojo;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarSubjectIdentification extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3563314712256143571L;

	private Long identifyId;

    private Long subId;

    private Byte type;

    private String value;

    private Integer number;

    private Byte issuingState;

    private Byte country;

    private Byte isDeleted;

    public Long getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(Long identifyId) {
        this.identifyId = identifyId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Byte getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(Byte issuingState) {
        this.issuingState = issuingState;
    }

    public Byte getCountry() {
        return country;
    }

    public void setCountry(Byte country) {
        this.country = country;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}