package com.apen.demo.activity;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;
import com.apen.demo.custom.IButton;
import com.apen.demo.custom.impl.GestureDetectorImpl;

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
    IButton btnCustom;


    @Override
    protected void init() {
        super.init();

//        View view = LayoutInflater.from(this).inflate(R.layout.custom_button, null);
//
//        //        linearLayout.addView(view);
        btnCustom.setOnTouchListener(new View.OnTouchListener() {
                                         @Override
                                         public boolean onTouch(View v, MotionEvent event) {

                                             int action = event.getAction();

                                             GestureDetector mGestureDetector = new GestureDetector(CustomViewActivity.this, new GestureDetectorImpl());

                                             switch (action) {
                                                 case MotionEvent.ACTION_DOWN:
                                                     Log.e(TAG, "button onTouch ACTION_DOWN");
                                                     break;
                                                 case MotionEvent.ACTION_MOVE:
                                                     Log.e(TAG, "button onTouch ACTION_MOVE");
                                                     break;
                                                 case MotionEvent.ACTION_UP:
                                                     Log.e(TAG, "button onTouch ACTION_UP");
                                                     break;
                                                 default:
                                                     break;
                                             }

                                             return mGestureDetector.onTouchEvent(event);
                                         }
                                     }
        );
//
//        linearLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "linearLayout onTouch: ");
//                return false;
//            }
//        });
    }
}
