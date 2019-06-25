package com.ellen.tasksevenpermission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static String ACTION= "com.ellen.tasksevenpermission.intent.action.NeedPermissionActivity";
    private Button btnOpenPermissionActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenPermissionActivity = (Button) findViewById(R.id.btnOpenPermissionActivity);
        btnOpenPermissionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ACTION));
            }
        });
    }
}
