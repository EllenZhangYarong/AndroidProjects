package com.ellen.taskeightsquares;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CustomView extends View {
    private String mExampleString = ""; // TODO: use a default from R.string...
    private int mExampleColor = Color.WHITE; // TODO: use a default from R.color...
    private float mExampleDimension = 100; // TODO: use a default from R.dimen...
    //private Drawable mExampleDrawable;


    //Rect and its brush
    private Rect mRect;
    private Paint mPaint;
    private int mRectColor = Color.RED;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public CustomView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.CustomView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.CustomView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.CustomView_exampleDimension,
                mExampleDimension);
        mRectColor = a.getColor(R.styleable.CustomView_rectColor, mRectColor);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mPaint = new Paint();


        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {

        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
//        System.out.println("invalidateTextPanit---\n" + mExampleString);
//        System.out.println("----------------------------------------");
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;

        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        invalidateTextPaintAndMeasurements();

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        //Draw a square
        mRect = new Rect(paddingLeft, paddingTop, contentWidth, contentHeight);
        canvas.drawRect(mRect, mPaint);

        //Draw Text on top of Square
        canvas.drawText(mExampleString, getWidth() / 2, getHeight() / 2 + mTextHeight, mTextPaint);

    }


    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        System.out.println("setExampleString---------\n" + exampleString);
        System.out.println("---------------------------------------------");
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;

        invalidateTextPaintAndMeasurements();
    }

//    /**
//     * Gets the example drawable attribute value.
//     *
//     * @return The example drawable attribute value.
//     */
//    public Drawable getExampleDrawable() {
//        return mExampleDrawable;
//    }
//
//    /**
//     * Sets the view's example drawable attribute value. In the example view, this drawable is
//     * drawn above the text.
//     *
//     * @param exampleDrawable The example drawable attribute value to use.
//     */
//    public void setExampleDrawable(Drawable exampleDrawable) {
//        mExampleDrawable = exampleDrawable;
//    }

    /**
     * Gets the Square attribute value.
     *
     * @return The Square attribute value.
     */
    public Rect getmRect() {
        return mRect;
    }

    public void setmRect(Rect mRect) {
        this.mRect = mRect;
    }
}
