package com.ellen.tasktencompass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


/*
* Task Ten: Compass
*
* Author : ellen
* 2015/12/17
*
* */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    /*
    * Member Virable
    *
    * */
    private ImageView ivCompass;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;

    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];

    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;

    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];

    private float mCurrentDegree = 0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate Sensor manager and two sensors
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //Initiate UI component
        ivCompass = (ImageView) findViewById(R.id.ivCompass);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Register two sensors to listener
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //When this app go backstage, unregister these two sensor
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.unregisterListener(this, mMagnetometer);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mAccelerometer) {

            //Copy elements in event.value into Sensor object
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;

        } else if (event.sensor == mMagnetometer) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;

        }

        correctAngle();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Computes the device's orientation based on the rotation matric
    private void correctAngle() {

        if (mLastAccelerometerSet && mLastMagnetometerSet) {

            //Use it at the same time instead of Sensor.TYPE_ORIENTATION(deprecated)
            SensorManager.getRotationMatrix(mR, null, mLastAccelerometer, mLastMagnetometer);
            SensorManager.getOrientation(mR, mOrientation);

            //Turn radians to degree
            float inRadians = mOrientation[0];
            float inDegrees = (float) ((Math.toDegrees(inRadians) + 360) % 360);

            //set rotate animation
            if(Math.abs(inDegrees+mCurrentDegree)>3) {
                RotateAnimation rotateAnimation = new RotateAnimation(
                        mCurrentDegree,
                        -inDegrees,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

                rotateAnimation.setDuration(250);
                rotateAnimation.setFillAfter(true);
                ivCompass.startAnimation(rotateAnimation);
            }

            mCurrentDegree = -inDegrees;
        }
    }
}
