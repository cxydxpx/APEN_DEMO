package com.example.proguarddemo.utils;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-20.
 * GitHub：https://github.com/cxydxpx
 */
public class CommonMethod {

    public void methodNormal() {
        String logMessage = "this is normal method";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }

    public void methodUnused() {
        String logMessage = "this is unused method";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }

}
