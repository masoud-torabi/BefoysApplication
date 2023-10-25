package com.befoys.core.models;

public class CodeGroup {
    private int Id;
    private String Name;
    private String Label;

    public CodeGroup() {}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
