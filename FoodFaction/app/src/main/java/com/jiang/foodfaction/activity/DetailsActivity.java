package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.jiang.foodfaction.R;

/**
 * Created by dllo on 17/2/18.
 */

public class DetailsActivity extends BaseActivity {
    private ImageView imageView;
    private WebView webView;


    @Override
    public int bindLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        imageView = bindView(R.id.include_image);
        webView = bindView(R.id.webView);
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

        webView.loadUrl(url);
        //希望当前加载的内容再webview中显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

    }

    @Override
    public void bindEvent() {

    }
}
