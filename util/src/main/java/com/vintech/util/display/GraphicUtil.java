package com.vintech.util.display;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by vincent on 2016/10/6.
 */

public class GraphicUtil {

    public static <T extends Drawable> T getDrawable(Context context, int resId) {
        return (T) context.getResources().getDrawable(resId);
    }

    public static Bitmap getBitmap(Context context, int resId) {
        BitmapDrawable d = getDrawable(context, resId);
        return d.getBitmap();
    }

    public static int getColor(Context context, int resId) {
        return context.getResources().getColor(resId);
    }
}
