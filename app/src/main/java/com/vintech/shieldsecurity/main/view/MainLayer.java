package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.vintech.shieldsecurity.R;
import com.vintech.shieldsecurity.framework.BaseActionEvent;
import com.vintech.util.display.AnimationFactory;
import com.vintech.util.layer.DrawerLayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
            EventBus.getDefault().post(new BaseActionEvent(BaseActionEvent.ACTION_STOP_PROCESSING));
            return true;
        }
        return super.onBackKey();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAction(BaseActionEvent event) {
        switch (event.getAction()) {
            case BaseActionEvent.ACTION_START_PROCESSING:
                mMainWorkspace.startProcessing();
                startProcessAnimation(true);
                break;
            case BaseActionEvent.ACTION_STOP_PROCESSING:
                mMainWorkspace.stopProcessing();
                startProcessAnimation(false);
                break;
        }
    }

    private void startProcessAnimation(boolean disapear) {
        View title = findViewById(R.id.title);
        View summary = findViewById(R.id.titile_sub);
        View dock = findViewById(R.id.dock);


        int visibility = disapear ? INVISIBLE : VISIBLE;
        int titleDistance = title.getHeight() / 2;
        title.setVisibility(visibility);
        title.startAnimation(AnimationFactory.tslAlphaAnimation(disapear, 0, -titleDistance, 300));
        summary.setVisibility(visibility);
        summary.startAnimation(AnimationFactory.tslAlphaAnimation(disapear, 0, -titleDistance, 300));
        dock.setVisibility(visibility);
        dock.startAnimation(AnimationFactory.tslAlphaAnimation(disapear, 0f, 0.5f, 300));
    }
}
