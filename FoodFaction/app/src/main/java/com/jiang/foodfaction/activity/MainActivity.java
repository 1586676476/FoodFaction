package com.jiang.foodfaction.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.FoodClassAdapter;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.fragment.FoodClassFragment;
import com.jiang.foodfaction.fragment.MyFragment;
import com.jiang.foodfaction.fragment.ShareFragment;

import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
//    private RecyclerView recyclerView;
//    private List<FoodClassBean> data;
//    private FoodClassAdapter foodClassAdapter;

    private RadioGroup radioGroup;

    private FragmentTransaction fragmentTransaction;

    //private String uri = "http://food.boohee.com/fb/v1/categories/list?token=9bzmuo7zpxWXcCZ5sDgo&user_key=e88bf69a-92d5-4dd4-89af-69aef89dc639&app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //recyclerView = bindView(R.id.foodclass_recycleview);
        radioGroup=bindView(R.id.radioGroup_bottom);
    }

    @Override
    public void initData() {
//        foodClassAdapter = new FoodClassAdapter(this);
//        //将recycleview设置成列表形式
//        GridLayoutManager manager = new GridLayoutManager(this, 3, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(foodClassAdapter);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,new FoodClassFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void bindEvent() {
        //radioGroup的点击事件
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //点击时所在页面替换掉main当中的fragLayout
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        switch (checkedId){
            case R.id.foodclass_foodclass:
                fragmentTransaction.replace(R.id.main_frame,new FoodClassFragment());
                fragmentTransaction.commit();
                break;
            case R.id.foodclass_share:
                fragmentTransaction.replace(R.id.main_frame,new ShareFragment());
                fragmentTransaction.commit();
                break;
            case R.id.foodclass_my:
                fragmentTransaction.replace(R.id.main_frame,new MyFragment());
                fragmentTransaction.commit();
                break;
        }
    }
}
