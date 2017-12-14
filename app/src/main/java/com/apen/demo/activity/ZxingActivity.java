package com.apen.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.apen.demo.custom.ISurfaceView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-08.
 * GitHub：https://github.com/cxydxpx
 * @author apen
 */

public class ZxingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ISurfaceView(this));
    }

    public void zxing(View v) {

        startActivity(new Intent(this, ScanActivity.class));
    }

    protected static final String TAG = "TAG";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private class AnimateView extends View {

        float radius = 10;
        Paint mPaint;

        public AnimateView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.translate(200, 200);
            canvas.drawCircle(0, 0, radius++, mPaint);

            if (radius > 100) {
                radius = 10;
            }

            invalidate();//通过调用这个方法让系统自动刷新视图
        }
    }

}
