package com.befoys.core.modules;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Device;
import com.befoys.core.models.DriverFavorite;
import com.befoys.core.webservice.base.ApiResultListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DriverFavoriteModule extends BaseModule<Device> {
    @Provides
    @Singleton
    DriverFavoriteModule getDriverFavoriteContext() {
        return new DriverFavoriteModule();
    }

    @Inject
    public void toggleDriverFavorite(DriverFavorite entity, ApiResultListener result) {
        createApiRequest(Enum_RequestType.POST, Enum_Api.DRIVER_FAVORITE, result, null, entity);
    }

    @Inject
    public void getDriverFavorites(ApiResultListener result) {
        createApiRequest(Enum_RequestType.GET, Enum_Api.DRIVER_FAVORITE, result);
    }
}
