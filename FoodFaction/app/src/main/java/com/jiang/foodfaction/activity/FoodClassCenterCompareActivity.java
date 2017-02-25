package com.jiang.foodfaction.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.foodfaction.R;

public class FoodClassCenterCompareActivity extends BaseActivity {
    private ImageView imageView;
    private TextView textView;

    @Override
    public int bindLayout() {
        return R.layout.foodclass_center_compare;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.include_image);
        textView = (TextView) findViewById(R.id.merge_text);
    }

    @Override
    public void initData() {
        //点击返回返回上一个界面
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置标题
        textView.setText("对比详情");
    }

    @Override
    public void bindEvent() {

    }
}
