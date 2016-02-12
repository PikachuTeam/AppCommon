package com.tatteam.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.tatteam.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import tatteam.com.app_common.ui.fragment.BaseFragment;

/**
 * Created by ThanhNH on 2/12/2016.
 */
public class FragmentA extends BaseFragment {

    public static final String KEY_HOLDER_AVATAR = "avatar";

    private android.widget.Button btnOpenFragB;
    private ImageView imgAvatar;
    private Bitmap bitmapAvatar;

    @Override
    protected int getLayoutResIdContentView() {
        return R.layout.fragment_a;
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        imgAvatar = (ImageView) rootView.findViewById(R.id.img_avarta);
        //download image
        Future<Bitmap> future = Ion.with(this).load("http://phunutoday.vn/upload_images/images/2015/08/26/ngoc-trinh-3.jpg").asBitmap();
        try {
            bitmapAvatar = future.get();
            imgAvatar.setImageBitmap(bitmapAvatar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        btnOpenFragB = (Button) rootView.findViewById(R.id.btn_open_frag_b);
        btnOpenFragB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentB = new FragmentB();
                replaceFragment(fragmentB, FragmentB.TAG);

                //store bitmap in memory
                putHolder(KEY_HOLDER_AVATAR, bitmapAvatar);
            }
        });
    }
}
