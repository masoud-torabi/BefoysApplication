package com.befoys.core.enums;

public enum Enum_ApiResultStatus {
    Exception((byte)0),
    Success((byte)1),
    InvalidKey((byte)2),
    Error((byte)3);

    private final Byte resultCode;

    private Enum_ApiResultStatus(Byte s) {
        resultCode = s;
    }

    public boolean equalsName(Byte otherName) {
        return resultCode.equals(otherName);
    }

    public Byte toValue() {
        return this.resultCode;
    }
}