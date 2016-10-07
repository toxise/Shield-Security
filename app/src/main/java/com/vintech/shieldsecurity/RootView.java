package com.vintech.shieldsecurity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by vincent on 2016/10/6.
 */

public class RootView extends FrameLayout {
    public RootView(Context context) {
        super(context);
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        View p = (View) getParent();

//        for (int i = 0; i <getChildCount(); i++) {
//            View childAt = getChildAt(i);
//            Logger.pgw("RootView.onLayout  view.measureHeight= " + childAt.getMeasuredHeight());
//        }
//        Logger.pgw("RootView.onLayout (" + l + "," + t + " - " + r + "," + b + ")" + ", parent= "  + p.getWidth() + "," + p.getHeight());
    }
}
