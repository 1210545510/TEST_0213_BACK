package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustomerTypes extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5937712803807975368L;

	private Integer id;

    private String codeName;

    private String descripition;

    private String riskModel;

    private String accepetionModel;

    private String caseScore;

    private String creatBy;

    private Date creatDate;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public String getDescripition() {
        return descripition;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition == null ? null : descripition.trim();
    }

    public String getRiskModel() {
        return riskModel;
    }

    public void setRiskModel(String riskModel) {
        this.riskModel = riskModel == null ? null : riskModel.trim();
    }

    public String getAccepetionModel() {
        return accepetionModel;
    }

    public void setAccepetionModel(String accepetionModel) {
        this.accepetionModel = accepetionModel == null ? null : accepetionModel.trim();
    }

    public String getCaseScore() {
        return caseScore;
    }

    public void setCaseScore(String caseScore) {
        this.caseScore = caseScore == null ? null : caseScore.trim();
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy == null ? null : creatBy.trim();
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}