package com.lhc.android.great.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lhc.android.great.Activity.AboutUsActivity;
import com.lhc.android.great.Activity.AccountManageActivity;
import com.lhc.android.great.Activity.BrowserDocuments;
import com.lhc.android.great.Activity.ChangePasswordActivity;
import com.lhc.android.great.Activity.CompleUserinfoActivity;
import com.lhc.android.great.Activity.FindPasswordActivity;
import com.lhc.android.great.Activity.LoginActivity;
import com.lhc.android.great.Activity.MainActivity;
import com.lhc.android.great.Activity.MyFileActivity;
import com.lhc.android.great.Activity.PersonalInfoActivity;
import com.lhc.android.great.Activity.PersonalIntegralActivity;
import com.lhc.android.great.Activity.PersonalWalletActivity;
import com.lhc.android.great.Activity.QuickPrint;
import com.lhc.android.great.Activity.RegistActivity;
import com.lhc.android.great.Activity.SecondBookStore;
import com.lhc.android.great.Activity.SetPasswordActivity;
import com.lhc.android.great.Activity.SetupActivity;
import com.lhc.android.great.Activity.ShopStore;
import com.lhc.android.great.Activity.ShoppingSettle;
import com.lhc.android.great.Activity.SignupActivity;
import com.lhc.android.great.Activity.UserInfo;
import com.lhc.android.great.Activity.UserInfoActivity;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/7/30.
 */
public class NavigateUtil {

    NavigateUtil mNavigateUtil;

    public static final int ADD_FILE_REQUEST=1;
    public static final int BROWSE_DOCUMENT_REQUEST=2;

    public static void navigateToPrintPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, QuickPrint.class);
        activity.startActivity(intent);
    }

    public static void navigateToShopStorePage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,ShopStore.class);
        activity.startActivity(intent);
    }

    public static void navigateToShopSecondBookStorePage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity,SecondBookStore.class);
        activity.startActivity(intent);
    }

    public static void navigateToAddFilePage(Activity activity){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        activity.startActivityForResult(intent,ADD_FILE_REQUEST);
    }

    public static void navigateToPersonalInfoPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, PersonalInfoActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToPersonalIntergralPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, PersonalIntegralActivity.class);
        activity.startActivity(intent);
    }


    public static void navigateToPersonalWalletPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, PersonalWalletActivity.class);
        activity.startActivity(intent);
    }


    public static void navigateToMyfilePage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, MyFileActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToAccountManagePage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, AccountManageActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToSetupPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, SetupActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToLoginActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToRegistActivity(Activity activity){
        Intent intent =new Intent();
        Bundle bundle=new Bundle();
        intent.setClass(activity, RegistActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToAboutUsActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, AboutUsActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToFindPasswordActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, FindPasswordActivity.class);
        activity.startActivity(intent);
    }


    public static void navigateToMainActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToUserInfoActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, UserInfo.class);
        activity.startActivity(intent);
    }


    public static void navigateToBrowseDocumentActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, BrowserDocuments.class);
        activity.startActivityForResult(intent,BROWSE_DOCUMENT_REQUEST);
    }


    public static void navigateToUserInfoPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, UserInfo.class);
        activity.startActivity(intent);
    }


    public static void navigateToCompleUserInfoPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, CompleUserinfoActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToSignupActivity(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, SignupActivity.class);
        activity.startActivity(intent);
    }

    public static void navigateToChangePasswordPage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, ChangePasswordActivity.class);
        activity.startActivity(intent);
    }


    public static void NavigateToShoppingSettlePage(Activity activity){
        Intent intent=new Intent();
        intent.setClass(activity, ShoppingSettle.class);
        activity.startActivity(intent);
    }

}
