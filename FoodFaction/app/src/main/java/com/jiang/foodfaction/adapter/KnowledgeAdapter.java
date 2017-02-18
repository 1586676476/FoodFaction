package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.KnowledgeBean;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class KnowledgeAdapter extends RecyclerView.Adapter<BaseHolder> {
    private List<KnowledgeBean.FeedsBean> feedsBeen;
    private Context context;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setFeedsBeen(List<KnowledgeBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }

    public void setMore(List<KnowledgeBean.FeedsBean> list){
        feedsBeen.addAll(list);
        notifyDataSetChanged();
    }

    public KnowledgeAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context, parent, R.layout.knowledge_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.knowledge_source, feedsBeen.get(position).getSource());
        holder.setText(R.id.knowledge_title, feedsBeen.get(position).getTitle());
        holder.setText(R.id.knowledge_tail, feedsBeen.get(position).getTail());

        holder.setImage(R.id.knowledge_images, feedsBeen.get(position).getImages().get(0));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedsBeen != null ? feedsBeen.size() : 0;
    }
//
//    @Override
//    public int getCount() {
//        return feedsBeen!= null ? feedsBeen.size():0 ;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        BaseHolder baseHolder=BaseHolder.creatListHolder(convertView,parent, R.layout.knowledge_item);
//        baseHolder.setText(R.id.knowledge_source,feedsBeen.get(position).getSource());
//        baseHolder.setText(R.id.knowledge_title,feedsBeen.get(position).getTitle());
//        baseHolder.setText(R.id.knowledge_tail,feedsBeen.get(position).getTail());
//
//        baseHolder.setImage(R.id.knowledge_images,feedsBeen.get(position).getImages().get(0));
//
//
//        return baseHolder.getjView();
//    }
}
