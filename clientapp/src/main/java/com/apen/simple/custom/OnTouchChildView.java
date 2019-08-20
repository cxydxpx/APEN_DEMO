package com.apen.simple.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-20.
 * GitHub：https://github.com/cxydxpx
 */

public class OnTouchChildView extends View {


    public OnTouchChildView(Context context) {
        super(context);

        initView();

    }

    public OnTouchChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private Paint mPaint;

    private void initView() {
        mPaint = new Paint();
        //模式 描边 填充 描边且填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 描边宽度
        mPaint.setStrokeWidth(10);
        // 画点
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getRight(), getBottom(), mPaint);

    }

}
