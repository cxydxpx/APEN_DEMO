package com.apen.demo.activity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-13.
 * GitHub：https://github.com/cxydxpx
 */

public class CustomViewActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_custom_view;
    }

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    @BindView(R.id.btn_custom)
    ImageView btnCustom;

    @Override
    protected void init() {
        super.init();

//        View view = LayoutInflater.from(this).inflate(R.layout.custom_button, null);
//        linearLayout.addView(view);
        btnCustom.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Log.d(TAG, "onClick: ");
                                         }
                                     }
        );

        btnCustom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d(TAG, "onTouch: " + event.getAction());

                return false;
            }
        });

    }

}
