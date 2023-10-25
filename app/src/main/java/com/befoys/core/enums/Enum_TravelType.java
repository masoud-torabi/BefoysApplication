package com.befoys.core.enums;

public enum Enum_TravelType {
    TRAVEL_TYPE_BUYER("TRAVEL_TYPE_BUYER"),
    TRAVEL_TYPE_SHOPRESELLER("TRAVEL_TYPE_SHOPRESELLER"),
    TRAVEL_TYPE_BEFOYS("TRAVEL_TYPE_BEFOYS");

    private final String apiName;

    private Enum_TravelType(String s) {
        apiName = s;
    }

    public boolean equalsName(String otherName) {
        return apiName.equals(otherName);
    }

    public String toString() {
        return this.apiName;
    }
}
