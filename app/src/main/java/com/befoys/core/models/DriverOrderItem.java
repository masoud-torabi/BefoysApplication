package com.befoys.core.models;

public class DriverOrderItem {
    private Integer id;
    private Product product;
    private Color color;
    private Size size;
    private Float count;
    private Boolean isCanceled;
    private Float cancelCount;
    private String cancelDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Float getCount() {
        return count;
    }

    public void setCount(Float count) {
        this.count = count;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Float getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(Float cancelCount) {
        this.cancelCount = cancelCount;
    }

    public String getCancelDescription() {
        return cancelDescription;
    }

    public void setCancelDescription(String cancelDescription) {
        this.cancelDescription = cancelDescription;
    }

}
