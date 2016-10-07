package com.vintech.util.layer;

import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 2016/10/6.
 */

public class LayerManager {

    private ILayerBuilder mBuilder;
    private ViewGroup mParent;
    private Map<Integer, DrawerLayer> mLayer = new HashMap<Integer, DrawerLayer>();

    public LayerManager(ILayerBuilder builder, ViewGroup parent) {
        mBuilder = builder;
        mParent = parent;
    }

    public void show(int layerId, LayerBundle bundle) {
        DrawerLayer layer = mLayer.get(layerId);
        boolean show = false;
        if (layer != null) {
            if (layer.getParent() == null) {
                mParent.addView(layer);
                show = true;
            } else if (layer.getVisibility() != View.VISIBLE) {
                show = true;
            }
        } else {
            layer = mBuilder.buildLayer(mParent, layerId);
            layer.setId(layerId);
            mParent.addView(layer);
            show = true;
        }

        if (show) {
            boolean handled = layer.onShow(bundle);
            if (!handled) {
                layer.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hide(int layerId, LayerBundle bundle) {
        final DrawerLayer layer = mLayer.get(layerId);
        if (layer == null) {
            return;
        }

        boolean handled = layer.onHide(bundle);
        if (!handled) {
            remove(layerId, bundle);
        }
    }

    public void remove(int layerId, LayerBundle bundle) {
        final DrawerLayer layer = mLayer.get(layerId);
        layer.setVisibility(View.GONE);
        mParent.post(new Runnable() {
            @Override
            public void run() {
                mParent.removeView(layer);
            }
        });
    }
}
