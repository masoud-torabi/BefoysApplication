package com.befoys.core.models;

public class Code {
    private int id;
    private String name;
    private String label;
    private CodeGroup codeGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CodeGroup getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(CodeGroup codeGroup) {
        this.codeGroup = codeGroup;
    }

    public Code() {}

}