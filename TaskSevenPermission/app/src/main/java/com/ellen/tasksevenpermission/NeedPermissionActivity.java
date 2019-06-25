package com.ellen.tasksevenpermission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NeedPermissionActivity extends AppCompatActivity {

    public static String ACTION = "com.ellen.tasksevenpermission.intent.action.NeedPermissionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_permission);
    }
}
