package com.befoys.core.models;

public class Slider {
    private int Id;
    private String Name;
    private Picture Picture;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Picture getPicture() {
        return Picture;
    }

    public void setPicture(Picture picture) {
        Picture = picture;
    }
}
