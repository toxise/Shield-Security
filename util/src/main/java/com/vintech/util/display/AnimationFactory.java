package com.vintech.util.display;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by vincent on 2016/10/7.
 */

public class AnimationFactory {
    public static final float TSL_ALPHA_DURATION_RATE = 1f;

    public static AlphaAnimation alphaAnimation(boolean disapear, int duration) {
        int from = disapear ? 1 : 0;
        int to = disapear ? 0 : 1;
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(duration);
        return alphaAnimation;
    }

    public static ScaleAnimation scaleAnimation(boolean disapear, int duration) {
        int from = disapear ? 1 : 0;
        int to = disapear ? 0 : 1;
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        return scaleAnimation;
    }

    public static AnimationSet tslAlphaAnimation(boolean disapear, float deltaX, float deltaY, int duration) {
        AnimationSet set = new AnimationSet(false);
        TranslateAnimation tsl = tslAnimation(disapear, deltaX, deltaY, duration);
        AlphaAnimation alpha = alphaAnimation(disapear, (int) (duration * TSL_ALPHA_DURATION_RATE));
        set.addAnimation(alpha);
        set.addAnimation(tsl);
        return set;
    }

    public static AnimationSet tslAlphaAnimation(boolean disapear, int deltaX, int deltaY, int duration) {
        AnimationSet set = new AnimationSet(false);
        TranslateAnimation tsl = tslAnimation(disapear, deltaX, deltaY, duration);
        AlphaAnimation alpha = alphaAnimation(disapear, (int) (duration * TSL_ALPHA_DURATION_RATE));
        set.addAnimation(alpha);
        set.addAnimation(tsl);
        return set;
    }

    public static TranslateAnimation tslAnimation(boolean disapear, float deltaX, float deltaY, int duration) {
        float fromX = disapear ? 0 : deltaX;
        float toX = disapear ? deltaX : 0;

        float fromY = disapear ? 0 : deltaY;
        float toY = disapear ? deltaY : 0;
        TranslateAnimation tsl = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX, Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        tsl.setDuration(duration);
        return tsl;
    }


    public static TranslateAnimation tslAnimation(boolean disapear, int deltaX, int deltaY, int duration) {
        float fromX = disapear ? 0 : deltaX;
        float toX = disapear ? deltaX : 0;

        float fromY = disapear ? 0 : deltaY;
        float toY = disapear ? deltaY : 0;
        TranslateAnimation tsl = new TranslateAnimation(fromX, toX, fromY, toY);
        tsl.setDuration(duration);
        return tsl;
    }
}
