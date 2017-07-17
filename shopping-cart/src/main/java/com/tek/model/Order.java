/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Order model
 */
public class Order {

    private User user;

    private List<OrderLine> orderLines;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    /**
     * Method to add order line to this order
     *
     * @param orderLine - order line to add
     * @return - current order
     */
    public Order addOrderLine(OrderLine orderLine) {
        if (this.orderLines == null) {
            orderLines = new ArrayList<OrderLine>();
        }
        orderLines.add(orderLine);
        return this;
    }


}
