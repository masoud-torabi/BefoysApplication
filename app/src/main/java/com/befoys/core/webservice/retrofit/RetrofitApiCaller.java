package com.befoys.core.webservice.retrofit;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Driver;
import com.befoys.core.modules.DriverModule;
import com.befoys.core.webservice.base.ApiBase;
import com.befoys.core.webservice.base.ApiCallerBase;
import com.befoys.core.webservice.base.ApiHeader;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;
import com.befoys.core.webservice.base.IApiCaller;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitApiCaller extends ApiCallerBase implements IApiCaller {

    ApiInterface apiInterface;
    public RetrofitApiCaller(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void createApiRequest(
                                 Enum_RequestType method,
                                 Enum_Api apiName,
                                 List<ApiHeader> headers,
                                 List<ApiParameter> params,
                                 Object obj,
                                 final ApiResultListener callback) {

        HashMap<String, String> listHeaders = new HashMap();
        listHeaders.put("WEB_TOKEN", ApiBase.WEBSERVICE_TOKEN);

        Driver currentDriver = DriverModule.getCurrent();
        if (currentDriver != null)
        {
            listHeaders.put("UNIQUE_ID", currentDriver.getUniqueId());
            listHeaders.put("UNIQUE_KEY", currentDriver.getUniqueValue());
        }

        HashMap<String, String> listQueryParameters = new HashMap();
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                ApiParameter parameter = params.get(i);
                if (parameter.getValue() != null)
                    listQueryParameters.put(parameter.getName(), parameter.getValue().toString());
            }
        }

        String url = ApiBase.WEBSERVICE_URL + apiName;

        Call<ApiResult> getData = null;
        if (method == Enum_RequestType.GET) {
            getData = apiInterface.get(url, listQueryParameters, listHeaders);
        } else if (method == Enum_RequestType.POST) {
            if (obj == null)
                getData = apiInterface.post(url, listQueryParameters, listHeaders);
            else
                getData = apiInterface.post(url, listQueryParameters, listHeaders, obj);
        } else if (method == Enum_RequestType.PUT) {
            if (obj == null)
                getData = apiInterface.put(url, listQueryParameters, listHeaders);
            else
                getData = apiInterface.put(url, listQueryParameters, listHeaders, obj);
        } else if (method == Enum_RequestType.DELETE) {
            if (obj == null)
                getData = apiInterface.delete(url, listQueryParameters, listHeaders);
            else
                getData = apiInterface.delete(url, listQueryParameters, listHeaders, obj);
        }

        if (getData != null) {
            getData.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                    ApiResult result = response.body();
                    if (callback != null) {
                        if (result == null)
                            callback.onFailure(Enum_ApiResultStatus.Exception, "API NULL RESULT");
                        else if (result.getCode() == Enum_ApiResultStatus.Success.toValue()) {
                            callback.onSuccess(result);
                        } else if (result.getCode() == Enum_ApiResultStatus.InvalidKey.toValue()) {
                            callback.onFailure(Enum_ApiResultStatus.InvalidKey, result.getMessage());
                        } else if (result.getCode() == Enum_ApiResultStatus.Exception.toValue()) {
                            callback.onFailure(Enum_ApiResultStatus.Exception, result.getMessage());
                        } else if (result.getCode() == Enum_ApiResultStatus.Error.toValue()) {
                            callback.onFailure(Enum_ApiResultStatus.Error, result.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    if (callback != null) {
                        callback.onFailure(Enum_ApiResultStatus.Exception, t.getMessage());
                    }
                }
            });
        } else {
            callback.onFailure(Enum_ApiResultStatus.Exception, null);
        }
    }
}
