package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.vintech.shieldsecurity.R;

/**
 * Created by vincent on 2016/10/6.
 */

public class ColorFadeView extends View {
    private int mColor;

    public ColorFadeView(Context context) {
        super(context);
    }

    public ColorFadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ColorFadeView);
        mColor = typedArray.getColor(R.styleable.ColorFadeView_color, Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(mColor);
    }
}
