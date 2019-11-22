package com.oasissnacks.oasissnacks.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

public class ButtonAnimation {

    public static Animation createButtonAnimation(){
        Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(50); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(0); // Repeat animation infinitely
        return animation;
    }

}
