package com.lhc.android.great.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.CheckAccount;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class SetPasswordActivity extends AppCompatActivity {

    public static final String KEY_USER_ID="objectid";
    private EditText mEtInput,mEtComfirm;
    private Button mBtnComfirm;
//    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.set_password);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        Intent intent=getIntent();
//        objectId=intent.getStringExtra(SignupActivity.KEY_USER_ID);

        mEtComfirm=(EditText)findViewById(R.id.et_comfirm_password);
        mEtInput=(EditText)findViewById(R.id.et_input_password);
        mBtnComfirm=(Button)findViewById(R.id.btn_password_commit);
        mBtnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String inputPwd = mEtInput.getText().toString();
                boolean isPwdValid = CheckAccount.checkPassword(inputPwd);
                if (isPwdValid) {
                    String comfirmPwd = mEtComfirm.getText().toString();
                    if (inputPwd.equals(comfirmPwd)) {
                        final UserProfile user=BmobUser.getCurrentUser(UserProfile.class);
                        user.updateCurrentUserPassword("123456", inputPwd, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null) {
                                    ToastUtil.showToast(SetPasswordActivity.this, "密码设置成功");
                                    NavigateUtil.navigateToLoginActivity(SetPasswordActivity.this);
                                    SetPasswordActivity.this.finish();
                                }else{
                                    ToastUtil.showToast(SetPasswordActivity.this,"密码设置失败");
                                }
                            }
                        });
                    } else {
                        ToastUtil.showToast(SetPasswordActivity.this, "前后密码不匹配");
                    }
                } else {
                    ToastUtil.showToast(SetPasswordActivity.this, "密码需要6-18位");
                }
            }
        });
    }


    /*
    private void navigateToCompleteUserInfoPage(){
        Intent intent=new Intent();
        intent.setClass(this,CompleUserinfoActivity.class);
        intent.putExtra(KEY_USER_ID,objectId);
        startActivity(intent);
    }*/
}
