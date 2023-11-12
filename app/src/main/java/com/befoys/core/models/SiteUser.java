package com.befoys.core.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class SiteUser {
    @DatabaseField(columnName = "id", id = true)
    private int id;

    @DatabaseField(columnName = "fullName")
    private String fullName;

    @DatabaseField(columnName = "mobile")
    private String mobile;

    @DatabaseField(columnName = "email")
    private String email;

    @DatabaseField(columnName = "uniqueId")
    private String uniqueId;

    @DatabaseField(columnName = "password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
