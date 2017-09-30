package com.huyang.lib.to;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/14 17:49
 */
public class UserTotle implements Serializable{
    private BigDecimal sumMoney;
    private String userName;

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
