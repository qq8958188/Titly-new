package com.grey_zoo.titly.titly.activity_manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grey_zoo.titly.titly.R;
import com.grey_zoo.titly.titly.application.TApplication;
import com.grey_zoo.titly.titly.base.BaseActivity;
import com.grey_zoo.titly.titly.dispatcher.TCallback;
import com.grey_zoo.titly.titly.dispatcher.TEvent;
import com.grey_zoo.titly.titly.dispatcher.TIntent;
import com.grey_zoo.titly.titly.dispatcher.TObserver;
import com.grey_zoo.titly.titly.dispatcher.event.ChangeActivityEvent;
import com.grey_zoo.titly.titly.dispatcher.event.StartActivityEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/24 0024.
 */

/**
 * 管理Activity之间通信，和打开等事件
 */
public class ActivityEventManager implements TObserver {

    private JSONObject jsonObject;
    /**
     * nickname：Activity类
     */
    private Map<String, String> map = new HashMap<String, String>();
    /**
     * Activity类：加载的类
     */
    private Map<String, Class> classMap = new HashMap<String, Class>();

    private static ActivityEventManager instance=new ActivityEventManager();

    public static ActivityEventManager getInstance(){
        return instance;
    }

    private ActivityEventManager() {
        String jsonStr = readFile();
        jsonObject = JSON.parseObject(jsonStr);
        parseJson();
        registerEvent();
    }

    /**
     * 从JSON文件中拿到配置项
     */
    private void parseJson() {
        JSONArray jsonArray=jsonObject.getJSONArray("Activitys");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            for (String key:object.keySet()) {
                map.put(key, object.getString(key));
            }
        }
    }

    @Override
    public void onEvent(final TEvent event, final TCallback callback) {
        if (event == null) {
            return;
        }
        //开启Activity事件
        if (event instanceof StartActivityEvent) {
            StartActivityEvent startActivityEvent = (StartActivityEvent) event;
            String clazz = map.get(startActivityEvent.model);
            if (null != clazz) {
                try {
                    Class c;
                    if (null != classMap.get(clazz)) {
                        c = classMap.get(clazz);
                    } else {
                        c = Class.forName(clazz);
                        classMap.put(clazz, c);
                    }
                    final TObserver observer = (TObserver) c.newInstance();
                    changeActivity((BaseActivity) observer,
                            new TCallback() {
                                @Override
                                public void onSuccess(Object result) {
                                    //成功了，分派事件
                                    observer.onEvent(event,callback);
                                }

                                @Override
                                public void onError(String srrMsg) {
                                    //失败了
                                }
                            });
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
     * 注册事件
     */
    public void registerEvent() {
        //注册开始Activity事件
        TIntent.getInstance().registerEvent(StartActivityEvent.class,this);
    }

    /**
     * 读取配置文件
     *
     * @return
     */
    public String readFile() {
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
//            reader = new BufferedReader(new FileReader("raw/activitylist.json"));
            InputStream inputStream = TApplication.GlobalContext.getResources().openRawResource(R.raw.activitylist);
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while (null != (str=reader.readLine())) {
                buffer.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * 派发切换Activity事件
     * @param to
     */
    public <T> void changeActivity(BaseActivity to,TCallback callback) {
        ChangeActivityEvent event=new ChangeActivityEvent();
        event.to=to;
        TIntent.getInstance().dispatch(event,callback);
    }

}
