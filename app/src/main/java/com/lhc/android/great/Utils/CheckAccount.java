package com.lhc.android.great.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/8.
 */
public class CheckAccount {
    public  static boolean isValid=true;
    public static boolean isAccountValid(String str){
        String regexName="^[a-zA-Z][a-zA-z0-9]*";
        String regexNumber="^1[358][0-9]{9}$";
        Pattern  pattern=Pattern.compile(regexName);
        Matcher matcher=pattern.matcher(str);
        isValid=matcher.matches();
        if(!isValid) {
            pattern = Pattern.compile(regexNumber);
            matcher = pattern.matcher(str);
            isValid = matcher.matches();
        }
        return isValid;
    }

}
