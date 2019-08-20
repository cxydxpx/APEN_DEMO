package com.apen.simple;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Vibrator;

import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-23.
 * GitHub：https://github.com/cxydxpx
 *
 * @author apen
 */

public class IApplication extends Application {

    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();

//        Utils.init(this);
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
