package com.tatteam;

import android.app.Application;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.sqlite.DatabaseLoader;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by ThanhNH-Mac on 10/23/15.
 */
public class ClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        AppCommon.getInstance().destroy();
        AppSpeaker.getInstance().destroy();
        DatabaseLoader.getInstance().destroy();

        super.onTerminate();
    }
}
