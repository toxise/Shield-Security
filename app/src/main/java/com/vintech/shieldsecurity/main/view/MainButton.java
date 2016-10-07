package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vintech.shieldsecurity.R;
import com.vintech.util.Device;
import com.vintech.util.debug.Logger;
import com.vintech.util.display.DimensUtil;

/**
 * Created by vincent on 2016/10/6.
 */

public class MainButton extends FrameLayout {

    public TextView mTipsStatus;
    public TextView mTipsSummary;
    private int mRadius;
    private Point mCenter = new Point();
    private Paint mPaint = new Paint();

    public MainButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawButton(canvas);
        super.dispatchDraw(canvas);
    }

    private void drawButton(Canvas canvas) {
        mPaint.setColor(0xffff9988);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius, mPaint);
    }

    @Override
    protected void onFinishInflate() {
        mTipsStatus = (TextView) findViewById(R.id.tips_status);
        mTipsSummary = (TextView) findViewById(R.id.tips_summary);
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
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mTipsStatus == null || mTipsSummary == null) {
            return;
        }
        int tipsTop = mCenter.y + mRadius + DimensUtil.dp2Pixel(20);
        Logger.pgw("MainButton.onLayout tipsTop=" + tipsTop);
        mTipsStatus.setTranslationY(tipsTop);
        mTipsSummary.setTranslationY(tipsTop);
    }
}
