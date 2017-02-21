package com.jiang.foodfaction.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.adapter.GrideViewDetailsAdapter;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.bean.GrideViewDetailsBean;
import com.jiang.foodfaction.fragment.FoodFragment;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/2/21.
 */

public class GrideViewDetails extends BaseActivity {
    private RecyclerView recyclerView;
    private List<GrideViewDetailsBean.FoodsBean> foodsBeen;

    private GrideViewDetailsAdapter grideViewDetailsAdapter;

    private static final String TAG = "GrideViewDetails";
    private int pager=1;
    private ArrayHolder arrayHolder;


    @Override
    public int bindLayout() {
        return R.layout.gridview_details;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.grideview_details_recyclerView);

        //让隐藏部分的标题显示
        findViewById(R.id.merge_linearLayout).setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {


        Intent intent = getIntent();
        String kind = intent.getStringExtra("kind");
        String id = intent.getStringExtra("id");

        String url;

        foodsBeen = new ArrayList<>();

        grideViewDetailsAdapter = new GrideViewDetailsAdapter(this);

        recyclerView.setAdapter(grideViewDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GrideViewDetails.this));

        url = "http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id + "&order_by=1" +
                "&page=1&order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1" +
                "&phone_model=M578CA&channel=meizu";

        NetTool.getInstance().startRequest(url, GrideViewDetailsBean.class, new CallBack<GrideViewDetailsBean>() {
            @Override
            public void onSuccess(GrideViewDetailsBean respomse) {
                foodsBeen = respomse.getFoods();

                grideViewDetailsAdapter.setFoodsBeen(foodsBeen);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        Scorell scorell = new Scorell(recyclerView,this);

        scorell.load();

        arrayHolder = new ArrayHolder();

        IntentFilter intentFilter = new IntentFilter("LOADING");

        registerReceiver(arrayHolder, intentFilter);
        Log.e(TAG, "initData: ");


    }

    @Override
    public void bindEvent() {

    }


    class ArrayHolder extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String kind = intent.getStringExtra("kind");
            String id = intent.getStringExtra("id");
            Log.e(TAG, "onReceive: "+kind);

            pager++;

            String url1="http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id + "&order_by=1&pager="+pager+
                    "&order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1" +
                    "&phone_model=M578CA&channel=meizu";

           NetTool.getInstance().startRequest(url1, GrideViewDetailsBean.class, new CallBack<GrideViewDetailsBean>() {
               @Override
               public void onSuccess(GrideViewDetailsBean respomse) {
                   Log.e(TAG, "onSuccess: " + (respomse.getFoods()));

                   foodsBeen.addAll(respomse.getFoods());
                   grideViewDetailsAdapter.setMore(foodsBeen);
               }

               @Override
               public void onError(Throwable throwable) {

               }
           });


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(arrayHolder);
    }
}
