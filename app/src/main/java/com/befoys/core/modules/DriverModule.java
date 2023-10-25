package com.befoys.core.modules;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Driver;
import com.befoys.core.webservice.base.ApiResultListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DriverModule extends BaseModule<Driver> {
    @Provides
    @Singleton
    DriverModule getDriverContext() {
        return new DriverModule();
    }

    public static Driver getCurrent()
    {
        /*
        Realm realm = getRealmInstance();
        Driver Driver = realm.where(Driver.class).findFirst();
        return Driver;
         */
        return null;
    }

    @Inject
    public Driver getCurrentDriver()
    {
        /*
        Realm realm = getRealmInstance();
        Driver Driver = realm.where(Driver.class).findFirst();
        return Driver;

         */
        return null;
    }

    @Inject
    public void setCurrentDriver(Driver entity)
    {
        /*
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(entity);
        realm.commitTransaction();
        */
    }
    @Inject
    public void removeCurrentDriver()
    {
        /*
        Realm realm = getRealmInstance();
        final RealmResults<Driver> users = realm
                .where(Driver.class)
                .findAll();

        Driver userdatabase = users.where().findFirst();

        if(userdatabase!=null) {

            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            userdatabase.deleteFromRealm();
            realm.commitTransaction();
        }
         */
    }

    @Inject
    public void login(String mobile, ApiResultListener result) {
        Driver Driver = new Driver();
        Driver.setMobile(mobile);
        createApiRequest(Enum_RequestType.POST, Enum_Api.DRIVER_LOGIN, result, null, Driver);
    }

    @Inject
    public void logout(ApiResultListener result)
    {
        createApiRequest(Enum_RequestType.DELETE, Enum_Api.DRIVER_LOGIN, result);
    }

    @Inject
    public void loginComplete(Driver Driver, ApiResultListener result) {
        createApiRequest(Enum_RequestType.PUT, Enum_Api.DRIVER_LOGIN, result, null, Driver);
    }
}
