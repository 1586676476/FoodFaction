package com.jiang.foodfaction.greendao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */
public class DbTool {

    private BeanDao beanDao;
    private static DbTool ourInstance;

    public static DbTool getInstance() {
        if (ourInstance==null){
            synchronized (DbTool.class){
                if (ourInstance==null){
                    ourInstance=new DbTool();
                }
            }
        }
        return ourInstance;
    }

    private DbTool() {
        //初始化对象
        beanDao=MyApp.getDaoSession().getBeanDao();
    }
    //添加数据的过程
    public void insertBean(Bean bean){
        beanDao.insert(bean);
    }
    //添加一个集合
    public void insertAllBean(List<Bean> list){
        beanDao.insertInTx(list);
    }
    //删除一个数据
    public void deteleBean(Bean bean){
        beanDao.delete(bean);
    }
    //删除所有
    public void deteleAll(){
        beanDao.deleteAll();
    }
    //删除对应名字的那个实体类
    public void deteleByName(String name){
        DeleteQuery<Bean> deleteQuery=beanDao.queryBuilder().where(BeanDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    //查询的方法
    public List<Bean> queryAll(){
        List<Bean> list=beanDao.loadAll();
        return list;
    }
    //查重的方法
    public boolean isSave(Bean bean){
        QueryBuilder<Bean> builder=beanDao.queryBuilder().where(BeanDao.Properties.Name.eq(bean.getName()));
        Long count=builder.buildCount().count();
        return count>0?true:false;
    }


}
