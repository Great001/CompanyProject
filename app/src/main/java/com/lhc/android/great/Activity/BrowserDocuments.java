package com.lhc.android.great.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lhc.android.great.Adapter.DocumentLvAdapter;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.BrowseFileUtil;
import com.lhc.android.great.Utils.ToastUtil;

import java.io.File;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class BrowserDocuments extends AppCompatActivity {

    public static final int RESULT_CODE = 11;

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


        if(savedInstanceState!=null&&savedInstanceState.getStringArrayList("FILES")!=null) {
            files = savedInstanceState.getStringArrayList("FILES");
            adapter = new DocumentLvAdapter(BrowserDocuments.this, files);
            mLvDocuments.setAdapter(adapter);
        }
        else {
            final ProgressDialog dialog = new ProgressDialog(BrowserDocuments.this);
            dialog.setMessage("加载中");
            dialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    files = BrowseFileUtil.getDocuments();
                    int len = files.size();
                    flags = new boolean[len];

                    for (int i = 0; i < len; i++) {
                        flags[i] = false;
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            adapter = new DocumentLvAdapter(BrowserDocuments.this, files);
                            mLvDocuments.setAdapter(adapter);
                        }
                    });
                }
            }).start();
        }

            mLlBack2Root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 3);

                }
            });


        mLvDocuments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!flags[i]) {
                    count++;
                    view.findViewById(R.id.iv_document_selected).setVisibility(View.VISIBLE);
                    flags[i] = true;
                } else {
                    count--;
                    view.findViewById(R.id.iv_document_selected).setVisibility(View.INVISIBLE);
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(files!=null){
            outState.putStringArrayList("FILES",files);
        }
        super.onSaveInstanceState(outState);
    }
}
