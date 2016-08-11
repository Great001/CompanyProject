package com.lhc.android.great.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/11.
 */
public class ToastUtil {
    public static void showToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context,int id){
        String text=context.getResources().getString(id);
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }



}
