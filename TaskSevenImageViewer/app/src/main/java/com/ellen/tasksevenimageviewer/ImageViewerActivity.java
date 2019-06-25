package com.ellen.tasksevenimageviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

public class ImageViewerActivity extends AppCompatActivity {

    Bitmap bitmap;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        Uri uri = this.getIntent().getData();
        if (uri != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(), uri);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

        } else {
            bitmap = FileFromAssets.getBitmapFromAssets(this, "cat.jpg");
        }

        Drawable drawableCat = new BitmapDrawable(getResources(), bitmap);
        this.getWindow().setBackgroundDrawable(drawableCat);

    }
}
