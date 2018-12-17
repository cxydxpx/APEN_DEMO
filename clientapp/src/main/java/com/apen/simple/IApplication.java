package com.apen.simple;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.apen.simple.helper.HelpeLocation;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;

import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-23.
 * GitHub：https://github.com/cxydxpx
 *
 * @author apen
 */

public class IApplication extends Application {

    public HelpeLocation locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();

//        String processName = getProcessName(getApplicationContext(), android.os.Process.myPid());

//        Log.v("TAG","application start process name : " + processName);

        Utils.init(this);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new HelpeLocation(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(this);
    }

    private static IApplication instance;

    public static Context getInstance() {
        if (instance == null) {
            instance = new IApplication();
        }
        return instance;
    }

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

}
