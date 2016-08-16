package com.lhc.android.great.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.great.Adapter.MyFilesAdapter;
import com.lhc.android.great.Adapter.ToPrintFilesAdapter;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

public class MyFileActivity extends AppCompatActivity {
    private ListView mLvMyfiles;
    private List<String> myfiles=new ArrayList<>();
    private List<String> fileUrls=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.myfiles);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mLvMyfiles = (ListView) findViewById(R.id.lv_myfiles);

        final UserProfile user = BmobUser.getCurrentUser(UserProfile.class);
        getUrls(user.getFiles());
        getNames(user.getFiles());
        if (myfiles != null) {
            MyFilesAdapter adapter = new MyFilesAdapter(this, myfiles);
            mLvMyfiles.setAdapter(adapter);
        }

        mLvMyfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String root = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(root + File.separator + "校园代步");
                if(!file.exists()){
                    file.mkdirs();
                }
                BmobFile bfile = new BmobFile(myfiles.get(i), "", fileUrls.get(i));
                bfile.download(file, new DownloadFileListener() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            ToastUtil.showToast(MyFileActivity.this,"下载成功");
                        }
                    }

                    @Override
                    public void onProgress(Integer integer, long l) {

                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void getUrls(List<String> list){
        int len=list.size();
        for(int i=0;i<len;i++){
            String parts[]=list.get(i).split("\\+");
            fileUrls.add(parts[0]);
        }
    }

    public void getNames(List<String> list){
        int len=list.size();
        for(int i=0;i<len;i++){
            String parts[]=list.get(i).split("\\+");
            myfiles.add(parts[1]);
        }
    }



}