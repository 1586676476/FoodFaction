package com.jiang.foodfaction.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jiang.foodfaction.R;
import com.jiang.foodfaction.bean.RegisterBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

import static android.R.attr.name;

/**
 * Created by dllo on 17/2/16.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView QQ;
    private ImageView weibo;
    private String picture, text;
    private static final String TAG = "RegisterActivity";

    private EventBus eventBus;

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        QQ = (ImageView) findViewById(R.id.register_qq);
        weibo = (ImageView) findViewById(R.id.register_weibo);
        eventBus = EventBus.getDefault();
    }

    @Override
    public void initData() {
        ShareSDK.initSDK(this);
        QQ.setOnClickListener(this);
        weibo.setOnClickListener(this);
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_qq:
                Platform qq = ShareSDK.getPlatform(this, cn.sharesdk.tencent.qq.QQ.NAME);
                //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();

                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        //遍历Map
                        Iterator ite = arg2.entrySet().iterator();
                        while (ite.hasNext()) {
                            Map.Entry entry = (Map.Entry) ite.next();
                            Object key = entry.getKey();
                            if (key.toString().equals("nickname")) {
                                text = entry.getValue().toString();
                                //Log.e(TAG, "onComplete: " + name);
                            }
                            if (key.toString().equals("figureurl_qq_2")) {
                                picture = entry.getValue().toString();
                                //Log.e(TAG, "onComplete: " + picture);

                            }

                        }

                        RegisterBean registerBean = new RegisterBean();
                        registerBean.setNameTv(text);
                        registerBean.setPhoto(picture);
                        //注意 传的是bean对象
                        eventBus.post(registerBean);
                        Log.e(TAG, "onComplete: "+text);
                        eventBus.post(registerBean);
                        Log.e(TAG, "onComplete: "+picture );

                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
                qq.showUser(null);//执行登录，登录后在回调里面获取用户资料


                finish();
                //登录后发送一条广播
                sendBroadcast(new Intent("CHANGE"));
                break;
            case R.id.register_weibo:

                break;
        }
    }
}
