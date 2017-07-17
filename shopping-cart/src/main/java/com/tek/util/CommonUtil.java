package com.tek.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonUtil {

    /**
     * Private method to print values to console
     *
     * @param val
     */
    public static void print(String val) {
        System.out.println(val);
    }

    /**
     * Private method to print values to console
     *
     * @param val1 - value 1
     * @param val2 - value 2
     */
    public static void print(String val1, BigDecimal val2) {
        System.out.println(val1 + NumericUtil.round(val2, 2, RoundingMode.HALF_UP));
    }
}
