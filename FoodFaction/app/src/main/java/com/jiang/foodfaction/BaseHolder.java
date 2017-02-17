package com.jiang.foodfaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dllo on 17/2/14.
 */

public class BaseHolder extends RecyclerView.ViewHolder {

    private View jView;
    //存放view的,类似于map
    private SparseArray<View> sparseArray;
    private Context context;

    //复写构造方法
     public BaseHolder(View view, Context context) {
         super(view);
         jView=view;
         sparseArray=new SparseArray<>();
         this.context=context;
    }

    public View getjView() {
        return jView;
    }

    //使用recycleview时调用的方法
    public static BaseHolder creatRecycleViewHolder(Context context, ViewGroup group,int layoutId){
        View itemView=LayoutInflater.from(context).inflate(layoutId,group,false);
        BaseHolder holder=new BaseHolder(itemView,context);
        return holder;
    }
    //初始化Holder对象
     public static BaseHolder creatListHolder(View view,ViewGroup group,int layoutId){
         BaseHolder holder=null;
         if (view==null){
             view= LayoutInflater.from(group.getContext()).inflate(layoutId,group,false);
             holder=new BaseHolder(view,group.getContext());
             view.setTag(holder);
         }else {
             holder= (BaseHolder) view.getTag();
         }
         return holder;
     }
    //根据id获取我们想要的组件
    public <T extends View> T getView(int id){
        View view=sparseArray.get(id);
        if (view==null){
            //通过id找到内容
            view=jView.findViewById(id);
            //通过key,value的形式传入集合中
            sparseArray.put(id,view);
        }
        //返回的就是我们想要的组件
        return (T) view;
    }

    //设置文字
    public BaseHolder setText(int id,String text){
        //根据id找到内容
        TextView textView=getView(id);
        if (text!=null){
            textView.setText(text);
        }
        return this;
    }
    //从本地拉取图片
    public BaseHolder setImage(int id,int resource){
        ImageView imageView=getView(id);
        if (resource!= 0){
            imageView.setImageResource(resource);
        }
        return this;
    }
    //从网络上拉取图片
    public BaseHolder setImage(int id,String url){
        ImageView imageView=getView(id);
        if (url!=null){
            Glide.with(context).load(url).into(imageView);
        }
        return this;
    }

}
