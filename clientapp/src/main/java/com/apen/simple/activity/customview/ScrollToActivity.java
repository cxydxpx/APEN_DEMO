package com.apen.simple.activity.customview;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.tool.ToolToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-19.
 * GitHub：https://github.com/cxydxpx
 */

public class ScrollToActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_scollto;
    }

    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;

    @OnClick(R.id.linearLayout)
    void click() {
        ToolToast.showToast(this, "scrollTo 演示");
        mLinearLayout.scrollTo(330, 0);
    }

    /**
     *  getX : 304.28198 getRawX : 330.28198
     *  activity getX : 14.007568activity getRawX : 14.007568
     */

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void init() {
        super.init();


        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        float x = event.getX();
                        float rawX = event.getRawX();
                        Log.v(tag, "getX : " + x + " getRawX : " + rawX);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float rawX = event.getRawX();
                Log.v(tag, "activity getX : " + x + "activity getRawX : " + rawX);
                break;
            default:
                break;
        }


        return super.onTouchEvent(event);
    }



}
