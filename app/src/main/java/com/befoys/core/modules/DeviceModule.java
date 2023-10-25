package com.befoys.core.modules;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.Device;
import com.befoys.core.models.Driver;
import com.befoys.core.utils.UniqueId;
import com.befoys.core.webservice.base.ApiResultListener;
//import com.getkeepsafe.relinker.BuildConfig;

/*
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
*/
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceModule extends BaseModule<Device> {
    @Provides
    @Singleton
    DeviceModule getDeviceContext() {
        return new DeviceModule();
    }

    /*
    @Inject
    public void sendDeviceInfoToServer(Context context) {
        sendDeviceInfoToServer(context, null);
    }

    @Inject
    public void sendDeviceInfoToServer(Context context, ApiResultListener result) {
        Device device = new Device();
        device.setName(Build.DEVICE);
        device.setSerialNumber(UniqueId.GetUniqueID(context));
        device.setPackageName(BuildConfig.APPLICATION_ID);
        device.setBrandName(Build.BRAND);
        device.setVersionCode(BuildConfig.VERSION_CODE);
        device.setVersionName(BuildConfig.VERSION_NAME);
        device.setModelNumber(Build.MODEL);
        device.setBuildNumber(Build.HARDWARE);
        device.setIMEI("-");
        device.setToken("-");
        device.setMobileNumber("-");
        Driver currentUser = DriverModule.getCurrent();
        if (currentUser == null)
        {
            Driver currentAccount = DriverModule.getCurrent();
            if (currentAccount != null)
                device.setMobileNumber(currentAccount.getMobile());
        }
        else
            device.setMobileNumber(currentUser.getMobile());

        /*
        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (!task.isSuccessful()) {
                        Log.e("FIREBASE => ", "getInstanceId failed", task.getException());
                        return;
                    }
                    device.setToken(task.getResult().getToken());
                    createApiRequest(Enum_RequestType.POST, Enum_Api.DEVICE, result, null, device);
                }
            });
    }
         */
}
