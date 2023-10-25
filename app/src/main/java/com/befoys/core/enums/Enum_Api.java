package com.befoys.core.enums;

public enum Enum_Api {
    DRIVER_LOGIN("driverapp/driverLogin"),
    DRIVER_ORDER("driverOrder"),
    SLIDER("slider"),
    DEVICE("device"),
    TRAVEL("travel"),
    TRAVEL_STEP("travelstep"),
    DRIVER_FAVORITE("driverfavorite");

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