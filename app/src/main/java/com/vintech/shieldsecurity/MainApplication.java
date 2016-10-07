package com.vintech.shieldsecurity;

import android.app.Application;
import android.content.Context;

import com.vintech.util.UtilManager;

/**
 * Created by vincent on 2016/10/6.
 */

public class MainApplication extends Application {
    private static MainApplication sInstance;

    public static Context getContext() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        UtilManager.initUtilModule(base);
    }
}
