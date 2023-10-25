package com.befoys.core.models;

public class Device {
    private int Id;
    private String Name;
    private String PackageName;
    private String MobileNumber;
    private String BrandName;
    private float VersionCode;
    private String VersionName;
    private String BuildNumber;
    private String ModelNumber;
    private String SerialNumber;
    private String IMEI;
    private String Token;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public float getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(float versionCode) {
        VersionCode = versionCode;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getBuildNumber() {
        return BuildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        BuildNumber = buildNumber;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    public void setModelNumber(String modelNumber) {
        ModelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

}
