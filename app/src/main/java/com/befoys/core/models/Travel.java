package com.befoys.core.models;

import java.util.ArrayList;

public class Travel {

    private Integer id;
    private String name;
    private Code status;
    private String startDatetime;
    private Double productCount;
    private Boolean active;
    private ArrayList<TravelStep> travelSteps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Code getStatus() {
        return status;
    }

    public void setStatus(Code status) {
        this.status = status;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Double getProductCount() {
        return productCount;
    }

    public void setProductCount(Double productCount) {
        this.productCount = productCount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ArrayList<TravelStep> getTravelSteps() {
        return travelSteps;
    }

    public void setTravelSteps(ArrayList<TravelStep> travelSteps) {
        this.travelSteps = travelSteps;
    }


}
