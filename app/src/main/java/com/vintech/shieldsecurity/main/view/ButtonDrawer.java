package com.vintech.shieldsecurity.main.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.animation.AnimationUtils;

import com.vintech.shieldsecurity.MainApplication;
import com.vintech.shieldsecurity.R;
import com.vintech.util.display.DimensUtil;
import com.vintech.util.display.GraphicUtil;

/**
 * Created by vincent on 2016/10/7.
 */

public class ButtonDrawer {
    private static final int PROCESSING_DURATION = 700;
    // 圆环的颜色
    private final int[] mRingColor = new int[]{
            R.color.scan_ring_1, R.color.scan_ring_2, R.color.scan_ring_3, R.color.scan_ring_4, R.color.scan_ring_5
    };
    // 圆环的弧长
    private final float[] mRingAngle = new float[]{
            120, 200, 160, 100, 135
    };
    // 旋转速度
    private final float[] mRingSpeed = new float[]{
            40, 25, 32, 15, 10
    };
    // 圆环的位置
    private final float[] mRingPosition = new float[mRingAngle.length];
    private Point mCenter = new Point();
    ;
    private float mRadius;
    ;
    private Paint mPaint = new Paint();
    private RectF mArcRect = new RectF();
    // 圆环的笔宽
    private int mRingSize = DimensUtil.dp2Pixel(2);
    // 圆环的会半径间距
    private int mRingSpace = mRingSize + DimensUtil.dp2Pixel(2);
    // 圆环动画的起始时间点
    private long mRotatingTime = 0;

    // 切换动画的时间点
    private long mProcessingTime = 0;


    //===
    // 圆按钮的背景色
    private int mScanBg;

    public ButtonDrawer() {
        for (int i = 0; i < mRingPosition.length; i++) {
            mRingPosition[i] = (float) (Math.random() * 360);
        }

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mRingSize);
        mScanBg = GraphicUtil.getColor(MainApplication.getContext(), R.color.scan_bg);
    }

    public void initSize(Point center, int radius) {
        mCenter.set(center.x, center.y);
        mRadius = radius;
    }

    public void drawButton(Canvas canvas) {
        drawCircle(canvas);
        drawRings(canvas);
    }

    public void startProcessAnimation() {
        mProcessingTime = AnimationUtils.currentAnimationTimeMillis();
    }

    public float getInnerRadius() {
        return mRadius - mRingPosition.length * mRingSpace;
    }

    private void drawCircle(Canvas canvas) {
        float circleRadius = mRadius - mRingPosition.length * mRingSpace;
        mPaint.setColor(mScanBg);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCenter.x, mCenter.y, circleRadius, mPaint);
    }

    private void drawRings(Canvas canvas) {
        float tRotating = animateRotating();
        float tProcessing = animateProcessing();

        mPaint.setStyle(Paint.Style.STROKE);


        // 遍历，绘制圆环
        for (int i = 0; i < mRingPosition.length; i++) {
            float anglePos = mRingPosition[i] + tRotating * mRingSpeed[i]; // 圆环起点
            float angleSweep = mRingAngle[i] + (360 - mRingAngle[i]) * tProcessing; // 圆环弧距

            mPaint.setColor(GraphicUtil.getColor(MainApplication.getContext(), mRingColor[i])); // 画笔颜色
            float radius = mRadius - i * mRingSpace; // 半径设定
            mArcRect.set(mCenter.x - radius, mCenter.y - radius, mCenter.x + radius, mCenter.y + radius); // 圆环的区域设定

            canvas.drawArc(mArcRect, anglePos, angleSweep, false, mPaint); // 绘制
        }
    }

    private float animateProcessing() {
        if (mProcessingTime == 0) {
            return 0;
        }

        if (mProcessingTime > 0) {
            float t = (float) (AnimationUtils.currentAnimationTimeMillis() - mProcessingTime) / PROCESSING_DURATION;
            return Math.min(1, t);
        } else {
            float t = 1 - (float) (AnimationUtils.currentAnimationTimeMillis() + mProcessingTime) / PROCESSING_DURATION;
            return Math.max(0, t);
        }
    }

    public void stopProcessing() {
        float t = animateProcessing();
        mProcessingTime = -AnimationUtils.currentAnimationTimeMillis();
        if (t > 0) {
            mProcessingTime += (1 - t) * PROCESSING_DURATION;
        }
    }

    private float animateRotating() {
        if (mRotatingTime <= 0) {
            mRotatingTime = AnimationUtils.currentAnimationTimeMillis();
        }
        if (mRotatingTime > AnimationUtils.currentAnimationTimeMillis()) {
            mRotatingTime = AnimationUtils.currentAnimationTimeMillis();
        }
        float t = (float) (AnimationUtils.currentAnimationTimeMillis() - mRotatingTime) / DimensUtil.SECOND;
        return t;
    }
}
