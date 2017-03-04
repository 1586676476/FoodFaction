package com.jiang.foodfaction.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.jiang.foodfaction.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 17/3/3.
 */

public class SettingActivity extends BaseActivity {
    private Button button;
    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        button = (Button) findViewById(R.id.setting_quit);
        ShareSDK.initSDK(this);
    }

    @Override
    public void initData() {
        //退出登录
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform qq = ShareSDK.getPlatform(SettingActivity.this, cn.sharesdk.tencent.qq.QQ.NAME);
                qq.removeAccount();
                SharedPreferences sp = getSharedPreferences("register", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("register",false);
                editor.commit();
                finish();
                sendBroadcast(new Intent("QUIT"));

            }
        });

    }

    @Override
    public void bindEvent() {

    }
}
