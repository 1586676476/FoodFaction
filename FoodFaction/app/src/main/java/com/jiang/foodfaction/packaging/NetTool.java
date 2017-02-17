package com.jiang.foodfaction.packaging;

import android.util.Log;

import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.inter.NetInterface;

/**
 * Created by dllo on 17/2/11.
 */
public class NetTool implements NetInterface{

    private static final String TAG = "NetTool";
    private NetInterface netInterface;
    private static NetTool ourInstance;


    public static NetTool getInstance() {
        //双重校验锁
        if (ourInstance == null) {
            synchronized (NetTool.class) {
            }
            if (ourInstance == null) {
                ourInstance = new NetTool();
            }

        }
        return ourInstance;
    }

    private NetTool() {
        netInterface=new OkTool();
    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, CallBack<T> tCallBack) {
        netInterface.startRequest(url,tClass,tCallBack);
        Log.e(TAG, "startRequest: ");
    }
}
