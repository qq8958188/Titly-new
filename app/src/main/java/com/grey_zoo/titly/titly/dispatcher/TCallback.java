package com.grey_zoo.titly.titly.dispatcher;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

/**
 * 回调函数
 * @param <T> 需要的返回类型
 */
public abstract class TCallback<T> {

    /**
     * 成功回调
     * @param result
     */
    public abstract void onSuccess(T result);

    /**
     * 失败回调
     * @param srrMsg
     */
    public abstract void onError(String srrMsg);

}
