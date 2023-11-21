package com.befoys.core.dagger;

import com.befoys.core.models.SiteUser;
import com.befoys.core.modules.DeviceModule;
import com.befoys.core.modules.DriverFavoriteModule;
import com.befoys.core.modules.DriverModule;
import com.befoys.core.modules.DriverOrderModule;
import com.befoys.core.modules.SiteUserModule;
import com.befoys.core.modules.SliderModule;
import com.befoys.core.modules.TravelModule;
import com.befoys.core.modules.TravelStepModule;
import com.befoys.core.modules.WarehouseDocModule;
import com.befoys.core.modules.WarehouseHandlingModule;
import com.befoys.core.modules.WarehouseHandlingNoItemModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        SliderModule.class,
        DeviceModule.class,
        DriverModule.class,
        TravelModule.class,
        TravelStepModule.class,
        DriverFavoriteModule.class,
        DriverOrderModule.class,
        WarehouseDocModule.class,
        SiteUserModule.class,
        WarehouseHandlingModule.class,
        WarehouseHandlingNoItemModule.class
})

public interface EntityComponent {
    SliderModule getSliderModule();
    DeviceModule getDeviceModule();
    DriverModule getDriverModule();
    TravelModule getTravelModule();
    TravelStepModule getTravelStepModule();
    DriverFavoriteModule getDriverFavoriteModule();
    DriverOrderModule getDriverOrderModule();
    WarehouseDocModule getWarehouseDocModule();
    SiteUserModule getSiteUserModule();
    WarehouseHandlingModule getWarehouseHandlingModule();
    WarehouseHandlingNoItemModule getWarehouseHandlingNoItemModule();
}