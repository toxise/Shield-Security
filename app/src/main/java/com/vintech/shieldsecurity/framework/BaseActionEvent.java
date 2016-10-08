package com.vintech.shieldsecurity.framework;

/**
 * Created by vincent on 2016/10/7.
 */
public class BaseActionEvent {
    public static final int ACTION_START_PROCESSING = 0;
    public static final int ACTION_STOP_PROCESSING = 1;


    private int mAction = -1;

    public BaseActionEvent(int action) {
        mAction = action;
    }

    public int getAction() {
        return mAction;
    }
}
