package com.befoys.core.models;

public class TravelStep {
    private Integer Id;
    private Integer ResellerOrderId;
    private Integer BuyerId;
    private Integer ShopResellerId;
    private Code Type;
    private Code Status;
    private String Name;
    private Double Latitude;
    private Double Longitude;
    private String Phone;
    private String AddressValue;
    private Double ProductCount;
    private Boolean IsFavorite;

    public Boolean getFavorite() {
        return IsFavorite;
    }

    public void setFavorite(Boolean favorite) {
        IsFavorite = favorite;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getResellerOrderId() {
        return ResellerOrderId;
    }

    public void setResellerOrderId(Integer resellerOrderId) {
        ResellerOrderId = resellerOrderId;
    }

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

    public Code getType() {
        return Type;
    }

    public void setType(Code type) {
        Type = type;
    }

    public Code getStatus() {
        return Status;
    }

    public void setStatus(Code status) {
        Status = status;
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

    public Double getProductCount() {
        return ProductCount;
    }

    public void setProductCount(Double productCount) {
        ProductCount = productCount;
    }
}
