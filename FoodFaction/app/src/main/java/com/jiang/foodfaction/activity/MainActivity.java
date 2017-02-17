package com.jiang.foodfaction.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.fragment.FoodClassFragment;
import com.jiang.foodfaction.fragment.MyFragment;
import com.jiang.foodfaction.fragment.ShareFragment;

/**
 * Created by dllo on 17/2/11.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    private FragmentTransaction fragmentTransaction;
    //声明一个广播接受者
    //private MainReceiver mainReceiver;

     @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        radioGroup=bindView(R.id.radioGroup_bottom);
    }

    @Override
    public void initData() {

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,new FoodClassFragment());
        fragmentTransaction.commit();

//        mainReceiver=new MainReceiver();
//        IntentFilter intentFilter=new IntentFilter("RETURN_MYFRAGMENT");
//        registerReceiver(mainReceiver,intentFilter);

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

//    class MainReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    }
}
