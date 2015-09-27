package com.grey_zoo.titly.titly.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.grey_zoo.titly.titly.R;
import com.grey_zoo.titly.titly.base.BaseActivity;
import com.grey_zoo.titly.titly.dispatcher.TCallback;
import com.grey_zoo.titly.titly.dispatcher.TEvent;
import com.grey_zoo.titly.titly.dispatcher.TObserver;

/**
 * Created by Administrator on 2015/9/24 0024.
 */
public class ActivityHome extends BaseActivity implements TObserver{

    @Override
    public View onCreateView(LayoutInflater inflater) {
        return View.inflate(getContext(), R.layout.activity_home,null);
    }

    @Override
    public void onCreated(Bundle savedInstanceState) {
        
    }

    @Override
    public void onEvent(TEvent event, TCallback callback) {

    }
}
