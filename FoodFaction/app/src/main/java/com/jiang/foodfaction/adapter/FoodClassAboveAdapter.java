package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.FoodClassAboveBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class FoodClassAboveAdapter extends RecyclerView.Adapter<BaseHolder> {

    private FoodClassAboveBean above;
    private Context context;

    private OnClickListener onClickListener;

    private static final String TAG = "FoodClassAboveAdapter";

    public void setAbove(FoodClassAboveBean above) {
        this.above = above;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public FoodClassAboveAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context,parent, R.layout.foodclass_above_details_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.foodclass_above_details_item_text,above.getKeywords().get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+(above==null));
        return above!=null?above.getKeyword_count():0;

    }
}
