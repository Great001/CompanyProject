package com.lhc.android.great.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.great.Bmod.ShopGoods;
import com.lhc.android.great.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/21.
 */
public class ShopgoodsAdapter extends BaseAdapter {
    private Context context;
    private List<ShopGoods> mList=new ArrayList<>();
    private OnItemAddLister onItemAddLister;

    public ShopgoodsAdapter(Context context,List<ShopGoods> list, OnItemAddLister onItemAddLister){
        this.context=context;
        this.mList=list;
        this.onItemAddLister=onItemAddLister;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos=i;
        final myViewHolder holder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.shop_goods_lv_item,null);
            holder=new myViewHolder();
            holder.name=(TextView)view.findViewById(R.id.tv_goods_name);
            holder.price=(TextView)view.findViewById(R.id.tv_goods_price);
            holder.count=(TextView)view.findViewById(R.id.tv_select_goods_num);
            holder.add=(ImageView)view.findViewById(R.id.iv_add_goods);
            holder.reduce=(ImageView)view.findViewById(R.id.iv_reduce_goods);
            holder.pic=(ImageView)view.findViewById(R.id.iv_shop_goods);

            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.reduce.setVisibility(View.VISIBLE);
                    holder.count.setVisibility(View.VISIBLE);
                    int count=Integer.valueOf(holder.count.getText().toString());
                    count++;
                    holder.count.setText(count+"");
                    if(onItemAddLister!=null){
                        onItemAddLister.onItemAdd(pos,count);
                    }
                }
            });

            holder.reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count=Integer.valueOf(holder.count.getText().toString());
                    count--;
                    if(count>0){
                        holder.count.setText(count+"");
                        if(onItemAddLister!=null){
                            onItemAddLister.onItemReduce(pos,count);
                        }
                    }else{
                        if(count==0){
                            holder.count.setText(count+"");
                            if(onItemAddLister!=null){
                                onItemAddLister.onItemReduce(pos,count);
                            }
                        }
                        holder.reduce.setVisibility(View.INVISIBLE);
                        holder.count.setVisibility(View.INVISIBLE);
                    }

                }
            });

            view.setTag(holder);
        }else{
            holder=(myViewHolder)view.getTag();
        }

        ShopGoods goods=mList.get(i);
        if(goods!=null){
            holder.name.setText(goods.getName());
            holder.price.setText(goods.getPrice()+"");
            String url=goods.getImgUrl();
            if(url!=null){
                ImageLoader.getInstance().displayImage(url,holder.pic);
            }

        }

        return view;
    }

    class myViewHolder{
        ImageView pic;
        TextView name;
        TextView price;
        ImageView add;
        ImageView reduce;
        TextView count;
    }


    public void setOnItemAddLister(OnItemAddLister  onItemAddLister){
        this.onItemAddLister=onItemAddLister;
    }

    public interface OnItemAddLister{
        public void onItemAdd(int pos,int sum);
        public void onItemReduce(int pos,int sum);

    }



}
