package com.lhc.android.great.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.great.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/14.
 */
public class AddedfilesAdapter  extends BaseAdapter {

    private List<String> files;
    private Context context;
    private OnDeleteLister onDeleteLister;

    public AddedfilesAdapter(Context context,List<String> files,OnDeleteLister lister){
        this.context=context;
        this.files=files;
        this.onDeleteLister=lister;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        myViewHolder holder;
        if(view==null){
            holder=new myViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.added_files_lv_items,null);
            holder.name=(TextView)view.findViewById(R.id.tv_added_document_name);
            holder.ivDelete=(ImageView)view.findViewById(R.id.iv_document_delete);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onDeleteLister!=null){
                        onDeleteLister.onDelete(i);
                    }

                }
            });
            view.setTag(holder);
        }else{
            holder=(myViewHolder)view.getTag();
        }
        String str=getFileName(files.get(i));
        holder.name.setText(str);
        return view;
    }

    class myViewHolder{

        TextView name;
        ImageView ivDelete;
    }

    public interface OnDeleteLister{
        public void onDelete(int pos);
    }

    public String getFileName(String str){
        String [] paths=str.split("/");
        return paths[paths.length-1];
    }


}
