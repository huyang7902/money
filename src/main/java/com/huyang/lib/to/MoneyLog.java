package com.huyang.lib.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MoneyLog implements Serializable{
    private Integer id;

    private BigDecimal money;

    private String usefor;

    private Integer uid;

    private String userName;

    private Date createTime;

    private Byte weeks;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUsefor() {
        return usefor;
    }

    public void setUsefor(String usefor) {
        this.usefor = usefor == null ? null : usefor.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getWeeks() {
        return weeks;
    }

    public void setWeeks(Byte weeks) {
        this.weeks = weeks;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}