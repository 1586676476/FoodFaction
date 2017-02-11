package com.jiang.foodfaction.inter;

/**
 * Created by dllo on 17/2/11.
 */

public interface NetInterface {
    <T> void startRequest(String url,Class<T> tClass,CallBack<T> tCallBack);

}
