package com.lhc.android.great.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lhc.android.great.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class UserInfoAdapter extends BaseAdapter {

    private static final int TYPE_CONTENT=1;
    private static final int TYPE_DIVIDER=0;
    int items[]={-1,R.string.nickname,R.string.school_id,R.string.sex,-1,R.string.phone_number,R.string.email,-1,R.string.common_address,-1,R.string.school,R.string.major,R.string.grade,-1};
    List<String> mListInfo;
    Context context;

    public UserInfoAdapter(Context context,List<String> list){
        this.context=context;
        this.mListInfo=list;
    }

    @Override
    public int getCount() {
        return mListInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return mListInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        myViewHolder holder;
        int type=getItemViewType(i);
        if(type==1) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.user_info_item, null);
                holder = new myViewHolder();
                holder.name = (TextView) view.findViewById(R.id.tv_userinfo_item_name);
                holder.value = (TextView) view.findViewById(R.id.tv_userinfo_value);
                view.setTag(holder);
            } else {
                holder = (myViewHolder) view.getTag();
            }
            holder.name.setText(context.getResources().getString(items[i]));
            if (mListInfo.get(i)==""||(mListInfo.get(i).length()==0)||mListInfo.get(i)=="null") {
                holder.value.setText("未填写");
            } else {
                holder.value.setText(mListInfo.get(i));
            }
        }else{
            view=LayoutInflater.from(context).inflate(R.layout.personal_page_lv_divider_view,null);
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if(items[position]==-1){
            return TYPE_DIVIDER;
        }else {
            return TYPE_CONTENT;
        }
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class myViewHolder{
        TextView name;
        TextView value;
    }

}
