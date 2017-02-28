package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.activity.GrideviewItemDetails;
import com.jiang.foodfaction.bean.FoodBean;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.bean.GrideViewDetailsBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class GrideViewDetailsAdapter extends RecyclerView.Adapter<BaseHolder> {
    private List<GrideViewDetailsBean.FoodsBean> foodsBeen;
    private Context context;




    public void setFoodsBeen(List<GrideViewDetailsBean.FoodsBean> foodsBeen) {
        this.foodsBeen = foodsBeen;
        notifyDataSetChanged();
    }

    public GrideViewDetailsAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context,parent, R.layout.grideview_recycleview_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,GrideviewItemDetails.class);
                intent.putExtra("code",foodsBeen.get(position).getCode());
                context.startActivity(intent);
//                context.startActivity(new Intent(context, GrideviewItemDetails.class));
            }
        });

        if (foodsBeen.get(position).getHealth_light()==1){
            holder.setImage(R.id.grideview_recycleView_image,R.mipmap.food_light_green);
        }else if (foodsBeen.get(position).getHealth_light()==2){
            holder.setImage(R.id.grideview_recycleView_image,R.mipmap.food_light_yellow);
        }else {
            holder.setImage(R.id.grideview_recycleView_image,R.mipmap.food_light_red);
        }

        holder.setText(R.id.grideview_details_recyclerView_name,foodsBeen.get(position).getName());
        holder.setText(R.id.grideview_details_recyclerView_energy,
                foodsBeen.get(position).getCalory()+"千卡/"+foodsBeen.get(position).getWeight()+"克");
        holder.setImage(R.id.grideview_details_recyclerView_circle,foodsBeen.get(position).getThumb_image_url());

    }

    @Override
    public int getItemCount() {
        return foodsBeen!=null? foodsBeen.size():0;
    }
}
