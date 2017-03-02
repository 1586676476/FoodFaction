package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.HomeDeatailsBean;
import com.jiang.foodfaction.bean.ShareHomeBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeDetailsActivity extends BaseActivity {

    private static final String TAG = "HomeDetailsActivity";

    private CircleImageView avatar;
    private TextView name;
    private ImageView imageView,back;
    private TextView number;

    private HomeDeatailsBean data;

    @Override
    public int bindLayout() {
        return R.layout.activity_homedetails;
    }

    @Override
    public void initView() {
        avatar = bindView(R.id.homeDetails_avatar);
        name = bindView(R.id.homeDetails_name);
        imageView = bindView(R.id.homeDetails_url);
        number = bindView(R.id.homeDetails_number);
        back=bindView(R.id.include_image);
    }

    @Override
    public void initData() {

        Intent intent = getIntent();

        intent.getStringExtra("homeUrl");

        String homeUrl = "http://food.boohee.com/fb/v1/food_cards/" + intent.getStringExtra("homeUrl") +
                "?token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu";


        NetTool.getInstance().startRequest(homeUrl, HomeDeatailsBean.class, new CallBack<HomeDeatailsBean>() {
            @Override
            public void onSuccess(HomeDeatailsBean respomse) {
                data = respomse;
                Glide.with(HomeDetailsActivity.this).load(data.getUser_avatar()).into(avatar);
                Glide.with(HomeDetailsActivity.this).load(data.getImage_url()).into(imageView);
                name.setText(data.getTitle());
                //转化为String类型
                number.setText(data.getLike_ct()+"");
            }

            @Override
            public void onError(Throwable throwable) {

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
