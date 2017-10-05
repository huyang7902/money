package com.huyang.lib.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/14 17:36
 */
public class MoneyResult implements Serializable{
    private static final long serialVersionUID = 9116278247765482587L;

    private BigDecimal totle;
    private BigDecimal avg;
    private List<MoneyLog> moneyLogList;

    public BigDecimal getTotle() {
        return totle;
    }

    public void setTotle(BigDecimal totle) {
        this.totle = totle;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public List<MoneyLog> getMoneyLogList() {
        return moneyLogList;
    }

    public void setMoneyLogList(List<MoneyLog> moneyLogList) {
        this.moneyLogList = moneyLogList;
    }
}
