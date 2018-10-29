package cn.edu.hytc.schoolhelper.common;

import android.app.Application;
import android.content.Context;

import cn.edu.hytc.schoolhelper.http.HttpMethods;

/**
 * Created by admin on 2018/10/16.
 */

public class MyApplication extends Application {
    public static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.getInstance();
        HttpMethods.getInstance();
       // ImageLoaderManager.getInstance();
    }
}
