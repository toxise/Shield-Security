package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vintech.shieldsecurity.R;
import com.vintech.util.Device;
import com.vintech.util.display.DimensUtil;
import com.vintech.util.display.GraphicUtil;

/**
 * Created by vincent on 2016/10/7.
 */

public class DockBar extends LinearLayout {
    private Paint mPaint = new Paint();
    private RectF mArcRect = new RectF();

    public DockBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(GraphicUtil.getColor(context, R.color.dock_bar_bg));
        mPaint.setStyle(Paint.Style.FILL);
        if (Device.KITKAT) {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + DimensUtil.getNavBarHeight());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawArcBackground(canvas);
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        drawArcBackground(canvas);
        super.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float arcExtra = w * 0.35f;
        mArcRect.set(-arcExtra, 0, w + arcExtra, getHeight() * 2);
    }

    private void drawArcBackground(Canvas canvas) {
        canvas.drawArc(mArcRect, -180, 180, true, mPaint);
    }
}
