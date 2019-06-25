package com.ellen.tasknine2dcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/*
* Task 9:
* Complete a 2D picture turn around
*
* */
public class MainActivity extends AppCompatActivity {

    //Two ImageView component
    private ImageView ivCat, ivDog;


    //    Two ScaleAnimation
    private ScaleAnimation sa1 = new ScaleAnimation(1, 0, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.5f,
            Animation.RELATIVE_TO_PARENT, 0.5f);

    private ScaleAnimation sa2 = new ScaleAnimation(0, 1, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.5f,
            Animation.RELATIVE_TO_PARENT, 0.5f);
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Tag", "Never be here");
//                ivCat.setImageResource(R.drawable.dog);
//                ivCat.setAnimation(sa1);

                if (ivCat.getVisibility() == View.VISIBLE) {
                    ivCat.startAnimation(sa1);

                } else {
                    ivDog.startAnimation(sa1);
                }

            }
        });
    }

    //    Initiate View, as general, find view by id
    private void initView() {

        ivCat = (ImageView) findViewById(R.id.ivCat);
        ivDog = (ImageView) findViewById(R.id.ivDog);

        showCat();


        sa1.setDuration(1000);
        sa2.setDuration(1000);

        sa1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (ivCat.getVisibility() == View.VISIBLE) {
                    ivCat.setAnimation(null);
                    showDog();
                    ivDog.startAnimation(sa2);
                } else {
                    ivDog.setAnimation(null);
                    showCat();
                    ivCat.startAnimation(sa2);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //    Display the different ImageView
    private void showCat() {

        ivCat.setVisibility(View.VISIBLE);
        ivDog.setVisibility(View.INVISIBLE);

    }

    //    Display the different ImageView
    private void showDog() {

        ivDog.setVisibility(View.VISIBLE);
        ivCat.setVisibility(View.INVISIBLE);

    }

}
