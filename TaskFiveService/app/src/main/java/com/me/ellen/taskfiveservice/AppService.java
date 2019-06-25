package com.me.ellen.taskfiveservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class AppService extends Service {

    private String data = "AppService is running : ";
    private boolean isRunning = false;
    private Intent intent;

    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();

    }

    public class MyBinder extends Binder {
        public void setData(String data){
            AppService.this.data = data;
        }

        public AppService getService(){
            return AppService.this;
        }

    }



    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service Started.......");


        new Thread() {
            public void run() {
                super.run();
                int count = 0;
                isRunning = true;
                while (isRunning) {

                    try {
                        Thread.sleep(1000);
                        count++;
                        String str = data + count;
                        System.out.println(str);
                        if(callBack!=null){
                            callBack.onDataChange(str);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }

            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service Stopped.......");
        isRunning = false;
    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    private CallBack callBack = null;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public static interface CallBack{
        void onDataChange(String data);
    }
}
