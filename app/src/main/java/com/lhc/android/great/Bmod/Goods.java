package com.lhc.android.great.Bmod;

/**
 * Created by Administrator on 2016/8/29.
 */
public class Goods {

    String name;
    String introduce;
    String imgUrl;
    int price;
    int stoke;
    String property="goods";

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStoke() {
        return stoke;
    }

    public void setStoke(int stoke) {
        this.stoke = stoke;
    }
}
