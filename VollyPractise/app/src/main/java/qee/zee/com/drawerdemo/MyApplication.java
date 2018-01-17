package qee.zee.com.drawerdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Zul Qarnain on 1/4/2018.
 */

public class MyApplication extends Application{
    private  static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
    public static MyApplication getsInstance(){
        return sInstance;
    }
    public static Context getAppContext(){

        return sInstance.getApplicationContext();
    }
}
