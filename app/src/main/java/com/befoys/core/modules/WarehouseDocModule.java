package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Travel;
import com.befoys.core.models.WarehouseDoc;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WarehouseDocModule extends BaseModule<WarehouseDoc> {
    @Provides
    @Singleton
    WarehouseDocModule getWarehouseDocModuleContext() { return new WarehouseDocModule();  }

    @Inject
    public void getById(String Id, ApiResultListener result) {
        ArrayList<ApiParameter> params = new ArrayList();
        params.add(new ApiParameter("id", Id));
        createApiRequest(Enum_RequestType.GET, Enum_Api.WAREHOUSE_DOC, result, params);
    }
}
