package com.example.proguarddemo.bean;

import android.util.Log;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-19.
 * GitHub：https://github.com/cxydxpx
 */
public class ChildenBean {

    private String name;

    public ChildenBean(String obj) {
        this.name = obj;
        Log.v("TAG", "ChildenBean construction" + name);

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
