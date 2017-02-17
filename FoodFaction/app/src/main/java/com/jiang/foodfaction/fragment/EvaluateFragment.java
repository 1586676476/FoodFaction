package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.EvaluateAdapter;
import com.jiang.foodfaction.bean.EvaluateBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class EvaluateFragment extends Fragment {

    private String url="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";

    private List<EvaluateBean.FeedsBean> list;
    private ListView listView;
    private EvaluateAdapter evaluateAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_evaluate,container,false);
        listView= (ListView) view.findViewById(R.id.evaluate_listView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list=new ArrayList<>();

        evaluateAdapter=new EvaluateAdapter(getContext());

        evaluateAdapter.setFeedsBeen(list);

        listView.setAdapter(evaluateAdapter);

        NetTool.getInstance().startRequest(url, EvaluateBean.class, new CallBack<EvaluateBean>() {
            @Override
            public void onSuccess(EvaluateBean respomse) {
                list=respomse.getFeeds();
                evaluateAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
