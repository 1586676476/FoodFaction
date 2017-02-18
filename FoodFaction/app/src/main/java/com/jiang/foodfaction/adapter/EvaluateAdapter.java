package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class EvaluateAdapter extends RecyclerView.Adapter<BaseHolder> {

    private static final String TAG = "EvaluateAdapter";
    private List<EvaluateBean.FeedsBean> feedsBeen;
    private Context context;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setFeedsBeen(List<EvaluateBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }
    public void setMore(List<EvaluateBean.FeedsBean> list){
        feedsBeen.addAll(list);
        notifyDataSetChanged();
    }

    public EvaluateAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context, parent, R.layout.evaluate_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.evaluate_item_above, feedsBeen.get(position).getSource());
        holder.setText(R.id.evaluate_item_center, feedsBeen.get(position).getTitle());
        holder.setText(R.id.evaluate_item_blow,feedsBeen.get(position).getTail());

        holder.setImage(R.id.evaluate_item_image,feedsBeen.get(position).getBackground());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedsBeen!=null ? feedsBeen.size():0;
    }

//    @Override
//    public int getCount() {
//        return feedsBeen != null ? feedsBeen.size() : 0;
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
//
//        //调用BaseHolder中的方法
//        BaseHolder holder = BaseHolder.creatListHolder(convertView, parent, R.layout.evaluate_item);
//        //设置文字
//        holder.setText(R.id.evaluate_item_above, feedsBeen.get(position).getSource());
//        holder.setText(R.id.evaluate_item_center, feedsBeen.get(position).getTitle());
//        holder.setText(R.id.evaluate_item_blow, feedsBeen.get(position).getTail());
//
//        holder.setImage(R.id.evaluate_item_image, feedsBeen.get(position).getBackground());
//
//
//        return holder.getjView();
//    }

}
