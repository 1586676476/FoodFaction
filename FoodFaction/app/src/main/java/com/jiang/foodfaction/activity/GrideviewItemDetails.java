package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.GrideViewDetailsBean;
import com.jiang.foodfaction.bean.GrideViewItemDetailsBean;
import com.jiang.foodfaction.greendao.BeanDao;
import com.jiang.foodfaction.greendao.DaoMaster;
import com.jiang.foodfaction.greendao.DbTool;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import de.hdodenhof.circleimageview.CircleImageView;

public class GrideviewItemDetails extends BaseActivity {

    private GrideViewDetailsBean.FoodsBean data;
    private CircleImageView circleImageView;
    private TextView name, number, title;
    private String url;

    private ImageView imageView, back;
    private boolean isLiked;

    private DbTool dbTool;

    @Override
    public int bindLayout() {
        return R.layout.grideview_item_details;
    }

    @Override
    public void initView() {
        circleImageView = (CircleImageView) findViewById(R.id.grideview_item_details_image);
        name = (TextView) findViewById(R.id.grideview_item_details_above_text);
        number = (TextView) findViewById(R.id.grideview_item_details_below_text);
        title = (TextView) findViewById(R.id.merge_text);
        imageView = (ImageView) findViewById(R.id.grideview_item_details_linearLayout_image);
        back = (ImageView) findViewById(R.id.include_image);

      }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        url = "http://food.boohee.com/fb/v1/foods/" + code + "/mode_show?token=&user_key=&app_version=2.6" +
                "&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";
        NetTool.getInstance().startRequest(url, GrideViewDetailsBean.FoodsBean.class, new CallBack<GrideViewDetailsBean.FoodsBean>() {
            @Override
            public void onSuccess(GrideViewDetailsBean.FoodsBean respomse) {
                data = respomse;
                name.setText(respomse.getName());
                number.setText(respomse.getCalory());
                title.setText(respomse.getName());
                Glide.with(GrideviewItemDetails.this).load(data.getThumb_image_url()).into(circleImageView);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        //第一次点击为红色,在进行点击为最初图片,并存入数据库
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    imageView.setImageResource(R.mipmap.news_keep_default);
                    isLiked = false;
                } else {
                    imageView.setImageResource(R.mipmap.news_keep_heighlight);

                    isLiked = true;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void bindEvent() {

    }
}
