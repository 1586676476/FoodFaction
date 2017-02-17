package com.jiang.foodfaction.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;

import com.jiang.foodfaction.R;

/**
 * Created by dllo on 17/2/13.
 */

public class SplashActivity extends BaseActivity {

    private MyCount myCount;

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {

    myCount=new MyCount(3000,1000);
        myCount.start();

    }

    @Override
    public void bindEvent() {

    }

    class MyCount extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
