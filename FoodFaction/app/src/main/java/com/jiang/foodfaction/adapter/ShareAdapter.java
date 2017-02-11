package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiang.foodfaction.R;

import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class ShareAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private Context context;


    private String[] titles=new String[]{"首页","评测","知识","美食"};

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public ShareAdapter(FragmentManager fm, Context context) {

        super(fm);
        this.context = context;
    }

    public ShareAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public String getTabtiles(int position){
            return titles[position];

    }

}
