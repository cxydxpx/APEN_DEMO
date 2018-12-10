package com.apen.simple.custom;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-11-12.
 * GitHub：https://github.com/cxydxpx
 */

public class ScollerCopyView extends Scroller{

    public ScollerCopyView(Context context) {
        super(context);
    }

    public ScollerCopyView(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ScollerCopyView(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy);
    }
}
