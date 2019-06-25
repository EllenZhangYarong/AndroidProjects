package com.ellen.taskeightsquares;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gridLayout = new GridLayout(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        for (int i = 0; i < 20; i++) {
            Button btnNums = new Button(this);
            btnNums.setText(i + 1 + "");
            btnNums.setBackgroundColor(Color.RED);
            btnNums.setTextSize(36);
            btnNums.setPadding(10, 20, 10, 20);
            btnNums.setTextColor(Color.WHITE);
            btnNums.setWidth(width / 4 - 20);
            btnNums.setHeight(height / 6 - 20);
            btnNums.setOnClickListener(this);

            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec colSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
            params.setMargins(10, 10, 10, 10);

            gridLayout.addView(btnNums, params);

        }
        setContentView(gridLayout);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,Main2Activity.class));

    }
}
