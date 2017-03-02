package com.jiang.foodfaction.greendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 17/3/2.
 */

public class MyApp extends Application {

    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static DaoMaster getDaoMaster() {
        //初始化并且建表
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "MyGreenDao", null);
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {

            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
        }
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
