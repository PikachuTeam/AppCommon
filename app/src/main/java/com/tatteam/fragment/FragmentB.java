package com.tatteam.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tatteam.R;

import tatteam.com.app_common.ui.fragment.BaseFragment;

/**
 * Created by ThanhNH on 2/12/2016.
 */
public class FragmentB extends BaseFragment {
    public static final String TAG = "FragmentB";

    private Bitmap bitmapAvatar;
    private ImageView imgAvatar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (containHolder(FragmentA.KEY_HOLDER_AVATAR)) {
            bitmapAvatar = (Bitmap) getHolder(FragmentA.KEY_HOLDER_AVATAR);
        }
    }

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_b;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        imgAvatar = (ImageView) rootView.findViewById(R.id.img_avarta);
        imgAvatar.setImageBitmap(bitmapAvatar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //remove holder
        removeHolder(FragmentA.KEY_HOLDER_AVATAR);
    }
}
