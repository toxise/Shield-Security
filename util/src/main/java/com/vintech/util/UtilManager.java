package com.vintech.util;

import android.content.Context;

import com.vintech.util.display.DimensUtil;

/**
 * Created by vincent on 2016/10/6.
 */

public class UtilManager {

    public static void initUtilModule(Context context) {
        DimensUtil.init(context);
    }
}
