package com.lhc.android.great.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class FindPasswordActivity extends AppCompatActivity {
    private EditText mEtPhoneNumber,mEtSmsCode,mEtNewPwd,mEtComfirmPwd;
    private Button mBtnSendSms,mBtnComfirm;
    private LinearLayout mLlVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.find_back_password);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mEtPhoneNumber=(EditText)findViewById(R.id.et_registed_phone_number);
        mBtnSendSms=(Button)findViewById(R.id.btn_findpwd_send_sms);
        mEtSmsCode=(EditText)findViewById(R.id.et_input_sms_code);
        mEtNewPwd=(EditText)findViewById(R.id.et_new_password);
        mEtComfirmPwd=(EditText)findViewById(R.id.et_comfirm_new_password);
        mLlVerify=(LinearLayout)findViewById(R.id.ll_reset_password);
        mBtnComfirm=(Button)findViewById(R.id.btn_comfirm_to_reset_password);

        mEtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence!=null&&charSequence.length()>=11){
                    mBtnSendSms.setClickable(true);
                }else{
                    mBtnSendSms.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mBtnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber=mEtPhoneNumber.getText().toString();
                BmobSMS.requestSMSCode(phoneNumber, "Great_SMS", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if(e==null){
                            mLlVerify.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });


        mBtnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smsCode=mEtSmsCode.getText().toString();
                String newPwd=mEtNewPwd.getText().toString();
                String comfirmNewPwd=mEtComfirmPwd.getText().toString();

                if(newPwd.equals(comfirmNewPwd)){
                    UserProfile user=new UserProfile();
                    user.resetPasswordBySMSCode(smsCode, newPwd, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                ToastUtil.showToast(FindPasswordActivity.this,"密码找回成功");
                                FindPasswordActivity.this.finish();
                            }else{
                                ToastUtil.showToast(FindPasswordActivity.this,"密码找回失败");
                            }
                        }
                    });

                }else{
                    ToastUtil.showToast(FindPasswordActivity.this,"前后密码不匹配");
                }
            }
        });
    }
}
