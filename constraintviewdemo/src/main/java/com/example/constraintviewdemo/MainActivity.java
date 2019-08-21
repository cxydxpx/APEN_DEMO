package com.example.constraintviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * 学习
     * https://developer.android.google.cn/reference/android/support/constraint/ConstraintLayout
     * https://developer.android.google.cn/studio/write/layout-editor
     * https://developer.android.google.cn/training/constraint-layout/
     * <p>
     * 参考线
     * https://developer.android.google.cn/reference/android/support/constraint/Guideline
     * 分组控制隐藏/展示
     * https://developer.android.google.cn/reference/android/support/constraint/Group
     * 组视图边缘线
     * https://developer.android.google.cn/reference/android/support/constraint/Barrier
     *
     * @return
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
