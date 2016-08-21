package com.lhc.android.great.Activity;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class ShopStore extends AppCompatActivity {
    private ListView mLvGoods;
    private List<ShopGoods> mListGoods;

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


        BmobQuery<ShopGoods> query=new BmobQuery<>();
        query.addWhereEqualTo("property","goods");
        query.findObjects(new FindListener<ShopGoods>() {
            @Override
            public void done(List<ShopGoods> list, BmobException e) {
                mListGoods=list;
            }
        });
        mLvGoods=(ListView)findViewById(R.id.lv_goods);
        if(mListGoods!=null&&mListGoods.size()>0) {
            ShopgoodsAdapter adapter = new ShopgoodsAdapter(this, mListGoods);
            mLvGoods.setAdapter(adapter);
        }

        mLvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }


}
