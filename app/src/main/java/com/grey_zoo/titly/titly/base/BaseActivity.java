package com.grey_zoo.titly.titly.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/9/24 0024.
 */
public abstract class BaseActivity extends Fragment {

    public View view;
    private Context ct;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        onCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ct = getActivity();
    }

    /**
     * setContentView;
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = onCreateView(inflater);

        return view;
    }

    /**
     * 初始化view，相当于Activity中setContentView
     */
    public abstract View onCreateView(LayoutInflater inflater);

    /**
     * 已完成创建，相当于Activity中setContentView后
     */
    public abstract void onCreated(Bundle savedInstanceState);

}
