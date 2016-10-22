package com.vintech.shieldsecurity.main.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.vintech.shieldsecurity.MainApplication;
import com.vintech.shieldsecurity.R;
import com.vintech.util.display.DimensUtil;
import com.vintech.util.display.GraphicUtil;

/**
 * Created by vincent on 2016/10/15.
 */

public class WaveDrawer {
    private Point mCenter = new Point();
    private float mRadius;
    private Paint mPain = new Paint();
    private Path mPath = new Path();
    private Path mClipPath = new Path();
    private float mWaveProcess = 0.0f;

    private float[] mWaveHeight = new float[]{
            DimensUtil.dp2Pixel(25),
            DimensUtil.dp2Pixel(10)
    };

    private float[] mWaveWidth = new float[]{
            DimensUtil.dp2Pixel(60),
            DimensUtil.dp2Pixel(80)
    };

    private float[] mWaveMove = new float[]{
            0, -50
    };

    private float[] mWaveMoveSpeed = new float[]{
            -2.5f, -4f
    };

    private int[] mWaveColor = new int[] {
            GraphicUtil.getColor(MainApplication.getContext(), R.color.wave_color_1),
            GraphicUtil.getColor(MainApplication.getContext(), R.color.wave_color_2)
    };

    public void initSize(Point center, float radius) {
        mCenter.set(center.x, center.y);
        mRadius = radius;
        mPain.setAntiAlias(true);
        mPain.setStyle(Paint.Style.FILL);
        mClipPath.reset();
        mClipPath.addCircle(mCenter.x, mCenter.y, mRadius, Path.Direction.CW);
    }

    public void drawWave(Canvas canvas) {
        canvas.save();
        canvas.clipPath(mClipPath);
        for (int i = 0; i < mWaveHeight.length; i++) {
            mWaveMove[i] += mWaveMoveSpeed[i];
            if (mWaveMove[i] > mWaveWidth[i] * 2) {
                mWaveMove[i] -= mWaveWidth[i] * 2;
            }

            if (mWaveMove[i] < -mWaveWidth[i] * 2) {
                mWaveMove[i] += mWaveWidth[i] * 2;
            }
            mPain.setColor(mWaveColor[i]);
            drawPath(canvas, mWaveMove[i], mWaveProcess * mRadius * 2, mWaveWidth[i], mWaveHeight[i]);
        }
        canvas.restore();
    }

    public void setProcess(float value) {
        mWaveProcess = Math.max(0, Math.min(value, 1));
    }

    private void drawPath(Canvas canvas, float moveX, float moveY, float width, float height) {
        mPath.reset();

        mPath.moveTo(mCenter.x - mRadius + moveX, mCenter.y + mRadius);
        mPath.rLineTo(0, -moveY);

        float w = moveX;
        while (w < mCenter.x + mRadius) {
            mPath.rCubicTo(width / 2, height, width * 1.5f, -height, width * 2, 0);
            w += width * 2;
        }
        mPath.rLineTo(0, moveY);
        mPath.close();

        canvas.drawPath(mPath, mPain);
    }
}
