package com.jiang.foodfaction.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.activity.DetailsActivity;
import com.jiang.foodfaction.adapter.EvaluateAdapter;
import com.jiang.foodfaction.bean.EvaluateBean;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.inter.OnClickListener;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class EvaluateFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,OnClickListener {

    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";

    private List<EvaluateBean.FeedsBean> list;
    private RecyclerView recyclerView;
    private EvaluateAdapter evaluateAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private Handler handler;

    private int pager = 1;
    private static final int DISTANCE = 100;

    private Receiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.evaluate_recycleView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = new ArrayList<>();
        //设置下拉加载监听事件
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置颜色
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark
                , R.color.colorPrimaryDark);

        evaluateAdapter = new EvaluateAdapter(getContext());

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DISTANCE:

                        evaluateAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                }
            }
        };


        evaluateAdapter.setFeedsBeen(list);

        evaluateAdapter.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(evaluateAdapter);

        NetTool.getInstance().startRequest(url, EvaluateBean.class, new CallBack<EvaluateBean>() {
            @Override
            public void onSuccess(EvaluateBean respomse) {
                list = respomse.getFeeds();
                evaluateAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        Scorell scorell = new Scorell(recyclerView, getContext());

        scorell.load();

        receiver = new Receiver();

        IntentFilter intentFilter = new IntentFilter("LOADING");

        getContext().registerReceiver(receiver, intentFilter);

    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(DISTANCE, 2000);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("url", list.get(position).getLink());
        intent.putExtra("titles",list.get(position).getTitle());
        startActivity(intent);
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
            final String url1 = "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + pager +"&category=2&per=10" ;

           NetTool.getInstance().startRequest(url1, EvaluateBean.class, new CallBack<EvaluateBean>() {
               @Override
               public void onSuccess(EvaluateBean respomse) {
                   list.addAll(respomse.getFeeds());
                   evaluateAdapter.setMore(list);
               }

               @Override
               public void onError(Throwable throwable) {

               }
           });

        }
    }
}
