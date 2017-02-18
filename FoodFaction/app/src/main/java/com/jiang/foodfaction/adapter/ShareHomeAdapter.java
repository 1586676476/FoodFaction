package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/13.
 */

public class ShareHomeAdapter extends RecyclerView.Adapter<BaseHolder> {

    private List<ShareHomeBean.FeedsBean> feedsBeen;
    private Context context;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setFeedsBeen(List<ShareHomeBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }

    public ShareHomeAdapter(Context context) {

        this.context = context;
    }

    public void setMore(List<ShareHomeBean.FeedsBean> list){
        feedsBeen.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context,parent,R.layout.home_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.home_publisher,feedsBeen.get(position).getPublisher());
        holder.setText(R.id.home_title,feedsBeen.get(position).getTitle());
        holder.setText(R.id.home_like_ct, String.valueOf(feedsBeen.get(position).getLike_ct()));

        holder.setImage(R.id.home_card_image,feedsBeen.get(position).getCard_image());
        holder.setImage(R.id.home_publisher_avatar,feedsBeen.get(position).getPublisher_avatar());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedsBeen!=null ? feedsBeen.size():0 ;
    }
}
