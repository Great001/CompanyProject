package com.lhc.android.great.Utils;

import android.os.Environment;

import com.lhc.android.great.Activity.BrowserDocuments;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14.
 */
public class BrowseFileUtil  {
    public static ArrayList<String> documents=new ArrayList<>();
    public static boolean hasSD;

    public static boolean hasSD(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
           hasSD=true;
        }
        else{
            hasSD=false;
        }
        return hasSD;
    }

    public static  void browseDocument(File root){
        File [] files=root.listFiles();
        int len=files.length;
        if(len>0) {
            for(int i=0;i<len;i++){
                if (!files[i].isDirectory()) {
                    String path = URLDecoder.decode(files[i].getAbsolutePath());
                    String[] str = path.split("\\.");
                    int n=str.length;
                    if(n>0) {
                        int last = n - 1;
                        if (isDocument(str[last])) {
                            documents.add(files[i].getAbsolutePath());
                        }
                    }
                }else{
                    if(files[i].getName()!="Android"&&files[i].getName()!="kugou"&&files[i].getName()!="DCIM"&&files[i].getName()!="kgmusic")
                    browseDocument(files[i]);
                }
            }
        }

    }

    public static ArrayList<String> getDocuments(){
        documents.clear();
        browseDocument(Environment.getExternalStorageDirectory());
        return documents;
    }


    private static boolean isDocument(String str){
        if(str.equals("dox")||str.equals("docx")||str.equals("ppt")||str.equals("xls")||str.equals("xlsx")) {
            return true;
        }
        else{
           return false;
        }
    }

}
