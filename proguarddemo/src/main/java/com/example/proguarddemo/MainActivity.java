package com.example.proguarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proguarddemo.bean.ChildenBean;
import com.example.proguarddemo.utils.CommonMethod;
import com.example.proguarddemo.utils.NativeUtils;

public class MainActivity extends AppCompatActivity {

    private String toastTip = "test instance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_copy);

        ChildenBean bean = new ChildenBean("this obj");
        Log.v("TAG", bean.getName());
        TextView tv = findViewById(R.id.tv);
        tv.setText(bean.getName());

        CommonMethod utils = new CommonMethod();
        utils.methodNormal();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                methodWithGlobalVariable();
                methodWithLocalVariable();
                NativeUtils.methodNative();
                NativeUtils.methodNotNative();
            }
        });


    }

    private void methodWithGlobalVariable() {
        Toast.makeText(MainActivity.this, toastTip, Toast.LENGTH_SHORT).show();
    }

    private void methodWithLocalVariable() {
        String logMessage = "log in MainActivity";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }
}
