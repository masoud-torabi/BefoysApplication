package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.TravelStep;
import com.befoys.core.webservice.base.ApiResultListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TravelStepModule extends BaseModule<TravelStep> {
    @Provides
    @Singleton
    TravelStepModule getTravelStepModuleContext() { return new TravelStepModule();  }

    @Inject
    public void changeStatus(TravelStep entity, ApiResultListener result) {
        createApiRequest(Enum_RequestType.PUT, Enum_Api.TRAVEL_STEP, result, null, entity);
    }
}
