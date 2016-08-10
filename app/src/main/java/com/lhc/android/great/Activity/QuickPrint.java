package com.lhc.android.great.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class QuickPrint extends AppCompatActivity {

    private ImageView mIvAddFile,mIvAddFileComplete;
    private TextView mTvResult;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_print);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setTitle(R.string.quick_print);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mIvAddFile=(ImageView)findViewById(R.id.iv_add_file);
        mTvResult=(TextView)findViewById(R.id.tv_qp_result);
        mIvAddFileComplete=(ImageView)findViewById(R.id.iv_add_file_complete);
        mProgressBar=(ProgressBar)findViewById(R.id.progress);
        mIvAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToAddFilePage(QuickPrint.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //JSONObject jsonObject = new JSONObject();
        //String path=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"lhc.docx";
        //StringBuilder strBuilder = new StringBuilder();
        if (requestCode == NavigateUtil.ADD_FILE_REQUEST) {
            if (data != null) {
                Uri uri = data.getData();
                //String str = Uri.decode(uri.toString());
                mTvResult.setText(uri.toString());
                mIvAddFileComplete.setVisibility(View.VISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(100);
                    }
                },2000);

            }

        }

    }
}
