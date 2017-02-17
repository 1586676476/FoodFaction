package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.FoodClassAdapter;
import com.jiang.foodfaction.adapter.GridViewAdapter;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class FoodClassFragment extends Fragment {

    private static final String TAG = "FoodClassFragment";
    private String url = "http://food.boohee.com/fb/v1/categories/list";

    private RecyclerView recyclerView;
    private FoodClassBean data;

    private FoodClassAdapter foodClassAdapter;

@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foodclass, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.foodclass_recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        foodClassAdapter=new FoodClassAdapter(getContext());

        foodClassAdapter.setFoodClassBean(data);

        recyclerView.setAdapter(foodClassAdapter);



        NetTool.getInstance().startRequest(url, FoodClassBean.class, new CallBack<FoodClassBean>() {
            @Override
            public void onSuccess(FoodClassBean respomse) {

                data = respomse;
                Log.e(TAG, "onSuccess: "+data.getGroup().size());

                foodClassAdapter.setFoodClassBean(data);
                foodClassAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "onError: ");
            }
        });
    }

}
