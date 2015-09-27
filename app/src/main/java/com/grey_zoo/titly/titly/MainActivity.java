package com.grey_zoo.titly.titly;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.grey_zoo.titly.titly.home.ActivityHome;

/**
 * 单一Activity，全部界面统一使用
 */
public class MainActivity extends FragmentActivity {

    /**
     * fragment的管理者
     */
    private FragmentManager  fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //加载布局文件
        setContentView(R.layout.activity_main);
        //初始化
        init();
    }

    /**
     * 切换界面使用的方法
     * @param from 当前的fragment
     * @param to 要换到的fragment
     */
    public void changeToFragement(final Fragment from,final Fragment to){

        if(null == from || null == to){
            throw new NullPointerException("from 或者 to为 null");
        }

        if(from == to){
            return;
        }

        FragmentTransaction fragmentTransaction;
        //为了保证要么同时成功 要么同时失败 开启事务
        fragmentTransaction = fragmentManager.beginTransaction();

        if (!to.isAdded()) {    // 先判断是否被add过
            fragmentTransaction.hide(from).add(R.id.content_frame, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            fragmentTransaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }

    /**
     * 开启首页
     */
    private void startHomeFragment(){
        ActivityHome activityHome = new ActivityHome();
        FragmentTransaction fragmentTransaction;
        //为了保证要么同时成功 要么同时失败 开启事务
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, activityHome);
        fragmentTransaction.commit();//提交事务。
    }

    /**
     * 进行初始化工作
     */
    private void init(){
        //开启首页
        startHomeFragment();
    }

}
