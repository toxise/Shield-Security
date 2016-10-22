package com.vintech.shieldsecurity.database;

import com.vintech.shieldsecurity.MainApplication;

/**
 * Created by vincent on 2016/10/9.
 */

public class DatabaseManager {
    private static DatabaseManager sInstance = null;
    private DatabaseHelper mDbHelper;

    private DatabaseManager() {
        mDbHelper = new DatabaseHelper(MainApplication.getContext());
    }

    public static DatabaseManager getInstance() {
        if (sInstance == null) {
            synchronized (DatabaseManager.class) {
                if (sInstance == null) {
                    sInstance = new DatabaseManager();
                }
            }
        }
        return sInstance;
    }
}
