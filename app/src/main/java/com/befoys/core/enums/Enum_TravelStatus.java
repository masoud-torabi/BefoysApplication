package com.befoys.core.enums;

public enum Enum_TravelStatus {
    TRAVEL_STATUS_INSERTED("TRAVEL_STATUS_INSERTED"),
    TRAVEL_STATUS_STARTED("TRAVEL_STATUS_STARTED"),
    TRAVEL_STATUS_DONE("TRAVEL_STATUS_DONE"),
    TRAVEL_STATUS_CANCELED("TRAVEL_STATUS_CANCELED");

    private final String apiName;

    private Enum_TravelStatus(String s) {
        apiName = s;
    }

    public boolean equalsName(String otherName) {
        return apiName.equals(otherName);
    }

    public String toString() {
        return this.apiName;
    }
}
