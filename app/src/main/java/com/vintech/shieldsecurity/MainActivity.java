package com.vintech.shieldsecurity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.vintech.shieldsecurity.framework.LayerBuilder;
import com.vintech.util.debug.Logger;
import com.vintech.util.layer.LayerManager;

public class MainActivity extends BaseActivity {

    private static LayerManager sLayerManager;
    private ViewGroup mMainView;

    public static LayerManager getLayerManager() {
        return sLayerManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.pgw("MainActivity.onCreate");
        setContentView(R.layout.activity_main);
        mMainView = (ViewGroup) findViewById(R.id.root_view);
        sLayerManager = new LayerManager(LayerBuilder.getInstance(), mMainView);
        sLayerManager.show(R.id.layer_main, null);
        sLayerManager.show(R.id.layer_one_key_result, null);
    }

    @Override
    public void onBackPressed() {
        boolean handled = sLayerManager.handleBackKey();
        if (!handled) {
            super.onBackPressed();
        }
    }
}
