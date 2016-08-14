package com.lhc.android.great.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lhc.android.great.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/14.
 */
public class ToPrintFilesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> files;

    public ToPrintFilesAdapter(Context context,ArrayList<String> files){
        this.context=context;
        this.files=files;
    }


    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int i) {
        return files.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        myViewHolder holder;
        if(view==null){
            holder=new myViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.upload_files_items,null);
            holder.fileName=(TextView)view.findViewById(R.id.tv_upload_file_name);
            holder.ivComplete=(ImageView)view.findViewById(R.id.iv_upload_file_complete);
            holder.progress=(ProgressBar)view.findViewById(R.id.upload_progress);
            view.setTag(holder);
        }
        else{
            holder=(myViewHolder)view.getTag();
        }
        holder.fileName.setText(getFileName(files.get(i)));
        return view;
    }

   public  class myViewHolder{
        TextView fileName;
        ProgressBar progress;
        ImageView ivComplete;
    }

    public String getFileName(String str){
        String [] paths=str.split("/");
        return paths[paths.length-1];
    }

}
