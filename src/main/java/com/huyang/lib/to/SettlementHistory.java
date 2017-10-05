package com.huyang.lib.to;

import java.math.BigDecimal;
import java.util.Date;

public class SettlementHistory {
    private Integer id;

    private BigDecimal totleMoney;

    private BigDecimal avgMoney;

    private String detail;

    private Date createDate;

    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotleMoney() {
        return totleMoney;
    }

    public void setTotleMoney(BigDecimal totleMoney) {
        this.totleMoney = totleMoney;
    }

    public BigDecimal getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(BigDecimal avgMoney) {
        this.avgMoney = avgMoney;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }
}