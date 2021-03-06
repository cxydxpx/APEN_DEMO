package com.apen.simple.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-13.
 * GitHub：https://github.com/cxydxpx
 */

public class CustomView extends View implements GestureDetector.OnGestureListener {

    private Path mPath;

    public CustomView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CustomView(Context context, @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;

    private void initV() {

    }

    private void init() {

        initV();

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
        //设置颜色
//        mPaint.setColor(Color.BLACK);

        // 线性渐变
//        Shader shader = new RadialGradient( 300, 300, 200,
//                Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"),
//                Shader.TileMode.CLAMP);

        // 圆图
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
//        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        mPaint.setShader(shader);
//
//        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
//        mPaint.setColorFilter(lightingColorFilter);

//        mPath = new Path();
        // 使用 path 对图形进行描述（这段描述代码不必看懂） ❤
//        mPath.addArc(200, 200, 400, 400, -225, 225);
//        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
//        mPath.lineTo(400, 542);
        //相当于 画圆
//        mPath.addCircle(300,300,200,Path.Direction.CW);

//        mPath.moveTo(100, 100);
//        mPath.lineTo(200, 100);
//        mPath.lineTo(150,150);
//        mPath.close();
        // 连线椭圆
//        mPath.arcTo(100, 100, 300, 300, -180, 180, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制一个圆
//        canvas.drawCircle(300, 300, 200, mPaint);
        //绘制一个正方形
        canvas.drawRect(100, 100, 500, 500, mPaint);
        //绘制一个点
//        float[] floats = {0,0,50,50,50,100,100,50,100,100,50,150,100,150};
//        canvas.drawPoints(floats,2,12,mPaint);
        //绘制椭圆
//        canvas.drawOval(100, 100, 300, 300, mPaint);
        //绘制直线
//        canvas.drawLine(100, 380, 720, 380, mPaint);
        //圆角矩形
//        canvas.drawRoundRect();
        //绘制弧形
//        canvas.drawArc();
        // 绘制自定义
//        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                scrollTo(100,0);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

//        velocity(event);
//
//        GestureDetector gestureDetector = new GestureDetector(this);
//        // 解决屏幕长按后 无法拖动的现象
//        gestureDetector.setIsLongpressEnabled(false);
//
//        scrollTo(0, 0);
//        scrollBy(0, 0);
//
//        return gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private Context mContext;
//    private Scroller mScroller = new Scroller(mContext);

    @Override
    public void computeScroll() {
//        if (mScroller.computeScrollOffset()) {
//            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            postInvalidate();
//        }
    }

    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        // 1000ms内划向destX,效果就是慢慢滑动
//        mScroller.startScroll(scrollX, 0, delta, 0, 1000);
    }

    private void velocity(MotionEvent event) {
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);

        // units时间内划过的像素数
        velocityTracker.computeCurrentVelocity(1000);
        float velocityX = velocityTracker.getXVelocity();
        float velocityY = velocityTracker.getYVelocity();

        // 清理
        velocityTracker.clear();
        velocityTracker.recycle();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * 单击
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * 滑动
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 快速滑动
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}
