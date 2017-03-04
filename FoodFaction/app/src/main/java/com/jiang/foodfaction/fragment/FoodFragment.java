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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.activity.DetailsActivity;
import com.jiang.foodfaction.adapter.FoodAdapter;
import com.jiang.foodfaction.bean.EvaluateBean;
import com.jiang.foodfaction.bean.FoodBean;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.inter.OnClickListener;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class FoodFragment extends Fragment implements OnClickListener {

    private static final String TAG = "FoodFragment";
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";

    private List<FoodBean.FeedsBean> list;
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;

    private int pager = 1;

    private Receiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.food_recycleView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
        list = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);

        foodAdapter = new FoodAdapter(getContext());

        foodAdapter.setOnClickListener(this);
        foodAdapter.setFeedsBeen(list);


        recyclerView.setAdapter(foodAdapter);

        NetTool.getInstance().startRequest(url, FoodBean.class, new CallBack<FoodBean>() {
            @Override
            public void onSuccess(FoodBean respomse) {
                list = respomse.getFeeds();
                foodAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
        //设置行间距
        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(decoration);


        Scorell scorell = new Scorell(recyclerView, getContext());

        scorell.load();

        receiver = new Receiver();

        IntentFilter intentFilter = new IntentFilter("LOADING");

        getContext().registerReceiver(receiver, intentFilter);

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
            final String url1 = "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + pager + "&category=4&per=10";

            NetTool.getInstance().startRequest(url1, FoodBean.class, new CallBack<FoodBean>() {


                @Override
                public void onSuccess(FoodBean respomse) {
                    list.addAll(respomse.getFeeds());
                    foodAdapter.setFeedsBeen(list);
//                    list.addAll(respomse.getFeeds());
//                    foodAdapter.setMore(list);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(receiver);
    }
}
