package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.activity.AnalyzeActivity;
import com.jiang.foodfaction.activity.FoodClassAboveDetailsActivity;
import com.jiang.foodfaction.activity.FoodClassCenterCompareActivity;
import com.jiang.foodfaction.activity.GrideViewDetails;
import com.jiang.foodfaction.activity.RegisterActivity;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class FoodClassAdapter extends RecyclerView.Adapter<ViewHolder> {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                //点击跳转的时候传一个type判断后面是调到那个界面,默认为0
               ((AboveHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, FoodClassAboveDetailsActivity.class);
                        intent.putExtra("type", 0);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                CenterHolder centerHolder = (CenterHolder) holder;

                    centerHolder.analyze.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, AnalyzeActivity.class);
                            context.startActivity(intent);
                        }
                    });

                centerHolder.compare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, FoodClassCenterCompareActivity.class);
                        intent.putExtra("type", 1);
                        context.startActivity(intent);
                    }
                });
                centerHolder.scan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


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
        LinearLayout linearLayout;

        public AboveHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.foodclass_queue);
        }
    }

    class CenterHolder extends RecyclerView.ViewHolder {
        private RadioButton analyze, compare, scan;

        public CenterHolder(View itemView) {
            super(itemView);
            analyze = (RadioButton) itemView.findViewById(R.id.foodclass_analyze);
            compare = (RadioButton) itemView.findViewById(R.id.foodclass_compare);
            scan = (RadioButton) itemView.findViewById(R.id.foodclass_scan);
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
