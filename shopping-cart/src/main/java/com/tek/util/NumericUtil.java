/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumericUtil {

    /**
     * Private method to round price amount
     *
     * @param val - input value
     * @return - rounded value
     */
    public static BigDecimal round(BigDecimal val, Integer roundingScale, RoundingMode roundingMode) {
        if (val != null) {
            return val.setScale(roundingScale, roundingScale);
        }
        return null;
    }
}
