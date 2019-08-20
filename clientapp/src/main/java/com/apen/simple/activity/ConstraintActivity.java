package com.apen.simple.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-17.
 * GitHub：https://github.com/cxydxpx
 *
 * @author Administrator
 */

public class ConstraintActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView mTextView;

    /**
     * 学习
     * https://developer.android.google.cn/reference/android/support/constraint/ConstraintLayout
     * https://developer.android.google.cn/studio/write/layout-editor
     * https://developer.android.google.cn/training/constraint-layout/
     * <p>
     * 参考线
     * https://developer.android.google.cn/reference/android/support/constraint/Guideline
     * 分组控制隐藏/展示
     * https://developer.android.google.cn/reference/android/support/constraint/Group
     * 组视图边缘线
     * https://developer.android.google.cn/reference/android/support/constraint/Barrier
     *
     * @return
     */

    @Override
    protected int layoutResId() {
        return R.layout.activity_constraint;
    }

    @Override
    protected void init() {
        super.init();

        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

}
