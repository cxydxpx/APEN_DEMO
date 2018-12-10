package com.apen.simple.tool;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-03-12.
 * GitHub：https://github.com/cxydxpx
 */

public class ToolMath {
    public static float initData(float a) {
        if (2.40f <= a && a < 2.41f) {
            a = 540f - 540f / (a / 0.01f);
        } else if (2.41f <= a && a < 2.42f) {
            a = 480f - 480f / (a / 0.01f);
        } else if (2.42f <= a && a < 2.43f) {
            a = 360f - 360f / (a / 0.01f);
        } else if (2.43f <= a && a < 2.44f) {
            a = 270f - 270f / (a / 0.01f);
        } else if (2.44f <= a && a < 2.45f) {
            a = 180f - 180f / (a / 0.01f);
        } else if (2.45f <= a && a < 2.46f) {
            a = 90f - 90f / (a / 0.01f);
        }

        return a;

    }
}
