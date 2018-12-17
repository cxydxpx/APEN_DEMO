package com.apen.simple.custom.impl;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.orhanobut.logger.Logger;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-12-05.
 * GitHub：https://github.com/cxydxpx
 */

public class GestureDetectorImpl implements GestureDetector.OnGestureListener {
    /**
     * 触碰屏幕时调用此方法
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {

        Logger.v("onDown");

        return false;
    }

    /**
     * 手指在屏幕上按下,且未移动和松开时间调用此方法
     *
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Logger.v("onShowPress");

    }

    /**
     * 轻击屏幕时调用该方法
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Logger.v("onSingleTapUp");
        return false;
    }

    /**
     * 手指在屏幕上滚动时会调用该方法
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Logger.v("onScroll");
        return false;
    }

    /**
     * 手指长按屏幕时均会调用该方法
     *
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Logger.v("onLongPress");
    }

    /**
     * 手指在屏幕上拖动时会调用该方法
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Logger.v("onFling");
        return false;
    }
}
