package com.vintech.util.layer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

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

    public boolean onBackKey() {
        return false;
    }
}
