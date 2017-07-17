/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class to hold Item related details
 */
class Item {

    private String description;
    private BigDecimal price;

    Item(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

/**
 * Model class to hold OrderLine related details
 */
class OrderLine {

    private Integer quantity;
    private Item item;

    OrderLine(Item item, Integer quantity) throws Exception {
        if (item == null) {
            System.err.println("ERROR - Item is NULL");
            throw new Exception("Item is NULL");
        }
        assert quantity > 0;
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

/**
 * Model class to hold Order related details.
 */
class Order {

    private List<OrderLine> orderLines;

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    public OrderLine get(int i) {
        if (orderLines != null) {
            return orderLines.get(i);
        }
        return null;
    }

    public void add(OrderLine o) throws Exception {
        if (o == null) {
            System.err.println("ERROR - Order is NULL");
            throw new IllegalArgumentException("Order is NULL");
        }
        if (orderLines == null) {
            orderLines = new ArrayList<OrderLine>();
        }
        orderLines.add(o);
    }
}

/**
 * Class for handling order calculations
 */
class Calculator {

    /**
     * Method to calculate the total price
     *
     * @param orders
     */
    public void calculate(Map<String, Order> orders) {

        BigDecimal grandTotal = BigDecimal.ZERO;
        BigDecimal totalTax = null;
        BigDecimal total = null;
        BigDecimal tax = null;
        BigDecimal totalPrice = null;

        // Iterate through the orders
        for (Map.Entry<String, Order> entry : orders.entrySet()) {
            System.out.println("*******" + entry.getKey() + "*******");
            totalTax = BigDecimal.ZERO;
            total = BigDecimal.ZERO;

            Order r = entry.getValue();

            for (OrderLine orderLine : entry.getValue().getOrderLines()) {
                // Calculate the taxes
                tax = BigDecimal.ZERO;
                if (orderLine.getItem().getDescription().toLowerCase().contains("imported")) {
                    tax = orderLine.getItem().getPrice().multiply(BigDecimal.valueOf(0.15)); // Extra 5% tax on imported items
                } else {
                    tax = orderLine.getItem().getPrice().multiply(BigDecimal.valueOf(0.10));
                }

                totalPrice = orderLine.getItem().getPrice().add(tax).multiply(BigDecimal.valueOf(orderLine.getQuantity()));

                System.out.println(orderLine.getQuantity() + " " + orderLine.getItem().getDescription() + ": " + totalPrice.setScale(2, RoundingMode.HALF_UP));

                totalTax = totalTax.add(tax);
                total = total.add(orderLine.getItem().getPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())));
            }

            // Print out the total taxes
            System.out.println("Sales Tax: " + totalTax.setScale(2, RoundingMode.HALF_UP));

            // Print out the total amount
            System.out.println("Total: " + total.setScale(2, RoundingMode.HALF_UP));
            grandTotal = grandTotal.add(total);
        }

        System.out.println("Sum of orders: " + grandTotal);
    }
}

/**
 * Class to start the calculation
 */
public class Foo {

    public static void main(String[] args) throws Exception {

        Map<String, Order> o = new LinkedHashMap<String, Order>();

        Order c = new Order();

        double grandTotal = 0;

        c.add(new OrderLine(new Item("book", BigDecimal.valueOf(12.49)), 1));
        c.add(new OrderLine(new Item("music CD", BigDecimal.valueOf(14.99)), 1));
        c.add(new OrderLine(new Item("chocolate bar", BigDecimal.valueOf(0.85)), 1));

        o.put("Order 1", c);

        c = new Order();

        c.add(new OrderLine(new Item("imported box of chocolate", BigDecimal.valueOf(10)), 1));
        c.add(new OrderLine(new Item("imported bottle of perfume", BigDecimal.valueOf(47.50)), 1));

        o.put("Order 2", c);

        c = new Order();

        c.add(new OrderLine(new Item("Imported bottle of perfume", BigDecimal.valueOf(27.99)), 1));
        c.add(new OrderLine(new Item("bottle of perfume", BigDecimal.valueOf(18.99)), 1));
        c.add(new OrderLine(new Item("packet of headache pills", BigDecimal.valueOf(9.75)), 1));
        c.add(new OrderLine(new Item("box of imported chocolates", BigDecimal.valueOf(11.25)), 1));

        o.put("Order 3", c);

        new Calculator().calculate(o);

    }
}
