package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.foodfaction.R;

public class FoodClassCenterCompareActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageView,leftImageView,rightImageView;
    private TextView textView;

    @Override
    public int bindLayout() {
        return R.layout.foodclass_center_compare;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.include_image);
        textView = (TextView) findViewById(R.id.merge_text);
        leftImageView = (ImageView) findViewById(R.id.foodclass_center_left_image);
        rightImageView = (ImageView) findViewById(R.id.foodclass_center_right_image);
    }

    @Override
    public void initData() {
        //设置标题
        textView.setText("对比详情");

        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
        imageView.setOnClickListener(this);

     }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击返回上一个页面
            case R.id.include_image:
                finish();
                break;
            //点击时跳转到搜索页面
            case R.id.foodclass_center_left_image:
                Intent intent=new Intent(this,FoodClassAboveDetailsActivity.class);
                startActivity(intent);
                break;
            //点击调到搜索界面
            case R.id.foodclass_center_right_image:
                Intent intentright=new Intent(this,FoodClassAboveDetailsActivity.class);
                startActivity(intentright);
                break;

        }
    }
}
