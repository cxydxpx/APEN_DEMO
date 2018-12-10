package com.apen.simple.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-01-23.
 * GitHub：https://github.com/cxydxpx
 */

public class GitActivity extends BaseActivity {
    @Override
    protected int layoutResId() {
        return R.layout.activity_git;
//        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git);
    }
}
