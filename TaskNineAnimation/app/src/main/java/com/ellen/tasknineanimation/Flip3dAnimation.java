package com.ellen.tasknineanimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Tool class, to make a custom animation
 * Created by ellen on 15/12/11.
 */
public class Flip3dAnimation extends Animation {

    private float mFromDegrees;
    private float mToDegrees;
    private float mCenterX;
    private float mCenterY;
    private Camera mCamera;
    private boolean mReverse;
    private float mDepthZ;


    public Flip3dAnimation(float toDegrees, float fromDegrees,
                           float centerY, float centerX,
                           float depthZ, boolean reverse) {
        this.mCenterY = centerY;
        this.mCenterX = centerX;
        this.mToDegrees = toDegrees;
        this.mFromDegrees = fromDegrees;
        this.mDepthZ = depthZ;
        this.mReverse = reverse;
    }


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime;

        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();

        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}



