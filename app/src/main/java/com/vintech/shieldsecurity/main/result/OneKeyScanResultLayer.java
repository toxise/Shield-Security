package com.vintech.shieldsecurity.main.result;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vintech.shieldsecurity.MainActivity;
import com.vintech.shieldsecurity.R;
import com.vintech.shieldsecurity.framework.view.CollapsingHeader;
import com.vintech.util.layer.DrawerLayer;

/**
 * Created by vincent on 2016/10/22.
 */

public class OneKeyScanResultLayer extends DrawerLayer implements CollapsingHeader.OnOffsetChangedListener {
    private RecyclerView mListView;
    private AppBarLayout mAppBarLayout;
    private CollapsingHeader mOneKeyResultHeader;
    private TextView mTitleText;
    private TextView mTitleSubText;

    public OneKeyScanResultLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mListView = (RecyclerView) findViewById(R.id.list);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mOneKeyResultHeader = (CollapsingHeader) findViewById(R.id.toolbar);
        mTitleText = (TextView) mOneKeyResultHeader.findViewById(R.id.title);
        mTitleSubText = (TextView) mOneKeyResultHeader.findViewById(R.id.title_sub);
        mTitleText.setAlpha(0);
        mAppBarLayout.addOnOffsetChangedListener(mOneKeyResultHeader);
        mOneKeyResultHeader.setOnOffsetChangedListener(this);
        initData();
    }

    private void initData() {
        mListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mListView.setAdapter(new OneKeyResultAdapter());
    }

    @Override
    public boolean onBackKey() {
        MainActivity.getLayerManager().hide(R.id.layer_one_key_result, null);
        return true;
    }

    @Override
    public void onOffsetChanged(float t) {
        float alpha = 0;
        float line = 0.7f;
        if (t > line) {
            alpha = ((t - line) / (1f - line));
        }
        mTitleText.setAlpha(alpha);

        line = 0.6f;
        if (t < line) {
            alpha = 1 - t / line;
        } else {
            alpha = 0;
        }
        mTitleSubText.setAlpha(alpha);
    }
}
