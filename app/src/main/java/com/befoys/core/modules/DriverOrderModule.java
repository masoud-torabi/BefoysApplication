package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.DriverOrder;
import com.befoys.core.models.DriverOrderItem;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DriverOrderModule extends BaseModule<DriverOrder> {
    @Provides
    @Singleton
    DriverOrderModule getDriverOrderContext() {
        return new DriverOrderModule();
    }

    @Inject
    public void get(Integer Id, ApiResultListener result) {
        List<ApiParameter> params = new ArrayList<>();
        params.add(new ApiParameter("orderId", Id));
        createApiRequest(Enum_RequestType.GET, Enum_Api.DRIVER_ORDER, result, params);
    }

    @Inject
    public void cancel(DriverOrderItem cancelItem, ApiResultListener result) {
        createApiRequest(Enum_RequestType.POST, Enum_Api.DRIVER_ORDER, result, null, cancelItem);
    }
}
