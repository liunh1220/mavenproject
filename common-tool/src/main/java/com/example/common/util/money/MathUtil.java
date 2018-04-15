package com.example.common.util.money;

import java.math.BigDecimal;

public class MathUtil
{
    
    /**
     * 四舍五入  小数bit位
     * @param number 数字字符串
     * @param bit 保留小数位数  ,未使用
     * @return 双精度的数字
     */
    public static double round(String number,int bit)
    {
        BigDecimal b1 = new BigDecimal(number); 
        if(bit<0){
        	bit=0;
        }
        Double d1 = b1.setScale(bit, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d1;
    }
    
    /**
     * 截取3位,不四舍五入
     * @param number
     * @param bit,未使用
     * @return
     */
    public static double round(double number)
    {
        BigDecimal b1 = new BigDecimal(number); 
        Double d1 = b1.setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
        return d1;
    }
    
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }


    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
}
