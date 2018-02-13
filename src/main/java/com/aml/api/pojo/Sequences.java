package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Sequences extends BasePoJo  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4461338620532096512L;

	private Integer id;

    private String objectType;

    private String width;

    private String conlaChart;

    private String createPression;

    private String sequernce;

    private String value;

    private String createPeer;

    private Date createDate;

    private String lastModify;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    public String getConlaChart() {
        return conlaChart;
    }

    public void setConlaChart(String conlaChart) {
        this.conlaChart = conlaChart == null ? null : conlaChart.trim();
    }

    public String getCreatePression() {
        return createPression;
    }

    public void setCreatePression(String createPression) {
        this.createPression = createPression == null ? null : createPression.trim();
    }

    public String getSequernce() {
        return sequernce;
    }

    public void setSequernce(String sequernce) {
        this.sequernce = sequernce == null ? null : sequernce.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getCreatePeer() {
        return createPeer;
    }

    public void setCreatePeer(String createPeer) {
        this.createPeer = createPeer == null ? null : createPeer.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify == null ? null : lastModify.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}