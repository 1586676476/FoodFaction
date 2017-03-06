package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.greendao.Bean;

import java.util.List;

/**
 * Created by dllo on 17/3/6.
 */

public class FoodClassAboveListViewAdapter extends BaseAdapter {
    private List<Bean> list;
    private Context context;

    public void setList(List<Bean> list) {
        this.list = list;
    }

    public FoodClassAboveListViewAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder baseHolder=BaseHolder.creatListHolder(convertView,parent,R.layout.foodclass_above_details_listview_item);
        baseHolder.setText(R.id.foodclass_above_details_listview_item_text,list.get(position).getTitle());
        return null;
    }
}
