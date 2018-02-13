package com.aml.api.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SystemParameters {
    private Integer id;

    private String code;

    private String name;

    private String accountBussinessDate;

    private String lastEndOfDay;

    private Date date;

    private String profileReviewFrequary;

    private String server;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccountBussinessDate() {
        return accountBussinessDate;
    }

    public void setAccountBussinessDate(String accountBussinessDate) {
        this.accountBussinessDate = accountBussinessDate == null ? null : accountBussinessDate.trim();
    }

    public String getLastEndOfDay() {
        return lastEndOfDay;
    }

    public void setLastEndOfDay(String lastEndOfDay) {
        this.lastEndOfDay = lastEndOfDay == null ? null : lastEndOfDay.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProfileReviewFrequary() {
        return profileReviewFrequary;
    }

    public void setProfileReviewFrequary(String profileReviewFrequary) {
        this.profileReviewFrequary = profileReviewFrequary == null ? null : profileReviewFrequary.trim();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server == null ? null : server.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}