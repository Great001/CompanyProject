package com.lhc.android.great.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class TabViewPagerAdapter extends PagerAdapter {

    private List<View> list=new ArrayList<>();
    public TabViewPagerAdapter(List<View> list){
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position),0);
       return list.get(position);
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(list.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
