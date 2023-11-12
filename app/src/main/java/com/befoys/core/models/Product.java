package com.befoys.core.models;

public class Product {
    private Integer id;
    private String name;
    private String unitName;
    private Integer price;
    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        unitName = unitName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        price = price;
    }
}
