package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vintech.shieldsecurity.R;
import com.vintech.shieldsecurity.framework.BaseActionEvent;
import com.vintech.util.Device;
import com.vintech.util.display.AnimationFactory;
import com.vintech.util.display.DimensUtil;
import com.vintech.util.display.GraphicUtil;
import com.vintech.util.touch.ClickHandler;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by vincent on 2016/10/6.
 */

public class MainWorkspace extends FrameLayout {

    private TextView mTipsStatus;
    private TextView mTipsSummary;
    private TextView mButtonScan;
    private TextView mButtonSummary;
    private TextView mTipsProcessing;
    private TextView mTipsProcessingUnit;

    private int mRadius;
    private Point mCenter = new Point();
    private ButtonDrawer mButtonDrawer;
    private Status mStatus = Status.NORMAL;
    private ClickHandler mClickHandler = new ClickHandler() {
        @Override
        public void notifiClick() {
            mStatus = Status.SCANING;
            EventBus.getDefault().post(new BaseActionEvent(BaseActionEvent.ACTION_START_PROCESSING));
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
        mTipsProcessing = (TextView) findViewById(R.id.scan_processing);
        mTipsProcessingUnit = (TextView) findViewById(R.id.scan_processing_unit);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
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

        int processingTop = mCenter.y - mTipsProcessing.getHeight() / 2;
        mTipsProcessing.offsetTopAndBottom(processingTop);
        mTipsProcessingUnit.offsetTopAndBottom(processingTop + mTipsProcessing.getHeight() - mTipsProcessingUnit.getHeight() - DimensUtil.dp2Pixel(10));
        mTipsProcessingUnit.offsetLeftAndRight(mTipsProcessing.getWidth() / 2 + DimensUtil.dp2Pixel(15));
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
        startButtonTextAnimation(false);
    }

    public void startProcessing() {
        mButtonDrawer.startProcessAnimation();
        startButtonTextAnimation(true);
    }

    private void startButtonTextAnimation(boolean toProcessing) {
        // 一键扫描的文案动画
        int buttonTextFrom = toProcessing ? 1 : 0;
        int buttonTextTo = toProcessing ? 0 : 1;
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(buttonTextFrom, buttonTextTo, buttonTextFrom, buttonTextTo, Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, mCenter.y - mButtonScan.getTop());
        AlphaAnimation alphaAnimation = AnimationFactory.alphaAnimation(toProcessing, 500);
        scaleAnimation.setDuration(500);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setStartOffset(toProcessing ? 0 : 500);
        set.setFillBefore(true);
        mButtonScan.startAnimation(set);
        mButtonScan.setVisibility(toProcessing ? INVISIBLE : VISIBLE);

        set = new AnimationSet(false);
        scaleAnimation = new ScaleAnimation(buttonTextFrom, buttonTextTo, buttonTextFrom, buttonTextTo, Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, mCenter.y - mButtonSummary.getTop());
        scaleAnimation.setDuration(500);
        alphaAnimation = AnimationFactory.alphaAnimation(toProcessing, 500);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setStartOffset(toProcessing ? 0 : 500);
        set.setFillBefore(true);
        mButtonSummary.startAnimation(set);
        mButtonSummary.setVisibility(toProcessing ? INVISIBLE : VISIBLE);

        // 进度文字的动画
        set = new AnimationSet(false);
        scaleAnimation = AnimationFactory.scaleAnimation(!toProcessing, 500);
        alphaAnimation = AnimationFactory.alphaAnimation(!toProcessing, 500);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setStartOffset(toProcessing ? 500 : 0);
        set.setFillBefore(true);
        mTipsProcessing.setVisibility(toProcessing ? VISIBLE : INVISIBLE);
        mTipsProcessing.startAnimation(set);

        alphaAnimation = AnimationFactory.alphaAnimation(!toProcessing, 500);
        alphaAnimation.setStartOffset(toProcessing ? 500 : 0);
        alphaAnimation.setFillBefore(true);
        mTipsProcessingUnit.setVisibility(toProcessing ? VISIBLE : INVISIBLE);
        mTipsProcessingUnit.startAnimation(alphaAnimation);
    }

    public enum Status {
        NORMAL, SCANING
    }
}
