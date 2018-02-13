package com.aml.api.pojo;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CloseReason extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5494271313399484323L;

	private Integer id;

    private String code;

    private String objectType;

    private String name;

    private String createOperation;

    private String dateOfCareation;

    private String modifiedOperation;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreateOperation() {
        return createOperation;
    }

    public void setCreateOperation(String createOperation) {
        this.createOperation = createOperation == null ? null : createOperation.trim();
    }

    public String getDateOfCareation() {
        return dateOfCareation;
    }

    public void setDateOfCareation(String dateOfCareation) {
        this.dateOfCareation = dateOfCareation == null ? null : dateOfCareation.trim();
    }

    public String getModifiedOperation() {
        return modifiedOperation;
    }

    public void setModifiedOperation(String modifiedOperation) {
        this.modifiedOperation = modifiedOperation == null ? null : modifiedOperation.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}