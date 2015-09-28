package com.grey_zoo.titly.titly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;

import com.grey_zoo.titly.titly.base.BaseActivity;
import com.grey_zoo.titly.titly.dispatcher.TCallback;
import com.grey_zoo.titly.titly.dispatcher.TEvent;
import com.grey_zoo.titly.titly.dispatcher.TIntent;
import com.grey_zoo.titly.titly.dispatcher.TObserver;
import com.grey_zoo.titly.titly.dispatcher.event.ChangeActivityEvent;
import com.grey_zoo.titly.titly.dispatcher.event.StartActivityEvent;
import com.grey_zoo.titly.titly.home.ActivityHome;

import java.util.List;

/**
 * 单一Activity，全部界面统一使用
 */
public class MainActivity extends FragmentActivity implements TObserver {

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
     * @param to 要换到的fragment
     */
    public void changeToFragement(final Fragment to){

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, to);
        fragmentTransaction.addToBackStack(null); //步骤三：调用commit()方法使得FragmentTransaction实例的改变生效
        fragmentTransaction.commit();

    }

    /**
     * 开启首页
     */
    private void startHomeFragment(){

        StartActivityEvent startActivityEvent=new StartActivityEvent();
        startActivityEvent.model="home";
        TIntent.getInstance().dispatch(startActivityEvent, null);
    }

    /**
     * 进行初始化工作
     */
    private void init(){
        //注册事件
        TIntent.getInstance().registerEvent(ChangeActivityEvent.class,this);
        //开启首页
        startHomeFragment();
    }

    @Override
    public void onEvent(TEvent event, TCallback callback) {
        if(event instanceof ChangeActivityEvent) {
            ChangeActivityEvent changeActivityEvent= (ChangeActivityEvent) event;
            changeToFragement(changeActivityEvent.to);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("MA onKeyDown");
        BaseActivity baseActivity = (BaseActivity) getVisibleFragment();
        return baseActivity.onKeyDown(keyCode,event);
    }

    public boolean superKeyDown(int keyCode, KeyEvent event){
        System.out.println("MA superKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    public Fragment getVisibleFragment(){
        System.out.println("MA getVisibleFragment");
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment fragment : fragments){
            if(fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }
}
