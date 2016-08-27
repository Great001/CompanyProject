package com.lhc.android.great;

import android.app.Application;
import android.content.SharedPreferences;

import com.lhc.android.great.Activity.BrowserDocuments;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.Utils.BrowseFileUtil;
import com.lhc.android.great.Utils.NavigateUtil;

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

    @Override
    public void onCreate() {
        super.onCreate();

        if(!checkDocsExist()){
            SharedPreferences sp=getSharedPreferences("docs",MODE_PRIVATE);
            sp.edit().putBoolean("isDocReady",false).commit();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> documents = BrowseFileUtil.getDocuments();
                SaveDocPath2File(documents);
                SharedPreferences sp=getSharedPreferences("docs",MODE_PRIVATE);
                sp.edit().putBoolean("isDocReady",true).commit();

            }
        }).start();
        }
    }

    //将扫描文件的路径存入缓存文件中
    public void SaveDocPath2File(List<String> files){
        String savePath=getCacheDir().getPath()+ File.separator+"docPath.txt";
        File file=new File(savePath);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            int size=files.size();
            for(int i=0;i<size;i++){
                writer.write(files.get(0)+"\n");
            }
        }catch (IOException e){}
    }

    public boolean checkDocsExist(){
        String savePath=getCacheDir().getPath()+ File.separator+"docPath.txt";
        File file=new File(savePath);
        if(file.exists()){
            return true;
        }else{
            return false;
        }

    }




}
