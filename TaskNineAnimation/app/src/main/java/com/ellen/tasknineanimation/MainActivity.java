package com.ellen.tasknineanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


/*
*
* TaskNineAnimation
* Android Training Task Nine
* Task One :
* Require to practise four ways to make a Animation
* This is the homework one of Task nine.
*
* Author: ellen
* 2015.12.10
* v1
* */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //The members: four buttons and one imageview
    private Button btnViewXml;
    private Button btnViewCode;
    private Button btnPropertyXml;
    private Button btnPropertyCode;

    private ImageView ivFlipMad;

    private AnimationSet animationSet;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    //Initiate the view. The main action is to find every component
    private void initView() {

        btnViewXml = (Button) findViewById(R.id.btnViewXml);
        btnViewCode = (Button) findViewById(R.id.btnViewCode);
        btnPropertyXml = (Button) findViewById(R.id.btnPropertyXml);
        btnPropertyCode = (Button) findViewById(R.id.btnPropertyCode);

        btnViewXml.setOnClickListener(this);
        btnViewCode.setOnClickListener(this);
        btnPropertyXml.setOnClickListener(this);
        btnPropertyCode.setOnClickListener(this);

        ivFlipMad = (ImageView) findViewById(R.id.ivFlipMad);
        ivFlipMad.setOnClickListener(this);
        ivFlipMad.setFocusable(true);
        ivFlipMad.setClickable(true);


    }

    //Every button and imageview has a click event, we can write them in one piece of code.
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnViewXml:

                animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.trans);
                v.startAnimation(animationSet);

                break;
            case R.id.btnViewCode:

                animationSet = new AnimationSet(true);

                TranslateAnimation tranOne = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.5f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f
                );
                tranOne.setFillAfter(true);
                tranOne.setInterpolator(new LinearInterpolator());
                tranOne.setDuration(1000);

                animationSet.addAnimation(tranOne);

                TranslateAnimation tranTwo = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.2f);

                tranTwo.setStartOffset(1000);
                tranTwo.setDuration(1000);
                tranTwo.setInterpolator(new LinearInterpolator());

                animationSet.addAnimation(tranTwo);

                v.startAnimation(animationSet);

                break;

            case R.id.btnPropertyXml:

                animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.trans_property);
                animatorSet.setTarget(btnPropertyXml);
                animatorSet.start();


                break;
            case R.id.btnPropertyCode:

                animatorSet = new AnimatorSet();

                ObjectAnimator oaOne = ObjectAnimator.ofFloat(btnPropertyCode, "translationX", 0, 250.0f);

                ObjectAnimator oaTwo = ObjectAnimator.ofFloat(btnPropertyCode, "translationY", 0, 200.0f);

                oaTwo.setRepeatMode(Animation.REVERSE);
                oaTwo.setRepeatCount(1);

                ObjectAnimator oaThree = ObjectAnimator.ofFloat(btnPropertyCode, "translationX", 250f, 0);

                animatorSet.playSequentially(oaOne, oaTwo, oaThree);
                animatorSet.setDuration(1000);

                animatorSet.setInterpolator(new LinearInterpolator());
                animatorSet.start();

                break;

            case R.id.ivFlipMad:

                applyRotation(180.0f, 0);


        }

    }

    private void applyRotation(float start, float stop) {

        final float centerX = ivFlipMad.getWidth() / 2.0f;
        final float centerY = ivFlipMad.getHeight() / 2.0f;

        final Flip3dAnimation rotate3d = new Flip3dAnimation(start, stop, centerX, centerY, 20.0f, true);
        rotate3d.setDuration(1000);
        rotate3d.setFillAfter(true);
        rotate3d.setInterpolator(new DecelerateInterpolator());
        rotate3d.setAnimationListener(new DisplayNextView(ivFlipMad));


        ivFlipMad.startAnimation(rotate3d);

    }

}




