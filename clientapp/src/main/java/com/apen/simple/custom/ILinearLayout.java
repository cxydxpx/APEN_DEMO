package com.apen.simple.custom;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import com.apen.simple.custom.impl.GestureDetectorImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-27.
 * GitHub：https://github.com/cxydxpx
 */

public class ILinearLayout extends LinearLayout {


    public ILinearLayout(Context context) {
        super(context);

        initView(context);
    }

    /**
     * Configuration
     * ViewConfiguration
     * GestureDetector
     * VelocityTracker
     * Scroller
     * ViewDragHelper
     */


    private void initView(Context context) {

        Configuration configuration = getResources().getConfiguration();

//        ViewConfiguration 初始化
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
//         系统能识别出的最小滑动距离
        int touchSlop = viewConfiguration.getScaledTouchSlop();
//          判断是否有屋里按键
        boolean b = viewConfiguration.hasPermanentMenuKey();
//          双击间隔时间.在该时间内是双击，否则是单击
        int slop = viewConfiguration.getScaledDoubleTapSlop();
//          按住状态转变为长按状态需要的时间
        int longPressTimeout = ViewConfiguration.getLongPressTimeout();
//          重复按键的时间
        int keyRepeatTimeout = ViewConfiguration.getKeyRepeatTimeout();
    }

    private Context mContext;

    public ILinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    ArrayBlockingQueue ms;
    ThreadPoolExecutor mExecutor;
    AsyncTask mTask = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * disallowIntercept 默认为false 调用requestDisallowInterceptTouchEvent 设置
     * dispatchTouchEvent() 方法里 取反
     * if (disallowIntercept || !onInterceptTouchEvent(ev)) {
     * ev.setAction(MotionEvent.ACTION_DOWN);
     * }
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


    /**
     * 最外层参数获取 ViewRoot
     * <p>
     * childWidthMeasureSpec = getRootMeasureSpec(desiredWindowWidth, lp.width);
     * childHeightMeasureSpec = getRootMeasureSpec(desiredWindowHeight, lp.height);
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        GestureDetector mGestureDetector = new GestureDetector(mContext, new GestureDetectorImpl());

        return mGestureDetector.onTouchEvent(event);
    }

    private VelocityTracker mVelocityTracker;

    private void startVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private int getScrollVelocity() {
        // 设置VelocityTracker单位.1000表示1秒时间内运动的像素
        mVelocityTracker.computeCurrentVelocity(1000);
        // 获取在1秒内X方向所滑动像素值
        int xVelocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(xVelocity);
    }


}
