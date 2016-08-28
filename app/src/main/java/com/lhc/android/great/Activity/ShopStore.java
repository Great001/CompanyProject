package com.lhc.android.great.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhc.android.great.Adapter.ShopgoodsAdapter;
import com.lhc.android.great.Bmod.ShopGoods;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class ShopStore extends AppCompatActivity implements ShopgoodsAdapter.OnItemAddLister {

    public static  String KEY_BUY_GOODS="to buy goods";
    public static String  KEY_TATAL_MOMEY="total money";
    private ListView mLvGoods;

    private List<ShopGoods> mListGoods;
    private ArrayList<ShopGoods> mBuyGoods=new ArrayList<>();
    private RelativeLayout mRlBottomBar;
    private TextView mTvSelectComplete,mTvtips;

    private int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.relex_shop);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mLvGoods=(ListView)findViewById(R.id.lv_goods);
        mRlBottomBar=(RelativeLayout)findViewById(R.id.rl_to_settle_area);
        mTvSelectComplete=(TextView)findViewById(R.id.tv_select_complete);
        mTvtips=(TextView)findViewById(R.id.tv_shopstore_tips);

        BmobQuery<ShopGoods> query=new BmobQuery<>();
        query.addWhereEqualTo("property","goods");
        query.findObjects(new FindListener<ShopGoods>() {
            @Override
            public void done(List<ShopGoods> list, BmobException e) {
                if(e==null) {
                    mListGoods = list;
                    if(mListGoods!=null&&mListGoods.size()>0) {
                        ShopgoodsAdapter adapter = new ShopgoodsAdapter(ShopStore.this, mListGoods,ShopStore.this);
                        mLvGoods.setAdapter(adapter);
                    }
                }else{
                    ToastUtil.showToast(ShopStore.this,"sorry,服务器出错啦！");
                }
            }
        });


        mLvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        mTvSelectComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int size=mListGoods.size();
                for(int i=0;i<size;i++){
                    ShopGoods goods=mListGoods.get(i);
                    if(goods.getSum()>0){
                        mBuyGoods.add(goods);
                    }
                }

                if(mBuyGoods.size()>0){
                    goToShoppingSettle();
                }else{
                    ToastUtil.showToast(ShopStore.this,"未选择商品");
                }

            }
        });

    }

    @Override
    public void onItemAdd(int pos,int sum) {
        ShopGoods goods=mListGoods.get(pos);
        goods.setSum(sum);
        int price=goods.getPrice();
        total=total+price;
        if(total>0){
            mTvSelectComplete.setBackgroundColor(getResources().getColor(R.color.green));
        }
        mTvtips.setText(total+"元");
    }

    @Override
    public void onItemReduce(int pos,int sum) {
        ShopGoods goods=mListGoods.get(pos);
        goods.setSum(sum);
        int price=goods.getPrice();
        total=total-price;
        if(total<=0){
            mTvSelectComplete.setBackgroundColor(getResources().getColor(R.color.app_gray));
        }
        mTvtips.setText(total+"元");
    }

    public void goToShoppingSettle(){
        Intent intent=new Intent();
        intent.putParcelableArrayListExtra(KEY_BUY_GOODS,mBuyGoods);
        intent.putExtra(KEY_TATAL_MOMEY,total);
        intent.setClass(this,ShoppingSettle.class);
        startActivity(intent);
    }

}
