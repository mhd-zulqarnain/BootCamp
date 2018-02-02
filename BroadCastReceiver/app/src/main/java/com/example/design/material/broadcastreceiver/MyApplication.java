package com.example.design.material.broadcastreceiver;

import android.app.Application;
import android.content.Context;

/**
 * Created by zulup on 2/2/2018.
 */

public class MyApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.mContext =getApplicationContext();
    }
    public static Context getContext(){
        return mContext;
    }
}
