package com.befoys.core.models;

import java.util.ArrayList;

public class DriverOrder {
    private Integer id;
    private Code status;
    private ArrayList<DriverOrderItem> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Code getStatus() {
        return status;
    }

    public void setStatus(Code status) {
        this.status = status;
    }

    public ArrayList<DriverOrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<DriverOrderItem> items) {
        this.items = items;
    }

}
