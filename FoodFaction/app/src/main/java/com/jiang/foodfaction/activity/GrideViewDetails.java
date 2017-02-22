package com.jiang.foodfaction.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.adapter.GrideViewDetailsAdapter;
import com.jiang.foodfaction.adapter.PopupWindowAdapter;
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
    private RecyclerView recyclerView, poprecyclerView;
    private List<GrideViewDetailsBean.FoodsBean> foodsBeen;
    //声明一个popupWindow
    private PopupWindow popupWindow;

    private GrideViewDetailsAdapter grideViewDetailsAdapter;
    private ImageView imageView;
    //声明一个对象
    private LinearLayout linearLayout;

    private static final String TAG = "GrideViewDetails";
    private int pager = 1;
    private ArrayHolder arrayHolder;

    private String kind;
    private int id;
    private FoodClassBean.GroupBean.CategoriesBean bean;


    @Override
    public int bindLayout() {
        return R.layout.gridview_details;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.include_image);
        recyclerView = (RecyclerView) findViewById(R.id.grideview_details_recyclerView);
        linearLayout = (LinearLayout) findViewById(R.id.merge_linearLayout);
        //因为要在同一个界面加载不同的布局,为避免出现空指针的情况,我们需要重新加载布局
        View v = LayoutInflater.from(this).inflate(R.layout.popupwindow_down, null);
        poprecyclerView = (RecyclerView) v.findViewById(R.id.popupWindow_recyclerView);
        Log.e(TAG, "initView: " + (poprecyclerView == null));
        //让隐藏部分的标题显示
        findViewById(R.id.merge_linearLayout).setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        kind = getIntent().getStringExtra("kind");
        id = getIntent().getIntExtra("id", 0);
        //传过来的pop是一个序列化的集合
        bean=getIntent().getParcelableExtra("pop");
        Log.d(TAG, "initData: "+bean.getSub_categories().get(0).getName());

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
        //初始化封装类,并发送广播
        final Scorell scorell = new Scorell(recyclerView, this);

        scorell.load();
        //广播接受者
        arrayHolder = new ArrayHolder();
        IntentFilter intentFilter = new IntentFilter("LOADING");
        registerReceiver(arrayHolder, intentFilter);

        //点击返回
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PopupWindowAdapter popupWindowAdapter = new PopupWindowAdapter(this);
        popupWindowAdapter.setCategoriesBeen(bean);

        poprecyclerView.setAdapter(popupWindowAdapter);

        poprecyclerView.setLayoutManager(new LinearLayoutManager(this));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupWindow_view = getLayoutInflater().inflate(R.layout.popupwindow_down, null);

                popupWindow = new PopupWindow(popupWindow_view, 200, 200, true);
                //让显示的位置在点击位置的底部
                popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                //点击其他的地方消失
                popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }
                });

            }
        });







    }

    @Override
    public void bindEvent() {

    }


    class ArrayHolder extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            pager++;

            String url1 = "http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id + "&order_by=1&page="
                    + pager + "&order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu";

            NetTool.getInstance().startRequest(url1, GrideViewDetailsBean.class, new CallBack<GrideViewDetailsBean>() {
                @Override
                public void onSuccess(GrideViewDetailsBean respomse) {

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
