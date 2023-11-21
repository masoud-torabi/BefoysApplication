package com.befoys.core.viewmodel;

import com.befoys.core.models.WarehouseHandlingNoItem;

import java.util.List;

public class ViewWarehouseHandlingPositionItem {
    private Integer ShelfId;
    private String PositionName;
    private List<WarehouseHandlingNoItem> items;

    public Integer getShelfId() {
        return ShelfId;
    }

    public void setShelfId(Integer shelfId) {
        ShelfId = shelfId;
    }
    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public List<WarehouseHandlingNoItem> getItems() {
        return items;
    }

    public void setItems(List<WarehouseHandlingNoItem> items) {
        this.items = items;
    }
}
