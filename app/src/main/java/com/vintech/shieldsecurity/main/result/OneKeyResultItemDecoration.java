package com.vintech.shieldsecurity.main.result;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vintech.util.display.DimensUtil;

/**
 * Created by vincent on 2016/10/25.
 */
public class OneKeyResultItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int lr = DimensUtil.dp2Pixel(8);
        int tb = DimensUtil.dp2Pixel(4);
        outRect.set(lr, tb, lr, tb);
    }
}
