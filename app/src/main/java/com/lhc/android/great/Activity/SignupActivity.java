package com.lhc.android.great.Activity;

import android.content.Intent;
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
import com.lhc.android.great.Utils.CheckAccount;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class SignupActivity extends AppCompatActivity {

    public static final String KEY_SIGNUP_PHONE_NUMBER="signup phone number";
    public static final String KEY_USER_ID="objectid";
    private EditText mEtPnumber,mEtSmscode;
    private Button mBtnSendSms,mBtnSignup;
    private LinearLayout mLlInputsms;
    private String phoneNumber;
    private String smsCode;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_phone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sign_up_quick);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBtnSendSms = (Button) findViewById(R.id.btn_signup_send_sms);
        mBtnSignup = (Button) findViewById(R.id.btn_comfirm_to_sign_up);
        mEtPnumber = (EditText) findViewById(R.id.et_input_phone_number);
        mEtSmscode = (EditText) findViewById(R.id.et_input_sms_code);
        mLlInputsms = (LinearLayout) findViewById(R.id.ll_input_sms_code);

        mEtPnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() >= 11) {
                    mBtnSendSms.setClickable(true);
//                    mBtnSendSms.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    mBtnSendSms.setClickable(false);
//                    mBtnSendSms.setBackgroundColor(getResources().getColor(R.color.app_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBtnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                phoneNumber = mEtPnumber.getText().toString();
                boolean isVaild = CheckAccount.checkPhoneNumber(phoneNumber);
                if (isVaild) {
                    BmobSMS.requestSMSCode(phoneNumber, "Great_SMS", new QueryListener<Integer>() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            if (e == null) {
                                smsCode = String.valueOf(integer);
                                mLlInputsms.setVisibility(View.VISIBLE);
                                mBtnSendSms.setClickable(false);
                            }
                        }
                    });
                } else {
                    ToastUtil.showToast(SignupActivity.this, "手机号码格式不正确！");
                }

            }
        });


        mEtSmscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() > 0) {
                    mBtnSignup.setBackgroundColor(getResources().getColor(R.color.green));
                    mBtnSignup.setClickable(true);
                } else {
                    mBtnSignup.setClickable(false);
                    mBtnSignup.setBackgroundColor(getResources().getColor(R.color.app_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smscode = mEtSmscode.getText().toString();
//                if (smsCode.equals(smscode)) {
                    UserProfile user = new UserProfile();
                    String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
                    int len=timeStamp.length();
                    String userName = "Great" + timeStamp.substring(5,len);
                    user.setMobilePhoneNumber(phoneNumber);
//                  user.setMobilePhoneNumberVerified(true);
                    user.setPassword("123456");
                    user.setUsername(userName);
                    user.signOrLogin(smscode, new SaveListener<UserProfile>() {
                        @Override
                        public void done(UserProfile userProfile, BmobException e) {
                            if (e == null) {
                                objectId = userProfile.getObjectId();
                                //userProfile.setLogined(true);
                                ToastUtil.showToast(SignupActivity.this, "注册成功");
                                navigateToSetPasswordPage();
                                SignupActivity.this.finish();
                            }else{
                                ToastUtil.showToast(SignupActivity.this, "注册失败");
                            }
                        }
                    });

            }


    });
    }


    private void navigateToSetPasswordPage(){
        Intent intent=new Intent();
        intent.setClass(this,SetPasswordActivity.class);
//        intent.putExtra(KEY_SIGNUP_PHONE_NUMBER,phoneNumber);
        intent.putExtra(KEY_USER_ID,objectId);
        startActivity(intent);
    }

}
