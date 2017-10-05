package com.huyang.criteria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/10/2 13:12
 */
public class MoneyLogCriteriaTO implements Serializable{

    private Integer id;

    private BigDecimal money;

    private String usefor;

    private Integer uid;

    private String userName;

    private Date createTime;

    private Byte weeks;

    private Byte status;

    /**********附加查询条件********************/

    private Integer year;
    private Integer month;

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
        this.usefor = usefor;
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
        this.userName = userName;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
