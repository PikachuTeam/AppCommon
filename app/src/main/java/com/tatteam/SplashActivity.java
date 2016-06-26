package com.tatteam;

import android.content.Intent;

import com.tatteam.database.PoolDatabaseLoader;

import java.util.Locale;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.sqlite.DatabaseLoader;
import tatteam.com.app_common.ui.activity.EssentialSplashActivity;
import tatteam.com.app_common.util.AppConstant;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by ThanhNH-Mac on 10/23/15.
 */
public class SplashActivity extends EssentialSplashActivity {

    @Override
    protected void onCreateContentView() {
        super.onCreateContentView(); // <- keep it, very important

        //find views, handle actions
    }

    @Override
    protected void onInitAppCommon() {
        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        AppCommon.getInstance().increaseLaunchTime();
        AppCommon.getInstance().syncAdsIfNeeded(AppConstant.AdsType.SMALL_BANNER_TEST,
                AppConstant.AdsType.BIG_BANNER_TEST,
                AppConstant.AdsType.NATIVE_EXPRESS_TEST);

        //init text to speech
        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), Locale.ENGLISH);

        //import database v1
        //DatabaseLoader.getInstance().createIfNeeded(getApplicationContext(), "italy_driving.db");

        //import database v2
        PoolDatabaseLoader.getInstance().initIfNeeded(getApplicationContext());
    }

    @Override
    protected void onFinishInitAppCommon() {
        openMainActivity();
    }


    private void openMainActivity(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
