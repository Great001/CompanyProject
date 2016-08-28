package com.lhc.android.great.Bmod;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/8/21.
 */
public class ShopGoods extends BmobObject implements Parcelable {
    String name;  //商品名称
    String introduce;  //商品介绍
    String imgUrl;   //商品图
    int price;  //单价
    int sum;  //购买数量
    int stoke;//库存
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Creator<ShopGoods> getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(Creator<ShopGoods> CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getStoke() {
        return stoke;
    }

    public void setStoke(int stoke) {
        this.stoke = stoke;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(introduce);
        parcel.writeString(imgUrl);
        parcel.writeInt(price);
        parcel.writeInt(stoke);
        parcel.writeInt(sum);
        parcel.writeString(property);

    }

    Parcelable.Creator<ShopGoods>  CREATOR=new Creator<ShopGoods>() {
        @Override
        public ShopGoods createFromParcel(Parcel parcel) {
            ShopGoods goods=new ShopGoods();
            goods.name=parcel.readString();
            goods.introduce=parcel.readString();
            goods.imgUrl=parcel.readString();
            goods.price=parcel.readInt();
            goods.stoke=parcel.readInt();
            goods.sum=parcel.readInt();
            goods.property=parcel.readString();
            return goods;
        }

        @Override
        public ShopGoods[] newArray(int i) {
            return new ShopGoods[i];
        }
    };
}
