package com.apen.simple.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apen.simple.R;

import org.jetbrains.annotations.Nullable;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-09-11.
 * GitHub：https://github.com/cxydxpx
 */

public class ViewPagerFragment extends Fragment{

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private int mTitle;
    private TextView mTextView;
    private LinearLayout mLinear;

    public static ViewPagerFragment newInstance(int title ){
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getInt("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);
        mTextView = (TextView) view.findViewById(R.id.fragment_textView);
        mTextView.setText("Page"+(mTitle + 1));
        mLinear = (LinearLayout) view.findViewById(R.id.fragment_ll);
        /**这里注意是setBackgroundResource不是setBackgroundColor；setBackgroundResource(int resId)方法的参数是一个组件的id值。该方法也是用于加载组件的背景图片的；setBackgroundColor(Color.XXX)方法参数为一个Color类的静态常量.顾名思义,它是用来设置背景颜色的方法.*/
        return view;
    }

}