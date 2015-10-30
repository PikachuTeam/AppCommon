package com.tatteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Locale;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.ads.AdsSmallBannerHandler;
import tatteam.com.app_common.util.AppSpeaker;
import tatteam.com.app_common.util.CloseAppHandler;


public class MainActivity extends AppCompatActivity implements CloseAppHandler.OnCloseAppListener, View.OnClickListener {
    private CloseAppHandler closeAppHandler;
    private Button btnMoreApps;
    private Button bntTextToSpeech;
    private FrameLayout adsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        AppSpeaker.getInstance().initIfNeeded(getApplicationContext(), Locale.ENGLISH);

        closeAppHandler = new CloseAppHandler(this);
        closeAppHandler.setListener(this);

        adsContainer = (FrameLayout) findViewById(R.id.ads_container);

        AdsSmallBannerHandler adsHandler = new AdsSmallBannerHandler(this, adsContainer);
        adsHandler.setup();

    }

    private void findViews() {
        btnMoreApps = (Button) findViewById(R.id.btn_more_apps);
        bntTextToSpeech = (Button) findViewById(R.id.btn_text_to_speech);

        btnMoreApps.setOnClickListener(this);
        bntTextToSpeech.setOnClickListener(this);
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
        closeAppHandler.handlerKeyBack(this);
    }

    @Override
    public void onRateAppDialogClose() {
        finish();
    }

    @Override
    public void onTryToCloseApp() {
        Toast.makeText(this, getString(R.string.message_exit), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReallyWantToCloseApp() {
        finish();
    }


}
