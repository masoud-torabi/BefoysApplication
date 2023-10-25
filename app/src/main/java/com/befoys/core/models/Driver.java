package com.befoys.core.models;

public class Driver {
    private int id;
    private String name;
    private Picture picture;
    private String mobile;
    private String licenceNumber;
    private String vehicleName;
    private String vehiclePlaque;
    private String smsValue;
    private String vehicleVIN;
    private String uniqueId;
    private String uniqueValue;
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehiclePlaque() {
        return vehiclePlaque;
    }

    public void setVehiclePlaque(String vehiclePlaque) {
        this.vehiclePlaque = vehiclePlaque;
    }

    public String getSmsValue() {
        return smsValue;
    }

    public void setSmsValue(String smsValue) {
        this.smsValue = smsValue;
    }

    public String getVehicleVIN() {
        return vehicleVIN;
    }

    public void setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public com.befoys.core.models.Picture getPicture() {
        return picture;
    }

    public void setPicture(com.befoys.core.models.Picture picture) {
        picture = picture;
    }

}
