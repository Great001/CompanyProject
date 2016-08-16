package com.lhc.android.great.Activity;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UpdateListener;

public class ShopStore extends AppCompatActivity {

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

        /*
        TextView result=(TextView) findViewById(R.id.result);
        UserProfile user= BmobUser.getCurrentUser(UserProfile.class);
        String str=user.getObjectId();
        String urls="";

        List<String> list=user.getFiles();
        if(list==null){
            ToastUtil.showToast(ShopStore.this,"空");
        }else{
            if(list.size()<=0){
                ToastUtil.showToast(ShopStore.this,"空");
            }else{
                for(String url:list) {
                    urls=urls+url;
                    result.setText(urls);
                }
                }
        }



    }*/
    }


}
