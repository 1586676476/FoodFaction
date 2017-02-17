package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiang.foodfaction.R;

import java.util.List;

public class CollectAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private Context context;

    private String[] title={"文章","食物"};

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public CollectAdapter(FragmentManager fm, Context context) {

        super(fm);
        this.context = context;
    }


     @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public String getTabTitles(int position){
        return title[position];
    }


}
