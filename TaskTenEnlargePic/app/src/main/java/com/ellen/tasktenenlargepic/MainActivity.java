package com.ellen.tasktenenlargepic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

/*
*
* Task Ten : Multi touch study
* example: Enlarge or shrink a picture using more than two fingers
*
*
* */
public class MainActivity extends AppCompatActivity{

    /*
    *
    * Using Custome Image View to display a picture and manipulate this pic
    *
    * */


    private ZoomImageView zivPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zivPic = (ZoomImageView) findViewById(R.id.zivPic);

        zivPic.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dog));


    }


}
