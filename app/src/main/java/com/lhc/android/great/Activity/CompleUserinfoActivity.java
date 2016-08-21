package com.lhc.android.great.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class CompleUserinfoActivity extends AppCompatActivity {
    private EditText mEtname,mEtemail,mEtaddress;
    private EditText mEtschoolid,mEtschool,mEtmajor,mEtgrade;
    private Button mBtnCommit;
    private Spinner mSex;
    private TextView mTvphonenumber;
    private  String nickName,sex,email,commonAddress,school,major,grade;
    private int schoolId;
    private String phoneNumber,objectId;
    private UserProfile user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comple_userinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.complete_userinfo);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        mEtname=(EditText)findViewById(R.id.et_nickname);
        mEtaddress=(EditText)findViewById(R.id.et_common_address);
        mEtemail=(EditText)findViewById(R.id.et_email);
        mEtschool=(EditText)findViewById(R.id.et_school);
        mEtschoolid=(EditText)findViewById(R.id.et_shool_id);
        mEtmajor=(EditText)findViewById(R.id.et_major);
        mEtgrade=(EditText)findViewById(R.id.et_grade);
        mTvphonenumber=(TextView)findViewById(R.id.tv_phonenumber);
        mSex=(Spinner)findViewById(R.id.spinner_sex);
        mBtnCommit =(Button)findViewById(R.id.btn_commit);

       final UserProfile user=BmobUser.getCurrentUser(UserProfile.class);
        final String objectId=user.getObjectId();


        String items[]={"男","女"};
        final ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSex.setAdapter(adapter);
        mSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        sex="男";
                        break;
                    case 1:
                        sex="女";
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickName = mEtname.getText().toString();
                school = mEtschool.getText().toString();
                major = mEtmajor.getText().toString();
                grade = mEtgrade.getText().toString();
                email = mEtemail.getText().toString();
                commonAddress = mEtaddress.getText().toString();
                schoolId = Integer.valueOf(mEtschoolid.getText().toString());

                if (user != null) {
                    user.setNickname(nickName);
                    user.setSex(sex);
                    user.setAddress(commonAddress);
                    user.setSchool(school);
                    user.setMajor(major);
                    user.setGrade(grade);
                    user.setEmail(email);
                    user.setSid(schoolId);

                    user.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            ToastUtil.showToast(CompleUserinfoActivity.this, "资料更新成功");
                        }
                    });

                }else{
                    ToastUtil.showToast(CompleUserinfoActivity.this,"用户未登录");
                    NavigateUtil.navigateToLoginActivity(CompleUserinfoActivity.this);
                }
            }
        });
    }
}
