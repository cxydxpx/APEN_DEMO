package com.apen.simple.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

import com.apen.simple.tool.ToolMath;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-03-12.
 * GitHub：https://github.com/cxydxpx
 */

public class CurveView extends View implements GestureDetector.OnGestureListener {


    private Paint mTextPaint, mLinePaint, mPathPaint, mPointPaint;
    //柱状图的宽度
    private float mPaintRectWidth;
    //路径
    private Path mPath;
    //高跟宽
    private float mWidth, mHeight;
    //柱状图的数量
    private final float mCount = 6;
    //偏移量
    private final float offsets = 1;

    private float mRectHeight;
    //x轴的坐标
    private List<Float> xline = new ArrayList<Float>();
    //Y轴的坐标
    private List<Float> yline = new ArrayList<Float>();

    //左边文字
    private float[] x = {2.46f, 2.45f, 2.44f, 2.43f, 2.42f, 2.41f, 2.40f};
    //底部文字
    private String[] day = {"07-01", "07-02", "07-03", "07-04", "07-05", "07-06", "07-07"};

    private Context mContext;

    public CurveView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        //绘制线和文字的颜色
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#cccccc"));
        mTextPaint.setTextSize(25);
        mTextPaint.setStrokeWidth(1);

        //绘制折线图的点
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(Color.parseColor("#000000"));
        mPointPaint.setTextSize(25);
        mPointPaint.setStrokeWidth(5);

        //绘制柱状图的画笔
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);

        //绘制折线图的画笔
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(Color.parseColor("#ff0000"));
        mPathPaint.setStyle(Paint.Style.STROKE);

        //折线图的路径
        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = (float) (getWidth() - getWidth() * 0.1);
        mHeight = (float) (getHeight() - getHeight() * 0.1);
        mRectHeight = (float) (getHeight() - getHeight() * 0.1);
        mPaintRectWidth = (float) (mWidth * 0.8 / mCount);
        mLinePaint.setStrokeWidth(mPaintRectWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        onDrawRect(canvas);
//        onDrawLine(canvas);
//        canvasPath(canvas);
    }

    //绘制6个矩形
    private void onDrawRect(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                mLinePaint.setColor(Color.parseColor("#eeeeee"));
            } else {
                mLinePaint.setColor(Color.parseColor("#ece1f3"));
            }
            float left = (float) (mWidth * 0.4 / 2 + mPaintRectWidth * i + offsets);
            float right = (float) (mWidth * 0.4 / 2 + mPaintRectWidth * (i + 1));

            canvas.drawRect(left, (float) (mRectHeight * 0.01), right, mHeight, mLinePaint);
        }

    }

    //绘制网格线
    private void onDrawLine(Canvas canvas) {
        //第一条线
        canvas.drawLine(mPaintRectWidth - mPaintRectWidth / 2, (float) (mRectHeight * 0.01), getWidth(), (float) (mRectHeight * 0.01), mTextPaint);
        //定义这里高度
        float height;
        //横七条
        for (float i = 0; i < 7; i++) {
            //从上到下
            if (i == 0) {
                height = i;
            } else {
                height = mRectHeight * (i / 6);
                float size = mTextPaint.measureText(x[(int) i] + "");
                //绘制线
                canvas.drawLine(mPaintRectWidth + mPaintRectWidth / 2, height, getWidth(), height, mTextPaint);
                //绘制左边Y轴的文字
                canvas.drawText(x[(int) i] + "", (float) (mPaintRectWidth - mPaintRectWidth * 0.35), height + size / 5, mTextPaint);
            }

        }
        //竖七条
        canvas.drawLine((float) (mPaintRectWidth - mPaintRectWidth / 2), 0, (float) (mPaintRectWidth - mPaintRectWidth / 2), mHeight, mTextPaint);
        for (float i = 0; i < 7; i++) {
            //从左到右
            canvas.drawLine((float) (mWidth * 0.4 / 2 + mPaintRectWidth * i), 0, (float) (mWidth * 0.4 / 2 + mPaintRectWidth * i), mHeight, mTextPaint);
            //绘制底边的日期文字
            canvas.drawText(day[(int) i], (float) (mWidth * 0.34 / 2 + mPaintRectWidth * i), (float) (mHeight + mHeight * 0.1), mTextPaint);
            //准备好下面折线图的X轴坐标
            xline.add((float) (mWidth * 0.4 / 2 + mPaintRectWidth * i));
        }
        //折线图的第一个点
        xline.add((float) (mPaintRectWidth - mPaintRectWidth / 2));


    }

    //绘制折线路径
    public void canvasPath(Canvas canvas) {


        for (int j = 0; j < yline.size(); j++) {

            float x = xline.get(j);
            float y = yline.get(j);
            float aftery = ToolMath.initData(y);
            if (j == 0) {
                mPath.moveTo(x, aftery);
            } else {

                mPath.lineTo(x, aftery);
            }
            canvas.drawPoint(x, aftery, mPointPaint);
            float size = mPointPaint.measureText(y + "");
            canvas.drawText(y + "", (float) (x - size / 2), (float) (aftery + size * 0.25), mPointPaint);
        }
        canvas.drawPath(mPath, mPathPaint);
    }

    //用于设置Y轴的坐标值
    public void setDataY(List<Float> yline) {
        this.yline.clear();
        this.yline = yline;
    }

    //一个更新UI的方法
    public void invalidata() {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        velocity(event);

        GestureDetector gestureDetector = new GestureDetector(this);
        // 解决屏幕长按后 无法拖动的现象
        gestureDetector.setIsLongpressEnabled(false);

        scrollTo(0,0);
        scrollBy(0,0);

        return gestureDetector.onTouchEvent(event);
    }

    private Scroller mScroller = new Scroller(mContext);

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        // 1000ms内划向destX,效果就是慢慢滑动
        mScroller.startScroll(scrollX, 0, delta, 0,1000);
        invalidata();
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
