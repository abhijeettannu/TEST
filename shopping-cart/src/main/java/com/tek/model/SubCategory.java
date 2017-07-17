/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.model;

/**
 * Class for SubCategory model
 */
public class SubCategory implements SuperCategory {
    public SubCategory() {
        this.name = "";
        this.subSubCategory = new SubSubCategory();
    }

    private String name;

    private SubSubCategory subSubCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubSubCategory getSubSubCategory() {
        return subSubCategory;
    }

    public void setSubSubCategory(SubSubCategory subSubCategory) {
        this.subSubCategory = subSubCategory;
    }

    public String getAllCategories() {
        return this.getName() + "/ " + getSubSubCategory().getName();
    }
}
