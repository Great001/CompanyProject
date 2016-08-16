package com.lhc.android.great.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class SettlemenActivity extends AppCompatActivity {

    private ListView mLv2PrintFiles;
    private TextView mTvComfirmOrder;
    private List<String> urls=new ArrayList<>();
    private ArrayList<String> files=new ArrayList<>();
    private ToPrintFilesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlemen);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setTitle(R.string.settlement);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mLv2PrintFiles=(ListView)findViewById(R.id.lv_to_print_files);
        mTvComfirmOrder =(TextView)findViewById(R.id.tv_comfirm_to_order);

        Intent intent=getIntent();
        files=intent.getStringArrayListExtra(QuickPrint.SELECTED_FILES_KEY);

        if(files!=null) {
            adapter = new ToPrintFilesAdapter(this, files);
            mLv2PrintFiles.setAdapter(adapter);
        }

        mTvComfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int len=files.size();
                for( int i=0;i<len;i++){
                    final int pos=i;
                    final View itemView=mLv2PrintFiles.getChildAt(pos);
                    String path=files.get(i);
                    final String fileName=getName(path);
                    final BmobFile bfile=new BmobFile(new File(path));
                    bfile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                String url=bfile.getUrl();
                                urls.add(url+"+"+fileName);
                                itemView.findViewById(R.id.iv_upload_file_complete).setVisibility(View.VISIBLE);
                                ToastUtil.showToast(SettlemenActivity.this,"文件上传成功");
                                if(pos==len-1){
                                    UserProfile user= BmobUser.getCurrentUser(UserProfile.class);
                                    if(user!=null){
                                        String uid=user.getObjectId();
                                        if(urls==null){
                                            ToastUtil.showToast(SettlemenActivity.this,"未选择任何文件");
                                        }
                                        user.setFiles(urls);
                                        user.update(uid, new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                ToastUtil.showToast(SettlemenActivity.this,"完美上传");
                                            }
                                        });
                                    }
                                }
                            }
                            else{
                                ToastUtil.showToast(SettlemenActivity.this,"文件上传失败");
                            }
                        }

                        @Override
                        public void onProgress(Integer value) {
                               ((ProgressBar)itemView.findViewById(R.id.upload_progress)).setProgress(value);
                        }
                    });
                }
            }
        });

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
}
