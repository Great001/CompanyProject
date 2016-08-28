package com.lhc.android.great.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lhc.android.great.Adapter.DocumentLvAdapter;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.BrowseFileUtil;
import com.lhc.android.great.Utils.ToastUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class BrowserDocuments extends AppCompatActivity {
    private static final  String SP_NAME="documents path";
;
    public static final int REQUEST_CODE=10;
    public static final int RESULT_CODE = 11;
    public static final String KEY_BROWSED_FILES="browsed files";
    private OnItemSelectListener mItemSelectListener;

    private LinearLayout mLlBack2Root;
    private ListView mLvDocuments;
    private TextView mTvComfirm;
    private ArrayList<String> selectFiles = new ArrayList<>();
    private ArrayList<String> files=new ArrayList<>();
    private boolean[] flags;
    private DocumentLvAdapter adapter;
    int count = 0;
    final Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_documents);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.browse_documents);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mTvComfirm = (TextView) findViewById(R.id.tv_selectd_file_confirm);
        mLvDocuments = (ListView) findViewById(R.id.lv_browse_documents);
        mLlBack2Root = (LinearLayout) findViewById(R.id.ll_back_to_root_dir);


        if(savedInstanceState!=null&&savedInstanceState.getStringArrayList(KEY_BROWSED_FILES)!=null) {
            files = savedInstanceState.getStringArrayList(KEY_BROWSED_FILES);
            adapter = new DocumentLvAdapter(BrowserDocuments.this, files);
            mLvDocuments.setAdapter(adapter);
        }
        else {
            int size=readDocsFromSp();
            if(size==-1) {
                final ProgressDialog dialog = new ProgressDialog(BrowserDocuments.this);
                dialog.setMessage("加载中...");
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        files = BrowseFileUtil.getDocuments();
                        SaveDocPath2SP();
                        dialog.dismiss();

                        //数组flag[]记录文档列表中的选择
                        int len = files.size();
                        flags = new boolean[len];
                        for (int i = 0; i < len; i++) {
                            flags[i] = false;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new DocumentLvAdapter(BrowserDocuments.this, files);
                                mItemSelectListener = adapter;
                                mLvDocuments.setAdapter(adapter);
                            }
                        });

                    }
                }).start();
            }
            //主要执行的代码
            else {
                //数组flag[]记录文档列表中的选择
                int len = files.size();
                flags = new boolean[len];
                for (int i = 0; i < len; i++) {
                    flags[i] = false;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new DocumentLvAdapter(BrowserDocuments.this, files);
                        mItemSelectListener = adapter;
                        mLvDocuments.setAdapter(adapter);
                    }
                });
            }
        }



            mLlBack2Root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("text/*");
                    startActivityForResult(intent, REQUEST_CODE);
                }
            });


        mLvDocuments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView ivSelect=(ImageView) mLvDocuments.getChildAt(i-mLvDocuments.getFirstVisiblePosition()).findViewById(R.id.iv_document_selected);
                if (!flags[i]) {
                    count++;
                    mItemSelectListener.onItemSelect(i);
                    ivSelect.setVisibility(View.VISIBLE);
                    flags[i] = true;
                } else {
                    count--;
                    mItemSelectListener.onItemSelectCancel(i);
                    ivSelect.setVisibility(View.GONE);
                    flags[i] = false;
                }

                if (count > 0) {
                    mTvComfirm.setVisibility(View.VISIBLE);
                } else {
                    mTvComfirm.setVisibility(View.INVISIBLE);
                }
            }
        });


        mTvComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len=files.size();
                for (int i = 0; i < len; i++) {
                    if (flags[i]) {
                        selectFiles.add(files.get(i));
                    }
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra(QuickPrint.SELECTED_FILES_KEY, selectFiles);
                setResult(RESULT_CODE, intent);
                onBackPressed();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(files!=null){
            outState.putStringArrayList(KEY_BROWSED_FILES,files);
        }
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE){
            Uri uri=data.getData();
            String path=uri.toString();
            int len=path.length();
            path=path.substring(7,len);
            selectFiles.add(path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public interface OnItemSelectListener{
        void onItemSelect(int position);
        void onItemSelectCancel(int position);
    }




    //将扫描文件的路径存入缓存文件中
    public void SaveDocPath2File(){
        String savePath=getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath()+File.separator+"docPath.txt";
        File file=new File(savePath);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            int size=files.size();
            for(int i=0;i<size;i++){
                writer.write(files.get(i)+"\n");
            }
            writer.flush();
            writer.close();
        }catch (IOException e){

        }
    }


    //从缓存文件中读取文档目录
    public void ReadDocsFromFile(){
        String savePath=getCacheDir().getPath()+File.separator+"docPath.txt";
        File file=new File(savePath);
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while(true){
                String path=reader.readLine();
                if(path==null||path==""||path=="\n"){
                    break;
                }
                files.add(path);
            }
        }catch (IOException e) {

        }

    }

    //将扫描到的文档路径存入到sharedpreference
    public void SaveDocPath2SP(){
        SharedPreferences sp=getSharedPreferences(SP_NAME,MODE_APPEND);
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


    //从sp中读取文档路径
    public  int readDocsFromSp(){
        SharedPreferences sp=getSharedPreferences(SP_NAME,MODE_APPEND);
        int size=sp.getInt("size",-1);
        for(int i=0;i<size;i++){
            String key=i+"";
            String path=sp.getString(key,"");
            files.add(path);
        }
        return size;
    }


}
