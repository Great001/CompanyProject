package com.lhc.android.great.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.great.Bmod.Goods;
import com.lhc.android.great.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class GoodsShowAdapter extends BaseAdapter {

    private Context context;
    private List<Goods> mListGoods;

    public GoodsShowAdapter (Context context, List<Goods> goods){
        this.context=context;
        this.mListGoods =goods;

    }


    @Override
    public int getCount() {
        return mListGoods.size()/2;
    }

    @Override
    public Object getItem(int i) {
        return mListGoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        myViewHolder holder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.goods_news_item,null);
            holder=new myViewHolder();
            holder.iconOne=(ImageView) view.findViewById(R.id.iv_goods_one);
            holder.iconTwo=(ImageView) view .findViewById(R.id.iv_goods_two);
            holder.nameOne=(TextView) view .findViewById(R.id.tv_goods_name_one);
            holder.nameTwo=(TextView)view.findViewById(R.id.tv_goods_name_two);
            holder.priceOne=(TextView)view.findViewById(R.id.tv_goods_price_one);
            holder.priceTwo=(TextView)view.findViewById(R.id.tv_goods_price_two);

            view.setTag(holder);
        }else{
            holder=(myViewHolder)view.getTag();
        }

        Goods goodsOne=mListGoods.get(i);
        Goods goodsTwo=mListGoods.get(i+1);
        if(goodsOne!=null){
            String name=goodsOne.getName();
            String imgUrl=goodsOne.getImgUrl();
            int price=goodsOne.getPrice();
            holder.nameOne.setText(name);
            holder.priceOne.setText(price+"");
            ImageLoader.getInstance().displayImage(imgUrl,holder.iconOne);
        }

        if(goodsTwo!=null){
            String name=goodsTwo.getName();
            String imgUrl=goodsTwo.getImgUrl();
            int price=goodsTwo.getPrice();
            holder.nameTwo.setText(name);
            holder.priceTwo.setText(price+"");
            ImageLoader.getInstance().displayImage(imgUrl,holder.iconTwo);

        }
        return view;
    }

    public class myViewHolder{
        ImageView iconOne,iconTwo;
        TextView priceOne,priceTwo;
        TextView nameOne,nameTwo;
    }

}
