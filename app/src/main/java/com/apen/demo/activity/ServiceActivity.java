package com.apen.demo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;
import com.apen.demo.service.ServiceDemo;

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
     *
     * IntentService
     *
     * onHandleIntent
     *
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
        startService(new Intent(this, ServiceDemo.class));
    }

    /**
     * 绑定服务
     *
     * @param v
     */
    public void bindService(View v) {
        bindService(new Intent(this, ServiceDemo.class), mServiceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     *
     * @param v
     */
    public void unbindService(View v) {
        unbindService(mServiceConnection);
    }

    /**
     * 停止服务
     *
     * @param v
     */
    public void stopService(View v) {
        stopService(new Intent(this, ServiceDemo.class));
    }


}
