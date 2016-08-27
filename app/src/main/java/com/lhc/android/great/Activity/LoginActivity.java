package com.lhc.android.great.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.CheckAccount;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {

    private EditText mEtAccount,mEtPwd;
    private Button mBtnCancel,mBtnConfirm;
    private TextView mTvForgetPwd,mTvRegistNow;
    private ImageView mIvAvatar;
    private UserProfile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.log_in);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mEtAccount =(EditText)findViewById(R.id.et_login_account);
        mEtPwd=(EditText)findViewById(R.id.et_login_password);
        mBtnCancel=(Button)findViewById(R.id.btn_login_cancel);
        mBtnConfirm=(Button)findViewById(R.id.btn_login_confirm);
        mTvForgetPwd=(TextView)findViewById(R.id.tv_forget_password);
        mTvRegistNow=(TextView)findViewById(R.id.tv_regist_now);
        mIvAvatar=(ImageView)findViewById(R.id.iv_user_avatar);

        user=BmobUser.getCurrentUser(UserProfile.class);
        if(user!=null){
            mEtAccount.setText(user.getMobilePhoneNumber());
            if(user.getSex()==null){
                mIvAvatar.setImageResource(R.drawable.header_default);
            }else if(user.getSex()=="男"){
                mIvAvatar.setImageResource(R.drawable.header_boy);
            }else{
                mIvAvatar.setImageResource(R.drawable.header_girl);
            }
        }else{
            user=new UserProfile();
        }

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account= mEtAccount.getText().toString();
                String pwd=mEtPwd.getText().toString();

                if(TextUtils.isEmpty(account)){
                    ToastUtil.showToast(LoginActivity.this,R.string.tips_account_can_not_empty);
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showToast(LoginActivity.this,R.string.tips_password_can_not_empty);
                }

                if(!TextUtils.isEmpty(account)&&!TextUtils.isEmpty(pwd)){
                    /*
                    //普通的用户注册
                        user.setUsername(phoneNumber);
                        user.setPassword(pwd);
                        user.login(new SaveListener<UserProfile>() {
                            @Override
                            public void done(UserProfile userProfile, BmobException e) {
                                if (e == null) {
                                ToastUtil.showToast(LoginActivity.this, "登录成功");
                                    userProfile.setLogined(true);
                                    mIvAvatar.setImageResource(userProfile.getSex()=="男"?R.drawable.avatar_boy:R.drawable.avatar_girl);
                                    onBackPressed();

                                } else {
                                }
                            }

                        });*/

                    user.loginByAccount(account, pwd, new LogInListener<UserProfile>() {
                        @Override
                        public void done(UserProfile userProfile, BmobException e) {
                            if(e==null) {
                                ToastUtil.showToast(LoginActivity.this, "登录成功");
                                userProfile.setLogined(true);
                                String sex=userProfile.getSex();
                                if(sex==null){
                                    mIvAvatar.setImageResource(R.drawable.header_default);
                                }else if(sex=="男"){
                                    mIvAvatar.setImageResource(R.drawable.header_boy);
                                }else{
                                    mIvAvatar.setImageResource(R.drawable.header_girl);
                                }
                                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle("立即完善个人资料?");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                NavigateUtil.navigateToCompleUserInfoPage(LoginActivity.this);

                                            }
                                        }
                                );
                                builder.setNegativeButton("不了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        onBackPressed();
                                    }
                                });

                                AlertDialog dialog=builder.create();
                                dialog.show();
                            }
                        }
                    });

            }}
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtAccount.setText("");
                mEtPwd.setText("");
            }
        });


        mTvRegistNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToSignupActivity(LoginActivity.this);
            }
        });

        mTvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToFindPasswordActivity(LoginActivity.this);
            }
        });
    }
}
