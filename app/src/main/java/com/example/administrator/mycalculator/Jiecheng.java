package com.example.administrator.mycalculator;

import java.math.BigDecimal;

public class Jiecheng {
    public Jiecheng() {
    }

    public static BigDecimal Jiecheng(double n){
        BigDecimal result = new BigDecimal(1);
        BigDecimal a;
        for(int i = 2; i <= n; i++){
            a = new BigDecimal(i);              //将i转换为BigDecimal类型
            result = result.multiply(a);        //不用result*a，因为BigDecimal类型没有定义*操作</span><span>
        }
        return result;
    }
}
