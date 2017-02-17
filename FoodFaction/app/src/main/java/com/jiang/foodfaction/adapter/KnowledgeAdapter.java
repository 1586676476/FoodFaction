package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.KnowledgeBean;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class KnowledgeAdapter extends BaseAdapter {
    private List<KnowledgeBean.FeedsBean> feedsBeen;
    private Context context;

    public void setFeedsBeen(List<KnowledgeBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }

    public KnowledgeAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return feedsBeen!= null ? feedsBeen.size():0 ;
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
        BaseHolder baseHolder=BaseHolder.creatListHolder(convertView,parent, R.layout.knowledge_item);
        baseHolder.setText(R.id.knowledge_source,feedsBeen.get(position).getSource());
        baseHolder.setText(R.id.knowledge_title,feedsBeen.get(position).getTitle());
        baseHolder.setText(R.id.knowledge_tail,feedsBeen.get(position).getTail());

        baseHolder.setImage(R.id.knowledge_images,"http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10");


        return baseHolder.getjView();
    }
}
