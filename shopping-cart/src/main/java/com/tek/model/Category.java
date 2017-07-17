/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.model;

/**
 * Class for Category model
 */
public class Category implements SuperCategory {
    public Category() {
        this.name = "";
        this.subCategory = new SubCategory();
    }

    public Category(String name) {
        this.name = name;
        this.subCategory = new SubCategory();
    }

    private String name;

    private SubCategory subCategory;

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllCategories() {
        return getName() + "/ " + getSubCategory().getName() + "/ " + getSubCategory().getSubSubCategory().getName();
    }
}
