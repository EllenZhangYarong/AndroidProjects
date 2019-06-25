package com.ellen.tasknineanimation;

import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by ellen on 15/12/11.
 */
public class DisplayNextView implements Animation.AnimationListener {


    ImageView ivFlipMad;

    public DisplayNextView(ImageView ivFirst) {

        this.ivFlipMad = ivFirst;

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        ivFlipMad.post(new SwapViews(ivFlipMad));

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
}
