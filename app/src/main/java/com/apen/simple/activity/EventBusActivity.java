package com.apen.simple.activity;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-05.
 * GitHub：https://github.com/cxydxpx
 */

public class EventBusActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void init() {
        super.init();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
}
