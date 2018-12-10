package com.apen.simple.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-01-03.
 * GitHub：https://github.com/cxydxpx
 */

public class ServiceDemo extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.v("Service onCreate()");
    }

    MyBinder mBinder = new MyBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.v("Service onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.v("Service onBind()");
        return mBinder;
    }

    public class MyBinder extends Binder {

        public void toastText() {
            Toast.makeText(ServiceDemo.this, "吼吼吼吼吼", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.v("Service Destroy()");
    }
}
