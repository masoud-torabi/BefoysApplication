package com.befoys.core.modules;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.befoys.core.database.DatabaseContext;
import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.helpers.MyApplication;
import com.befoys.core.models.Driver;
import com.befoys.core.models.SiteUser;
import com.befoys.core.webservice.base.ApiResultListener;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class SiteUserModule extends BaseModule<SiteUser> {
    @Provides
    @Singleton
    SiteUserModule getSiteUserContext() {
        return new SiteUserModule();
    }

    public static SiteUser getCurrent()
    {
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            List<SiteUser> list = dbContext.getAll(SiteUser.class);
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
    public SiteUser getCurrentSiteUser() {
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            List<SiteUser> list = dbContext.getAll(SiteUser.class);
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
    public void setCurrentSiteUser(SiteUser entity)
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
    public void removeCurrentSiteUser()
    {
        Context context = MyApplication.getContext();
        DatabaseContext dbContext = new DatabaseContext(context);
        try {
            List<SiteUser> list = dbContext.getAll(SiteUser.class);
            dbContext.deleteAll(SiteUser.class, list);
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Inject
    public void login(String mobile, String password, ApiResultListener result) {
        SiteUser SiteUser = new SiteUser();
        SiteUser.setMobile(mobile);
        SiteUser.setPassword(password);
        createApiRequest(Enum_RequestType.POST, Enum_Api.SITEUSER, result, null, SiteUser);
    }

    @Inject
    public void logout(ApiResultListener result)
    {
        createApiRequest(Enum_RequestType.DELETE, Enum_Api.SITEUSER, result);
    }
}
