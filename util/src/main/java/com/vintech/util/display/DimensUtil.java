package com.vintech.util.display;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.vintech.util.debug.Logger;

/**
 * Created by vincent on 2016/10/6.
 */

public class DimensUtil {
    public static final long SECOND = 1000;
    public static final long MIN = 60 * SECOND;
    public static final long HOUR = 60 * MIN;
    private static float sDpi;
    private static int sDisplayWidth;
    private static int sDisplayHeight;
    private static int sRealWidth;
    private static int sRealHeight;
    private static int sStatusBarHeight;
    private static int sNavBarHeight = 0;

    public static void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        sDpi = metrics.density;
        sDisplayWidth = metrics.widthPixels;
        sDisplayHeight = metrics.heightPixels;
        sStatusBarHeight = dp2Pixel(25);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            wm.getDefaultDisplay().getRealSize(p);
            sRealWidth = p.x;
            sRealHeight = p.y;
            Logger.pgw("sRealHeight= " + sRealHeight);
            sNavBarHeight = Math.max(0, sRealHeight - sDisplayHeight - sStatusBarHeight);
        } else {
            sRealWidth = sDisplayWidth;
            sRealHeight = sDisplayHeight;
        }
        Logger.pgw("disH= " + sDisplayHeight + ", statusH=" + sStatusBarHeight + ", navBarHeight= " + sNavBarHeight);
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

    public static int getNavBarHeight() {
        return sNavBarHeight;
    }
}
