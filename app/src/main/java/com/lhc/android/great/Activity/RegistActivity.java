package com.lhc.android.great.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lhc.android.great.R;

public class RegistActivity extends AppCompatActivity {


    private EditText mEtUsername,mEtPassword;
    private Button mBtnSignUp;
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

    }
}
