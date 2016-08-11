package com.lhc.android.great.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lhc.android.great.Adapter.PersonalPageLvAdapter;
import com.lhc.android.great.Bmod.UserProfile;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/8/7.
 */
public class PersonalpageFragment extends Fragment {

    private ListView mLvPp;
    private ImageView mIvAvatar;
    private Button mBtnLogout;
    private TextView mTvLogin,mTvRegist;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.personal_page,null);
        mLvPp=(ListView)view.findViewById(R.id.lv_user_personal);
        mBtnLogout=(Button)view.findViewById(R.id.btn_log_out);
        mIvAvatar=(ImageView)view.findViewById(R.id.iv_peosonal_page_avatar_);
        mTvLogin=(TextView)view.findViewById(R.id.tv_log_in);
        mTvRegist=(TextView)view.findViewById(R.id.tv_registe_in);

        mLvPp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 1:
                        NavigateUtil.navigateToPersonalInfoPage(getActivity());
                        break;
                    case 2:
                        NavigateUtil.navigateToPersonalIntergralPage(getActivity());
                        break;
                    case 3:
                        NavigateUtil.navigateToPersonalWalletPage(getActivity());
                        break;
                    case 5:
                        NavigateUtil.navigateToMyfilePage(getActivity());
                        break;
                    case 7:
                        NavigateUtil.navigateToAccountManagePage(getActivity());
                        break;
                    case 8:
                        NavigateUtil.navigateToSetupPage(getActivity());
                        break;
                    default:break;
                }
            }
        });

        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToLoginActivity(getActivity());
            }
        });

        mTvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToRegistActivity(getActivity());
            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showComfirmDialog();
            }
        });

        int [] items={-1,R.string.personal_info,R.string.personal_integral,R.string.personal_wallet,-1,R.string.myfiles,-1,R.string.account_manage, R.string.setting};
        int [] icons={-1,R.drawable.header_big_default,R.drawable.icon_integral,R.drawable.wallet,-1,R.drawable.file_folder,-1,R.drawable.account,R.drawable.setup_01};
        PersonalPageLvAdapter adapter=new PersonalPageLvAdapter(getActivity(),items,icons);
        if(mLvPp!=null) {
            mLvPp.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
    }

    private void showComfirmDialog(){
        final AlertDialog dialog;
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("确认退出？");

        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserProfile user=BmobUser.getCurrentUser(UserProfile.class);
                user.logOut();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog=builder.create();
        dialog.show();
    }

}
