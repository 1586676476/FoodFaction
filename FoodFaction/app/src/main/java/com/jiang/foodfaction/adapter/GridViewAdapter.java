package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.R;

import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class GridViewAdapter extends BaseAdapter {
    //声明一个type用来区别gridView中的三种布局
    private int type;
    private static final String TAG = "GridViewAdapter";
    private FoodClassBean.GroupBean groupBeen;
    private Context context;

    public void setType(int type) {
        this.type = type;
    }
    public int getType(){
        return type;
    }

    public void setGroupBeen(FoodClassBean.GroupBean groupBeen) {
        this.groupBeen = groupBeen;
        notifyDataSetChanged();
    }

    public GridViewAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {

        return groupBeen != null? groupBeen.getCategories().size():0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //复写getView方法
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayHolder holder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.gridview_item ,parent ,false);
            holder=new ArrayHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder= (ArrayHolder) convertView.getTag();
        }

        //加载文字
        holder.textView.setText(groupBeen.getCategories().get(position).getName());
        //加载图片
        Glide.with(context).load(groupBeen.getCategories().get(position).getImage_url()).into(holder.imageView);

        return convertView;
    }

    class ArrayHolder{

        private ImageView imageView;
        private TextView textView;
        public ArrayHolder(View view) {

            imageView= (ImageView) view.findViewById(R.id.gridView_image);
            textView= (TextView) view.findViewById(R.id.gridview_text);
        }


    }
}
