package com.lhc.android.great.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.great.Activity.BrowserDocuments;
import com.lhc.android.great.R;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14.
 */
public class DocumentLvAdapter extends BaseAdapter implements BrowserDocuments.OnItemSelectListener{
    private List<String> files;
    private Context context;
    private boolean flags[];


    public DocumentLvAdapter(Context context,List<String> files){
        this.context=context;
        this.files=files;
        int len=files.size();
        flags=new boolean[len];
        for(int i=0;i<len;i++){
            flags[i]=false;
        }
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
            view=LayoutInflater.from(context).inflate(R.layout.document_listview_item,null);
            holder=new myViewHolder();
            holder.filename=(TextView)view.findViewById(R.id.tv_document_name);
            holder.ivCheck=(ImageView)view.findViewById(R.id.iv_document_selected);
            holder.ivCheck.setVisibility(View.INVISIBLE);
            view.setTag(holder);
            }else{
                holder=(myViewHolder) view.getTag();
            if(flags[i]){
                holder.ivCheck.setVisibility(View.VISIBLE);
            }
            else{
                holder.ivCheck.setVisibility(View.INVISIBLE);
            }
        }
        String filename=getName(files.get(i));
        holder.filename.setText(filename);

        return view;
    }

    class myViewHolder{
        TextView filename;
        ImageView ivCheck;
    }


    public String getName(String str){
        String[] paths=str.split("/");
        int len=paths.length;
        if(len>0){
            return paths[len-1];
        }else{
            return str;
        }
    }

    @Override
    public void onItemSelect(int position) {
        flags[position]=true;
    }

    @Override
    public void onItemSelectCancel(int position) {
        flags[position]=false;
    }
}
