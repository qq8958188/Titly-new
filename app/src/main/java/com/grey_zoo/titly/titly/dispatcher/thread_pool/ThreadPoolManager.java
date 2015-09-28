package com.grey_zoo.titly.titly.dispatcher.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/9/27 0027.
 */

/**
 * 线程池管理类，全局使用一个线程池
 */
public class ThreadPoolManager {

    private ExecutorService service = Executors.newFixedThreadPool(10);

    public void execute(Runnable task){
        service.execute(task);
    }

    public void close(){
        service.shutdown();
    }

}
