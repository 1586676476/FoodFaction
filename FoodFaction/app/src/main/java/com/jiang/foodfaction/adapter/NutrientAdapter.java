package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.EvaluateBean;
import com.jiang.foodfaction.bean.NutrientBean;

import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class NutrientAdapter extends RecyclerView.Adapter<BaseHolder>{

    private NutrientBean nutrientBean;
    private Context context;

    public void setNutrientBean(NutrientBean nutrientBean) {
        this.nutrientBean = nutrientBean;
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
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setText(R.id.nutrient_text,nutrientBean.getTypes().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return nutrientBean!=null?nutrientBean.getTypes().size():0;
    }
}
