package com.apen.simple.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-11-12.
 * GitHub：https://github.com/cxydxpx
 */

public class IView extends View {

    public IView(Context context) {
        super(context);


    }

    private void initView(Context con) {
    }

    public IView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int lastX;
    int lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 获取手指触摸点的横坐标与纵坐标

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 调用Layout() 重绘
//                layout(getLeft() + offsetX,getTop() + offsetY,getRight() + offsetX,getBottom() + offsetY);
                //
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                break;
            default:
                break;
        }

        return true;
    }
}
