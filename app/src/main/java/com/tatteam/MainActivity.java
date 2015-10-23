package com.tatteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import tatteam.com.app_common.AppSetting;
import tatteam.com.app_common.CloseAppHandler;

public class MainActivity extends AppCompatActivity implements CloseAppHandler.OnCloseAppListener {
    private CloseAppHandler closeAppHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppSetting.getInstance().initIfNeeded(getApplicationContext());
        AppSetting.getInstance().increaseLaunchTime();
        AppSetting.getInstance().openMoreAppDialog(this);

        closeAppHandler = new CloseAppHandler(this);
        closeAppHandler.setListener(this);
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
        Toast.makeText(this, "again!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReallyWantToCloseApp() {
        finish();
    }
}
