package com.vintech.shieldsecurity.framework.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.vintech.shieldsecurity.R;
import com.vintech.util.Device;
import com.vintech.util.display.DimensUtil;

/**
 * Created by vincent on 2016/10/22.
 */

public class CollapsingHeader extends FrameLayout implements AppBarLayout.OnOffsetChangedListener {
    private int mRecordMinHeight;
    private OnOffsetChangedListener mOnOffsetChangedListener;

    public CollapsingHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRecordMinHeight = DimensUtil.getPixel(context, R.dimen.actionBarSize);
        if (Device.KITKAT) {
            mRecordMinHeight += DimensUtil.getStatusBarHeight();
            setPadding(0, DimensUtil.getStatusBarHeight(), 0, 0);
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
        View pinMode = findViewById(R.id.pin_mode);
        if (pinMode != null) {
            int height = pinMode.getHeight();
            pinMode.setTop(-verticalOffset + getPaddingTop());
            pinMode.setBottom(pinMode.getTop() + height);
        }
    }

    public void setOnOffsetChangedListener(OnOffsetChangedListener listener) {
        mOnOffsetChangedListener = listener;
    }

    public interface OnOffsetChangedListener {
        void onOffsetChanged(float t);
    }
}
