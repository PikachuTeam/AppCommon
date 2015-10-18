package com.tatteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tatteam.com.app_common.AppSetting;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppSetting.getInstance().initIfNeeded(getApplicationContext());
        AppSetting.getInstance().openMoreAppDialog(this);
    }
}
