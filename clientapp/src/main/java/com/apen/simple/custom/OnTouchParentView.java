package com.apen.simple.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-20.
 * GitHub：https://github.com/cxydxpx
 */

public class OnTouchParentView extends RelativeLayout {

    public OnTouchParentView(Context context) {
        super(context);
    }

    public OnTouchParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


}
