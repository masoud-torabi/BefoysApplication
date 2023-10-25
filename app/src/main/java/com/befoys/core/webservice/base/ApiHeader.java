package com.befoys.core.webservice.base;

public class ApiHeader {
    private String Name;
    private String Value;

    public ApiHeader() {}

    public ApiHeader(String Name, String Value) {
        setName(Name);
        setValue(Value);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
