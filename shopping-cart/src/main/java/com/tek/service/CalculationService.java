/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.service;

import com.tek.model.Order;
import com.tek.model.OrderLine;
import com.tek.util.NumericUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Interface for all shopping calculation related implementations
 */
public interface CalculationService {
    Integer ROUNDING_SCALE = 2;
    RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    void calculate(Map<String, Order> orders);

    /**
     * Default method to calculate total price of an item without tax
     *
     * @param orderLine - input order line
     * @return - total price exclusive of tax
     */
    default BigDecimal calculateTotalPriceWithoutTax(OrderLine orderLine) {
        if (orderLine == null || orderLine.getItem() == null) {
            throw new IllegalArgumentException("Order line is missing");
        }
        BigDecimal totalPrice = orderLine.getItem().getPrice().
                multiply(BigDecimal.valueOf(orderLine.getQuantity()));
        return NumericUtil.round(totalPrice, ROUNDING_SCALE, ROUNDING_MODE);
    }

    /**
     * Private method to calculate total price of an item considering tax
     *
     * @param orderLine - input order line
     * @return - total price inclusive of tax
     */
    default BigDecimal calculateTotalPrice(OrderLine orderLine, BigDecimal tax) {
        if (orderLine == null || orderLine.getItem() == null || tax == null) {
            throw new IllegalArgumentException("Order line/ tax is missing");
        }
        BigDecimal totalPrice = orderLine.getItem().getPrice().
                add(calculateTax(orderLine.getItem().getPrice(), tax)).
                multiply(BigDecimal.valueOf(orderLine.getQuantity()));
        return NumericUtil.round(totalPrice, ROUNDING_SCALE, ROUNDING_MODE);
    }

    /**
     * Private method to calculate tax
     *
     * @param price   - price of item
     * @param percent - tax percentage
     * @return - item tax
     */
    default BigDecimal calculateTax(BigDecimal price, BigDecimal percent) {
        if (percent == null || percent == null) {
            throw new IllegalArgumentException("Invalid arguments passed");
        }
        return price.multiply(percent);
    }

}
