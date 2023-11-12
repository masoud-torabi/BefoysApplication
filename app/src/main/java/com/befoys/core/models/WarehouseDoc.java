package com.befoys.core.models;

import java.util.ArrayList;

public class WarehouseDoc {
    private int id;
    private int relatedOrderId;
    private String sourceName;
    private String destinationName;
    private String description;
    private String statusName;
    private String userCreator;
    private String persianDatetime;
    private ArrayList<WarehouseDocItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelatedOrderId() {
        return relatedOrderId;
    }

    public void setRelatedOrderId(int relatedOrderId) {
        this.relatedOrderId = relatedOrderId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public String getPersianDatetime() {
        return persianDatetime;
    }

    public void setPersianDatetime(String persianDatetime) {
        this.persianDatetime = persianDatetime;
    }

    public ArrayList<WarehouseDocItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<WarehouseDocItem> items) {
        this.items = items;
    }

}
