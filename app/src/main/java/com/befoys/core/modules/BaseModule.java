package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.enums.Enum_WebServiceType;
import com.befoys.core.webservice.base.ApiBase;
import com.befoys.core.webservice.base.ApiHeader;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.core.webservice.retrofit.RetrofitApiCaller;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BaseModule<T>  {

    private static Enum_WebServiceType serviceType = Enum_WebServiceType.RETROFIT;

    public static <T> T castObject(Object obj, Class<T> classOfT) {
        Gson gs = new Gson();
        return gs.fromJson(gs.toJson(obj), classOfT);
    }

    public static <T> ArrayList<T> castObjectList(List<Object> objList, Class<T> classOfT) {
        Gson gs = new Gson();
        ArrayList<T> list = new ArrayList<T>();
        if (objList != null) {
            for (int i = 0; i < objList.size(); i++)
            {
                list.add(gs.fromJson(gs.toJson(objList.get(i)), classOfT));
            }
        }
        return  list;
    }

    public static void createApiRequest(
            final Enum_Api apiName,
            final ApiResultListener callback) {
        createApiRequest(Enum_RequestType.GET, apiName, callback, null, null);
    }

    public static void createApiRequest(
            final Enum_RequestType method,
            final Enum_Api apiName,
            final ApiResultListener callback) {
        createApiRequest(method, apiName, callback, null, null);
    }

    public static void createApiRequest(
            final Enum_RequestType method,
            final Enum_Api apiName,
            final ApiResultListener callback,
            final List<ApiParameter> params) {

        createApiRequest(method, apiName, callback, params, null);
    }

    public static void createApiRequest(final Enum_RequestType method,
                                        final Enum_Api apiName,
                                        final ApiResultListener callback,
                                        final List<ApiParameter> params,
                                        final Object obj) {

        if (serviceType == Enum_WebServiceType.RETROFIT) {
            RetrofitApiCaller apiCaller = new RetrofitApiCaller();
            apiCaller.createApiRequest(method, apiName, getHeaders(), params, obj, callback);
        }
    }

    public static List<ApiHeader> getHeaders() {
        List<ApiHeader> listHeaders = new ArrayList<>();
        listHeaders.add(new ApiHeader("WEB_TOKEN", ApiBase.WEBSERVICE_TOKEN));
        return  listHeaders;
    }
}
