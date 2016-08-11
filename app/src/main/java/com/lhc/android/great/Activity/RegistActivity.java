package com.lhc.android.great.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.CheckAccount;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegistActivity extends AppCompatActivity {

    private EditText mEtUsername,mEtPassword;
    private Button mBtnSignUp;
    private TextView mTvHelp;
    private ImageView mIvAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.regist_in);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mEtUsername=(EditText)findViewById(R.id.et_username);
        mEtPassword=(EditText)findViewById(R.id.et_password);
        mBtnSignUp=(Button)findViewById(R.id.btn_sign_up);
        mTvHelp=(TextView)findViewById(R.id.tv_help);
        mIvAvatar=(ImageView)findViewById(R.id.iv_avatar);


        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();

                if(TextUtils.isEmpty(username)){
                    ToastUtil.showToast(RegistActivity.this,"学号不能为空");
                }
                if(TextUtils.isEmpty(password)){
                    ToastUtil.showToast(RegistActivity.this,"密码不能为空");
                }
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)) {
                    UserProfile user = new UserProfile();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.signUp(new SaveListener<UserProfile>() {
                        @Override
                        public void done(UserProfile o, BmobException e) {
                            if (e == null) {
                                ToastUtil.showToast(RegistActivity.this, "注册成功");
                                mIvAvatar.setImageResource(R.drawable.avatar_boy);
                                NavigateUtil.navigateToLoginActivity(RegistActivity.this);
                            } else {
                                ToastUtil.showToast(RegistActivity.this, "注册失败，请检查学号是否填写正确");
                            }
                        }
                    });
                }
                else{
                    ToastUtil.showToast(RegistActivity.this,"注册信息填写不合法");
            }

            }

        });


    }
}
