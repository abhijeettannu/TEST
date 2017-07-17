/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class for Item model
 */
public class Item {

    private String name;

    private Department department;

    private Category category;

    private String description;

    private BigDecimal price;

    private List<Review> reviews;

    /**
     * Method to get average user rating for this item
     *
     * @return - average rating
     */
    public Integer getAverageRating() {
        Integer sum = 0;
        Integer avg = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        if (sum != 0) {
            avg = sum / reviews.size();
        }
        return avg;
    }

    public Department getDepartment() {
        return department;
    }

    public Item setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        if (this.category == null) {
            return new Category();
        }
        return category;
    }

    public Item setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        if (this.price == null) {
            return BigDecimal.ZERO;
        }
        return price;
    }

    public Item setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Item setReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }
}