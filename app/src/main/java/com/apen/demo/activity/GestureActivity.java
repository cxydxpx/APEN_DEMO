package com.apen.demo.activity;

import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;
import com.apen.demo.tool.ToolToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-12-21.
 * GitHub：https://github.com/cxydxpx
 */

public class GestureActivity extends BaseActivity implements GestureOverlayView.OnGesturePerformedListener, GestureOverlayView.OnGesturingListener {

    private Gesture mGesture;

    @OnClick(R.id.btn_submit)
    void click() {
        Bitmap bitmap = mGesture.toBitmap(480, 750, 12, Color.BLACK);

        Bitmap toBitmap = toBitmap(mGesture, 720, 1280, 12, Color.BLACK);


        mGestureView.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
        mImageView.setImageBitmap(toBitmap);
    }

    @OnClick(R.id.btn_clear)
    void cliear(){
        mGestureView.clear(false);
    }

    @BindView(R.id.gestureView)
    GestureOverlayView mGestureView;

    @BindView(R.id.image)
    ImageView mImageView;

    @Override
    protected int layoutResId() {
        return R.layout.activity_gesture;
    }

    @Override
    protected void init() {
        super.init();

        //设置手势可多笔画绘制，默认情况为单笔画绘制
        mGestureView.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
        //设置手势的颜色(蓝色)
        mGestureView.setGestureColor(getResources().getColor(R.color.black));
        //设置还没未能形成手势绘制是的颜色(红色)
        mGestureView.setUncertainGestureColor(getResources().getColor(R.color.black));
        //设置手势的粗细
        mGestureView.setGestureStrokeWidth(10);
        /*手势绘制完成后淡出屏幕的时间间隔，即绘制完手指离开屏幕后相隔多长时间手势从屏幕上消失；
         * 可以理解为手势绘制完成手指离开屏幕后到调用onGesturePerformed的时间间隔
         * 默认值为420毫秒，这里设置为2秒
         */
        mGestureView.setFadeOffset(Integer.MAX_VALUE);



        //手势监听器
        mGestureView.addOnGesturePerformedListener(this);
        //手势执行监听器
        mGestureView.addOnGesturingListener(this);

    }

    /**
     * 手势绘制完成时调用
     *
     * @param overlay
     * @param gesture
     */
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ToolToast.showToast(this, "手势绘制完成");
    }

    @Override
    public void onGesturingStarted(GestureOverlayView overlay) {
        mGesture = null;
        ToolToast.showToast(this, "正在绘制手势");
    }

    private static final float LENGTH_THRESHOLD = 120.0f;

    @Override
    public void onGesturingEnded(GestureOverlayView overlay) {
        mGesture = overlay.getGesture();
        if (mGesture.getLength() < LENGTH_THRESHOLD) {
            overlay.clear(false);
        }
        ToolToast.showToast(this, "结束绘制手势");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除绑定的监听器
        mGestureView.removeOnGesturePerformedListener(this);
        mGestureView.removeOnGesturingListener(this);
    }

    /**
     * Creates a bitmap of the gesture with a transparent background.
     *
     * @param width
     * @param height
     * @param inset
     * @param color
     * @return the bitmap
     */
    public Bitmap toBitmap(Gesture gesture, int width, int height, int inset, int color) {
        final Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(15);

        final Path path = gesture.toPath();
        final RectF bounds = new RectF();
        path.computeBounds(bounds, true);

        final float sx = (width - 2 * inset) / bounds.width();
        final float sy = (height - 2 * inset) / bounds.height();
        final float scale = sx > sy ? sy : sx;
//        paint.setStrokeWidth(2.0f / scale);

        path.offset(-bounds.left + (width - bounds.width() * scale) / 2.0f,
                -bounds.top + (height - bounds.height() * scale) / 2.0f);

        canvas.translate(inset, inset);
        canvas.scale(scale, scale);

        canvas.drawPath(path, paint);

        return bitmap;
    }

}
