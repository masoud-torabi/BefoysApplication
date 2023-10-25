package com.befoys.core.webservice.base;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;

import java.util.List;

public interface IApiCaller {
    public void createApiRequest(Enum_RequestType method,
                                 Enum_Api apiName,
                                 List<ApiHeader> headers,
                                 List<ApiParameter> params,
                                 Object obj,
                                 ApiResultListener callback);
}
