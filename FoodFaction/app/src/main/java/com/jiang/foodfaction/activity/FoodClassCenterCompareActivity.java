package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.SearchDetailsEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FoodClassCenterCompareActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageView, leftImageView, rightImageView;
    private TextView textView, leftText, rightText;
    private String TYPECOMPARE="compare";



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
        leftText = (TextView) findViewById(R.id.foodclass_center_left_text);
        rightText = (TextView) findViewById(R.id.foodclass_center_right_text);
        //对eventbus进行注册操作
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        //设置标题
        textView.setText("对比详情");
        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        //让文字显示
//        leftText.setVisibility(View.VISIBLE);
//        rightText.setVisibility(View.VISIBLE);
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击返回上一个页面
            case R.id.include_image:
                finish();
                break;
            //点击时跳转到搜索页面,传一个type判断之后跳转到那个界面
            case R.id.foodclass_center_left_image:
                Intent intentleft = new Intent(this, FoodClassAboveDetailsActivity.class);
                Intent lastIntent1 = getIntent();
                int type1 = lastIntent1.getIntExtra("type", 0);
                intentleft.putExtra("type",type1);
                startActivity(intentleft);
                finish();
                sendBroadcast(new Intent("COMPARE"));
                break;
            //点击调到搜索界面
            case R.id.foodclass_center_right_image:
                Intent intentright2 = new Intent(this, FoodClassAboveDetailsActivity.class);
                Intent lastIntent = getIntent();
                int type2 = lastIntent.getIntExtra("type", 0);
                intentright2.putExtra("type",type2);
                startActivity(intentright2);
                finish();
                sendBroadcast(new Intent("COMPARE"));
                break;

        }
    }

    //添加注解 声明线程的类型,使这个方法运行在主线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(SearchDetailsEventBus searchDetailsEventBus) {

        leftText.setText(searchDetailsEventBus.getName());
        rightText.setText(searchDetailsEventBus.getName());

        Glide.with(this).load(searchDetailsEventBus.getThumb_image_url()).into(leftImageView);
        Glide.with(this).load(searchDetailsEventBus.getThumb_image_url()).into(rightImageView);
    }

    //销毁eventBus

    protected void onDestory() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
