package com.huyang.lib.to;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/14 17:36
 */
public class MoneyResult implements Serializable{
    private BigDecimal totle;
    private BigDecimal hy;
    private BigDecimal hxb;
    private BigDecimal ldq;
    private BigDecimal avg;
    private String method;

    public BigDecimal getTotle() {
        return totle;
    }

    public void setTotle(BigDecimal totle) {
        this.totle = totle;
    }

    public BigDecimal getHy() {
        return hy;
    }

    public void setHy(BigDecimal hy) {
        this.hy = hy;
    }

    public BigDecimal getHxb() {
        return hxb;
    }

    public void setHxb(BigDecimal hxb) {
        this.hxb = hxb;
    }

    public BigDecimal getLdq() {
        return ldq;
    }

    public void setLdq(BigDecimal ldq) {
        this.ldq = ldq;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
