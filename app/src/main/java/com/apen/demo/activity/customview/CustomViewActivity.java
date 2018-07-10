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
        yline.add(2.420f);
        yline.add(2.444f);
        yline.add(2.453f);
        yline.add(2.420f);
        yline.add(2.435f);
        yline.add(2.422f);
        yline.add(2.435f);
        mCurveView.setDataY(yline);
    }
}
