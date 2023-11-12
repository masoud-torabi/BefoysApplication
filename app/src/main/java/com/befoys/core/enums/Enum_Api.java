package com.befoys.core.enums;

public enum Enum_Api {
    DRIVER_LOGIN("driverapp/driverLogin"),
    DRIVER_ORDER("driverapp/driverOrder"),
    SLIDER("slider"),
    DEVICE("device"),
    TRAVEL("driverapp/travel"),
    TRAVEL_STEP("driverapp/travelstep"),
    DRIVER_FAVORITE("driverapp/driverfavorite"),
    WAREHOUSE_DOC("inventoryapp/warehousedoc"),
    SITEUSER("inventoryapp/siteuser");

    private final String apiName;

    private Enum_Api(String s) {
        apiName = s;
    }

    public boolean equalsName(String otherName) {
        return apiName.equals(otherName);
    }

    public String toString() {
        return this.apiName;
    }
}