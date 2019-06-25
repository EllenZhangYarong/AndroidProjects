package com.ellen.tasktenlockscreen;

import android.app.admin.DevicePolicyManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class LockActivity extends AppCompatActivity {

    private DevicePolicyManager devicePolicyManager;
    private ComponentName activityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        List<ComponentName> list = devicePolicyManager.getActiveAdmins();

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Log.e("DPMLockScreen", "" + list.get(i));
            }
            devicePolicyManager.lockNow();

            finish();

        }else {

            Log.e("DPMLockScreen", "No Active Device Policy Manager" );
            try {
                Intent intent = new Intent(this,MainActivity.class);
                startActivityForResult(intent, 1);

            } catch (ActivityNotFoundException e) {

                Log.e("LockActivity", "Not found this acitivty");

            }
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
