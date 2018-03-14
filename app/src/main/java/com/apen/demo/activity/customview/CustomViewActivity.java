package com.apen.demo.activity.customview;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;
import com.apen.demo.custom.CurveView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-13.
 * GitHub：https://github.com/cxydxpx
 */

public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.line)
    CurveView mCurveView;

    @Override
    protected int layoutResId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void init() {
        super.init();
        List<Float> yline=new ArrayList<Float>();
        yline.add(2.420123f);
        yline.add(2.444122f);
        yline.add(2.45359f);
        yline.add(2.4206f);
        yline.add(2.4357f);
        yline.add(2.4228f);
        yline.add(2.4350f);
        mCurveView.setDataY(yline);
    }
}
