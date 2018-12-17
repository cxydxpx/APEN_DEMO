package com.apen.simple.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.apen.simple.R;
import com.apen.simple.adapter.ViewPagerAdater;
import com.apen.simple.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-09-11.
 * GitHub：https://github.com/cxydxpx
 */

public class ViewPagerActivity extends BaseActivity{

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTableLayout;

    @Override
    protected int layoutResId() {
        return R.layout.activity_viewpager;
    }

    private List<String> mData = new ArrayList<>();

    @Override
    protected void init() {
        super.init();

        mData.add("服装行业");
        mData.add("餐饮行业");
//        mData.add("鞋子");
        mData.add("帽子行业");
        mData.add("外套行业");
        mData.add("裤子行业");
//        mData.add("卫衣");
//        mData.add("靴子");
//        mData.add("铆钉");

        ViewPagerAdater mAdater = new ViewPagerAdater(this,mData);
        mViewPager.setAdapter(mAdater);



        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
