package com.apen.simple.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-17.
 * GitHub：https://github.com/cxydxpx
 */

public class TextSwitcherView extends TextSwitcher implements TextSwitcher.ViewFactory {

    private Context mContext;

    public TextSwitcherView(Context context) {
        super(context);
        initView(context);

    }

    private void initView(Context context) {
        this.mContext = context;
        setFactory(this);
        setAnimTime();
    }

    public TextSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(mContext);
        textView.setTextSize(24);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    private void setAnimTime() {

        Animation in = new TranslateAnimation(0, 0, -300, 0);
        in.setDuration(300);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0, 0, 0, 300);
        out.setDuration(300);
        out.setInterpolator(new AccelerateInterpolator());
        setInAnimation(in);
        setOutAnimation(out);
    }

    public void setTextContent(String str) {
        setText(str);
    }
}
