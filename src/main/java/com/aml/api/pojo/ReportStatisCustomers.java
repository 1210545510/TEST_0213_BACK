package com.aml.api.pojo;

import java.util.Date;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReportStatisCustomers extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1632610662779592977L;

	private Long id;

    private Date statisDate;

    private Integer customersSarCount1;

    private Integer customersSarCount2;

    private Integer customersSarCount3;

    private Integer customersSarCount4;

    private Integer nonCustomersSarCount1;

    private Integer nonCustomersSarCount2;

    private Integer nonCustomersSarCount3;

    private Integer nonCustomersSarCount4;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStatisDate() {
        return statisDate;
    }

    public void setStatisDate(Date statisDate) {
        this.statisDate = statisDate;
    }

    public Integer getCustomersSarCount1() {
        return customersSarCount1;
    }

    public void setCustomersSarCount1(Integer customersSarCount1) {
        this.customersSarCount1 = customersSarCount1;
    }

    public Integer getCustomersSarCount2() {
        return customersSarCount2;
    }

    public void setCustomersSarCount2(Integer customersSarCount2) {
        this.customersSarCount2 = customersSarCount2;
    }

    public Integer getCustomersSarCount3() {
        return customersSarCount3;
    }

    public void setCustomersSarCount3(Integer customersSarCount3) {
        this.customersSarCount3 = customersSarCount3;
    }

    public Integer getCustomersSarCount4() {
        return customersSarCount4;
    }

    public void setCustomersSarCount4(Integer customersSarCount4) {
        this.customersSarCount4 = customersSarCount4;
    }

    public Integer getNonCustomersSarCount1() {
        return nonCustomersSarCount1;
    }

    public void setNonCustomersSarCount1(Integer nonCustomersSarCount1) {
        this.nonCustomersSarCount1 = nonCustomersSarCount1;
    }

    public Integer getNonCustomersSarCount2() {
        return nonCustomersSarCount2;
    }

    public void setNonCustomersSarCount2(Integer nonCustomersSarCount2) {
        this.nonCustomersSarCount2 = nonCustomersSarCount2;
    }

    public Integer getNonCustomersSarCount3() {
        return nonCustomersSarCount3;
    }

    public void setNonCustomersSarCount3(Integer nonCustomersSarCount3) {
        this.nonCustomersSarCount3 = nonCustomersSarCount3;
    }

    public Integer getNonCustomersSarCount4() {
        return nonCustomersSarCount4;
    }

    public void setNonCustomersSarCount4(Integer nonCustomersSarCount4) {
        this.nonCustomersSarCount4 = nonCustomersSarCount4;
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