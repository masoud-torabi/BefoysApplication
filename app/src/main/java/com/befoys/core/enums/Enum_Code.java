package com.befoys.core.enums;

public enum Enum_Code {
    TRAVEL_TYPE_SHOPRESELLER("TRAVEL_TYPE_SHOPRESELLER"),
    TRAVEL_TYPE_BUYER("TRAVEL_TYPE_BUYER");

    private final String codeName;

    private Enum_Code(String s) {
        codeName = s;
    }

    public boolean equalsName(String otherName) {
        return codeName.equals(otherName);
    }

    public String toString() {
        return this.codeName;
    }
}
