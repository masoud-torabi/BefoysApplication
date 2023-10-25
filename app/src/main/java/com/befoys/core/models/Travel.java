package com.befoys.core.models;

import java.util.ArrayList;

public class Travel {
    private Integer Id;
    private String Name;
    private Code Status;
    private String StartDatetime;
    private Double ProductCount;
    private Boolean Active;
    private ArrayList<TravelStep> TravelSteps;

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

    public Code getStatus() {
        return Status;
    }

    public void setStatus(Code status) {
        Status = status;
    }

    public String getStartDatetime() {
        return StartDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        StartDatetime = startDatetime;
    }

    public Double getProductCount() {
        return ProductCount;
    }

    public void setProductCount(Double productCount) {
        ProductCount = productCount;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public ArrayList<TravelStep> getTravelSteps() {
        return TravelSteps;
    }

    public void setTravelSteps(ArrayList<TravelStep> travelSteps) {
        TravelSteps = travelSteps;
    }
}
