package com.lhc.android.great.Bmod;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/8/21.
 */
public class ShopGoods extends BmobObject {
    String name;
    String imgUrl;
    String price;
    Integer sum;
    String property="goods";

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
