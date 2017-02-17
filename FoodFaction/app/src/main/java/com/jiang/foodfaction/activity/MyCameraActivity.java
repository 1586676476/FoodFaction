package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jiang.foodfaction.R;

/**
 * Created by dllo on 17/2/15.
 */

public class MyCameraActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back, praise;

    @Override
    public int bindLayout() {
        return R.layout.activity_mycamera;
    }

    @Override
    public void initView() {
        back = bindView(R.id.mycamera_return);
        praise = bindView(R.id.mycamera_praise);
    }

    @Override
    public void initData() {
        back.setOnClickListener(this);
        praise.setOnClickListener(this);

    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mycamera_return:
                finish();
                break;
            case R.id.mycamera_praise:
                break;
        }
    }
}
