package com.befoys.core.models;

public class WarehouseDocItem {
    private Product product;
    private Color color;
    private Size size;
    private float count;

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

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }
}
