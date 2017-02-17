package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.EvaluateBean;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class EvaluateAdapter extends BaseAdapter {

    private static final String TAG = "EvaluateAdapter";
    private List<EvaluateBean.FeedsBean> feedsBeen;
    private Context context;

    public void setFeedsBeen(List<EvaluateBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }

    public EvaluateAdapter(Context context) {

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

        //调用BaseHolder中的方法
        BaseHolder holder = BaseHolder.creatListHolder(convertView, parent, R.layout.evaluate_item);
        //设置文字
        holder.setText(R.id.evaluate_item_above, feedsBeen.get(position).getSource());
        holder.setText(R.id.evaluate_item_center, feedsBeen.get(position).getTitle());
        holder.setText(R.id.evaluate_item_blow, feedsBeen.get(position).getTail());

        holder.setImage(R.id.evaluate_item_image, "http://http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10");


        return holder.getjView();
    }

}
