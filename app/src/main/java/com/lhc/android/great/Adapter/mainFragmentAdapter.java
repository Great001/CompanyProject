package com.lhc.android.great.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/7.
 */
public class mainFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;
    public mainFragmentAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.mFragments=list;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
