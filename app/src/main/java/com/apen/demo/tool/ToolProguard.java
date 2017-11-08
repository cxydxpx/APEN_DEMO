package com.apen.demo.tool;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-01.
 * GitHub：https://github.com/cxydxpx
 */

public class ToolProguard {

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
