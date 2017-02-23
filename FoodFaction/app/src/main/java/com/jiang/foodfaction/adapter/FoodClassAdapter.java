package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.activity.GrideViewDetails;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class FoodClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FoodClassAdapter";
    //声明一个Bean,这个Bean装有所有想要的元素
    private FoodClassBean foodClassBean;
    private Context context;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private String[] titles = {"食物分类", "热门品牌", "连锁餐饮"};

    public void setFoodClassBean(FoodClassBean foodClassBean) {
        this.foodClassBean = foodClassBean;
        notifyDataSetChanged();
    }

    public FoodClassAdapter(Context context) {

        this.context = context;
    }

    public final int ABOVE = 0;
    public final int CENTER = 1;
    public final int BLOW = 2;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View aboveHolder = LayoutInflater.from(context).inflate(R.layout.foodclass_above, parent, false);
                holder = new AboveHolder(aboveHolder);
                break;
            case 1:
                View centerHoler = LayoutInflater.from(context).inflate(R.layout.foodclass_center, parent, false);
                holder = new CenterHolder(centerHoler);
                break;
            case 2:
                View blowHolder = LayoutInflater.from(context).inflate(R.layout.foodclass_blow, parent, false);
                holder = new BlowHolder(blowHolder);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case 0:

                break;
            case 1:

                break;
            case 2:


                ((BlowHolder) holder).textView.setText(titles[position - 2]);

                final GridViewAdapter adapter = new GridViewAdapter(context);
                adapter.setGroupBeen(foodClassBean.getGroup().get(position - 2));
                //从网上拉取数据的数量是当前总数—2
                adapter.setType(position - 2);
                ((BlowHolder) holder).gridView.setAdapter(adapter);
                //设置点击事件
                ((BlowHolder) holder).gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, GrideViewDetails.class);
                        //判断我们传值是哪个类型中的kind,id,pop
                        intent.putExtra("kind", foodClassBean.getGroup().get(adapter.getType()).getKind());

                        intent.putExtra("id", foodClassBean.getGroup().get(adapter.getType()).getCategories().get(position).getId());
//
                        intent.putExtra("pop", foodClassBean.getGroup().get(adapter.getType()).getCategories().get(position));
                        context.startActivity(intent);
                        adapter.notifyDataSetChanged();
                    }

                });

                break;
        }

    }

    //返回行布局的数量,当集合为空的时候加载头两个布局,如果不为空,则返回的数量等于集合数加2
    @Override
    public int getItemCount() {
        return foodClassBean == null ? 2 : foodClassBean.getGroup().size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ABOVE;
        } else if (position == 1) {
            return CENTER;
        } else {
            return BLOW;
        }
    }

    class AboveHolder extends RecyclerView.ViewHolder {
        public AboveHolder(View itemView) {
            super(itemView);
        }
    }

    class CenterHolder extends RecyclerView.ViewHolder {
        public CenterHolder(View itemView) {
            super(itemView);
        }
    }

    class BlowHolder extends RecyclerView.ViewHolder {
        TextView textView;
        GridView gridView;

        public BlowHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.foodclass_text);
            gridView = (GridView) itemView.findViewById(R.id.foodclass_girdView);

        }
    }
}
