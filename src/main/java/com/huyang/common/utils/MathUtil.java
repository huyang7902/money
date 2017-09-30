package com.huyang.common.utils;

import java.math.BigDecimal;

/**
 * 数学计算工具类
 */
public class MathUtil {

    private static final int DEFAULT_SCALE = 2; // 默认保留小数位

    /**
     * 相加
     * @param args
     * @return
     */
    public static Double add(Object... args){
        BigDecimal b1 = null;
        for (Object d : args) {
            if (null == d) {
                continue;
            }
            BigDecimal bd = new BigDecimal(d.toString());
            if (null == b1) {
                b1 = bd;
            } else {
                b1 = b1.add(bd);
            }
        }
        if (null == b1) {
            return null;
        }
        return b1.doubleValue();

    }

    /**
     * 相减
     * @param d1
     * @param doubleArgs
     * @return
     */
    public static Double sub(Object d1, Object... doubleArgs){
        if (null == d1) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(d1.toString());
        for (Object d : doubleArgs) {
            if (null == d) {
                continue;
            }
            BigDecimal bd = new BigDecimal(d.toString());
            b1 = b1.subtract(bd);
        }
        return b1.doubleValue();

    }

    /**
     * 想乘
     * @param d1
     * @param doubleArgs
     * @return
     */
    public static Double mul(Object d1, Object... doubleArgs){
        if (null == d1) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(d1.toString());
        for (Object d : doubleArgs) {
            if (null == d) {
                continue;
            }
            BigDecimal bd = new BigDecimal(d.toString());
            b1 = b1.multiply(bd);
        }
        return b1.doubleValue();
    }

    /**
     * 相除
     * @param d1
     * @param doubleArgs
     * @return
     */
    public static Double defaultDiv(Object d1, Object... doubleArgs){
        return div(DEFAULT_SCALE, d1, doubleArgs);
    }

    /**
     * 相除，保留scale位小数
     * @param scale
     * @param d1
     * @param doubleArgs
     * @return
     */
    public static Double div(int scale, Object d1, Object... doubleArgs){
        if(scale < 0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (null == d1) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(d1.toString());
        for (Object d : doubleArgs) {
            if (null == d) {
                continue;
            }
            BigDecimal bd = new BigDecimal(d.toString());
            b1 = b1.divide(bd, scale, BigDecimal.ROUND_HALF_UP);
        }
        return b1.doubleValue();
    }

    /**
     * 相除，保留scale位小数
     * @param scale
     * @param round
     * @param d1
     * @param doubleArgs
     * @return
     */
    public static Double divHasSet(int scale,int round, Object d1, Object... doubleArgs){
        if(scale < 0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (null == d1) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(d1.toString());
        for (Object d : doubleArgs) {
            if (null == d) {
                continue;
            }
            BigDecimal bd = new BigDecimal(d.toString());
            b1 = b1.divide(bd, scale, round);
        }
        return b1.doubleValue();
    }

    /**
     * 四舍五入保留r位小数
     * @param num 需要调整的数据
     * @param s 保留位数
     * @return
     */
    public static Double roundDouble(Object num, Integer s) {
        if(null == num) {
            return null;
        }
        try {
            BigDecimal bd = new BigDecimal(num.toString());
            return bd.setScale(s, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 四舍五入，默认保留2位小数
     * @param num 需要调整的数据
     * @return 该数字的字符串值
     */
    public static String roundDouble(Object num) {
        Double d = roundDouble(num, DEFAULT_SCALE);
        if(null == d) {
            return "";
        }
        return d.toString();
    }

}
