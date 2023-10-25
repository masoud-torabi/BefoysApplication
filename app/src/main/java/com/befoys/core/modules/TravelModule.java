package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Travel;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TravelModule extends BaseModule<Travel> {
    @Provides
    @Singleton
    TravelModule getTravelModuleContext() { return new TravelModule();  }

    @Inject
    public void getById(Integer Id, ApiResultListener result) {
        ArrayList<ApiParameter> params = new ArrayList();
        params.add(new ApiParameter("id", Id));
        createApiRequest(Enum_RequestType.GET, Enum_Api.TRAVEL, result, params);
    }

    @Inject
    public void search(List<ApiParameter> params, ApiResultListener result) {
        createApiRequest(Enum_RequestType.GET, Enum_Api.TRAVEL, result, params);
    }
}
