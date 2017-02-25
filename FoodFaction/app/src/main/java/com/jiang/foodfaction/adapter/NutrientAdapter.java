package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.EvaluateBean;
import com.jiang.foodfaction.bean.NutrientBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class NutrientAdapter extends RecyclerView.Adapter<BaseHolder>{

    private static final String TAG = "NutrientAdapter";
    private NutrientBean nutrientBean;
    private Context context;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setNutrientBean(NutrientBean nutrientBean) {
        this.nutrientBean = nutrientBean;
        notifyDataSetChanged();
    }
    public void setMore(NutrientBean bean){
        nutrientBean.addAll(bean);
        notifyDataSetChanged();
    }

    public NutrientAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context,parent, R.layout.nutrient_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.nutrient_text,nutrientBean.getTypes().get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+(nutrientBean.getTypes().size()));
        return nutrientBean!=null?nutrientBean.getTypes().size():0;

    }
}
