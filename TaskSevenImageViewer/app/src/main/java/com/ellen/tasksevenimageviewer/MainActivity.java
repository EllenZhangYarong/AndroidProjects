package com.ellen.tasksevenimageviewer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static String PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static String PACKNAME = "com.ellen.tasksevenimageviewer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        int isChecked = this.getPackageManager().checkPermission(PERMISSION, PACKNAME);
        if (isChecked == PackageManager.PERMISSION_GRANTED) {
            FileFromAssets.copyToSDCard(this);
        } else {
            Toast.makeText(this, R.string.hint,
                    Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.btnOpenImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File imageCat = new File(Environment.getExternalStorageDirectory() + "/" + "cat.jpg");

                if (imageCat.exists()) {

                    Uri uriCat = Uri.fromFile(imageCat);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(uriCat, "image/*");
                    startActivity(intent);

                }
            }
        });
    }
}
