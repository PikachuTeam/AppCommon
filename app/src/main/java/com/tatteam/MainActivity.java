package com.tatteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdSize;
import com.tatteam.database.BaseIndoDataSource;
import com.tatteam.database.BaseItalyDataSource;

import java.util.Locale;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.ads.AdsBigBannerHandler;
import tatteam.com.app_common.ads.AdsSmallBannerHandler;
import tatteam.com.app_common.util.AppConstant;
import tatteam.com.app_common.util.AppSpeaker;
import tatteam.com.app_common.util.CloseAppHandler;
import tatteam.com.app_common.util.CommonUtil;


public class MainActivity extends AppCompatActivity implements CloseAppHandler.OnCloseAppListener, View.OnClickListener {
    private CloseAppHandler closeAppHandler;
    private Button btnMoreApps;
    private Button bntTextToSpeech;
    private Button bntAdsBigBanner;
    private Button btnShare;
    private Button btn_select_sqlite, btn_base_activity;
    private FrameLayout adsContainer, adsContainer2;
    private AdsSmallBannerHandler adsSmallBannerHandler, adsSmallBannerHandler2;
    private AdsBigBannerHandler adsBigBannerHandler;
    private Button buttonFlat;
    private Button buttonRaised;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), Locale.ENGLISH);

        closeAppHandler = new CloseAppHandler(this);
        closeAppHandler.setListener(this);

        setupAds();

    }

    private void findViews() {
        adsContainer = (FrameLayout) findViewById(R.id.ads_container);
        adsContainer2 = (FrameLayout) findViewById(R.id.ads_container_2);
        btnMoreApps = (Button) findViewById(R.id.btn_more_apps);
        bntTextToSpeech = (Button) findViewById(R.id.btn_text_to_speech);
        bntAdsBigBanner = (Button) findViewById(R.id.btn_ads_big_banner);
        btn_select_sqlite = (Button) findViewById(R.id.btn_select_sqlite);
        btn_base_activity = (Button) findViewById(R.id.btn_base_activity);
        buttonFlat = (Button) findViewById(R.id.buttonFlat);
        buttonRaised = (Button) findViewById(R.id.buttonRaised);
        btnShare = (Button) findViewById(R.id.btn_share);

        btnMoreApps.setOnClickListener(this);
        bntTextToSpeech.setOnClickListener(this);
        bntAdsBigBanner.setOnClickListener(this);
        btn_select_sqlite.setOnClickListener(this);
        btn_base_activity.setOnClickListener(this);
        buttonFlat.setOnClickListener(this);
        buttonRaised.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    private void setupAds() {
        adsSmallBannerHandler = new AdsSmallBannerHandler(this, adsContainer, AppConstant.AdsType.SMALL_BANNER_TEST);
        adsSmallBannerHandler.setup();

        adsSmallBannerHandler2 = new AdsSmallBannerHandler(this, adsContainer2, AppConstant.AdsType.SMALL_BANNER_TEST, AdSize.MEDIUM_RECTANGLE);
        adsSmallBannerHandler2.setup();

        adsBigBannerHandler = new AdsBigBannerHandler(this, AppConstant.AdsType.BIG_BANNER_TEST);
        adsBigBannerHandler.setup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adsSmallBannerHandler.destroy();
        adsSmallBannerHandler2.destroy();
        adsBigBannerHandler.destroy();
    }

    @Override
    public void onClick(View view) {
        if (view == btnMoreApps) {
            AppCommon.getInstance().openMoreAppDialog(this);
        } else if (view == bntTextToSpeech) {
            String message = "Hello world";
            if (AppSpeaker.getInstance().ready()) {
                AppSpeaker.getInstance().speak(message);
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else if (view == bntAdsBigBanner) {
            adsBigBannerHandler.show();
        } else if (view == btn_select_sqlite) {

            //v1
//            int count = DataSource.countExams();
//            Toast.makeText(this, count + " exams", Toast.LENGTH_SHORT).show();

            //v2
            int indoCount = BaseIndoDataSource.countExams();
            int italyCount = BaseItalyDataSource.countExams();
            Toast.makeText(this, "Indo " + indoCount + " exams" + "\n" + "Italy " + italyCount + " exams", Toast.LENGTH_SHORT).show();

        } else if (view == btn_base_activity) {
            startActivity(new Intent(this, DemoBaseActivity.class));
        } else if (view == btnShare) {
            String androidLink = "https://play.google.com/store/apps/details?id=" + getApplication().getPackageName();
            String sharedText = "This is an awesome app.\nAndroid: " + androidLink;
            CommonUtil.sharePlainText(this, sharedText);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (AppSpeaker.getInstance().ready()) {
            AppSpeaker.getInstance().stop();
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        closeAppHandler.setKeyBackPress(this);
    }

    @Override
    public void onRateAppDialogClose() {
        finish();
    }

    @Override
    public void onTryToCloseApp() {
        Toast.makeText(this, closeAppHandler.getDefaultExitMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReallyWantToCloseApp() {
        finish();
    }


}
