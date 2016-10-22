package com.vintech.shieldsecurity.framework;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by vincent on 2016/10/7.
 */
public class BaseActionEvent {
    public static final int ACTION_START_PROCESSING_ANIMATION = 0;
    public static final int ACTION_STOP_PROCESSING_ANIMATION = 1;
    public static final int ACTION_FINISHED_PROCESSING_ANIMATION = 2;

    public static final int ACTION_FINISH_PROCESSING = 3;


    private int mAction = -1;

    public BaseActionEvent(int action) {
        mAction = action;
    }

    public int getAction() {
        return mAction;
    }

    public static void send(int action) {
        EventBus.getDefault().post(new BaseActionEvent(action));
    }
}
