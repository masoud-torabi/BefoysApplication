package com.befoys.core.models;

public class WarehouseHandlingNoItem {
    private int id;
    private Product product;
    private Color color;
    private Size size;
    private int shelfId;
    private String shelfName;
    private double count;
    private double quantityCount;
    private int barcodeCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getShelfName() {
        return shelfName;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getQuantityCount() {
        return quantityCount;
    }

    public void setQuantityCount(double quantityCount) {
        this.quantityCount = quantityCount;
    }

    public int getBarcodeCount() {
        return barcodeCount;
    }

    public void setBarcodeCount(int barcodeCount) {
        this.barcodeCount = barcodeCount;
    }
}
