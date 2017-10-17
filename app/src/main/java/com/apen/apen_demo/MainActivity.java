package com.apen.apen_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick({R.id.btn_constraintLayout})
    public void allClick(View v) {
        switch (v.getId()) {
            case R.id.btn_constraintLayout:
                startActivity(new Intent(MainActivity.this, ConstraintActivity.class));
                break;
            default:
                break;
        }
    }

}
