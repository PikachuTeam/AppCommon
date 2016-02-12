package com.tatteam;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.tatteam.fragment.FragmentA;

import tatteam.com.app_common.ads.AdsSmallBannerHandler;
import tatteam.com.app_common.ui.activity.BaseActivity;
import tatteam.com.app_common.ui.fragment.BaseFragment;
import tatteam.com.app_common.util.AppConstant;

/**
 * Created by ThanhNH on 2/12/2016.
 */
public class DemoBaseActivity extends BaseActivity {

    private Button btnBack;
    private FrameLayout adsContainer;
    private AdsSmallBannerHandler adsSmallBannerHandler;

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.activity_base;
    }

    @Override
    protected void onCreateContentView() {
        adsContainer = (FrameLayout) findViewById(R.id.ads_container);
        adsSmallBannerHandler = new AdsSmallBannerHandler(this, adsContainer, AppConstant.AdsType.SMALL_BANNER_TEST);
        adsSmallBannerHandler.setup();

        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment();
            }
        });
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected BaseFragment getFragmentContent() {
        return new FragmentA();
    }
}
