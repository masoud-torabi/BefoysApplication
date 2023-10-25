package com.befoys.core.models;

public class DriverFavorite {
    private Integer BuyerId;
    private Integer ShopResellerId;
    private String Name;
    private Double Latitude;
    private Double Longitude;
    private String Phone;
    private String AddressValue;

    public Integer getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(Integer buyerId) {
        BuyerId = buyerId;
    }

    public Integer getShopResellerId() {
        return ShopResellerId;
    }

    public void setShopResellerId(Integer shopResellerId) {
        ShopResellerId = shopResellerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddressValue() {
        return AddressValue;
    }

    public void setAddressValue(String addressValue) {
        AddressValue = addressValue;
    }
}
