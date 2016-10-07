package com.vintech.util.layer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vintech.util.Device;
import com.vintech.util.R;
import com.vintech.util.display.DimensUtil;

/**
 * Created by vincent on 2016/10/6.
 */

public class DrawerLayer extends RelativeLayout {

    public DrawerLayer(Context context) {
        super(context);
    }

    public DrawerLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onShow(LayerBundle bundle) {
        return false;
    }

    public boolean onHide(LayerBundle bundle) {
        return false;
    }

    public boolean onRemove(LayerBundle bundle) {
        return false;
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params,
                                      boolean preventRequestLayout) {
        fixStatusBarBelow(params);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            fixStatusBarBelow(childAt.getLayoutParams());
        }
    }

    private void fixStatusBarBelow(ViewGroup.LayoutParams params) {
        if (!(params instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        LayoutParams lp = (LayoutParams) params;
        int[] rules = lp.getRules();
        if (Device.KITKAT && rules[BELOW] == R.id.below_fit_status) {
            lp.topMargin = DimensUtil.getStatusBarHeight();
        }
    }
}
