/*
 * Copyright (c) 2017 TEST and/or its affiliates. All rights reserved.
 *
 */
package com.tek.model;

/**
 * Class for SubSubCategory model
 */
public class SubSubCategory implements SuperCategory {
    public SubSubCategory() {
        this.name = "";
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllCategories() {
        return this.getName();
    }
}
