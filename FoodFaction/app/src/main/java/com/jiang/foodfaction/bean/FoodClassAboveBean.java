package com.jiang.foodfaction.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class FoodClassAboveBean implements Parcelable{


    /**
     * keyword_count : 10
     * keywords : ["苹果","香蕉","馒头","红薯","酸奶","米饭","玉米","鸡蛋","豆浆","草莓"]
     */

    private int keyword_count;
    private List<String> keywords;

    protected FoodClassAboveBean(Parcel in) {
        keyword_count = in.readInt();
        keywords = in.createStringArrayList();
    }

    public static final Creator<FoodClassAboveBean> CREATOR = new Creator<FoodClassAboveBean>() {
        @Override
        public FoodClassAboveBean createFromParcel(Parcel in) {
            return new FoodClassAboveBean(in);
        }

        @Override
        public FoodClassAboveBean[] newArray(int size) {
            return new FoodClassAboveBean[size];
        }
    };

    public int getKeyword_count() {
        return keyword_count;
    }

    public void setKeyword_count(int keyword_count) {
        this.keyword_count = keyword_count;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(keyword_count);
        dest.writeStringList(keywords);
    }
}
