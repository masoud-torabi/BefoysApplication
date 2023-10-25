package com.befoys.core.webservice.base;

public class ApiParameter {
    private String Name;
    private Object Value;

    public ApiParameter() {}

    public ApiParameter(String Name, Object Value) {
        setName(Name);
        setValue(Value);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }
}
