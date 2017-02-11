package com.jiang.foodfaction.packaging;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.inter.NetInterface;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 17/2/11.
 */

public class OkTool implements NetInterface{
    //声明对象
    private OkHttpClient okHttpClient;

    private Gson gson;
    //声明一个handler并让其在主线程运行
    private Handler handler=new Handler(Looper.getMainLooper());

    public OkTool() {
        okHttpClient=new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
        gson=new Gson();
    }

    @Override
    public <T> void startRequest(String url, final Class<T> tClass, final CallBack<T> tCallBack) {
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            //连接失败时回调的方法
            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tCallBack.onError(e);
                    }
                });
            }
            //连接成功时回调的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //转化为String类型
                String back=response.body().string();
                final T result=gson.fromJson(back,tClass);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tCallBack.onSuccess(result);
                    }
                });

            }
        });
    }
}
