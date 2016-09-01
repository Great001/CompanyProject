package com.lhc.android.great;

import android.app.Application;
import android.content.SharedPreferences;

import com.lhc.android.great.Activity.BrowserDocuments;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.Utils.BrowseFileUtil;
import com.lhc.android.great.Utils.NavigateUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/8/25.
 */
public class GreatApplication extends Application {

    public static final String DOCUMENT_SP_NAME="documents path";
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration imageLoaderConfiguration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        //app一启动就检测文档路径是否有扫描保存
        SharedPreferences sp = getSharedPreferences(DOCUMENT_SP_NAME, MODE_APPEND);
        int size = sp.getInt("size", -1);
        if (size == -1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<String> files=BrowseFileUtil.getDocuments();
                    SaveDocPath2SP(files);
                }
            }).start();
        }
    }


    //将扫描到的文档路径存入到sharedpreference
    public void SaveDocPath2SP(ArrayList<String> files){
        SharedPreferences sp=getSharedPreferences(DOCUMENT_SP_NAME,MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        int size=files.size();
        editor.putInt("size",size);
        for(int i=0;i<size;i++){
            String key=i+"";
            String value=files.get(i);
            editor.putString(key,value);
        }
        editor.commit();
    }

}





