package com.befoys.core.models;

public class DriverFavorite {
    private Integer buyerId;
    private Integer shopResellerId;
    private String name;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String addressValue;

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getShopResellerId() {
        return shopResellerId;
    }

    public void setShopResellerId(Integer shopResellerId) {
        this.shopResellerId = shopResellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }
}
