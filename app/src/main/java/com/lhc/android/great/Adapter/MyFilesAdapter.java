package com.lhc.android.great.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lhc.android.great.R;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public class MyFilesAdapter extends BaseAdapter {
    private Context context;
    private List<String> files;

    public MyFilesAdapter(Context context,List<String> files){
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
            view= LayoutInflater.from(context).inflate(R.layout.download_files_items,null);
            holder.fileName=(TextView)view.findViewById(R.id.tv_download_file_name);
            holder.ivComplete=(ImageView)view.findViewById(R.id.iv_download_file_complete);
            holder.progress=(ProgressBar)view.findViewById(R.id.download_progress);
            view.setTag(holder);
        }
        else{
            holder=(myViewHolder)view.getTag();
        }
        holder.fileName.setText(files.get(i));
        return view;
    }

    public  class myViewHolder{
        TextView fileName;
        ProgressBar progress;
        ImageView ivComplete;
    }

}
