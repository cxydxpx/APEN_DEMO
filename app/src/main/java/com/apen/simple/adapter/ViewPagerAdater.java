package com.apen.simple.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.apen.simple.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-09-11.
 * GitHub：https://github.com/cxydxpx
 */

public class ViewPagerAdater extends FragmentPagerAdapter {

    public ViewPagerAdater(FragmentManager fm) {
        super(fm);
    }

    private List<String> mDatas = new ArrayList<>();

    public ViewPagerAdater(FragmentActivity context, List<String> data) {
        super(context.getSupportFragmentManager());
        this.mDatas = data;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
