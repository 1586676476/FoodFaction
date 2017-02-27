package com.jiang.foodfaction.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.Scorell;
import com.jiang.foodfaction.adapter.GrideViewDetailsAdapter;
import com.jiang.foodfaction.adapter.NutrientAdapter;
import com.jiang.foodfaction.adapter.PopupWindowAdapter;
import com.jiang.foodfaction.bean.FoodClassBean;
import com.jiang.foodfaction.bean.GrideViewDetailsBean;
import com.jiang.foodfaction.bean.NutrientBean;
import com.jiang.foodfaction.fragment.FoodFragment;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.inter.OnClickListener;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/2/21.
 */

public class GrideViewDetails extends BaseActivity implements OnClickListener {
    private RecyclerView recyclerView, poprecyclerView, ntrecyclerView;
    private List<GrideViewDetailsBean.FoodsBean> foodsBeen;
    //声明一个popupWindow
    private PopupWindow popupWindow, popnutrient;

    private GrideViewDetailsAdapter grideViewDetailsAdapter;
    private ImageView imageView;
    //声明一个对象
    private LinearLayout linearLayout, nutrient;

    private static final String TAG = "GrideViewDetails";
    private int pager = 0;

    private ArrayHolder arrayHolder;

    private String kind;
    private int id;
    private String number;

    private FoodClassBean.GroupBean.CategoriesBean bean;

    private NutrientBean nutrientBean;
    private PopupWindowAdapter popupWindowAdapter;


    @Override
    public int bindLayout() {
        return R.layout.gridview_details;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.include_image);
        recyclerView = (RecyclerView) findViewById(R.id.grideview_details_recyclerView);
        View view = LayoutInflater.from(this).inflate(R.layout.nutrient_down, null);
        ntrecyclerView = (RecyclerView) view.findViewById(R.id.nurinut_recyclerView);


        nutrient = (LinearLayout) findViewById(R.id.linearLayout_nutrient);
        popnutrient = new PopupWindow(this);
        popnutrient.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popnutrient.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popnutrient.setContentView(view);

        popnutrient.setOutsideTouchable(true);


        //营养素列表网址
        String url3 = "http://food.boohee.com/fb/v1/foods/sort_types?token=pxN9j6S1za8PGQzefHxh" +
                "&user_key=e88bf69a-92d5-4dd4-89af-69aef89dc639&app_version=2.6&app_device=Android&os_version=6.0.1" +
                "&phone_model=MI+NOTE+LTE&channel=xiaomi ";

        final NutrientAdapter nutrientAdapter = new NutrientAdapter(this);

        nutrientAdapter.setNutrientBean(nutrientBean);

        GridLayoutManager glm = new GridLayoutManager(GrideViewDetails.this, 3);

        ntrecyclerView.setLayoutManager(glm);

        ntrecyclerView.setAdapter(nutrientAdapter);

        nutrient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popnutrient.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                popnutrient.showAsDropDown(nutrient);

                nutrientAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e(TAG, "onItemClick: " + (nutrientBean.getTypes() == null));
                        number = nutrientBean.getTypes().get(position).getIndex();

                        String url2 = "http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id + "&order_by=" + number +
                                "&page=" + pager + "&order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1"
                                + "&phone_model=M578CA&channel=meizu";

                        NetTool.getInstance().startRequest(url2, GrideViewDetailsBean.class, new CallBack<GrideViewDetailsBean>() {
                            @Override
                            public void onSuccess(GrideViewDetailsBean respomse) {
                                grideViewDetailsAdapter.setFoodsBeen(respomse.getFoods());
                                popnutrient.dismiss();
                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }
                        });

                    }
                });


            }
        });
        //解析营养素排序表
        NetTool.getInstance().startRequest(url3, NutrientBean.class, new CallBack<NutrientBean>() {
            @Override
            public void onSuccess(NutrientBean respomse) {
                nutrientBean = respomse;
                nutrientAdapter.setNutrientBean(nutrientBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.merge_linearLayout);
        //因为要在同一个界面加载不同的布局,为避免出现空指针的情况,我们需要重新加载布局
        View v = LayoutInflater.from(this).inflate(R.layout.popupwindow_down, null);
        //初始化popWindow
        popupWindow = new PopupWindow(this);
        //给popWindow设置宽高 并设置显示的位置
        popupWindow.setWidth(400);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置popWindow的内容
        popupWindow.setContentView(v);
        //点击外部消失
        popupWindow.setOutsideTouchable(true);

        poprecyclerView = (RecyclerView) v.findViewById(R.id.popupWindow_recyclerView);

        //让隐藏部分的标题显示
        findViewById(R.id.merge_linearLayout).setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        pager++;
        kind = getIntent().getStringExtra("kind");
        id = getIntent().getIntExtra("id", 0);
        //传过来的pop是一个序列化的集合
        bean = getIntent().getParcelableExtra("pop");
        Log.e(TAG, "initData: " + (bean == null));

        String url;

        foodsBeen = new ArrayList<>();
        grideViewDetailsAdapter = new GrideViewDetailsAdapter(this);
        recyclerView.setAdapter(grideViewDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GrideViewDetails.this));

        //设置行点击事件



        //显示加载的页面
        url = "http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id + "&order_by=1" +
                "&page=" + pager + "&order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1" +
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


        popupWindowAdapter = new PopupWindowAdapter(this);

        popupWindowAdapter.setCategoriesBeen(bean);

        poprecyclerView.setAdapter(popupWindowAdapter);

        poprecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //popWindow设置点击事件
        popupWindowAdapter.setClickListener(this);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                //设置popWindow显示的位置
                popupWindow.showAsDropDown(linearLayout);
            }
        });


    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onItemClick(int Id) {
        //popWindow的行点击事件
        String urlTwo = "http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id +
                "&sub_value=" + Id + "&order_by=1&page="
                + pager + "&order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1" +
                "&phone_model=M578CA&channel=meizu";
        Log.e(TAG, "onItemClick: " + urlTwo);
        NetTool.getInstance().startRequest(urlTwo, GrideViewDetailsBean.class, new CallBack<GrideViewDetailsBean>() {
            @Override
            public void onSuccess(GrideViewDetailsBean respomse) {
                grideViewDetailsAdapter.setFoodsBeen(respomse.getFoods());
                popupWindow.dismiss();
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });




    }


    class ArrayHolder extends BroadcastReceiver {
        //接受到广播滑动到下一页
        @Override
        public void onReceive(Context context, Intent intent) {


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
