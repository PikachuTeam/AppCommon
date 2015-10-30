package com.tatteam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.util.AppConstant;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by ThanhNH-Mac on 10/23/15.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        switchToMainActivity();
        initAppCommon();

        //for text to speech
        initAppSpeaker();
    }

    private void initAppCommon() {
        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        AppCommon.getInstance().increaseLaunchTime();
        AppCommon.getInstance().syncAdsSmallBannerIfNeeded(AppConstant.AdsType.SMALL_BANNER_LANGUAGE_LEARNING);
    }

    private void initAppSpeaker() {
        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), Locale.ENGLISH);
    }

    private void switchToMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }


}
