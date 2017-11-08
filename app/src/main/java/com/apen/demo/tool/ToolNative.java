package com.apen.demo.tool;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-01.
 * GitHub：https://github.com/cxydxpx
 */

public class ToolNative {

//    public static native void methodNative();

    public static void methodNotNative() {
        String logMessage = "this is not native method";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }


    public static void methodNative() {

    }
}
