package com.apen.simple.activity.customview;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.custom.OnTouchChildView;
import com.apen.simple.custom.OnTouchParentView;
import com.apen.simple.tool.ToolToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-20.
 * GitHub：https://github.com/cxydxpx
 */

public class TouchViewActivity extends BaseActivity {
    @Override
    protected int layoutResId() {
        return R.layout.activity_touch_view;
    }

    @BindView(R.id.child)
    OnTouchChildView mChildView;

    @BindView(R.id.parent)
    OnTouchParentView mParentView;

    @Override
    protected void init() {
        super.init();
        mChildView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        ToolToast.showToast(TouchViewActivity.this, " setOnTouchListener 子布局点击");
                        return true;
                    }
                }
        );

        Looper.prepare();

        Looper.loop();


        mHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });

    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };


    LinearLayout mLinearLayout;
    RelativeLayout mRelativeLayout;

    private View.MeasureSpec mSpec;

    @OnClick({R.id.parent, R.id.child})
    void click(View view) {
        switch (view.getId()) {
            case R.id.parent:
                ToolToast.showToast(this, "父布局点击");
                break;
            case R.id.child:
                ToolToast.showToast(this, "setOnClickListener 子布局点击");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}

