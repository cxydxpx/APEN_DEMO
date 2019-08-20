package com.apen.simple.activity.customview;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.custom.CustomView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-13.
 * GitHub：https://github.com/cxydxpx
 */

public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.relative)
    RelativeLayout relative;

    @BindView(R.id.tv)
    CustomView tv;

    @Override
    protected int layoutResId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void init() {
        super.init();

    }

    public void showLocation(View view) {

        int tvleft = tv.getLeft();
        int tvright = tv.getRight();
        int tvtop = tv.getTop();
        int tvbottom = tv.getBottom();

        int rlleft = relative.getLeft();
        int rlright = relative.getRight();
        int rltop = relative.getTop();
        int rlbottom = relative.getBottom();

        Log.v(tag, "tvleft : " + tvleft);
        Log.v(tag, "tvright : " + tvright);
        Log.v(tag, "tvtop : " + tvtop);
        Log.v(tag, "tvbottom : " + tvbottom);
        Log.v(tag, "tvX : " + tv.getX());
        Log.v(tag, "tvY : " + tv.getY());
        Log.v(tag, "tvTranX : " + tv.getTranslationX());
        Log.v(tag, "tvTranY : " + tv.getTranslationY());


        Log.v(tag, "rlleft : " + rlleft);
        Log.v(tag, "rlright : " + rlright);
        Log.v(tag, "rltop : " + rltop);
        Log.v(tag, "rlbottom : " + rlbottom);

    }




    private void initCur() {
        List<Float> yline = new ArrayList<Float>();
        yline.add(2.420f);
        yline.add(2.444f);
        yline.add(2.453f);
        yline.add(2.420f);
        yline.add(2.435f);
        yline.add(2.422f);
        yline.add(2.435f);
    }
}
