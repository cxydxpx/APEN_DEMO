package com.example.proguarddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.proguarddemo.R;
import com.example.proguarddemo.bean.ParentBean;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_copy);

        setCommonMethod();

    }

    private void setCommonMethod() {
        ParentBean mb = new ParentBean();
        mb.setName("hello");
        String name = mb.getName();
    }
}
