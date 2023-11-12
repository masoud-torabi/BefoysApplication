package com.befoys.core.models;

public class TravelStep {
    private Integer id;
    private Integer resellerOrderId;
    private Integer buyerId;
    private Integer shopResellerId;
    private Code type;
    private Code status;
    private String name;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String addressValue;
    private Double productCount;
    private Boolean isFavorite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResellerOrderId() {
        return resellerOrderId;
    }

    public void setResellerOrderId(Integer resellerOrderId) {
        this.resellerOrderId = resellerOrderId;
    }

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

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }

    public Code getStatus() {
        return status;
    }

    public void setStatus(Code status) {
        this.status = status;
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

    public Double getProductCount() {
        return productCount;
    }

    public void setProductCount(Double productCount) {
        this.productCount = productCount;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
