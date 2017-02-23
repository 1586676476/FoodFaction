package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.bean.GrideViewDetailsBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class PopupWindowAdapter extends RecyclerView.Adapter<BaseHolder>{

    private static final String TAG = "PopupWindowAdapter";
    private FoodClassBean.GroupBean.CategoriesBean categoriesBeen;
    private Context context;

    private OnClickListener clickListener;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

     public void setCategoriesBeen(FoodClassBean.GroupBean.CategoriesBean categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
         notifyDataSetChanged();
    }

    public PopupWindowAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.creatRecycleViewHolder(context,parent,R.layout.popupwindow_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {

        holder.setText(R.id.popupWindow_item_textView,categoriesBeen.getSub_categories().get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickListener.onItemClick(categoriesBeen.getSub_categories().get(position).getId());
                Log.e(TAG, "onClick: "+categoriesBeen.getSub_categories().get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {

        return categoriesBeen!=null?categoriesBeen.getSub_category_count():0 ;

    }
}
