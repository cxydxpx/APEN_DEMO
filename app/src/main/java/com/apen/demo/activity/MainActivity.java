package com.apen.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author apen
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_constraintLayout, R.id.btn_mpandroidchart, R.id.btn_map,R.id.btn_Proguard,
    R.id.btn_glide})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_constraintLayout:
                startActivity(new Intent(MainActivity.this, ConstraintActivity.class));
                break;
            case R.id.btn_mpandroidchart:
                startActivity(new Intent(MainActivity.this, MPAndroidChartActivity.class));
                break;
            case R.id.btn_map:
                startActivity(new Intent(MainActivity.this, MapActivity.class));
                break;
            case R.id.btn_Proguard:
                startActivity(new Intent(this,ProguardActivity.class));
                break;
            case R.id.btn_glide:
                startActivity(new Intent(this,GlideActivity.class));
                break;
            default:
                break;
        }
    }

}
