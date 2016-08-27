package com.lhc.android.great.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.CheckAccount;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText mEtOriginPwd,mEtNewPwd,mEtComfirmPwd;
    private Button mBtnComfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.change_password);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mEtComfirmPwd=(EditText)findViewById(R.id.et_comfirm_new_password);
        mEtNewPwd=(EditText)findViewById(R.id.et_new_password);
        mEtOriginPwd=(EditText)findViewById(R.id.et_origin_pwd);
        mBtnComfirm=(Button)findViewById(R.id.btn_comfirm_to_change_password);


        mBtnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfile user= BmobUser.getCurrentUser(UserProfile.class);
                if(user!=null){
                    final String originPwd=mEtOriginPwd.getText().toString();
                    final String newPwd=mEtNewPwd.getText().toString();
                    final String comfirmPwd=mEtComfirmPwd.getText().toString();

                    if(CheckAccount.checkPassword(newPwd)){
                        if(newPwd==comfirmPwd){
                            user.updateCurrentUserPassword(originPwd, newPwd, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null){
                                        ToastUtil.showToast(ChangePasswordActivity.this,R.string.change_password_success);
                                        onBackPressed();
                                    }else{
                                        ToastUtil.showToast(ChangePasswordActivity.this,R.string.change_password_failed);
                                    }
                                }
                            });

                        }else{
                            ToastUtil.showToast(ChangePasswordActivity.this,R.string.password_not_the_same);
                        }

                    }else{
                        ToastUtil.showToast(ChangePasswordActivity.this,R.string.password_need_bits);
                    }

                }else{
                    ToastUtil.showToast(ChangePasswordActivity.this,R.string.user_not_login);
                }
            }
        });


    }
}
