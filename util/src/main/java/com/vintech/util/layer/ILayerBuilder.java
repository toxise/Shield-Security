package com.vintech.util.layer;

import android.view.ViewGroup;

/**
 * Created by vincent on 2016/10/6.
 */

public interface ILayerBuilder {
    DrawerLayer buildLayer(ViewGroup parent, int layerId);
}
