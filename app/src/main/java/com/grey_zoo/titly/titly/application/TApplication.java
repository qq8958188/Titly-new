package com.grey_zoo.titly.titly.application;

import android.app.Application;
import android.content.Context;

import com.grey_zoo.titly.titly.class_loader.LoadClassConfiguration;

/**
 * Created by Administrator on 2015/9/28 0028.
 */
public class TApplication extends Application {

    public static Context GlobalContext=null;

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext=this;
        LoadClassConfiguration.loadClass();
    }
}
