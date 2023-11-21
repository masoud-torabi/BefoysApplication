package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.WarehouseHandling;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WarehouseHandlingModule extends BaseModule<WarehouseHandling> {
    @Provides
    @Singleton
    WarehouseHandlingModule getWarehouseHandlingModuleContext() { return new WarehouseHandlingModule();  }

    @Inject
    public void getAllActive(ApiResultListener result) {
        createApiRequest(Enum_RequestType.GET, Enum_Api.WAREHOUSE_HANDLING, result, null);
    }
}
