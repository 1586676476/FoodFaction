package com.jiang.foodfaction.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.adapter.ShareHomeAdapter;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class HomeFragment extends Fragment {
    private List<ShareHomeBean.FeedsBean> list;
    private RecyclerView recyclerView;
    private ShareHomeAdapter shareHomeAdapter;
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    private int pager = 1;
    //声明一个广播接受者
    private Receiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerView);

        list = new ArrayList<>();

        NetTool.getInstance().startRequest(url, ShareHomeBean.class, new CallBack<ShareHomeBean>() {
            @Override
            public void onSuccess(ShareHomeBean respomse) {
                list = respomse.getFeeds();
                shareHomeAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(sglm);

        shareHomeAdapter = new ShareHomeAdapter(getContext());

        shareHomeAdapter.setFeedsBeen(list);

        recyclerView.setAdapter(shareHomeAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //吃实话实现类 当滑动到最下面的时候发送广播
        Scorell scorell = new Scorell(recyclerView, getContext());

        scorell.load();

        receiver = new Receiver();

        IntentFilter intentFilter = new IntentFilter("LOADING");

        getContext().registerReceiver(receiver, intentFilter);


        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(decoration);


    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        //声明一个距离
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }

        }
    }
    //广播接收器
    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            pager++;
            final String url1 = "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + pager + "&category=1&per=10";

            NetTool.getInstance().startRequest(url1, ShareHomeBean.class, new CallBack<ShareHomeBean>() {
                @Override
                public void onSuccess(ShareHomeBean respomse) {
                    list = respomse.getFeeds();
                    shareHomeAdapter.setMore(list);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
        }
    }
}


