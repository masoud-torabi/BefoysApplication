package com.befoys.core.enums;

public enum  Enum_ApiHeader {
    WEB_TOKEN("WEB_TOKEN"),
    UNIQUE_ID("UNIQUE_ID"),
    UNIQUE_KEY("UNIQUE_KEY");

    private final String apiName;

    private Enum_ApiHeader(String s) {
        apiName = s;
    }

    public boolean equalsName(String otherName) {
        return apiName.equals(otherName);
    }

    public String toString() {
        return this.apiName;
    }
}
