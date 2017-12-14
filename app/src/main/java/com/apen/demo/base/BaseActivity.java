package com.apen.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import butterknife.ButterKnife;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-23.
 * GitHub：https://github.com/cxydxpx
 * @author Administrator
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected static final String TAG = "TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());
        ButterKnife.bind(this);

        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    protected void init(){}

    /**
     * 内容view
     *
     * @return
     */
    protected abstract int layoutResId();

}
