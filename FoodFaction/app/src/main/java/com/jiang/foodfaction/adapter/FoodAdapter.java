package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.FoodBean;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class FoodAdapter extends RecyclerView.Adapter<BaseHolder> {
    private static final String TAG = "FoodAdapter";
    private List<FoodBean.FeedsBean> feedsBeen;
    private Context context;
    //声明一个接口对象
    private OnClickListener onClickListener;


    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        Log.e(TAG, "setOnClickListener: 初始化listener" );
    }

    public void setFeedsBeen(List<FoodBean.FeedsBean> feedsBeen) {
        this.feedsBeen = feedsBeen;
        notifyDataSetChanged();
    }
    public void setMore(List<FoodBean.FeedsBean> list){
        feedsBeen.addAll(list);
        notifyDataSetChanged();
    }

    public FoodAdapter(Context context) {

        this.context = context;


    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context,parent,R.layout.food_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.food_source,feedsBeen.get(position).getSource());
        holder.setText(R.id.food_title, feedsBeen.get(position).getTitle());
        holder.setText(R.id.food_tail, feedsBeen.get(position).getTail());

        holder.setImage(R.id.food_images, feedsBeen.get(position).getImages().get(0));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + position);
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedsBeen != null ? feedsBeen.size():0;
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
//        BaseHolder holder=BaseHolder.creatListHolder(convertView,parent, R.layout.food_item);
//        holder.setText(R.id.food_source,feedsBeen.get(position).getSource());
//        holder.setText(R.id.food_title,feedsBeen.get(position).getTitle());
//        holder.setText(R.id.food_tail,feedsBeen.get(position).getTail());
//
//        holder.setImage(R.id.food_images,feedsBeen.get(position).getImages().get(0));
//        return holder.getjView();
//    }
}
