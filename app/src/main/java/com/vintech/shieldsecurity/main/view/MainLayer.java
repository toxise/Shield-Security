package com.vintech.shieldsecurity.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.vintech.shieldsecurity.MainActivity;
import com.vintech.shieldsecurity.R;
import com.vintech.shieldsecurity.framework.BaseActionEvent;
import com.vintech.util.display.AnimationFactory;
import com.vintech.util.display.DimensUtil;
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
            EventBus.getDefault().post(new BaseActionEvent(BaseActionEvent.ACTION_STOP_PROCESSING_ANIMATION));
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
            case BaseActionEvent.ACTION_START_PROCESSING_ANIMATION:
                mMainWorkspace.startProcessing();
                startProcessAnimation(true);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMainWorkspace.finishProcessing();
                    }
                }, DimensUtil.SECOND * 3);
                break;
            case BaseActionEvent.ACTION_STOP_PROCESSING_ANIMATION:
                mMainWorkspace.stopProcessing();
                startProcessAnimation(false);
                break;
            case BaseActionEvent.ACTION_FINISHED_PROCESSING_ANIMATION:
                // TODO: 2016/10/22 jump to result of scan
                MainActivity.getLayerManager().show(R.id.layer_onekey_result, null);
                break;
            case BaseActionEvent.ACTION_FINISH_PROCESSING:
                mMainWorkspace.finishProcessing();
                break;
        }
    }

    private void startProcessAnimation(boolean dispear) {
        View title = findViewById(R.id.title);
        View summary = findViewById(R.id.title_sub);
        View dock = findViewById(R.id.dock);


        int visibility = dispear ? INVISIBLE : VISIBLE;
        int titleDistance = title.getHeight() / 2;
        title.setVisibility(visibility);
        title.startAnimation(AnimationFactory.tslAlphaAnimation(dispear, 0, -titleDistance, 300));
        summary.setVisibility(visibility);
        summary.startAnimation(AnimationFactory.tslAlphaAnimation(dispear, 0, -titleDistance, 300));
        dock.setVisibility(visibility);
        dock.startAnimation(AnimationFactory.tslAlphaAnimation(dispear, 0f, 0.5f, 300));
    }
}
