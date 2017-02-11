package com.jiang.foodfaction.inter;

/**
 * Created by dllo on 17/2/11.
 */

public interface CallBack <T>{
    void onSuccess(T respomse);
    void onError(Throwable throwable);
}
