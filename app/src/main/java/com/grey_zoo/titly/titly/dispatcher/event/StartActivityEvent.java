package com.grey_zoo.titly.titly.dispatcher.event;

import android.os.Bundle;

import com.grey_zoo.titly.titly.base.BaseActivity;
import com.grey_zoo.titly.titly.dispatcher.TEvent;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

/**
 * 开启Activity事件
 */
public class StartActivityEvent extends TEvent{
    //目标名称
    public String model;
    //附带数据
    public Bundle bundle;
}
