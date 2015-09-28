package com.grey_zoo.titly.titly.class_loader;

/**
 * Created by Administrator on 2015/9/28 0028.
 */

import com.grey_zoo.titly.titly.activity_manager.ActivityEventManager;
import com.grey_zoo.titly.titly.dispatcher.TIntent;

/**
 * 预加载模块
 */
public class LoadClassConfiguration {

    public static void loadClass(){
        //加载分发模块
        TIntent.getInstance();
        //加载Activity管理模块
        ActivityEventManager.getInstance();
    }

}
