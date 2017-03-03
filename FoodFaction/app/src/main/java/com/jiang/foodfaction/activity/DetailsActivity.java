package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.greendao.Bean;
import com.jiang.foodfaction.greendao.BeanDao;
import com.jiang.foodfaction.greendao.DbTool;

/**
 * Created by dllo on 17/2/18.
 */

public class DetailsActivity extends BaseActivity {
    private static final String TAG = "DetailsActivity";

    private ImageView imageView, collect;
    private WebView webView;
    private LinearLayout linearLayout;
    //判断是否被点击
    private boolean isCollect;

    private Bean bean;

    @Override
    public int bindLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        imageView = bindView(R.id.include_image);
        webView = bindView(R.id.webView);
        linearLayout = bindView(R.id.collcet);
        collect = bindView(R.id.collect_image);
    }

    @Override
    public void initData() {


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        final String title = intent.getStringExtra("titles");
        bean = new Bean();
        bean.setUrl(url);
        bean.setTitle(title);

        webView.loadUrl(url);
        //希望当前加载的内容再webView中显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });

        //判断数据库中的url
        if(bean!=null&&DbTool.getInStance().queueUrl(bean)){
            collect.setImageResource(R.mipmap.news_keep_heighlight);
        }


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //点击变色,并存入数据库中
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollect) {
                    //当点击时图片变回原来的样子,并且对应的删除数据
                    collect.setImageResource(R.mipmap.keepdefault);
                    isCollect = false;
                    DbTool.getInStance().deleteBean(bean);
                    Log.e(TAG, "onClick: ");
                } else {
                    //点击图片变颜色,并且添加到数据库中
                    collect.setImageResource(R.mipmap.news_keep_heighlight);
                    isCollect = true;
                    DbTool.getInStance().insert(bean);
                    Log.e(TAG, "onClick: " + bean);

                }
            }
        });


//        webView.loadUrl(String.valueOf(data));
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(String.valueOf(data));
//                return true;
//            }
//        });


    }

    @Override
    public void bindEvent() {

    }
}
