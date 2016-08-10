package com.lhc.android.great.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.great.R;

/**
 * Created by Administrator on 2016/7/26.
 */
public class PersonalPageLvAdapter extends BaseAdapter {

    private int [] items;
    private int [] icons;
    private Context context;

    private static final int TYPE_ITEM=0;
    private static final int TYPE_DIVIDER=1;

    public PersonalPageLvAdapter(Context context,int [] items,int [] icons){
        this.context=context;
        this.items=items;
        this.icons=icons;
    }

    @Override
    public int getItemViewType(int position) {
        if(items[position]==-1)
            return TYPE_DIVIDER;
        else {
            return TYPE_ITEM;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if(getItemViewType(i)==TYPE_ITEM) {
            if (view == null) {
                holder = new MyViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.personal_page_lv_item, null);
                holder.tvItem = (TextView) view.findViewById(R.id.tv_pp_lv_item);
                holder.ivIcon=(ImageView)view.findViewById(R.id.iv_pp_lv_item);
                view.setTag(holder);
            } else {
                holder = (MyViewHolder) view.getTag();
            }
            holder.tvItem.setText(items[i]);
            holder.ivIcon.setImageResource(icons[i]);
        }
        else{
            view=LayoutInflater.from(context).inflate(R.layout.personal_page_lv_divider_view,null);
        }

        return view;
    }

    class MyViewHolder {
        ImageView ivIcon;
        TextView tvItem;
    }

}
