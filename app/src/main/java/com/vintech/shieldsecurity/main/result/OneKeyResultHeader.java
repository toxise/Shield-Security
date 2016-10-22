package com.vintech.shieldsecurity.main.result;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;

import com.vintech.shieldsecurity.R;
import com.vintech.util.Device;
import com.vintech.util.display.DimensUtil;

/**
 * Created by vincent on 2016/10/22.
 */

public class OneKeyResultHeader extends CollapsingToolbarLayout implements AppBarLayout.OnOffsetChangedListener {
    private int mRecordMinHeight;
    private OnOffsetChangedListener mOnOffsetChangedListener;

    public OneKeyResultHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRecordMinHeight = DimensUtil.getPixel(context, R.dimen.actionBarSize);
        if (Device.KITKAT) {
            setPadding(0, DimensUtil.getStatusBarHeight(), 0, 0);
            mRecordMinHeight += DimensUtil.getStatusBarHeight();
        }
        setMinimumHeight(mRecordMinHeight);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int delta = getHeight() - mRecordMinHeight;
        float t = Math.abs((float) verticalOffset / delta);
        if (mOnOffsetChangedListener != null) {
            mOnOffsetChangedListener.onOffsetChanged(t);
        }
    }

    public void setOnOffsetChangedListener(OnOffsetChangedListener listener) {
        mOnOffsetChangedListener = listener;
    }


    public interface OnOffsetChangedListener {
        void onOffsetChanged(float t);
    }
}
