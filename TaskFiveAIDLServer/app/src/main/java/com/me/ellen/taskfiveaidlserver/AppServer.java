package com.me.ellen.taskfiveaidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

public class AppServer extends Service {
    private RemoteCallbackList<TimerServiceCallBack> callBackRemoteCallbackList = new RemoteCallbackList<>();
    private boolean isRunning = false;
    private int count = 0;
    public AppServer() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: 15/11/17
        return new IAppServiceAIDLInterface.Stub() {
            @Override
            public void registCallBack(TimerServiceCallBack callback) throws RemoteException {
                callBackRemoteCallbackList.register(callback);

            }

            @Override
            public void unregistCallBack(TimerServiceCallBack callback) throws RemoteException {

                callBackRemoteCallbackList.unregister(callback);
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;

        new Thread(){
           public void run(){
               super.run();
               for (count=0;isRunning;count++){
                   int iCount = callBackRemoteCallbackList.beginBroadcast();

                   while (iCount>0){
                       iCount--;
                       try {
                           callBackRemoteCallbackList.getBroadcastItem(iCount).onTimer(count);
                       } catch (RemoteException e) {
                           e.printStackTrace();
                       }
                   }

                   callBackRemoteCallbackList.finishBroadcast();

                   try {
                       sleep(1000);
                       System.out.println(count);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

               }

           }
        }.start();

        System.out.println("Service is created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;

        System.out.println("Service is destroyed");
    }
}
