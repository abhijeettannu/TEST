/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.service;

import com.tek.model.Category;
import com.tek.model.Item;
import com.tek.model.Order;
import com.tek.model.OrderLine;
import org.junit.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class to test negative and positive test scenarios of CalculatorService class methods
 */
public class CalculatorServiceIT {

    CalculationService service = new CalculationServiceImpl();
    private static final Item book = new Item();
    private static final Item musicCD = new Item();
    private static final Map<String, Order> orders = new LinkedHashMap<String, Order>();


    @BeforeClass
    public static void init() {
        book.setName("book").setPrice(BigDecimal.valueOf(12.49)).setCategory(new Category("local"));
        musicCD.setName("music CD").setPrice(BigDecimal.valueOf(14.99)).setCategory(new Category("local"));
    }

    @AfterClass
    public static void destroy() {
        //do cleanup here
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalPriceWithoutTax_invalid_input() {
        service.calculateTotalPriceWithoutTax(null);
    }

    @Test
    public void testCalculateTotalPriceWithoutTax_valid_input() {
        OrderLine orderLine = new OrderLine(book, 1);
        BigDecimal totalPrice = service.calculateTotalPriceWithoutTax(new OrderLine(book, 1));
        Assert.assertEquals(totalPrice, orderLine.getItem().getPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalPriceTax_invalid_input() {
        service.calculateTotalPrice(null, null);
    }

    @Test
    public void testCalculateTotalPrice_valid_input() {
        OrderLine orderLine = new OrderLine(book, 1);
        BigDecimal totalPrice = service.calculateTotalPrice(new OrderLine(book, 1), BigDecimal.TEN.divide(BigDecimal.valueOf(100)));
        Assert.assertEquals(totalPrice, orderLine.getItem().getPrice().
                add(orderLine.getItem().getPrice().multiply(BigDecimal.TEN).divide(BigDecimal.valueOf(100))).
                multiply(BigDecimal.valueOf(orderLine.getQuantity())).
                setScale(2, RoundingMode.HALF_UP));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTax_invalid_input() {
        service.calculateTax(null, null);
    }

    @Test
    public void testCalculateTax_valid_input() {
        BigDecimal tax = service.calculateTax(BigDecimal.TEN, BigDecimal.valueOf(0.13));
        Assert.assertEquals(tax, BigDecimal.TEN.multiply(BigDecimal.valueOf(0.13)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_invalid_input_1() {
        service.calculate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_invalid_input_2() {
        Map<String, Order> orders = new HashMap<>();
        service.calculate(orders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_invalid_input_3() {
        Map<String, Order> orders = new HashMap<>();
        Order order = new Order();
        orders.put("", order);
        service.calculate(orders);
    }

}
