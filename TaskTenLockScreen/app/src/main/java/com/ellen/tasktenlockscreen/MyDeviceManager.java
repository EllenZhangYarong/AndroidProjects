package com.ellen.tasktenlockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ellen on 15/12/15.
 */
public class MyDeviceManager extends DeviceAdminReceiver {
    String ACTION ="com.ellen.tasktenlockscreen.MyDeviceManager";

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Log.e("MyDeviceManager","--------------->onEnabled");
        Toast.makeText(context, R.string.hintEnable,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        Log.e("MyDeviceManager", "-------------->onDisabled");
        Toast.makeText(context, R.string.hintDisabled,Toast.LENGTH_SHORT).show();
    }
}
