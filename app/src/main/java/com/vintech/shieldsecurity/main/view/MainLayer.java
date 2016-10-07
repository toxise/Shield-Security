package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.util.AttributeSet;

import com.vintech.shieldsecurity.R;
import com.vintech.util.layer.DrawerLayer;

/**
 * Created by vincent on 2016/10/6.
 */

public class MainLayer extends DrawerLayer {
    private MainWorkspace mMainWorkspace;
    public MainLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        mMainWorkspace = (MainWorkspace) findViewById(R.id.main_content);
    }

    @Override
    public boolean onBackKey() {
        if (mMainWorkspace.getStatus() != MainWorkspace.Status.NORMAL) {
            mMainWorkspace.stopProcessing();
            return true;
        }
        return super.onBackKey();
    }
}
