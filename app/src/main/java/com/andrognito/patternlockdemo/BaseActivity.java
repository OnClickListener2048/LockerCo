package com.andrognito.patternlockdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by wangxin on 2017/5/29.
 */

public class BaseActivity extends Activity {
    private BaseApplication mApplication;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mApplication = (BaseApplication) this.getApplication();
        mApplication.add(this);
    }
}
