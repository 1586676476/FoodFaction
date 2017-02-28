package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.SearchDetailsBean;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class SearchDetailsAdapter extends RecyclerView.Adapter<BaseHolder> {
    private List<SearchDetailsBean.ItemsBean> itemsBeen;
    private Context context;

    public void setItemsBeen(List<SearchDetailsBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    public SearchDetailsAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return BaseHolder.creatRecycleViewHolder(context, parent, R.layout.search_details_item);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (itemsBeen.get(position).getHealth_light()==1){
            holder.setImage(R.id.search_recycleView_image,R.mipmap.food_light_green);
        }else if (itemsBeen.get(position).getHealth_light()==2){
            holder.setImage(R.id.search_recycleView_image,R.mipmap.food_light_yellow);
        }else {
            holder.setImage(R.id.search_recycleView_image,R.mipmap.food_light_red);
        }

        holder.setImage(R.id.search_details_recyclerView_circle, itemsBeen.get(position).getThumb_image_url());
        holder.setText(R.id.search_details_recyclerView_name, itemsBeen.get(position).getName());
        holder.setText(R.id.search_details_recyclerView_energy,
                itemsBeen.get(position).getCalory() + "千卡/" + itemsBeen.get(position).getWeight() + "克");
    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }
}
