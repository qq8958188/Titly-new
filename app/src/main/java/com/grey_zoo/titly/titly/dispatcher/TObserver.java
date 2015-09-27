package com.grey_zoo.titly.titly.dispatcher;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

/**
 * 事件相应的观察者
 */
public interface TObserver {

    /**
     * 响应事件
     * @param event 事件参数对象
     * @param callback 回调方法
     */
    public void onEvent(TEvent event,TCallback callback);

}
