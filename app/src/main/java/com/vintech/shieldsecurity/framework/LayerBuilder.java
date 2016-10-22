package com.vintech.shieldsecurity.framework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vintech.shieldsecurity.R;
import com.vintech.util.layer.DrawerLayer;
import com.vintech.util.layer.ILayerBuilder;

/**
 * Created by vincent on 2016/10/6.
 */

public class LayerBuilder implements ILayerBuilder {
    private static LayerBuilder sInstance;

    private LayerBuilder() {
    }

    public static LayerBuilder getInstance() {
        if (sInstance == null) {
            synchronized (LayerBuilder.class) {
                if (sInstance == null) {
                    sInstance = new LayerBuilder();
                }
            }
        }
        return sInstance;
    }


    @Override
    public DrawerLayer buildLayer(ViewGroup parent, int layerId) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layer = null;
        switch (layerId) {
            case R.id.layer_main:
                layer = inflater.inflate(R.layout.layer_main, parent, false);
                break;
            case R.id.layer_onekey_result:
                layer = inflater.inflate(R.layout.layer_onekey_result, parent, false);
                break;
        }

        if (layer != null && layer instanceof DrawerLayer) {
            return (DrawerLayer) layer;
        }

        return null;
    }
}
