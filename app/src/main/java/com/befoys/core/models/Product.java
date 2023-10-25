package com.befoys.core.models;

public class Product {
    private Integer Id;
    private String Name;
    private Integer Price;
    private Picture Picture;

    public Picture getPicture() {
        return Picture;
    }

    public void setPicture(Picture picture) {
        Picture = picture;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
}
