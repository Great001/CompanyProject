package com.lhc.android.great.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lhc.android.great.Adapter.AddedfilesAdapter;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class QuickPrint extends AppCompatActivity implements AddedfilesAdapter.OnDeleteLister {

    public static final String SELECTED_FILES_KEY="selected_files";
    private LinearLayout mLlAddFile;
    private ListView mLvAddFiles;
    private TextView mTvComfirm;
    private ArrayList<String> files=new ArrayList<>();
    private AddedfilesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_print);

        if(savedInstanceState!=null&&savedInstanceState.getStringArrayList(SELECTED_FILES_KEY)!=null){
            files=savedInstanceState.getStringArrayList(SELECTED_FILES_KEY);
        }

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setTitle(R.string.quick_print);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTvComfirm=(TextView)findViewById(R.id.tv_comfirm_to_order);
        mLlAddFile=(LinearLayout)findViewById(R.id.ll_add_files);
        mLvAddFiles=(ListView)findViewById(R.id.lv_added_files);

        adapter=new AddedfilesAdapter(QuickPrint.this,files,this);
        mLvAddFiles.setAdapter(adapter);

        mLlAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToBrowseDocumentActivity(QuickPrint.this);
            }
        });

        mTvComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size=files.size();
                if(size>0) {
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra(SELECTED_FILES_KEY, files);
                    intent.setClass(QuickPrint.this, SettlemenActivity.class);
                    startActivity(intent);
                }else{
                    ToastUtil.showToast(QuickPrint.this,"您未选择任何文件");
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NavigateUtil.BROWSE_DOCUMENT_REQUEST&&resultCode==BrowserDocuments.RESULT_CODE) {
            files.addAll(data.getStringArrayListExtra(SELECTED_FILES_KEY));
            if(files!=null) {
                adapter = new AddedfilesAdapter(QuickPrint.this, files, this);
                mLvAddFiles.setAdapter(adapter);
                mTvComfirm.setBackgroundColor(getResources().getColor(R.color.green));
                mTvComfirm.setClickable(true);
            }
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            files=savedInstanceState.getStringArrayList(SELECTED_FILES_KEY);
        }
    }

    @Override
    public void onDelete(int pos) {
        files.remove(pos);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(files!=null) {
            outState.putStringArrayList(SELECTED_FILES_KEY, files);
        }
        super.onSaveInstanceState(outState);
    }
}
