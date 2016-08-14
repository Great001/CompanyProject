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
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class SettlemenActivity extends AppCompatActivity {
    private ArrayList<String> files=new ArrayList<>();
    private ListView mLv2PrintFiles;
    private TextView mTvOrder;
    private List<String> urls=new ArrayList<>();
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
        mTvOrder=(TextView)findViewById(R.id.tv_comfirm_to_order);
        Intent intent=getIntent();
        files=intent.getStringArrayListExtra(QuickPrint.SELECTED_FILES_KEY);

        if(files!=null) {
            adapter = new ToPrintFilesAdapter(this, files);
            mLv2PrintFiles.setAdapter(adapter);
        }

        mTvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int len=files.size();
                for( int i=0;i<len;i++){
                    final int pos=i;
//                    View itemView=(View)(mLv2PrintFiles.getItemAtPosition(pos));
//                    final ProgressBar bar;
//                    final TextView name;
//                         bar = (ProgressBar)(itemView.findViewById(R.id.upload_progress));
//                        name = (TextView)itemView.findViewById(R.id.tv_upload_file_name);
                    String path=files.get(i);
                    final BmobFile bfile=new BmobFile(new File(path));
                    bfile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                String url=bfile.getUrl();
                                urls.add(url);
                                ToastUtil.showToast(SettlemenActivity.this,"文件上传成功");
//                                if(bar!=null&&name!=null){
//                                    bar.setVisibility(View.GONE);
//                                    name.setVisibility(View.VISIBLE);
//                                }
                                if(pos==len-1){
                                    UserProfile user= BmobUser.getCurrentUser(UserProfile.class);
                                    if(user!=null){

                                        String uid=user.getObjectId();

                                        if(urls==null){
                                            ToastUtil.showToast(SettlemenActivity.this,"空");
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
//                            ProgressBar bar=(ProgressBar)((View)(mLv2PrintFiles.getItemAtPosition(pos))).findViewById(R.id.upload_progress);
//                            if(bar!=null){
//                                bar.setVisibility(View.VISIBLE);
//                                bar.setProgress(value);
//                                name.setVisibility(View.GONE);
//                            }
                        }
                    });
                }
            }
        });

    }
}
