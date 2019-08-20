package com.apen.simple.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.service.ServiceDemo;
import com.apen.simple.tool.ToolToast;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-01-03.
 * GitHub：https://github.com/cxydxpx
 */

public class ServiceActivity extends BaseActivity {

    private ServiceDemo.MyBinder mBinder;

    /**
     * service 基本用法
     * Service和Activity通信 onBind
     * Service的销毁方式
     * Service和Thread的关系
     * 如何创建前台服务
     * <p>
     * IntentService
     * <p>
     * onHandleIntent
     * <p>
     * <p>
     * onBind 用于activity和service的交互
     */

    @Override
    protected int layoutResId() {
        return R.layout.activity_service;
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mBinder = (ServiceDemo.MyBinder) service;
            mBinder.toastText();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 开启服务
     *
     * @param v
     */
    public void startService(View v) {
//        ToolToast.showToast("开启服务");
//        startService(new Intent(this, ServiceDemo.class));
        startActivity(new Intent(this, GlideActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(tag, " ServiceActivity onCreate ");

    }

    @Override
    protected void onStart() {
        super.onStart();

         Log.v(tag," ServiceActivity onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
         Log.v(tag," ServiceActivity onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
         Log.v(tag," ServiceActivity onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
         Log.v(tag," ServiceActivity onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         Log.v(tag," ServiceActivity onDestroy ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
         Log.v(tag," ServiceActivity onRestart ");
    }

    /**
     * 绑定服务
     *
     * @param v
     */
    public void bindService(View v) {
        ToolToast.showToast("绑定服务");
        bindService(new Intent(this, ServiceDemo.class), mServiceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     *
     * @param v
     */
    public void unbindService(View v) {
        unbindService(mServiceConnection);
        ToolToast.showToast("解除绑定");
    }

    /**
     * 停止服务
     *
     * @param v
     */
    public void stopService(View v) {
        stopService(new Intent(this, ServiceDemo.class));
        ToolToast.showToast("停止服务");
    }


}
