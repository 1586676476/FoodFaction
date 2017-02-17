package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.FoodAdapter;
import com.jiang.foodfaction.bean.FoodBean;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class FoodFragment extends Fragment {
    private String url="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";

    private List<FoodBean.FeedsBean> list;
    private ListView listView;
    private FoodAdapter foodAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_food,container,false);
        listView= (ListView) view.findViewById(R.id.food_listView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = new ArrayList<>();

        foodAdapter = new FoodAdapter(getContext());

        foodAdapter.setFeedsBeen(list);

        listView.setAdapter(foodAdapter);

        NetTool.getInstance().startRequest(url, FoodBean.class, new CallBack<FoodBean>() {
            @Override
            public void onSuccess(FoodBean respomse) {
                list=respomse.getFeeds();
                foodAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
