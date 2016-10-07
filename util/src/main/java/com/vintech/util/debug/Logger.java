package com.vintech.util.debug;

import android.util.Log;

/**
 * Created by vincent on 2016/10/6.
 */

public class Logger {

    public static final boolean DEBUG = true;

    public static final void pgw(Object object) {
        if (!DEBUG) {
            return;
        }
        Log.d("PGW", "" + object);
    }


    public static final void pgw(Object object, Throwable e) {
        if (!DEBUG) {
            return;
        }
        Log.d("PGW", "" + object, e);
    }
}
