package org.liukai.tutorial.utils;

import java.math.BigDecimal;

public class CalHelper {

    /**
     * 加法
     *
     * @return 两个数的和
     */
    public static double add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法
     *
     * @return 两个数的差
     */

    public static double sub(String firstValue, String secondValue) {
        BigDecimal b1 = new BigDecimal(firstValue);
        BigDecimal b2 = new BigDecimal(secondValue);
        return b2.subtract(b1).doubleValue();
    }

    /**
     * 乘法
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个数的积
     */
    public static double mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法（保留小数点后两位，四舍五入）
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个数的商
     */
    public static double div(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b2.divide(b1, 2, BigDecimal.ROUND_HALF_UP).doubleValue();//相处并保留两位小数四舍五入
    }
}
