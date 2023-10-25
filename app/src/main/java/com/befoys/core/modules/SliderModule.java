package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Slider;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SliderModule extends BaseModule<Slider> {

    @Provides
    @Singleton
    SliderModule getSliderModuleContext() { return new SliderModule();  }

    @Inject
    public void search(ApiResultListener result) {
        createApiRequest(Enum_RequestType.GET, Enum_Api.SLIDER, result);
    }

    @Inject
    public void search(List<ApiParameter> params, ApiResultListener result) {
        createApiRequest(Enum_RequestType.GET, Enum_Api.SLIDER, result, params);
    }

}
