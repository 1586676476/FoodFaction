package com.jiang.foodfaction.greendao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.net.URL;
import java.util.List;

/**
 * Created by dllo on 17/3/2.
 */

public class DbTool {

    private BeanDao beanDao;
    private static DbTool ourInstance;

    public static DbTool getInStance() {
        if (ourInstance == null) {
            synchronized (DbTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DbTool();
                }
            }
        }
        return ourInstance;
    }

    //在构造方法中进行初始化的操作
    public DbTool() {
        beanDao = MyApp.getDaoSession().getBeanDao();
    }

    //添加一个数据
    public void insert(Bean bean) {
        beanDao.insert(bean);
    }

    //添加一个集合
    public void insert(List<Bean> list) {
        beanDao.insertInTx(list);
    }

    //删除一个数据
    public void deleteBean(Bean bean) {
        beanDao.delete(bean);
    }

    //删除所有的数据
    public void deleteAll() {
        beanDao.deleteAll();
    }

    //删除特定某一个内容
    public void deleteByTitle(String title) {
        DeleteQuery<Bean> deleteQuery = beanDao.queryBuilder().where(BeanDao.Properties.Title.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    //查询的方法
    public List<Bean> queueAll() {
        List<Bean> list = beanDao.loadAll();
        return list;
    }
    //查询是否是bean中的某一个部分
    public boolean queueUrl(Bean bean){
        Bean queueBean = beanDao.queryBuilder().where(BeanDao.Properties.Url.eq(bean.getUrl())).build().unique();

        return queueBean == null ? false : true;
    }

    //查重的方法
    public boolean isSave(Bean bean) {
        QueryBuilder<Bean> builder = beanDao.queryBuilder().where(BeanDao.Properties.Title.eq(bean.getTitle()));

        Long count = builder.buildCount().count();
        return count > 0 ? true : false;
    }

}
