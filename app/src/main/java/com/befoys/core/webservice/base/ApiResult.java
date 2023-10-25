package com.befoys.core.webservice.base;

import android.util.Log;

import com.google.gson.Gson;

public class ApiResult {
    private byte code;
    private String message;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer resultSize;
    private Integer totalCount;
    private Object value;

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getResultSize() {
        return resultSize;
    }

    public void setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static ApiResult jsonToObject(String json) {
        Gson gson = new Gson();
        Log.e("ERROR CONVERT", json);
        ApiResult result = gson.fromJson(json, ApiResult.class);
        return result;
    }
}
