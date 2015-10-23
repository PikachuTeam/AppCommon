package com.tatteam;

import android.app.Application;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by ThanhNH-Mac on 10/23/15.
 */
public class ClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        LocalSharedPreferManager.getInstance().init(getApplicationContext());
//        DataSource.getInstance().init(getApplicationContext());
//        AppCommon.getInstance().initIfNeeded(getApplicationContext());
//        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), Locale.ENGLISH);
    }

    @Override
    public void onTerminate() {
//        DataSource.getInstance().destroy();
        AppCommon.getInstance().destroy();
        AppSpeaker.getInstance().destroy();
        super.onTerminate();
    }
}
