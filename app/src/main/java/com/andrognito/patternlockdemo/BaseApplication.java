package com.andrognito.patternlockdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxin on 2017/5/18.
 */

public class BaseApplication extends Application {
    public static final String TAG = "BaseApplication";
    /**
     * Activity
     */
    public List<BaseActivity> mActivityList = null;
    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context
                .getApplicationContext();
        return application.refWatcher;
    }



    //在自己的Application中添加如下代码
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {

        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    public void add(BaseActivity baseActivity) {
        if (mActivityList == null) {
            mActivityList = new ArrayList<>();
        }
        mActivityList.add(baseActivity);
        Log.d(TAG, "add: mActivityList.size() " + mActivityList.size());
    }

    public void remove(BaseActivity b) {
        if (mActivityList == null) {
            mActivityList = new ArrayList<>();
        }
        mActivityList.remove(b);
        Log.d(TAG, "add: mActivityList.size() " + mActivityList.size());
    }

    public int getActivityCount() {
        if (mActivityList != null) {
            return mActivityList.size();
        }
        return 0;
    }


}
