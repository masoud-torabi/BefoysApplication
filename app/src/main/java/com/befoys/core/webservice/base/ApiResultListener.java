package com.befoys.core.webservice.base;

import com.befoys.core.enums.Enum_ApiResultStatus;

public interface ApiResultListener {
    public void onSuccess(ApiResult result);
    public void onFailure(Enum_ApiResultStatus status, String message);
}
