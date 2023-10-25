package com.befoys.core.models;

public class DriverOrderItem {
    private Integer Id;
    private Product Product;
    private Color Color;
    private Size Size;
    private Float Count;
    private Boolean IsCanceled;
    private Float CancelCount;
    private String CancelDescription;

    public Boolean getCanceled() {
        return IsCanceled;
    }

    public void setCanceled(Boolean canceled) {
        IsCanceled = canceled;
    }

    public Float getCancelCount() {
        return CancelCount;
    }

    public void setCancelCount(Float cancelCount) {
        CancelCount = cancelCount;
    }

    public String getCancelDescription() {
        return CancelDescription;
    }

    public void setCancelDescription(String cancelDescription) {
        CancelDescription = cancelDescription;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color color) {
        Color = color;
    }

    public Size getSize() {
        return Size;
    }

    public void setSize(Size size) {
        Size = size;
    }

    public Float getCount() {
        return Count;
    }

    public void setCount(Float count) {
        Count = count;
    }
}
