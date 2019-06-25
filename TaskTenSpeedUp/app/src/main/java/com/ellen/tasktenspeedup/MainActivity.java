package com.ellen.tasktenspeedup;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
* Task Ten : Speed up
*
* Author : ellen
* 2015/12/18
* */
public class MainActivity extends AppCompatActivity {
    private static String TAG = "SpeedUp";

    //used to keep the value before/after speed up(clear all apps running background)
    private long mMemoryBefor, mMemoryAfter;

    //available memory now and total memory in phone
    private long availableMemory, totalMemory;

    ActivityManager am;
    List<ActivityManager.RunningAppProcessInfo> rapInfoList;
    ActivityManager.MemoryInfo memoryInfo;
    ActivityManager.RunningTaskInfo runningTaskInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        mMemoryBefor = getMemorySize();


        kill();

        mMemoryAfter = getMemorySize();

        String space = String.valueOf(mMemoryAfter - mMemoryBefor);
        Log.e(TAG, "Free " + space + "M");

        Toast.makeText(this, "Free " + space + "M", Toast.LENGTH_LONG).show();

        finish();
    }

    private long getMemorySize() {




        memoryInfo = new ActivityManager.MemoryInfo();

        am.getMemoryInfo(memoryInfo);

//        totalMemory = memoryInfo.totalMem / 1048576L;

        availableMemory = memoryInfo.availMem / 1048576L;

        Log.e(TAG, "TotalMemory : " + String.valueOf(totalMemory
                + "\n Available Memory : " + availableMemory));

        return availableMemory;
    }

    private void kill() {
        rapInfoList = am.getRunningAppProcesses();

        int count = 0;
        if (rapInfoList != null) {
            for (int i = 0; i < rapInfoList.size(); i++) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = rapInfoList.get(i);

                Log.e(TAG, "Process name is " + runningAppProcessInfo.processName);

                Log.e(TAG, "Its importance level is " + runningAppProcessInfo.importance);

                if (runningAppProcessInfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    String[] pkgList = runningAppProcessInfo.pkgList;

                    for (int j = 0; j < pkgList.length; j++) {
                        Log.e(TAG,"__________________killed____________________________");
                        Log.e(TAG, "" + pkgList[j] + "will be killed!");

                        am.killBackgroundProcesses(pkgList[j]);

                        Log.e(TAG, "--------------------");

                        count++;


                    }
                }
            }
        }
    }
}
