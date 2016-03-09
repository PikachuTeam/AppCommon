package com.tatteam;

import android.content.Intent;

import java.util.Locale;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.sqlite.DatabaseLoader;
import tatteam.com.app_common.ui.activity.EssentialSplashActivity;
import tatteam.com.app_common.util.AppConstant;
import tatteam.com.app_common.util.AppSpeaker;
import tatteam.com.app_common.ui.activity.BaseSplashActivity;

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
        AppCommon.getInstance().syncAdsIfNeeded(AppConstant.AdsType.SMALL_BANNER_TEST, AppConstant.AdsType.BIG_BANNER_TEST);

        //init text to speech
        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), Locale.ENGLISH);

        //import database
        DatabaseLoader.getInstance().createIfNeeded(getApplicationContext(), "test_common.db");
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
