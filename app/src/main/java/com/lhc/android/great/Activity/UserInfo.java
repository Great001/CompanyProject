package com.lhc.android.great.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.great.Adapter.UserInfoAdapter;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class UserInfo extends AppCompatActivity {

    private ListView mLvUserInfo;
    private List<String> mUserInfo=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.user_info_display);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mLvUserInfo=(ListView)findViewById(R.id.lv_user_info);

        UserProfile user=BmobUser.getCurrentUser(UserProfile.class);
        if (user != null) {
            mUserInfo.add("-1");
            String nickname=user.getNickname()+"";
            mUserInfo.add(nickname);
            String username=user.getUsername()+"";
            mUserInfo.add(username);
            String sex=user.getSex()+"";
            mUserInfo.add(sex);
            mUserInfo.add("-1");
            String phonenumber=user.getMobilePhoneNumber()+"";
            mUserInfo.add(phonenumber);
            String email=user.getEmail();
            if(email!=null) {
                mUserInfo.add(email);
            }else{
                mUserInfo.add("未填写");
            }
            mUserInfo.add("-1");
            String address=user.getAddress()+"";
            mUserInfo.add(address);
            mUserInfo.add("-1");
            String school=user.getSchool()+"";
            mUserInfo.add(school);
            String major=user.getMajor()+"";
            mUserInfo.add(major);
            String grade=user.getGrade()+"";
            mUserInfo.add(grade);
            mUserInfo.add("-1");
            UserInfoAdapter adapter=new UserInfoAdapter(this,mUserInfo);
            mLvUserInfo.setAdapter(adapter);
        }

        mLvUserInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.userinfo_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.edit:
                NavigateUtil.navigateToCompleUserInfoPage(this);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
