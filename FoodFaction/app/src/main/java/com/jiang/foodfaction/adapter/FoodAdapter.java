package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.FoodBean;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class FoodAdapter extends BaseAdapter {
    private List<FoodBean.FeedsBean> feedsBeen;
    private Context context;

    public void setFeedsBeen(List<FoodBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }

    public FoodAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return feedsBeen != null ? feedsBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder=BaseHolder.creatListHolder(convertView,parent, R.layout.food_item);
        holder.setText(R.id.food_source,feedsBeen.get(position).getSource());
        holder.setText(R.id.food_title,feedsBeen.get(position).getTitle());
        holder.setText(R.id.food_tail,feedsBeen.get(position).getTail());

        holder.setImage(R.id.food_images,"http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10");
        return holder.getjView();
    }
}
