package com.vintech.util.display;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by vincent on 2016/10/6.
 */

public class DimensUtil {
    private static float sDpi;
    private static int sDisplayWidth;
    private static int sDisplayHeight;
    private static int sStatusBarHeight;

    public static void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        sDpi = metrics.density;
        sDisplayWidth = metrics.widthPixels;
        sDisplayHeight = metrics.heightPixels;
        sStatusBarHeight = dp2Pixel(25);
    }

    public static int getScreenWidth() {
        return sDisplayWidth;
    }

    public static int getScreenHeight() {
        return sDisplayHeight;
    }

    public static int dp2Pixel(float dp) {
        return (int) (dp * sDpi);
    }

    public static int getPixel(Context context, int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    public static int getStatusBarHeight() {
        return sStatusBarHeight;
    }
}
