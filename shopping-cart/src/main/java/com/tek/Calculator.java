/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek;

import com.tek.model.Category;
import com.tek.model.Item;
import com.tek.model.Order;
import com.tek.model.OrderLine;
import com.tek.service.CalculationServiceImpl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculator {

    /**
     * Sample items in inventory
     */
    private static final Item book = new Item();
    private static final Item musicCD = new Item();
    private static final Item chocolateBar = new Item();
    private static final Item importedChocolateBox1 = new Item();
    private static final Item importedChocolateBox2 = new Item();
    private static final Item importedPerfumeBottle1 = new Item();
    private static final Item importedPerfumeBottle2 = new Item();
    private static final Item perfumeBottle = new Item();
    private static final Item pills = new Item();

    /**
     * Start
     *
     * @param args
     */
    public static void main(String args[]) {
        start();
    }

    /**
     * Method to start calculation
     */
    public static void start() {
        calculate(getOrders());

    }

    /**
     * Method to calculate
     *
     * @param orders - input orders
     */
    public static void calculate(Map<String, Order> orders) {
        CalculationServiceImpl service = new CalculationServiceImpl();
        service.calculate(orders);
    }

    /**
     * Get all orders for calculation
     *
     * @return
     */
    public static Map<String, Order> getOrders() {
        Map<String, Order> orders = new LinkedHashMap<String, Order>();

        //order 1
        Order order = new Order();
        order.addOrderLine(new OrderLine(book, 1));
        order.addOrderLine(new OrderLine(musicCD, 1));
        order.addOrderLine(new OrderLine(chocolateBar, 1));
        orders.put("Order 1", order);

        //order 2
        order = new Order();
        order.addOrderLine(new OrderLine(importedChocolateBox1, 1));
        order.addOrderLine(new OrderLine(importedPerfumeBottle1, 1));
        orders.put("Order 2", order);

        //order 3
        order = new Order();
        order.addOrderLine(new OrderLine(importedPerfumeBottle2, 1));
        order.addOrderLine(new OrderLine(perfumeBottle, 1));
        order.addOrderLine(new OrderLine(pills, 1));
        order.addOrderLine(new OrderLine(importedChocolateBox2, 1));
        orders.put("Order 3", order);

        return orders;
    }

    /**
     * Init sample items in inventory. //Generally all this data would be in database.
     */
    static {
        book.setName("book").setPrice(BigDecimal.valueOf(12.49)).setCategory(new Category("local"));
        musicCD.setName("music CD").setPrice(BigDecimal.valueOf(14.99)).setCategory(new Category("local"));
        chocolateBar.setName("chocolate bar").setPrice(BigDecimal.valueOf(0.85)).setCategory(new Category("local"));
        importedChocolateBox1.setName("imported box of chocolate").setPrice(BigDecimal.valueOf(10)).setCategory(new Category("imported"));
        importedPerfumeBottle1.setName("imported bottle of perfume").setPrice(BigDecimal.valueOf(47.50)).setCategory(new Category("imported"));
        importedPerfumeBottle2.setName("Imported bottle of perfume").setPrice(BigDecimal.valueOf(27.99)).setCategory(new Category("imported"));
        perfumeBottle.setName("bottle of perfume").setPrice(BigDecimal.valueOf(18.99)).setCategory(new Category("local"));
        pills.setName("packet of headache pills").setPrice(BigDecimal.valueOf(9.75)).setCategory(new Category("local"));
        importedChocolateBox2.setName("box of imported chocolates").setPrice(BigDecimal.valueOf(11.25)).setCategory(new Category("imported"));
    }

}
