package com.befoys.core.webservice.base;

import com.befoys.core.enums.Enum_Api;

import java.util.List;

public class ApiCallerBase {
    public static String getRequestString(Enum_Api api, List<ApiParameter> params)
    {
        String apiName = ApiBase.WEBSERVICE_URL + api.toString();
        if (params != null) {
            apiName = apiName + "?";
            for (ApiParameter item : params){
                apiName = apiName + item.getName().toLowerCase() + "=" + item.getValue() + "&";
            }
        }
        return  apiName;
    }
}
