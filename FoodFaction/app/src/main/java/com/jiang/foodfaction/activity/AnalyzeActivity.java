package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jiang.foodfaction.R;

public class AnalyzeActivity extends BaseActivity {
    private ImageView imageView;

    private SharedPreferences sharedPreferences;

    @Override
    public int bindLayout() {
        return R.layout.foodclass_center_analyze;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.foodclass_center_back);
    }

    @Override
    public void initData() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

     }

    @Override
    public void bindEvent() {

    }



}
