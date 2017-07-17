/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.service;

import com.tek.model.Category;
import com.tek.model.Order;
import com.tek.model.OrderLine;
import com.tek.util.CommonUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Class for shopping related calculations
 */
public class CalculationServiceImpl implements CalculationService {

    private static final BigDecimal LOCAL_TAX_PERCENT = BigDecimal.TEN;
    private static final BigDecimal IMPORT_TAX_PERCENT = BigDecimal.valueOf(15);

    private static final BigDecimal VAL_100 = BigDecimal.valueOf(100);

    /**
     * Method to calculate price of item/ total tax/ grand total of orders
     *
     * @param orders - input orders
     */
    public void calculate(Map<String, Order> orders) {

        validateIfOrdersPresent(orders);

        BigDecimal grandTotal = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal orderTotal = null;
        BigDecimal totalTax = null;

        for (Map.Entry<String, Order> order : orders.entrySet()) {
            totalTax = BigDecimal.ZERO;
            orderTotal = BigDecimal.ZERO;

            CommonUtil.print("*******" + order.getKey() + "*******");

            for (OrderLine orderLine : validateOrderLines(order.getValue().getOrderLines())) {
                totalPrice = calculateTotalPrice(orderLine, deriveTax(orderLine.getItem().getCategory()));

                CommonUtil.print(orderLine.getQuantity() + " " + orderLine.getItem().getName() + ": ", totalPrice);

                totalTax = totalTax.add(calculateTax(orderLine.getItem().getPrice(), deriveTax(orderLine.getItem().getCategory())));

                orderTotal = orderTotal.add(calculateTotalPriceWithoutTax(orderLine));
            }
            //Total Sales Tax
            CommonUtil.print("Sales Tax: ", totalTax);

            // Print out the total amount
            CommonUtil.print("Total: ", orderTotal);

            grandTotal = grandTotal.add(orderTotal);
        }
        CommonUtil.print("Sum of orders: ", grandTotal);
    }

    /**
     * Default method to validate if orders are present
     *
     * @param orders
     */
    private void validateIfOrdersPresent(Map<String, Order> orders) {
        if (orders == null || orders.size() == 0) {
            throw new IllegalArgumentException("No order for calculation");
        }
    }

    /**
     * Default method to validate order lines
     *
     * @param orderLines
     * @return
     */
    private List<OrderLine> validateOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.size() == 0) {
            throw new IllegalArgumentException("No order lines for calculation");
        }
        return orderLines;
    }

    /**
     * Private method to derive tax percentage based on local/ imported item category
     *
     * @param category - category of item
     * @return - tax percentage
     */
    private BigDecimal deriveTax(Category category) {
        if (category.getAllCategories().toLowerCase().contains("import")) {
            return IMPORT_TAX_PERCENT.divide(VAL_100);
        } else {
            return LOCAL_TAX_PERCENT.divide(VAL_100);
        }
    }


}
