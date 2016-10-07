package com.vintech.util.touch;

import android.view.MotionEvent;

import com.vintech.util.display.DimensUtil;
import com.vintech.util.display.GraphicUtil;

/**
 * Created by vincent on 2016/10/7.
 */

public abstract class ClickHandler {
    private long mTouchDownTime = 0;
    private long TOUCH_SLOP = DimensUtil.dp2Pixel(6);
    private float mDownX;
    private float mDownY;

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;
                mTouchDownTime = System.currentTimeMillis();
                return judgeArea(x, y);
            case MotionEvent.ACTION_UP:
                if (System.currentTimeMillis() - mTouchDownTime < 600 && judgeArea(x, y) && GraphicUtil.inCircle(mDownX, mDownY, TOUCH_SLOP, x, y)) {
                    notifiClick();
                }
            case MotionEvent.ACTION_CANCEL:
                mTouchDownTime = 0;
        }

        return false;
    }

    public abstract void notifiClick();

    public abstract boolean judgeArea(float x, float y);
}
