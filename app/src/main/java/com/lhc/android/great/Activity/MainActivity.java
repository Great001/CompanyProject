package com.lhc.android.great.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lhc.android.great.Adapter.TabViewPagerAdapter;
import com.lhc.android.great.Adapter.mainFragmentAdapter;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.Fragment.OrderFragment;
import com.lhc.android.great.Fragment.PersonalpageFragment;
import com.lhc.android.great.Fragment.homepageFragment;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpTab;
    private RadioGroup mRgTab;
    private RadioButton mRbtnHomepage,mRbtnOrderpage,mRbtnPersonalpage;
    private Toolbar mToolbar;
    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"7ae45a73dca60578c328dd80e7a3a9ad");
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("     校园代步");
//        mToolbar.setNavigationIcon(R.drawable.arrow_back);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });


        mVpTab = (ViewPager) findViewById(R.id.vp_main);
        mRgTab = (RadioGroup) findViewById(R.id.rg_tab);
        mRbtnHomepage=(RadioButton) findViewById(R.id.rbtn_home_page);
        mRbtnOrderpage=(RadioButton)findViewById(R.id.rbtn_order_page);
        mRbtnPersonalpage=(RadioButton)findViewById(R.id.rbtn_personal_page);



       mRbtnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVpTab.setCurrentItem(0);
            }
        });

        mRbtnOrderpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVpTab.setCurrentItem(1);
            }
        });

        mRbtnPersonalpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVpTab.setCurrentItem(2);
            }
        });

        mFragments=new ArrayList<>();
        mFragments.add(new homepageFragment());
        mFragments.add(new OrderFragment());
        mFragments.add(new PersonalpageFragment());
        mainFragmentAdapter adapter=new mainFragmentAdapter(getSupportFragmentManager(),mFragments);
        mVpTab.setAdapter(adapter);
        mVpTab.setCurrentItem(0, false);

        mVpTab.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                       mRbtnHomepage.setChecked(true);
                        break;
                    case 1:
                        mRbtnOrderpage.setChecked(true);
                        break;
                    case 2:
                        mRbtnPersonalpage.setChecked(true);
                        break;
                    default:
                        break;
                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //检测、询问用户是否登录
        UserProfile user= BmobUser.getCurrentUser(UserProfile.class);
        if (user==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("你未处于登录状态，是否登录？");
            builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    NavigateUtil.navigateToLoginActivity(MainActivity.this);
                }
            });
            builder.setNegativeButton("先看看", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.about_us:
                NavigateUtil.navigateToAboutUsActivity(this);
                break;
            case R.id.saoyisao:
                showToast(R.string.saoyisao);
                break;
            case R.id.change_password:
               NavigateUtil.navigateToChangePasswordPage(MainActivity.this);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public  void showToast(int rid){
        String str=getResources().getString(rid);
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }


    public void showComfirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确认退出");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                System.exit(1);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        showComfirmDialog();
        //super.onBackPressed();
    }
}
