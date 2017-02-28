package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.SearchDetailsAdapter;
import com.jiang.foodfaction.bean.SearchDetailsBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class SearchDetailsActivity extends BaseActivity implements View.OnClickListener {
    private List<SearchDetailsBean.ItemsBean> data;
    private RecyclerView recyclerView;
    private SearchDetailsAdapter searchDetailsAdapter;
    private EditText editText;
    private ImageView imageView,back;

    private String url;

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
    }

    @Override
    public void initData() {
        //接受我点击位置所传过来的内容
        Intent intent=getIntent();
        String kind=intent.getStringExtra("name");

        editText.setText(kind);

        searchDetailsAdapter = new SearchDetailsAdapter(this);
        searchDetailsAdapter.setItemsBeen(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchDetailsAdapter);

        //设置监听事件
        imageView.setOnClickListener(this);
        back.setOnClickListener(this);


//
//        url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=豆浆"+
//               " &token=QYCCzzZVYMpoxvYoz8ig&user_key=c538dd6d-c12f-4742-ac0c-7c71ea74aa5e"+
//                "&app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";
//        Log.d("SearchDetailsActivity", url);
//        NetTool.getInstance().startRequest(url, SearchDetailsBean.class, new CallBack<SearchDetailsBean>() {
//            @Override
//            public void onSuccess(SearchDetailsBean respomse) {
//                data.addAll(respomse.getItems());
//                searchDetailsAdapter.setItemsBeen(data);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//        });

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
}
