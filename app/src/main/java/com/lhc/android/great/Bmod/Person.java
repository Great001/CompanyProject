package com.lhc.android.great.Bmod;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Person extends BmobObject {
    String name;
    String password;
    String Address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
