package com.lhc.android.great.Bmod;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/8/11.
 */
public class UserProfile extends BmobUser {
    private String nickname;
    private int sex;
    private String avatarUrl;
    private String Address;

    private int sid;
    private String school;
    private String major;
    private int grade;

    private int integral;
    private List<String> files;

    private Boolean isLogined;

    public Boolean getLogined() {
        return isLogined;
    }

    public void setLogined(Boolean logined) {
        isLogined = logined;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
