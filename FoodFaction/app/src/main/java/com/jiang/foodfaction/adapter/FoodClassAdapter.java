package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.R;

import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class FoodClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FoodClassBean> list;
    private Context context;

    private ArrayHolder arrayHolder;

    public void setList(List<FoodClassBean> list) {
        this.list = list;
    }

    public FoodClassAdapter(Context context) {

        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.foodclass_item,parent,false);

        arrayHolder=new ArrayHolder(view);
        return arrayHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        arrayHolder= (ArrayHolder) holder;

        arrayHolder.textView.setText(list.get(position).getGroup_count());
        //设置图片
        Glide.with(context).load(
                list.get(position).getGroup().get(position).getCategories().get(position).getImage_url());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ArrayHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ArrayHolder(View itemView) {
             super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.foodclass_item_image);
            textView= (TextView) itemView.findViewById(R.id.foodclass_item_text);
        }
    }
}
