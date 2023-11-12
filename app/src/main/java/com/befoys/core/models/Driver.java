package com.befoys.core.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "driver")
public class Driver {
    @DatabaseField(columnName = "id", id = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "mobile")
    private String mobile;
    @DatabaseField(columnName = "licenceNumber")
    private String licenceNumber;
    @DatabaseField(columnName = "vehicleName")
    private String vehicleName;
    @DatabaseField(columnName = "vehiclePlaque")
    private String vehiclePlaque;
    @DatabaseField(columnName = "smsValue")
    private String smsValue;
    @DatabaseField(columnName = "vehicleVIN")
    private String vehicleVIN;
    @DatabaseField(columnName = "uniqueId")
    private String uniqueId;
    @DatabaseField(columnName = "uniqueValue")
    private String uniqueValue;
    @DatabaseField(columnName = "active")
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
}
