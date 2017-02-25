package com.jiang.foodfaction.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.FoodClassAboveAdapter;
import com.jiang.foodfaction.bean.FoodClassAboveBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 17/2/25.
 */

public class FoodClassAboveDetailsActivity extends BaseActivity {

    private static final String TAG = "FoodClassAboveDetailsAc";

    private FoodClassAboveBean data;
    private RecyclerView recyclerView;
    private FoodClassAboveAdapter foodClassAboveAdapter;
    private LinearLayout linearLayout;
    private EditText editText;

    private String url = "http://food.boohee.com/fb/v1/keywords?token=pxN9j6S1za8PGQzefHxh&user_key=e88bf69a-92d5-4dd4-89af-69aef89dc639&" +
            "app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";

    @Override
    public int bindLayout() {
        return R.layout.foodclass_above_details;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.foodclass_above_details_recyclerView);
        linearLayout = (LinearLayout) findViewById(R.id.foodclass_above_details_linearLayout);
        editText = (EditText) findViewById(R.id.foodclass_above_details_editText);
    }

    @Override
    public void initData() {


        foodClassAboveAdapter = new FoodClassAboveAdapter(this);
        foodClassAboveAdapter.setAbove(data);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(foodClassAboveAdapter);

        NetTool.getInstance().startRequest(url, FoodClassAboveBean.class, new CallBack<FoodClassAboveBean>() {
            @Override
            public void onSuccess(FoodClassAboveBean respomse) {

                foodClassAboveAdapter.setAbove(respomse);
                Log.e(TAG, "onSuccess: " + (respomse == null));
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                InputMethodManager manager = (InputMethodManager) editText.
//                        getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                manager.showSoftInput(editText, 0);
//            }
//        }, 500);

    }

    @Override
    public void bindEvent() {

    }
}
