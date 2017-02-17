package com.jiang.foodfaction.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiang.foodfaction.BaseHolder;
import com.jiang.foodfaction.R;

import java.util.List;

/**
 * Created by dllo on 17/2/16.
 */

public class UploadingAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public void setList(List<String> list) {
        this.list = list;
    }

    public UploadingAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder baseHolder=BaseHolder.creatListHolder(convertView,parent, R.layout.uploading_listitem);
        switch (position){
            case 0:
                baseHolder.setText(R.id.uploading_left,"我上传的食物");
                baseHolder.setText(R.id.uploading_right,"0");
                baseHolder.setImage(R.id.uploading_image,R.mipmap.arrow_right_normal);
            case 1:
                baseHolder.setText(R.id.uploading_left,"草稿箱");
                baseHolder.setText(R.id.uploading_right,"0");
                baseHolder.setImage(R.id.uploading_image,R.mipmap.arrow_right_normal);
        }

    return baseHolder.getView(position);
    }
}
