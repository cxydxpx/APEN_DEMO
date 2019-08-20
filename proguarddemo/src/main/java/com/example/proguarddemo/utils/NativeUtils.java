package com.example.proguarddemo.utils;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-20.
 * GitHub：https://github.com/cxydxpx
 */
public class NativeUtils {

    public static native void methodNative();

    public static void methodNotNative() {
        String logMessage = "this is not native method";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }

}
