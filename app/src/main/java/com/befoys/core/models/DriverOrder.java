package com.befoys.core.models;

import java.util.ArrayList;

public class DriverOrder {
    private Integer Id;
    private Code Status;
    private ArrayList<DriverOrderItem> Items;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Code getStatus() {
        return Status;
    }

    public void setStatus(Code status) {
        Status = status;
    }

    public ArrayList<DriverOrderItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<DriverOrderItem> items) {
        Items = items;
    }
}
