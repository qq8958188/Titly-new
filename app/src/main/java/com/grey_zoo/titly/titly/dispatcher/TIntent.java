package com.grey_zoo.titly.titly.dispatcher;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

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

    //分发事件
    private TIntent(){

    }

    public static TIntent getInstance(){
        return instance;
    }

    public void dispatch(){

    }

    public void register(){

    }

    public void unregister(){

    }

}
