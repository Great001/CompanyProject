package com.lhc.android.great.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/8.
 */
public class CheckAccount {
    public  static boolean isNameValid =true;
    public static boolean isPasswordValid=true;

    public static boolean checkName(String str){
        String regexName="^[a-zA-Z][a-zA-z0-9]*";
        String regexNumber="^1[358][0-9]{9}$";
        Pattern  pattern=Pattern.compile(regexName);
        Matcher matcher=pattern.matcher(str);
        isNameValid =matcher.matches();
        if(!isNameValid) {
            pattern = Pattern.compile(regexNumber);
            matcher = pattern.matcher(str);
            isNameValid = matcher.matches();
        }
        return isNameValid;
    }

    public static boolean checkPassword(String str){
        isPasswordValid=true;
        int len=str.length();
        if(len<6||len>18){
            isPasswordValid=false;
        }
        return isPasswordValid;
    }

    public static boolean checkPhoneNumber(String number){
        String regexNumber="^1[358][0-9]{9}$";
        Pattern pattern=Pattern.compile(regexNumber);
        return pattern.matcher(number).matches();
    }

    public static boolean checkEmail(String email){
        String regexEmail="^([a-z0-9A-Z]+[-|+|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+)(\\.[a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?$";
         Pattern pattern = Pattern.compile(regexEmail);
        return pattern.matcher(email).matches();
    }

}
