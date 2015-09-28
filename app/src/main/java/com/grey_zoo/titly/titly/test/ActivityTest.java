package com.grey_zoo.titly.titly.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.grey_zoo.titly.titly.R;
import com.grey_zoo.titly.titly.base.BaseActivity;

/**
 * Created by Administrator on 2015/9/28 0028.
 */
public class ActivityTest extends BaseActivity{
    @Override
    public View onCreateView(LayoutInflater inflater) {
        return View.inflate(getContext(), R.layout.activity_test,null);
    }

    @Override
    public void onCreated(Bundle savedInstanceState) {

    }
}
