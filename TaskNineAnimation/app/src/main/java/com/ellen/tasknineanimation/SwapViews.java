package com.ellen.tasknineanimation;

import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * swap the images
 * Created by ellen on 15/12/11.
 */
public class SwapViews implements Runnable {

    ImageView ivFlipMad;


    public SwapViews(ImageView ivFirst) {

        this.ivFlipMad = ivFirst;

    }

    @Override
    public void run() {

        final float centerX = ivFlipMad.getWidth() / 2.0f;
        final float centerY = ivFlipMad.getHeight() / 2.0f;

        Flip3dAnimation flip3dAnimation;


        flip3dAnimation = new Flip3dAnimation(0, 180.0f, centerX, centerY, 20.0f, true);


        flip3dAnimation.setDuration(1000);
        flip3dAnimation.setFillAfter(true);
        flip3dAnimation.setInterpolator(new DecelerateInterpolator());


        ivFlipMad.startAnimation(flip3dAnimation);


    }
}
