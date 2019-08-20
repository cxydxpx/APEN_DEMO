package com.apen.simple.activity.customview;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-19.
 * GitHub：https://github.com/cxydxpx
 */

public class SimpleViewActivity extends BaseActivity {
    @Override
    protected int layoutResId() {
        return R.layout.activity_simple_view;
    }

    public void simpleScroll(View view) {
        startActivity(new Intent(this, ScrollToActivity.class));
    }

    public void simpleTouch(View view) {
        startActivity(new Intent(this, TouchViewActivity.class));
    }

}
