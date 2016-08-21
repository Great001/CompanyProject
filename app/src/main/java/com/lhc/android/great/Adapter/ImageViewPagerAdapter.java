package com.lhc.android.great.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ImageViewPagerAdapter extends PagerAdapter {
    private List<ImageView> mListImage;
    private Context context;

    public ImageViewPagerAdapter(Context context,List<ImageView> list){
        this.context=context;
        this.mListImage=list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view=mListImage.get(position);
        ViewParent vp=view.getParent();
        if(vp!=null){
           ViewGroup parent=(ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView(mListImage.get(position),0);
        return mListImage.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mListImage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mListImage.get(position));
    }




}
