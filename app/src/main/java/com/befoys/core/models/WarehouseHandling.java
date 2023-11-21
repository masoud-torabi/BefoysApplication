package com.befoys.core.models;

public class WarehouseHandling {
    private int id;
    private String name;
    private String status;
    private int countingNo;
    private int noId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCountingNo() {
        return countingNo;
    }

    public void setCountingNo(int countingNo) {
        this.countingNo = countingNo;
    }

    public int getNoId() {
        return noId;
    }

    public void setNoId(int noId) {
        this.noId = noId;
    }
}
