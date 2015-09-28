package com.grey_zoo.titly.titly.dispatcher;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

import com.grey_zoo.titly.titly.dispatcher.thread_pool.ThreadPoolManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中心分发机制
 */
public class TIntent {

    //单例设计模式
    private static TIntent instance=new TIntent();

    //key为事件的Class文件，value是响应这个事件的模块
    private Map<Class,List<TObserver>> map=new HashMap<Class,List<TObserver>>();

    private ThreadPoolManager threadPoolManager=null;

    //分发事件
    private TIntent(){
        threadPoolManager=new ThreadPoolManager();
//        initEvent();
    }

    /**
     * 初始化事件加载
     */
//    private void initEvent() {
//
//    }

    public static TIntent getInstance(){
        return instance;
    }

    /**
     * 分派任务
     */
    public <T> void dispatch(final TEvent event, final TCallback<T> callback){

        synchronized (this) {
            //找到要分派给谁
            List<TObserver> observers = map.get(event.getClass());
            for(final TObserver observer:observers){
                //异步形式进行分发
                threadPoolManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        observer.onEvent(event,callback);
                    }
                });
            }
        }

    }

    /**
     * 执行异步任务,本模块独立逻辑，如实在无办法，则可调用此方法
     * @param task
     */
    public void executeAsynRunnable(Runnable task){
        threadPoolManager.execute(task);
    }

    /**
     * 注册监听事件
     * @param clazz 事件的Clazz
     * @param observer 观察者
     */
    public synchronized void registerEvent(Class clazz,TObserver observer){
        List<TObserver> observers=null;
        if(null == map.get(clazz)){
            observers=new ArrayList<TObserver>();
            map.put(clazz,observers);
        }else {
            observers=map.get(clazz);
        }
        observers.add(observer);
    }

    /**
     *
     * @param clazz
     * @param observer
     */
    public synchronized void unregisterEvent(Class clazz,TObserver observer){
        if(null != map.get(clazz)){
          List<TObserver> list=map.get(clazz);
            list.remove(observer);
        }
    }

}
