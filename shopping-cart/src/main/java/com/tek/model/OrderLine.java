/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.model;

/**
 * Class for OrderLine model
 */
public class OrderLine {
    public OrderLine(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    private Item item;

    private Integer quantity;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        if (this.quantity == null) {
            return 0;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
