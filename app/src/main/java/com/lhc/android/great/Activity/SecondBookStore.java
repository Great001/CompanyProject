package com.lhc.android.great.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lhc.android.great.Bmod.ShopGoods;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SecondBookStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_book_store);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.second_hand_book_store);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ShopGoods goods=new ShopGoods();
        goods.setName("面包");
        goods.setPrice("5元");
        goods.setSum(100);
        goods.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(SecondBookStore.this,"保存成功");
                }else{
                    ToastUtil.showToast(SecondBookStore.this,"保存失败");
                }
            }
        });
    }
}
