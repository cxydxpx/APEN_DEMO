package com.apen.demo.activity;

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

    @OnClick({R.id.btn_constraintLayout, R.id.btn_mpandroidchart, R.id.btn_map,R.id.btn_proguard,
    R.id.btn_glide,R.id.btn_zxing,R.id.btn_custom})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_constraintLayout:
                startActivity(new Intent(this, ConstraintActivity.class));
                break;
            case R.id.btn_mpandroidchart:
                startActivity(new Intent(this, MpAndroidChartActivity.class));
                break;
            case R.id.btn_map:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.btn_proguard:
                startActivity(new Intent(this,ProguardActivity.class));
                break;
            case R.id.btn_glide:
                startActivity(new Intent(this,GlideActivity.class));
                break;
            case R.id.btn_zxing:
                startActivity(new Intent(this,ZxingActivity.class));
                break;
            case R.id.btn_custom:
                startActivity(new Intent(this,CustomViewActivity.class));
                break;
            default:
                break;
        }
    }

}
