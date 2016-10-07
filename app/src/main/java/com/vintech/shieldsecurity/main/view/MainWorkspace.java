package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vintech.shieldsecurity.R;
import com.vintech.util.Device;
import com.vintech.util.debug.Logger;
import com.vintech.util.display.DimensUtil;
import com.vintech.util.display.GraphicUtil;
import com.vintech.util.touch.ClickHandler;

/**
 * Created by vincent on 2016/10/6.
 */

public class MainWorkspace extends FrameLayout {

    private TextView mTipsStatus;
    private TextView mTipsSummary;
    private TextView mButtonScan;
    private TextView mButtonSummary;

    private int mRadius;
    private Point mCenter = new Point();
    private ButtonDrawer mButtonDrawer;
    private Status mStatus = Status.NORMAL;
    private ClickHandler mClickHandler = new ClickHandler() {
        @Override
        public void notifiClick() {
            mButtonDrawer.startProcessAnimation();
            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, mCenter.y - mButtonScan.getTop());
            scaleAnimation.setDuration(500);
            mButtonScan.startAnimation(scaleAnimation);

            scaleAnimation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, mCenter.y - mButtonSummary.getTop());
            scaleAnimation.setDuration(500);
            mButtonSummary.startAnimation(scaleAnimation);

            mStatus = Status.SCANING;
        }

        @Override
        public boolean judgeArea(float x, float y) {
            if (mStatus != Status.NORMAL) {
                return false;
            }
            return GraphicUtil.inCircle(mCenter.x, mCenter.y, mRadius, x, y);
        }
    };

    public MainWorkspace(Context context, AttributeSet attrs) {
        super(context, attrs);
        mButtonDrawer = new ButtonDrawer();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mButtonDrawer.drawButton(canvas);
        super.dispatchDraw(canvas);
        invalidate();
    }

    @Override
    protected void onFinishInflate() {
        mTipsStatus = (TextView) findViewById(R.id.tips_status);
        mTipsSummary = (TextView) findViewById(R.id.tips_summary);

        mButtonScan = (TextView) findViewById(R.id.scan);
        mButtonSummary = (TextView) findViewById(R.id.scan_summary);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Logger.pgw("MainButton.onSizeChanged h=" + h);
        int top = 0;
        int bottom = DimensUtil.dp2Pixel(100); // 底部留白空间
        if (Device.KITKAT) {
            top += DimensUtil.getStatusBarHeight(); // 状态栏的高度
        }
        top += DimensUtil.dp2Pixel(60); // 标题栏的高度
        int tipsHeight = DimensUtil.dp2Pixel(60); // 提示文案高度
        int side = DimensUtil.dp2Pixel(60); // 左右留白
        mRadius = Math.min(h - top - bottom - tipsHeight, w - side) / 2; // 圆的半径
        int contentHeight = mRadius * 2 + tipsHeight; // 圆 + 提示文案的总高度
        mCenter.set(getWidth() / 2, top + mRadius + (h - top - bottom - contentHeight) / 2);

        mButtonDrawer.initSize(mCenter, mRadius);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mTipsStatus == null || mTipsSummary == null || mButtonScan == null || mButtonSummary == null) {
            return;
        }
        int tipsTop = mCenter.y + mRadius + DimensUtil.dp2Pixel(20);
        mTipsStatus.offsetTopAndBottom(tipsTop);
        mTipsSummary.offsetTopAndBottom(tipsTop);

        int scanTop = mCenter.y - mButtonScan.getHeight() / 2 - DimensUtil.dp2Pixel(15);
        mButtonSummary.offsetTopAndBottom(scanTop);
        mButtonScan.offsetTopAndBottom(scanTop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mClickHandler.onTouchEvent(event);
    }

    public Status getStatus() {
        return mStatus;
    }

    public void stopProcessing() {
        mButtonDrawer.stopProcessing();
        mStatus = Status.NORMAL;
    }

    public enum Status {
        NORMAL, SCANING
    }
}
