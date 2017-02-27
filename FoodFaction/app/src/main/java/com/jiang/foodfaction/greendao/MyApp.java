package com.jiang.foodfaction.greendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 17/2/27.
 */

public class MyApp extends Application {
    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    private static Context getContext(){
        return context;
    }

    //初始化daomaster
    private static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(getContext(),"MyGreenDao.db",null);
        daoMaster=new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }
    //初始化daosession
    private static DaoSession getDaoSession(){
        if (daoSession==null){
            if (daoMaster==null){
                daoMaster=getDaoMaster();
            }
        }
        daoSession=getDaoMaster().newSession();
        return daoSession;
    }
}
