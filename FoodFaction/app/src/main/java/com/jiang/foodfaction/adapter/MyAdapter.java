package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.MyBean;
import com.jiang.foodfaction.inter.OnClickListener;

import java.util.List;

/**
 * Created by dllo on 17/2/13.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MyAdapter";
    private List<MyBean> list;
    private Context context;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setList(List<MyBean> list) {
        this.list = list;
    }

    public MyAdapter(Context context) {

        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.my_item,parent,false);

        ArrayHolder arrayHolder=new ArrayHolder(view);

        return arrayHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //向下转型
        ArrayHolder arrayHolder= (ArrayHolder) holder;

        arrayHolder.left.setImageResource(list.get(position).getLeftId());
        arrayHolder.textView.setText(list.get(position).getText());
        arrayHolder.right.setImageResource(list.get(position).getRightId());

        arrayHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+list.size());
        return list.size();
    }

    class ArrayHolder extends RecyclerView.ViewHolder {
        private ImageView left;
        private TextView textView;
        private ImageView right;
        //private ImageView blow;

        public ArrayHolder(View itemView) {
            super(itemView);
            left= (ImageView) itemView.findViewById(R.id.my_item_left);
            textView= (TextView) itemView.findViewById(R.id.my_item_text);
            right= (ImageView) itemView.findViewById(R.id.my_item_right);
            //blow= (ImageView) itemView.findViewById(R.id.my_item_blow);
        }
    }
}
