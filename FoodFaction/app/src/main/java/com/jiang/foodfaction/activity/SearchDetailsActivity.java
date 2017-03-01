package com.jiang.foodfaction.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.SearchDetailsAdapter;
import com.jiang.foodfaction.bean.SearchDetailsBean;
import com.jiang.foodfaction.bean.SearchDetailsEventBus;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.inter.OnClickListener;
import com.jiang.foodfaction.packaging.NetTool;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class SearchDetailsActivity extends BaseActivity implements View.OnClickListener, OnClickListener {
    private static final String TAG = "SearchDetailsActivity";
    private List<SearchDetailsBean.ItemsBean> data;
    private RecyclerView recyclerView;
    private SearchDetailsAdapter searchDetailsAdapter;
    private EditText editText;
    private ImageView imageView,back;

    //private ArrayReceive arrayReceive;
    private EventBus eventBus;

    private String url,kind;
    private String utfStr="";
    private int pager=1;

    @Override
    public int bindLayout() {
        return R.layout.search_details;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.search_details_recyclerView);
        editText = (EditText) findViewById(R.id.search_details_editText);
        imageView = (ImageView) findViewById(R.id.search_details_clear);
        back = (ImageView) findViewById(R.id.search_details_back);
        //初始化eventBus
        eventBus=EventBus.getDefault();
    }

    @Override
    public void initData() {
        //接受我点击位置所传过来的内容
        Intent intent=getIntent();
        kind=intent.getStringExtra("name");
        //editText显示我搜索的内容
        editText.setText(kind);
        // 将字符串转换为UTF-8编码 要不然会出现解析错误

        try {
            utfStr= URLEncoder.encode(kind,"UTF_8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        searchDetailsAdapter = new SearchDetailsAdapter(this);
        searchDetailsAdapter.setItemsBeen(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchDetailsAdapter);

        searchDetailsAdapter.setOnClickListener(this);

        //设置监听事件
        imageView.setOnClickListener(this);
        back.setOnClickListener(this);



        url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q="+utfStr+
               " &token=QYCCzzZVYMpoxvYoz8ig&user_key=c538dd6d-c12f-4742-ac0c-7c71ea74aa5e"+
                "&app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";
        Log.d("SearchDetailsActivity", url);
        NetTool.getInstance().startRequest(url, SearchDetailsBean.class, new CallBack<SearchDetailsBean>() {
            @Override
            public void onSuccess(SearchDetailsBean respomse) {

//                data.addAll(respomse.getItems());
                searchDetailsAdapter.setItemsBeen(respomse.getItems());
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

//        //接受广播
//        arrayReceive=new ArrayReceive();
//        IntentFilter intentFilter = new IntentFilter("LOADING");
//        registerReceiver(arrayReceive, intentFilter);



    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_details_back:
                break;
            case R.id.search_details_clear:
                finish();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(this,FoodClassCenterCompareActivity.class);
        startActivity(intent);
        //使用EventBus进行传值
        SearchDetailsEventBus searchDetailsEventBus=new SearchDetailsEventBus();
        eventBus.post(searchDetailsEventBus);
        finish();



    }

//    class ArrayReceive extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//
//            pager++;
//            String urlscorell ="http://food.boohee.com/fb/v1/search?page=2&order_asc=desc&q="+utfStr+
//                    " &token=QYCCzzZVYMpoxvYoz8ig&user_key=c538dd6d-c12f-4742-ac0c-7c71ea74aa5e "+
//                    " &app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";
//
//            NetTool.getInstance().startRequest(urlscorell, SearchDetailsBean.class, new CallBack<SearchDetailsBean>() {
//                @Override
//                public void onSuccess(SearchDetailsBean respomse) {
//                    searchDetailsAdapter.setItemsBeen(respomse.getItems());
//                }
//
//                @Override
//                public void onError(Throwable throwable) {
//
//                }
//            });
//
//        }
//    }
}
