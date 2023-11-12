package com.befoys.core.modules;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.befoys.core.database.DatabaseContext;
import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.helpers.MyApplication;
import com.befoys.core.models.Driver;
import com.befoys.core.webservice.base.ApiResultListener;

import java.sql.SQLException;
import java.util.List;

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
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            List<Driver> list = dbContext.getAll(Driver.class);
            if (list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Inject
    public Driver getCurrentDriver() {
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            List<Driver> list = dbContext.getAll(Driver.class);
            if (list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Inject
    public void setCurrentDriver(Driver entity)
    {
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            dbContext.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("DATABASE", e.getMessage());
        }
    }
    @Inject
    public void removeCurrentDriver()
    {
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            List<Driver> list = dbContext.getAll(Driver.class);
            dbContext.deleteAll(Driver.class, list);
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
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
