package com.befoys.core.models;

public class Code {
    private int Id;
    private String Name;
    private String Label;
    private CodeGroup CodeGroup;

    public Code() {}

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

    public CodeGroup getCodeGroup() {
        return CodeGroup;
    }

    public void setCodeGroup(CodeGroup codeGroup) {
        CodeGroup = codeGroup;
    }
}