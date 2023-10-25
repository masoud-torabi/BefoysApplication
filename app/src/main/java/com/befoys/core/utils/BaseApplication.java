package com.befoys.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

public class BaseApplication {

    public static void startApplication(Context context) {
        //EntityComponent _context = DaggerEntityComponent.builder().build();
        //core.database.realm.DatabaseOrm.initRealmDatabase(context);
        //_context.getDeviceModule().sendDeviceInfoToServer(context);
    }

    public static void startApplicationWithoutFirebase(Context context) {
        //core.database.realm.DatabaseOrm.initRealmDatabase(context);
    }
}
