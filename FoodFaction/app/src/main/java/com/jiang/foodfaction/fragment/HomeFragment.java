package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.ShareHomeAdapter;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class HomeFragment extends Fragment{
    private List<ShareHomeBean.FeedsBean> list;
    private RecyclerView recyclerView;
    private ShareHomeAdapter shareHomeAdapter;
    private String url="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.home_recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list=new ArrayList<>();

        StaggeredGridLayoutManager sglm=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(sglm);

        shareHomeAdapter=new ShareHomeAdapter(getContext());

        shareHomeAdapter.setFeedsBeen(list);

        recyclerView.setAdapter(shareHomeAdapter);

        NetTool.getInstance().startRequest(url, ShareHomeBean.class, new CallBack<ShareHomeBean>() {
            @Override
            public void onSuccess(ShareHomeBean respomse) {
                list=respomse.getFeeds();
                shareHomeAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
