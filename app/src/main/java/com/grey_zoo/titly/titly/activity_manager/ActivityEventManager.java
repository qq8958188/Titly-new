package com.grey_zoo.titly.titly.activity_manager;

import com.alibaba.fastjson.JSONArray;
import com.grey_zoo.titly.titly.base.BaseActivity;
import com.grey_zoo.titly.titly.dispatcher.TCallback;
import com.grey_zoo.titly.titly.dispatcher.TEvent;
import com.grey_zoo.titly.titly.dispatcher.TIntent;
import com.grey_zoo.titly.titly.dispatcher.TObserver;
import com.grey_zoo.titly.titly.dispatcher.event.StartActivityEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

/**
 * 管理Activity之间通信，和打开等事件
 */
public class ActivityEventManager implements TObserver {

    private JSONArray jsonArray;
    private Map<String,String> map=new HashMap<String,String>();
    private Map<String,Class> classMap=new HashMap<String,Class>();

    public ActivityEventManager(){
        String jsonStr=readFile();
        try {
            jsonArray=new JSONArray(jsonStr);
            parseJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化JSON
     */
    private void parseJson() {

        for(int i=0;i<jsonArray.length();++i){
            try {
                JSONObject object= (JSONObject) jsonArray.get(i);
                while(object.keys().hasNext()){
                    String key=object.keys().next();
                    map.put(key,object.getString(key));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onEvent(TEvent event, TCallback callback) {
        if(event==null){
            return;
        }
        //开启Activity事件
        if(event instanceof StartActivityEvent){
            StartActivityEvent startActivityEvent= (StartActivityEvent) event;
            String clazz = map.get(startActivityEvent.model);
            if(null != clazz){
                try {
                    Class c;
                    if(null != classMap.get(clazz)){
                        c=classMap.get(clazz);
                    }else {
                        c = Class.forName(clazz);
                        classMap.put(clazz, c);
                    }
                    TObserver observer = (TObserver) c.newInstance();
                    changeActivity(startActivityEvent.currentActivity, (BaseActivity) observer);
                    observer.onEvent(event,callback);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取配置文件
     * @return
     */
    public String readFile()  {
        StringBuffer buffer=new StringBuffer();
        BufferedReader reader = null;
        try {
            reader=new BufferedReader(new FileReader("raw/ActivityList.json"));
            while (null != reader.readLine()){
                buffer.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public void changeActivity(BaseActivity from,BaseActivity to){

        TIntent.getInstance().dispatch();

    }

}
