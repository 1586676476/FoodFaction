package com.jiang.foodfaction.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.adapter.KnowledgeAdapter;
import com.jiang.foodfaction.bean.EvaluateBean;
import com.jiang.foodfaction.bean.KnowledgeBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class KnowledgeFragment extends Fragment {

    private String url="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";

    private List<KnowledgeBean.FeedsBean> list;
    private RecyclerView recyclerView;
    private KnowledgeAdapter knowledgeAdapter;

    private int pager=1;

    private Receiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_knowledge,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.knowledge_recycleView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list=new ArrayList<>();

        knowledgeAdapter=new KnowledgeAdapter(getContext());

        knowledgeAdapter.setFeedsBeen(list);

        LinearLayoutManager manager=new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(knowledgeAdapter);

        NetTool.getInstance().startRequest(url, KnowledgeBean.class, new CallBack<KnowledgeBean>() {
            @Override
            public void onSuccess(KnowledgeBean respomse) {
                list=respomse.getFeeds();
                knowledgeAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        Scorell scorell = new Scorell(recyclerView, getContext());

        scorell.load();

        receiver = new Receiver();

        IntentFilter intentFilter = new IntentFilter("LOADING");

        getContext().registerReceiver(receiver, intentFilter);

        //设置行布局之间的距离
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
            final String url1 = "http://food.boohee.com/fb/v1/feeds/category_feed?page="+ pager + "&category=3&per=10";

            NetTool.getInstance().startRequest(url1, KnowledgeBean.class, new CallBack<KnowledgeBean>() {

                @Override
                public void onSuccess(KnowledgeBean respomse) {
                    list=respomse.getFeeds();
                    knowledgeAdapter.setMore(list);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }
    }
}
