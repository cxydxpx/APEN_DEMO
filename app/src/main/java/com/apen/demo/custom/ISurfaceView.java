package com.apen.demo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-29.
 * GitHub：https://github.com/cxydxpx
 */

public class ISurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public ISurfaceView(Context context) {
        super(context);
        initHolder();
    }

    LoopThread thread;

    private void initHolder() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        thread = new LoopThread(holder, getContext());
    }

    /**
     * SurfaceView被显示的时候调用
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning = true;
        thread.start();
    }

    /**
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * SurfaceView 隐藏销毁时调用
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class LoopThread extends Thread {

        SurfaceHolder mSurfaceHolder;
        Context mContext;
        float radius = 10;
        Paint mPaint;
        boolean isRunning;


        public LoopThread(SurfaceHolder holder, Context context) {
            mSurfaceHolder = holder;
            mContext = context;
            isRunning = false;

            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void run() {
            super.run();
            Canvas canvas = null;
            while (isRunning) {
                try {
                    synchronized (mSurfaceHolder) {

                        canvas = mSurfaceHolder.lockCanvas(null);
                        doDraw(canvas);
                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

        public void doDraw(Canvas c) {

            //这个很重要，清屏操作，清楚掉上次绘制的残留图像
            c.drawColor(Color.BLACK);

            c.translate(200, 200);
            c.drawCircle(0, 0, radius++, mPaint);

            if (radius > 100) {
                radius = 10f;
            }

        }

    }
}
