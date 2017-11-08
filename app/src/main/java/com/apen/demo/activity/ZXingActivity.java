package com.apen.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.apen.demo.R;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-08.
 * GitHub：https://github.com/cxydxpx
 */

public class ZXingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
    }

    public void zxing(View v) {

        startActivity(new Intent(this, ScanActivity.class));
    }

    protected static final String TAG = "TAG";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

}
