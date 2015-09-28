package com.grey_zoo.titly.titly.home;

import android.app.ActivityManager;
import android.content.Context;
import android.os.*;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.grey_zoo.titly.titly.R;
import com.grey_zoo.titly.titly.base.BaseActivity;
import com.grey_zoo.titly.titly.dispatcher.TCallback;
import com.grey_zoo.titly.titly.dispatcher.TEvent;
import com.grey_zoo.titly.titly.dispatcher.TIntent;
import com.grey_zoo.titly.titly.dispatcher.TObserver;
import com.grey_zoo.titly.titly.dispatcher.event.StartActivityEvent;

/**
 * Created by Administrator on 2015/9/24 0024.
 */
public class ActivityHome extends BaseActivity{

    //Button
    private Button button=null;

    @Override
    public View onCreateView(LayoutInflater inflater) {
        return View.inflate(getContext(), R.layout.activity_home,null);
    }

    @Override
    public void onCreated(Bundle savedInstanceState) {
        button= (Button) getActivity().findViewById(R.id.button_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityEvent event=new StartActivityEvent();
                event.model="test";
                TIntent.getInstance().dispatch(event,null);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("AH onKeyDown");
        if(keyCode == event.KEYCODE_BACK){
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
